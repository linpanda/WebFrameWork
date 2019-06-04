package com.code.common.poi;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.code.common.testng.Assertion;

public class CompareExcel {

	public static void comparefiles(String excellFile1Path, String excellFile2Path, String casefilename) {
		try {
			// get input excel files
			FileInputStream excellFile1 = new FileInputStream(new File(excellFile1Path));
			FileInputStream excellFile2 = new FileInputStream(new File(excellFile2Path));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
			XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet1 = workbook1.getSheetAt(0);
			XSSFSheet sheet2 = workbook2.getSheetAt(0);

			// Compare sheets
			compareTwoSheets(sheet1, sheet2, casefilename);

			// close files
			excellFile1.close();
			excellFile2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void comparefiles(String excellFile1Path, String excellFile2Path, String casefilename, int colnums) {
		try {
			// get input excel files
			FileInputStream excellFile1 = new FileInputStream(new File(excellFile1Path));
			FileInputStream excellFile2 = new FileInputStream(new File(excellFile2Path));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
			XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet1 = workbook1.getSheetAt(0);
			XSSFSheet sheet2 = workbook2.getSheetAt(0);

			// Compare sheets
			compareTwoSheets(sheet1, sheet2, casefilename, colnums);

			// close files
			excellFile1.close();
			excellFile2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Compare Two Sheets
	public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2, String casefilename) {
		int firstRow1 = sheet1.getFirstRowNum();
		int lastRow1 = sheet1.getLastRowNum();
		boolean equalSheets = true;
		for (int i = firstRow1; i <= lastRow1; i++) {
			XSSFRow row1 = sheet1.getRow(i);
			XSSFRow row2 = sheet2.getRow(i);
			compareTwoRows(row1, row2, casefilename, i);
		}
		return equalSheets;
	}

	// Compare Two Sheets
	public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2, String casefilename, int colnums) {
		int firstRow1 = sheet1.getFirstRowNum();
		int lastRow1 = sheet1.getLastRowNum();
		boolean equalSheets = true;
		for (int i = firstRow1; i <= lastRow1; i++) {
			XSSFRow row1 = sheet1.getRow(i);
			XSSFRow row2 = sheet2.getRow(i);
			compareTwoRows(row1, row2, casefilename, i, colnums);
		}
		return equalSheets;
	}
	// Compare Two Rows

	public static void compareTwoRows(XSSFRow row1, XSSFRow row2, String casefilename, int rownum) {

		int firstCell1 = row1.getFirstCellNum();
		int lastCell1 = row1.getLastCellNum();

		// Compare all cells in a row

		for (int i = firstCell1; i < lastCell1; i++) {
			XSSFCell cell1 = row1.getCell(i);
			XSSFCell cell2 = row2.getCell(i);
			XSSFCell cellname = null;
			Object hintname = null;
			if (i >= 1) {
				cellname = row1.getCell(i - 1);
				hintname = getValue(cellname);
			}
			Object actualValue = getValue(cell1);
			Object excepValue = getValue(cell2);
			int colnums = i;
			Assertion.verifyEquals(actualValue, excepValue, "文件：" + casefilename + "\n           第" + (rownum + 1)
					+ "行 " + "第" + (colnums + 1) + "列 " + "\n           " + hintname);

		}
	}

	public static void compareTwoRows(XSSFRow row1, XSSFRow row2, String casefilename, int rownum, int colnums) {

		XSSFCell cell1 = row1.getCell(colnums);
		XSSFCell cell2 = row2.getCell(colnums);
		XSSFCell cellname = null;
		Object hintname = null;
		if (colnums >= 1) {
			cellname = row1.getCell(colnums - 1);
			hintname = getValue(cellname);
		}
		Object actualValue = getValue(cell1);
		Object excepValue = getValue(cell2);
		Assertion.verifyEquals(actualValue, excepValue, "文件：" + casefilename + "\n           第" + (rownum + 1) + "行 "
				+ "第" + (colnums + 1) + "列 " + "\n           " + hintname);

	}

	private static Object getValue(XSSFCell xCell) {
		Object o = null;
		if (xCell == null)
			return null;
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
			o = DateFormat.getDateTimeInstance().format(cell.getDateCellValue());
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
			BigInteger xs = new BigInteger(doubleStr.substring(indexOfPoint + BigInteger.ONE.intValue(), indexOfE));
			// 指数
			int pow = Integer.valueOf(doubleStr.substring(indexOfE + BigInteger.ONE.intValue()));
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

}