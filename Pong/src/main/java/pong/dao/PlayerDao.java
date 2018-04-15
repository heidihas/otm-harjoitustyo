/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pong.database.Database;
import pong.domain.Player;

/**
 *
 * @author Heidi
 */
public class PlayerDao {
    private Database database;

    public PlayerDao(Database database) {
        this.database = database;
    }
    
    public List<Player> findAll() throws SQLException {
        List<Player> players = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, name, score FROM Player").executeQuery()) {

            while (result.next()) {
                players.add(new Player(result.getInt("id"), result.getString("name"), result.getInt("score")));
            }
        }

        return players;
    }
    
    public List<Player> findFiveTop() throws SQLException {
        List<Player> players = new ArrayList<>();
        
        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id AS id, name AS name, score AS score FROM Player GROUP BY name ORDER BY score DESC LIMIT 5").executeQuery()) {
            
            while (result.next()) {
                players.add(new Player(result.getInt("id"), result.getString("name"), result.getInt("score")));
            }      
        }
        return players;
    }
    
    public Player findOneByName(String name) throws SQLException {

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name, score FROM Player WHERE name = ?");
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new Player(result.getInt("id"), result.getString("name"), result.getInt("score"));
            }
        }

        return null;
    }
    
    public Player saveOrUpdate(Player object) throws SQLException {
        Player byName = findOneByName(object.getName());

        if (byName != null) {
            try (Connection conn = database.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement("UPDATE Player SET score = ? WHERE name = ?");
                stmt.setInt(1, object.getScore() + byName.getScore());
                stmt.setString(2, object.getName());
                stmt.executeUpdate();
            }
            return byName;
        } 

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Player (name, score) VALUES (?, ?)");
            stmt.setString(1, object.getName());
            stmt.setInt(2, object.getScore());
            stmt.executeUpdate();
        }

        return findOneByName(object.getName());
    }

}
