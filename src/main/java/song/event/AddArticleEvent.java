package song.event;

/**
 * 增加文章事件
 * Created by Song on 2015/10/9.
 */
public class AddArticleEvent {


    private final String message ;

    public AddArticleEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
