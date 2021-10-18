package com.bariwala.bariwala.entities.barioyala.property;

import com.bariwala.bariwala.models.CommonResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
//Class created for get user Details response
//Created Date : 03-09-2021
public class SearchPropertyResponse extends CommonResponse {

    public int propertyID;
    public String propertyName;
    public String propertyAddress;
    public String propertyDivision;
    public String propertyDictrict;
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
    public String propertyOwner;
    public String createBy;
    public String createDate;
    public String updateBy;
    public String updateDate;
    public String deleteBy;
    public String deleteDate;
    public String status;


}
