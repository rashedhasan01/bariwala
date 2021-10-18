package com.bariwala.bariwala.entities.barioyala.user;

import com.bariwala.bariwala.models.CommonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DeleteUserRequest extends CommonRequest {

@NotNull(message = "user Id by can't be empty. it's user table auto increment ID")
public int userId;
@NotEmpty(message = "status can't be empty -- example value A for Active, D for Delete")
public String status;
@NotEmpty(message = "delete by can't be empty")
public String deleteBy;
}
