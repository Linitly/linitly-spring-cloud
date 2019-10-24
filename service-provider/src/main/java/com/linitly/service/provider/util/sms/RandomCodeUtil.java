package com.linitly.service.provider.util.sms;

import java.util.Random;

public class RandomCodeUtil {

    /**
     * @author linxiunan
     * @date 2019/8/13 19:58
     * @return java.lang.String
     * @description 生成验证码，传入生成验证码的位数
     */
    public static String randomCode(Integer count) throws RuntimeException {
        if (count == null || count < 0) {
            throw new RuntimeException("random code's count must not null");
        }
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * @author linxiunan
     * @date 2019/8/13 19:58
     * @return java.lang.String
     * @description 生成6位数的验证码
     */
    public static String randomCode() {
        return randomCode(6);
    }
}
