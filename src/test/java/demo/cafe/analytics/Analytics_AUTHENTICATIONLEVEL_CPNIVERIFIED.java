package demo.cafe.analytics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import demo.cafe.common.TestData_UAT;
import demo.cafe.common.logging;
import demo.cafe.regression.Analytics_Test_Cases;

public class Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED {
	static int newrow = 0;
	static int newrow_orion = 0;
	static boolean flag_present = false;

	public static void Analytics_AUTHENTICATIONLEVEL_CPNIVERIFY() throws Exception {
		// Create one new Excel
		String excel_time;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss aa");
		Date date = new Date();
		excel_time = formatter.format(date);
		XSSFWorkbook workbook_new = new XSSFWorkbook();
		FileOutputStream out_new = new FileOutputStream(
				new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx"));
		XSSFSheet Spreadsheet_new = workbook_new.createSheet("Cafe_UAT_Legacy_Account");
		workbook_new.write(out_new);
		out_new.close();
		// Open new Excel
		File file_analytics = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
		int flag_analytics = TestData_UAT.TestData_Legacy_Excel("Cafe_UAT_Legacy_Account", excel_time);
		FileInputStream fls_analytics = new FileInputStream(file_analytics);
		XSSFWorkbook wb_analytics = new XSSFWorkbook(fls_analytics);
		XSSFSheet sheet_analytics = wb_analytics.getSheetAt(flag_analytics);
		Row row_num_analytics;
		Cell cell_analytics;
		// Fetch Excel
		String data, data_ntid, data_pinentered, data_qaentered, data_cpnibypass, data_ccentered;
		ArrayList<String> notfoundaccount_uat = new ArrayList<String>();
		File file = new File(".\\Test_Result\\" + Analytics_Test_Cases.analytics_file + ".xlsx");
		XSSFSheet sheet = null;
		int flag = 0;
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		// Sheet Name
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheet = wb.getSheetAt(i);
			if ((sheet.getSheetName().toString()).equals("CAFENX_AUTHENTICATION_FACT_Repo")) {
				flag = i;

			}

		}
		sheet = wb.getSheetAt(flag);
		// Last row
		int row_count = sheet.getLastRowNum();
		// System.out.println("Total Row : " + row_count);
		Row row = sheet.getRow(0);
		int column_count = 0;
		// Column Count
		if (row != null) {
			String cell_1 = row.getCell(1).getStringCellValue();
			String cell_2 = row.getCell(2).getStringCellValue();
			if (cell_1 != null || cell_2 != null) {
				column_count = 1;
			}

		}
		int cafe_AuthenticationLevel = 0, cafe_CPNIVerified = 0, cafe_PinEntered = 0, cafe_QAEntered = 0,
				cafe_CPNIByPass = 0, cafe_CCEntered = 0;
		if (row_count > 0 || column_count > 0) {
			for (int i = 0; i <= 0; i++) {
				row_num_analytics = sheet_analytics.createRow(0);
				int noOfColumnsfirst = sheet.getRow(0).getLastCellNum();
				for (int k = 0; k < noOfColumnsfirst; k++) {
					Cell cellvalue = row.getCell(k);
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else
						data = cellvalue.getStringCellValue();
					if (data.contains("AuthenticationLevel"))
						cafe_AuthenticationLevel = k;
					if (data.contains("CPNIVerified"))
						cafe_CPNIVerified = k;
					if (data.contains("PinEntered"))
						cafe_PinEntered = k;
					if (data.contains("QAEntered"))
						cafe_QAEntered = k;
					if (data.contains("CPNIByPass"))
						cafe_CPNIByPass = k;
					if (data.contains("CCEntered"))
						cafe_CCEntered = k;
					cell_analytics = row_num_analytics.createCell(k);
					cell_analytics.setCellValue(data);
					// logging.LoggerInfo("Value Inserted in Row 0" + " and
					// Column " + k + " is " + data);
				}
			}
			int j = 1;
			int column = 0;
			for (int i = 0; i <= row_count; i++) {
				// row_num_analytics = sheet_analytics.createRow(j);
				row = sheet.getRow(i);
				Cell cellvalue = row.getCell(cafe_AuthenticationLevel);

				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data = cellvalue.getStringCellValue();
				} else
					data = "";

				cellvalue = row.getCell(cafe_CPNIVerified);
				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data_ntid = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data_ntid = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data_ntid = cellvalue.getStringCellValue();
				} else
					data_ntid = "";

				cellvalue = row.getCell(cafe_PinEntered);
				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data_pinentered = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data_pinentered = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data_pinentered = cellvalue.getStringCellValue();
				} else
					data_pinentered = "";

				cellvalue = row.getCell(cafe_QAEntered);
				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data_qaentered = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data_qaentered = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data_qaentered = cellvalue.getStringCellValue();
				} else
					data_qaentered = "";

				cellvalue = row.getCell(cafe_CPNIByPass);
				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data_cpnibypass = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data_cpnibypass = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data_cpnibypass = cellvalue.getStringCellValue();
				} else
					data_cpnibypass = "";

				cellvalue = row.getCell(cafe_CCEntered);
				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data_ccentered = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data_ccentered = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data_ccentered = cellvalue.getStringCellValue();
				} else
					data_ccentered = "";

				// System.out.println("value is :" + data);
				if (data.equalsIgnoreCase("C")) {
					if (!data_ntid.contains("Y")) {
						if (data_pinentered.contains("Y") || data_qaentered.contains("Y")
								|| data_cpnibypass.contains("Verified") || data_ccentered.contains("Y")) {
							row_num_analytics = sheet_analytics.createRow(j);
							int noOfColumns = sheet.getRow(0).getLastCellNum();
							for (int k = 0; k < noOfColumns; k++) {
								cellvalue = row.getCell(k);
								if (cellvalue != null) {
									if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
										data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
									else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
										data = Boolean.toString((cellvalue.getBooleanCellValue()));
									else
										data = cellvalue.getStringCellValue();
								} else
									data = "";
								cell_analytics = row_num_analytics.createCell(k);
								cell_analytics.setCellValue(data);
								// logging.LoggerInfo("Value Inserted in Row " +
								// j + " and Column " + k + " is " + data);
								flag_present = true;
							}
							j++;
						}
					}

				}
			}
		}
		FileOutputStream output = new FileOutputStream(file_analytics);
		wb_analytics.write(output);
		output.close();
		File old_name = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
		File new_name = new File(".\\Test_Result\\Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED_"
				+ Analytics_Test_Cases.file_name + ".xlsx");
		if (!flag_present) {
			if (old_name.exists()) {
				if (old_name.delete()) {
					System.out.println("Test Scenario is Passed, Hence Excel will not be created");
				}
			}
		} else {
			if (old_name.renameTo(new_name))
				System.out.println("Executed successfully and excel created as : " + new_name.getName());
			else
				System.out.println("Error");
		}
	}
}