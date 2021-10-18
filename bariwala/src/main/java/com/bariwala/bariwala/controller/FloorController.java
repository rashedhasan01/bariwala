package com.bariwala.bariwala.controller;

import com.bariwala.bariwala.entities.barioyala.floor.CreateFloorRequest;
import com.bariwala.bariwala.entities.barioyala.floor.DeleteFloorRequest;
import com.bariwala.bariwala.entities.barioyala.floor.UpdateFloorRequest;
import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.models.GenericSingleDataResponse;
import com.bariwala.bariwala.service.FloorService;
import com.bariwala.bariwala.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/barioyala/floor")
public class FloorController extends BaseSingleDataController{
@Autowired
   private FloorService floorService;

    @RequestMapping(value = "/createFloor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> createFloor(@RequestBody @Valid CreateFloorRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = floorService.createProperty(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }

    @RequestMapping(value = "/updateFloor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> updateFloor(@RequestBody @Valid UpdateFloorRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = floorService.updateProperty(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }


    @RequestMapping(value = "/deleteFloor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> deleteFlor(@RequestBody @Valid DeleteFloorRequest request) throws MethodArgumentNotValidException {

        CommonResponse objResponse = new CommonResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = floorService.deleteProperty(request);
                return checkResponseSimpleData(objResponse, -100, "POST");
            } catch (Exception ex) {
                return handleException(objResponse, ex);
            }
        }
    }

    @RequestMapping(value = "/getFloor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> searchFlor(@RequestBody @Valid SearchPropertyRequest request) throws MethodArgumentNotValidException {

        SearchPropertyResponse objResponse = new SearchPropertyResponse();
        if (!validateAndAuthnticateRequest(request, objResponse)) {
            return getAPIResponse(objResponse);
        } else {
            try {
                objResponse = floorService.searchProperty(request);
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
