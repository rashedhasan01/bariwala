package com.bariwala.bariwala.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class APIConfiguration {
    private Properties config;
    private final String CONFIG_FILE_NAME = "application.properties";
    private String apiDBAlias;
    private String apiDBUser;
    private String apiDBPassword;
    private String apiDBhost;
    private String apiDBport;

    private String bariwalaDBAlias;
    private String bariwalaDBUser;

    public String getBariwalaDBAlias() {
        return bariwalaDBAlias;
    }

    public void setBariwalaDBAlias(String bariwalaDBAlias) {
        this.bariwalaDBAlias = bariwalaDBAlias;
    }

    public String getBariwalaDBUser() {
        return bariwalaDBUser;
    }

    public void setBariwalaDBUser(String bariwalaDBUser) {
        this.bariwalaDBUser = bariwalaDBUser;
    }

    public String getBariwalaDBPassword() {
        return bariwalaDBPassword;
    }

    public void setBariwalaDBPassword(String bariwalaDBPassword) {
        this.bariwalaDBPassword = bariwalaDBPassword;
    }

    public String getBariwalaDBhost() {
        return bariwalaDBhost;
    }

    public void setBariwalaDBhost(String bariwalaDBhost) {
        this.bariwalaDBhost = bariwalaDBhost;
    }

    public String getBariwalaDBport() {
        return bariwalaDBport;
    }

    public void setBariwalaDBport(String bariwalaDBport) {
        this.bariwalaDBport = bariwalaDBport;
    }

    private String bariwalaDBPassword;
    private String bariwalaDBhost;
    private String bariwalaDBport;

    private int apiDBMaximumPollSize;
    private String autoCommit;
    private String cachePrepStmts;
    private String prepStmtCacheSize;
    private String prepStmtCacheSqlLmit;
    private String serviceIP;
    private String requestLogger;
    private String responseLogger;
    private String logPath;


    public APIConfiguration() {
        this.loadConfig();
        this.loadProperties();
    }
    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }

    public String getCONFIG_FILE_NAME() {
        return CONFIG_FILE_NAME;
    }

    public String getApiDBAlias() {
        return apiDBAlias;
    }

    public void setApiDBAlias(String apiDBAlias) {
        this.apiDBAlias = apiDBAlias;
    }

    public String getApiDBUser() {
        return apiDBUser;
    }

    public void setApiDBUser(String apiDBUser) {
        this.apiDBUser = apiDBUser;
    }

    public String getApiDBPassword() {
        return apiDBPassword;
    }

    public void setApiDBPassword(String apiDBPassword) {
        this.apiDBPassword = apiDBPassword;
    }

    public String getApiDBhost() {
        return apiDBhost;
    }

    public void setApiDBhost(String apiDBhost) {
        this.apiDBhost = apiDBhost;
    }

    public String getApiDBport() {
        return apiDBport;
    }

    public void setApiDBport(String apiDBport) {
        this.apiDBport = apiDBport;
    }



    public int getApiDBMaximumPollSize() {
        return apiDBMaximumPollSize;
    }

    public void setApiDBMaximumPollSize(int apiDBMaximumPollSize) {
        this.apiDBMaximumPollSize = apiDBMaximumPollSize;
    }

    public boolean getAutoCommit() {
        return autoCommit.toUpperCase().equals("Y");
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getCachePrepStmts() {
        return cachePrepStmts;
    }

    public void setCachePrepStmts(String cachePrepStmts) {
        this.cachePrepStmts = cachePrepStmts;
    }

    public String getPrepStmtCacheSize() {
        return prepStmtCacheSize;
    }

    public void setPrepStmtCacheSize(String prepStmtCacheSize) {
        this.prepStmtCacheSize = prepStmtCacheSize;
    }

    public String getPrepStmtCacheSqlLmit() {
        return prepStmtCacheSqlLmit;
    }

    public void setPrepStmtCacheSqlLmit(String prepStmtCacheSqlLmit) {
        this.prepStmtCacheSqlLmit = prepStmtCacheSqlLmit;
    }

    public String getServiceIP() {
        return serviceIP;
    }

    public void setServiceIP(String serviceIP) {
        this.serviceIP = serviceIP;
    }

    public Boolean getRequestLogger() { return  requestLogger.toUpperCase().equals("Y"); }

    public void setRequestLogger(String requestLogger) {
        this.requestLogger = requestLogger;
    }

    public String getResponseLogger() {
        return responseLogger;
    }

    public void setResponseLogger(String responseLogger) {
        this.responseLogger = responseLogger;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public static APIConfiguration getApiConfiguration() {
        return apiConfiguration;
    }

    public static void setApiConfiguration(APIConfiguration apiConfiguration) {
        APIConfiguration.apiConfiguration = apiConfiguration;
    }






    private static APIConfiguration apiConfiguration;

    public static APIConfiguration getInstance() {
        if (apiConfiguration == null) {
            apiConfiguration = new APIConfiguration();
        }
        return apiConfiguration;
    }

    private void loadConfig() {
        config = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/" + CONFIG_FILE_NAME);
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            try {
                config.load(inputStreamReader);
            } catch (IOException ex) {

            }

        }
    }

    private void loadProperties() {
        try {
            apiDBhost = config.getProperty("apiDBhost");
            apiDBport = config.getProperty("apiDBport");
            apiDBAlias = config.getProperty("apiDBAlias");
            apiDBUser = config.getProperty("apiDBUser");
            apiDBPassword = config.getProperty("apiDBPassword");

            bariwalaDBhost = config.getProperty("bariwaraDBhost");
            bariwalaDBport = config.getProperty("bariwaraport");
            bariwalaDBAlias = config.getProperty("bariwaraDBAlias");
            bariwalaDBUser = config.getProperty("bariwaraDBUser");
            bariwalaDBPassword = config.getProperty("bariwaraDBPassword");

            logPath = config.getProperty("logPath");
            serviceIP = config.getProperty("requestLogger");
            requestLogger = config.getProperty("requestLogger");
            responseLogger = config.getProperty("responseLogger");
            apiDBMaximumPollSize = 20;
            autoCommit = "Y";
            cachePrepStmts = "true";
            prepStmtCacheSize = "250";
            prepStmtCacheSqlLmit = "2048";

        } catch (Exception ex) {

        }
    }


}
