package com.poxiao.system;

import cn.hutool.core.convert.Convert;

import java.math.BigDecimal;

/**
 * @author qq
 * @date 2020/11/9
 * 测试huTool工具类的使用
 */
public class TestHutool {

    public static void main(String[] args){
        test01();
    }

    //金额大小写转换
    static void test01() {
        BigDecimal bigDecimal = new BigDecimal("10.01");
        String toChinese = Convert.digitToChinese(bigDecimal);
        System.out.println("result:"+toChinese);
    }
}
