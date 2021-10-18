package com.bariwala.bariwala.repositories;

import com.bariwala.bariwala.utilities.APIConfiguration;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bariwala.bariwala.entities.BankInfoResponse;
import com.bariwala.bariwala.utilities.APIConfiguration;
import com.bariwala.bariwala.utilities.CommonUtilities;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class DBLoggerRepository {
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger reportsLog = Logger.getLogger("reportsLog");

    private APIConfiguration configuration;
    private OracleDBConnectionPool oracleDBConnectionPool;

    public DBLoggerRepository() {
        configuration = APIConfiguration.getInstance();
        oracleDBConnectionPool = OracleDBConnectionPool.getInstance();
    }

    private Connection getAPIDBConnection() throws SQLException {
        return oracleDBConnectionPool.getApiDBConnection();
    }




    public void writeLogTest(String logStr, String fromWhere)
    {

        String fileName= null;

        switch ( fromWhere) {

            case "1":  fileName = "ClientRequest";
                break;

            case "2":  fileName = "MGBLEkycservice";
                break;

            case "3":  fileName = "error";
                break;


        }

        Date myDate     = new Date();

        SimpleDateFormat ft4 = new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(configuration.getLogPath()+fileName+"-"+ft4.format(myDate)+".log", true));
            writer.write(logStr);
            writer.newLine();
            writer.close();
        }catch(IOException e){
            errorLog.error("Error writing log: " + e.getMessage());
        }
    }

    public void requestLogger(String clientIp, String operationName, Object myObject, String serviceId, boolean isError)
    {
        String tString = null;
        String cardNo = null;
        String password = "";
        String PIN = "";
        String smsText = "";
        ObjectMapper mapper = new ObjectMapper();

        Object obj = new Object();
        String requestJson = "";
        for (Field f : myObject.getClass().getFields()) {
            f.setAccessible(true);
            try {

                if (f.getName().trim().equals("getCardNoActual()")) {
                    cardNo = f.get(myObject).toString().trim();
                    cardNo = this.maskCCNumber(cardNo);
                    //f.set(myObject, cardNo);
                    obj.getClass().getField("getCardNoActual()").set(obj, cardNo);

                    tString = tString + f.getName() + ":" + cardNo + "  ";
                } else if (f.getName().trim().equals("password")) {
                    password = f.get(myObject).toString().trim();
                    password = this.maskPassword(password);
                    //f.set(myObject, password);
                    obj.getClass().getField("password").set(obj, password);

                    tString = tString + f.getName() + ":" + password + "  ";
                } else if (f.getName().trim().equals("clearPIN")) {
                    PIN = f.get(myObject).toString().trim();
                    PIN = this.maskPassword(PIN);
                    // f.set(myObject, PIN);
                    obj.getClass().getField("clearPIN").set(obj, PIN);
                    tString = tString + "******" + ":" + PIN + "*****  ";
                } else if (f.getName().trim().equals("smsText")) {
                    smsText = f.get(myObject).toString().trim();
                    smsText = smsText.replaceAll("\\d", "");
                    obj.getClass().getField("smsText").set(obj, smsText);
                } else {
                    tString = tString + f.getName() + ":" + f.get(myObject) + "  ";
                }

            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException |NullPointerException e) {
                errorLog.error("Request log db connectivity error: " + e.getMessage());

            }

        }

        try {

            requestJson = mapper.writeValueAsString(myObject);
            obj = mapper.readValue(requestJson, myObject.getClass());
            Connection myConn = null;
            Statement myStmt;
            try {
                myConn = getAPIDBConnection();
                SimpleDateFormat ft4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date myDate = new Date();
                myStmt = myConn.createStatement();
                String myQ = "INSERT INTO MID_REQ_RESP_LOG (SERVICE_ID, REQUEST_SERVICE, REQUEST_FROM_IP, REQUEST_BY, REQUEST_TIME,REQUEST_DETAILS) "
                        + "VALUES ('" + serviceId + "', '" + operationName + "' , '"
                        + clientIp + "','" + serviceId + "'," + "TO_DATE('" + ft4.format(myDate)
                        + "', 'dd-mm-yyyy hh24:mi:ss'), '" + requestJson
                        + "')";

                myStmt.execute(myQ);
                myStmt.execute("COMMIT");
            } catch (SQLException e) {
                errorLog.error("Service ID = " + serviceId + " >> SQL Error :" + e.toString());
            }
            finally {
                if(myConn != null)
                    if(!myConn.isClosed())
                        myConn.close();
            }


        } catch (JsonGenerationException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (JsonMappingException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (IOException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (SQLException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Closing DB Connection: " + e.getMessage());
        }
        try {
            Date myDate = new Date();
            SimpleDateFormat ft3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            int myRandom = (int) (Math.random() * 8847);

            this.writeLogTest(ft3.format(myDate) + "  ClientIp: " + clientIp + " Service ID: "+ serviceId+ " OperationName: " + operationName + " " + tString, "1");

        } catch (SecurityException e) {
            errorLog.error("Request log db connectivity error: " + e.getMessage());
        }


        try {
            requestJson = mapper.writeValueAsString(obj);

        } catch (JsonGenerationException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (JsonMappingException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (IOException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        }

        if (isError) {
            errorLog.error("Service ID -  " + serviceId + " >> Operation Name - " + operationName + " >> Client IP - " + clientIp + " >>Error - " + requestJson);
        } else {
            reportsLog.info("Service ID -  " + serviceId + " >> Operation Name - " + operationName + " >> Client IP - " + clientIp + " >>Request - " + requestJson);
        }
    }


    public String maskCCNumber(String ccnum) {
        return CommonUtilities.maskNumber(ccnum, 6, 4, 'X');
    }


    public String maskCCNumberForSms(String ccnum) {
        return CommonUtilities.maskNumber(ccnum, 3, 3, 'X');
    }


    public String maskPassword(String password) {
        return CommonUtilities.maskNumber(password, 0, password.length(), '*');
    }


    public String maskCCNumberNew(String ccnum) {
        return CommonUtilities.maskNumber(ccnum, 8, 0, 'X');
    }




    public void responseLogger(String clientIp, String operationName, Object myObject, String serviceId, boolean isError) {

        ObjectMapper mapper = new ObjectMapper();

        String resposeJson = "";

        try {
            resposeJson = mapper.writeValueAsString(myObject);
            Connection myConn = null;

            Statement myStmt;
            try {
                myConn = getAPIDBConnection();
                SimpleDateFormat ft4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date myDate = new Date();
                myStmt = myConn.createStatement();
                String myQ = "INSERT INTO MID_REQ_RESP_LOG (SERVICE_ID, REQUEST_SERVICE, REQUEST_FROM_IP, REQUEST_BY,RESPONSE_TIME,RESPONSE_STATUS,RESPONSE_DETAILS) "
                        + "VALUES ('" + serviceId + "', '" + operationName + "' , '"
                        + clientIp + "','" + serviceId + "'," + "TO_DATE('" + ft4.format(myDate)
                        + "', 'dd-mm-yyyy hh24:mi:ss'), '100', '" + resposeJson
                        + "' )";

                myStmt.execute(myQ);
                myStmt.execute("COMMIT");
            } catch (SQLException e) {
                errorLog.error("Service ID = " + serviceId + " >> SQL Error :" + e.toString());
            }
            finally {
                if(myConn != null)
                    if(!myConn.isClosed())
                        myConn.close();
            }

        } catch (JsonGenerationException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (JsonMappingException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (IOException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (SQLException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Closing DB Connection: " + e.getMessage());
        }
        try {
            Date myDate = new Date();
            SimpleDateFormat ft3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            int myRandom = (int) (Math.random() * 8847);

            this.writeLogTest(ft3.format(myDate) + "  ClientIp: " + clientIp + " OperationName: " + operationName + " " + resposeJson, "2");

        } catch (SecurityException e) {
            errorLog.error("Request log db connectivity error: " + e.getMessage());
        }


        try {
            resposeJson = mapper.writeValueAsString(myObject);

        } catch (JsonGenerationException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (JsonMappingException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        } catch (IOException e) {
            errorLog.error("Service ID -  " + serviceId + " >> Error Request writing log: " + e.getMessage());
        }

        if (isError) {
            errorLog.error("Service ID -  " + serviceId + " >> Operation Name - " + operationName + " >> Client IP - " + clientIp + " >>Error - " + resposeJson);
        } else {
            reportsLog.info("Service ID -  " + serviceId + " >> Operation Name - " + operationName + " >> Client IP - " + clientIp + " >>Request - " + resposeJson);
        }
    }


}
