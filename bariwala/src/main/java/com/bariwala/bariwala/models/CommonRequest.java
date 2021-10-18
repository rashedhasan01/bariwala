package com.bariwala.bariwala.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CommonRequest {
    @NotEmpty(message = "userName can't be empty.")
    public String userName;
    @NotEmpty(message = "password can't be empty.")
    public String password;

}
