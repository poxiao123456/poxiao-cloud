package com.poxiao.system;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

/**
 * @author qq
 * @date 2020/11/16
 * 测试文件压缩和解压缩
 * 有些类使用的的ant.jar，而没有全部使用jdk原生类，是因为jdk原生类压缩时文件不能时中文
 * 输出流必须写在具体的文件中，而不是目录，否则显示拒绝访问
 */
public class TestZip {

    public static void main(String[] args) throws IOException {
        //zip("C:\\Users\\25311\\Desktop\\测试.docx","C:\\Users\\25311\\Desktop\\测试.zip",null);
        unZip("C:\\Users\\25311\\Desktop\\测试.zip","C:\\Users\\25311\\Desktop\\");
        //testSeparator();
    }

    /**
     * 提供给外部压缩调用
     */
    public static void zip(String src, String des,String baseName) throws IOException {
        ZipOutputStream out = null;
        CheckedOutputStream cos = null;
        try {
            cos = new CheckedOutputStream(
                    new FileOutputStream(des), new Adler32());
            out = new ZipOutputStream(new BufferedOutputStream(cos));
            out.setEncoding("GBK");
            if (baseName == null) {
                baseName = "";
            }
            zip(new File(src), out, baseName);
        } finally {
            if (out != null) {
                out.close();
            }
            if (cos != null) {
                cos.close();
            }
        }
    }

    /**
     * 压缩文件
     */
    private static void zip(File file, ZipOutputStream out, String base)
            throws IOException {
        if (file.isFile()) {
            if (base.length() > 0) {
                out.putNextEntry(new ZipEntry(base));
            } else {
                out.putNextEntry(new ZipEntry(file.getName()));
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "ISO8859_1"));
            int len;
            while ((len = br.read()) != -1) {
                out.write(len);
            }
            br.close();
        } else if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (base.length() > 0) {
                out.putNextEntry(new ZipEntry(base + File.separator));
            } else {
                out.putNextEntry(new ZipEntry(file.getName() + File.separator));
            }
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    zip(subFile, out, base);
                }
            }
        }
    }
    /**
     * 解压文件到指定目录
     *
     * @param zipPath
     *            源zip文件全路径
     * @param descDir
     *            输出目录
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    private static void unZip(String zipPath, String descDir)
            throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(new File(zipPath));
        for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream is = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            if (new File(outPath).isDirectory()) {// 如果是全路径是目录，不需要解压
                continue;
            }
            OutputStream os = new FileOutputStream(outPath);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            is.close();
            os.close();
        }
    }

    /**废除*/
    private static void unZip2(String src, String des) throws IOException {
        ZipFile zipFile = new ZipFile(src,"GBK");//压缩文件的实列,并设置编码
        //获取压缩文件中的所有项
        for(Enumeration<ZipEntry> enumeration = zipFile.getEntries(); enumeration.hasMoreElements();)
        {
            ZipEntry zipEntry = enumeration.nextElement();//获取元素
            String name = zipEntry.getName();
            System.out.println("zipEntry.getName:"+name);
            if(!zipEntry.isDirectory())
            {
                System.out.println("正在解压文件:"+zipEntry.getName());//打印输出信息
                File f;
                int indexOf = zipEntry.getName().lastIndexOf("/");
                if (indexOf == -1) {
                    f = new File(des + zipEntry.getName());
                    if(!f.exists()) {
                        System.out.println("创建解压文件");
                        f.createNewFile();
                    }
                }else {
                    f = new File(des + zipEntry.getName().substring(0,zipEntry.getName().lastIndexOf("/")));
                    //判断是否存在解压目录
                    if(!f.exists()) {
                        System.out.println("创建解压目录");
                        f.mkdirs();
                    }
                }
                InputStream is = zipFile.getInputStream(zipEntry);//读取元素
                BufferedInputStream bis = new BufferedInputStream(is);//读取流的缓存流
                CheckedInputStream cos = new CheckedInputStream(bis, new Adler32());//检查读取流，采用CRC32算法，保证文件的一致性
                //byte [] b = new byte[1024];//字节数组，每次读取1024个字节
                OutputStream os = new FileOutputStream(des+zipEntry.getName());//创建解压后的文件
                BufferedOutputStream bos = new BufferedOutputStream(os);//带缓的写出流
                int len;
                //循环读取压缩文件的值
                while((len = cos.read()) != -1)
                {
                    bos.write(len);//写入到新文件
                }
                cos.close();
                bis.close();
                is.close();
                bos.close();
                os.close();
            }
            else
            {
                //如果是文件夹，则创建该文件夹
                new File(des+zipEntry.getName()).mkdirs();
            }
        }
        System.out.println("解压完成");
        zipFile.close();
    }


    private static void testSeparator() {
        String path = "test/test01.txt";
        String path2 = "C:\\Users\\25311\\Desktop\\测试.zip";
        String path3 = "test/test01.txt/";
        //String substring = path.substring(0, path.lastIndexOf(File.separator));
        String substring3 = path.substring(0, path.lastIndexOf("/"));
        String substring4 = path.substring(0, path.lastIndexOf("."));
        String substring5 = path.substring(0, path.lastIndexOf(""));
        boolean b = path3.endsWith(File.separator);
        if (b) {
            System.out.println("endwith");
        }
        boolean b1 = path3.endsWith("/");
        String substring2 = path2.substring(0, path2.lastIndexOf(File.separator));
        //System.out.println("sub:"+substring);
        System.out.println("sub3:"+substring3);
        System.out.println("sub4:"+substring4);
        System.out.println("sub5:"+substring5);
        System.out.println("sub2:"+substring2);
    }
}
