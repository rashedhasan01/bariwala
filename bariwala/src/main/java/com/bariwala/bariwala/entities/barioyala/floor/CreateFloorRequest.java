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
public class CreateFloorRequest extends CommonRequest {
    @NotNull(message = "Property ID can't be empty")
    public int proertyId;
    @NotNull(message = "Number of flat can't be empty")
    public int numberOfFlat;
    @NotEmpty(message = "floor type can't be empty")
    public String floorType;
    @NotEmpty(message = "created by can't be empty")
    public String creayedBy;


}
