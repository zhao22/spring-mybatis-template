package com.seanzx.util;
 
import java.io.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
 
/**
 * Zip压缩/解压缩工具类
 * 实现对目标路径及其子路径下的所有文件及空目录的压缩
 * 参考网上若干种实现，并修改其bug
 *
 * @version        v0.1, 17/09/04
 * @author         Kiwi Liu
 */
public class ZIPUtil {
 
    /** 缓冲器大小 */
    private static final int BUFFER = 512;
 
    /**
     * 取的给定源目录下的所有文件及空的子目录
     * 递归实现
     *
     * @param srcFile
     *
     * @return
     */
    private static List<File> getAllFiles(File srcFile) {
        List<File> fileList = new ArrayList<>();
        File[]     tmp      = srcFile.listFiles();
 
        for (int i = 0; i < tmp.length; i++) {
 
            if (tmp[i].isFile()) {
                fileList.add(tmp[i]);
                System.out.println("add file: "+tmp[i].getName());
            }
 
            if (tmp[i].isDirectory()) {
                //若不是空目录，则递归添加其下的目录和文件
                if (tmp[i].listFiles().length!=0){
                    fileList.addAll(getAllFiles(tmp[i]));
                }
                else{//若是空目录，则添加这个目录到fileList
                    fileList.add(tmp[i]);
                    System.out.println("add empty dir: "+tmp[i].getName());
                }
            }
        }
 
        return fileList;
    }
 
    /**
     * 取相对路径
     * 依据文件名和压缩源路径得到文件在压缩源路径下的相对路径
     *
     * @param dirPath 压缩源路径
     * @param file
     *
     * @return 相对路径
     */
    private static String getRelativePath(String dirPath, File file) {
        File   dir          = new File(dirPath);
        String relativePath = file.getName();
 
        while (true) {
            file = file.getParentFile();
 
            if (file == null) {
                break;
            }
 
            if (file.equals(dir)) {
                break;
            } else {
                relativePath = file.getName() + "/" + relativePath;
            }
        }    // end while
 
        return relativePath;
    }
 
    /**
     * 创建文件
     * 根据压缩包内文件名和解压缩目的路径，创建解压缩目标文件，
     * 生成中间目录
     * @param dstPath 解压缩目的路径
     * @param fileName 压缩包内文件名
     *
     * @return 解压缩目标文件
     *
     * @throws IOException
     */
    private static File createFile(String dstPath, String fileName) throws IOException {
        //将文件名的各级目录分解
        String[] dirs = fileName.split("/");
        File     file = new File(dstPath);
        //文件有上级目录
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                //依次创建文件对象知道文件的上一级目录
                file = new File(file, dirs[i]);
            }
 
            if (!file.exists()) {
                //文件对应目录若不存在，则创建
                file.mkdirs();
                System.out.println("mkdirs: " + file.getCanonicalPath());
            }
            //创建文件
            file = new File(file, dirs[dirs.length - 1]);
 
            return file;
        } else {
            if (!file.exists()) {
                //若目标路径的目录不存在，则创建
                file.mkdirs();
                System.out.println("mkdirs: " + file.getCanonicalPath());
            }
            //创建文件
            file = new File(file, dirs[0]);
 
            return file;
        }
    }
 
    /**
     * 解压缩方法
     *
     *
     * @param zipFileName 压缩文件名
     * @param dstPath 解压目标路径
     *
     * @return
     */
    public static boolean unzip(String zipFileName, String dstPath) {
        System.out.println("zip uncompressing...");
 
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName));
            //缓冲器
            ZipEntry zipEntry = null;
            byte[] buffer = new byte[BUFFER];
            //每次读出来的长度
            int readLength = 0;
 
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                //若是zip条目目录，则需创建这个目录
                if (zipEntry.isDirectory()) {
                    File dir = new File(dstPath + "/" + zipEntry.getName());
 
                    if (!dir.exists()) {
                        dir.mkdirs();
                        System.out.println("mkdirs: " + dir.getCanonicalPath());
 
                        continue;//跳出
                    }
                }
                //若是文件，则需创建该文件
                File file = createFile(dstPath, zipEntry.getName());
 
                System.out.println("file created: " + file.getCanonicalPath());
 
                OutputStream outputStream = new FileOutputStream(file);
 
                while ((readLength = zipInputStream.read(buffer, 0, BUFFER)) != -1) {
                    outputStream.write(buffer, 0, readLength);
                }
 
                outputStream.close();
                System.out.println("file uncompressed: " + file.getCanonicalPath());
            }    // end while
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("unzip fail!");
 
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("unzip fail!");
 
            return false;
        }
 
        System.out.println("unzip success!");
 
        return true;
    }
 
    /**
     * 压缩方法
     * （可以压缩空的子目录）
     * @param srcPath 压缩源路径
     * @param zipFileName 目标压缩文件
     *
     * @return
     */
    public static boolean zip(String srcPath, String zipFileName) {
        System.out.println("zip compressing...");
 
        File       srcFile    = new File(srcPath);
        //所有要压缩的文件
        List<File> fileList   = getAllFiles(srcFile);
        //缓冲器
        byte[]     buffer     = new byte[BUFFER];
        ZipEntry   zipEntry   = null;
        //每次读出来的长度
        int        readLength = 0;
 
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
 
            for (File file : fileList) {
                //若是文件，则压缩这个文件
                if (file.isFile()){
                    zipEntry = new ZipEntry(getRelativePath(srcPath, file));
                    zipEntry.setSize(file.length());
                    zipEntry.setTime(file.lastModified());
                    zipOutputStream.putNextEntry(zipEntry);
 
                    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
                    while ((readLength = inputStream.read(buffer, 0, BUFFER)) != -1) {
                        zipOutputStream.write(buffer, 0, readLength);
                    }
 
                    inputStream.close();
                    System.out.println("file compressed: " + file.getCanonicalPath());
                }
                //若是目录（即空目录）则将这个目录写入zip条目
                else {
                    zipEntry = new ZipEntry(getRelativePath(srcPath, file)+"/");
                    zipOutputStream.putNextEntry(zipEntry);
                    System.out.println("dir compressed: " + file.getCanonicalPath()+"/");
                }
 
            }    // end for
 
            zipOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("zip fail!");
 
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("zip fail!");
 
            return false;
        }
 
        System.out.println("zip success!");
 
        return true;
    }
}