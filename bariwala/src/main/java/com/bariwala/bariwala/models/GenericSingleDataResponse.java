package com.bariwala.bariwala.models;

public class GenericSingleDataResponse<T> extends CommonResponse {

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    private T responseData;

}
