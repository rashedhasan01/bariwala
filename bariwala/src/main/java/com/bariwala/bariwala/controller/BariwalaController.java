package com.bariwala.bariwala.controller;


import com.bariwala.bariwala.models.GenericSingleDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barioyala")
public class BariwalaController extends BaseSingleDataController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "This is my first ";
    }


    @Override
    protected void executeGetLogic(GenericSingleDataResponse objResponse, String[] param) throws Exception {


    }


}
