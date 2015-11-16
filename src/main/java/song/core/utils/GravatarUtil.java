package song.core.utils;


import org.apache.commons.codec.binary.*;

/**
 * 获取Gravatar中的头像
 * Created by Song on 2015/11/15.
 */
public class GravatarUtil {

    private static final  String GRAVATAR_PREFIX="http://www.gravatar.com/avatar/";

    /**
     * 默认头像
     */
    private static final  String DEFAULT_HEADPORT="http://ww1.sinaimg.cn/mw1024/71091f23gw1ey1ziv0odxj213d13dta0.jpg";

    private  GravatarUtil() {
    }

    /**
     * 根据邮箱的MD5值构造URL
     * 获取指定的头像
     * @param email
     * @return
     */
    public static String getHeadPortrait(String email) {
        String hash = MD5Util.md5Hex(email.toLowerCase());
        //得到头像URL
        String imgUrl = GRAVATAR_PREFIX + hash +"?d="+DEFAULT_HEADPORT;
        return imgUrl!=null?imgUrl:null;
    }
}
