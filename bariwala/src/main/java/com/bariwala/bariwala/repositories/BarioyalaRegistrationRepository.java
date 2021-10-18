package com.bariwala.bariwala.repositories;


import com.bariwala.bariwala.entities.barioyala.user.*;
import com.bariwala.bariwala.utilities.APIConfiguration;
import com.bariwala.bariwala.utilities.CommonEnum;
import com.bariwala.bariwala.utilities.CommonUtilities;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

public class BarioyalaRegistrationRepository {
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger reportsLog = Logger.getLogger("reportsLog");
    @Autowired

    private APIConfiguration configuration;
    private OracleDBConnectionPool oracleDBConnectionPool;


    public BarioyalaRegistrationRepository() {
        configuration = APIConfiguration.getInstance();
        oracleDBConnectionPool = OracleDBConnectionPool.getInstance();

    }

    private Connection getBarioyalaConnection() throws SQLException {
        return oracleDBConnectionPool.getBariwalaDBConnection();
    }


    private final String CREATE_USER = "{call  BARIOYALA.PKG_USER_B.CREATE_USER(?,?,?,?,?,?,?)}";
    private final String VALIDATE_USER = "{call  BARIOYALA.PKG_USER_B.valid_user(?,?,?)}";
    private final String CHANGE_PASSWORD = "{call  BARIOYALA.PKG_USER_B.b_change_password(?,?,?,?)}";
    private final String FORGET_PASSWORD = "{call  BARIOYALA.PKG_USER_B.b_forget_password(?,?,?,?)}";
    private final String GET_USER_DETAILS = "{call  BARIOYALA.PKG_USER_B.findUserDetails(?,?)}";
    private final String DELETE_USER = "{call  BARIOYALA.PKG_USER_B.DELETE_USER(?,?,?,?)}";

    public CreateUserResponse createUser(CreateUserRequest request) {
        CreateUserResponse myResponse = new CreateUserResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";
        int tpRowID = 0;
        try {
            connection = getBarioyalaConnection();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }

        try {
            myStatement = connection.prepareCall(CREATE_USER);
            myStatement.setString("p_NAME", request.getUserFullName());
            myStatement.setString("p_USERNAME", request.getLoginUserName());
            myStatement.setString("p_PASSWORD", request.getLoginPassword());
            myStatement.setString("p_EMAIL", request.getEmailAddress());
            myStatement.setString("p_MOBILE", request.getMobileNumber());
            myStatement.setString("p_CREATED_BY", "SYSTEM");
            myStatement.registerOutParameter("result", Types.VARCHAR);
            myStatement.executeQuery();
            rowID = myStatement.getString("result");

            if(rowID.equals("777")) {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }


        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            myResponse.setErrorMessage(ex.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);
            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
            myResponse.setErrorMessage(e.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
        }

        finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }

        return myResponse;

    }

    public CreateUserResponse validateUser(ValidateUserRequest request) {

        CreateUserResponse myResponse = new CreateUserResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";
        int tpRowID = 0;
        try {
            connection = getBarioyalaConnection();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }

        try {
            myStatement = connection.prepareCall(VALIDATE_USER);
            myStatement.setString("p_USERNAME", request.getUserName());
            myStatement.setString("p_PASSWORD", request.getPassword());
            myStatement.registerOutParameter("result", Types.VARCHAR);
            myStatement.executeQuery();
            rowID = myStatement.getString("result");
            if(rowID.equals("777")) {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }

        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            myResponse.setErrorMessage(ex.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);
            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
            myResponse.setErrorMessage(e.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
        }

        finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }

        return myResponse;
    }

    public CreateUserResponse chnagePassword(ChnagePasswordRequest request) {
        CreateUserResponse myResponse = new CreateUserResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";
        int tpRowID = 0;
        try {
            connection = getBarioyalaConnection();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }

        try {
            myStatement = connection.prepareCall(CHANGE_PASSWORD);
            myStatement.setString("p_USERNAME", request.getUserName());
            myStatement.setString("p_OLD_PASSWORD", request.getOldPassword());
            myStatement.setString("p_NEW_PASSWORD", request.getNewPassword());
            myStatement.registerOutParameter("result", Types.VARCHAR);
            myStatement.executeQuery();
            rowID = myStatement.getString("result");
            if(rowID.equals("777")) {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }

        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            myResponse.setErrorMessage(ex.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);
            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
            myResponse.setErrorMessage(e.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
        }

        finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }

        return myResponse;
    }

    public CreateUserResponse forgetPassword(ForgetPasswordRequest request) {


        CreateUserResponse myResponse = new CreateUserResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";
        int tpRowID = 0;
        try {
            connection = getBarioyalaConnection();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }

        try {
            myStatement = connection.prepareCall(FORGET_PASSWORD);
            myStatement.setString("p_USERNAME", request.getUserName());
            myStatement.setString("p_MOBILE", request.getMobileNumber());
            myStatement.setString("p_NEW_PASSWORD", request.getNewPassword());
            myStatement.registerOutParameter("result", Types.VARCHAR);
            myStatement.executeQuery();
            rowID = myStatement.getString("result");
            if(rowID.equals("777")) {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }

        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            myResponse.setErrorMessage(ex.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);
            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
            myResponse.setErrorMessage(e.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
        }

        finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }

        return myResponse;
    }

    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        GetUserDetailsResponse myResponse  = new GetUserDetailsResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";
        int tpRowID = 0;
        try {
            connection = getBarioyalaConnection();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }

        try {
            myStatement = connection.prepareCall(GET_USER_DETAILS);
            myStatement.setString("p_USERNAME", request.getUserID());
            myStatement.registerOutParameter(2, OracleTypes.CURSOR);
            myStatement.executeQuery();
            ResultSet rs = (ResultSet) myStatement.getObject("presult_cur");
            if (rs.next()) {
                do {
                    myResponse.userName = rs.getString("NAME");
                    myResponse.loginId = rs.getString("LOGIN_ID");
                    myResponse.emailAddress = rs.getString("EMAIL");
                    myResponse.mobileNumber = rs.getString("MOBILE");
                    myResponse.createdDate = rs.getString("CREATED_DATE");
                    myResponse.createdBy = rs.getString("CREATED_BY");
                    myResponse.status = rs.getString("STATUS");
                    myResponse.userGroupId = rs.getInt("GROUP_ID");
                    myResponse.userGroupName = rs.getString("GROUP_NAME");
                    myResponse.lastLoginDate = rs.getString("LAST_LOGIN");


                } while (rs.next());

                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.USER_NOT_FOUND);
            }

        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            myResponse.setErrorMessage(ex.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);

            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
            myResponse.setErrorMessage(e.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
        }

        finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }

        return myResponse;
    }

    public CreateUserResponse deleteSingleUser(DeleteUserRequest request) {
        CreateUserResponse myResponse = new CreateUserResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";
        int tpRowID = 0;
        try {
            connection = getBarioyalaConnection();
        } catch (SQLException ex) {
            errorLog.error("Connection Error");
        }

        try {
            myStatement = connection.prepareCall(DELETE_USER);
            myStatement.setInt("p_USER_ID", request.getUserId());
            myStatement.setString("p_STATUS", request.getStatus());
            myStatement.setString("p_DELETED_BY", request.getDeleteBy());;
            myStatement.registerOutParameter("result", Types.VARCHAR);
            myStatement.executeQuery();
            rowID = myStatement.getString("result");

            if(rowID.equals("777")) {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }


        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            myResponse.setErrorMessage(ex.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);
            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
            myResponse.setErrorMessage(e.getMessage());
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
        }

        finally {
            if (connection != null)
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception ex) {
                    errorLog.error("Connection Error");
                }
        }

        return myResponse;
    }
}
