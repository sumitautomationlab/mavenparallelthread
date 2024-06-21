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
import org.testng.annotations.Test;

import demo.cafe.common.TestData_UAT;
import demo.cafe.common.logging;
import demo.cafe.regression.Analytics_Test_Cases;

public class Analytics_TicketNumberField_empty {
	static int newrow = 0;
	static int newrow_orion = 0;
	static boolean flag_present = false;

	public static void Analytics_TicketNumber_Field_empty() throws Exception {
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
		String data, data_ntid;
		ArrayList<String> notfoundaccount_uat = new ArrayList<String>();
		File file = new File(".\\Test_Result\\" + Analytics_Test_Cases.analytics_file + ".xlsx");
		XSSFSheet sheet = null;
		int flag = 0;
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		// Sheet Name
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheet = wb.getSheetAt(i);
			if ((sheet.getSheetName().toString()).equals("CAFENX_TicketAttributes")) {
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
		int cafe_TicketNumber = 0;
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
					if (data.contains("TicketNumber"))
						cafe_TicketNumber = k;
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
				Cell cellvalue = row.getCell(cafe_TicketNumber);
				if (cellvalue != null) {
					if (cellvalue.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data = NumberToTextConverter.toText(cellvalue.getNumericCellValue());
					else if (cellvalue.getCellType() == Cell.CELL_TYPE_BOOLEAN)
						data = Boolean.toString((cellvalue.getBooleanCellValue()));
					else
						data = cellvalue.getStringCellValue();
				} else
					data = "";
				// System.out.println("value is :" + data);
				if (data.equalsIgnoreCase("NULL")) {
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
						// logging.LoggerInfo("Value Inserted in Row " + j + "
						// and Column " + k + " is " + data);
						flag_present = true;
					}
					j++;
				}
			}
		}
		FileOutputStream output = new FileOutputStream(file_analytics);
		wb_analytics.write(output);
		output.close();
		File old_name = new File(".\\Test_Result\\Test_Data_Legacy_" + excel_time + ".xlsx");
		File new_name = new File(
				".\\Test_Result\\Analytics_TicketNumberField_empty_" + Analytics_Test_Cases.file_name + ".xlsx");
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
