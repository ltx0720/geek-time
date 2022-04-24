package com.example.week07.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static final String DATABASE_1 = "primaryDataSource";

    public static final String DATABASE_2 = "secondDataSource";

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

    public static void determineDataSource(String dataBaseName) {
        if (DATABASE_2.equals(dataBaseName)) {
            setDataSource(DATABASE_2);
            return;
        }
        setDataSource(DATABASE_1);
    }
}
