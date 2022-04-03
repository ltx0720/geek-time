package com.example.week05.T3;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class JDBC {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        JDBC jdbc = new JDBC();

        jdbc.testHaKaRiCP();
    }

    public void test() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
        options(connection, true);
    }

    public void testTransaction() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
        options(connection, false);
    }

    private void testHaKaRiCP() throws SQLException {
        HikariConfig config = new HikariConfig("/hikaricp.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        Connection connection = dataSource.getConnection();
        options(connection, true);
    }

    private void options(Connection connection, boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
        String insert = "insert into people (name) values ('ltx')";
        String query = "select * from people";
        String delete = "delete from people where name = 'ltx1'";
        String update = "update people set  name = 'ltx1' where name = 'ltx'";
        Statement statement = connection.createStatement();
        try {
            statement.execute(insert);
            ResultSet resultSet = statement.executeQuery(query);
            statement.executeUpdate(update);
            statement.execute(delete);
            if (!autoCommit) {
                connection.commit();
            }
        } catch (Exception ex) {
            if (!autoCommit) {
                connection.rollback();
            }
            throw ex;
        } finally {
            statement.close();
            connection.close();
        }

    }

}
