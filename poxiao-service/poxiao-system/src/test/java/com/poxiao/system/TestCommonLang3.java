package com.poxiao.system;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author qq
 * @date 2020/9/15
 */
public class TestCommonLang3 {

    private String username;
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
//        return "TestCommonLang3{" +
//                "username='" + username + '\'' +
//                ", age=" + age +
//                '}';
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("username",getUsername())
                .append("age",getAge())
                .toString();
    }

    public static void main(String[] args){
        TestCommonLang3 testCommonLang3 = new TestCommonLang3();
        testCommonLang3.setUsername("zhangsan");
        testCommonLang3.setAge(18);
        System.out.println("result:"+testCommonLang3);
    }
}
