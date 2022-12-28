package com.xs.common.util;

import com.alibaba.fastjson2.JSONObject;
import com.xs.common.constant.model.MinioProp;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Slf4j
@Component
public class MinioUtil implements ObjectStorage{

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProp minioProp;

    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
//        if (!client.bucketExists(bucketName)) {
//            client.makeBucket(bucketName);
//        }
    }

    /**
     * 获取全部bucket
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
//        client.removeBucket(bucketName);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
//        return client.presignedGetObject(bucketName, objectName, expires);
        return null;
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
//        return client.getObject(bucketName, objectName);
        return null;
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
//        client.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
//        client.putObject(bucketName, objectName, stream, size, contextType);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public String getObjectInfo(String bucketName, String objectName) throws Exception {
//        return client.statObject(bucketName, objectName);
        return null;
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
//        client.removeObject(bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储桶
     * @return
     */
    public JSONObject uploadFile(MultipartFile file, String bucketName) throws Exception {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        // 判断上传文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        // 判断存储桶是否存在
        createBucket(bucketName);
        // 文件名
        String originalFilename = file.getOriginalFilename();
        // 新的文件名
//        String fileName = bucketName + "_" + DateUtils.getYyyymmdd() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 开始上传
//        client.putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
        res.put("code", 1);
//        res.put("msg", minioProp.getEndpoint() + "/" + bucketName + "/" + fileName);
        return res;
    }
}
