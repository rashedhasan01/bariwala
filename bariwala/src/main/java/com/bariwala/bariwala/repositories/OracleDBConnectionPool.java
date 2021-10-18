package com.bariwala.bariwala.repositories;

import com.bariwala.bariwala.utilities.APIConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleDBConnectionPool {

    private static OracleDBConnectionPool oracleDBConnectionPool;
    private APIConfiguration apiConfiguration;
    private HikariConfig apiDbhikariConfig;
    private DataSource apiDBdataSource;

    private HikariConfig bariwalaDbhikariConfig;
    private DataSource bariwalaDBdataSource;

    private OracleDBConnectionPool()
    {
        apiConfiguration = APIConfiguration.getInstance();
        apiDbhikariConfig = new HikariConfig();
        apiDbhikariConfig.setJdbcUrl("jdbc:oracle:thin:@"+apiConfiguration.getApiDBhost()+":"+apiConfiguration.getApiDBport()+":"+apiConfiguration.getApiDBAlias());
        apiDbhikariConfig.setUsername(apiConfiguration.getApiDBUser());
        apiDbhikariConfig.setPassword(apiConfiguration.getApiDBPassword());
        apiDbhikariConfig.setMaximumPoolSize(apiConfiguration.getApiDBMaximumPollSize());
        apiDbhikariConfig.setAutoCommit(apiConfiguration.getAutoCommit());
        apiDbhikariConfig.addDataSourceProperty("cachePrepStmts",apiConfiguration.getCachePrepStmts());
        apiDbhikariConfig.addDataSourceProperty("prepStmtCacheSize", apiConfiguration.getPrepStmtCacheSize());
        apiDbhikariConfig.addDataSourceProperty("prepStmtCacheSqlLmit", apiConfiguration.getPrepStmtCacheSqlLmit());
        apiDbhikariConfig.setPoolName("apiDBhikariCp");
        apiDBdataSource = new HikariDataSource(apiDbhikariConfig);


        apiConfiguration = APIConfiguration.getInstance();
        bariwalaDbhikariConfig = new HikariConfig();
        bariwalaDbhikariConfig.setJdbcUrl("jdbc:oracle:thin:@"+apiConfiguration.getBariwalaDBhost()+":"+apiConfiguration.getBariwalaDBport()+":"+apiConfiguration.getBariwalaDBAlias());
        bariwalaDbhikariConfig.setUsername(apiConfiguration.getBariwalaDBUser());
        bariwalaDbhikariConfig.setPassword(apiConfiguration.getBariwalaDBPassword());
        bariwalaDbhikariConfig.setMaximumPoolSize(apiConfiguration.getApiDBMaximumPollSize());
        bariwalaDbhikariConfig.setAutoCommit(apiConfiguration.getAutoCommit());
        bariwalaDbhikariConfig.addDataSourceProperty("cachePrepStmts",apiConfiguration.getCachePrepStmts());
        bariwalaDbhikariConfig.addDataSourceProperty("prepStmtCacheSize", apiConfiguration.getPrepStmtCacheSize());
        bariwalaDbhikariConfig.addDataSourceProperty("prepStmtCacheSqlLmit", apiConfiguration.getPrepStmtCacheSqlLmit());
        bariwalaDbhikariConfig.setPoolName("apiDBhikariCp");
        bariwalaDBdataSource = new HikariDataSource(bariwalaDbhikariConfig);

    }


    public static OracleDBConnectionPool getInstance()
    {
        if(oracleDBConnectionPool == null)
        {
            oracleDBConnectionPool = new OracleDBConnectionPool();
        }
        return oracleDBConnectionPool;
    }

    public Connection getApiDBConnection() throws SQLException
    {
        return apiDBdataSource.getConnection();
    }

    public Connection getBariwalaDBConnection() throws SQLException
    {
        return bariwalaDBdataSource.getConnection();
    }



}
