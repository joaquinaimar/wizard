package com.wizard.web.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * 类名： MessageUtils
 * 类说明：国际化信息工具类
 * 
 * 注意事项：
 * 
 * 使用示例：MessageUtils.load("zh", "cn");
 * String msg = MessageUtils.get("key", "arg0", "arg1")
 * 
 * @author zhanglizhi042888
 * @version 1.0.0
 */
public final class MessageUtils {

    private static ResourceBundle resb = null;

    private MessageUtils() {}

    /**
     * 根据地区加载资源文件
     * 
     * @param args
     */
    public static void load(String... args) {

        Locale locale = null;

        switch (args.length) {

            case 1:
                locale = new Locale(args[0]);
                break;
            case 2:
                locale = new Locale(args[0], args[1]);
                break;
            case 3:
                locale = new Locale(args[0], args[1], args[2]);
                break;
            default:
                locale = Locale.getDefault();
                break;
        }

        try {
            resb = ResourceBundle.getBundle("message/message", locale);
        } catch (MissingResourceException mre) {
            resb = ResourceBundle.getBundle("message/message", Locale.getDefault());
        }
    }

    /**
     * 根据键获取值，并传入参数
     * 
     * @param key
     * @param args
     * @return
     */
    public static String get(String key, Object... args) {

        String value = "";
        try {
            value = resb.getString(key);
        } catch (MissingResourceException mre) {
            return "";
        }
        if (0 != args.length) {
            value = MessageFormat.format(value, args);
        }
        return value;
    }
}
