package com.bariwala.bariwala.controller;

import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.service.DBLoggerService;
import com.bariwala.bariwala.utilities.APIConfiguration;
import com.bariwala.bariwala.utilities.LoggerUtility;
import com.bariwala.bariwala.utilities.ReflexionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@ControllerAdvice


public class ExceptionController extends ResponseEntityExceptionHandler {
    private static int sequenceNumber;
    protected String timeStamp;
    protected String clientIP;
    protected String operationName;
    protected String apiNAme;
    protected String serviceID;
    private SimpleDateFormat ft3 = new SimpleDateFormat("yyDDDHHmm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    DBLoggerService dbLoggerService;
    @Autowired
    LoggerUtility errorLogger;
    @Autowired
    private ReflexionHelper reflexionHelper;
    private HttpServletRequest httpServletRequest;
    private APIConfiguration configuration = APIConfiguration.getInstance();

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }
@Override

protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    serviceID = this.getServiceID();
    clientIP = this.getClientIP();
    operationName =  getOperationName();
    if (configuration.getRequestLogger()) {
        dbLoggerService.requestLogger(clientIP, operationName, request, serviceID, false);
    }
    CommonResponse myResponse = new CommonResponse();
    List<String> validationMessge = new ArrayList<>();
    myResponse.setResponseCode(Integer.toString(status.value()));
    myResponse.setServiceId(serviceID);
    myResponse.setTimeStamp(this.getTimeStamp());
    List<ObjectError> ObjectError =  ex.getBindingResult().getAllErrors();
    for (ObjectError ObjectErrors:  ObjectError)
    {
        validationMessge.add(ObjectErrors.getDefaultMessage());

    }

    myResponse.setResponseMessage(validationMessge.toString());
    if (configuration.getRequestLogger()) {
        dbLoggerService.responseLogger(clientIP, operationName, myResponse, serviceID, false);
    }
    return new ResponseEntity<>(myResponse,HttpStatus.BAD_REQUEST);
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

    private String getTimeStamp() {
        Date myDate = new Date();
        return dateFormat.format(myDate).toString();
    }

    private String getClientIP() {
        //String remoteAddress = "127.0.0.1";
        String remoteAddress = "";
        try {
            if (this.httpServletRequest != null) {
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


    private String getOperationName() {
        String operationName = "";

        try {
            operationName = httpServletRequest.getRequestURI() + "/" + httpServletRequest.getMethod();
            apiNAme = httpServletRequest.getRequestURI().trim().replace("/","");
            System.out.println("API NAme:"+ apiNAme);
        } catch (Exception ex) {
            operationName = "";
        }
        return operationName;
    }
}
