package song.core.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;
import song.rest.AdminError;

import java.util.List;

/**
 * Created by Song on 2015/11/10.
 */
public class PersonalBadRequestException extends RuntimeException{

    private List<ObjectError> errors;



    public PersonalBadRequestException() {
    }

    public PersonalBadRequestException(List<ObjectError> errors){
        this.errors = errors;
    }

    public PersonalBadRequestException(String message) {
        super(message);
    }

    public PersonalBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonalBadRequestException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        List<AdminError> errorList = Lists.newArrayList();
        for(ObjectError error : errors){
            errorList.add(new AdminError(error.getCode(),error.getDefaultMessage()));
        }
        try {
            return new ObjectMapper().writeValueAsString(errorList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return getMessage();
    }
}
