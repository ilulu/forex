<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.bstek.com/dorado/taglib-7.0" prefix="d" %>
<%@page import="org.forex.loadhtm.GetInfomation"%>
<%@page import="org.forex.loadhtm.LoadSkin" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils " %>
<%@page import="org.springframework.web.context.WebApplicationContext" %>
<%@page import="javax.servlet.*" %>
<%@page import="java.util.*"%>
<%
	String skinColor= "color_blue";
	ServletContext servletContext = this.getServletContext(); 
	WebApplicationContext ctx =WebApplicationContextUtils.getWebApplicationContext(servletContext); 
	LoadSkin ls = (LoadSkin)ctx.getBean("loadSkin");
	skinColor = ls.getSkin();
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<d:PageHeader/>
<title>MorePip Invest - Performance Summary</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15" />
<meta name="keywords" content="currency investment" />
<meta http-equiv="Content-Language" content="en" />
<meta name="robots" content="index,follow" />
<meta name="googlebot" content="index,follow" />
<link rel="shortcut icon" type="images/x-icon" href="favicon.ico" />
<link type="text/css" rel="StyleSheet" href="css/oipcustomer_metro.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/responsive.css" />
<link rel="stylesheet" href="css/nivoslider.css" />
<link rel="stylesheet" href="css/color_<%=skinColor%>.css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.bxSlider.min.js"></script>
<script src="js/jquery.nivo.slider.js"></script>
<script src="js/highcharts.js"></script>
<script src="js/exporting.js"></script>

<style type="text/css" media="print">
#header,#nav,#mainnav,#subnav,#meta,#playermessage,.tipOwner,.button,.wideButton,.narrowButton,div.excludefromprinting
	{
	display: none;
}
</style>
<script language="javascript" type="text/javascript">
	if (window.XMLHttpRequest) {
		// IE 7, mozilla, safari, opera 9
	} else {
		window.location = "oldbrowser.vm";
	}
</script>
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/jquery.lightbox_me.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<!-- Start Header -->
			<h1 id="title">
				<a href="welcome.d">OlsenInvest</a>
			</h1>
			<div>
				<ul id="meta-navigation">
					<li id="meta-navContact"><a href="contactus.d">Contact</a></li>
					
				</ul>
			</div>
			<!--div id="ticker" class="empty">
      </div-->
		</div>
		<!-- top_title -->
		<div class="top_title_out">
			<div class="top_title">
				<div class="wraper">
					
				</div>
			</div>
			<div class="shadow top"></div>
			<div class="shadow bottom"></div>
		</div>
		<div id="content">
			<div class="large" id="text">
				<h2 class="pageTitle">
					<div id="productName"></div></h2>
				<div id="manager-actions">
					<a href="javascript:history.back()">Back</a>
				</div>
<!-- 				<span style="padding-left: 100px;"><strong>Monthly -->
<!-- 						Cumulated Gross Return [%]</strong></span> -->
				<div id="product_AA" style="min-height: 300px"></div>
				<div id='accountViewDiv'></div>
				<script language='javascript'>
					var accountViewOpts = {
						ssid : '4l63a1Sf9PD6oeMUhOnR',
						historyDays : '10000',
						width : '850',
						height : '320',
						products : 'AA'
					}
				</script>
				<script language='javascript'
					src='accountview/accountview.nocache.js'></script>
				<div id="dataRow">
				</div>
				<table class="noline" style="margin-top: 30px;" border="0">
					<tr class="reportbold">
						<td class="borderBottom" colspan="6" style="padding-bottom: 10px;"><strong>Statistics</strong></td>
					</tr>
					<tr>
						<td valign="top" width="30%">
							<table class="reporttable" style="margin-top: 0px;">
								<tr>
									<td>Return current month</td>
									<td id="returnCurrentMonth" class="oipBold right"></td>
								</tr>
								<tr>
									<td>Return last month</td>
									<td id="returnLastMonth" class="oipBold right"></td>
								</tr>
								<tr>
									<td>Return last 3 months</td>
									<td id="returnList3Months" class="oipBold right"></td>
								</tr>
								<tr>
									<td>Return last 6 months</td>
									<td id="returnLast6Months" class="oipBold right"></td>
								</tr>
								<tr>
									<td>Return last 12 months</td>
									<td id="returnLast12Months" class="oipBold right"></td>
								</tr>
							</table>
						</td>
						<td width="3%"></td>
						<td valign="top" width="30%">
							<table class="reporttable" style="margin-top: 0px;">
								<tr>
									<td>Return last 24 months</td>
									<td id="returnLast24months" class="oipBold right"></td>
								</tr>

								<tr>
									<td>Return last 36 months</td>
									<td id="returnLast36Months" class="oipBold right"></td>
								</tr>
								<tr>
									<td>Return since inception</td>
									<td id="returnSinceInception" class="oipBold right"></td>
								</tr>
								<tr>
									<td>Return average monthly</td>
									<td id="returnAvgMonthly" class="oipBold right"></td>
								</tr>
							</table>
						</td>
						<td width="3%"></td>
						<td valign="top" width="30%">
							<table class="reporttable" style="margin-top: 0px;">
							</table>
						</td>
					</tr>	
				</table>
				<div style="margin-top: 40px;">
					<strong>DISCLAIMER</strong>: VISITORS SHOULD BE AWARE THAT THE
					INFORMATION CONTAINED HEREIN IS PROVIDED IN GOOD FAITH TO
					DEMONSTRATE THE PROGRESS THAT OLSEN LTD HAS MADE IN THE DEVELOPMENT
					OF ITS INVESTMENT PROGRAMMES.<br />&nbsp;<br /> ANYONE
					CONSIDERING THE MAKING OF AN INVESTMENT IN ANY PROGRAMME OFFERED BY
					OLSEN LTD MUST DO SO ENTIRELY AT HIS OR HER OWN RISK. OLSEN LTD
					PROVIDES NO ASSURANCE AS TO THE FUTURE PERFORMANCE OF ANY OF ITS
					INVESTMENT PROGRAMMES. NOR DOES OLSEN LTD PROVIDES ANY ADVICE AS TO
					SUCH INVESTMENT OR THE SUITABILITY THEREOF. <br />&nbsp;<br />
					THIS INFORMATION IS CONFIDENTIAL AND IS INTENDED SOLELY FOR THE USE
					OF THE PERSON TO WHOM IT HAS BEEN DELIVERED. THIS INFORMATION MAY
					NOT BE REPRODUCED OR GIVEN TO ANY OTHER PERSON.<br />&nbsp;<br />

					PAST PERFORMANCE IS NO GUARANTEE OF FUTURE RESULTS. THE VALUE OF
					INVESTMENTS MAY FALL AS WELL AS RISE. <br />&nbsp;<br /> 
				</div>
		</div>
	</div>
	
	<div class="footer_out">
		<div class="tt-overlay">
			<div class="fbg">
				<div class="fbg_resize">
					<div class="clr">
						&copy;MorePip Invest&nbsp;2012-2015&nbsp;All&nbsp;rights&nbsp;received.
					</div>
				</div>
			</div>
			<div class="shadow top"></div>
		</div>
	</div>

</body>
</html>
