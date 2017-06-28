package org.lmarcich;

import java.sql.*;

public class SQLiteJDBC {

    private static final String DATABASE_URL = "jdbc:sqlite::memory:";

    static void createTable(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Table created successfully");
    }

    static void insert(Connection c) throws SQLException {
        c.setAutoCommit(false);
        Statement stmt = c.createStatement();
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
        stmt.executeUpdate(sql);

        stmt.close();
        c.commit();

        System.out.println("Records inserted successfully");
    }


    static void select(Connection c) throws SQLException {
        c.setAutoCommit(false);
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String address = rs.getString("address");
            float salary = rs.getFloat("salary");

            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println("AGE = " + age);
            System.out.println("ADDRESS = " + address);
            System.out.println("SALARY = " + salary);
            System.out.println();
        }
        rs.close();
        stmt.close();

        System.out.println("Selection performed successfully");
    }


    public static void main(String args[]) {
        try {
            Connection c = DriverManager.getConnection(DATABASE_URL);
            createTable(c);
            insert(c);
            select(c);
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}


