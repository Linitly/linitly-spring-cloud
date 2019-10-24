/**
 * @author: linxiunan
 * @date: 2019/8/28 15:20
 * @descrption
 */
package com.linitly.service.provider.util.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author linxiunan
 * @date 2019/8/28 15:25
 * @description 文件工具类
 */
public class FileUtil {

    /**
     * @author linxiunan
     * @date 2019/8/28 16:31
     * @return java.io.File
     * @description multipartFile转为file，通过输入输出流的方式
     */
    public static File multipartFileToFile(MultipartFile mFile) {
        InputStream inputStream = null;
        File file = null;
        try {
            if (mFile != null && mFile.isEmpty()) {
                inputStream = mFile.getInputStream();
                file = new File(mFile.getOriginalFilename());
                inputStreamToFile(inputStream, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * @author linxiunan
     * @date 2019/8/28 16:31
     * @return java.io.File
     * @description 通过inputStream将数据写入到传入的file中
     */
    public static void inputStreamToFile(InputStream inputStream, File file) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
