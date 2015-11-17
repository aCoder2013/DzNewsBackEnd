package song.core.exception;

import com.google.web.bindery.requestfactory.shared.messages.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import song.rest.form.FormError;
import song.rest.util.MediaTypes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Song on 2015/10/19.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNameExistException.class)
    public ResponseEntity<FormError> handleUserNameExist(){
        logger.debug("handle news not found exception");
        return new ResponseEntity<>(new FormError("0","用户名已经存在，请重新选择一个！"),HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserEmailExistException.class)
    public ResponseEntity<FormError>  handleUserEmailExist(){
        logger.debug("handle news not found exception");
        return new ResponseEntity<>(new FormError("0","邮箱已经存在，请重新选择一个！"),HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersonalBadRequestException.class)
    public ResponseEntity<?> handleBadRequest(Exception ex , WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        String body = ex.getLocalizedMessage();
        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NewsNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> handleNewsNotFoundException(){
        logger.debug("handle news not found exception");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the NewsNotFoundException example
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
