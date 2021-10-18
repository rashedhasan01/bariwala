package com.bariwala.bariwala.service;

import com.bariwala.bariwala.entities.barioyala.floor.CreateFloorRequest;
import com.bariwala.bariwala.entities.barioyala.floor.DeleteFloorRequest;
import com.bariwala.bariwala.entities.barioyala.floor.UpdateFloorRequest;
import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.entities.barioyala.user.CreateUserResponse;
import com.bariwala.bariwala.models.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface FloorService {

    CreateUserResponse createProperty(CreateFloorRequest request);

    CreateUserResponse updateProperty(UpdateFloorRequest request);

    CreateUserResponse deleteProperty(DeleteFloorRequest request);

    SearchPropertyResponse searchProperty(SearchPropertyRequest request);
}
