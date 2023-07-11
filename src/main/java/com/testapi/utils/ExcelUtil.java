package com.testapi.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenjiafneg
 * @Date 2020/8/5 17:38
 * @Version 1.0
 * sheet.getLastRowNum();//返回最后一行的索引，即比行总数小1
 * row.getLastCellNum();//返回的是最后一列的列数，即等于总列数
 */
public class ExcelUtil {
    private XSSFWorkbook ExcelWBook; //整个ex文档对象
    private XSSFSheet ExcelWSheet; //工作表对象
    private XSSFRow Row; //行对象
    private XSSFCell Cell; //单元格
    private String filePath; //文件对象

    /**
     * 把文件对象转成excel文档对象
     *
     * @param filepath 文件路径
     * @throws Exception
     */
    public ExcelUtil(String filepath) throws Exception {
        FileInputStream ExcelFile;
        try {
            // 实例化 excel 文件的 FileInputStream 对象
            ExcelFile = new FileInputStream(filepath);
            // 实例化 excel 文件的 XSSFWorkbook 对象
            ExcelWBook = new XSSFWorkbook(ExcelFile);

        } catch (Exception e) {
            throw (e);
        }
        this.filePath = filepath;
    }

    /**
     * 读取excel，获取单元格的函数，此函数只支持后缀xlsx的excel文件
     *
     * @param RowNum    行号
     * @param ColNum    列号 ，知道行号和列号，就可以定位到单元格
     * @param sheetName 工作表名
     * @return
     * @throws Exception
     */
    public String getCellData(int RowNum, int ColNum, String sheetName) throws Exception {
        //获取excel文件中对应的工作表
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        try {
            // 通过函数参数指定单元格的行号和列号，获取指定的单元格对象
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            // 如果单元格内容为字符串类型，则使用 getStringCellValue 方法获取单元格的内容
            // 如果单元格内容为数字类型，则使用 getNumericCellValue() 方法获取单元格的内容
            String CellData = "";
            if (Cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                CellData = Cell.getStringCellValue();
            } else if (Cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                DecimalFormat df = new DecimalFormat("0");
                CellData = df.format(Cell.getNumericCellValue());
            } else {

                throw new RuntimeException("no support data type");
            }

            return CellData;

        } catch (Exception e) {

            throw new RuntimeException("get excel data error-->" + e.getMessage());
        }

    }

    /**
     * 设置写入单原格
     *
     * @param RowNum      行号
     * @param ColNum      列号
     * @param resultValue 写入内容
     * @param sheetName   工作表
     * @param testFlag    成功or失败
     * @throws Exception
     */

    // 在 excel 文件的执行单元格中写入数据，此函数只支持后缀为xlsx的 excel 文件写入
    public void setCellData(int RowNum, int ColNum, String resultValue, String sheetName, boolean testFlag)
            throws Exception {
        //读取工作表
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        try {
            // 获取excel文件中的行对象
            Row = ExcelWSheet.getRow(RowNum);
            // 如果单元格为空，则返回 Null
            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            //创建单元格样式对象
            XSSFCellStyle style = ExcelWBook.createCellStyle();
            if (testFlag) {
                //成功则标绿色
                style.setFillForegroundColor(IndexedColors.GREEN.index);

            } else {
                style.setFillForegroundColor(IndexedColors.RED.index);
            }
            //填充颜色
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            if (Cell == null) {
                // 当单元格对象是 null 的时候，则创建单元格
                // 如果单元格为空，无法直接调用单元格对象的 setCellValue 方法设定单元格的值
                Cell = Row.createCell(ColNum);
                // 创建单元格后可以调用单元格对象的 setCellValue 方法设定单元格的值
                Cell.setCellValue(resultValue);

            } else {
                // 单元格中有内容，则可以直接调用单元格对象的 setCellValue 方法设定单元格的值
                Cell.setCellValue(resultValue);
            }
            //加载样式
            Cell.setCellStyle(style);
            // 实例化写入 excel 文件的文件输出流对象
            FileOutputStream fileOut = new FileOutputStream(filePath);
            // 将内容写入 excel 文件中
            ExcelWBook.write(fileOut);
            // 调用flush 方法强制刷新写入文件
            fileOut.flush();
            // 关闭文件输出流对象
            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("set excel data error-->" + e.getMessage());
        }
    }

    /**
     * 数据驱动转换成二维素组
     * @param sheetName 工作表名
     * @return
     * @throws IOException
     */
    // 从 excel 文件获取测试数据的静态方法
    public Object[][] getTestData(String sheetName) throws IOException {
        // 通过 sheetName 参数,生成 sheet 对象
        Sheet Sheet = ExcelWBook.getSheet(sheetName);
        int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();

        List<Object[]> records = new ArrayList<Object[]>();
        for (int i = 1; i <= rowCount; i++) {
            // 获取excel文件中的行对象
            Row row = Sheet.getRow(i);
            //返回的是倒数第二列
            String fields[] = new String[row.getLastCellNum() - 2];
            //当前所在的行倒数第二例为y，读取当前行的数据
            if (row.getCell(row.getLastCellNum() - 2).getStringCellValue().equalsIgnoreCase("y") && row.getCell(row.getLastCellNum() - 2) != null) {
                //不读去倒数2列
                for (int j = 0; j < row.getLastCellNum() - 2; j++) {
                    //判断类型是不是string
                    if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        fields[j] = row.getCell(j).getStringCellValue();
                        //数值类型
                    } else if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                        DecimalFormat df = new DecimalFormat("0");
                        fields[j] = df.format(row.getCell(j).getNumericCellValue());
                    } else {
                        throw new RuntimeException("cell value format is error");
                    }
                }
                records.add(fields);
            }

        }

        // 定义函数返回值，即 Object[][]
        // 将存储测试数据的 list 转换为一个 Object 的二维数组
        // {{“”，“”，“”}，{“”，“”，“”}，{“”，“”，“”}，{“”，“”，“”}}
        Object[][] results = new Object[records.size()][];
        // 设置二维数组每行的值，每行是个object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        // 关闭 excel 文件
        if (!(results.length > 0)) {
            throw new RuntimeException("dataprovider data is empty");
        }
        return results;
    }

    public static void main(String[] args) throws Exception {
//
//        try {
//            ExcelUtil excel = new ExcelUtil("/Users/chenjiafeng/Desktop/TTDMKEYWORD.xlsx");
//
////            excel.setCellData(2, eu.getLastColumnNum(),"测试执行失败");
//            Object[][] ob = excel.getTestData("t");
//            for (int i = 0; i < ob.length; i++) {
//                Object[] obl = ob[i];
//                System.out.println("========");
//                for (int j = 0; j < obl.length; j++) {
//                    System.out.println(obl[j]);
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        ExcelUtil excel=null;
//        try {
//             excel = new ExcelUtil("/Users/chenjiafeng/Desktop/TTDMKEYWORD.xlsx");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        XSSFSheet sheet=excel.ExcelWBook.getSheet("login");
//        // 获取excel文件中的行对象
//        XSSFRow  Row1 =sheet.getRow(3);
//
//        //获取单元对象
//        XSSFCell  Cell1 = Row1.getCell(3, Row1.RETURN_BLANK_AS_NULL);
//
//        //创建单元格样式对象
//        XSSFCellStyle style =excel.ExcelWBook.createCellStyle();
//        //成功则标绿色
//        style.setFillForegroundColor(IndexedColors.GREEN.index);
//        //填充颜色
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        //加载样式
//        Cell1.setCellStyle(style);
//        // 实例化写入 excel 文件的文件输出流对象
//        FileOutputStream fileOut = new FileOutputStream("/Users/Work/test_api/testapi/src/main/resources/tianhong.xlsx");
//        // 将内容写入 excel 文件中
//        excel.ExcelWBook.write(fileOut);
//        // 调用flush 方法强制刷新写入文件
//        fileOut.flush();
//        // 关闭文件输出流对象
//        fileOut.close();


//        ExcelUtil excel = new ExcelUtil("/Users/chenjiafeng/Desktop/TTDMKEYWORD.xlsx");

//        //获取sheet的内容
//        XSSFSheet sheet=excel.ExcelWBook.getSheet("TTDM");
//        System.out.println(sheet);
//        // 获取excel文件中的行对象
//        XSSFRow  Row1 =sheet.getRow(3);
//
//        //获取指定单元格
//        String ttdm = excel.getCellData(1,4, "TTDM");
//        System.out.println(ttdm);
//
//        //写入指定单元格数据,将测试结果写入
//        excel.setCellData(1,6,"测试失败","TTDM",false);

        try {
            ExcelUtil excel = new ExcelUtil("/Users/chenjiafeng/Desktop/TTDMKEYWORD.xlsx");
            Object[][] ttdms = excel.getTestData("TTDM");
            for (int i=0;i<ttdms.length;i++){
                Object[] ttdm1 = ttdms[i];
                System.out.println("============");
                for (int j=0;j<ttdm1.length;j++){
                    System.out.println(ttdm1[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
