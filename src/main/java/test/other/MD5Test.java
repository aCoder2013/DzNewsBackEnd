package test.other;

import org.junit.Test;
import song.core.utils.GravatarUtil;

/**
 * Created by Song on 2015/11/16.
 */
public class MD5Test {


    @Test
    public void test(){
        String email ="asdasd@gmail.com";
        String headPortrait = GravatarUtil.getHeadPortrait(email);
        System.out.println(headPortrait);
    }
}
