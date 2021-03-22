package com.poxiao.system;

/**
 * @author qq
 * @date 2020/9/15
 * 测试转义字符/
 */
public class TestZy {
    public static void main(String[] args){
        //八进制
        String str1 = "\u0024你好\n好的";
        String str2 = "\\\\{}";
        //System.out.println("u:"+str1);
        System.out.println("str2:"+str2);
    }
}
