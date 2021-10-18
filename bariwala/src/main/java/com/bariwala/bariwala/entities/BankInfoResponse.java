package com.bariwala.bariwala.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BankInfoResponse {
    private String bankCode;
    private String bankName;


    public BankInfoResponse(String bankCode, String bankName) {
        this.bankCode = bankCode;
        this.bankName = bankName;
    }
}
