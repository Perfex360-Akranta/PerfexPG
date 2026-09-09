package com.akranta.tpm.model;

import com.zaxxer.hikari.HikariDataSource;

public class DataSoursePool {
	private HikariDataSource dataSource;
	public DataSoursePool(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
