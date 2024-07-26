package vn.hoidanit.jobhunter.utils.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.hoidanit.jobhunter.domain.RestResponse;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidExeption.class)
    public ResponseEntity<RestResponse<Object>> handleIdInvalidExeption(IdInvalidExeption idInvalidExeption) {

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("call API fails");
        res.setMessage(idInvalidExeption.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleIdInvalidLoginExeption(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> messages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            messages.add(fieldError.getDefaultMessage());
        }
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("call API fails");
        res.setMessage(messages.size() > 1 ? messages : fieldErrors.get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<RestResponse<Object>> handleIdInvalidLoginNotFoundExeption(AuthenticationException ex) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setError("call API fails");
        if (ex.getMessage().equals("Bad credentials"))
            res.setMessage("Password is incorrect");
        else
            res.setMessage("User Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }


}

