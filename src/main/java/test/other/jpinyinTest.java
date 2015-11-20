package test.other;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.junit.Test;

/**
 * Created by Song on 2015/11/18.
 */
public class jpinyinTest {



    @Test
    public void  test(){
        String str = "中文";
        print(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK)); // nǐ,hǎo,shì,jiè
        print(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER)); // ni3,hao3,shi4,jie4
        print(PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE)); // ni,hao,shi,jie
        print(PinyinHelper.getShortPinyin(str)); // nhsj
    }

    private static void print(String string){
        System.out.println(string);
    }

}
