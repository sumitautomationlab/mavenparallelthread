package demo.cafe.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData_UAT {

	public static int TestData_Excel(String testcasename) throws Exception {

		XSSFSheet sheet = null;
		int flag = 0;
		File file = new File(".\\src\\main\\resources\\TestData_UAT.xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheet = wb.getSheetAt(i);
			if ((sheet.getSheetName().toString()).equals(testcasename)) {
				flag = i;
			}

		}
		return flag;

	}

	public static int TestData_Legacy_Excel(String testcasename, String time) throws Exception {

		XSSFSheet sheet = null;
		int flag = 0;
		File file = new File(".\\Test_Result\\Test_Data_Legacy_" + time + ".xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheet = wb.getSheetAt(i);
			if ((sheet.getSheetName().toString()).equals(testcasename)) {
				flag = i;

			}

		}
		return flag;

	}

	public static String getTestData(int flag, int row, int column) throws Exception {
		String data = null;
		File file = new File(".\\src\\main\\resources\\TestData_UAT.xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		Row row_value = sheet.getRow(row);
		Cell cellvalue = row_value.getCell(column);
		if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
			data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
		else
			data = cellvalue.getStringCellValue();
		return data;
	}

	public static String getTestData_str(int flag, int row, int column) throws Exception {
		String data = null;
		File file = new File(".\\src\\main\\resources\\TestData_UAT.xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		Row row_value = sheet.getRow(row);
		Cell cellvalue = row_value.getCell(column);
		if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
			data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
		else
			data = cellvalue.getStringCellValue();
		return data;
	}

	public static String getTestData_UAT(int flag, int row, int column, String excel_time) throws Exception {
		String data = null;
		File file = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		Row row_value = sheet.getRow(row);
		Cell cellvalue = row_value.getCell(column);
		if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
			data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
		else
			data = cellvalue.getStringCellValue();
		return data;
	}

	public static int getRow(int flag) throws Exception {

		File file = new File(".\\src\\main\\resources\\TestData_UAT.xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		int row = sheet.getLastRowNum();
		return row;
	}

	public static int getRow_UAT(int flag, String excel_time) throws Exception {

		File file = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		int row = sheet.getLastRowNum();
		return row;
	}

	public static int getcolumn(int flag, String excel_time) throws Exception {

		File file = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
		int flag1 = 0;
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		Row row = sheet.getRow(0);
		if (row != null) {
			String cell_1 = row.getCell(1).getStringCellValue();
			String cell_2 = row.getCell(2).getStringCellValue();
			if (cell_1 != null || cell_2 != null) {
				flag1 = 1;
			}

		}
		return flag1;
	}

	public static void TestData_Create_UAT(String excel_time) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		FileOutputStream out = new FileOutputStream(
				new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx"));
		XSSFSheet Spreadsheet = workbook.createSheet("Cafe_UAT_Legacy_Account");
		workbook.write(out);
		out.close();
	}

	public static void TestData_Create_UAT_final(String excel_time) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		FileOutputStream out = new FileOutputStream(
				new File(".\\Test_Result\\Final\\Test_Data_Legacy_Final_" + excel_time + ".xlsx"));
		XSSFSheet Spreadsheet = workbook.createSheet("Cafe_UAT_Legacy_Account");
		workbook.write(out);
		out.close();
	}
}
