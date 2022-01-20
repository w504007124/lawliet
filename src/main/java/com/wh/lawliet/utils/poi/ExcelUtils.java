package com.wh.lawliet.utils.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ExcelUtils {
    private static final DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
    /**
     * 读取excel中的数据 返回一个list
     *
     * @param fileName  文件
     * @param startLine 从第几行开始读取
     * @param sheetNum  从第几列开始读取
     * @return 读取之后List
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel(File fileName, int startLine, int sheetNum) throws Exception {
        try {
            // 如果出现异常， 则用excel2007的方法 读取数据
            return getDataFromExcel07(fileName, startLine, sheetNum);
        } catch (Exception e) {
            log.error("解析Excel文件失败 :{}", e);
            // 先用excel2003的方法 读取数据
            return getDataFromExcel03(fileName, startLine, sheetNum);
        }
    }
    /**
     * 读取excel中的数据 返回一个list
     *
     * @param in  文件
     * @param startLine 从第几行开始读取
     * @param sheetNum  从第几列开始读取
     * @return 读取之后List
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel(InputStream in, int startLine, int sheetNum) throws Exception {
        try {
            // 如果出现异常， 则用excel2007的方法 读取数据
            return getDataFromExcel07(in, startLine, sheetNum);
        } catch (Exception e) {
            log.error("解析Excel文件失败 :{}", e);
            // 先用excel2003的方法 读取数据
            return getDataFromExcel03(in, startLine, sheetNum);
        }
    }
    /**
     * 读取excel中的数据 返回一个list
     *
     * @param fileName  文件
     * @param startLine 从第几行开始读取
     * @param sheetNum  从第几列开始读取
     * @param word0     总列数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel(File fileName, int startLine, int sheetNum, int word0) throws Exception {
        try {

            // 如果出现异常， 则用excel2007的方法 读取数据
            return getDataFromExcel07(fileName, startLine, sheetNum, word0);
        } catch (Exception e) {
            log.error("解析Excel文件失败 :{}", e);
            // 先用excel2003的方法 读取数据
            return getDataFromExcel03(fileName, startLine, sheetNum, word0);
        }
    }

    /**
     * 读取excel数据 适用excel2003
     *
     * @param file      文件信息
     * @param startLine 读取开始行数
     * @param sheetNum  开始读取的列
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel03(File file, int startLine, int sheetNum) throws Exception {
        return getDataFromExcel03(file, startLine, sheetNum, null, null);
    }
    /**
     * 读取excel数据 适用excel2003
     *
     * @param in      文件信息
     * @param startLine 读取开始行数
     * @param sheetNum  开始读取的列
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel03(InputStream in, int startLine, int sheetNum) throws Exception {
        return getDataFromExcel03(in, startLine, sheetNum, null, null);
    }

    /**
     * 读取excel数据 适用excel2003
     *
     * @param file      文件
     * @param startLine 读取开始行数
     * @param sheetNum  开始读取的列
     * @param word0     总列数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel03(File file, int startLine, int sheetNum, int word0) throws Exception {
        return getDataFromExcel03(file, startLine, sheetNum, null, word0);
    }

    /**
     * 读取excel数据 适用excel2007
     *
     * @param file      文件
     * @param startLine 读取开始行数
     * @param sheetNum  读取开始的Sheet
     * @param collCount 总列数
     * @param lineCount 总行数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel03(File file, Integer startLine, Integer sheetNum, Integer collCount,
                                                    Integer lineCount) throws Exception {
        ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
        InputStream inputstream = new FileInputStream(file);
        try {
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
            sheetNum = sheetNum == null ? 0 : sheetNum;
            HSSFSheet hssfsheet = hssfworkbook.getSheetAt(sheetNum);
            //判断传入的行数是否为空，如果为空，系统自动获取Excel中的行数
            lineCount = lineCount == null ? hssfsheet.getPhysicalNumberOfRows() : lineCount;
            for (int j = 0; j < lineCount; j++) {
                if (j < startLine) {
                    continue;
                }
                HSSFRow hssfrow = hssfsheet.getRow(j);
                if (hssfrow == null) {
                    break;
                }
                //传入的列数如果为空，系统自动获取列数
                collCount = collCount == null ? hssfrow.getLastCellNum() : collCount;
                if (collCount < 0) {
                    continue;
                }
                Object[] objs = new Object[collCount];
                for (int k = 0; k < collCount; k++) {
                    HSSFCell hssfcell = hssfrow.getCell(k);
                    if (hssfcell != null) {
                        switch (hssfcell.getCellType()) {
                            case 0:
                                double d = hssfcell.getNumericCellValue();
                                objs[k] =new DecimalFormat("####.##").format(d);
                                break;
                            case 1:
                                objs[k] = hssfcell.getRichStringCellValue().getString().trim();
                                break;
                            case 2:
                                objs[k] = hssfcell.getCellFormula().trim();
                                break;
                            case 3:
                                objs[k] = "";
                                break;
                            case 4:
                                objs[k] = hssfcell.getBooleanCellValue();
                                break;
                            case 5:
                                objs[k] = hssfcell.getErrorCellValue();
                                break;
                            default:
                                objs[k] = "";
                                break;
                        }
                    } else {
                        objs[k] = "";
                    }
                }
                arrayList.add(objs);


            }
        } finally {
            IOUtils.closeQuietly(inputstream);
        }
        return arrayList;
    }
    /**
     * 读取excel数据 适用excel2007
     *
     * @param inputstream      文件
     * @param startLine 读取开始行数
     * @param sheetNum  读取开始的Sheet
     * @param collCount 总列数
     * @param lineCount 总行数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel03(InputStream inputstream , Integer startLine, Integer sheetNum, Integer collCount,
                                                    Integer lineCount) throws Exception {
        ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
        try {
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
            sheetNum = sheetNum == null ? 0 : sheetNum;
            HSSFSheet hssfsheet = hssfworkbook.getSheetAt(sheetNum);
            //判断传入的行数是否为空，如果为空，系统自动获取Excel中的行数
            lineCount = lineCount == null ? hssfsheet.getPhysicalNumberOfRows() : lineCount;
            for (int j = 0; j < lineCount; j++) {
                if (j < startLine) {
                    continue;
                }
                HSSFRow hssfrow = hssfsheet.getRow(j);
                if (hssfrow == null) {
                    break;
                }
                //传入的列数如果为空，系统自动获取列数
                collCount = collCount == null ? hssfrow.getLastCellNum() : collCount;
                if (collCount < 0) {
                    continue;
                }
                Object[] objs = new Object[collCount];
                for (int k = 0; k < collCount; k++) {
                    HSSFCell hssfcell = hssfrow.getCell(k);
                    if (hssfcell != null) {
                        switch (hssfcell.getCellType()) {
                            case 0:
                                double d = hssfcell.getNumericCellValue();
                                objs[k] = new DecimalFormat("####.##").format(d);
                                break;
                            case 1:
                                objs[k] = hssfcell.getRichStringCellValue().getString().trim();
                                break;
                            case 2:
                                objs[k] = hssfcell.getCellFormula().trim();
                                break;
                            case 3:
                                objs[k] = "";
                                break;
                            case 4:
                                objs[k] = hssfcell.getBooleanCellValue();
                                break;
                            case 5:
                                objs[k] = hssfcell.getErrorCellValue();
                                break;
                            default:
                                objs[k] = "";
                                break;
                        }
                    } else {
                        objs[k] = "";
                    }
                }
                arrayList.add(objs);


            }
        } finally {
            IOUtils.closeQuietly(inputstream);
        }
        return arrayList;
    }

    /**
     * 读取excel数据 适用excel2007
     *
     * @param file      文件流
     * @param startLine 读取开始行数
     * @param sheetNum  开始读取的列
     * @param word0     总列数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel07(File file, int startLine, int sheetNum, int word0) throws Exception {
        return getDataFromExcel07(file, startLine, sheetNum, null, word0);
    }


    /**
     * 读取excel数据 适用excel2007
     *
     * @param file      文件
     * @param startLine 读取开始行数
     * @param sheetNum  读取开始的Sheet
     * @param collCount 总列数
     * @param lineCount 总行数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel07(File file, Integer startLine, Integer sheetNum, Integer collCount,
                                                    Integer lineCount) throws Exception {
        ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
        if (file == null) {
            return arrayList;
        }
        InputStream inputstream = new FileInputStream(file);
        try {
            XSSFWorkbook wb = new XSSFWorkbook(inputstream);
            sheetNum = sheetNum == null ? 0 : sheetNum;
            XSSFSheet xssfsheet = wb.getSheetAt(sheetNum);
            //判断传入的行数是否为空，如果为空，系统自动获取Excel中的行数
            lineCount = lineCount == null ? xssfsheet.getPhysicalNumberOfRows() : lineCount;
            for (int j = 0; j < lineCount; j++) {
                //比较和传入的开始读取行数是否符合
                if (j < startLine) {
                    continue;
                }
                //开始循环行数
                XSSFRow xssfrow = xssfsheet.getRow(j);
                if (xssfrow == null) {
                    break;
                }
                //传入的列数如果为空，系统自动获取列数
                collCount = collCount == null ? xssfrow.getLastCellNum() : collCount;
                if (collCount < 0) {
                    continue;
                }
                Object[] objs = new Object[collCount];
                for (int k = 0; k < collCount; k++) {
                    XSSFCell xssfcell = xssfrow.getCell((short) k);
                    if (xssfcell != null) {
                        switch (xssfcell.getCellType()) {
                            case 0:
                                double d = xssfcell.getNumericCellValue();
                                objs[k] =new DecimalFormat("####.##").format(d);
                                break;
                            case 1:
                                objs[k] = xssfcell.getStringCellValue().trim();
                                break;
                            case 2:
                                objs[k] = xssfcell.getCellFormula().trim();
                                break;
                            case 3:
                                objs[k] = "";
                                break;
                            case 4:
                                objs[k] = xssfcell.getBooleanCellValue();
                                break;
                            case 5:
                                objs[k] = xssfcell.getErrorCellValue();
                                break;
                            default:
                                objs[k] = "";
                                break;
                        }
                    } else {
                        objs[k] = "";
                    }
                }
                arrayList.add(objs);


            }
        } finally {
            IOUtils.closeQuietly(inputstream);
        }
        return arrayList;
    }
    /**
     * 读取excel数据 适用excel2007
     *
     * @param inputstream      文件
     * @param startLine 读取开始行数
     * @param sheetNum  读取开始的Sheet
     * @param collCount 总列数
     * @param lineCount 总行数
     * @return List集合
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel07(InputStream inputstream, Integer startLine, Integer sheetNum, Integer collCount,
                                                    Integer lineCount) throws Exception {
        ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
        if (inputstream == null) {
            return arrayList;
        }
        try {
            XSSFWorkbook wb = new XSSFWorkbook(inputstream);
            sheetNum = sheetNum == null ? 0 : sheetNum;
            XSSFSheet xssfsheet = wb.getSheetAt(sheetNum);
            //判断传入的行数是否为空，如果为空，系统自动获取Excel中的行数
            lineCount = lineCount == null ? xssfsheet.getPhysicalNumberOfRows() : lineCount;
            for (int j = 0; j < lineCount; j++) {
                //比较和传入的开始读取行数是否符合
                if (j < startLine) {
                    continue;
                }
                //开始循环行数
                XSSFRow xssfrow = xssfsheet.getRow(j);
                if (xssfrow == null) {
                    break;
                }
                //传入的列数如果为空，系统自动获取列数
                collCount = collCount == null ? xssfrow.getLastCellNum() : collCount;
                if (collCount < 0) {
                    continue;
                }
                Object[] objs = new Object[collCount];
                for (int k = 0; k < collCount; k++) {
                    XSSFCell xssfcell = xssfrow.getCell((short) k);
                    if (xssfcell != null) {
                        switch (xssfcell.getCellType()) {
                            case 0:
                                double d = xssfcell.getNumericCellValue();
                                objs[k] =new DecimalFormat("####.##").format(d);
                                break;
                            case 1:
                                objs[k] = xssfcell.getStringCellValue().trim();
                                break;
                            case 2:
                                objs[k] = xssfcell.getCellFormula().trim();
                                break;
                            case 3:
                                objs[k] = "";
                                break;
                            case 4:
                                objs[k] = xssfcell.getBooleanCellValue();
                                break;
                            case 5:
                                objs[k] = xssfcell.getErrorCellValue();
                                break;
                            default:
                                objs[k] = "";
                                break;
                        }
                    } else {
                        objs[k] = "";
                    }
                }
                arrayList.add(objs);


            }
        } finally {
            IOUtils.closeQuietly(inputstream);
        }
        return arrayList;
    }
    /**
     * 读取excel数据 适用excel2007
     *
     * @param file      文件
     * @param startLine 读取开始行数
     * @param sheetNum  读取开始的Sheet
     * @return List集合L
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel07(File file, Integer startLine, Integer sheetNum) throws Exception {
        return getDataFromExcel07(file, startLine, sheetNum, null, null);
    }
    /**
     * 读取excel数据 适用excel2007
     *
     * @param in      文件
     * @param startLine 读取开始行数
     * @param sheetNum  读取开始的Sheet
     * @return List集合L
     * @throws Exception
     */
    public static List<Object[]> getDataFromExcel07(InputStream in, Integer startLine, Integer sheetNum) throws Exception {
        return getDataFromExcel07(in, startLine, sheetNum, null, null);
    }

    /**
     * @param sheet      sheet
     * @param values     结果集
     * @param copyRow    复制样式的行
     * @param startRow   结果集开始插入行
     * @param margeCells 需要合并的单元格
     * @param totalValue 需要计算总值的列
     */
    public static void insertRows2(HSSFSheet sheet, List<String[]> values,
                                   int copyRow, int startRow, Map<Integer, Integer> margeCells,
                                   Map<Integer, Double> totalValue) {
        HSSFRow targetRow = sheet.getRow(copyRow);
        // 1传入的list大小确定添加的行数
        if (null != values && values.size() > 0) {
            int n = values.size();
            // 插入行
            if (n > 1) {
                sheet.shiftRows(startRow, sheet.getLastRowNum(), n - 1, true, false);
            }
            setValue(values, sheet, startRow, targetRow, margeCells);

            //设置总值
            if (null != totalValue) {
                int totalRowNum = startRow + values.size() - 1;
                HSSFRow totalRow = sheet.getRow(totalRowNum);
                for (int t : totalValue.keySet()) {
                    String totalString = tranferObjectToDoubleString(totalValue.get(t));
                    totalRow.getCell(t).setCellValue(new HSSFRichTextString(totalString));
                }
            }
        }
    }
    public static String tranferObjectToDoubleString(Object o) {
        return o != null ? tranferDoubleNew(Double.parseDouble(o.toString())) : "0.00";
    }
    public static String tranferDoubleNew(Object o) {
        Double d = o != null ? Double.parseDouble(o.toString())/100.0 : 0.00;
        return fmt.format(d);
    }
    private static void setValue(List<String[]> values, HSSFSheet sheet, int startRow, HSSFRow targetRow,
                                 Map<Integer, Integer> margeCells) {
        // 插入值ֵ
        for (int i = 0; i < values.size(); i++) {

            String[] field = values.get(i);

            if (null != field && field.length > 0) {

                HSSFCell sourceCell;
                HSSFCell targetCell;
                // 开始的时候有一处空行
                HSSFRow row = sheet.createRow(startRow + i - 1);
                for (int m = 0; m < targetRow.getPhysicalNumberOfCells(); m++) {
                    sourceCell = row.createCell(m);
                    targetCell = targetRow.getCell(m);
                    if (targetCell == null) {
                        targetCell = targetRow.createCell(m);
                    }
                    sourceCell.setCellStyle(targetCell.getCellStyle());
                    sourceCell.setCellType(targetCell.getCellType());
                }
                for (int j = 0; j < field.length; j++) {
                    if (null != field[j]) {
                        //判断值是否为空
                        String str = field[j];
                        HSSFCell cell = row.getCell(j);
                        if (cell == null) {
                            cell = row.createCell(j);
                        }
                        HSSFRichTextString string = new HSSFRichTextString(str);
                        cell.setCellValue(string);

                    }
                }
                // 合并单元格
                if (null != margeCells) {
                    for (int key : margeCells.keySet()) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(startRow + i - 1, startRow + i - 1,
                                key, margeCells.get(key));
                        sheet.addMergedRegion(cellRangeAddress);
                    }
                }

            }
        }
    }

    /**
     * @param sheet      sheet
     * @param values     结果集
     * @param copyRow    复制样式的行
     * @param startRow   结果集开始插入行
     * @param margeCells 需要合并的单元格
     * @param totalValue 需要计算总值的列
     */
    public static void insertRows3(XSSFSheet sheet, List<String[]> values,
                                   int copyRow, int startRow, Map<Integer, Integer> margeCells,
                                   Map<Integer, Double> totalValue) {
        XSSFRow targetRow = sheet.getRow(copyRow);
        // 1传入的list大小确定添加的行数
        if (values == null || values.size() < 1) {
            return;
        }
        int n = values.size();
        // 插入行
        if (n > 1) {
            sheet.shiftRows(startRow, sheet.getLastRowNum(), n - 1, true, false);
        }
        // 插入值ֵ
        for (int i = 0; i < values.size(); i++) {

            String[] field = values.get(i);
            if (field == null || field.length < 1) {
                continue;
            }

            XSSFCell sourceCell;
            XSSFCell targetCell;
            // 开始的时候有一处空行
            XSSFRow row = sheet.createRow(startRow + i - 1);
            if (targetRow != null) {
                row.setHeight(targetRow.getHeight());
            }
            for (int m = 0; m < targetRow.getPhysicalNumberOfCells(); m++) {
                sourceCell = row.createCell(m);
                targetCell = targetRow.getCell(m);
                if (targetCell == null) {
                    targetCell = targetRow.createCell(m);
                }
                sourceCell.setCellStyle(targetCell.getCellStyle());
                sourceCell.setCellType(targetCell.getCellType());
            }
            for (int j = 0; j < field.length; j++) {
                if (null == field[j]) {
                    continue;
                }
                //判断值是否为空
                String str = field[j];
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                XSSFRichTextString string = new XSSFRichTextString(str);
                cell.setCellValue(string);
            }
            // 合并单元格
            if (null != margeCells) {
                for (int key : margeCells.keySet()) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(
                            startRow + i - 1, startRow + i - 1, key, margeCells.get(key));
                    sheet.addMergedRegion(cellRangeAddress);
                }
            }


            //设置总值
            if (null != totalValue) {
                int totalRowNum = startRow + values.size() - 1;
                XSSFRow totalRow = sheet.getRow(totalRowNum);
                for (int t : totalValue.keySet()) {
                    String totalString = tranferObjectToDoubleString(totalValue.get(t));
                    totalRow.getCell(t).setCellValue(new XSSFRichTextString(totalString));
                }
            }
        }
    }

    /**
     * 主要用于单元格以数字格式显示数字（非文本）
     * @param sheet      sheet
     * @param values     结果集
     * @param copyRow    复制样式的行
     * @param startRow   结果集开始插入行
     * @param margeCells 需要合并的单元格
     * @param digitCol 数字单元格
     * @param totalValue 需要计算总值的列
     */
    public static void insertRowsWithNumber(XSSFSheet sheet, List<String[]> values,
                                            int copyRow, int startRow, Map<Integer, Integer> margeCells,Set<Integer> digitCol,
                                            Map<Integer, Double> totalValue) {
        XSSFRow targetRow = sheet.getRow(copyRow);
        // 1传入的list大小确定添加的行数
        if (values == null || values.size() < 1) {
            return;
        }
        int n = values.size();
        // 插入行
        if (n > 1) {
            sheet.shiftRows(startRow, sheet.getLastRowNum(), n - 1, true, false);
        }
        // 插入值ֵ
        for (int i = 0; i < values.size(); i++) {

            String[] field = values.get(i);
            if (field == null || field.length < 1) {
                continue;
            }

            XSSFCell sourceCell;
            XSSFCell targetCell;
            // 开始的时候有一处空行
            XSSFRow row = sheet.createRow(startRow + i - 1);
            if (targetRow != null) {
                row.setHeight(targetRow.getHeight());
            }
            for (int m = 0; m < targetRow.getPhysicalNumberOfCells(); m++) {
                sourceCell = row.createCell(m);
                targetCell = targetRow.getCell(m);
                if (targetCell == null) {
                    targetCell = targetRow.createCell(m);
                }
                XSSFCellStyle cellStyle = targetCell.getCellStyle();
                sourceCell.setCellStyle(cellStyle);
                sourceCell.setCellType(targetCell.getCellType());
            }
            for (int j = 0; j < field.length; j++) {
                if (null == field[j]) {
                    continue;
                }
                //判断值是否为空
                String str = field[j];
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                if (null != digitCol && digitCol.contains(j)) {
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(Double.parseDouble(str));
                }else{
                    XSSFRichTextString string = new XSSFRichTextString(str);
                    cell.setCellValue(string);
                }
            }
            // 合并单元格
            if (null != margeCells) {
                for (int key : margeCells.keySet()) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(
                            startRow + i - 1, startRow + i - 1, key, margeCells.get(key));
                    sheet.addMergedRegion(cellRangeAddress);
                }
            }


            //设置总值
            if (null != totalValue) {
                int totalRowNum = startRow + values.size() - 1;
                XSSFRow totalRow = sheet.getRow(totalRowNum);
                for (int t : totalValue.keySet()) {
                    totalRow.getCell(t).setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    totalRow.getCell(t).setCellValue(totalValue.get(t) == null ? null
                            : Double.parseDouble(totalValue.get(t).toString())/100);
                }
            }
        }
    }

    /**
     * @param sheet      sheet
     * @param values     结果集
     * @param copyRow    复制样式的行
     * @param startRow   结果集开始插入行
     * @param margeCells 需要合并的单元格
     * @param totalValue 需要计算总值的列
     */
    public static void insertRows(HSSFSheet sheet, List<String[]> values,
                                  int copyRow, int startRow, Map<Integer, Integer> margeCells,
                                  Map<Integer, Integer> totalValue) {
        HSSFRow targetRow = sheet.getRow(copyRow);
        // 1传入的list大小确定添加的行数
        if (null == values || values.size() < 0) {
            return;
        }
        int n = values.size();
        // 插入行
        if (n > 1) {
            sheet.shiftRows(startRow, sheet.getLastRowNum(), n - 1, true, false);
        }
        //设置值
        setValue(values, sheet, startRow, targetRow, margeCells);

        //设置总值
        if (null != totalValue) {
            int totalRowNum = startRow + values.size() - 1;
            HSSFRow totalRow = sheet.getRow(totalRowNum);
            for (int t : totalValue.keySet()) {
                String totalString = ObjectUtils.toString(totalValue.get(t));
                totalRow.getCell(t).setCellValue(new HSSFRichTextString(totalString));
            }
        }
    }

    /**
     * @param values    需要填充到Excel的值
     * @param colName   存放对应列号及列名map
     * @param arraySize 表格列数
     * @return 返回结果
     */
    public static List<String[]> transferMapToString(List<Map<String, Object>> values, Map<Integer, String> colName,
                                                     Set<Integer> digitCol, int arraySize) {
        List<String[]> result = new ArrayList<String[]>();
        if (null == values || values.size() < 0) {
            return null;
        }
        for (Map<String, Object> map : values) {
            String[] value = new String[arraySize];
            for (Map.Entry<Integer, String> entry : colName.entrySet()) {
                //转化数值字段
                Integer key = entry.getKey();
                if (null != digitCol && digitCol.contains(key)) {
                    value[key] =tranferObjectToDoubleString(map.get(entry.getValue()));
                } else {
                    value[key] = ObjectUtils.toString(map.get(entry.getValue()));
                }
            }
            result.add(value);
        }
        return result;
    }

    /***
     * 获取Sheet中的某一行
     *
     * @param sheet    2007以上Excel Sheet对象
     * @param rowIndex Excel中行数
     * @return 返回Excel行对象
     */
    public static XSSFRow getRow(XSSFSheet sheet, Integer rowIndex) {
        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    public static void setCellValue(XSSFRow row, int cellNo, String value) {
        setCellValue(row, cellNo, null, value);
    }

    public static void setCellValue(XSSFRow row, int cellNo, HSSFCellStyle style, String value) {
        XSSFCell cell = row.getCell(cellNo);
        if (cell == null) {
            cell = row.createCell(cellNo);
        }
        if (style != null) {
            cell.setCellStyle(style);
        }
        cell.setCellValue(value);
    }

    /***
     * 获取Sheet中的某一行
     *
     * @param sheet    Excel中Sheet
     * @param rowIndex Excel行数
     * @return 返回Excel对象Row
     */
    public static HSSFRow getRow(HSSFSheet sheet, Integer rowIndex) {
        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    public static void setCellValue(HSSFRow row, int cellNo, String value) {
        setCellValue(row, cellNo, null, value);
    }

    /**
     * 设置Cell的值
     *
     * @param row    Excel行
     * @param cellNo Cell
     * @param style  Excel样式
     * @param value  填入值
     */
    public static void setCellValue(HSSFRow row, int cellNo, HSSFCellStyle style, String value) {
        HSSFCell cell = row.getCell(cellNo);
        if (cell == null) {
            cell = row.createCell(cellNo);
        }
        if (style != null) {
            cell.setCellStyle(style);
        }
        cell.setCellValue(new HSSFRichTextString(value));
    }

    /**
     * 设置Cell的样式
     *
     * @param row    Excel行
     * @param cellNo Cell
     * @param style  Excel样式
     */
    public static void setCellStyle(XSSFRow row, int cellNo, XSSFCellStyle style) {
        XSSFCell cell = row.getCell(cellNo);
        if (cell == null) {
            cell = row.createCell(cellNo);
        }
        if (style != null) {
            cell.setCellStyle(style);
        }
    }
}
