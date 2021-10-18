package com.bariwala.bariwala.service;

import com.bariwala.bariwala.entities.barioyala.user.*;
import com.bariwala.bariwala.models.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {


    CreateUserResponse createUser(CreateUserRequest request);

    CreateUserResponse validateUser(ValidateUserRequest request);

    CreateUserResponse changePassword(ChnagePasswordRequest request);

    CreateUserResponse forgetPassword(ForgetPasswordRequest request);

    GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request);

    CreateUserResponse deleteUser(DeleteUserRequest request);
}
