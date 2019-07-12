package com.seanzx.util;

/**
 * Created by Lucy on 2018/5/23.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Desc:properties文件获取工具类
 * Created by hafiz.zhang on 2016/9/15.
 */
public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getResourceAsStream("/environment.properties");
            if (in == null) {
                logger.error("找不到文件");
                return;
            }
            props.load(in);
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("文件流关闭出现异常");
            }
        }

    }

    public static String get(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}

