package com.poxiao.system;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qq
 * @date 2020/10/27
 */
public class TestLambda {
    public static void main(String[] args){

        test02();
    }

    /** count*/
    public static void test00() {
        List<Integer> list = Arrays.asList(0, 1, 1, 0);

        if (CollectionUtils.isEmpty(list)) {
            System.out.println(0);
        }
        long count = list.stream().filter(num -> num == 1).count();
        System.out.println(count);
    }


    /** 去重求和*/
    public static void test01() {

        Person p1 = new Person("张三", new Long("10"));
        Person p2 = new Person("王五", new Long("10"));
        Person p3 = new Person("张三", new Long("10"));
        Person p4 = new Person("李明", new Long("10"));
        Person p5 = new Person("李明", new Long("10"));
        /**
         * 求薪资总和，名字相同的只加一次
         */
        List<Person> pList = new ArrayList<Person>();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);
        pList.add(p5);

        StringBuilder dealBankNumber = new StringBuilder();
        Long sum = pList.stream().filter(v -> {
            boolean flag = !dealBankNumber.toString().contains(v.getName());
            dealBankNumber.append("_").append(v.getName());
            return flag;
        }).map(Person::getSalary).reduce(0L, (a,b)-> a+b);

        System.out.println(sum);
    }

    /** 分组，统计，求和*/
    public static void test02() {
        Person p1 = new Person(1,"张三", new Long("10"));
        Person p2 = new Person(2,"王五", new Long("10"));
        Person p3 = new Person(1,"张三", new Long("20"));
        Person p4 = new Person(3,"李明", new Long("10"));
        Person p5 = new Person(3,"李明", new Long("20"));

        List<Person> pList = new ArrayList<>();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);
        pList.add(p5);

        //求每个人的总工资
        Map<Integer, List<Person>> collect = pList.stream().collect(Collectors.groupingBy(Person::getId));
        System.out.println("result:"+collect);

        Map<Integer, LongSummaryStatistics> collect1 = pList.stream().collect(Collectors.groupingBy(Person::getId, Collectors.summarizingLong(Person::getSalary)));
        System.out.println("result1:"+collect1);

        //求每个人的工资次数
        pList.stream().collect(Collectors.groupingBy(Person::getId, Collectors.summarizingLong(Person::getSalary)));
    }
}
class Person{
    private Integer id;
    private String name;// 姓名
    private Long salary;// 工资

    public Person(String name, Long salary) {
        this.name = name;
        this.salary = salary;
    }

    public Person(Integer id, String name, Long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
