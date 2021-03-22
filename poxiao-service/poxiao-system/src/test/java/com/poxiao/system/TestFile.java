package com.poxiao.system;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author qq
 * @date 2020/11/16
 * 测试文件类方法
 */
public class TestFile {

    public static void main(String[] args) throws IOException {
        //testFileLoad();
    }

    /**测试文件属性*/
    private static void testFile() throws IOException {


        /*------------File的常用方法----------------*/


        File file = new File("C:\\Users\\25311\\Desktop\\test.docx");
        String name = file.getName();
        String parent = file.getParent();
        String parentName = file.getParentFile().getName();
        String absolutePath = file.getAbsolutePath();
        String path = file.getPath();
        String substring = file.getPath().substring(file.getPath().indexOf(name));

        System.out.println("fileName:"+name);//fileName:test.docx
        System.out.println("parent:"+parent);//parent:C:\Users\25311\Desktop
        System.out.println("parentName:"+parentName);//parentName:Desktop
        System.out.println("absolutePath:"+absolutePath);//absolutePath:C:\Users\25311\Desktop\test.docx
        System.out.println("path:"+path);//path:C:\Users\25311\Desktop\test.docx
        System.out.println("substring:"+substring);//substring:test.docx


        /**
         * 用new File创建出来的是文件不是目录，如果不做任何操作的话，是不可见的，也是不存在的，但可以使用isDirectory方法判断是文件不是目录
         * 如果使用了mkdir，则创建目录可以成功
         * 如果使用了createNewFile,则创建空文件成功
         */
        File file2 = new File("C:\\Users\\25311\\Desktop\\test1test2");
        boolean exists2 = file2.exists();
        if (exists2) {
            System.out.println("file2存在");
        }
        if (file2.isDirectory()) {
            System.out.println("file2是目录");
        }else {
            System.out.println("file2是文件");
            boolean mkdir = file2.mkdir();
            if (mkdir) {
                System.out.println("file2是文件，mkdir后创建目录成功");
            }
        }

        File file3 = new File("C:\\Users\\25311\\Desktop\\test1test2test3.txt");
        boolean exists3 = file3.exists();
        if (exists3) {
            System.out.println("file3存在");
        }
        if (file3.isDirectory()) {
            System.out.println("file3是目录");
        }else {
            System.out.println("file3是文件");
            boolean newFile = file3.createNewFile();
            if (newFile) {
                System.out.println("file3是文件，通过createNewFile创建空文件成功");
            }
        }
    }


    private static void testFile02() {
        String str = "C:\\Users\\25311\\Desktop\\test1test2";
        String outPath = str.replaceAll("\\*", "/");
        System.out.println("outPath:"+outPath);
    }

    @Test
    public void testFileSystem() {
        //1.File-用户根目录
        String property = System.getProperty("user.dir");
        System.out.println("property:"+property);//property:G:\IdeaProjects\poxiao-cloud\poxiao-service\poxiao-system

        //2.File-绝对路径
        String name = new File("/banner.txt").getAbsolutePath();
        System.out.println("name:"+name);//name:G:\banner.txt
        Assert.assertNotNull(name);

        String name0 = new File("banner.txt").getAbsolutePath();
        System.out.println("name0:"+name0);//name0:G:\IdeaProjects\poxiao-cloud\poxiao-service\poxiao-system\banner.txt
        Assert.assertNotNull(name0);

        //3.File-相对路径
        String name1 = new File("audio/explode.wav").getAbsolutePath();
        System.out.println("name1:"+name1);//name1:G:\IdeaProjects\poxiao-cloud\poxiao-service\poxiao-system\audio\explode.wav
        Assert.assertNotNull(name1);

        //4.File-相对路径-验证绝对路径
        String name2 = new File("/audio/explode.wav").getAbsolutePath();
        System.out.println("name2:"+name2);//name2:G:\audio\explode.wav
        Assert.assertNotNull(name2);

        //5.File-相对路径-验证绝对路径
        String name3 = new File("/audio/explode.wava").getAbsolutePath();
        System.out.println("name3:"+name3);//name3:G:\audio\explode.wava
        Assert.assertNotNull(name3);

        //结论：
        //使用file（文件系统）有相对路径和绝对路径
        //相对路径：指的是System.getProperty("user.dir")（用户当前路径）下的路径，也就是当前项目名之下
        //绝对路径：是指根目录下的路径，而根目录在window系统指c,d,e,f，在linux系统指/
    }

    @Test
    public void testClassFile() throws IOException {
        //1.class-绝对路径
        InputStream inputStream = this.getClass().getResourceAsStream("/banner.txt");
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        System.out.println("bytes[0]:"+bytes[0]);
        Assert.assertNotNull("为空",inputStream);//not null
        //2.class-相对路径
        InputStream inputStream2 = this.getClass().getResourceAsStream("banner.txt");
        Assert.assertNull("不为空",inputStream2);//is null
        //3.class-相对路径-""
        String path = this.getClass().getResource("").getPath();
        System.out.println("path:"+path);//path:/G:/IdeaProjects/poxiao-cloud/poxiao-service/poxiao-system/target/test-classes/com/poxiao/system/
        //4.class-绝对路径-"/"
        String path1 = this.getClass().getResource("/").getPath();
        System.out.println("path1:"+path1);//path1:/G:/IdeaProjects/poxiao-cloud/poxiao-service/poxiao-system/target/test-classes/
        //5.class-相对路径-"banner.txt"
        String path2 = this.getClass().getResource("banner.txt").getPath();
        System.out.println("path2:"+path2);//null point
        //6.class-绝对路径-"/banner.txt"
        String path3 = this.getClass().getResource("/banner.txt").getPath();
        System.out.println("path3:"+path3);//path3:/G:/IdeaProjects/poxiao-cloud/poxiao-service/poxiao-system/target/classes/banner.txt

        //结论：
        //使用class加载有相对路径和绝对路径
        //相对路径：指的是当前类所在的包下
        //绝对路径：指的是当前类所在的根路径下，指的是classes或者test-classes
    }

    @Test
    public void testClassLoader() throws IOException {
        //1.class-绝对路径
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/banner.txt");
//        byte[] bytes = new byte[1024];
//        inputStream.read(bytes);//null point
//        System.out.println("bytes[0]:"+bytes[0]);
//        Assert.assertNotNull("为空",inputStream);
        //2.class-相对路径
        InputStream inputStream2 = this.getClass().getClassLoader().getResourceAsStream("banner.txt");
        Assert.assertNotNull("为空",inputStream2);//not null
        //3.class-相对路径-""
        String path = this.getClass().getClassLoader().getResource("").getPath();
        System.out.println("path:"+path);//path:/G:/IdeaProjects/poxiao-cloud/poxiao-service/poxiao-system/target/test-classes/
        //4.class-绝对路径-"/"
//        String path1 = this.getClass().getClassLoader().getResource("/").getPath();
//        System.out.println("path1:"+path1);//is null
        //5.class-相对路径-"banner.txt"
        String path2 = this.getClass().getClassLoader().getResource("banner.txt").getPath();
        System.out.println("path2:"+path2);//path2:/G:/IdeaProjects/poxiao-cloud/poxiao-service/poxiao-system/target/classes/banner.txt
        //6.class-绝对路径-"/banner.txt"
//        String path3 = this.getClass().getClassLoader().getResource("/banner.txt").getPath();
//        System.out.println("path3:"+path3);//null point

        //结论
        //classLoador只有相对路径，这个和类加载器所处的位置有关系
        //相对路径：相对于根路径，这个根路径指classes和test-classes的下面
    }

}
