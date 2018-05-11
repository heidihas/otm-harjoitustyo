/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.database.Database;

/**
 *
 * @author Heidi
 */
public class DatabaseTest {
    
    Database database;
    
    @Before
    public void setUp() {
        database = new Database("jdbc:sqlite:test.db");
    }
    
    @Test
    public void createdDatabaseExists() {
        assertTrue(database != null);      
    }
    
    @Test
    public void getConnection() throws SQLException {
        Connection conn = database.getConnection();
        assertTrue(conn != null);    
    }
    
    @Test
    public void tableExistsNot() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Paddle");
        assertFalse(getTable("Ball"));
    }

    @Test
    public void tableExists() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Score");
        assertTrue(getTable("Score"));
    }
    
    @Test
    public void columnExistsNot() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Player");
        assertFalse(getColumn("Player", "age", "varchar"));
    }
    
    @Test
    public void columnNameExists() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Player");
        assertTrue(getColumn("Player", "name", "varchar"));
    }
    
    @Test
    public void columnScoreExists() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Player");
        assertTrue(getColumn("Player", "score", "integer"));
    }
    
    @Test
    public void columnIdExists() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Player");
        assertTrue(getColumn("Player", "id", "integer"));
    }
    
    @Test
    public void initNotNeeded() throws SQLException {
        dropTables();
        database.getConnection();
        database.init("Player");
        assertTrue(database.init("Player") == null);
    }
    
    @Test
    public void initUpdates() throws SQLException {
        dropTables();
        database.getConnection();
        Statement stmt = database.init("Movement");
        assertTrue(stmt != null);
    }
    
    public boolean getTable(String table) throws SQLException {
        createdDatabaseExists();

        try (Connection conn = database.getConnection()) {
            ResultSet result = conn.getMetaData().getTables(null, null, table, null);
            return result.next();
        }
    }
    
    public void dropTables() throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt1 = conn.prepareStatement("SELECT name FROM sqlite_master "
                    + "WHERE type='table'");
            ResultSet result = stmt1.executeQuery();

            List<String> tables = new ArrayList<>();
            
            while (result.next()) {
                tables.add(result.getString(1));
            }
            
            for (String table : tables) {
                PreparedStatement stmt2 = conn.prepareStatement("DROP TABLE IF EXISTS " + table);
                stmt2.executeUpdate();
            }
            
            conn.close();
        }
    }
    
    public boolean getColumn(String table, String columnName, String type) throws SQLException {
        Optional<Column> name = getTableColumn(table).stream().filter(s -> s.name.toLowerCase()
                .equals(columnName)).findFirst();
        if (!name.isPresent()) {
            return false;
        }
        
        return name.get().type.toLowerCase().trim().equals(type);
    }
    
    List<Column> getTableColumn(String table) throws SQLException {
        createdDatabaseExists();

        try (Connection conn = database.getConnection()) {
            ResultSet result = conn.prepareStatement("SELECT * FROM " + table).executeQuery();
            ResultSetMetaData meta = result.getMetaData();

            List<Column> columns = new ArrayList<>();
            for (int i = 0; i < meta.getColumnCount(); i++) {
                Column c = new Column();
                c.name = meta.getColumnName(i + 1);
                c.type = meta.getColumnTypeName(i + 1);

                columns.add(c);
            }

            return columns;
        }
    }

    static class Column {

        String name;
        String type;
    }
}