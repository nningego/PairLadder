package hsqldb;


import org.hsqldb.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Data_connection {

    public static void main(String[] args) throws
            ClassNotFoundException, SQLException {

        // 'Server' is a class of HSQLDB representing
        // the database server
        Server hsqlServer = null;
        try {
            hsqlServer = new Server();

            // HSQLDB prints out a lot of informations when
            // starting and closing, which we don't need now.
            // Normally you should point the setLogWriter
            // to some Writer object that could store the logs.
            hsqlServer.setLogWriter(null);
            hsqlServer.setSilent(true);

            hsqlServer.setDatabaseName(0, "pairLadder");
            hsqlServer.setDatabasePath(0, "file:src/hsqldb/db_tables/pair");


            hsqlServer.start();

            Connection connection = null;
            // We have here two 'try' blocks and two 'finally'
            // blocks because we have two things to close
            // after all - HSQLDB server and connection
            try {
                // Getting a connection to the newly started database
                Class.forName("org.hsqldb.jdbcDriver");
                // Default user of the HSQLDB is 'sa'
                // with an empty password
                connection = DriverManager.getConnection(
                        "jdbc:hsqldb:hsql://localhost/pairLadder", "sa", "");

                // Here we run a few SQL statements to see if
                // everything is working.
                // We first drop an existing 'testtable' (supposing
                // it was there from the previous run), create it
                // once again, insert some data and then read it
                // with SELECT query.
                connection.prepareStatement("drop table pair;").execute();
                connection.prepareStatement("create table pair ( id INTEGER, name1 VARCHAR(10), name2 VARCHAR(10));").execute();
                connection.prepareStatement("insert into pair(id, name1, name2) values (1, 'testdev1', 'testdev2');").execute();
                ResultSet rs = connection.prepareStatement("select * from pair;").executeQuery();

                // Checking if the data is correct
                rs.next();
                System.out.println("Id: " + rs.getInt(1) + " Name1: "+ rs.getString(2)+ " Name2:" + rs.getString(3));
            } finally {
                // Closing the connection
                if (connection != null) {
                    connection.close();
                }

            }
        } finally {
            // Closing the server
            if (hsqlServer != null) {
                hsqlServer.stop();
            }
        }
    }
}