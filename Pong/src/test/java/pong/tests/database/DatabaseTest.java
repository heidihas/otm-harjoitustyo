/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tests.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File file = new File("db", "player.db");
        try {
            database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void createdDatabaseExists() {
        assertTrue(database!=null);      
    }
    
    @Test
    public void tableExists() throws SQLException {
        getTable("Player");
    }

    @Test
    public void columnNameExists() throws SQLException {
        getColumn("Player", "name", "varchar");
    }
    
    @Test
    public void getConnection() {
        try {
            Connection conn = database.getConnection();
            assertTrue(conn != null); 
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public void getColumn(String table, String columnName, String type) throws SQLException {
        Optional<Column> name = getTableColumn(table).stream().filter(s -> s.name.toLowerCase().equals(columnName)).findFirst();
        if (!name.isPresent()) {
            fail("Table " + table + " lacks the column \"" + columnName + "\".");
        }

        if (!name.get().type.toLowerCase().trim().equals(type)) {
            fail("Table " + table + " should have the column of type " + type + ". Now the type was " + name.get().type);
        }
    }
    
    List<Column> getTableColumn(String table) throws SQLException {
        createdDatabaseExists();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseFile().getAbsolutePath())) {
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

    public ResultSet getTable(String table) throws SQLException {
        createdDatabaseExists();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseFile().getAbsolutePath())) {
            ResultSet result = conn.getMetaData().getTables(null, null, table, null);
            if (!result.next()) {
                fail("Directory \"db\" has the file \"tasks.db\" but doesn't have the table " + table + ".");
            }

            return result;
        }
    }

    static File databaseFile() {
        return new File("db", "player.db");
    }

    static class Column {

        String name;
        String type;
    }
}
