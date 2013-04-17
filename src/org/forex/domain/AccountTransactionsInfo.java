package org.forex.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ���׼�¼��ϸ��Ϣ
 * @author ilulu
 *
 */
public class AccountTransactionsInfo {
	private String id;
	private String accountId;
	private String ticket;
	private Date openTime;
	private String type;
	private String size;
	private String item;
	private double openPrice;
	private double sL;
	private double tP;
	private Date cloasTime;
	private double closePrice;
	private double commission;
	private double taxes;
	private double swap;
	private double profit;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getsL() {
		return sL;
	}
	public void setsL(double sL) {
		this.sL = sL;
	}
	public double gettP() {
		return tP;
	}
	public void settP(double tP) {
		this.tP = tP;
	}
	public Date getCloasTime() {
		return cloasTime;
	}
	public void setCloasTime(Date cloasTime) {
		this.cloasTime = cloasTime;
	}
	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getSwap() {
		return swap;
	}
	public void setSwap(double swap) {
		this.swap = swap;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	
}
