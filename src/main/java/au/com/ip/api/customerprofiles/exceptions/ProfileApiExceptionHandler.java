package au.com.ip.api.customerprofiles.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ProfileApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This exception is thrown when argument annotated with @Valid failed validation:
     * @param ex MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status HttpStatus.BAD_REQUEST
     * @param request Current Web Request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ProfileApiError apiError =
                new ProfileApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);

    }

    /**
     * This exception is thrown when method argument is not the expected type
     * @param ex MethodArgumentTypeMismatchException
     * @param request WebRequest
     * @return
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ProfileApiError apiError = new ProfileApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ Exception.class, IOException.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ProfileApiError apiError = new ProfileApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Throwing exception instead of send 404 response
     * @param ex NoHandlerFoundException
     * @param headers HTTP Headers
     * @param status HttpStatus.NOT_FOUND
     * @param request WebRequest
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ProfileApiError apiError = new ProfileApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * HttpRequestMethodNotSupportedException occurs when you send a requested with an unsupported HTTP method; this
     * function translates it to custom api error response
     * @param ex HttpRequestMethodNotSupportedException
     * @param headers HttpHeaders
     * @param status HttpStatus.METHOD_NOT_ALLOWED
     * @param request Current Web Request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String error = ex.getMethod() + " method is not supported for this request.";

        ProfileApiError apiError = new ProfileApiError(HttpStatus.METHOD_NOT_ALLOWED,
                ex.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Method handles HttpMediaTypeNotSupportedException – which occurs when the client send a request with unsupported media type
     * @param ex HttpMediaTypeNotSupported Exception
     * @param headers HttpHeaders
     * @param status Unsupported Media Type
     * @param request Web Request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String error = ex.getContentType() + " media type is not supported.";

        ProfileApiError apiError = new ProfileApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
