package com.bariwala.bariwala.entities.barioyala.user;

import com.bariwala.bariwala.models.CommonRequest;
import com.bariwala.bariwala.models.CommonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest extends CommonRequest {
    @NotEmpty(message = "User full name can't be empty")
    public String userFullName;
    @NotEmpty(message = "Login User Name can't be empty")
    public String loginUserName;
    @NotEmpty(message = "Login Password can't be empty")
    public String loginPassword;
    @NotEmpty(message = "Mobile Number can't be empty")
    public String mobileNumber;
    public String emailAddress;

}
