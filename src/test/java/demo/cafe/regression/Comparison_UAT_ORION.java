package demo.cafe.regression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.google.common.base.Objects;
import com.google.common.collect.ObjectArrays;

import demo.cafe.common.DriverInitialize;
import demo.cafe.common.logging;

public class Comparison_UAT_ORION {

	@Test
	public void compare_uat_orion() throws Exception {
		ArrayList<String> notfound_orion = new ArrayList<String>();
		boolean trunk = false;
		boolean internet = false;
		boolean ethernet = false;
		boolean bve = false;
		boolean voice = false;
		String[] account_header = null;
		String[] Generic = { "Compare Value", "ORION Site ID/ GEN3 Account", "GEN2 Account", "Customer Name",
				"Account Type", "LOBs", "Product Type" };
		String[] Trunk = { "My Account for Business in Trunk", "Switch in Trunk", "Transport in Trunk",
				"Product in Trunk", "Total TNs in Trunk", "Trunking Header in Trunk", "E911 in Trunk", "LRN in Trunk",
				"PRI Trunk Config in Trunk" };
		String[] Internet = { "My Account for Business in Internet", "Package in Internet",
				"Download Speed in Internet", "Upload Speed in Internet", "Transport in Internet",
				"Gateway Static IP in Internet", "Usable Static IP Range in Internet", "Add ons in Internet",
				"IPv6 Prefix Delegation in Internet", "Make/Model in Internet" };
		String[] Ethernet = { "My Account for Business in Ethernet", "Product type in Ethernet" };
		String[] BVE = { "My Account for Business in BVE", "Switch in BVE", "Transport in BVE", "Site Type in BVE",
				"Group ID in BVE", "ESG DHCP in BVE", "Price Model in BVE", "BTN in BVE", "Make/ Model in BVE" };
		/*
		 * String[] Voice = { "My Account for Business in Voice LOB",
		 * "Contract END", "Bundle", "Basic", "FF", "Mobility", "Seats", "HG",
		 * "AA", "RCF", "Transport", "TN", "Voice LOB Make/ Model" };
		 */
		String[] Voice = { "My Account for Business in Voice", "Contract END in Voice", "Bundle in Voice",
				"Basic in Voice", "FF in Voice", "Mobility in Voice", "Seats in Voice", "HG in Voice", "AA in Voice",
				"RCF in Voice", "TN in Voice", "Make/ Model in Voice" };

		String ComparisonLOB = DriverInitialize.getComparisonLOB();
		int lob_count = 0;

		if (ComparisonLOB.contains("Trunk")) {
			lob_count++;
			trunk = true;
		}

		if (ComparisonLOB.contains("Internet")) {
			lob_count++;
			internet = true;
		}

		if (ComparisonLOB.contains("Ethernet")) {
			lob_count++;
			ethernet = true;
		}

		if (ComparisonLOB.contains("BVE")) {
			lob_count++;
			bve = true;
		}

		if (ComparisonLOB.contains("Voice")) {
			lob_count++;
			voice = true;
		}
		
		if (lob_count == 0) {
			account_header = Generic;
		}

		if (lob_count == 1) {
			if (trunk) {
				account_header = ObjectArrays.concat(Generic, Trunk, String.class);
			}
			if (internet) {
				account_header = ObjectArrays.concat(Generic, Internet, String.class);
			}
			if (ethernet) {
				account_header = ObjectArrays.concat(Generic, Ethernet, String.class);
			}
			if (bve) {
				account_header = ObjectArrays.concat(Generic, BVE, String.class);
			}
			if (voice) {
				account_header = ObjectArrays.concat(Generic, Voice, String.class);
			}
		}

		if (lob_count == 2) {
			if (trunk && internet) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				account_header = ObjectArrays.concat(value, Internet, String.class);
			} else if (trunk && ethernet) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				account_header = ObjectArrays.concat(value, Ethernet, String.class);
			} else if (trunk && bve) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				account_header = ObjectArrays.concat(value, BVE, String.class);
			} else if (trunk && voice) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				account_header = ObjectArrays.concat(value, Voice, String.class);
			}

			if (internet && ethernet) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				account_header = ObjectArrays.concat(value, Ethernet, String.class);
			} else if (internet && bve) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				account_header = ObjectArrays.concat(value, BVE, String.class);
			} else if (internet && voice) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				account_header = ObjectArrays.concat(value, Voice, String.class);
			}

			if (ethernet && bve) {
				String[] value = ObjectArrays.concat(Generic, Ethernet, String.class);
				account_header = ObjectArrays.concat(value, BVE, String.class);
			} else if (ethernet && voice) {
				String[] value = ObjectArrays.concat(Generic, Ethernet, String.class);
				account_header = ObjectArrays.concat(value, Voice, String.class);
			}

			if (bve && voice) {
				String[] value = ObjectArrays.concat(Generic, BVE, String.class);
				account_header = ObjectArrays.concat(value, Voice, String.class);
			}
		}

		if (lob_count == 3) {
			if (!trunk && !internet) {
				String[] value = ObjectArrays.concat(Generic, Ethernet, String.class);
				String[] value1 = ObjectArrays.concat(value, BVE, String.class);
				account_header = ObjectArrays.concat(value1, Voice, String.class);
			} else if (!trunk && !ethernet) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				String[] value1 = ObjectArrays.concat(value, BVE, String.class);
				account_header = ObjectArrays.concat(value1, Voice, String.class);
			} else if (!trunk && !bve) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				String[] value1 = ObjectArrays.concat(value, Ethernet, String.class);
				account_header = ObjectArrays.concat(value1, Voice, String.class);
			} else if (!trunk && !voice) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				String[] value1 = ObjectArrays.concat(value, Ethernet, String.class);
				account_header = ObjectArrays.concat(value1, BVE, String.class);
			}

			if (!internet && !ethernet) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, BVE, String.class);
				account_header = ObjectArrays.concat(value1, Voice, String.class);
			} else if (!internet && !bve) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Ethernet, String.class);
				account_header = ObjectArrays.concat(value1, Voice, String.class);
			} else if (!internet && !voice) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Ethernet, String.class);
				account_header = ObjectArrays.concat(value1, BVE, String.class);
			}

			if (!ethernet && !bve) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Internet, String.class);
				account_header = ObjectArrays.concat(value1, Voice, String.class);
			} else if (!ethernet && !voice) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Internet, String.class);
				account_header = ObjectArrays.concat(value1, BVE, String.class);
			}

			if (!bve && !voice) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Internet, String.class);
				account_header = ObjectArrays.concat(value1, Ethernet, String.class);
			}
		}

		if (lob_count == 4) {
			if (!trunk) {
				String[] value = ObjectArrays.concat(Generic, Internet, String.class);
				String[] value1 = ObjectArrays.concat(value, Ethernet, String.class);
				String[] value2 = ObjectArrays.concat(value1, BVE, String.class);
				account_header = ObjectArrays.concat(value2, Voice, String.class);
			}
			if (!internet) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Ethernet, String.class);
				String[] value2 = ObjectArrays.concat(value1, BVE, String.class);
				account_header = ObjectArrays.concat(value2, Voice, String.class);
			}
			if (!ethernet) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Internet, String.class);
				String[] value2 = ObjectArrays.concat(value1, BVE, String.class);
				account_header = ObjectArrays.concat(value2, Voice, String.class);
			}
			if (!bve) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Internet, String.class);
				String[] value2 = ObjectArrays.concat(value1, Ethernet, String.class);
				account_header = ObjectArrays.concat(value2, Voice, String.class);
			}
			if (!voice) {
				String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
				String[] value1 = ObjectArrays.concat(value, Internet, String.class);
				String[] value2 = ObjectArrays.concat(value1, Ethernet, String.class);
				account_header = ObjectArrays.concat(value2, BVE, String.class);
			}

		}
		if (lob_count == 5) {
			String[] value = ObjectArrays.concat(Generic, Trunk, String.class);
			String[] value1 = ObjectArrays.concat(value, Internet, String.class);
			String[] value2 = ObjectArrays.concat(value1, Ethernet, String.class);
			String[] value3 = ObjectArrays.concat(value2, BVE, String.class);
			account_header = ObjectArrays.concat(value3, Voice, String.class);
		}

		logging.LoggerInfo("Header for Comparison LOB: " + account_header);

		String orion_account = null;
		String old_account = null;
		String orion_acc = null;
		int a = 1;
		try {
			Scanner uat = new Scanner(System.in);
			Scanner orion = new Scanner(System.in);
			Scanner exclude_rules = new Scanner(System.in);
			logging.LoggerInfo("Enter file name for UAT: ");
			String uat_excel = uat.nextLine();
			uat_excel = uat_excel + ".xlsx";
			logging.LoggerInfo("UAT File name is: " + uat_excel);
			logging.LoggerInfo("Enter file name for ORION: ");
			String orion_excel = orion.nextLine();
			orion_excel = orion_excel + ".xlsx";
			logging.LoggerInfo("ORION File name is: " + orion_excel);
			logging.LoggerInfo(
					"Please provide any exclude column during execution: \n Press 3: Name \n Press 4: Address \n Press 5: Product Type \n Press 6: Email \n Press 7: Phone Number \n Press 8: LOBs \n Press -1: None");
			String exclue = exclude_rules.nextLine();
			int inum = Integer.parseInt(exclue);

			logging.LoggerInfo("******Comparison is in progress between Old and new Account " + orion_excel + " and "
					+ uat_excel + " ******");

			// Orion Excel fetch
			File orion_file = new File(".\\Test_Result\\Final\\" + orion_excel);
			FileInputStream orion_fls = new FileInputStream(orion_file);
			XSSFWorkbook orion_wb = new XSSFWorkbook(orion_fls);
			XSSFSheet orion_sheet = orion_wb.getSheetAt(0);
			int orion_row = orion_sheet.getLastRowNum();
			logging.LoggerInfo("Number of row in Orion is: " + orion_row);

			// UAT Excel fetch
			File uat_file = new File(".\\Test_Result\\Final\\" + uat_excel);
			FileInputStream uat_fls = new FileInputStream(uat_file);
			XSSFWorkbook uat_wb = new XSSFWorkbook(uat_fls);
			XSSFSheet uat_sheet = uat_wb.getSheetAt(0);
			int uat_row = uat_sheet.getLastRowNum();
			logging.LoggerInfo("Number of row in UAT is: " + uat_row);

			Row orion_row_value;
			Cell orion_cell_value;
			Cell orion_cell_acc;
			Cell UAT_cell_value;
			Cell oldAcc;
			Cell NewAcc;
			Cell MisMatch;
			Cell MisMatchColumn;
			Cell oldValue;
			Cell newValue;
			Cell siteID;

			// CreatingCompareResultSheet
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss aa");
			Date date = new Date();
			String compare_sheet = formatter.format(date);
			XSSFWorkbook compare_workbook = new XSSFWorkbook();
			FileOutputStream compare_out = new FileOutputStream(
					new File(".\\Test_Result\\Final\\Compare_UAT_ORION_" + compare_sheet + ".xlsx"));
			XSSFSheet compare_Spreadsheet = compare_workbook.createSheet("Compare_UAT_ORION");
			Row compare_row;
			// CreatingColumnLables
			compare_row = compare_Spreadsheet.createRow(0);
			oldAcc = compare_row.createCell(0);
			oldAcc.setCellValue("OldGEN2");
			NewAcc = compare_row.createCell(1);
			NewAcc.setCellValue("NewGEN3");
			siteID = compare_row.createCell(2);
			siteID.setCellValue("Compare_Value");
			MisMatch = compare_row.createCell(3);
			MisMatch.setCellValue("MisMatch(Yes/No)");
			MisMatchColumn = compare_row.createCell(4);
			MisMatchColumn.setCellValue("MisMatch Column");
			oldValue = compare_row.createCell(5);
			oldValue.setCellValue("Pre-Migration Value");
			newValue = compare_row.createCell(6);
			newValue.setCellValue("Post-Migration Value");

			// Fetch individual account number from Orion sheet
			for (int i = 0; i <= orion_row; i++) {
				orion_row_value = orion_sheet.getRow(i);
				orion_cell_value = orion_row_value.getCell(0);
				orion_cell_acc = orion_row_value.getCell(1);
				UAT_cell_value = orion_row_value.getCell(2);
				orion_account = orion_cell_value.getStringCellValue();
				orion_acc = orion_cell_acc.getStringCellValue();
				old_account = UAT_cell_value.getStringCellValue();

				int row_num_uat = row_search_uat(uat_sheet, uat_row, orion_account);
				if (row_num_uat == -1) {
					String message = "New Account " + orion_account + " is not present";
					notfound_orion.add(message);
					continue;
				} else {
					int uat_column_size = uat_sheet.getRow(row_num_uat).getLastCellNum();
					int orion_column_size = orion_sheet.getRow(i).getLastCellNum();

					if (Objects.equal(uat_column_size, orion_column_size)) {

						for (int k = 0; k < orion_column_size; k++) {

							if (inum == k && inum == 8) {
								break;
							}

							if (inum == k) {
								continue;
							} else {
								String orion_value = orion_sheet.getRow(i).getCell(k).getStringCellValue();
								String orion_value_l = orion_value.toLowerCase();
								String uat_value = uat_sheet.getRow(row_num_uat).getCell(k).getStringCellValue();
								String uat_value_l = uat_value.toLowerCase();
								if (!Objects.equal(orion_value_l, uat_value_l)) {
									compare_row = compare_Spreadsheet.createRow(a);
									oldAcc = compare_row.createCell(0);
									oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
									NewAcc = compare_row.createCell(1);
									NewAcc.setCellValue(orion_acc);
									siteID = compare_row.createCell(2);
									siteID.setCellValue(orion_account);
									MisMatch = compare_row.createCell(3);
									MisMatch.setCellValue("Yes");
									MisMatchColumn = compare_row.createCell(4);
									MisMatchColumn.setCellValue(account_header[k]);
									oldValue = compare_row.createCell(5);
									oldValue.setCellValue(uat_value);
									newValue = compare_row.createCell(6);
									newValue.setCellValue(orion_value);
									a++;

								} else if (Objects.equal(orion_value, uat_value)) {
									compare_row = compare_Spreadsheet.createRow(a);
									oldAcc = compare_row.createCell(0);
									oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
									NewAcc = compare_row.createCell(1);
									NewAcc.setCellValue(orion_acc);
									siteID = compare_row.createCell(2);
									siteID.setCellValue(orion_account);
									MisMatch = compare_row.createCell(3);
									MisMatch.setCellValue("No");
									MisMatchColumn = compare_row.createCell(4);
									MisMatchColumn.setCellValue(account_header[k]);
									oldValue = compare_row.createCell(5);
									oldValue.setCellValue(uat_value);
									newValue = compare_row.createCell(6);
									newValue.setCellValue(orion_value);
									a++;

								}
							}
						}
					}

					if (uat_column_size < orion_column_size) {
						logging.LoggerInfo("Orion Column showing as " + orion_column_size
								+ " and UAT Column size showing as " + uat_column_size);
						String uat_value = null;
						String orion_value_l = null;
						String orion_value = null;
						String uat_value_l = null;
						for (int o = 0; o < orion_column_size; o++) {
							int flag = 0;

							if (inum == o && inum == 8) {
								break;
							}

							if (inum == o) {
								continue;
							} else {
								if (o < 8) {
									orion_value = orion_sheet.getRow(i).getCell(o).getStringCellValue();
									orion_value_l = orion_value.toLowerCase();
									uat_value = uat_sheet.getRow(row_num_uat).getCell(o).getStringCellValue();
									uat_value_l = uat_value.toLowerCase();
									if (Objects.equal(orion_value_l, uat_value_l)) {
										flag = 1;
										compare_row = compare_Spreadsheet.createRow(a);
										oldAcc = compare_row.createCell(0);
										oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
										NewAcc = compare_row.createCell(1);
										NewAcc.setCellValue(orion_acc);
										siteID = compare_row.createCell(2);
										siteID.setCellValue(orion_account);
										MisMatch = compare_row.createCell(3);
										MisMatch.setCellValue("No");
										MisMatchColumn = compare_row.createCell(4);
										MisMatchColumn.setCellValue(account_header[o]);
										oldValue = compare_row.createCell(5);
										oldValue.setCellValue(uat_value);
										newValue = compare_row.createCell(6);
										newValue.setCellValue(orion_value);
										a++;
									}
								} else {

									for (int u = 8; u < uat_column_size; u++) {
										orion_value = orion_sheet.getRow(i).getCell(o).getStringCellValue();
										orion_value_l = orion_value.toLowerCase();
										uat_value = uat_sheet.getRow(row_num_uat).getCell(u).getStringCellValue();
										uat_value_l = uat_value.toLowerCase();
										if (Objects.equal(orion_value_l, uat_value_l)) {
											flag = 1;
											compare_row = compare_Spreadsheet.createRow(a);
											oldAcc = compare_row.createCell(0);
											oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
											NewAcc = compare_row.createCell(1);
											NewAcc.setCellValue(orion_acc);
											siteID = compare_row.createCell(2);
											siteID.setCellValue(orion_account);
											MisMatch = compare_row.createCell(3);
											MisMatch.setCellValue("No");
											MisMatchColumn = compare_row.createCell(4);
											MisMatchColumn.setCellValue(account_header[o]);
											oldValue = compare_row.createCell(5);
											oldValue.setCellValue(uat_value);
											newValue = compare_row.createCell(6);
											newValue.setCellValue(orion_value);
											a++;
											break;
										}
									}
								}
								if (flag == 0) {
									String msg = "Old account: " + UAT_cell_value + " ,New account: " + orion_acc
											+ " ,New Site id: " + orion_account + " , Mismatch in column: "
											+ account_header[o] + " - Before Migration - " + uat_value
											+ " , After Migration - " + orion_value;
									notfound_orion.add(msg);
									compare_row = compare_Spreadsheet.createRow(a);
									oldAcc = compare_row.createCell(0);
									oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
									NewAcc = compare_row.createCell(1);
									NewAcc.setCellValue(orion_acc);
									siteID = compare_row.createCell(2);
									siteID.setCellValue(orion_account);
									MisMatch = compare_row.createCell(3);
									MisMatch.setCellValue("Yes");
									MisMatchColumn = compare_row.createCell(4);
									MisMatchColumn.setCellValue(account_header[o]);
									oldValue = compare_row.createCell(5);
									oldValue.setCellValue(uat_value);
									newValue = compare_row.createCell(6);
									newValue.setCellValue(orion_value);
									a++;
								}
							}
						}
					}

					if (uat_column_size > orion_column_size) {
						logging.LoggerInfo("Orion Column showing as " + orion_column_size
								+ " and UAT Column size showing as " + uat_column_size);
						String uat_value = null;
						String orion_value_l = null;
						String orion_value = null;
						String uat_value_l = null;
						for (int o = 0; o < orion_column_size; o++) {
							int flag = 0;

							if (inum == o && inum == 8) {
								break;
							}

							if (inum == o) {
								continue;
							} else {
								if (o < 8) {
									orion_value = orion_sheet.getRow(i).getCell(o).getStringCellValue();
									orion_value_l = orion_value.toLowerCase();
									uat_value = uat_sheet.getRow(row_num_uat).getCell(o).getStringCellValue();
									uat_value_l = uat_value.toLowerCase();
									if (Objects.equal(orion_value_l, uat_value_l)) {
										flag = 1;
										compare_row = compare_Spreadsheet.createRow(a);
										oldAcc = compare_row.createCell(0);
										oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
										NewAcc = compare_row.createCell(1);
										NewAcc.setCellValue(orion_acc);
										siteID = compare_row.createCell(2);
										siteID.setCellValue(orion_account);
										MisMatch = compare_row.createCell(3);
										MisMatch.setCellValue("No");
										MisMatchColumn = compare_row.createCell(4);
										MisMatchColumn.setCellValue(account_header[o]);
										oldValue = compare_row.createCell(5);
										oldValue.setCellValue(uat_value);
										newValue = compare_row.createCell(6);
										newValue.setCellValue(orion_value);
										a++;
									}
								} else {

									for (int u = 8; u < uat_column_size; u++) {
										orion_value = orion_sheet.getRow(i).getCell(o).getStringCellValue();
										orion_value_l = orion_value.toLowerCase();
										uat_value = uat_sheet.getRow(row_num_uat).getCell(u).getStringCellValue();
										uat_value_l = uat_value.toLowerCase();
										if (Objects.equal(orion_value_l, uat_value_l)) {
											flag = 1;
											compare_row = compare_Spreadsheet.createRow(a);
											oldAcc = compare_row.createCell(0);
											oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
											NewAcc = compare_row.createCell(1);
											NewAcc.setCellValue(orion_acc);
											siteID = compare_row.createCell(2);
											siteID.setCellValue(orion_account);
											MisMatch = compare_row.createCell(3);
											MisMatch.setCellValue("No");
											MisMatchColumn = compare_row.createCell(4);
											MisMatchColumn.setCellValue(account_header[o]);
											oldValue = compare_row.createCell(5);
											oldValue.setCellValue(uat_value);
											newValue = compare_row.createCell(6);
											newValue.setCellValue(orion_value);
											a++;
											break;
										}
									}
								}
								if (flag == 0) {
									String msg = "Old account: " + UAT_cell_value + " ,New account: " + orion_acc
											+ " ,New Site id: " + orion_account + " , Mismatch in column: "
											+ account_header[o] + " - Before Migration - " + uat_value
											+ " , After Migration - " + orion_value;
									notfound_orion.add(msg);
									compare_row = compare_Spreadsheet.createRow(a);
									oldAcc = compare_row.createCell(0);
									oldAcc.setCellValue(UAT_cell_value.getRichStringCellValue());
									NewAcc = compare_row.createCell(1);
									NewAcc.setCellValue(orion_acc);
									siteID = compare_row.createCell(2);
									siteID.setCellValue(orion_account);
									MisMatch = compare_row.createCell(3);
									MisMatch.setCellValue("Yes");
									MisMatchColumn = compare_row.createCell(4);
									MisMatchColumn.setCellValue(account_header[o]);
									oldValue = compare_row.createCell(5);
									oldValue.setCellValue(uat_value);
									newValue = compare_row.createCell(6);
									newValue.setCellValue(orion_value);
									a++;
								}
							}
						}
					}

				}

			}

			FileOutputStream output = new FileOutputStream(
					new File(".\\Test_Result\\Final\\Compare_UAT_ORION_" + compare_sheet + ".xlsx"));
			compare_workbook.write(output);
			compare_out.close();
			String emailpath = ".\\Test_Result\\Final\\Compare_UAT_ORION_" + compare_sheet + ".xlsx";
			// EmailFunctionality.EmailNotification("Compare", emailpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int row_search_uat(XSSFSheet uat_sheet, int uat_row, String orion_account) {

		Row uat_row_value;
		Cell uat_cell_value;
		String uat_account;
		int row_position_uat = -1;

		for (int j = 0; j <= uat_row; j++) {
			uat_row_value = uat_sheet.getRow(j);
			uat_cell_value = uat_row_value.getCell(0);
			uat_account = uat_cell_value.getStringCellValue();
			if (Objects.equal(uat_account, orion_account)) {
				row_position_uat = j;
				break;
			}
		}
		return row_position_uat;
	}

}