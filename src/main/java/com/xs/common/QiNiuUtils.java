package com.xs.common;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 七牛云工具类
 */
public class QiNiuUtils {
    public static final String accessKey = "nccHwQX42Io_TrucTSQmksJZhCm2hxtT_X-msF7N";
    public static final String secretKey = "Pw3ziu4SFEoUDr640pl5-XYTj7r9rA6mhzv8coXN";
    public static final String bucket = "myqrcode";

    public static Auth getAuth(){
        return Auth.create(accessKey, secretKey);
    }

    public static String getToken(){
        return getAuth().uploadToken(bucket);
    }

    //上传文件
    public static void upload2QiNiu(String filePath, String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet =
                    new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //上传文件
    public static void upload2QiNiu(byte[] bytes, String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet =
                    new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //删除文件
    public static void deleteFileFromQiNiu(String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    public static void main(String[] args) {
//        String accessKey = "nccHwQX42Io_TrucTSQmksJZhCm2hxtT_X-msF7N";
//        String secretKey = "Pw3ziu4SFEoUDr640pl5-XYTj7r9rA6mhzv8coXN";
//        String bucket = "myqrcode";
//
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//        System.out.println(upToken);
    }

    // 文件上传
    public static DefaultPutRet uploadFile(String path, String filename) {
        // 构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2; // 指定分片上传版本
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "qrcode/static/" + filename;
        System.out.println(path);
        System.out.println(key);
        path += filename;
        String upToken = getToken();
        try {
            Response response = uploadManager.put(path, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return putRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    public static String getVisitAddress(String filename) throws UnsupportedEncodingException {
        filename = "qrcode/static/" + filename;
        String domainOfBucket = "http://rj0g1pmcb.hn-bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(filename, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = getAuth().privateDownloadUrl(publicUrl, expireInSeconds);
        System.out.println(finalUrl);
        return finalUrl;
    }
}


