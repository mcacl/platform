package com.platform.cloud.common.core.utils;

import cn.hutool.core.lang.UUID;
import lombok.Data;
import lombok.var;
import org.jasypt.digest.config.SimpleStringDigesterConfig;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

/**
 * 创建时间:2021/9/24 0024
 * 创建人:pmc
 * 描述:密码工具类
 */
public class UtilPassWord{
    public static PasswordWrapper generatePassword(String password){
        // 生成盐
        String salt = UUID.randomUUID().toString(true);
        PasswordEncryptor encryptor = getEncryptor(salt);
        String encryptPassword = encryptor.encryptPassword(password);
        return new PasswordWrapper(encryptPassword,salt);
    }

    public static Boolean comparePassword(String salt,String encryptPassword,String userPassword){
        PasswordEncryptor encryptor = getEncryptor(salt);
        return encryptor.checkPassword(userPassword,encryptPassword);
    }

    private static PasswordEncryptor getEncryptor(String salt){
        var encryptor = new ConfigurablePasswordEncryptor();
        var config = new SimpleStringDigesterConfig();
        var saltGenerator = new StringFixedSaltGenerator(salt);
        config.setSaltGenerator(saltGenerator);
        encryptor.setConfig(config);
        encryptor.setStringOutputType("base64");
        encryptor.setAlgorithm("MD5");
        return encryptor;
    }

    public static void main(String[] args){
        var wrapper = UtilPassWord.generatePassword("你好abc");
        System.out.println("salt:" + wrapper.getSalt());
        System.out.println("password:" + wrapper.getPassword());
        System.out.println("password-OK:" + UtilPassWord.comparePassword(wrapper.getSalt(),wrapper.getPassword(),"你好abc"));
        System.out.println("password-FAIL:" + UtilPassWord.comparePassword(wrapper.getSalt(),"wrapper.password","admin"));
    }

    @Data
    public static class PasswordWrapper{
        String password;
        String salt;

        public PasswordWrapper(String password,String salt){
            this.password = password;
            this.salt = salt;
        }
    }
}
