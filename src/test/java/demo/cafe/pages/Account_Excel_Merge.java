package demo.cafe.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import demo.cafe.common.TestData_UAT;

public class Account_Excel_Merge {
	static int newrow = 0;
	static int newrow_orion = 0;

	public static void AccountExcelMerge(String finalexceltime, ArrayList<String> accounts_uat) throws Exception {
		// Create Final Excel Data
		TestData_UAT.TestData_Create_UAT_final(finalexceltime);
		File file_final = new File(
				".\\Test_Result\\Final\\Test_Data_Legacy_Final_" + finalexceltime + ".xlsx");
		FileInputStream fls_final = new FileInputStream(file_final);
		XSSFWorkbook wb_final = new XSSFWorkbook(fls_final);
		XSSFSheet sheet_final = wb_final.getSheetAt(0);
		int lastRow = sheet_final.getLastRowNum();
		Row row_num;
		Cell cell;
		// Fetched old Excel data
		for (int k = 0; k < accounts_uat.size(); k++) {
			String excel_time = accounts_uat.get(k);
			File file = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
			int flag = TestData_UAT.TestData_Legacy_Excel("Cafe_UAT_Legacy_Account", excel_time);
			int row_count = TestData_UAT.getRow_UAT(flag, excel_time); 
			int column_count = TestData_UAT.getcolumn(flag, excel_time);
			FileInputStream fls = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fls);
			XSSFSheet sheet = wb.getSheetAt(flag);
			if (row_count > 0 || column_count > 0) {
				for (int i = 0; i <= row_count; i++) {
					int column_size = sheet.getRow(i).getLastCellNum();
					row_num = sheet_final.createRow(newrow);
					for (int j = 0; j < column_size; j++) {
						String value = TestData_UAT.getTestData_UAT(flag, i, j, excel_time);
						cell = row_num.createCell(j);
						cell.setCellValue(value);
					}
					newrow = newrow + 1;
				}
			}
		}
		FileOutputStream output = new FileOutputStream(file_final);
		wb_final.write(output);
		output.close();
		String emailpath = ".\\Test_Result\\Final\\Test_Data_Legacy_Final_" + finalexceltime + ".xlsx";
		//EmailFunctionality.EmailNotification("GEN2Analysis", emailpath);
	}

	public static void AccountExcelMergeOrion(String finalexceltime, ArrayList<String> accounts_uat) throws Exception {
		// Create Final Excel Data
		TestData_UAT.TestData_Create_UAT_final(finalexceltime);
		File file_final = new File(
				".\\Test_Result\\Final\\Test_Data_Legacy_Final_" + finalexceltime + ".xlsx");
		FileInputStream fls_final = new FileInputStream(file_final);
		XSSFWorkbook wb_final = new XSSFWorkbook(fls_final);
		XSSFSheet sheet_final = wb_final.getSheetAt(0);
		int lastRow = sheet_final.getLastRowNum();
		Row row_num;
		Cell cell;
		// Fetched old Excel data
		for (int k = 0; k < accounts_uat.size(); k++) {
			String excel_time = accounts_uat.get(k);
			File file = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
			int flag = TestData_UAT.TestData_Legacy_Excel("Cafe_UAT_Legacy_Account", excel_time);
			int row_count = TestData_UAT.getRow_UAT(flag, excel_time);
			int column_count = TestData_UAT.getcolumn(flag, excel_time);
			FileInputStream fls = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fls);
			XSSFSheet sheet = wb.getSheetAt(flag);
			if (row_count > 0 || column_count > 0) {
				for (int i = 0; i <= row_count; i++) {
					int column_size = sheet.getRow(i).getLastCellNum();
					row_num = sheet_final.createRow(newrow_orion);
					for (int j = 0; j < column_size; j++) {
						String value = TestData_UAT.getTestData_UAT(flag, i, j, excel_time);
						cell = row_num.createCell(j);
						cell.setCellValue(value);
					}
					newrow_orion = newrow_orion + 1;
				}
			}
		}
		FileOutputStream output = new FileOutputStream(file_final);
		wb_final.write(output);
		output.close();
		String emailpath = ".\\Test_Result\\Final\\Test_Data_Legacy_Final_" + finalexceltime + ".xlsx";
		//EmailFunctionality.EmailNotification("GEN3Analysis", emailpath);
	}
}
