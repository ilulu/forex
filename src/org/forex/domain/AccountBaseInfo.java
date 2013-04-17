package org.forex.domain;

import java.util.Date;

/**
 * htm基础汇总信息
 * 
 * @author ilulu
 * 
 */
public class AccountBaseInfo {
	private String id;
	private String account;
	private String name;
	private String currency;
	private Date currentDate;
	private double deposit;
	private double closeTrade;
	
	private String currentMonth;
	private String currentYearToDate;
	private String sinceStart;
	private String annualisedReturn;
	private String totalReturn;
	private String avgMonthlyReturn;
	private String  startOfRealPerformance;
	private String initialCaptial;
	private String productName;
	
	private String outLinkUrl;
	
	private String minimumInvestment;
	

	public String getMinimumInvestment() {
		return minimumInvestment;
	}

	public void setMinimumInvestment(String minimumInvestment) {
		this.minimumInvestment = minimumInvestment;
	}

	public String getOutLinkUrl() {
		return outLinkUrl;
	}

	public void setOutLinkUrl(String outLinkUrl) {
		this.outLinkUrl = outLinkUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getCurrentYearToDate() {
		return currentYearToDate;
	}

	public void setCurrentYearToDate(String currentYearToDate) {
		this.currentYearToDate = currentYearToDate;
	}

	public String getSinceStart() {
		return sinceStart;
	}

	public void setSinceStart(String sinceStart) {
		this.sinceStart = sinceStart;
	}

	public String getAnnualisedReturn() {
		return annualisedReturn;
	}

	public void setAnnualisedReturn(String annualisedReturn) {
		this.annualisedReturn = annualisedReturn;
	}

	public String getTotalReturn() {
		return totalReturn;
	}

	public void setTotalReturn(String totalReturn) {
		this.totalReturn = totalReturn;
	}

	public String getAvgMonthlyReturn() {
		return avgMonthlyReturn;
	}

	public void setAvgMonthlyReturn(String avgMonthlyReturn) {
		this.avgMonthlyReturn = avgMonthlyReturn;
	}

	public String getStartOfRealPerformance() {
		return startOfRealPerformance;
	}

	public void setStartOfRealPerformance(String startOfRealPerformance) {
		this.startOfRealPerformance = startOfRealPerformance;
	}

	public String getInitialCaptial() {
		return initialCaptial;
	}

	public void setInitialCaptial(String initialCaptial) {
		this.initialCaptial = initialCaptial;
	}

	public AccountBaseInfo() {

	}

	public AccountBaseInfo(String id,String account,String name,String currency,Date currentDate,float deposti,float closeTrade) {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getCloseTrade() {
		return closeTrade;
	}

	public void setCloseTrade(double closeTrade) {
		this.closeTrade = closeTrade;
	}

}
