package com.poxiao.system;

import org.springframework.beans.BeanUtils;

/**
 * @author qinqi
 * @date 2020/11/6
 */
public class TestBeanUtil {

    static class Test1{
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test1{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class Test2{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test2{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args){

        Test1 test1 = new Test1();
        test1.setId(1);
        test1.setName("test1");
        Test2 test2 = new Test2();
        BeanUtils.copyProperties(test1,test2);
        System.out.println("test1:"+test1);
        System.out.println(test2);
    }
}
