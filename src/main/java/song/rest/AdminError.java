package song.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by Song on 2015/11/9.
 */
@JsonAutoDetect
public class AdminError {


    private String code ;

    private String message ;

    public AdminError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
