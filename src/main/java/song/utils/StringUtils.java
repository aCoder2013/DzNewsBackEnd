package song.utils;

/**
 * 工具类
 * 不可实例化
 * Created by Song on 2015/6/12.
 */
public final class StringUtils {


    /*
        检查给定字符数组是否为空或者长度为0
     */
    public static boolean isEmpty(String ...str){
        for(String s :str){
            if(str.length==0||str==null){
                return true;
            }
        }
        return false;
    }



    private StringUtils(){}
}
