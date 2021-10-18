package com.bariwala.bariwala.controller;

import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.models.GenericSingleDataResponse;
import com.bariwala.bariwala.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/barioyala/property")
public class PropertyController extends BaseSingleDataController{
@Autowired
   private PropertyService propertyService;

    @RequestMapping(value = "/createProperty", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> createProperty(@RequestBody @Valid CreatePropertyRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = propertyService.createProperty(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }

    @RequestMapping(value = "/updateProperty", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> updateProperty(@RequestBody @Valid UpdatePropertyRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = propertyService.updateProperty(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }


    @RequestMapping(value = "/deleteProperty", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> deleteProperty(@RequestBody @Valid DeletePropertyRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = propertyService.deleteProperty(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }

    @RequestMapping(value = "/searchSingleProperty", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> searchSingleProperty(@RequestBody @Valid SearchPropertyRequest request) throws MethodArgumentNotValidException {

        SearchPropertyResponse objResponse = new SearchPropertyResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = propertyService.searchProperty(request);
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
