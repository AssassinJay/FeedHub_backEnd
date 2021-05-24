package com.rss.feedhub.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangKaijie
 * @Date: 2019/7/26 13:11
 * @Description:
 */
@Component
public class Md5Util {
    /**
     * 加密方法
     * @param src
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    //固定盐
    private static final String SALT = "panda";

    /**
     * 将用户输入的明文密码与固定盐进行拼装后再进行MD5加密
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = inputPass + SALT;
        return md5(str);
    }

    /**
     * 将form表单中的密码转换成数据库中存储的密码
     * @param formPass
     * @param random 随机盐
     * @return
     */
    public static String formPassToDBPass(String formPass, String random) {
        String str = formPass + random;
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String random) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, random);
        return dbPass;
    }

    public static boolean md5Verifier(String inputpass,String dbpass,String account){
        return dbpass.equals(inputPassToDbPass(inputpass,account));
    }
}
