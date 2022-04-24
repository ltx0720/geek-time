package com.example.week07;

import java.sql.*;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class MySQLTest {

    private static final Random random = new Random();

    public static void main(String[] args) throws SQLException {
        MySQLTest test = new MySQLTest();
        // 100w
        int count = 10000000;
        test.testExecute(count);
//        test.testPrepared(count);
//        test.testPreparedAndBatch(count);
    }
    /**
     * 原生 sql
     */
    public void testExecute(int count) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "1234");
        Statement statement = connection.createStatement();
        long start = System.currentTimeMillis();
        connection.setAutoCommit(false);
        while (count-- > 0) {
            Order order = buildOrder();
            String insert = "insert into `order` (orderNo, user_id, goods_id, count, create_time, update_time) values (" +
                    "'" + order.getOrderNo() + "'" + "," +
                    "'" + order.getUserId() + "" + "'" + "," +
                    "'" + order.getGoodsId() + "" + "'" + "," +
                    "'" + order.getCount() + "" + "'" + "," +
                    "CURRENT_DATE()" + "," +
                    "CURRENT_DATE()" +
                    ")";
            try {

                statement.execute(insert);
//                connection.commit();
                connection.rollback();
            } catch (Exception ex) {
                System.out.println(insert);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("testExecute time: " + (end - start) + "");
    }


    /**
     * 预编译
     */
    public void testPrepared(int count) throws SQLException {
        String insertSqlPrepared = "insert into `order` (orderNo, user_id, goods_id, count, create_time, update_time) values (?, ?, ?, ?, CURRENT_DATE(), CURRENT_DATE())";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
        PreparedStatement ptmt = connection.prepareStatement(insertSqlPrepared);
        long start = System.currentTimeMillis();
        while (count-- > 0) {
            Order order = buildOrder();
            ptmt.setString(1, order.getOrderNo());
            ptmt.setLong(2, order.getUserId());
            ptmt.setInt(3, order.getGoodsId());
            ptmt.setInt(4, order.getCount());
            try {
                ptmt.execute();
            } catch (Exception ex) {
                System.out.println(order);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("testPrepared time: " + (end - start) + "");
    }


    /**
     * 预编译批处理
     */
    public void testPreparedAndBatch(int count) throws SQLException {
        String insertSqlPrepared = "insert into `order` (orderNo, user_id, goods_id, count, create_time, update_time) values (?, ?, ?, ?, CURRENT_DATE(), CURRENT_DATE())";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
        PreparedStatement ptmt = connection.prepareStatement(insertSqlPrepared);
        long start = System.currentTimeMillis();
        int c = 0;

        while (count-- > 0) {
            Order order = buildOrder();
            ptmt.setString(1, order.getOrderNo());
            ptmt.setLong(2, order.getUserId());
            ptmt.setInt(3, order.getGoodsId());
            ptmt.setInt(4, order.getCount());
            if (c == 1000) {
                try {
                    c = 0;
                    ptmt.execute();
                } catch (Exception ex) {
                    System.out.println(order);
                }
            } else {
                ptmt.addBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("testPreparedAndBatch time: " + (end - start) + "");
    }


    public Order buildOrder() {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setGoodsId(random.nextInt(10000));
        order.setCount(random.nextInt(10000));
        order.setUserId(random.nextLong());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return order;
    }
}
