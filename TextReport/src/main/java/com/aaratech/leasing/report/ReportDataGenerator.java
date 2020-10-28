package com.aaratech.leasing.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import com.aaratech.leasing.projections.ClassWiseAccountSummary;
import com.aaratech.leasing.report.dataentity.UnworkedAcctDays;
import com.aaratech.leasing.report.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReportDataGenerator {

	@Autowired
	ReportGeneratorService reportService;

	@Autowired
	CollAccountDetailsRepo accountDetailsRepo;

	@Autowired
	ClassMoveRepo classMoveRepo;

	@Autowired
	DailyCollActionRepo collActionRepo;

	@Autowired
	DailyTransactionRepo transactionRepo;

	@Autowired
	CollExceptionRepo exceptionRepo;

	@Autowired
	AccountDetailsPurgeRepo detailsPurgeRepo;

	@Autowired
	PTPPartialRepo ptpPartialRepo;

	@Autowired
	DateMasterRepo dateMasterRepo;

	@Autowired
	UnworkedAccountDaysRepo unworkedAccountDaysRepo;
			;

	private Date getDateByCompanyCode(String companyCode){
		return Optional.ofNullable(dateMasterRepo.findByCompanyCode(companyCode))
				.map(dateMaster -> dateMaster.getBusinessDate())
				.orElse(new Date());
	}


	@GetMapping("/classreassignment")
	public List<CollAccountDetails> getDetails() throws NoSuchFieldException, SecurityException {
		
		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Class_Reassignment_Template");
		mainDataMap.put("title", "Aaratech Leasing System"); 
		mainDataMap.put("total", 0);
		List<HashMap<String, String>> detailsList = new ArrayList<HashMap<String, String>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+classMoveRepo.getCollClassMoves().size());
		classMoveRepo.getCollClassMoves().forEach(classMove->{
			HashMap<String, String> dataMap= new HashMap<String, String>();
			dataMap.put("CONTRACT_CARD_ID", classMove.getCONTRACT_CARD_ID());
			dataMap.put("CUSTOMER_NAME", classMove.getCUSTOMER_NAME());
			dataMap.put("DATE_MOVED", formatDate(classMove.getDATE_MOVED()));
			dataMap.put("MOVED_BY", classMove.getMOVED_BY());
			dataMap.put("NEW_CLASS", classMove.getNEW_CLASS());
			dataMap.put("OLD_CLASS", classMove.getOLD_CLASS());
			dataMap.put("COMPANY_CODE", classMove.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			System.out.println("Company Code >>> "+classMove.getCOMPANY_CODE()+ "classMove.getMOVED_BY() "
					+ classMove.getMOVED_BY() + " classMove.getNEW_CLASS()"+ classMove.getNEW_CLASS()
			+" classMove.getOLD_CLASS()"+ classMove.getOLD_CLASS() + " classMove.getCOMPANY_CODE()"+ classMove.getCOMPANY_CODE());
			detailsList.add(dataMap);
		});
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@GetMapping("/dailycollectoractionreports")
	public List<CollAccountDetails> getDailycollactionReport() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Daily_Collector_Action_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		mainDataMap.put("total", 0);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+collActionRepo.getDailyCollAction().size());
		collActionRepo.getDailyCollAction().forEach(collRep->{
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();

			dataMap.put("ACTION_COLLECTOR_ID", collRep.getACTION_COLLECTOR_ID());
			dataMap.put("ACTION_CODE", collRep.getACTION_CODE());
			dataMap.put("ACTION_DATE_AND_TIME", collRep.getACTION_DATE_AND_TIME());
			dataMap.put("CONTRACT_CARD_ID", collRep.getCONTRACT_CARD_ID());
			dataMap.put("ACTION_DATE", formatDate(collRep.getACTION_DATE()));
			dataMap.put("ACTION_TIME", removeZeroTime(collRep.getACTION_TIME()));
			dataMap.put("COMPANY_CODE", collRep.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			System.out.println("Company Code >>> "+collRep.getCOMPANY_CODE());
			detailsList.add(dataMap);
		});
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	String removeZeroTime(Date dt){
		return Optional.ofNullable(dt).map(dt1 -> dt1.toString())
				.filter(dt1 -> !dt1.equalsIgnoreCase("00:00:00"))
				.orElse("");
	}
	@GetMapping("/dailyTransactionReport")
	public List<CollAccountDetails> getDailyTransactionReport() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Daily_Transactions_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		mainDataMap.put("total", 0);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+transactionRepo.getDailyCollAction().size());
		transactionRepo.getDailyCollAction().forEach(collRep->{
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();

			dataMap.put("CONTRACT_ID", collRep.getCONTRACT_ID());
			dataMap.put("CUSTOMER_NAME", collRep.getCUSTOMER_NAME());
			dataMap.put("POSTING_DATE", formatDate(collRep.getPOSTING_DATE()));
			dataMap.put("TXN_DESCRIPTION", collRep.getTXN_DESCRIPTION());
			dataMap.put("TXN_TYPE", collRep.getTXN_TYPE());
			dataMap.put("TXN_AMOUNT", collRep.getTXN_AMOUNT());
			dataMap.put("COMPANY_CODE", collRep.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}


	@GetMapping("/rejectedAccountsReport")
	public List<CollAccountDetails> getRejectedAccountsReport() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Rejected_Accounts_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		mainDataMap.put("total", 0);
		List<HashMap<String, String>> detailsList = new ArrayList<HashMap<String, String>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+exceptionRepo.getRejectedRecordsList().size());
		exceptionRepo.getRejectedRecordsList().forEach(collRep->{
			HashMap<String, String> dataMap= new HashMap<String, String>();

			dataMap.put("CONTRACT_CARD_ID", collRep.getCONTRACT_CARD_ID());
			dataMap.put("CUSTOMER_ID", collRep.getCUSTOMER_ID());
			dataMap.put("REASON_CODE", collRep.getREASON_CODE());
			dataMap.put("PROCESS_DATE", formatDate(collRep.getPROCESS_DATE()));
			dataMap.put("REJECT_REASON", collRep.getREJECT_REASON());
			dataMap.put("COMPANY_CODE", collRep.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));

			detailsList.add(dataMap);
		});
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0){
		System.out.println("mainDataMap >>> "+mainDataMap);
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
			String json = objectMapper.writeValueAsString(mainDataMap);
			System.out.println("json >>>> "+json);
			reportService.generateReport(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		}

		return null;
	}

	@GetMapping("/overridePTPReport")
	public List<CollAccountDetails> getOverridePTPReport() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Override_PTP_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		mainDataMap.put("total", 0);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+collActionRepo.getOverridePTPReport().size());
		collActionRepo.getOverridePTPReport().forEach(collRep->{
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();

			dataMap.put("CONTRACT_CARD_ID", collRep.getCONTRACT_CARD_ID());
			dataMap.put("CUSTOMER_NAME", collRep.getCUSTOMER_NAME());
			dataMap.put("PTP_AMOUNT", collRep.getPTP_AMOUNT());
			dataMap.put("ACTION_CODE", collRep.getACTION_CODE());
			dataMap.put("ACTION_COLLECTOR_ID", collRep.getACTION_COLLECTOR_ID());
			dataMap.put("ACTION_DATE_AND_TIME", formatDate(collRep.getACTION_DATE_AND_TIME()));
			dataMap.put("COMPANY_CODE", collRep.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));

			detailsList.add(dataMap);
		});
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0){
			System.out.println("mainDataMap >>> "+mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> "+json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	@GetMapping("/classwiseaccounts")
	public List<CollAccountDetails> getClassReassignmentReport() throws NoSuchFieldException, SecurityException {
		
		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Class1_template");
		mainDataMap.put("title", "Aaratech Leasing System");
		Date reportDate = getDateByCompanyCode("BAY");
		AtomicReference<Double> doubleval= new AtomicReference<>(0d);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		System.out.println("Size of list "+accountDetailsRepo.findByContractStatusOrderByClassIdAsc("A").size());
		accountDetailsRepo.findByContractStatusOrderByClassIdAsc("A").forEach(acc -> {
			System.out.println(acc.getCustomerName()+"     "+acc.getAccountNumber()+"   "+acc.getClassId() + "   "+  acc.getCollectionStartDate());
			doubleval.updateAndGet(v -> v + acc.getTotalAmountDue());
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();
			dataMap.put("CONTRACT_CARD_ID", acc.getContractCardId());
			dataMap.put("CUSTOMER_NAME", acc.getCustomerName());
			dataMap.put("LEASE_TYPE", acc.getLeaseType());
			dataMap.put("REASON_CODE", acc.getReasonCode());
			dataMap.put("PRIORITY", acc.getPriority());
			dataMap.put("COLLECTION_START_DATE", formatDate(acc.getCollectionStartDate()));
			dataMap.put("DELINQUENT_DUE", acc.getDelinquentDue());
			dataMap.put("TOTAL_AMOUNT_DUE", acc.getTotalAmountDue());
			dataMap.put("COMPANY_CODE", acc.getCompanyCode());
			dataMap.put("CLASS_ID", acc.getClassId());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});
		mainDataMap.put("total", doubleval);
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@GetMapping("/purgedAccountsReport")
	public List<CollAccountDetails> getpurgedAccountsReport() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Purged_Accounts_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		BigDecimal decimal=new BigDecimal(0);
		List<HashMap<String, String>> detailsList = new ArrayList<HashMap<String, String>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+detailsPurgeRepo.getAccountDetailsPurge().size());
		detailsPurgeRepo.getAccountDetailsPurge().forEach(acc -> {
			HashMap<String, String> dataMap= new HashMap<String, String>();

			dataMap.put("CONTRACT_CARD_ID", acc.getCONTRACT_CARD_ID());
			dataMap.put("CUSTOMER_NAME", acc.getCUSTOMER_NAME());
			dataMap.put("COLLECTION_START_DATE", formatDate(acc.getCOLLECTION_START_DATE()));
			dataMap.put("COLLECTION_END_DATE", formatDate(acc.getCOLLECTION_END_DATE()));
			dataMap.put("PURGE_DATE", formatDate(acc.getPURGE_DATE()));
			dataMap.put("COMPANY_CODE", acc.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});

		mainDataMap.put("total", decimal);
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@GetMapping("/PTPPartialReport")
	public List<CollAccountDetails> getPTPPartialReport() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "PTP_Partial_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		BigDecimal decimal=new BigDecimal(0);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+ptpPartialRepo.getPTPPartialData().size());
		ptpPartialRepo.getPTPPartialData().forEach(acc -> {
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();

			dataMap.put("CONTRACT_CARD_ID", acc.getCONTRACT_CARD_ID());
			dataMap.put("CUSTOMER_NAME", acc.getCUSTOMER_NAME());
			dataMap.put("PTP_TOTAL_AMOUNT", acc.getPTP_TOTAL_AMOUNT());
			dataMap.put("NEXT_PTP_DATE", formatDate(acc.getNEXT_PTP_DATE()));
			dataMap.put("PTP_AMOUNT_PAID", acc.getPTP_AMOUNT_PAID());
			dataMap.put("REMAINING_AMOUNT", acc.getREMAINING_AMOUNT());
			dataMap.put("COMPANY_CODE", acc.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});

		mainDataMap.put("total", decimal);
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@GetMapping("/manual_collector_changed_Report")
	public List<CollAccountDetails> getManual_Collector_changed_Report() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Manual_Collector_Changed_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		BigDecimal decimal=new BigDecimal(0);
		List<HashMap<String, String>> detailsList = new ArrayList<HashMap<String, String>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+collActionRepo.getManualCollectorChanged().size());
		collActionRepo.getManualCollectorChanged().forEach(acc -> {
			HashMap<String, String> dataMap= new HashMap<String, String>();

			dataMap.put("CONTRACT_CARD_ID", acc.getCONTRACT_CARD_ID());
			dataMap.put("CUSTOMER_NAME", acc.getCUSTOMER_NAME());
			dataMap.put("ACTION_COLLECTOR_ID", acc.getACTION_COLLECTOR_ID());
			dataMap.put("COLLECTOR_ID", acc.getCOLLECTOR_ID());
			dataMap.put("ACTION_DATE_AND_TIME", formatDate(acc.getACTION_DATE_AND_TIME()));
			dataMap.put("COMPANY_CODE", acc.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});

		mainDataMap.put("total", decimal);
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@GetMapping("/broken_promise_Report")
	public List<CollAccountDetails> getBroken_Promise_Report() throws NoSuchFieldException, SecurityException {

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Broken_Promise_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		BigDecimal decimal=new BigDecimal(0);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		Date reportDate = getDateByCompanyCode("BAY");
		System.out.println("Size of list "+collActionRepo.getManualCollectorChanged().size());
		collActionRepo.getManualCollectorChanged().forEach(acc -> {
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();

			dataMap.put("CONTRACT_CARD_ID", acc.getCONTRACT_CARD_ID());
			dataMap.put("COLLECTOR_ID", acc.getCUSTOMER_NAME());
			dataMap.put("CLASS_ID", acc.getACTION_COLLECTOR_ID());
			dataMap.put("PRIORITY", acc.getCOLLECTOR_ID());
			dataMap.put("PTP_TOTAL_AMOUNT", acc.getACTION_DATE_AND_TIME());
			dataMap.put("NEXT_PTP_DATE", formatDate(acc.getACTION_DATE_AND_TIME()));
			dataMap.put("PTP_AMOUNT_PAID", acc.getACTION_DATE_AND_TIME());
			dataMap.put("COMPANY_CODE", acc.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});

		mainDataMap.put("total", decimal);
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@GetMapping("/unworkedAccountsReport")
	public void unworkedAccountsReport(){
		List<UnworkedAcctDays> unworkedAcctDaysList= new ArrayList<>();//unworkedAccountDaysRepo.fetchUnworkedAccntDays();
		List<UnworkedAcctDays> filteredList = new ArrayList<>();
		/*unworkedAcctDaysList.forEach(u->{
			if(!unworkedAccountDaysRepo.checkExistsInActionHistory(u.getContractCardId(), LocalDate.now(), LocalDate.now().minusDays(u.getUnworkedAccountDays())))
				filteredList.add(u);
		});*/

		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Unworked_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		Date reportDate = getDateByCompanyCode("BAY");
		AtomicReference<Double> doubleval= new AtomicReference<>(0d);
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		System.out.println("Size of list "+accountDetailsRepo.findByContractStatusOrderByClassIdAsc("A").size());
		filteredList.forEach(acc -> {
			doubleval.updateAndGet(v -> v + acc.getTotalAmountDue());
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();
			dataMap.put("CONTRACT_CARD_ID", acc.getContractCardId());
			dataMap.put("CUSTOMER_NAME", acc.getCustomerName());
			dataMap.put("CLASS_ID", acc.getClassId());
			dataMap.put("UNWORKED_ACCOUNT_DAYS", acc.getUnworkedAccountDays());
			dataMap.put("PRIORITY", acc.getPriority());
			dataMap.put("COLLECTION_START_DATE", formatDate(java.sql.Date.valueOf(acc.getCollectionStartDate())));
			dataMap.put("DELINQUENT_DUE", acc.getDelinquentDue());
			dataMap.put("TOTAL_AMOUNT_DUE", acc.getTotalAmountDue());
			dataMap.put("COMPANY_CODE", acc.getCompanyCode());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});
		mainDataMap.put("total", doubleval);
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException | NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	@GetMapping("/classWiseAccountSummaryReport")
	public void classWiseAccountSummaryReport(){
		List<ClassWiseAccountSummary> accntSummary=accountDetailsRepo.fetchAccountSummary();
		
		HashMap<String, Object> mainDataMap= new HashMap<String, Object>();
		mainDataMap.put("class", "Classwise_Account_Summary_Template");
		mainDataMap.put("title", "Aaratech Leasing System");
		Date reportDate = getDateByCompanyCode("BAY");
		List<HashMap<String, Comparable>> detailsList = new ArrayList<HashMap<String, Comparable>>();
		accntSummary.forEach(acc -> {
			HashMap<String, Comparable> dataMap= new HashMap<String, Comparable>();
			dataMap.put("NUMBER_OF_ACCOUNTS", acc.getNUMBER_OF_ACCOUNTS()!=null?acc.getNUMBER_OF_ACCOUNTS():0);
			dataMap.put("CURRENT_DUE_AMOUNT", acc.getCURRENT_DUE_AMOUNT()!=null?acc.getCURRENT_DUE_AMOUNT():0);
			dataMap.put("CLASS_ID", acc.getCLASS_ID());
			dataMap.put("TOTAL_DUE_AMOUNT", acc.getTOTAL_DUE_AMOUNT()!=null?acc.getTOTAL_DUE_AMOUNT():0);
			dataMap.put("PTP_AMOUNT", acc.getPTP_AMOUNT()!=null?acc.getPTP_AMOUNT():0);
			dataMap.put("COMPANY_CODE", acc.getCOMPANY_CODE());
			dataMap.put("HEADER_DATE", formatDate(reportDate));
			detailsList.add(dataMap);
		});
		mainDataMap.put("details", detailsList);
		if (detailsList.size()>0) {
			System.out.println("mainDataMap >>> " + mainDataMap);
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				objectMapper.setDateFormat(new SimpleDateFormat("MMM-dd-yyyy"));
				String json = objectMapper.writeValueAsString(mainDataMap);
				System.out.println("json >>>> " + json);
				reportService.generateReport(json);
			} catch (JsonProcessingException | NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}
	
	String formatDate(Date dt){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
		System.out.println("Date: "+dateFormat.format(dt));
		String formattedDate = dateFormat.format(dt);
		String finalDAte = formattedDate.trim().equalsIgnoreCase("Jan-01-1900") ? "" : formattedDate;
		return finalDAte;
	}
}
