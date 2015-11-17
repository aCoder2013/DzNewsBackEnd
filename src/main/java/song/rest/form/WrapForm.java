package song.rest.form;

/**
 * Created by Song on 2015/11/17.
 */
public class WrapForm {

    private int code ;

    private Object msg ;

    public WrapForm(int code, Object obj) {
        this.code = code;
        this.msg = obj;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
