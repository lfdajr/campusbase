package br.com.campusbase.exception;

import java.util.Arrays;
import org.springframework.http.HttpStatus;

public class ApiError {
 
    private HttpStatus status;
    private int code;
 
    public ApiError(HttpStatus status, int code) {
        super();
        this.status = status;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
    public int code() {
        return code;
    }
 
}