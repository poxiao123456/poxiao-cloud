package com.poxiao.system;

/**
 * @author qinqi
 * @date 2020/11/2
 */
public class TestClone {
    public static void main(String[] args){

        CloneDemo clone1 = new CloneDemo();
        clone1.setA(1);
        clone1.setName("zhangsan");
    }

    static class CloneDemo implements Cloneable{
        int a;
        String name;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
