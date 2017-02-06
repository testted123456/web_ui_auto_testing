package com.nonobank.apps.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import com.nonobank.apps.utils.variable.ReplaceVariable;

public class ParseXLSX {

	private static Logger logger = LogManager.getLogger(ParseXLSX.class);
	private static File file;
	private static List<String> columnNames = new ArrayList<String>();
	private static Map<String, String> mapColumnNames = new HashMap<String, String>();

	public static File getFile(String f) {
		if (null != file) {
			if (file.getAbsolutePath().contains(f)) {
				return file;
			}
		}
		URL url = ParseXLSX.class.getClassLoader().getResource(f);

		String path = null;

		try {
			path = java.net.URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		file = new File(path);
		return file;
	}

	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fis;
	}

	public static XSSFWorkbook getXSSFWorkbook(FileInputStream fis) {
		XSSFWorkbook xssfWorkbook = null;
		try {
			xssfWorkbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xssfWorkbook;
	}

	// 返回execl sheet
	public static XSSFSheet getXSSFSheet(XSSFWorkbook xssfWorkbook, String sheetName) {
		if (xssfWorkbook == null) {
			return null;
		}
		return xssfWorkbook.getSheet(sheetName);
	}

	// 获取单元格
	public static XSSFCell getCell(int row, int col, XSSFSheet xssfSheet) {
		XSSFCell xssCell = null;
		XSSFRow xssRow = xssfSheet.getRow(row);

		if (null != xssRow) {
			xssCell = xssRow.getCell(col);
		}
		if (null == xssCell) {
			xssCell = xssRow.createCell(col);
		}
		return xssCell;
	}

	// 设置单元格的值
	public static void setCellValue(String cellValue, int row, int col, XSSFSheet xssfSheet) {
		XSSFCell xssCell = getCell(row, col, xssfSheet);
		xssCell.setCellValue(cellValue);
	}

	// 设置单元格的值
	public static void setCellValue(String cellValue, XSSFCell xssCell) {
		if (null != xssCell) {
			xssCell.setCellValue(cellValue);
		} else {
			logger.info("xssCell is null!");
		}
	}

	// 设置单元格的颜色
	public static void setCellColor(short index, XSSFCell xssCell) {
		Workbook workbook = xssCell.getSheet().getWorkbook();
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		xssCell.setCellStyle(style);
	}

	/**
	 * 获取EXCEL单元格的值
	 * 
	 * @param row
	 * @param col
	 * @param xssfSheet
	 * @return
	 */
	public static String getCellValue(int row, int col, XSSFSheet xssfSheet) {

		XSSFCell xssfCell = null;
		XSSFRow xssfRow = xssfSheet.getRow(row);

		if (null != xssfRow) {
			xssfCell = xssfRow.getCell(col);
		}
		return getCellValue(xssfCell, row, col);
	}

	// 获取列数
	public static int getColNumber(int row, XSSFSheet xssfSheet) {
		XSSFRow xssRow = xssfSheet.getRow(row);
		return xssRow.getLastCellNum();
	}

	// 获取行数
	public static int getRowNumber(XSSFSheet xssfSheet) {
		return xssfSheet.getLastRowNum();
	}

	/**
	 * 根据第一行的列名返回列号
	 * 
	 * @param columnName
	 * @param xssfSheet
	 * @return
	 */
	public static int getColumnIndex(String columnName, XSSFSheet xssfSheet) {
		XSSFRow xssfRow = xssfSheet.getRow(0);
		int colStart = 1;
		int colEnd = xssfRow.getLastCellNum();
		XSSFCell xssfCell = null;
		for (int i = colStart; i <= colEnd; i++) {
			xssfCell = xssfRow.getCell(i - 1);
			if (getCellValue(xssfCell, 0, i).equals(columnName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取测试数据，返回一个二维数组给Testng的DataProvider
	 * 
	 * @param filePath
	 *            ： excel文件路径
	 * @param args
	 *            ：sheet页中第一行所对应的参数名；如果不选args，则默认选择所有参数（不包文件括第一个参数flag，
	 *            也不包括最后一个参数result）； 第一个参数flag，取值为Y/N，Y表示执行此行数据，N表示不执行；
	 *            最后一个参数result，表示测试结果
	 * @return
	 */
	public static Object[][] getDataValue(String file, String sheetName, String... args) {
		File f = getFile(file);

		List<String> list = new ArrayList<String>();

		for (String arg : args) {
			list.add(arg.toLowerCase());
		}

		Object[][] dataSource = null;

		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();

		// excel workbook

		FileInputStream fis = getFileInputStream(f);
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(fis);

		checkExcelFile(xssfWorkbook, sheetName);

		// excel sheet页
		XSSFSheet xssfSheet = getXSSFSheet(xssfWorkbook, sheetName);

		// excel单元格
		XSSFCell xssfCell = null;

		// result所在的列
		int result_column = 0;

		String cellValue = null;

		int rowStart = xssfSheet.getFirstRowNum();

		int rowEnd = xssfSheet.getLastRowNum();

		// excel sheet页得第一行
		XSSFRow xssfRow = xssfSheet.getRow(0);

		int cellStart = xssfRow.getFirstCellNum();

		int cellEnd = xssfRow.getLastCellNum();

		// 最后一列是comments
		xssfCell = xssfRow.getCell(cellEnd - 1);
		cellValue = getCellValue(xssfCell, rowStart, cellEnd - 1);
		if (cellValue.trim().toLowerCase().equals("comments")) {
			cellEnd = cellEnd - 1;
		}

		TreeSet<Integer> set = new TreeSet<Integer>();

		// 参数名如果在excel的第一行中，则把对应的列号保存到set中
		if (list.size() == 0) {
			for (int i = cellStart; i < cellEnd; i++) {
				String tmp = getCellValue(xssfRow.getCell(i), rowStart, i);
				columnNames.add(tmp);
				if (tmp.toLowerCase().equals("result")) {
					result_column = i;
					continue;
				}
				set.add(i);
			}
		} else {
			for (int i = cellStart + 1; i < cellEnd; i++) {
				Object tmp = getCellValue(xssfRow.getCell(i), rowStart, i);
				if (tmp.toString().toLowerCase().equals("result")) {
					result_column = i;
					continue;
				}
				if (list.contains(tmp.toString().toLowerCase())) {
					set.add(i);
				}
			}
		}

		// 循环处理excel的行
		for (int i = rowStart + 1; i <= rowEnd; i++) {

			ArrayList<String> itemArrayList = new ArrayList<String>();

			xssfRow = xssfSheet.getRow(i);
			if (null == xssfRow) {
				continue;
			}

			// result列置为空
			if (result_column != 0) {
				setCellValue("", i, result_column, xssfSheet);
			}
			xssfCell = xssfRow.getCell(0);
			if (xssfCell.toString().equals("N")) {
				continue;
			}
			for (int col = 1; col < cellEnd - 1; col++) {
				xssfCell = xssfRow.getCell(col);
				if (null == xssfCell) {
					cellValue = "";
				} else {
					cellValue = getCellValue(xssfCell, i, col);
					if (null == cellValue) {
						cellValue = "";
					}
				}

				itemArrayList.add(cellValue);
			}
			if (itemArrayList.size() != 0) {
				arrayList.add(itemArrayList);
			}
		}

		int size = arrayList.size();
		dataSource = new Object[size][];

		for (int i = 0; i < arrayList.size(); i++) {
			dataSource[i] = Arrays.copyOf(arrayList.get(i).toArray(), arrayList.get(i).toArray().length);
		}

		// result列清空后写入文件
		try {
			FileOutputStream fos = new FileOutputStream(f);
			xssfWorkbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}

	/**
	 * 检查excel文件，第一列是flag，表示数据是否执行；最后一列是result，记录此条数据执行结果
	 * 
	 * @param filePath
	 * @param sheetName
	 */
	public static void checkExcelFile(XSSFWorkbook xssfWorkbook, String sheetName) {

		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);

		// 获取第一行，第一行如果为空则报错
		XSSFRow xssfRow = xssfSheet.getRow(0);

		if (xssfRow == null) {
			logger.error(sheetName + " : sheet页不存在...");
			Assert.fail(sheetName + " : sheet页不存在...");
		}

		XSSFCell xssfCell = xssfRow.getCell(0);

		Object cellValue = getCellValue(xssfCell, 0, 0);

		// 第一列是否为flag，不是就报错
		if (!cellValue.toString().toLowerCase().trim().equals("flag")) {
			logger.error("第一列不是flag...");
			Assert.fail("第一列不是flag...");
		}

		int cellEnd = xssfRow.getLastCellNum();
		xssfCell = xssfRow.getCell(cellEnd - 1);

		if (xssfCell == null) {
			logger.error("获取sheet cell失败...");
			Assert.fail("获取sheet cell失败...");
		}

		cellValue = getCellValue(xssfCell, 0, 0);

		// 最后一列是否为comments，不是就报错
		if (!cellValue.toString().toLowerCase().trim().equals("comments")) {
			logger.error("最后一列不是comments...");
			Assert.fail("最后一列不是comments...");
		} else {
			xssfCell = xssfRow.getCell(cellEnd - 2);
			cellValue = getCellValue(xssfCell, 0, 0);
			if (!cellValue.toString().toLowerCase().trim().equals("result")) {
				logger.error("倒数第二列不是result...");
				Assert.fail("倒数第二列不是result...");
			}
		}
	}

	public static void main(String[] args) {
		List<String> lst = new ArrayList<String>();
		// lst.add("/bindcard/LoginBindCard.xlsx");
		// lst.add("/bindcard/RegisterBindCard.xlsx");
		// lst.add("/portal/Login.xlsx");
		// lst.add("/portal/Register2.xlsx");
		// lst.add("/recharge/Recharge.xlsx");
		// lst.add("/repayment/PrePaymentTestCase.xlsx");
		// lst.add("/student/BorrowsTestCase.xlsx");
		// lst.add("/withdrawal/WithDrawal.xlsx");
		lst.add("/licai/PaymentByBalance.xlsx");
		lst.add("/licai/PaymentByNewBank.xlsx");
		Object[][] objects = getDataValue("resources/TestData/com/nonobank/apps/testcase" + lst.get(0), "test");
		System.out.println("******************objects=" + objects.length);
		for (Object[] objects2 : objects) {
			for (Object object : objects2) {
				System.out.println("**************object=" + object);
			}
		}

	objects = getDataValue("resources/TestData/com/nonobank/apps/testcase" + lst.get(1), "test");
		System.out.println("******************objects=" + objects.length);
		for (Object[] objects2 : objects) {
			for (Object object : objects2) {
				System.out.println("**************object=" + object);
			}
		}
	}

	public static String getCellValue(XSSFCell xssfCell, int row, int col) {

		if (null == xssfCell) {
			return null;
		}

		String cellValue = "";

		switch (xssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(xssfCell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			cellValue = xssfCell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(xssfCell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			cellValue = xssfCell.getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			break;
		default:
			cellValue = null;
		}
		if (row != 0) {
			cellValue = getCellValue(row, col, cellValue);
		}
		return cellValue;
	}

	private static String getCellValue(int row, int col, String cellValue) {
		String columnNameKey = columnNames.get(col) + "_" + row;
		if (cellValue.lastIndexOf("$") != -1 && cellValue.lastIndexOf("$") != 0) {
			int index = cellValue.indexOf("(");
			String replaceKey = cellValue.substring(index + 1, cellValue.length() - 2);
			String key = replaceKey.substring(2, replaceKey.length() - 1) + "_" + row;
			cellValue = cellValue.replace(replaceKey, mapColumnNames.get(key));
		}
		cellValue = ReplaceVariable.handleVarible(cellValue);
		mapColumnNames.put(columnNameKey, cellValue);
		return cellValue;
	}

	/**
	 * 保存一个sheet页的结果
	 * 
	 * @param sheetName
	 * @param resultMap
	 */
	public static void saveResults(String testfile, TreeMap<Long, Integer> resultMap) {
		// 测试方法默认为test,对应sheet页也为test
		String sheetName = "test";
		File file = getFile(testfile);
		FileInputStream fis = getFileInputStream(file);
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(fis);
		XSSFSheet xssfSheet = getXSSFSheet(xssfWorkbook, sheetName);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		// result所在的列
		int col_res = getColumnIndex("result", xssfSheet);
		int rowEnd = xssfSheet.getLastRowNum();

		for (int i = 1; i <= rowEnd; i++) {
			xssfRow = xssfSheet.getRow(i);

			// 先把结果置为空
			setCellColor(HSSFColor.TURQUOISE.index, getCell(i, col_res - 1, xssfSheet));

			xssfCell = xssfRow.getCell(0);

			if (xssfCell == null) {
				continue;
			}
			if (!getCellValue(xssfCell, i, col_res - 1).toString().toUpperCase().equals("Y")) {
				continue;
			}

			xssfCell = getCell(i, col_res - 1, xssfSheet);
			Entry<Long, Integer> entry = resultMap.pollFirstEntry();
			int status;

			if (entry != null) {
				status = entry.getValue();
			} else {
				status = -99;
			}

			String res = null;

			switch (status) {
			case 1:
				res = "passed";
				setCellColor(HSSFColor.GREEN.index, xssfCell);
				break;
			case 2:
				res = "failed";
				setCellColor(HSSFColor.RED.index, xssfCell);
				break;
			case -99:
				res = "not run";
				break;
			default:
				res = "skipped";
				break;
			}

			setCellValue(res, xssfCell);
		}

		try {
			FileOutputStream fos = new FileOutputStream(file);
			xssfWorkbook.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
