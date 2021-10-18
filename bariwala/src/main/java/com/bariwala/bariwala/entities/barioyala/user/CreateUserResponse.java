package com.bariwala.bariwala.entities.barioyala.user;

import com.bariwala.bariwala.models.CommonResponse;
import com.bariwala.bariwala.models.CommonResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CreateUserResponse extends CommonResponse {
    public String errorMessage;
}
