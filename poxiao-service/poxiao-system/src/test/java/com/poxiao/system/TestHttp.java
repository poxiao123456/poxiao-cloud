package com.poxiao.system;

import cn.hutool.http.HtmlUtil;
import org.junit.Test;

/**
 * @author qq
 * @date 2021/1/14
 */
public class TestHttp {

    /**
     * 清除所有HTML标签，但是保留标签内的内容
     */
    @Test
    public void test01() {
        String str = "<p>哈哈哈哈</p> <p style=\"text-align: center;\">哈哈哈</p> <p style=\"text-align: right;\">哈哈</p>";
        // 结果为：pre\r\n\t\tdfdsfdsfdsf\r\nBBBB
        String result = HtmlUtil.cleanHtmlTag(str);
        String s = result.replaceAll("&nbsp;", " ");
        System.out.println("s:"+s);
    }
}
