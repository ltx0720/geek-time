package com.example.week08.分库分表;

public class DbParam {

    private String userId;

    private String databaseName;

    private String tableName;

    public DbParam(String userId, String databaseName, String tableName) {
        this.userId = userId;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public DbParam() {
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
