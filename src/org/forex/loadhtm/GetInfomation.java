package org.forex.loadhtm;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.forex.common.AppUtil;
import org.forex.domain.AccountBaseInfo;
import org.forex.domain.AccountBaseInfoRowMapper;
import org.forex.domain.AccountInfo;
import org.forex.domain.AccountInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;

/**
 * 从数据库中获得数据各种利润率 利润率的计算公式为 利润率=利润/本金 type为buy或者sell的profit汇总为利润
 * type为balance,size=AddFunds的第一条记录的profit为本金
 * 
 * @author lu
 * 
 */
@Service
public class GetInfomation {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private LoadHtm loadHtm;

	/**
	 * 获取该账户的本金 本金为交易表中,type为balance,size为AddFunds的第一条记录的profit
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	private double getCapital(String account) throws Exception {
		double returnValue = 0d;

		String sqlForCode = "select BALANCE_SIZE_ from accountinfo where ACCOUNT_='"
				+ account + "'";
		List resultForCode = jdbcTemplate.queryForList(sqlForCode);
		if (resultForCode.size() > 0) {
			String balanceSizes=(String)((Map)resultForCode.get(0)).get("BALANCE_SIZE_");
			String[] balanceCodes=balanceSizes.split("\\|");
			String size="";
			for(int i=0;i<balanceCodes.length;i++){
				size+="'"+balanceCodes[i]+"'";
				if(i+1<balanceCodes.length){
					size+=",";
				}
			}
			String sql = "select sum(t1.profit_) as profit_ from ACCOUNTTRANSACTIONSINFO t1 where account_ID_='"
					+ account
					+ "'"
					+ " and t1.type_='balance' and t1.size_ in ("+size+")  order by opentime_,ticket_ limit 1";
			
			List result = jdbcTemplate.queryForList(sql);
			if (result.size() > 0) {
				Map record = (Map) result.get(0);
				returnValue = (Double) record.get("profit_");
			}
		}
		return returnValue;
	}

	/**
	 * 计算汇总页面上CurrentMonth值 本月1好到现在
	 * 
	 * @param account账户ID
	 * @return
	 */
	@Expose
	public String getCurrentMonth(String account) throws Exception {
		// 求当前月的利润率
		// 先取得当前账户的本金
		double deposit = getCapital(account);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 再取得本月利润
		String sqlproift = "SELECT sum(COALESCE(profit_,0)+COALESCE(COMMISSION_,0)+COALESCE(SWAP_,0)) as profit FROM accounttransactionsinfo WHERE TYPE_ in('sell','buy') and account_id_='"
				+ account
				+ "'\n"
				+ "and OPENTIME_ between '"
				+ sdf.format(AppUtil.getFirstDayMonth())
				+ " 00:00:00"
				+ "' and '"
				+ sdf.format(AppUtil.getLastDayMonth())
				+ " 23:59:59" + "'";

		List result = jdbcTemplate.queryForList(sqlproift);
		Iterator ite = result.iterator();
		ite = result.iterator();
		Double profit = 0d;
		while (ite.hasNext()) {
			Map record = (Map) ite.next();
			if (record.get("profit") != null) {
				profit = (Double) record.get("profit");
			}
		}
		String currentMonthRate = "0";
		if (deposit != 0) {
			currentMonthRate = String.valueOf(AppUtil.getRoundUp((profit
					/ deposit * 100), 2))
					+ "%";
		}
		return currentMonthRate;
	}

	/**
	 * 汇总页面上yearToDate值
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getCurrentYearToDate(String account) throws Exception {
		String yearToDate = "0";

		// 先取得当前账户的本金
		double deposit = getCapital(account);
		// 再取得本年到今天的利润
		String sqlproift = "SELECT sum(COALESCE(profit_,0)+COALESCE(COMMISSION_,0)+COALESCE(SWAP_,0)) as profit FROM accounttransactionsinfo WHERE TYPE_ in ('buy','sell') and account_id_=?\n"
				+ "and OPENTIME_ between ? and sysdate()";
		List result = jdbcTemplate.queryForList(sqlproift, account,
				AppUtil.getFirstDayOfYear());
		Iterator ite = result.iterator();
		ite = result.iterator();
		double profit = 0;
		while (ite.hasNext()) {
			Map record = (Map) ite.next();
			if (record.get("profit") != null) {
				profit = (Double) record.get("profit");
			}
		}
		if (deposit != 0) {
			yearToDate = String.valueOf(AppUtil.getRoundUp(profit / deposit
					* 100, 2))
					+ "%";
		}
		return yearToDate;
	}

	/**
	 * 从账户开始交易到现在的所有 since start 是从开户开始时开始计算profit/第一个addfunds
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getSinceStart(String account) throws Exception {
		String sinceStart = "0";

		double deposit = getCapital(account);
		double closeTrade = 0;
		String sql = "select sum(COALESCE(profit_,0)+COALESCE(COMMISSION_,0)+COALESCE(SWAP_,0)) as profit from accounttransactionsinfo where TYPE_ in ('sell','buy') and ACCOUNT_ID_='"
				+ account + "'";
		List result = jdbcTemplate.queryForList(sql);
		if (result.size() > 0) {
			Map record = (Map) result.get(0);
			if (record.get("profit") != null) {
				closeTrade = (Double) record.get("profit");
			}
		}
		if (deposit != 0) {
			sinceStart = String.valueOf(AppUtil.getRoundUp(closeTrade / deposit
					* 100, 2))
					+ "%";
		}
		return sinceStart;
	}

	/**
	 * 获取年化收益 就用current year to date 的收益除以今年过去的时间再乘以365
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getAnnualisedReturn(String account) throws Exception {
		String currentYearToDate = getTotalReutrn(account).replace("%", "");
		double annualisedReturn = (Double.parseDouble(currentYearToDate) / 100)
				/ AppUtil.getLostDayFromIn(getStartOfRealPerformance(account))
				* 365;
		return String.valueOf(AppUtil.getRoundUp(annualisedReturn * 100, 2))
				+ "%";
	}

	/**
	 * 获取totalReturn的值 total return 跟since start的值是一样的
	 */
	@Expose
	public String getTotalReutrn(String account) throws Exception {
		return getSinceStart(account);
	}

	/**
	 * 获取average monthly return值 average monthly return 平均月收益， 就用total
	 * return除以交易的月份 交易的月份就是从账户交易第一单到现在，过去了几个月
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getAverageMonthlyReturn(String account) throws Exception {
		String amr = "";
		String totalReturn = getTotalReutrn(account).replace("%", "");

		String sql = "SELECT * FROM accounttransactionsinfo WHERE account_id_=?\n"
				+ "order by  opentime_,ticket_ asc limit 1";
		List result = jdbcTemplate.queryForList(sql, account);
		Date firstOpenTime = null;
		if (result.size() > 0) {
			Map record = (Map) result.get(0);
			firstOpenTime = (Date) record.get("OPENTIME_");
		}
		if (firstOpenTime != null) {
			amr = String
					.valueOf(AppUtil.getRoundUp(
							(Double.parseDouble(totalReturn) / 100)
									/ AppUtil.getLostMonthFrom(firstOpenTime)
									* 100, 2))
					+ "%";
		}
		return amr;
	}

	/**
	 * 读取账户开始交易的时间,表格第一条记录的时间
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getStartOfRealPerformance(String account) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String realStartTime = sdf.format(new Date());
		String sql = "SELECT opentime_ as opentime FROM ACCOUNTTRANSACTIONSINFO where account_ID_='"
				+ account + "' order by opentime_,ticket_ limit 1";
		List result = jdbcTemplate.queryForList(sql);
		if (result.size() > 0) {
			Map record = (Map) result.get(0);
			Date op = (Date) record.get("opentime");
			realStartTime = sdf.format(op);
		}
		return realStartTime;
	}

	/**
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getInitialCaptial(String account) throws Exception {
		String initial = "0";
		String sql = "SELECT DEPOSIT_,CURRENCY_ FROM ACCOUNTBASEINFO where account_='"
				+ account + "'";
		List result = jdbcTemplate.queryForList(sql);
		if (result.size() > 0) {
			Map record = (Map) result.get(0);
			initial = String.valueOf(getCapital(account));
			initial += "\t" + record.get("CURRENCY_");
		}
		return initial;
	}

	/**
	 * 读取生成报表的数据,以及配套表格的基础数据
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@DataProvider
	public Collection getChartResult(Map param) throws Exception {
		// sql的内容为根据时间的分组,然后分别计算出当月的利润率和从开户到当月截至的利润率
		/******
		 * SQL********* select dm, round(profit/(SELECT profit_ FROM
		 * accounttransactionsinfo WHERE TYPE_='balance' and SIZE_ in
		 * ('AddFunds') order by OPENTIME_,TICKET_ limit 1),4) as profitRate,
		 * round(total/(SELECT profit_ FROM accounttransactionsinfo WHERE
		 * TYPE_='balance' and SIZE_ in ('AddFunds') order by OPENTIME_,TICKET_
		 * limit 1),4) as totalRate FROM ( select
		 * DATE_FORMAT(t1.OPENTIME_,'%Y%m') as dm, round(sum(t1.profit_),4) as
		 * profit , (select round(sum(t2.PROFIT_),4) from
		 * accounttransactionsinfo t2 where
		 * DATE_FORMAT(t2.OPENTIME_,'%Y%m')<=DATE_FORMAT(t1.OPENTIME_,'%Y%m')
		 * and t2.TYPE_ in ('buy','sell')) as total FROM accounttransactionsinfo
		 * t1 where t1.TYPE_ in ('buy','sell') group by
		 * DATE_FORMAT(t1.OPENTIME_,'%Y%m') ) a
		 */
		String account = (String) param.get("account");
		Boolean isRolling = (Boolean) param.get("isRolling");

		String sqlFunds = "select * from accountinfo where 1=1";
		String sqlDespoit = "SELECT sum(profit_) as profit_ FROM accounttransactionsinfo \n"
				+ "WHERE 1=1";
		if (StringUtils.isNotEmpty(account)) {
			sqlDespoit += " and ACCOUNT_ID_='" + account + "'";
			sqlFunds += " and ACCOUNT_='" + account + "'";
		}

		List<AccountInfo> fundList = this.jdbcTemplate.query(sqlFunds,
				new AccountInfoRowMapper());
		String funds = "";
		if (fundList.size() > 0) {
			String[] fund = fundList.get(0).getBalanceSize().split("\\|");
			for(int i=0;i<fund.length;i++){
				funds+="'"+fund[i]+"'";
				if(i+1<fund.length){
					funds+=",";
				}
			}
			
		}

		sqlDespoit += " AND TYPE_='balance' and SIZE_ in (" + funds + ")\n"
				+ "order by OPENTIME_,TICKET_ limit 1";

		String sql = "select \n"
				+ "dm,\n"
				+ "round(profit/("
				+ sqlDespoit
				+ "),4) as profitRate,\n"
				+ "round(total/("
				+ sqlDespoit
				+ "),4) as totalRate\n"
				+ "FROM (\n"
				+ "select DATE_FORMAT(t1.OPENTIME_,'%Y-%m') as dm,\n"
				+ "round(sum(COALESCE(t1.profit_,0)+COALESCE(t1.COMMISSION_,0)+COALESCE(t1.SWAP_,0)),4) as profit ,\n"
				+ "(select round(sum(COALESCE(t2.PROFIT_,0)+COALESCE(t2.COMMISSION_,0)+COALESCE(t2.SWAP_,0)),4) from accounttransactionsinfo t2 where t2.ACCOUNT_ID_='"
				+ account
				+ "' AND DATE_FORMAT(t2.OPENTIME_,'%Y%m')<=DATE_FORMAT(t1.OPENTIME_,'%Y%m') and t2.TYPE_ in ('buy','sell')) as total\n"
				+ "FROM accounttransactionsinfo t1\n"
				+ "where t1.TYPE_ in ('buy','sell') AND t1.ACCOUNT_ID_='"
				+ account + "'\n"
				+ "group by DATE_FORMAT(t1.OPENTIME_,'%Y%m')\n" + ") a";
		if (isRolling != null && isRolling) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			String cudm = String.valueOf(year)
					+ "-"
					+ (month < 10 ? "0" + String.valueOf(month) : String
							.valueOf(month));
			cal.add(Calendar.YEAR, -1);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			String lastdm = String.valueOf(year)
					+ "-"
					+ (month < 10 ? "0" + String.valueOf(month) : String
							.valueOf(month));
			sql += "\n where dm>='" + lastdm + "' and dm<='" + cudm + "'";
		}

		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 计算前X个月的利润率
	 * 
	 * @param Xmonth
	 * @return
	 * @throws Exception
	 */
	@Expose
	public String getLastXMonthRate(Map param) throws Exception {
		int Xmonth = Integer.valueOf(param.get("Xmonth").toString());
		String account = (String) param.get("account");
		Calendar cal = Calendar.getInstance();
		String nowYear = String.valueOf(cal.get(Calendar.YEAR));
		String nowMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
		cal.add(Calendar.MONTH, -Xmonth);
		String targetYear = String.valueOf(cal.get(Calendar.YEAR));
		String targetMonth = cal.get(Calendar.MONTH) + 1 < 10 ? "0"
				+ String.valueOf(cal.get(Calendar.MONTH) + 1) : String
				.valueOf(cal.get(Calendar.MONTH) + 1);
		String xMonthRate = "";
		String sql = "\n"
				+ "select sum(profit) as profit from ( \n"
				+ "select\n"
				+ "		  DATE_FORMAT(t1.OPENTIME_,'%Y%m') as dm, round(sum(t1.profit_),4) as\n"
				+ "		  profit , (select round(sum(t2.PROFIT_),4) from\n"
				+ "		  accounttransactionsinfo t2 where\n"
				+ "		  DATE_FORMAT(t2.OPENTIME_,'%Y%m')<=DATE_FORMAT(t1.OPENTIME_,'%Y%m')\n"
				+ "		  and t2.TYPE_ in ('buy','sell')) as total FROM accounttransactionsinfo\n"
				+ "		  t1 where t1.TYPE_ in ('buy','sell') and t1.ACCOUNT_ID_='"
				+ account + "' group by\n"
				+ "		  DATE_FORMAT(t1.OPENTIME_,'%Y%m') \n" + ") a\n"
				+ "where dm<'" + nowYear + nowMonth + "' and dm>='"
				+ targetYear + targetMonth + "'";

		List results = jdbcTemplate.queryForList(sql);
		while (results.size() > 0) {
			Map record = (Map) results.get(0);
			xMonthRate = String.valueOf(record.get("profit"));
		}
		return xMonthRate;
	}

	/**
	 * 获取月平均利润率 总利润率除月份数
	 * 
	 * @param account
	 * @return
	 * @throws Exceptioin
	 */
	@Expose
	public String getAVGMonthly(String account) throws Exception {
		String avg = "0";
		String total = getSinceStart(account);
		String sql = " select count(0) from (\n"
				+ " select\n"
				+ "		  DATE_FORMAT(t1.OPENTIME_,'%Y%m') as dm, round(sum(t1.profit_),4) as\n"
				+ "		  profit , (select round(sum(t2.PROFIT_),4) from\n"
				+ "		  accounttransactionsinfo t2 where\n"
				+ "		  DATE_FORMAT(t2.OPENTIME_,'%Y%m')<=DATE_FORMAT(t1.OPENTIME_,'%Y%m')\n"
				+ "		  and t2.TYPE_ in ('buy','sell')) as total FROM accounttransactionsinfo\n"
				+ "		  t1 where t1.TYPE_ in ('buy','sell') and t1.ACCOUNT_ID_='"
				+ account + "'group by\n"
				+ "		  DATE_FORMAT(t1.OPENTIME_,'%Y%m') \n" + ") a";
		int monthCount = jdbcTemplate.queryForInt(sql);
		float tt = Float.parseFloat(total.substring(0, total.length() - 2));
		if (monthCount != 0) {
			System.out.println(tt / monthCount);
			avg = String.valueOf(AppUtil.getRoundUp(tt / monthCount, 4)) + "%";
		}
		return avg;
	}

	/**
	 * 查询所有的账户基本信息.
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider
	public Collection<AccountBaseInfo> getAllAccountBaseInfos()
			throws Exception {
		String sql = "SELECT\n"
				+ "t1.ID_,\n"
				+ "t1.ACCOUNT_,\n"
				+ "t1.NAME_,\n"
				+ "t1.CURRENCY_,\n"
				+ "t1.CURRENTDATE_,\n"
				+ "t1.DEPOSIT_,\n"
				+ "t1.CLOSETRADE_,t2.PRODUCT_NAME_,t2.OUT_LINK_URL_,t2.MINIMUM_INVESTMENT\n"
				+ "FROM accountbaseinfo t1";
		sql += "\n left join accountinfo t2 on t1.ACCOUNT_=t2.ACCOUNT_\n"
				+ "where t2.ISABLE_=1\n" + "order by t2.ORDER_";
		List<AccountBaseInfo> result = this.jdbcTemplate.query(sql,
				new AccountBaseInfoRowMapper());
		Iterator<AccountBaseInfo> ite = result.iterator();
		while (ite.hasNext()) {
			AccountBaseInfo base = ite.next();
			String account = base.getAccount();
			base.setCurrentMonth(getCurrentMonth(account));
			base.setCurrentYearToDate(getCurrentYearToDate(account));
			base.setSinceStart(getSinceStart(account));
			base.setAnnualisedReturn(getAnnualisedReturn(account));
			base.setTotalReturn(getTotalReutrn(account));
			base.setAvgMonthlyReturn(getAverageMonthlyReturn(account));
			base.setStartOfRealPerformance(getStartOfRealPerformance(account));
			base.setInitialCaptial(getInitialCaptial(account));
		}
		return result;
	}

	/**
	 * 查询所有定义的产品名称
	 * 
	 * @return
	 */
	@DataProvider
	public List<AccountInfo> getAllAccountInfo() {
		List<AccountInfo> result = null;
		String sql = "select * from accountinfo";
		result = this.jdbcTemplate.query(sql, new AccountInfoRowMapper());
		return result;
	}

	/**
	 * 保存
	 * 
	 * @param account
	 * @param parameter
	 * @return
	 */
	@DataResolver
	public Object saveAccountInfo(List<AccountInfo> account, Object parameter)
			throws Exception {
		Iterator<AccountInfo> ite = account.iterator();
		while (ite.hasNext()) {
			AccountInfo info = ite.next();
			if (EntityUtils.getState(info).equals(EntityState.NEW)) {
				if (!AppUtil.createDir(info.getFilePath())) {
					throw new Exception("该文件夹以存在");
				}
				String sql = "insert into accountinfo values(?,?,?,?,?,?,?,?,?)";
				int count = this.jdbcTemplate.update(sql, UUID.randomUUID()
						.toString(), info.getProductName(), info.getFilePath(),
						info.getOrder(), info.getAccount(), info.getIsable(),
						info.getBalanceSize(), info.getOutLinkUrl(), info
								.getMinimumInvestment());
			}
			if (EntityUtils.getState(info).equals(EntityState.MODIFIED)) {
				String sql = "update accountinfo set PRODUCT_NAME_=?,FILE_PATH_=?,ORDER_=?,ACCOUNT_=?,ISABLE_=?,BALANCE_SIZE_=?,OUT_LINK_URL_=?,MINIMUM_INVESTMENT=? where ID_=?";
				int count = this.jdbcTemplate.update(sql,
						info.getProductName(), info.getFilePath(),
						info.getOrder(), info.getAccount(), info.getIsable(),
						info.getBalanceSize(), info.getOutLinkUrl(),
						info.getMinimumInvestment(), info.getId());
			}
			if (EntityUtils.getState(info).equals(EntityState.DELETED)) {
				String sql = "delete from accountinfo where id_=?";
				this.jdbcTemplate.update(sql, info.getId());
				AppUtil.delete(info.getFilePath());
			}
		}
		return null;
	}

	@Expose
	public Object changeStatus(Map param) {
		String id = (String) param.get("id");
		int able = (Integer) param.get("able");
		if (StringUtils.isNotEmpty(id)) {
			String sql = "update accountinfo set ISABLE_=? where ID_=?";
			jdbcTemplate.update(sql, able, id);
		}
		return null;
	}

	/**
	 * 根据Account获取产品名称
	 * 
	 * @param account
	 * @return
	 */
	@Expose
	public String getProductNameByAccount(String account) {
		String pName = "";
		if (StringUtils.isNotEmpty(account)) {
			String sql = "select PRODUCT_NAME_ FROM ACCOUNTINFO where ACCOUNT_=?";
			pName = this.jdbcTemplate.queryForObject(sql,
					new Object[] { account }, String.class);

		}
		return pName;
	}

	/**
	 * 手动调用任务,刷新已启用的产品
	 * 
	 * @param param
	 * @return
	 */
	@Expose
	public String flushData(Map param) throws Exception {
		String returnMsg = loadHtm.readHtm() ? "success" : "failure";
		return returnMsg;
	}
}
