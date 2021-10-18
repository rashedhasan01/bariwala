package com.bariwala.bariwala.utilities;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoggerUtility {


    private Logger errorLog;
    private Logger reportsLog;
    private APIConfiguration configuration;


    public LoggerUtility() {
        errorLog = Logger.getLogger("errorLogger");
        reportsLog = Logger.getLogger("reportLogger");
        configuration = APIConfiguration.getInstance();

    }

    public void logError(String errorDetails) {
        errorLog.error(errorDetails);
    }

    public void logError(String serviceId, Exception ex) {
        logError("Service ID = " + serviceId + ">> error : " + ex.toString() + ">> at :" + ex.getStackTrace()[0].toString());

    }


    public void logOtherService(String serviceID, String pulishText, Object myObject, boolean isError) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = "";
        try {
            requestJson = objectMapper.writeValueAsString(myObject);

        } catch (JsonGenerationException e) {
            logError("Service ID = " + serviceID + ">> error request wrting log: " + e.getMessage().substring(200));
        } catch (JsonMappingException e) {
            logError("Service ID = " + serviceID + ">> error request wrting log: " + e.getMessage().substring(200));
        } catch (IOException e) {
            logError("Service ID = " + serviceID + ">> error request wrting log: " + e.getMessage().substring(200));
        }

        writeLog("ServiceID - " +serviceID+ ">> "+pulishText+ " - " +requestJson, isError);

    }


    public void writeLog(String pulishText, boolean isError) {
        if (isError) {
            errorLog.error(pulishText);
        } else {
            reportsLog.info(pulishText);
        }

    }

    public void writeCategoryWiseLog(String serviceId,String logStr, String serviceType) {
        String fileName = null;

        switch (serviceType) {
            case "1":
                fileName = "AccountService";
                break;

        }
        Date myDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(configuration.getLogPath() + fileName + "-" + dateFormat.format(myDate) + ".log", true));
            writer.write(logStr);
            writer.newLine();
            writer.close();

        } catch (IOException ex) {
            errorLog.error("Can't write log" + ex.getMessage());

        }
    }

}
