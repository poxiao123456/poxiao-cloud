package com.poxiao.system;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import	java.lang.reflect.Field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinqi
 * @date 2020/11/11
 * 测试Class的方法
 */
public class TestClass {

    public static void main(String[] args) throws IllegalAccessException {
        User user1 = new User();
        user1.setName("zhangsan");
        user1.setAge("18");
        User user2 = new User();
        user2.setName("lisi");
        user2.setAge("19");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        for (int i = 0; i < list.size(); i++) {
            Field[] declaredFields = User.class.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String value = field.getDeclaredAnnotation(FieldName.class).value();
                String fieldName = field.getName();
                Class<?> type = field.getType();
                String fieldValue = (String)field.get(list.get(i));
                System.out.println("fieldName1:"+value);
                System.out.println("fieldName2:"+fieldName);
                System.out.println("fieldValue:"+fieldValue);
                System.out.println("fieldType:"+type);
            }
        }
    }

    static class User{

        @FieldName("姓名")
        private String name;
        @FieldName("年龄")
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldName {
        String value();
    }
}
