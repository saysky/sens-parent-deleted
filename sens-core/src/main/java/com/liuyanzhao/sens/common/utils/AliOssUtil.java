package com.liuyanzhao.sens.common.utils;

import com.liuyanzhao.sens.common.constant.SettingConstant;
import com.liuyanzhao.sens.modules.base.vo.OssSetting;
import com.liuyanzhao.sens.common.exception.SensException;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSSClient;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author 言曌
 */
@Component
@Slf4j
public class AliOssUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public OssSetting getOssSetting(){

        String v = redisTemplate.opsForValue().get(SettingConstant.ALI_OSS);
        if(StrUtil.isBlank(v)){
            throw new SensException("您还未配置阿里云OSS");
        }
        return new Gson().fromJson(v, OssSetting.class);
    }

    /**
     * 文件路径上传
     * @param filePath
     * @param key
     * @return
     */
    public String aliUpload(String filePath, String key) {

        OssSetting os = getOssSetting();
        OSSClient ossClient = new OSSClient(os.getHttp() + os.getEndpoint(), os.getAccessKey(), os.getSecretKey());
        ossClient.putObject(os.getBucket(), key, new File(filePath));
        ossClient.shutdown();
        return os.getHttp() + os.getBucket() + "." + os.getEndpoint() + "/" + key;
    }

    /**
     * 文件流上传
     * @param inputStream
     * @param key
     * @return
     */
    public String aliInputStreamUpload(InputStream inputStream, String key) {

        OssSetting os = getOssSetting();
        OSSClient ossClient = new OSSClient(os.getHttp() + os.getEndpoint(), os.getAccessKey(), os.getSecretKey());
        ossClient.putObject(os.getBucket(), key, inputStream);
        ossClient.shutdown();
        if(!Strings.isNullOrEmpty(os.getCdnDomain())) {
            return os.getCdnHttp() + os.getCdnDomain() + "/" + key;
        }
        return os.getHttp() + os.getBucket() + "." + os.getEndpoint() + "/" + key;
    }

    /**
     * 重命名
     * @param fromKey
     * @param toKey
     */
    public String renameFile(String fromKey, String toKey){

        OssSetting os = getOssSetting();
        copyFile(fromKey, toKey);
        deleteFile(fromKey);
        return os.getHttp() + os.getBucket() + "." + os.getEndpoint() + "/" + toKey;
    }

    /**
     * 复制文件
     * @param fromKey
     */
    public String copyFile(String fromKey, String toKey){

        OssSetting os = getOssSetting();
        OSSClient ossClient = new OSSClient(os.getHttp() + os.getEndpoint(), os.getAccessKey(), os.getSecretKey());
        ossClient.copyObject(os.getBucket(), fromKey, os.getBucket(), toKey);
        ossClient.shutdown();
        return os.getHttp() + os.getBucket() + "." + os.getEndpoint() + "/" + toKey;
    }

    /**
     * 删除文件
     * @param key
     */
    public void deleteFile(String key){

        OssSetting os = getOssSetting();
        OSSClient ossClient = new OSSClient(os.getHttp() + os.getEndpoint(), os.getAccessKey(), os.getSecretKey());
        ossClient.deleteObject(os.getBucket(), key);
        ossClient.shutdown();
    }
}
