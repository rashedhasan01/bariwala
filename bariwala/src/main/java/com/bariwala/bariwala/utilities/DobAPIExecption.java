package com.bariwala.bariwala.utilities;

public class DobAPIExecption extends Exception {

    private CommonEnum.responseCode errorCode;
    private String errorMessage;

    public DobAPIExecption(CommonEnum.responseCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getMessage()+ ", "+ errorMessage;
    }

    @Override
    public String toString() {
        return "DobAPIExecption{" +
                "errorCode=" + errorCode.getCode() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public CommonEnum.responseCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
