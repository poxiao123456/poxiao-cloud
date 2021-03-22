package com.poxiao.system;
import java.math.BigDecimal;
import	java.sql.Driver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qq
 * @date 2021/1/18
 */
public class TestStream {


    /**
     * 测试forEach对null str的处理
     */
    @Test
    public void test01() {
        List<String> stringList = Arrays.asList("a", "b", null, "d");
        stringList.forEach(str -> {
            System.out.println("str:"+str);
        });
        
        System.out.println("-----------------------------");


        List<Device> devices = new ArrayList<>();
        Device device1 = new Device();
        device1.setDeviceName("a");
        Device device2 = new Device();
        device2.setDeviceName(null);
        Device device3 = new Device();
        device3.setDeviceName("b");
        devices.add(device1);
        devices.add(device2);
        devices.add(device3);

        List<String> deviceNameList = devices.stream().map(Device::getDeviceName).distinct().collect(Collectors.toList());
        deviceNameList.forEach(str -> {
            Device device = new Device();
            device.setDeviceName(str);
            System.out.println("device:"+device);
            System.out.println("str1:"+str);
        });
    }

    /**
     * 测试BigDecimal的count
     */
    @Test
    public void test02() {
        List<BigDecimal> list = new ArrayList<>();
        BigDecimal bigDecimal1 = new BigDecimal(1.01);
        BigDecimal bigDecimal2 = new BigDecimal(2.02);
        list.add(bigDecimal1);
        list.add(bigDecimal2);

        BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        String s = sum.toString();
        System.out.println("s:"+s);
        System.out.println("sum:"+sum);

        sum = BigDecimal.valueOf(0.00);
        System.out.println("sum:"+sum);
    }
}

class Device {
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceName='" + deviceName + '\'' +
                '}';
    }
}
