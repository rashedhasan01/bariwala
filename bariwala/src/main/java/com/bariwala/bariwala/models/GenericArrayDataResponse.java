package com.bariwala.bariwala.models;

public class GenericArrayDataResponse<T> extends CommonResponse {
    private T[] responseData;


    public T[] getResponseData() {
        return responseData;
    }

    public void setResponseData(T[] responseData) {
        this.responseData = responseData;
    }
}
