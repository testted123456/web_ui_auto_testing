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
import java.util.Iterator;
import java.util.List;
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
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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
	 * @param xssfCell
	 * @return
	 */
	public static String getCellValue(XSSFCell xssfCell) {

		if (null == xssfCell) {
			return null;
		}

		String cellValue = null;

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
		
		cellValue = ReplaceVariable.handleVarible(cellValue);
		return cellValue;
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
		return getCellValue(xssfCell);
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
			if (getCellValue(xssfCell).equals(columnName)) {
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
		cellValue = getCellValue(xssfCell);
		if (cellValue.trim().toLowerCase().equals("comments")) {
			cellEnd = cellEnd - 1;
		}

		TreeSet<Integer> set = new TreeSet<Integer>();

		// 参数名如果在excel的第一行中，则把对应的列号保存到set中
		if (list.size() == 0) {
			for (int i = cellStart + 1; i < cellEnd; i++) {
				String tmp = getCellValue(xssfRow.getCell(i));
				if (tmp.toLowerCase().equals("result")) {
					result_column = i;
					continue;
				}
				set.add(i);
			}
		} else {
			for (int i = cellStart + 1; i < cellEnd; i++) {
				Object tmp = getCellValue(xssfRow.getCell(i));
				if (tmp.toString().toLowerCase().equals("result")) {
					result_column = i;
					continue;
				}
				if (list.contains(tmp.toString().toLowerCase())) {
					set.add(i);
				}
			}
		}

		Iterator<Integer> ite = null;

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

			cellStart = xssfRow.getFirstCellNum();

			cellEnd = xssfRow.getLastCellNum();

			// 判断第一个cell是否为“Y”
			xssfCell = xssfRow.getCell(cellStart);

			if (null == xssfCell) {
				continue;
			} else if (getCellValue(xssfCell).toString().toUpperCase().equals("N")) {
				continue;
			}

			ite = set.iterator();
			// 处理保存参数的列
			while (ite.hasNext()) {
				xssfCell = xssfRow.getCell(ite.next());

				if (null == xssfCell) {
					cellValue = "";
				} else {
					cellValue = getCellValue(xssfCell);
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

		Object cellValue = getCellValue(xssfCell);

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

		cellValue = getCellValue(xssfCell);

		// 最后一列是否为comments，不是就报错
		if (!cellValue.toString().toLowerCase().trim().equals("comments")) {
			logger.error("最后一列不是comments...");
			Assert.fail("最后一列不是comments...");
		} else {
			xssfCell = xssfRow.getCell(cellEnd - 2);
			cellValue = getCellValue(xssfCell);
			if (!cellValue.toString().toLowerCase().trim().equals("result")) {
				logger.error("倒数第二列不是result...");
				Assert.fail("倒数第二列不是result...");
			}
		}
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
			if (!getCellValue(xssfCell).toString().toUpperCase().equals("Y")) {
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

	/*
	 * public static XSSFWorkbook getXSSFWorkbook(String file){ if(null ==
	 * file){ return null; }
	 * 
	 * XSSFWorkbook xssfWorkbook = null;
	 * 
	 * try { FileInputStream fis = new FileInputStream(file); xssfWorkbook = new
	 * XSSFWorkbook(fis); } catch (FileNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return xssfWorkbook; }
	 * 
	 * public static ArrayList<XSSFSheet> getSheets(String file){
	 * 
	 * XSSFWorkbook xssfWorkbook = getXSSFWorkbook(file);
	 * 
	 * if(xssfWorkbook == null){ return null; }
	 * 
	 * ArrayList<XSSFSheet> xssfSheets = new ArrayList<XSSFSheet>(); int
	 * numberOfSheets = 0; numberOfSheets = xssfWorkbook.getNumberOfSheets();
	 * 
	 * for(int i=0;i<numberOfSheets;i++){
	 * xssfSheets.add(xssfWorkbook.getSheetAt(i)); }
	 * 
	 * return xssfSheets; }
	 * 
	 * public static XSSFSheet getXSSFSheet(XSSFWorkbook xssfWorkbook, String
	 * sheetName){ if(xssfWorkbook == null){ return null; } return
	 * xssfWorkbook.getSheet(sheetName); }
	 * 
	 * public static XSSFCell getCell(int row, int col, XSSFSheet xssfSheet){
	 * XSSFCell xssCell = null; XSSFRow xssRow = xssfSheet.getRow(row);
	 * 
	 * if(null != xssRow){ xssCell = xssRow.getCell(col); }
	 * 
	 * if(null == xssCell){ xssCell = xssRow.createCell(col); }
	 * 
	 * return xssCell; }
	 * 
	 * public static int getRowNumber(XSSFSheet xssfSheet){ return
	 * xssfSheet.getLastRowNum(); }
	 * 
	 * public static int getColNumber(int row, XSSFSheet xssfSheet){ XSSFRow
	 * xssRow = xssfSheet.getRow(row); return xssRow.getLastCellNum(); }
	 * 
	 * public static void setCellValue(String cellValue, int row, int col,
	 * XSSFSheet xssfSheet){ XSSFCell xssCell = getCell(row, col, xssfSheet);
	 * xssCell.setCellValue(cellValue); }
	 * 
	 * public static void setCellValue(String cellValue, XSSFCell xssCell){
	 * if(null != xssCell){ xssCell.setCellValue(cellValue); }else{ logger.info(
	 * "xssCell is null!"); } }
	 * 
	 * public static void setCellColor(short index, XSSFCell xssCell){ Workbook
	 * workbook = xssCell.getSheet().getWorkbook(); CellStyle style =
	 * workbook.createCellStyle(); style.setFillForegroundColor(index);
	 * style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	 * xssCell.setCellStyle(style); }
	 * 
	 *//**
		 * 行号从0开始，列号从0开始
		 * 
		 * @param file
		 * @param sheetName
		 * @param row
		 * @param col
		 * @return
		 */
	/*
	 * public static String getCellValue(String file, String sheetName, int row,
	 * int col){ XSSFWorkbook xssfWorkbook = getXSSFWorkbook(file); XSSFSheet
	 * xssfSheet = getXSSFSheet(xssfWorkbook, sheetName); XSSFCell xssfCell =
	 * getCell(row, col, xssfSheet); return getCellValue(xssfCell); }
	 * 
	 *//**
		 * 获取EXCEL单元格的值
		 * 
		 * @param row
		 * @param col
		 * @param xssfSheet
		 * @return
		 */
	/*
	 * public static String getCellValue(int row, int col, XSSFSheet xssfSheet){
	 * 
	 * XSSFCell xssfCell = null; XSSFRow xssfRow = xssfSheet.getRow(row);
	 * 
	 * if(null != xssfRow){ xssfCell = xssfRow.getCell(col); }
	 * 
	 * return getCellValue(xssfCell); }
	 * 
	 *//**
		 * 获取EXCEL单元格的值
		 * 
		 * @param xssfCell
		 * @return
		 */
	/*
	 * public static String getCellValue(XSSFCell xssfCell){
	 * 
	 * if(null == xssfCell){ return null; }
	 * 
	 * String cellValue = null;
	 * 
	 * switch (xssfCell.getCellType()) { case HSSFCell.CELL_TYPE_NUMERIC: // 数字
	 * cellValue = String.valueOf(xssfCell.getNumericCellValue()); break; case
	 * HSSFCell.CELL_TYPE_STRING: // 字符串 cellValue =
	 * xssfCell.getStringCellValue(); break; case HSSFCell.CELL_TYPE_BOOLEAN: //
	 * Boolean cellValue = String.valueOf(xssfCell.getBooleanCellValue());
	 * break; case HSSFCell.CELL_TYPE_FORMULA: // 公式 cellValue =
	 * xssfCell.getCellFormula(); break; case HSSFCell.CELL_TYPE_BLANK: // 空值
	 * break; case HSSFCell.CELL_TYPE_ERROR: // 故障 break; default: cellValue =
	 * null; } return cellValue; }
	 * 
	 *//**
		 * 根据第一行的列名返回列号
		 * 
		 * @param columnName
		 * @param xssfSheet
		 * @return
		 */
	/*
	 * public static int getColumnIndex(String columnName, XSSFSheet xssfSheet){
	 * XSSFRow xssfRow = xssfSheet.getRow(0); int colStart = 1; int colEnd =
	 * xssfRow.getLastCellNum(); XSSFCell xssfCell = null; for(int
	 * i=colStart;i<=colEnd;i++){ xssfCell = xssfRow.getCell(i-1);
	 * if(getCellValue(xssfCell).equals(columnName)){ return i; } } return -1; }
	 * 
	 *//**
		 * 检查excel文件，第一列是flag，表示数据是否执行；最后一列是result，记录此条数据执行结果
		 * 
		 * @param filePath
		 * @param sheetName
		 */
	/*
	 * public static void checkExcelFile(String filePath, String sheetName){
	 * File file = new File(filePath);
	 * 
	 * if(!file.isFile()){ String msg = "excel file doesn't exist :" + filePath;
	 * logger.info(msg); }
	 * 
	 * FileInputStream fis;
	 * 
	 * XSSFWorkbook xssfWorkbook = null;
	 * 
	 * XSSFSheet xssfSheet = null;
	 * 
	 * try { fis = new FileInputStream(file);
	 * 
	 * InputStream is =
	 * ParseXLSX.class.getClassLoader().getResourceAsStream(filePath);
	 * 
	 * xssfWorkbook = getXSSFWorkbook(is); // new XSSFWorkbook(fis);
	 * 
	 * xssfSheet = xssfWorkbook.getSheet(sheetName); } catch (Exception e) { //
	 * TODO Auto-generated catch block logger.info("can't get sheet from file: "
	 * + filePath + ", and sheet name is :" + sheetName); }
	 * 
	 * //获取第一行，第一行如果为空则报错 XSSFRow xssfRow = xssfSheet.getRow(0);
	 * 
	 * if(xssfRow == null){ logger.info("can't get the first row from file: " +
	 * filePath + ", and sheet name is :" + sheetName); }
	 * 
	 * int cellEnd = xssfRow.getLastCellNum();
	 * 
	 * XSSFCell xssfCell = xssfRow.getCell(0);
	 * 
	 * if(xssfCell == null){ logger.info(
	 * "the last field is not result, and file is " + filePath +
	 * ", and sheet name is :" + sheetName); }
	 * 
	 * Object cellValue = getCellValue(xssfCell);
	 * 
	 * //第一列是否为flag，不是就报错
	 * if(!cellValue.toString().toLowerCase().trim().equals("flag")){
	 * logger.info("the first field is not flag, and file is " + filePath +
	 * ", and sheet name is :" + sheetName); }
	 * 
	 * xssfCell = xssfRow.getCell(cellEnd-1);
	 * 
	 * if(xssfCell == null){ logger.info("can not get excel cell, and file is "
	 * + filePath + ", and sheet name is :" + sheetName); }
	 * 
	 * cellValue = getCellValue(xssfCell);
	 * 
	 * //最后一列是否为result，不是就报错 if(!sheetName.equals("comment")){
	 * if(!cellValue.toString().toLowerCase().trim().equals("comments")){
	 * logger.error("the last field is not comments, and file is " + filePath +
	 * ", and sheet name is :" + sheetName); }else{ xssfCell =
	 * xssfRow.getCell(cellEnd-2); cellValue = getCellValue(xssfCell);
	 * if(!cellValue.toString().toLowerCase().trim().equals("result")){
	 * logger.error("the last field is not result, and file is " + filePath +
	 * ", and sheet name is :" + sheetName); } } } }
	 * 
	 *//**
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
	/*
	 * public static Object[][] getDataValue(String filePath, String sheetName,
	 * String ...args){
	 * 
	 * file = filePath;
	 * 
	 * // ParseXLSX.class.getClassLoader().getResource(name);
	 * 
	 * checkExcelFile(filePath, sheetName);
	 * 
	 * List<String> list = new ArrayList<String>();
	 * 
	 * for(String arg : args){ list.add(arg.toLowerCase()); }
	 * 
	 * Object [][] dataSource = null;
	 * 
	 * ArrayList<ArrayList<String>> arrayList = new
	 * ArrayList<ArrayList<String>>();
	 * 
	 * //excel workbook InputStream is =
	 * ParseXLSX.class.getClassLoader().getResourceAsStream(file); //
	 * xssfWorkbook = getXSSFWorkbook(file); xssfWorkbook = getXSSFWorkbook(is);
	 * 
	 * //excel sheet页 xssfSheet = getXSSFSheet(xssfWorkbook, sheetName);
	 * 
	 * //excel单元格 XSSFCell xssfCell = null;
	 * 
	 * //result所在的列 int result_column = 0;
	 * 
	 * String cellValue = null;
	 * 
	 * int rowStart = xssfSheet.getFirstRowNum();
	 * 
	 * int rowEnd = xssfSheet.getLastRowNum();
	 * 
	 * //excel sheet页得第一行 XSSFRow xssfRow = xssfSheet.getRow(0);
	 * 
	 * int cellStart = xssfRow.getFirstCellNum();
	 * 
	 * int cellEnd = xssfRow.getLastCellNum();
	 * 
	 * //最后一列是comments xssfCell = xssfRow.getCell(cellEnd-1); cellValue =
	 * getCellValue(xssfCell);
	 * if(cellValue.trim().toLowerCase().equals("comments")){ cellEnd =
	 * cellEnd-1; }
	 * 
	 * TreeSet<Integer> set = new TreeSet<Integer>();
	 * 
	 * //参数名如果在excel的第一行中，则把对应的列号保存到set中 if(list.size() == 0){ for(int
	 * i=cellStart+1;i<cellEnd;i++){ String tmp =
	 * getCellValue(xssfRow.getCell(i)); if(tmp.toLowerCase().equals("result")){
	 * result_column = i; continue; } set.add(i); } }else{ for(int
	 * i=cellStart+1;i<cellEnd;i++){ Object tmp =
	 * getCellValue(xssfRow.getCell(i));
	 * if(tmp.toString().toLowerCase().equals("result")){ result_column = i;
	 * continue; } if(list.contains(tmp.toString().toLowerCase())){ set.add(i);
	 * } } }
	 * 
	 * Iterator<Integer> ite = null;
	 * 
	 * //循环处理excel的行 for(int i=rowStart+1; i<=rowEnd; i++){
	 * 
	 * ArrayList<String> itemArrayList = new ArrayList<String>();
	 * 
	 * xssfRow = xssfSheet.getRow(i);
	 * 
	 * if(null == xssfRow){ continue; }
	 * 
	 * //result列置为空 if(result_column != 0){ setCellValue("", i, result_column,
	 * xssfSheet); }
	 * 
	 * cellStart = xssfRow.getFirstCellNum();
	 * 
	 * cellEnd = xssfRow.getLastCellNum();
	 * 
	 * //判断第一个cell是否为“Y” xssfCell = xssfRow.getCell(cellStart);
	 * 
	 * if(null == xssfCell){ continue; }else
	 * if(getCellValue(xssfCell).toString().toUpperCase().equals("N")){
	 * continue; }
	 * 
	 * ite = set.iterator(); //处理保存参数的列 while(ite.hasNext()){ xssfCell =
	 * xssfRow.getCell(ite.next());
	 * 
	 * if(null ==xssfCell){ cellValue = ""; }else{ cellValue =
	 * getCellValue(xssfCell); if(null == cellValue){ cellValue = ""; } }
	 * 
	 * itemArrayList.add(cellValue); } if(itemArrayList.size() != 0){
	 * arrayList.add(itemArrayList); } }
	 * 
	 * int size = arrayList.size(); dataSource = new Object[size][];
	 * 
	 * for(int i=0;i<arrayList.size();i++){ dataSource[i] =
	 * Arrays.copyOf(arrayList.get(i).toArray(),
	 * arrayList.get(i).toArray().length); }
	 * 
	 * //result列清空后写入文件 try { FileOutputStream fos = new
	 * FileOutputStream(filePath); xssfWorkbook.write(fos); fos.close(); } catch
	 * (FileNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return dataSource; }
	 * 
	 *//**
		 * 保存每个case运行结果
		 * 
		 * @param filePath
		 * @param resultsMap
		 */
	/*
	 * public static void saveResults(String filePath, TreeMap<String, Integer>
	 * resultsMap){ if(resultsMap.isEmpty()){ logger.info("result is empty.");
	 * return; }
	 * 
	 * file = filePath;
	 * 
	 * String sheetName = null;
	 * 
	 * //excel workbook if(null == xssfWorkbook){ xssfWorkbook =
	 * getXSSFWorkbook(file); }
	 * 
	 * //每个sheet运行结果 TreeMap<Long, Integer> sheetResultsMap = new TreeMap<Long,
	 * Integer>();
	 * 
	 * //每条数据测试结果 int status = -100;
	 * 
	 * String key = null;
	 * 
	 * int resultsSize = resultsMap.size();
	 * 
	 * Set<String> resultKeySet = resultsMap.keySet();
	 * 
	 * Set<String> sheetNameSet = new HashSet<String>();
	 * 
	 * Iterator<String> ite = resultKeySet.iterator();
	 * 
	 * while(ite.hasNext()){ String str = ite.next(); sheetName =
	 * str.substring(str.indexOf(':')+1); if(sheetName.equals("comment")){
	 * continue; } sheetNameSet.add(sheetName); }
	 * 
	 * ite = sheetNameSet.iterator();
	 * 
	 * while(ite.hasNext()){ sheetName = ite.next();
	 * 
	 * for(Map.Entry<String, Integer> entry : resultsMap.entrySet()){ key =
	 * entry.getKey(); if(key.endsWith(sheetName)){
	 * sheetResultsMap.put(Long.valueOf(key.substring(0, key.indexOf(':'))),
	 * entry.getValue()); } }
	 * 
	 * saveSheetResults(sheetName, sheetResultsMap); sheetResultsMap.clear(); }
	 * }
	 * 
	 *//**
		 * 保存一个sheet页的结果
		 * 
		 * @param sheetName
		 * @param sheetResultsMap
		 *//*
		 * public static void saveSheetResults(String sheetName, TreeMap<Long,
		 * Integer> sheetResultsMap){
		 * 
		 * //testcase的注册 if(sheetName.toLowerCase().equals("comment")){ return;
		 * }
		 * 
		 * //excel workbook if(null == xssfWorkbook){ xssfWorkbook =
		 * getXSSFWorkbook(file); }
		 * 
		 * //excel sheet页 xssfSheet = getXSSFSheet(xssfWorkbook, sheetName);
		 * 
		 * int rowEnd = 0;
		 * 
		 * int cellEnd = 0;
		 * 
		 * XSSFRow xssfRow = null;
		 * 
		 * XSSFCell xssfCell = null;
		 * 
		 * //result所在的列 cellEnd = xssfSheet.getRow(0).getLastCellNum() - 1;
		 * 
		 * //最后一列是comments xssfRow = xssfSheet.getRow(0); xssfCell =
		 * xssfRow.getCell(cellEnd); String cellValue = getCellValue(xssfCell);
		 * if(cellValue.trim().toLowerCase().equals("comments")){ cellEnd =
		 * cellEnd-1; }
		 * 
		 * rowEnd = xssfSheet.getLastRowNum();
		 * 
		 * for(int i=1;i<=rowEnd;i++){ xssfRow = xssfSheet.getRow(i);
		 * 
		 * //先把结果置为空 setCellColor(HSSFColor.TURQUOISE.index, getCell(i, cellEnd,
		 * xssfSheet));
		 * 
		 * xssfCell = xssfRow.getCell(0);
		 * 
		 * if(xssfCell == null ){ continue; }
		 * if(!getCellValue(xssfCell).toString().toUpperCase().equals("Y")){
		 * continue; }
		 * 
		 * xssfCell = getCell(i, cellEnd, xssfSheet);
		 * 
		 * Entry<Long, Integer> entry = sheetResultsMap.pollFirstEntry();
		 * 
		 * int status;
		 * 
		 * if(entry != null){ status = entry.getValue(); }else{ status = -99; }
		 * 
		 * String res = null;
		 * 
		 * switch (status) { case 1: res = "passed";
		 * setCellColor(HSSFColor.GREEN.index, xssfCell); break; case 2: res =
		 * "failed"; setCellColor(HSSFColor.RED.index, xssfCell); break; case
		 * -99: res = "not run"; break; default: res = "skipped"; break; }
		 * 
		 * setCellValue(res,xssfCell); }
		 * 
		 * try { FileOutputStream fos = new FileOutputStream(file);
		 * xssfWorkbook.write(fos); fos.close(); } catch (FileNotFoundException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
}
