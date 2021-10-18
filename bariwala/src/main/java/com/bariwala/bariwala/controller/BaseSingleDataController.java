package com.bariwala.bariwala.controller;

import com.bariwala.bariwala.models.CommonRequest;
import com.bariwala.bariwala.models.GenericSingleDataResponse;
import com.bariwala.bariwala.models.GenericSingleDataResponse;
import org.springframework.http.ResponseEntity;

public abstract class BaseSingleDataController extends BaseController {


    public ResponseEntity executeGetLogic(GenericSingleDataResponse objResponse, CommonRequest objRequest, String[] param) {
        if (!validateAndAuthnticateRequest(objRequest, objResponse)) {
            return getAPIResponse(objResponse);

        } else {
            try {
                executeGetLogic(objResponse, param);
                return checkResponseSingleData(objResponse);
            } catch (Exception ex) {
                return handleException(objResponse,ex);
            }
        }
    }



    protected abstract void executeGetLogic(GenericSingleDataResponse objResponse, String[] param) throws Exception;


}
