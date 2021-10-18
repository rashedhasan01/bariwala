package com.bariwala.bariwala.utilities;


import com.bariwala.bariwala.models.CommonResponse;

import javax.xml.soap.SOAPException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CommonUtilities {

    public static String formatPhoneNumberBracketOperatorCode(String phoneNumber)
    {

        StringBuilder sb;

        try {
            sb = new StringBuilder();
            sb.append("(");
            int startLength = phoneNumber.length() - 10;
            sb.append(phoneNumber.substring(startLength, startLength + 4));
            sb.append(")");
            startLength = phoneNumber.length() - 6;
            sb.append(phoneNumber.substring(startLength, startLength + 6));

            return sb.toString();
        }
        catch (Exception ex)
        {
            return "";
        }
        finally {
            sb = null;
        }
    }


    public static String cleanTextContent(String text) {
        // strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", " ");

        // erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", " ");

        // removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", " ");

        return text.trim();
    }


    public static String getLastDigits(String num, int numberofDigits) {
        int startIndex = num.length() - numberofDigits;
        return num.substring(startIndex);
    }

    public static String maskCCNumber(String num){
        return maskNumber(num, 6, 4, 'X');
    }

    public static String maskNumber(String num, int startlen, int endlen, char maskingChar) {

        int total = num.length();
        int masklen = total - (startlen + endlen);

        StringBuffer maskedbuf = new StringBuffer(num.substring(0, startlen));
        for (int i = 0; i < masklen; i++) {
            maskedbuf.append(maskingChar);
        }
        maskedbuf.append(num.substring(startlen + masklen, total));
        String masked = maskedbuf.toString();

        return masked;
    }

    public void setResponse(CommonResponse myResponse, CommonEnum.responseCode responseCode) {
        myResponse.setResponseCode(responseCode.getCode());
        myResponse.setResponseMessage(responseCode.getMessage());
    }
}
