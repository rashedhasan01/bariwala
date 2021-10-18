package com.bariwala.bariwala.controller;

import com.bariwala.bariwala.authentication.Authentication;
import com.bariwala.bariwala.models.CommonRequest;
import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.models.GenericArrayDataResponse;
import com.bariwala.bariwala.models.GenericSingleDataResponse;
import com.bariwala.bariwala.service.DBLoggerService;
import com.bariwala.bariwala.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseController {

    @Autowired
    DBLoggerService dbLoggerService;
    @Autowired
    LoggerUtility errorLogger;
    @Autowired
    private ReflexionHelper reflexionHelper;
    private HttpServletRequest httpServletRequest;
    private APIConfiguration configuration = APIConfiguration.getInstance();
    protected String clientIP;
    protected String operationName;
    protected String apiNAme;
    protected String serviceID;
    protected String timeStamp;

    private SimpleDateFormat ft3 = new SimpleDateFormat("yyDDDHHmm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static int sequenceNumber;


    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }


    protected Boolean validateAndAuthnticateRequest(CommonRequest objRequest, CommonResponse objResponse) {
        setClientOperationDetails();


        if (!validateRequestFields(objRequest)) {
            prepareResponseObject(objResponse, CommonEnum.responseCode.REQUEST_ERROR);
            return false;
        }

         LogRequest(objRequest);

        if (!authenticateRequest(objRequest)) {
            prepareResponseObject(objResponse, CommonEnum.responseCode.AUTH_ERROR);
            return false;
        }

        return true;
    }

    private boolean authenticateRequest(CommonRequest objRequest) {
        System.out.println("Operaton Name : "  + operationName);
        Authentication authObj = new Authentication();
        if (authObj.passwordValidator(objRequest.getUserName(), objRequest.getPassword(),
                apiNAme, getClientIP())) {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected Boolean validateRequestFields(Object request) {

        if ((httpServletRequest.getMethod().equals("GET")) && (!doVlidationForGet(request)))
            return false;
        else if ((httpServletRequest.getMethod().equals("POST")) && (!doVlidationForPost(request)))
            return false;
        else if ((httpServletRequest.getMethod().equals("PUT")) && (!doVlidationForPut(request)))
            return false;
        else if ((httpServletRequest.getMethod().equals("DELETE")) && (!doVlidationForDelete(request)))
            return false;

        return true;
    }




    protected void prepareResponseObject(CommonResponse objResponse, CommonEnum.responseCode responseCode) {
        objResponse.setResponseCode(responseCode.getCode());
        objResponse.setResponseMessage(responseCode.getMessage());

    }


    protected Boolean doVlidationForGet(Object request) {

       return this.validateAllField(request);

    }


    protected Boolean validateAllField(Object request)
    {
        Boolean istrue = true;
        for (Field f : request.getClass().getFields()) {
            f.setAccessible(true);
            try {
                if (f.get(request) == null) {
                    istrue =   false;
                }

            } catch (IllegalArgumentException | IllegalAccessException e) {
                istrue =  false;
            }
        }
        return istrue;

    }

    private boolean doVlidationForPost(Object request) {

        return true;
    }

    private boolean doVlidationForPut(Object request) {
        return true;
    }

    private boolean doVlidationForDelete(Object request) {
        return true;
    }


    protected void LogRequest(CommonRequest request) {
        if (configuration.getRequestLogger())
            dbLoggerService.requestLogger(clientIP, operationName, request, serviceID, false);
    }

    protected void LogResponse(CommonResponse response) {
        if (configuration.getRequestLogger()) {
            dbLoggerService.responseLogger(clientIP, operationName, response, serviceID, false);
        }
    }

    protected String getServiceID() {
        Date myDate = new Date();
        //int myRandom = (int) (Math.random() * 8847);
        String sequenceString = "0000" + String.valueOf(sequenceNumber);
        if(sequenceNumber < 100)
            sequenceNumber++;
        else
            sequenceNumber = 0;
        String serviceID = ft3.format(myDate).toString() + sequenceString.substring(sequenceString.length() - 3, sequenceString.length()); //+ (new Random().nextInt(10)); //+ myRandom;
        return serviceID;
    }


    protected void setClientOperationDetails() {
        clientIP = getClientIP();
        operationName = getOperationName();
        serviceID = getServiceID();
        timeStamp = getTimeStamp();

    }

    private String getTimeStamp() {
        Date myDate = new Date();
        return dateFormat.format(myDate).toString();
    }

    private String getOperationName() {
        String operationName = "";

        try {
            operationName = httpServletRequest.getRequestURI() + "/" + httpServletRequest.getMethod();
            String[] urlArray = httpServletRequest.getRequestURI().split("\\/");
            apiNAme = urlArray[urlArray.length-1];
            System.out.println("API NAme:"+ apiNAme);
        } catch (Exception ex) {
            operationName = "";
        }
        return operationName;
    }


    private String getClientIP() {
        //String remoteAddress = "127.0.0.1";
        String remoteAddress = "";
        try {
            if (httpServletRequest != null) {
               // remoteAddress = httpServletRequest.getRemoteAddr();
              remoteAddress = "127.0.0.1";
            }
        }
        catch (Exception ex)
        {
            remoteAddress = "localhost";
        }
        return remoteAddress;
    }


    protected ResponseEntity logAndGetAPIResponse(CommonResponse objResponse)
    {

        return getAPIResponse(objResponse);
    }


    protected ResponseEntity logAndGetAPIResponse(CommonResponse objResponse, CommonEnum.responseCode responseCode)
    {
        return getAPIResponse(objResponse, responseCode);
    }


    protected ResponseEntity getAPIResponse(CommonResponse objResponse)
    {
        objResponse.setServiceId(serviceID);
        objResponse.setTimeStamp(timeStamp);
        LogResponse(objResponse);
        return getHttpResponse(objResponse);
    }





    protected ResponseEntity handleException(CommonResponse objResponse, Exception ex)
    {

        if(ex instanceof DobAPIExecption)
        return handleException(objResponse, ex, ((DobAPIExecption) ex).getErrorCode());
        else if (ex instanceof SQLException)
            return handleException(objResponse, ex, CommonEnum.responseCode.DB_ERROR);
        else if (ex instanceof ConstraintViolationException)
            return handleException(objResponse, ex, CommonEnum.responseCode.REQUEST_ERROR);
        else if (ex instanceof MethodArgumentNotValidException)
            return handleException(objResponse, ex, CommonEnum.responseCode.REQUEST_ERROR);

        else if (ex instanceof ParseException)
            return handleException(objResponse, ex, CommonEnum.responseCode.REQUEST_ERROR);
        else if (ex instanceof NullPointerException)
            return handleException(objResponse, ex, CommonEnum.responseCode.NULL_POINTER_EXCEPTION);
        else if (ex instanceof IOException)
            return handleException(objResponse, ex, CommonEnum.responseCode.DECLINED_FAULT);
        else if (ex instanceof RuntimeException)
            return handleException(objResponse, ex, CommonEnum.responseCode.DECLINED_FAULT);
        else if (ex instanceof SQLException)
            return handleException(objResponse, ex, CommonEnum.responseCode.NOT_FOUND);
        else
            return handleException(objResponse, ex, CommonEnum.responseCode.NOT_FOUND);
    }


    public ResponseEntity executePostLogic(CommonResponse objResponse, CommonRequest objRequest, String[] params)
    {
        if(!validateAndAuthnticateRequest(objRequest,objResponse))
        {
            return getAPIResponse(objResponse);
        }
        else
            try {
                int result = executePostLogic(objResponse,params);
                return checkResponseSimpleData(objResponse,result,"POST");
            }
            catch (Exception ex)
            {
                return handleException(objResponse,ex);
            }

    }




    public int executePostLogic(CommonResponse objResponse, String[] params) throws Exception
    {
        throw new NotImplementedException();
    }



    protected ResponseEntity checkResponseSingleData(GenericSingleDataResponse objResponse)
    {
        if (objResponse.getResponseData() != null)
        {
            return logAndGetAPIResponse(objResponse,CommonEnum.responseCode.REQUEST_SUCCESS);
        }
        else
        {
            return logAndGetAPIResponse(objResponse,CommonEnum.responseCode.NOT_FOUND);
        }
    }

    protected ResponseEntity checkResponseSimpleData(CommonResponse objResponse, int result, String method) {

        if (result == -100)
        {
            return logAndGetAPIResponse(objResponse);
        }
        else if(result > 0)
        {
            return logAndGetAPIResponse(objResponse,CommonEnum.responseCode.REQUEST_SUCCESS);
        }
        else
        {
            if(method.equals("GET"))
            {
                return logAndGetAPIResponse(objResponse,CommonEnum.responseCode.NOT_FOUND);
            }
            else
            {
                return logAndGetAPIResponse(objResponse,CommonEnum.responseCode.REQUEST_NOT_SUCCESS);
            }
        }

    }


    protected ResponseEntity handleException(CommonResponse objResponse, Exception ex, CommonEnum.responseCode responseCode)
    {
        prepareResponseObject(objResponse,responseCode);
        if (ex instanceof MethodArgumentNotValidException) {
            objResponse.setResponseMessage(ex.getMessage());
        }
        else {
            objResponse.setResponseMessage(ex.toString());
        }
        errorLogger.logError(serviceID,ex);
        return logAndGetAPIResponse(objResponse);
    }



    protected ResponseEntity getAPIResponse(CommonResponse objResponse, CommonEnum.responseCode responseCode)
    {
        prepareResponseObject(objResponse, responseCode);
        return getAPIResponse(objResponse);
    }

    protected ResponseEntity getHttpResponse(CommonResponse objResponse)
    {


        CommonEnum.responseCode responseCode = null;
        for (CommonEnum.responseCode rsCode : CommonEnum.responseCode.values())
        {
               if (rsCode.getCode().equals(objResponse.getResponseCode()))
               {
                   responseCode = rsCode;
                   break;
               }
        }

        if (responseCode == CommonEnum.responseCode.REQUEST_SUCCESS)
        return ResponseEntity.ok(objResponse);
        else if (responseCode == CommonEnum.responseCode.REQUEST_ERROR)
            return ResponseEntity.status(HttpStatus.OK).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.NULL_POINTER_EXCEPTION)
            return ResponseEntity.status(HttpStatus.OK).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.AUTH_ERROR)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.NOT_FOUND)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.IO_ERROR)
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.DB_ERROR)
            return ResponseEntity.status(HttpStatus.OK).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.USER_NOT_FOUND)
            return ResponseEntity.status(HttpStatus.OK).body(objResponse);
        else if (responseCode == CommonEnum.responseCode.SECURITY_ERROR)
            return ResponseEntity.status(HttpStatus.OK).body(objResponse);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objResponse);
    }


    protected ResponseEntity checkResponseArrayData(GenericArrayDataResponse objResponse)
    {
        if (objResponse.getResponseData().length > 0) {
            return logAndGetAPIResponse(objResponse, CommonEnum.responseCode.REQUEST_SUCCESS);
        } else {
            return logAndGetAPIResponse(objResponse, CommonEnum.responseCode.USER_NOT_FOUND);
        }
    }




}
