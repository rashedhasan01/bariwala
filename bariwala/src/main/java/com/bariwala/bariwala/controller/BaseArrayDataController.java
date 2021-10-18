package com.bariwala.bariwala.controller;


import com.bariwala.bariwala.models.CommonRequest;
import com.bariwala.bariwala.models.GenericArrayDataResponse;
import org.springframework.http.ResponseEntity;

public abstract class BaseArrayDataController extends BaseController {

        public ResponseEntity executeGetLogic(GenericArrayDataResponse objResponse, CommonRequest objRequest, String[] params) {
            if (!validateAndAuthnticateRequest(objRequest, objResponse)) {
                return getAPIResponse(objResponse);
            } else {
                try {
                    executeGetLogic(objResponse, params);
                    return checkResponseArrayData(objResponse);
                } catch (Exception ex) {
                    return handleException(objResponse, ex);
                }
            }
        }

        public abstract void executeGetLogic(GenericArrayDataResponse objResponse, String[] params) throws Exception;

    }
