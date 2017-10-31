package com.gugu.android.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FileUtil {

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.isFile() && file.exists() && file.delete();
    }

    /**
     * 删除文件夹以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String filePath) {
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }

        boolean flag = true;
        File[] files = dirFile.listFiles();
        //遍历删除文件夹下的所有文件(包括子目录)
        for (File file : files) {
            if (file.isFile()) {
                //删除子文件
                flag = deleteFile(file.getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(file.getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    public boolean DeleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    public static boolean saveFile(File path, String fileName, byte[] content) {
        File file = new File(path, fileName);
        try(FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean existFile(File path, String filename) {
        File file = new File(path, filename);
        return file.isFile() && file.exists();
    }

    public static InputStream readFile(File path, String fileName) {
        File file = new File(path, fileName);
        byte[] buffer;
        InputStream stream = null;
        try(FileInputStream fis = new FileInputStream(file)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[2048];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
            stream = new ByteArrayInputStream(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

    public static byte[] readFileTobytes(File path, String fileName) {
        File file = new File(path, fileName);
        byte[] buffer = null;
        try(FileInputStream fis = new FileInputStream(file)) {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[2048];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 文件流转成byte数组
     *
     * @return
     */
    public static byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        String hexStr = swapStream.toString();
        return HexUtil.getByteArray(hexStr);
    }

    /**
     * byte数组转成 文件流
     *
     * @return
     */
    public static InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }
}
