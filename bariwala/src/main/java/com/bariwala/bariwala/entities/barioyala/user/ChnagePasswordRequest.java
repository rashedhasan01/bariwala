package com.bariwala.bariwala.entities.barioyala.user;

import com.bariwala.bariwala.models.CommonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ChnagePasswordRequest extends CommonRequest {
    public String loginUserName;
    public String oldPassword;
    public String newPassword;
}