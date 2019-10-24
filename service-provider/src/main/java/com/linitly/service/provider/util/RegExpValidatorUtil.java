package com.linitly.service.provider.util;

import com.linitly.service.provider.helper.constant.GlobalConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linxiunan
 * @date 2019/8/13 20:02
 * @description 正则校验工具类
 **/
public class RegExpValidatorUtil {

    /**
     * @author linxiunan
     * @date 2019/8/13 20:02
     * @return boolean
     * @description 传入正则和字符串进行正则校验
     **/
    public static boolean match(String regex, String str) {
        if (StringUtils.isBlank(regex)) {
            throw new RuntimeException("regex must not null");
        }
        if (StringUtils.isBlank(str)) return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * @author linxiunan
     * @date 2019/8/14 9:32
     * @return boolean
     * @description 验证手机号
     **/
    public static boolean validMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        } else {
            return match(GlobalConstant.MOBILE_NUMBER_REG, mobile);
        }
    }
}
