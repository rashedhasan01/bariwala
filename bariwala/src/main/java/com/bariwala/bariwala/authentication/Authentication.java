package com.bariwala.bariwala.authentication;

import com.bariwala.bariwala.repositories.OracleDBConnectionPool;
import com.bariwala.bariwala.utilities.APIConfiguration;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentication {

    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger reportsLog = Logger.getLogger("reportsLog");
 ;


    private APIConfiguration configuration;
    private static OracleDBConnectionPool oracleDBConnectionPool;

    public Authentication() {
        configuration = APIConfiguration.getInstance();
        oracleDBConnectionPool = OracleDBConnectionPool.getInstance();
    }

    private Connection getAPIDBConnection() throws SQLException {
        return oracleDBConnectionPool.getApiDBConnection();
    }



    public  boolean passwordValidator(String userName, String password,String function_name,String ip)
    {
        if ((userName == null) && (password == null)) {
            return false;
        }
        if (isAuthorized(userName, getMD5EncryptedValue(password), function_name, ip).equals("000")) {
            return true;
        }
        return false;
    }

    public  String getMD5EncryptedValue(String password) {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();
            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (final NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return password;
    }


    public  String isAuthorized(String username, String password, String function_name,String ip) {
        Connection connection = null;
        Statement myStatement = null;
        try {

            connection = getAPIDBConnection();
            myStatement = connection.createStatement();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }
        String response="";
        try
        {
            CallableStatement stmt = null;
                stmt = connection.prepareCall("call ER_USR.MID_PKG.isAuthorized (?,?,?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, function_name);
            stmt.setString(4, ip);
            stmt.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            stmt.execute();
            response = stmt.getString(5);

        } catch (SQLException e) {
           // logDAO.insertMessageLog(
                  //  new MessageLog("SQLException", "Error", e.getMessage(), "isAuthorized", new Date()));
            //logger.error(e.getMessage(), e);
        }  finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }
        return response;
    }


}
