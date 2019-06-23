package org.uni2biz.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("fields")
    @Expose
    private String fields;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("response")
    @Expose
    private String response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
