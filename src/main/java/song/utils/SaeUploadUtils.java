package song.utils;

import com.sina.cloudstorage.auth.AWSCredentials;
import com.sina.cloudstorage.auth.BasicAWSCredentials;
import com.sina.cloudstorage.services.scs.SCS;
import com.sina.cloudstorage.services.scs.SCSClient;
import com.sina.cloudstorage.services.scs.model.AccessControlList;
import com.sina.cloudstorage.services.scs.model.Permission;
import com.sina.cloudstorage.services.scs.model.PutObjectResult;
import com.sina.cloudstorage.services.scs.model.UserIdGrantee;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * 新浪云Storage上传
 * Created by Song on 2015/6/17.
 */
public final class SaeUploadUtils {

    private static String accessKey = "vd2xibMJD7uCvBPd68FQ";
    private static String secretKey = "0f7744850e1e3bee07088e9ae44049b668fed21c";

    private static AWSCredentials credentials = null;
    private static SCS conn = null;
    static {
        credentials = new BasicAWSCredentials(accessKey, secretKey);
        conn = new SCSClient(credentials);
    }

    /*
        上传图片
        @bucket:bucket名称
        @path:上传的路径
        @localFile:要上传的图片
        return :返回访问路径
     */
    public static String upload(String bucket,String path,File localFile) {
        PutObjectResult putObjectRequest =
                conn.putObject(bucket ,path,localFile);
        Date expiration = new Date();
        long expochMills = expiration.getTime();
        expochMills+=60*60*24*365*100*1000;//过期时间
        expiration = new Date(expochMills);
        URL presignedUrl =conn.
                generatePresignedUrl("songnews",path,expiration,false);
        return presignedUrl.toString();
    }
    /**
     * 设置bucket acl
     */
    private  void putBucketAcl(){
        AccessControlList acl = new AccessControlList();
        acl.grantPermissions(UserIdGrantee.CANONICAL, Permission.Read, Permission.ReadAcp);
        acl.grantPermissions(UserIdGrantee.ANONYMOUSE,
                Permission.ReadAcp,
                Permission.Write,
                Permission.WriteAcp);
        acl.grantPermissions(new UserIdGrantee("UserId"),
                Permission.Read,
                Permission.ReadAcp,
                Permission.Write,
                Permission.WriteAcp);

        conn.setBucketAcl("create-a-bucket", acl);
    }

}
