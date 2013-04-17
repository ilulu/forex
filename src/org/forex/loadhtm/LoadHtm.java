package org.forex.loadhtm;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.forex.common.AppUtil;
import org.forex.domain.AccountBaseInfo;
import org.forex.domain.AccountTransactionsInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 解析html
 * 
 * @author ilulu
 * 
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Component
public class LoadHtm {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询文件名称
	 * 
	 * @return
	 * @throws Exception
	 */
	private Object[] getFileNames() throws Exception {
		String sql = "select FILE_PATH_ from accountinfo where ISABLE_=1";
		List filepath = this.jdbcTemplate.queryForList(sql);
		Iterator ite = filepath.iterator();
		List<String> files = new ArrayList<String>();
		while (ite.hasNext()) {
			Map record = (Map) ite.next();
			files.add((String) record.get("FILE_PATH_"));
		}
		return files.toArray();
	}

	public boolean readHtm() throws IOException, ParseException, Exception {
		Object[] files = getFileNames();
		boolean returnValue=false;
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i] + "/" + AppUtil.getHtmName() + ".htm";
			AppUtil.renameFileInWindows(fileName, AppUtil.getHtmName() + ".html");
			File htm = new File(fileName + "l");
			System.out.println("\t read the file" + htm);
			if (htm.exists()) {
				Document doc = Jsoup.parse(htm, "UTF-8", "");
				readTable(doc);
			}
			returnValue=AppUtil.delete(fileName + "l");
			System.out.println("\t read over file" + htm);
			
		}
		return returnValue;
	}

	/**
	 * 每次读取数据之前,需要首先把以前的记录删除调
	 * 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void clearBaseInfo(String account) throws Exception {
		this.jdbcTemplate
				.execute("delete from accountbaseinfo where ACCOUNT_='"
						+ account + "'");
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void clearTransactionInfo(String account) throws Exception {
		this.jdbcTemplate
				.execute("delete from accounttransactionsinfo where ACCOUNT_ID_='"
						+ account + "'");
	}

	/**
	 * 分解读取数据
	 * 
	 * @param doc
	 * @throws ParseException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void readTable(Document doc) throws ParseException, Exception {
		if (doc != null) {
			Elements tables = doc.getElementsByTag("table");
			AccountBaseInfo baseInfo = new AccountBaseInfo();
			baseInfo.setId(UUID.randomUUID().toString());
			for (Element table : tables) {
				// load all html file 读取HTML
				Elements tableRows = table.getElementsByTag("tr");
				int totalRow = tableRows.size();

				// frist row is the base infomation第一行为基本数据
				readTableHead(tableRows.get(0), baseInfo);
				// from 4th row to last 29th is grid 从第四行开始到倒数29行为表格数据
				//2013.01.29 由于部分Htm数据源不一致.所以去掉行数判断改为判断第二列是否为时间,否则认为表格已经中断
				second: for (int i = 3; i < tableRows.size(); i++) {
					AccountTransactionsInfo transactions = new AccountTransactionsInfo();
					transactions.setAccountId(baseInfo.getAccount());
					if (readGrid(tableRows.get(i), transactions)) {
						break second;
					}
				}
				// 倒数17tr为Deposit汇总信息倒数18tr为Closed Trade P/L
				// saveSummaryInfo(tableRows.get(totalRow - 17),
				// tableRows.get(totalRow - 16), baseInfo);
				saveBaseInfo(baseInfo);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void saveBaseInfo(AccountBaseInfo baseInfo) throws Exception {
		jdbcTemplate
				.update("insert into accountbaseinfo(ID_,Account_,DEPOSIT_,CLOSETRADE_,CURRENCY_,NAME_) values (?,?,?,?,?,?)",
						baseInfo.getId(), baseInfo.getAccount(),
						baseInfo.getDeposit(), baseInfo.getCloseTrade(),
						baseInfo.getCurrency(), baseInfo.getName());
	}

	/**
	 * 表格的倒数第17行为汇总的deposit,表格的倒数第16行为汇总的closedtrade 保存汇总的Deposit和Closed Trade
	 * P/L
	 * 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void saveSummaryInfo(Element deposits, Element closedtrades,
			AccountBaseInfo baseInfo) throws Exception {
		try {
			Elements detds = deposits.getElementsByTag("td");
			String deposit = detds.get(1).text();
			if (StringUtils.isNotEmpty(deposit)) {
				baseInfo.setDeposit(Float.parseFloat(deposit.replace(" ", "")));
			}
			Elements cttds = closedtrades.getElementsByTag("td");
			String closedtrade = cttds.get(1).text();
			if (StringUtils.isNotEmpty(closedtrade)) {
				baseInfo.setCloseTrade(Float.parseFloat(closedtrade.replace(
						" ", "")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 处理头部数据
	 * 
	 * @param table
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void readTableHead(Element tableRow, AccountBaseInfo baseInfo)
			throws Exception {
		Elements tds = tableRow.getElementsByTag("td");
		String accountinfo = tds.get(0).text();
		if (StringUtils.isNotEmpty(accountinfo)) {
			baseInfo.setAccount((accountinfo.split(":")[1]).trim());
		}
		String nameInfo = tds.get(1).text();
		if (StringUtils.isNotEmpty(nameInfo)) {
			baseInfo.setName((nameInfo.split(":")[1]).trim());
		}
		String currencyInfo = tds.get(2).text();
		if (StringUtils.isNotEmpty(currencyInfo)) {
			baseInfo.setCurrency(currencyInfo.split(":")[1].trim());
		}

		// 清理数据
		clearBaseInfo(baseInfo.getAccount());
		clearTransactionInfo(baseInfo.getAccount());
	}

	/**
	 * 
	 * @param tableRow
	 * @param transactions
	 * @return 读取表格时,当第一次碰到表格的第二列内容不为时间格式时,表格解析完毕 返回TRUE
	 * @throws ParseException
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private boolean readGrid(Element tableRow,
			AccountTransactionsInfo transactions) throws ParseException,
			Exception {
		Elements tds = tableRow.getElementsByTag("td");
		String ticket = tds.get(0).text();
		if (StringUtils.isNotEmpty(ticket)) {
			transactions.setTicket(ticket);
		}
		String openTime = tds.get(1).text();
		if (StringUtils.isNotEmpty(openTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
			try {
				Date oTime = sdf.parse(openTime);
				transactions.setOpenTime(oTime);
			} catch (ParseException e) {
				return true;
			}

		}
		String type = tds.get(2).text();
		if (StringUtils.isNotEmpty(type)) {
			transactions.setType(type.trim());
		}
		String size = tds.get(3).text();
		if (StringUtils.isNotEmpty(size)) {
			transactions.setSize(size.trim());
		}
		String commission=tds.get(tds.size()-4).text();
		if(StringUtils.isNotEmpty(commission)){
			commission = commission.replace(" ", "");
			try {
				if(AppUtil.isNum(commission)){
					transactions.setCommission(Double.parseDouble(commission));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		String swap=tds.get(tds.size()-2).text();
		if(StringUtils.isNotEmpty(swap)){
			swap = swap.replace(" ", "");
			try {
				if(AppUtil.isNum(swap))
				transactions.setSwap(Double.parseDouble(swap));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		String profit = tds.get(tds.size() - 1).text();
		if (StringUtils.isNotEmpty(profit)) {
			profit = profit.replace(" ", "");
			if (profit.equals("cancelled")) {
				profit = "0";
			}
			try {
				transactions.setProfit(Double.parseDouble(profit));
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		jdbcTemplate
				.update("INSERT INTO accounttransactionsinfo(ID_,ACCOUNT_ID_,TICKET_,OPENTIME_,TYPE_,SIZE_,COMMISSION_,SWAP_,PROFIT_) VALUES(?,?,?,?,?,?,?,?,?) ",
						UUID.randomUUID().toString(),
						transactions.getAccountId(), transactions.getTicket(),
						transactions.getOpenTime(), transactions.getType(),
						transactions.getSize(),transactions.getCommission(),transactions.getSwap(),transactions.getProfit());

		return false;

	}

}
