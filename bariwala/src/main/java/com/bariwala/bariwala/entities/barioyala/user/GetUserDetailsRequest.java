package com.bariwala.bariwala.entities.barioyala.user;

import com.bariwala.bariwala.models.CommonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

//Class created for get user Details request
//Created Date : 03-09-2021
@ToString
@Getter
@Setter
@NoArgsConstructor
public class GetUserDetailsRequest extends CommonRequest {
    @NotEmpty(message = "User ID can't be empty")
    public String userID;

}
