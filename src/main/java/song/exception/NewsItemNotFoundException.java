package song.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Song on 2015/10/19.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such News ")  // 404
public class NewsItemNotFoundException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(NewsItemNotFoundException.class);
    private String message;
    public NewsItemNotFoundException(String message) {
        super(message);
        this.message = message;
        logger.warn(message,getCause());
    }
}
