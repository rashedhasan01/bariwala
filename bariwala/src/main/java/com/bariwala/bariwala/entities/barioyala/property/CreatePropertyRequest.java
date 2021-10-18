package com.bariwala.bariwala.entities.barioyala.property;

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
public class CreatePropertyRequest extends CommonRequest {
    @NotEmpty(message = "Property name can't be empty")
    public String propertyName;
    @NotEmpty(message = "Property address can't be empty")
    public String propertyAddress;
    @NotEmpty(message = "Property division can't be empty")
    public String propertyDivision;
    @NotEmpty(message = "Property district can't be empty")
    public String propertyDictrict;
    @NotEmpty(message = "Property thana can't be empty")
    public String propertyThana;
    public String isCareTakerExists;
    public String careTakerName;
    public String careTakerMobileNumber;
    public String careTakerSalary;
    public String isSecurityChargeExists;
    public String isServiceChargeExists;
    public String isParkingChargeExists;
    public String iswaterChargeExists;
    public String isGasChargeExists;
    public String isRentCollectorExists;
    public String rentCollectorName;
    @NotEmpty(message = "Property Owner can't be empty")
    public String propertyOwner;
    public String creayedBy;


}
