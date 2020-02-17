package com.railvayticketiffice.persistance;

import org.apache.log4j.Logger;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static com.railvayticketiffice.constant.AppConstants.*;

public class DataSourceFactory {

    private static final Logger LOG = Logger.getLogger(DataSourceFactory.class);

    private static DataSource dataSource;

    private DataSourceFactory() {
    }

    static {
        Properties properties = new Properties();
        try {
            properties.load(DataSourceFactory.class.getResourceAsStream(DB_PROPERTIES));
            PGPoolingDataSource pgDataSource = new PGPoolingDataSource();
            pgDataSource.setServerName(properties.getProperty(DB_HOST));
            pgDataSource.setPortNumber(5432);
            pgDataSource.setDatabaseName(properties.getProperty(DB_NAME));
            pgDataSource.setUser(properties.getProperty(DB_USERNAME));
            pgDataSource.setPassword(properties.getProperty(DB_PASSWORD));
            dataSource = pgDataSource;
            LOG.info("DataSource created: " + dataSource);
        } catch (IOException e) {
            LOG.error("Error while reading properties from file!", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Error while connection creation", e);
        }
        return connection;
    }

}
