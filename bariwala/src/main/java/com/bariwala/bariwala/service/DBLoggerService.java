package com.bariwala.bariwala.service;

import com.bariwala.bariwala.entities.BankInfoResponse;
import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.repositories.DBLoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBLoggerService {

@Autowired
DBLoggerRepository repository;

public void requestLogger(String clientIp, String operationName, Object myObject, String serviceId, Boolean isError)
{
    repository.requestLogger(clientIp, operationName, myObject, serviceId, isError);
}



    public void responseLogger(String clientIP, String operationName, CommonResponse response, String serviceID, boolean isError) {
        repository.responseLogger(clientIP, operationName, response, serviceID, isError);
    }
}
