package org.forex.domain;

public class AccountInfo {
	private String id;
	private String productName;
	private String filePath;
	private int order;
	private String account;
	private int isable;
	private String balanceSize;
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
	public String getBalanceSize() {
		return balanceSize;
	}
	public void setBalanceSize(String balanceSize) {
		this.balanceSize = balanceSize;
	}
	public int getIsable() {
		return isable;
	}
	public void setIsable(int isable) {
		this.isable = isable;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
