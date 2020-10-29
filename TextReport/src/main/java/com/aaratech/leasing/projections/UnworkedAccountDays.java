package com.aaratech.leasing.projections;

import java.time.LocalDate;

public interface UnworkedAccountDays {
	public String getCOMPANY_CODE();
	
	public String getCONTRACT_CARD_ID();
	
	public String getCUSTOMER_NAME();
	
	public String getCLASS_ID();
	
	public Integer getUNWORKED_ACCOUNT_DAYS();
	
	public Integer getPRIORITY();
	
	public LocalDate getCOLLECTION_START_DATE();
	
	public Integer getDELINQUENT_DUE();
	
	public Integer getTOTAL_AMOUNT_DUE();
	
	public LocalDate getBUSINESS_DATE(); 
	
}
