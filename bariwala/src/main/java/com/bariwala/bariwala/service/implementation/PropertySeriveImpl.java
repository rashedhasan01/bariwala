package com.bariwala.bariwala.service.implementation;

import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.entities.barioyala.user.CreateUserResponse;
import com.bariwala.bariwala.repositories.PropertyRepository;
import com.bariwala.bariwala.service.PropertyService;
import org.springframework.stereotype.Service;

@Service
public class PropertySeriveImpl implements PropertyService {
    CreateUserResponse createUserResponse;
    SearchPropertyResponse searchPropertyResponse;
    PropertyRepository propertyRepository= new PropertyRepository();
    @Override
    public CreateUserResponse createProperty(CreatePropertyRequest request) {
        return createUserResponse = propertyRepository.createProperty(request);
    }

    @Override
    public CreateUserResponse updateProperty(UpdatePropertyRequest request) {
        return createUserResponse = propertyRepository.updateProperty(request);
    }

    @Override
    public CreateUserResponse deleteProperty(DeletePropertyRequest request) {
        return createUserResponse = propertyRepository.deleteProperty(request);
    }

    @Override
    public SearchPropertyResponse searchProperty(SearchPropertyRequest request) {
        return searchPropertyResponse = propertyRepository.searchProperty(request);
    }
}
