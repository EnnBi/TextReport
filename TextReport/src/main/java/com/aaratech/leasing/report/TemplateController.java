package com.aaratech.leasing.report;


import java.util.ArrayList;
import java.util.List;

import com.aaratech.leasing.report.entity.*;
import com.aaratech.leasing.report.repo.TemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TemplateController {

	@Autowired
    TemplateDao templateDao;
	
	@Autowired
	ReportGeneratorService reportService;

	enum Type{LABEL,VALUE,DATE,PAGE}
	enum Alignment{RIGHT,LEFT,CENTER}	

	
	@RequestMapping("/save")
	public void save() {

		Template template = new Template();
		template.setName("Class1_template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));		
		mainHeaders.add(setMainHeader("Classwise Accounts Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, false, true, template));
		mainHeaders.add(setMainHeader("Class        : ", Type.LABEL, 6, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("CLASS_ID", Type.VALUE, 6, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 7, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 8, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);
		
		
		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Account Number",template));
		headers.add(setHeaders("Name",template));
		headers.add(setHeaders("Loan Type",template));
		headers.add(setHeaders("Reason Code",template));
		headers.add(setHeaders("Priority",template));
		headers.add(setHeaders("Date in to Collections",template));
		headers.add(setHeaders("Delinquncy Cycle",template));
		headers.add(setHeaders("Total Amount Due",template));
		
		template.setTableHeaders(headers);
		
		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,16,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("LEASE_TYPE",Alignment.LEFT,11,template));
		keys.add(setKey("REASON_CODE",Alignment.LEFT,13,template));
		keys.add(setKey("PRIORITY",Alignment.LEFT,10,template));
		keys.add(setKey("COLLECTION_START_DATE",Alignment.LEFT,24,template));
		keys.add(setKey("DELINQUENT_DUE",Alignment.LEFT,18,template));
		keys.add(setKey("TOTAL_AMOUNT_DUE",Alignment.RIGHT,18,template));		
		template.setTableKeys(keys);
		
		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("Class Total    :", Type.LABEL, 2, Alignment.LEFT, 0, false, false, template));
		footer.add(setMainFooter("classTotal", Type.VALUE, 2, Alignment.RIGHT, 25, false, false, template));
		footer.add(setMainFooter("", Type.LABEL, 3, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("Grand Total    :", Type.LABEL, 2, Alignment.LEFT, 0, false, false, template));
		footer.add(setMainFooter("classTotal", Type.VALUE, 2, Alignment.RIGHT, 25, false, false, template));
		template.setMainFooter(footer);
		
		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);		
	}


	@RequestMapping("/class_reassignment_template")
	public void create_class_reassignment() {

		Template template = new Template();
		template.setName("Class_Reassignment_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Class Reassignment Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);


		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Contract / Card Id",template));
		headers.add(setHeaders("Name",template));
		headers.add(setHeaders("Date Moved",template));
		headers.add(setHeaders("Auto / Manual",template));
		headers.add(setHeaders("From Class",template));
		headers.add(setHeaders("To CLass",template));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,16,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("DATE_MOVED",Alignment.LEFT,11,template));
		keys.add(setKey("MOVED_BY",Alignment.LEFT,13,template));
		keys.add(setKey("NEW_CLASS",Alignment.LEFT,10,template));
		keys.add(setKey("OLD_CLASS",Alignment.LEFT,24,template));
		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/daily_collector_action_template")
	public void create_daily_collector_action_template() {

		Template template = new Template();
		template.setName("Daily_Collector_Action_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Daily Collectors Action Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);


		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Colector Id",template));
		headers.add(setHeaders("Action Code",template));
		headers.add(setHeaders("Action Date and Time",template));
		headers.add(setHeaders("Contract / Card Number",template));
		headers.add(setHeaders("Review Date",template));
		headers.add(setHeaders("Review Time",template));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("ACTION_COLLECTOR_ID",Alignment.LEFT,20,template));
		keys.add(setKey("ACTION_CODE",Alignment.LEFT,25,template));
		keys.add(setKey("ACTION_DATE_AND_TIME",Alignment.LEFT,30,template));
		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("ACTION_DATE",Alignment.LEFT,25,template));
		keys.add(setKey("ACTION_TIME",Alignment.LEFT,25,template));
		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/daily_transactions_template")
	public void create_daily_transactions_template() {

		Template template = new Template();
		template.setName("Daily_Transactions_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Daily Transaction Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);


		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Account Number",template));
		headers.add(setHeaders("Name",template));
		headers.add(setHeaders("Transaction Date",template));
		headers.add(setHeaders("Transaction Description",template));
		headers.add(setHeaders("Txn Type",template));
		headers.add(setHeaders("Amount",template));
		headers.add(setHeaders("Amount Flag",template));


		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CONTRACT_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("POSTING_DATE",Alignment.LEFT,30,template));
		keys.add(setKey("TXN_DESCRIPTION",Alignment.LEFT,20,template));
		keys.add(setKey("TXN_TYPE",Alignment.LEFT,25,template));
		keys.add(setKey("TXN_AMOUNT",Alignment.LEFT,25,template));
		keys.add(setKey("TXN_AMOUNT_FLAG",Alignment.LEFT,25,template));
		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}


	@RequestMapping("/rejected_accounts_template")
	public void create_rejected_accounts_template() {

		Template template = new Template();
		template.setName("Rejected_Accounts_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Exception Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);

		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Account Number",template));
		headers.add(setHeaders("Customer Id",template));
		headers.add(setHeaders("Reason Code",template));
		headers.add(setHeaders("Process Date",template));
		headers.add(setHeaders("Reject Reason",template));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_ID",Alignment.LEFT,25,template));
		keys.add(setKey("REASON_CODE",Alignment.LEFT,15,template));
		keys.add(setKey("PROCESS_DATE",Alignment.LEFT,20,template));
		keys.add(setKey("REJECT_REASON",Alignment.LEFT,30,template));

		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/override_ptp_report")
	public void create_override_ptp_template() {

		Template template = new Template();
		template.setName("Override_PTP_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Override PTP Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);

		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Contract/card Number",template));
		headers.add(setHeaders("Customer Name",template));
		headers.add(setHeaders("PTP Amount",template));
		headers.add(setHeaders("Action Code",template));
		headers.add(setHeaders("Collector  Id",template));
		headers.add(setHeaders("Date and Time",template));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();

		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("PTP_AMOUNT",Alignment.LEFT,15,template));
		keys.add(setKey("ACTION_CODE",Alignment.LEFT,20,template));
		keys.add(setKey("ACTION_COLLECTOR_ID",Alignment.LEFT,25,template));
		keys.add(setKey("ACTION_DATE_AND_TIME",Alignment.LEFT,20,template));

		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/purged_accounts_reports_template")
	public void create_purged_accounts_template() {

		Template template = new Template();
		template.setName("Purged_Accounts_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Purged Accounts Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);

		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Contract Id /Card Id",template));
		headers.add(setHeaders("Customer Name",template));
		headers.add(setHeaders("Collection start Date",template));
		headers.add(setHeaders("Collection End Date",template));
		headers.add(setHeaders("Purged Date",template));

		template.setTableHeaders(headers);
		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("COLLECTION_START_DATE",Alignment.LEFT,15,template));
		keys.add(setKey("COLLECTION_END_DATE",Alignment.LEFT,20,template));
		keys.add(setKey("PURGE_DATE",Alignment.LEFT,25,template));
		keys.add(setKey("COMPANY_CODE",Alignment.LEFT,20,template));

		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/PTP_Partial_template")
	public void create_PTP_Partial_template() {

		Template template = new Template();
		template.setName("PTP_Partial_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Purged Accounts Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);

		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));

		headers.add(setHeaders("Account Number",template));
		headers.add(setHeaders("Name",template));
		headers.add(setHeaders("PTP Amount",template));
		headers.add(setHeaders("PTP Date",template));
		headers.add(setHeaders("Grace Days",template));
		headers.add(setHeaders("Amount Paid",template));

		template.setTableHeaders(headers);
		List<Key> keys = new ArrayList<>();

		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("PAYMENT_AMOUNT",Alignment.LEFT,15,template));
		keys.add(setKey("PAYMENT_DATE",Alignment.LEFT,20,template));
		keys.add(setKey("PTP_GRACE_DAYS",Alignment.LEFT,25,template));
		keys.add(setKey("PAID_AMOUNT",Alignment.LEFT,25,template));
		keys.add(setKey("COMPANY_CODE",Alignment.LEFT,20,template));

		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/manual_Collector_Changed_template")
	public void create_Manual_Collector_Changed_template() {

		Template template = new Template();
		template.setName("Manual_Collector_Changed_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Manual Collector Changed Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);

		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template));


		headers.add(setHeaders("Contract/card Number",template));
		headers.add(setHeaders("Customer Name",template));
		headers.add(setHeaders("Old Collector",template));
		headers.add(setHeaders("New Collector",template));
		headers.add(setHeaders("Changed Date & Time",template));

		template.setTableHeaders(headers);
		List<Key> keys = new ArrayList<>();

		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,25,template));
		keys.add(setKey("ACTION_COLLECTOR_ID",Alignment.LEFT,15,template));
		keys.add(setKey("COLLECTOR_ID",Alignment.LEFT,15,template));
		keys.add(setKey("ACTION_DATE_AND_TIME",Alignment.LEFT,25,template));
		keys.add(setKey("COMPANY_CODE",Alignment.LEFT,20,template));

		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/broken_Promise_template")
	public void create_Broken_Promise_template() {

		Template template = new Template();
		template.setName("Broken_Promise_Template");
		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template));
		mainHeaders.add(setMainHeader("Manual Collector Changed Report", Type.LABEL, 2, Alignment.CENTER, 115, false, false, template));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.RIGHT, 15, false, false, template));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.RIGHT, 3, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template));
		mainHeaders.add(setMainHeader("", Type.LABEL, 6, Alignment.CENTER, 0, false, true, template));
		template.setMainHeader(mainHeaders);

		List<TableHeader>headers = new ArrayList<>();

		headers.add(setHeaders("S.No",template));
		headers.add(setHeaders("Contract / Card Id",template));
		headers.add(setHeaders("Collector Id",template));
		headers.add(setHeaders("Class",template));
		headers.add(setHeaders("Priority",template));
		headers.add(setHeaders("Promised Amount",template));
		headers.add(setHeaders("Promised Date",template));
		headers.add(setHeaders("Paid Amount",template));

		template.setTableHeaders(headers);
		List<Key> keys = new ArrayList<>();

		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("COLLECTOR_ID",Alignment.LEFT,25,template));
		keys.add(setKey("CLASS_ID",Alignment.LEFT,15,template));
		keys.add(setKey("PRIORITY",Alignment.LEFT,15,template));
		keys.add(setKey("PTP_TOTAL_AMOUNT",Alignment.LEFT,25,template));
		keys.add(setKey("NEXT_PTP_DATE",Alignment.LEFT,20,template));
		keys.add(setKey("PTP_AMOUNT_PAID",Alignment.LEFT,20,template));

		template.setTableKeys(keys);

		List<PageFooter> pageFooters = new ArrayList<>();
		pageFooters.add(setPageFooter(null, Type.LABEL, 0, Alignment.LEFT, 1, false,true,template));
		template.setPageFooter(pageFooters);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.CENTER, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.CENTER, 0, true, false, template));
		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/unworked_accounts_template")
	public void unworked_accounts_template() {

		Template template = new Template();
		template.setName("Unworked_Template");

		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template,false));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template,false));
		mainHeaders.add(setMainHeader("Unworked Accounts Report", Type.LABEL, 2, Alignment.CENTER, 117, false, false, template,false));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.LEFT, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.LEFT, 10, false, false, template,false));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template,false));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template,true));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template,false));
		template.setMainHeader(mainHeaders);


		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template,6));
		headers.add(setHeaders("Contract/Card Id",template,20));
		headers.add(setHeaders("Name",template,10));
		headers.add(setHeaders("Class",template,10));
		headers.add(setHeaders("Unworked Days",template,15));
		headers.add(setHeaders("Priority",template,10));
		headers.add(setHeaders("Date in to Collections",template,24));
		headers.add(setHeaders("Delinquncy Cycle",template,18));
		headers.add(setHeaders("Total Amount Due",template,18));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CONTRACT_CARD_ID",Alignment.LEFT,20,template));
		keys.add(setKey("CUSTOMER_NAME",Alignment.LEFT,10,template));
		keys.add(setKey("CLASS_ID",Alignment.LEFT,10,template));
		keys.add(setKey("UNWORKED_ACCOUNT_DAYS",Alignment.LEFT,15,template));
		keys.add(setKey("PRIORITY",Alignment.LEFT,10,template));
		keys.add(setKey("COLLECTION_START_DATE",Alignment.LEFT,24,template));
		keys.add(setKey("DELINQUENT_DUE",Alignment.LEFT,18,template));
		keys.add(setKey("TOTAL_AMOUNT_DUE",Alignment.RIGHT,18,template));
		template.setTableKeys(keys);

		List<MainFooter> footer= new ArrayList<>();
		footer.add(setMainFooter("", Type.LABEL, 1, Alignment.LEFT, 0, false, true, template));
		footer.add(setMainFooter("", Type.LABEL, 2, Alignment.LEFT, 0, true, false, template));
		footer.add(setMainFooter("", Type.LABEL, 3, Alignment.LEFT, 0, false, true, template));

		template.setMainFooter(footer);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}
	
	@RequestMapping("/classwise_accounts_summary_template")
	public void classwise_account_summary_template() {

		Template template = new Template();
		template.setName("Classwise_Account_Summary_Template");

		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template,false));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template,false));
		mainHeaders.add(setMainHeader("Classwise Account Summary Report", Type.LABEL, 2, Alignment.CENTER, 117, false, false, template,false));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.LEFT, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.LEFT, 10, false, false, template,false));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template,false));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template,true));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template,false));
		template.setMainHeader(mainHeaders);


		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template,6));
		headers.add(setHeaders("Class",template,20));
		headers.add(setHeaders("Number of Accounts",template,20));
		headers.add(setHeaders("Total Due Amount",template,20));
		headers.add(setHeaders("Current Due Amount",template,20));
		headers.add(setHeaders("PTP Amount",template,20));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CLASS_ID",Alignment.LEFT,20,template));
		keys.add(setKey("NUMBER_OF_ACCOUNTS",Alignment.RIGHT,20,template));
		keys.add(setKey("TOTAL_DUE_AMOUNT",Alignment.RIGHT,20,template));
		keys.add(setKey("CURRENT_DUE_AMOUNT",Alignment.RIGHT,20,template));
		keys.add(setKey("PTP_AMOUNT",Alignment.RIGHT,20,template));
		template.setTableKeys(keys);

		List<PageFooter> pageFooters=new ArrayList<>();
		pageFooters.add(setPageFooter("", Type.LABEL, 0, Alignment.LEFT, 1, false, true, template));
		pageFooters.add(setPageFooter("Total", Type.LABEL,46, Alignment.LEFT, 2, false, false, template));
		pageFooters.add(setPageFooter("NUMBER_OF_ACCOUNTS_TOTAL", Type.VALUE,20, Alignment.RIGHT, 2, false, false, template));
		pageFooters.add(setPageFooter("TOTAL_DUE_AMOUNT_TOTAL", Type.VALUE,20, Alignment.RIGHT, 2, false, false, template));
		pageFooters.add(setPageFooter("CURRENT_DUE_AMOUNT_TOTAL", Type.VALUE,20, Alignment.RIGHT, 2, false, false, template));
		pageFooters.add(setPageFooter("PTP_AMOUNT_TOTAL", Type.VALUE,20, Alignment.RIGHT, 2, false, false, template));
		pageFooters.add(setPageFooter("", Type.LABEL, 0, Alignment.LEFT,3, false, true, template));
		pageFooters.add(setPageFooter("", Type.LABEL, 0, Alignment.LEFT, 4, true, false, template));
		template.setPageFooter(pageFooters);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}

	@RequestMapping("/ptp_summary_template")
	public void ptp_summary_template() {

		Template template = new Template();
		template.setName("PTP_Summary_Template");

		List<MainHeader> mainHeaders = new ArrayList<>();
		mainHeaders.add(setMainHeader("title", Type.VALUE, 1, Alignment.CENTER, 115, false, false, template,false));
		mainHeaders.add(setMainHeader("Date :", Type.LABEL, 1, Alignment.CENTER, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("date", Type.DATE, 1, Alignment.RIGHT, 11, false, false, template,false));
		mainHeaders.add(setMainHeader("Promise To Pay Summary Report", Type.LABEL, 2, Alignment.CENTER, 117, false, false, template,false));
		mainHeaders.add(setMainHeader("Page :", Type.LABEL, 2, Alignment.LEFT, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("page", Type.PAGE, 2, Alignment.LEFT, 10, false, false, template,false));
		mainHeaders.add(setMainHeader("", Type.LABEL, 3, Alignment.CENTER, 0, true, false, template,false));
		mainHeaders.add(setMainHeader("Company Name : ", Type.LABEL, 4, Alignment.LEFT, 0, false, false, template,false));
		mainHeaders.add(setMainHeader("COMPANY_CODE", Type.VALUE, 4, Alignment.LEFT, 117, false, false, template,true));
		mainHeaders.add(setMainHeader("", Type.LABEL, 5, Alignment.CENTER, 0, true, false, template,false));
		template.setMainHeader(mainHeaders);


		List<TableHeader>headers = new ArrayList<>();
		headers.add(setHeaders("S.No",template,6));
		headers.add(setHeaders("Class",template,20));
		headers.add(setHeaders("Number of Accounts",template,20));
		headers.add(setHeaders("PTP Amount",template,20));

		template.setTableHeaders(headers);

		List<Key> keys = new ArrayList<>();
		keys.add(setKey("CLASS_ID",Alignment.LEFT,20,template));
		keys.add(setKey("NO_OF_ACCOUNTS",Alignment.RIGHT,20,template));
		keys.add(setKey("PTP_AMOUNT",Alignment.RIGHT,20,template));
		template.setTableKeys(keys);

		template.setDateFormat("MMM-dd-yyyy");
		template.setPageLines(80);
		template.setMaxWidth(133);
		templateDao.save(template);
	}
	
	
	TableHeader setHeaders(String name,Template template,int width){
		TableHeader header=new TableHeader();
		header.setValue(name);
		header.setWidth(width);
		header.setTemplate(template);
		return header;
	}

	MainHeader setMainHeader(String name,Type type,int row,Alignment alignment,int width,boolean dashed,boolean newLine,Template template,boolean isCompany){
		MainHeader mainHeader=new MainHeader();
		mainHeader.setName(name);
		mainHeader.setType(type.toString());
		mainHeader.setRow(row);
		mainHeader.setAlignment(alignment.toString());
		mainHeader.setWidth(width);
		mainHeader.setDashed(dashed);
		mainHeader.setNewLine(newLine);
		mainHeader.setTemplate(template);
		mainHeader.setCompany(isCompany);
		return mainHeader;

	}


	TableHeader setHeaders(String name,Template template){
		TableHeader header=new TableHeader();
		header.setValue(name);
		header.setWidth(name.length()+2);
		header.setTemplate(template);
		return header;
	}
	
	Key setKey(String value,Alignment alignment,int width,Template template){
		Key key =  new Key();
		key.setValue(value);
		key.setAlignment(alignment.toString());
		key.setTemplate(template);
		key.setWidth(width);
		return key;
	}
	
	MainHeader setMainHeader(String name,Type type,int row,Alignment alignment,int width,boolean dashed,boolean newLine,Template template){
		MainHeader mainHeader=new MainHeader();
		mainHeader.setName(name);
		mainHeader.setType(type.toString());
		mainHeader.setRow(row);
		mainHeader.setAlignment(alignment.toString());
		mainHeader.setWidth(width);
		mainHeader.setDashed(dashed);
		mainHeader.setNewLine(newLine);
		mainHeader.setTemplate(template);
		return mainHeader;
		
	}
	
	MainFooter setMainFooter(String name,Type type,int row,Alignment alignment,int width,boolean dashed,boolean newLine,Template template){
		MainFooter mainFooter=new MainFooter();
		mainFooter.setName(name);
		mainFooter.setType(type.toString());
		mainFooter.setRow(row);
		mainFooter.setAlignment(alignment.toString());
		mainFooter.setWidth(width);
		mainFooter.setDashed(dashed);
		mainFooter.setNewLine(newLine);
		mainFooter.setTemplate(template);
		return mainFooter;
		
	}

	PageFooter setPageFooter(String name, Type type, int width, Alignment alignment, int row, boolean isDashed, boolean isNewLine, Template template){
		PageFooter pageFooter = new PageFooter();
		pageFooter.setType(type.toString());
		pageFooter.setWidth(width);
		pageFooter.setAlignment(alignment.toString());
		pageFooter.setRow(row);
		pageFooter.setDashed(isDashed);
		pageFooter.setNewLine(isNewLine);
		pageFooter.setTemplate(template);
		return pageFooter;
	}
}
