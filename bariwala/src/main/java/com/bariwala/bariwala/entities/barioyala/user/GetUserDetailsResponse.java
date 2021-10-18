package com.bariwala.bariwala.entities.barioyala.user;

import com.bariwala.bariwala.models.CommonResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
//Class created for get user Details response
//Created Date : 03-09-2021
public class GetUserDetailsResponse extends CommonResponse {

    public String userName;
    public String loginId;
    public String emailAddress;
    public String mobileNumber;
    public String createdDate;
    public String createdBy;
    public String status;
    public int userGroupId;
    public String userGroupName;
    public String lastLoginDate;
    public String errorMessage;


}
