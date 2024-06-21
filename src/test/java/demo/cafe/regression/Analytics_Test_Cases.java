package demo.cafe.regression;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import demo.cafe.analytics.Analytics_ACCOUNTNUMBER_ORION_SITE_ID_NULL;
import demo.cafe.analytics.Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED;
import demo.cafe.analytics.Analytics_AUTHENTICATIONLEVEL_SUBTITLE;
import demo.cafe.analytics.Analytics_CAFELOCATION_CAFEROLE_NULL;
import demo.cafe.analytics.Analytics_CONSUMER_ID_NULL;
import demo.cafe.analytics.Analytics_ITGNameField_null;
import demo.cafe.analytics.Analytics_IVRAUTHENTICATIONLEVEL_CPNIVERIFIED;
import demo.cafe.analytics.Analytics_LOB_fieldisnotcaptured_tickets;
import demo.cafe.analytics.Analytics_TicketNumberField_empty;
import demo.cafe.analytics.Analytics_TicketNumberField_null_truckroll;
import demo.cafe.common.DriverInitialize;
import demo.cafe.common.FileDelete;
import demo.cafe.common.logging;
import demo.cafe.pages.Account_Excel_Merge;
import demo.cafe.pages.Account_Search;

public class Analytics_Test_Cases {
	public static String analytics_file, file_name;

	@BeforeTest
	public void tearDown() throws Exception {
		try {
			FileDelete.final_file_delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void Analytics_cases() throws Exception {
		try {
			analytics_file = DriverInitialize.getAnalyticsFile();
			file_name = analytics_file.substring(10);
			Scanner analytics = new Scanner(System.in);
			logging.LoggerInfo("Please provide analytics cases you need to run: \n "
					+ "Press 1: CAFENX_AGENTDETAILS_FACT_Report: Analytics_CAFELOCATION_CAFEROLE_NULL: CAFELOCATION and CAFEROLE column values shouldn’t be null. Tets cases should fail with null records returned \n "
					+ "Press 2: CAFENX_AGENTDETAILS_FACT_Report: Analytics_CONSUMER_ID_NULL: Consumer ID column values shouldn’t be null. If null fail the test case with null records returned \n "
					+ "Press 3: CAFENX_CustomerDim: Analytics_ACCOUNTNUMBER_ORION_SITE_ID_NULL: When AccountNumber is null the Orion_Customer ID shouldn’t be null. Fail the test case with both Account Number and Orion_Customer ID fields returned \n "
					+ "Press 4: CAFENX_AUTHENTICATION_FACT_Repo: Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED: When Authentication Level is C, CPNI verified should be Y and (PINEntered should be Y or QAEntered should be Y or CPNI Bypass should be 'Verified' or CCEntered should be Y') \n "
					+ "Press 5: CAFENX_AUTHENTICATION_FACT_Repo: Analytics_AUTHENTICATIONLEVEL_SUBTITLE: When Authentication level is C subtitle should be Full with CPNI, for F its Full, if L its Locked, P-Partial, X-Partial with CPNI, Z-Full with CPNI \n "
					+ "Press 6: CAFENX_AUTHENTICATION_FACT_Repo: Analytics_IVRAUTHENTICATIONLEVEL_CPNIVERIFIED: IVR Authentication level should be added. When IVR is F and CPNI is done by agent then IVR authentication should be F, Authentication level should be C and CPNI verified should be Y \n "
					+ "Press 7: CAFENX_TicketAttributes: Analytics_TicketNumberField_empty: Verify Ticket number field is not empty \n "
					+ "Press 8: CAFENX_TruckRollDetails: Analytics_LOB_fieldisnotcaptured_tickets: Verify LOB field is captured for all the tickets \n "
					+ "Press 9: CAFENX_TruckRollDetails: Analytics_TicketNumber_Field_null_truck: Ticket Number shouldn’t be null \n "
					+ "Press 10: CAFENX_TruckRollDetails: Analytics_ITGNameField_null_truck: ITG Name shouldn't be null  \n "
					+ "Press 11: All of these \n"
					+ " For Multiple run, please select multiple values with , seperated ");
			String analytics_value = analytics.nextLine();
			if (analytics_value.equals("1"))
				Analytics_CAFELOCATION_CAFEROLE_NULL.Analytics_CAFELOCATION_CAFEROLE();
			if (analytics_value.equals("2"))
				Analytics_CONSUMER_ID_NULL.Analytics_CONSUMER_ID();
			if (analytics_value.equals("3"))
				Analytics_ACCOUNTNUMBER_ORION_SITE_ID_NULL.Analytics_ACCOUNTNUMBER_ORION_SITE_ID();
			if (analytics_value.equals("4"))
				Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED.Analytics_AUTHENTICATIONLEVEL_CPNIVERIFY();
			if (analytics_value.equals("5"))
				Analytics_AUTHENTICATIONLEVEL_SUBTITLE.Analytics_AUTHENTICATIONLEVEL_SUB();
			if (analytics_value.equals("6"))
				Analytics_IVRAUTHENTICATIONLEVEL_CPNIVERIFIED.Analytics_AUTHENTICATIONLEVEL_CPNIVERIFY();
			if (analytics_value.equals("7"))
				Analytics_TicketNumberField_empty.Analytics_TicketNumber_Field_empty();
			if (analytics_value.equals("8"))
				Analytics_LOB_fieldisnotcaptured_tickets.Analytics_LOBfieldisnotcaptured_tickets();
			if (analytics_value.equals("9"))
				Analytics_TicketNumberField_null_truckroll.Analytics_TicketNumber_Field_null_truck();
			if (analytics_value.equals("10"))
				Analytics_ITGNameField_null.Analytics_ITGNameField_null_truck();
			if (analytics_value.equals("11")) {
				System.out.println(
						"********************CAFENX_AGENTDETAILS_FACT_Report: Analytics_CAFELOCATION_CAFEROLE_NULL: CAFELOCATION and CAFEROLE column values shouldn’t be null. Tets cases should fail with null records returned********************");
				Analytics_CAFELOCATION_CAFEROLE_NULL.Analytics_CAFELOCATION_CAFEROLE();
				System.out.println(
						"********************CAFENX_AGENTDETAILS_FACT_Report: Analytics_CONSUMER_ID_NULL: Consumer ID column values shouldn’t be null. If null fail the test case with null records returned********************");
				Analytics_CONSUMER_ID_NULL.Analytics_CONSUMER_ID();
				System.out.println(
						"********************CAFENX_CustomerDim: Analytics_ACCOUNTNUMBER_ORION_SITE_ID_NULL: When AccountNumber is null the Orion_Customer ID shouldn’t be null. Fail the test case with both Account Number and Orion_Customer ID fields returned********************");
				Analytics_ACCOUNTNUMBER_ORION_SITE_ID_NULL.Analytics_ACCOUNTNUMBER_ORION_SITE_ID();
				System.out.println(
						"********************CAFENX_AUTHENTICATION_FACT_Repo: Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED: When Authentication Level is C, CPNI verified should be Y and (PINEntered should be Y or QAEntered should be Y or CPNI Bypass should be 'Verified' or CCEntered should be Y')********************");
				Analytics_AUTHENTICATIONLEVEL_CPNIVERIFIED.Analytics_AUTHENTICATIONLEVEL_CPNIVERIFY();
				System.out.println(
						"********************CAFENX_AUTHENTICATION_FACT_Repo: Analytics_AUTHENTICATIONLEVEL_SUBTITLE: When Authentication level is C subtitle should be Full with CPNI, for F its Full, if L its Locked, P-Partial, X-Partial with CPNI, Z-Full with CPNI********************");
				Analytics_AUTHENTICATIONLEVEL_SUBTITLE.Analytics_AUTHENTICATIONLEVEL_SUB();
				System.out.println(
						"********************CAFENX_AUTHENTICATION_FACT_Repo: Analytics_IVRAUTHENTICATIONLEVEL_CPNIVERIFIED: IVR Authentication level should be added. When IVR is F and CPNI is done by agent then IVR authentication should be F, Authentication level should be C and CPNI verified should be Y********************");
				Analytics_IVRAUTHENTICATIONLEVEL_CPNIVERIFIED.Analytics_AUTHENTICATIONLEVEL_CPNIVERIFY();
				System.out.println(
						"********************CAFENX_TicketAttributes: Analytics_TicketNumberField_empty: Verify Ticket number field is not empty********************");
				Analytics_TicketNumberField_empty.Analytics_TicketNumber_Field_empty();
				System.out.println(
						"********************CAFENX_TruckRollDetails: Analytics_LOB_fieldisnotcaptured_tickets: Verify LOB field is captured for all the tickets********************");
				Analytics_LOB_fieldisnotcaptured_tickets.Analytics_LOBfieldisnotcaptured_tickets();
				System.out.println(
						"********************CAFENX_TruckRollDetails: Analytics_TicketNumber_Field_null_truck: Ticket Number shouldn’t be null********************");
				Analytics_TicketNumberField_null_truckroll.Analytics_TicketNumber_Field_null_truck();
				System.out.println(
						"********************CAFENX_TruckRollDetails: Analytics_ITGNameField_null_truck: ITG Name shouldn't be null********************");
				Analytics_ITGNameField_null.Analytics_ITGNameField_null_truck();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}