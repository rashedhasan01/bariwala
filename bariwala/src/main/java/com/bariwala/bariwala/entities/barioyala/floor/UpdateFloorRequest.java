package com.bariwala.bariwala.entities.barioyala.floor;

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
public class UpdateFloorRequest extends CommonRequest {
    @NotNull(message = "Floor ID can't be empty")
    public int floorID;
    @NotNull(message = "Number of flat can't be empty")
    public int numberOfFlat;
    @NotEmpty(message = "floor type can't be empty")
    public String floorType;
    @NotEmpty(message = "created by can't be empty")
    public String updateBy;
    @NotEmpty(message = "status can't be empty")
    public String status;


}
