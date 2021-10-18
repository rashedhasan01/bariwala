package com.bariwala.bariwala.service.implementation;

import com.bariwala.bariwala.entities.barioyala.floor.CreateFloorRequest;
import com.bariwala.bariwala.entities.barioyala.floor.DeleteFloorRequest;
import com.bariwala.bariwala.entities.barioyala.floor.UpdateFloorRequest;
import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.entities.barioyala.user.CreateUserResponse;
import com.bariwala.bariwala.repositories.FloorRepository;
import com.bariwala.bariwala.repositories.PropertyRepository;
import com.bariwala.bariwala.service.FloorService;
import com.bariwala.bariwala.service.PropertyService;
import org.springframework.stereotype.Service;

@Service
public class FloorSeriveImpl implements FloorService {
    CreateUserResponse createUserResponse;
    SearchPropertyResponse searchPropertyResponse;
    FloorRepository floorRepository =  new FloorRepository();

    @Override
    public CreateUserResponse createProperty(CreateFloorRequest request) {
        return createUserResponse = floorRepository.createFloor(request);
    }

    @Override
    public CreateUserResponse updateProperty(UpdateFloorRequest request) {
        return createUserResponse = floorRepository.updateFloor(request);
    }

    @Override
    public CreateUserResponse deleteProperty(DeleteFloorRequest request) {
        return createUserResponse = floorRepository.deleteFloor(request);
    }

    @Override
    public SearchPropertyResponse searchProperty(SearchPropertyRequest request) {
        return null;
    }
}
