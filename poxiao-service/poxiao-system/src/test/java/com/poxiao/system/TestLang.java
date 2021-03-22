package com.poxiao.system;
import	java.util.Random;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author qq
 * @date 2020/11/11
 * 测试lang包下的类
 * 测试常用方法
 */
public class TestLang {

    public static void main(String[] args){
        test04();
    }

    /**测试forEach和for对于list是否是顺序执行，结果是没区别*/
    private static void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        //forEach
        for (Integer i : list) {
            System.out.println("forEach测试");
            System.out.println(i);
        }

        //for测试
        for(int i = 0; i < list.size(); i++) {
            System.out.println("for测试");
            System.out.println(list.get(i));
        }
    }

    /**测试BigDecimal.toString()方法是否会去掉（.00），结果是不会*/
    private static void test02() {
        BigDecimal bigDecimal = new BigDecimal("3.00");
        System.out.println("result:"+bigDecimal.toString());//result:3.00
    }

    /**测试string.tochararray*/
    private static void test03() {
        String str = "11+22";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            System.out.println(c);
        }
    }

    /**测试random.nextint*/
    private static void test04() {
        Random random = new Random();
        int i = random.nextInt(2);
        int i2 = random.nextInt(2);
        System.out.println("i:"+i);//1
        System.out.println("i2:"+i2);//0
    }
}
