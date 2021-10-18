package com.bariwala.bariwala.entities.barioyala.property;

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
public class DeletePropertyRequest extends CommonRequest {
    @NotNull(message = "propertyID can't be empty")
    public int propertyID;
    @NotEmpty(message = "updateBy  can't be empty")
    public String deleteBy;
    @NotEmpty(message = "status  can't be empty and must be value D for delete")
    public String status;

}
