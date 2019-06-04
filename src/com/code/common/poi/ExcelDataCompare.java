
package com.code.common.poi;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataCompare {
	
	//实际，期望
	public static Object[] ExcelCompare(String filePaths1, int sheetNum1, int rowNum1, int Celnum1,String filePaths2, int sheetNum2, int rowNum2, int Celnum2) throws IOException{
		Object mm =readExcel(filePaths1, sheetNum1, rowNum1, Celnum1);
		Object nn =readExcel(filePaths2, sheetNum2, rowNum2, Celnum2);
		Object[] result = {mm,nn};
		return result;
		
	}

	public static Object readExcel(String filePaths, int sheetNum, int rowNum, int Celnum) throws IOException {

		String fileName = filePaths;
		XSSFWorkbook xwb = new XSSFWorkbook(fileName);
		// 循环工作表Sheet
		XSSFSheet sheet = xwb.getSheetAt(sheetNum);
		XSSFRow aRow = sheet.getRow(rowNum);
		XSSFCell aCell = aRow.getCell(Celnum);
		Object mm = getValue(aCell);
		return mm;
	}

	
	private static Object getValue(XSSFCell xCell) {
		   Object o = null;
	        int cellType = xCell.getCellType();
	        switch (cellType) {
	        case XSSFCell.CELL_TYPE_BLANK:
	            o = "";
	            break;
	        case XSSFCell.CELL_TYPE_BOOLEAN:
	            o = xCell.getBooleanCellValue();
	            break;
	        case XSSFCell.CELL_TYPE_ERROR:
	            o = "Bad value!";
	            break;
	        case XSSFCell.CELL_TYPE_NUMERIC:
	            o = getValueOfNumericCell(xCell);
	            break;
	        case XSSFCell.CELL_TYPE_FORMULA:
	            try {
	                o = getValueOfNumericCell(xCell);
	            } catch (IllegalStateException e) {
	                try {
	                    o = xCell.getRichStringCellValue().toString();
	                } catch (IllegalStateException e2) {
	                    o = xCell.getErrorCellString();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            break;
	        default:
	            o = xCell.getRichStringCellValue().getString();
	        }
	        return o;
	}
    // 获取数字类型的cell值
    private static Object getValueOfNumericCell(XSSFCell cell) {
        Boolean isDate = DateUtil.isCellDateFormatted(cell);
        Double d = cell.getNumericCellValue();
        Object o = null;
        if (isDate) {
            o = DateFormat.getDateTimeInstance()
                    .format(cell.getDateCellValue());
        } else {
            o = getRealStringValueOfDouble(d);
        }
        return o;
    }
    // 处理科学计数法与普通计数法的字符串显示，尽最大努力保持精度
    private static String getRealStringValueOfDouble(Double d) {
        String doubleStr = d.toString();
        boolean b = doubleStr.contains("E");
        int indexOfPoint = doubleStr.indexOf('.');
        if (b) {
            int indexOfE = doubleStr.indexOf('E');
            // 小数部分
            BigInteger xs = new BigInteger(doubleStr.substring(indexOfPoint
                    + BigInteger.ONE.intValue(), indexOfE));
            // 指数
            int pow = Integer.valueOf(doubleStr.substring(indexOfE
                    + BigInteger.ONE.intValue()));
            int xsLen = xs.toByteArray().length;
            int scale = xsLen - pow > 0 ? xsLen - pow : 0;
            doubleStr = String.format("%." + scale + "f", d);
        } else {
            java.util.regex.Pattern p = Pattern.compile(".0$");
            java.util.regex.Matcher m = p.matcher(doubleStr);
            if (m.find()) {
                doubleStr = doubleStr.replace(".0", "");
            }
        }
        return doubleStr;
    }

	public static void main(String[] args) {

	}
}