package com.wizard.web.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public final class WizardUtils {

    public static <E> List<E> toList(E[] array) {
        List<E> list = new ArrayList<E>();
        for (E e : array) {
            list.add(e);
        }
        return list;
    }

    public static String toJson(Object arg) {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = "";
        try {
            str = objectMapper.writeValueAsString(arg);
        } catch (JsonGenerationException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return str;
    }

    public static <E> E toObject(String arg, Class<E> cls) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(arg, cls);
        } catch (IOException e) {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public static Map toMap(String arg) {
        return toObject(arg, Map.class);
    }

    public static boolean isBlank(Object obj) {
        return null == obj || "".equals(obj);
    }

    public static String doDateToStr(Date date, String formatStr) {

        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return str;
    }

    public static Date doStringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String firstUpper(String str) {

        char[] c = str.toCharArray();

        if (c[0] >= 'a' && c[0] <= 'z') {
            c[0] -= 32;
        }

        return new String(c);
    }

}
