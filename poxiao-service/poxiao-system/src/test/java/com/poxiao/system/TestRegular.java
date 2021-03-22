package com.poxiao.system;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qq
 * @date 2020/12/2
 * 测试正则表达式
 */
public class TestRegular {

    @Test
    public void test01() {
        String str = "您好，.";
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        boolean b = matcher.find();
        System.out.println("result:"+b);
        if (b) {
            String group = matcher.group();
            String s = matcher.toString();
            System.out.println("s:"+s);
            System.out.println("group:"+group);
        }
    }

    @Test
    public void test02() {
        String s = escapeChar("张%三");
        System.out.println("s:"+s);
    }

    @Test
    public void test03() {
        String str = "0.11";
        String regEx = "^(([1-9]\\d{0,13})|0)\\.\\d{2}$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        boolean b = matcher.find();
        System.out.println("result:"+b);
        if (b) {
            String group = matcher.group();
            String s = matcher.toString();
            System.out.println("s:"+s);
            System.out.println("group:"+group);
        }
    }

    //判断|的顺序
    @Test
    public void test04() {
        String str = "0.11";
        String regEx = "^([1-9]\\d{0,9})|(0)\\.\\d{2}$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        boolean b = matcher.find();
        System.out.println("result:"+b);
        if (b) {
            String group = matcher.group();
            String s = matcher.toString();
            System.out.println("s:"+s);
            System.out.println("group:"+group);
        }
    }

    //mysql的模糊查询时特殊字符转义
    // \\\\%在Java中最后转换成了\%，因为要在数据库中显示成\%
    // 而pattern就是在Java中，所以\\%就表示%，只需要\\%就可以了
    public String escapeChar(String before){
        if(StringUtils.isNotBlank(before)){
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before ;
    }
}
