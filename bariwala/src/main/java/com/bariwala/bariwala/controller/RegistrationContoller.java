package com.bariwala.bariwala.controller;

import com.bariwala.bariwala.entities.barioyala.user.*;
import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.models.GenericSingleDataResponse;
import com.bariwala.bariwala.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//Class created for Bariwala User module
//Created Date : 03-09-2021

@RestController
@RequestMapping("/barioyala")
public class RegistrationContoller extends BaseSingleDataController{
    @Autowired
    private UserRegistrationService userRegistrationService;


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid CreateUserRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = userRegistrationService.createUser(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }



    @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> validateUser(@RequestBody @Valid ValidateUserRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = userRegistrationService.validateUser(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }


    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> chnagePassword(@RequestBody @Valid ChnagePasswordRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = userRegistrationService.changePassword(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }



    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> forgetPassword(@RequestBody @Valid ForgetPasswordRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = userRegistrationService.forgetPassword(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }


    @RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> getUserDetails(@RequestBody @Valid GetUserDetailsRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = userRegistrationService.getUserDetails(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> deleteUser(@RequestBody @Valid DeleteUserRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = userRegistrationService.deleteUser(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }

    @Override
    protected void executeGetLogic(GenericSingleDataResponse objResponse, String[] param) throws Exception {

    }
}
