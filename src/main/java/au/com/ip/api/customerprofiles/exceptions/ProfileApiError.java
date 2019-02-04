package au.com.ip.api.customerprofiles.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
public class ProfileApiError {
    // HTTP Status code
    private HttpStatus status;
    // Error message associated with exception
    private String message;
    // List of constructed error message
    private List<String> errors;

    public ProfileApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ProfileApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }



}
