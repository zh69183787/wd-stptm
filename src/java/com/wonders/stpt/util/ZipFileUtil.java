package com.wonders.stpt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * 采用JDK API中自带的类时压缩的文件会出现中文乱码
 */
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;


/**
 *Module:          	ZipFileUtil.java
 *Description:     	用zip对单个和多个文件进行自动压缩
 *Version:          1.0.0
 *Author:           mengjie
 *Date:             2012-11-10
 */
public class ZipFileUtil {

    public static void main(String[] args) throws Exception {
        
        /*
         * 测试单个文件的ZIP压缩 
         * 指定文件路径可以是绝对路径,也可以是相对路径
         */
/*        String file = "src/zip/文件1.txt";
        String zipFile1 = "src/zip/单个文件压缩.zip";
        boolean flag1 = zipSingleFile(file, zipFile1);
        System.out.println("\n**************压缩【单】个文件<author:mengjie>******************************\n");
        if (flag1) {
            System.out.println("单个文件ZIP压缩成功,压缩后文件所在路径为:"+zipFile1);
        } else {
            System.out.println("单个文件ZIP压缩失败");
        }
        System.out.println("\n*******************************************************************************");
        */
        /*
         * 测试多个文件的ZIP压缩
         * 指定文件路径可以是绝对路径,也可以是相对路径
         */
        String files[] = { "C:\\123.txt", "C:\\aaa0.txt" };
        String zipFile2 = "C:\\多个文件压缩.zip";
        boolean flag2 = zipFiles(files, zipFile2);
        System.out.println("\n**************压缩【多】个文件<author:mengjie>******************************\n");
        if (flag2) {
            System.out.println("多个文件ZIP压缩成功,压缩后文件所在路径为:"+zipFile2);
        } else {
            System.out.println("多个文件ZIP压缩失败");
        }
        System.out.println("\n*******************************************************************************");
        
    }

    /**
     *<p>
     *@param  file 待压缩文件的名称 例如,src/zip/文件1.txt
     *@param  zipFile 压缩后文件的名称 例如,src/zip/单个文件压缩.zip
     *@return boolean
     *@throws :IOException
     *@Function: zipSingleFile
     *@Description:单个文件的压缩
     *@version : v1.0.0
     *@author: mengjie
     *@Date:2012-11-11
     *</p>
     *Modification History:
     * Date                     Author          Version         Description
     * ---------------------------------------------------------------------
     * May 24, 2012        mengjie           v1.0.0           Create
     */
    public static boolean zipSingleFile(String file, String zipFile)
            throws IOException {
        boolean bf = true;
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("文件不存在");
            bf = false;
        } else {
            File ff = new File(zipFile);
            if (!f.exists()) {
                ff.createNewFile();
            }
            // 创建文件输入流对象
            FileInputStream in = new FileInputStream(file);
            // 创建文件输出流对象
            FileOutputStream out = new FileOutputStream(zipFile);
            // 创建ZIP数据输出流对象
            ZipOutputStream zipOut = new ZipOutputStream(out);
            // 得到文件名称
            String fileName = file.substring(file.lastIndexOf('/') + 1, file.length());
            // 创建指向压缩原始文件的入口
            ZipEntry entry = new ZipEntry(fileName);
            zipOut.putNextEntry(entry);
            // 向压缩文件中输出数据
            int number = 0;
            byte[] buffer = new byte[512];
            while ((number = in.read(buffer)) != -1) {
                zipOut.write(buffer, 0, number);
            }
            zipOut.close();
            out.close();
            in.close();
        }
        return bf;
    }

    /**
     *<p>
     *@param files    待压缩的文件列表 例如,src/zip/文件1.txt, src/zip/file2.txt
     *@param zipfile 压缩后的文件名称 例如,src/zip/多个文件压缩.zip
     *@return boolean
     *@throws :Exception
     *@Function: zipFiles
     *@Description:多个文件的ZIP压缩
     *@version : v1.0.0
     *@author
     *@Date:
     *</p>
     *Modification History:
     * Date                     Author          Version         Description
     * ---------------------------------------------------------------------
     * May 24, 2012        mengjie           v1.0.0           Create
     */
    public static boolean zipFiles(String[] files, String zipfile)
            throws Exception {
        boolean bf = true;

        // 根据文件路径构造一个文件实例
        File ff = new File(zipfile);
        // 判断目前文件是否存在,如果不存在,则新建一个
        if (!ff.exists()) {
            ff.createNewFile();
        }
        // 根据文件路径构造一个文件输出流
        FileOutputStream out = new FileOutputStream(zipfile);
        // 传入文件输出流对象,创建ZIP数据输出流对象
        ZipOutputStream zipOut = new ZipOutputStream(out);

        // 循环待压缩的文件列表
        for (int i = 0; i < files.length; i++) {
            File f = new File(files[i]);
            if (!f.exists()) {
                bf = false;
            }
            try {
                // 创建文件输入流对象
                FileInputStream in = new FileInputStream(files[i]);
                // 得到当前文件的文件名称
                String fileName = files[i].substring(
                        files[i].lastIndexOf('/') + 1, files[i].length());
                // 创建指向压缩原始文件的入口
                ZipEntry entry = new ZipEntry(fileName);
                zipOut.putNextEntry(entry);
                // 向压缩文件中输出数据
                int nNumber = 0;
                byte[] buffer = new byte[512];
                while ((nNumber = in.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, nNumber);
                }
                // 关闭创建的流对象
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                bf = false;
            }
        }
        zipOut.close();
        out.close();
        return bf;
    }
    
}