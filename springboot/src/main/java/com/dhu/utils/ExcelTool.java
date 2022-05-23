package com.dhu.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/22 15:34
 */
public class ExcelTool {
    public static final String EMPTY = "";
    private static final String POINT = ".";

    /**
     * 获得path的后缀名
     *
     * @param path 文件路径
     * @return 路径的后缀名
     */
    public static String getPostfix(String path) {
        if (path == null || EMPTY.equals(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return EMPTY;
    }
}

