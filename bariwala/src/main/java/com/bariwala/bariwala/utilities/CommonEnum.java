package com.bariwala.bariwala.utilities;

public class CommonEnum {
    public String serviceMessgae;

    public String getMessage(String responseCode)
    {
        switch (responseCode)
        {
            case "100": serviceMessgae = "Operation Successful";break;
            case "101": serviceMessgae = "Record Not Found.";break;
            case "102": serviceMessgae = "Authentication User";break;
            case "103": serviceMessgae = "Request Error";break;
            case "104": serviceMessgae = "Null Pointer Exception";break;
            case "105": serviceMessgae = "Connection Error";break;
            case "106": serviceMessgae = "Security Error";break;
            case "107": serviceMessgae = "Declined Fault Error";break;
            case "108": serviceMessgae = "Log Write Error";break;
            case "109": serviceMessgae = "Database Error";break;
            case "110": serviceMessgae = "I/O Error";break;
            case "000": serviceMessgae = "Operation not success";break;
            case "911": serviceMessgae = "Request Timeout";break;
            case "113": serviceMessgae = "Not Permitted";break;
            case "114": serviceMessgae = "NullPointerException";break;
            default: serviceMessgae = "Unknown Error"; break;
        }
        return  this.serviceMessgae;
    }

    public enum responseCode
    {
        REQUEST_SUCCESS("100"),
        NOT_FOUND("101"),
        AUTH_ERROR("102"),
        REQUEST_ERROR("103"),
        AXIS_FAULT("104"),
        CONn_ERROR("105"),
        SECURITY_ERROR("106"),
        DECLINED_FAULT("107"),
        LOG_ERROR("108"),
        DB_ERROR("109"),
        IO_ERROR("110"),
        REQUEST_NOT_SUCCESS("000"),
        REQUEST_TIME_OUT("111"),
        NOT_PERMITTED("112"),
        NULL_POINTER_EXCEPTION("113"),
        USER_NOT_FOUND("114"),
        DELETE_STATUS_VALUE_CHECK("115");

        private String code;
        responseCode(String code)
        {
            this.code = code;
        }
        public String getCode()
        {
            return String.valueOf(code);
        }
        public String getMessage()
        {
            String serviceMessage ="";
            switch (code)
            {


                case "100": serviceMessage = "Operation Successful";break;
                case "101": serviceMessage = "Record Not Found.";break;
                case "102": serviceMessage = "Authentication error";break;
                case "103": serviceMessage = "Request Error";break;
                case "104": serviceMessage = "Null Pointer Exception";break;
                case "105": serviceMessage = "Connection Error";break;
                case "106": serviceMessage = "Security Error";break;
                case "107": serviceMessage = "Declined Fault Error";break;
                case "108": serviceMessage = "Log Write Error";break;
                case "109": serviceMessage = "Database Error";break;
                case "110": serviceMessage = "I/O Error";break;
                case "000": serviceMessage = "Operation not success";break;
                case "911": serviceMessage = "Not Permitted";break;
                case "113": serviceMessage = "Null Pointer Exception ";break;
                case "114": serviceMessage = "User not found. ";break;
                case "115": serviceMessage = "For delete status value should be D ";break;
            }
            return serviceMessage;

        }

    }
}
