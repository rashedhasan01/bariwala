package com.bariwala.bariwala.service.implementation;

import com.bariwala.bariwala.entities.barioyala.user.*;
import com.bariwala.bariwala.repositories.BarioyalaRegistrationRepository;
import com.bariwala.bariwala.service.UserRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class BarioyalaRegistrationServiceImp implements UserRegistrationService {
    CreateUserResponse createUserResponse;
    GetUserDetailsResponse getUserDetailsResponse;
    BarioyalaRegistrationRepository barioyalaUserRegiRepository = new BarioyalaRegistrationRepository();
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
       return createUserResponse = barioyalaUserRegiRepository.createUser(request);
    }

    @Override
    public CreateUserResponse validateUser(ValidateUserRequest request) {
        return createUserResponse = barioyalaUserRegiRepository.validateUser(request);
    }

    @Override
    public CreateUserResponse changePassword(ChnagePasswordRequest request) {
        return createUserResponse = barioyalaUserRegiRepository.chnagePassword(request);
    }

    @Override
    public CreateUserResponse forgetPassword(ForgetPasswordRequest request) {
        return createUserResponse = barioyalaUserRegiRepository.forgetPassword(request);
    }

    @Override
    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        return getUserDetailsResponse = barioyalaUserRegiRepository.getUserDetails(request);
    }

    @Override
    public CreateUserResponse deleteUser(DeleteUserRequest request) {
        return createUserResponse = barioyalaUserRegiRepository.deleteSingleUser(request);
    }
}
