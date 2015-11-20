package song.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import song.rest.form.WrapResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * Created by Song on 2015/10/19.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler  {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotExistException.class)
    public WrapResponse handleUserNotExist(){
        logger.error("handle user not exit exception ");
        return new WrapResponse().failure("用户名不存在");
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNameExistException.class)
    public WrapResponse handleUserNameExist(){
        logger.error("handle user name exist exception");
        return new WrapResponse().failure("用户名已经存在，请重新选择一个！");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserEmailExistException.class)
    public WrapResponse  handleUserEmailExist(){
        logger.error("handle user email exist exception");
        return new WrapResponse().failure("邮箱已经存在，请重新选择一个！");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public WrapResponse handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return new WrapResponse().failure("validation_exception");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NewsNotFoundException.class)
    public WrapResponse handleNewsNotFoundException(){
        logger.debug("handle news not found exception");
        return new WrapResponse().failure("News Not Found ");
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
