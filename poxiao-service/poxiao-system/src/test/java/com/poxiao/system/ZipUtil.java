package com.poxiao.system;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author qq
 * @date 2020/11/16
 * zip的压缩和解压
 */
public class ZipUtil {


    /**
     * 提供给外部压缩调用
     * 使用的是org.apache.tools.zip.ZipOutputStream
     */
    public static void zip(String src, String des) throws IOException {
        ZipOutputStream out = null;
        try {
            CheckedOutputStream cos = new CheckedOutputStream(
                    new FileOutputStream(des), new Adler32());
            out = new ZipOutputStream(new BufferedOutputStream(cos));
            //out.setEncoding("GBK");
            zip(new File(src), out, "");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /*使用org.apache.tools.ant.taskdefs.Zip来进行压缩，更方便，同样支持中文无乱码*/
    public static void zip2(String src, String des) throws IOException {
        File srcFile = new File(src);// 源文件
        File desFile = new File(des);// 目标zip文件
        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(desFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        if (srcFile.isFile()) {
            fileSet.setFile(srcFile);
        } else if (srcFile.isDirectory()) {
            fileSet.setDir(srcFile);
        }
        // fileSet.setIncludes("**/*.java"); //包含哪些文件或文件夹
        // eg:zip.setIncludes("*.java")
        // fileSet.setExcludes(...); //排除哪些文件或文件夹
        zip.addFileset(fileSet);
        zip.execute();
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
            out.putNextEntry(new ZipEntry(base + File.separator));
            base = base.length() != 0 ? base + File.separator : "";
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    zip(subFile, out, base + subFile.getName());
                }
            }
        }

    }

    /**
     * 对外多文件压缩接口
     */
    public static void ZipFiles(File zip, File... srcFiles) throws IOException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zip));
            ZipFiles(zos, "", srcFiles);
        } finally {
            if (zos != null) {
                zos.close();
            }
        }
    }

    /**
     * 压缩文件
     */
    private static void ZipFiles(ZipOutputStream out, String path,
                                 File... srcFiles) {
        path = path.replaceAll("\\*", "/");
        if (!path.endsWith("/")) {
            path += "/";
        }
        byte[] buf = new byte[1024];
        try {
            for (int i = 0; i < srcFiles.length; i++) {
                if (srcFiles[i].isDirectory()) {
                    File[] files = srcFiles[i].listFiles();
                    String srcPath = srcFiles[i].getName();
                    srcPath = srcPath.replaceAll("\\*", "/");
                    if (!srcPath.endsWith("/")) {
                        srcPath += "/";
                    }
                    out.putNextEntry(new ZipEntry(path + srcPath));
                    ZipFiles(out, path + srcPath, files);
                } else {
                    FileInputStream in = new FileInputStream(srcFiles[i]);
                    out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*---------------------------解压---------------------------*/


    /**
     * 解压文件到指定目录
     * java自带工具
     * @param zipPath
     *            源zip文件全路径
     * @param descDir
     *            输出目录
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(String zipPath, String descDir)
            throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(new File(zipPath));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
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
}
