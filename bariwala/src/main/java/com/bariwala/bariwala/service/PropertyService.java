package com.bariwala.bariwala.service;

import com.bariwala.bariwala.entities.barioyala.property.*;
import com.bariwala.bariwala.entities.barioyala.user.*;
import com.bariwala.bariwala.models.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface PropertyService {
    CreateUserResponse createProperty(CreatePropertyRequest request);

    CreateUserResponse updateProperty(UpdatePropertyRequest request);

    CreateUserResponse deleteProperty(DeletePropertyRequest request);

    SearchPropertyResponse searchProperty(SearchPropertyRequest request);
}
