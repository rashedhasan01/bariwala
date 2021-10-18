package com.bariwala.bariwala.repositories;


import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.entities.barioyala.user.*;
import com.bariwala.bariwala.utilities.APIConfiguration;
import com.bariwala.bariwala.utilities.CommonEnum;
import com.bariwala.bariwala.utilities.CommonUtilities;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.Locale;

public class PropertyRepository {
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger reportsLog = Logger.getLogger("reportsLog");
    @Autowired

    private APIConfiguration configuration;
    private OracleDBConnectionPool oracleDBConnectionPool;


    public PropertyRepository() {
        configuration = APIConfiguration.getInstance();
        oracleDBConnectionPool = OracleDBConnectionPool.getInstance();

    }

    private Connection getBarioyalaConnection() throws SQLException {
        return oracleDBConnectionPool.getBariwalaDBConnection();
    }


    private final String CREATE_PROPERTY = "{call  BARIOYALA.PKG_GEN_SETUP.CREATE_PROPERTY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    private final String UPDATE_PROPERTY = "{call  BARIOYALA.PKG_GEN_SETUP.UPDATE_PROPERTY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    private final String DELETE_PROPERTY = "{call  BARIOYALA.PKG_GEN_SETUP.DELETE_PROPERTY(?,?,?,?)}";
    private final String SEARCH_PROPERTY = "{call  BARIOYALA.PKG_GEN_SETUP.GET_SINGLE_PROPERTY(?,?,?)}";



    public CreateUserResponse createProperty(CreatePropertyRequest request) {
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
            myStatement = connection.prepareCall(CREATE_PROPERTY);
            myStatement.setString("p_PROPERTY_NAME", request.getPropertyName());
            myStatement.setString("p_PROPERTY_ADDRESS", request.getPropertyAddress());
            myStatement.setString("p_DIVISION", request.getPropertyDivision());
            myStatement.setString("p_DISTRICT", request.getPropertyDictrict());
            myStatement.setString("p_THANA", request.getPropertyThana());
            myStatement.setString("p_CARETAKERS", request.getIsCareTakerExists());
            myStatement.setString("p_CARE_TAKER_NAME", request.getCareTakerName());
            myStatement.setString("p_CARE_TAKER_MOBILE", request.getCareTakerMobileNumber());
            myStatement.setString("p_CARE_TAKER_SAL", request.getCareTakerSalary());
            myStatement.setString("p_SECURITY_CHARGE", request.getIsSecurityChargeExists());
            myStatement.setString("p_SERVICE_CHARGE", request.getIsServiceChargeExists());
            myStatement.setString("p_PARKING", request.getIsParkingChargeExists());
            myStatement.setString("p_WATER_CHARGE", request.getIswaterChargeExists());
            myStatement.setString("p_GAS_CHARGE", request.getIsGasChargeExists());
            myStatement.setString("p_RENTCOLLECTOR", request.getIsRentCollectorExists());
            myStatement.setString("p_RENT_COLLECTOR", request.getRentCollectorName());
            myStatement.setString("p_PROPERTY_OWNER", request.getPropertyOwner());
            myStatement.setString("p_SHOWING_ORDER", "1");
            myStatement.setString("p_CREATED_BY", request.getCreayedBy());
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

    public CreateUserResponse updateProperty(UpdatePropertyRequest request) {

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
            myStatement = connection.prepareCall(UPDATE_PROPERTY);
            myStatement.setInt("p_PROPERTY_ID", request.getPropertyID());
            myStatement.setString("p_PROPERTY_NAME", request.getPropertyName());
            myStatement.setString("p_PROPERTY_ADDRESS", request.getPropertyAddress());
            myStatement.setString("p_DIVISION", request.getPropertyDivision());
            myStatement.setString("p_DISTRICT", request.getPropertyDictrict());
            myStatement.setString("p_THANA", request.getPropertyThana());
            myStatement.setString("p_CARETAKERS", request.getIsCareTakerExists());
            myStatement.setString("p_CARE_TAKER_NAME", request.getCareTakerName());
            myStatement.setString("p_CARE_TAKER_MOBILE", request.getCareTakerMobileNumber());
            myStatement.setString("p_CARE_TAKER_SAL", request.getCareTakerSalary());
            myStatement.setString("p_SECURITY_CHARGE", request.getIsSecurityChargeExists());
            myStatement.setString("p_SERVICE_CHARGE", request.getIsServiceChargeExists());
            myStatement.setString("p_PARKING", request.getIsParkingChargeExists());
            myStatement.setString("p_WATER_CHARGE", request.getIswaterChargeExists());
            myStatement.setString("p_GAS_CHARGE", request.getIsGasChargeExists());
            myStatement.setString("p_RENTCOLLECTOR", request.getIsRentCollectorExists());
            myStatement.setString("p_RENT_COLLECTOR", request.getRentCollectorName());
            myStatement.setString("p_UPDATED_BY", request.getUpdateBy());
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

    public CreateUserResponse deleteProperty(DeletePropertyRequest request) {


        CreateUserResponse myResponse = new CreateUserResponse();
        CommonUtilities commonUtilities = new CommonUtilities();
        Connection connection = null;
        CallableStatement myStatement = null;
        String rowID = "";

        if(request.getStatus().toUpperCase().equals("D")) {

            try {
                connection = getBarioyalaConnection();
            } catch (SQLException ex) {
                errorLog.error("Connection Error");
            }

            try {

                myStatement = connection.prepareCall(DELETE_PROPERTY);
                myStatement.setInt("p_PROPERTY_ID", request.getPropertyID());
                myStatement.setString("p_DELETED_BY", request.getDeleteBy());
                myStatement.setString("p_STATUS", request.getStatus());
                myStatement.registerOutParameter("result", Types.VARCHAR);
                myStatement.executeQuery();
                rowID = myStatement.getString("result");

                if (rowID.equals("777")) {
                    commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
                } else {
                    commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
                }


            } catch (SQLException ex) {
                errorLog.error("SQL Error", ex);
                myResponse.setErrorMessage(ex.getMessage());
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);
                System.out.println(ex.fillInStackTrace());
            } catch (Exception e) {
                myResponse.setErrorMessage(e.getMessage());
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            } finally {
                if (connection != null)
                    try {
                        if (!connection.isClosed())
                            connection.close();
                    } catch (Exception ex) {
                        errorLog.error("Connection Error");
                    }
            }
        }
        else
        {
            myResponse.setErrorMessage("Request value error");
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DELETE_STATUS_VALUE_CHECK);
        }
        return myResponse;
    }

    public SearchPropertyResponse searchProperty(SearchPropertyRequest request) {

        SearchPropertyResponse myResponse  = new SearchPropertyResponse();
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
            myStatement = connection.prepareCall(SEARCH_PROPERTY);
            myStatement.setInt("p_PROPERTY_ID", request.getPropertyID());
            myStatement.registerOutParameter(2, OracleTypes.CURSOR);
            myStatement.registerOutParameter("result", Types.VARCHAR);
            myStatement.executeQuery();
            rowID = myStatement.getString("result");
            if (rowID.equals("777")) {
                ResultSet rs = (ResultSet) myStatement.getObject("presult_cur");
                if (rs.next()) {
                    do {
                        myResponse.setPropertyID(rs.getInt("PROPERTY_ID"));
                        myResponse.setPropertyName(rs.getString("PROPERTY_NAME"));
                        myResponse.setPropertyAddress(rs.getString("PROPERTY_ADDRESS"));
                        myResponse.setPropertyDivision(rs.getString("DIVISION"));
                        myResponse.setPropertyDictrict(rs.getString("DISTRICT"));
                        myResponse.setPropertyThana(rs.getString("THANA"));
                        myResponse.setIsCareTakerExists(rs.getString("CARETAKERS"));
                        myResponse.setCareTakerName(rs.getString("CARE_TAKER_NAME"));
                        myResponse.setCareTakerMobileNumber(rs.getString("CARE_TAKER_MOBILE"));
                        myResponse.setCareTakerSalary(rs.getString("CARE_TAKER_SAL"));
                        myResponse.setIsSecurityChargeExists(rs.getString("SECURITY_CHARGE"));
                        myResponse.setIsServiceChargeExists(rs.getString("SERVICE_CHARGE"));
                        myResponse.setIsParkingChargeExists(rs.getString("PARKING"));
                        myResponse.setIswaterChargeExists(rs.getString("WATER_CHARGE"));
                        myResponse.setIsGasChargeExists(rs.getString("GAS_CHARGE"));
                        myResponse.setIsRentCollectorExists(rs.getString("RENTCOLLECTOR"));
                        myResponse.setRentCollectorName(rs.getString("RENT_COLLECTOR"));
                        myResponse.setPropertyOwner(rs.getString("PROPERTY_OWNER"));
                        myResponse.setCreateBy(rs.getString("CREATED_BY"));
                        myResponse.setCreateDate(rs.getString("CREATED_DATE"));
                        myResponse.setUpdateBy(rs.getString("UPDATED_BY"));
                        myResponse.setUpdateDate(rs.getString("UPDATED_DATE"));
                        myResponse.setDeleteDate(rs.getString("DELETED_BY"));
                        myResponse.setDeleteDate(rs.getString("DELETED_DATE"));
                        myResponse.setStatus(rs.getString("STATUS"));


                    } while (rs.next());

                    commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
                } else {
                    commonUtilities.setResponse(myResponse, CommonEnum.responseCode.USER_NOT_FOUND);
                }
            }
            else
            {
                commonUtilities.setResponse(myResponse, CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }

        } catch (SQLException ex) {
            errorLog.error("SQL Error", ex);
            commonUtilities.setResponse(myResponse, CommonEnum.responseCode.DB_ERROR);

            System.out.println(ex.fillInStackTrace());
        }

        catch (Exception e)
        {
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
