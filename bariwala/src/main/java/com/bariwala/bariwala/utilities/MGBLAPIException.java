package com.bariwala.bariwala.utilities;

public class MGBLAPIException extends Exception{
    private CommonEnum.responseCode errorCode;
    private String errorMessage;

    public MGBLAPIException(CommonEnum.responseCode errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString()
    {
        return errorCode.getCode().concat(" >> ").concat(errorCode.getMessage()).concat(" >> ").concat(errorMessage);
    }

    public CommonEnum.responseCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
