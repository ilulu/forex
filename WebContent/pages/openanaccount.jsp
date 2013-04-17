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
<title>MorePip Invest - How to open an account</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15" />
<meta name="keywords" content="currency investment" />
<meta http-equiv="Content-Language" content="en" />
<meta name="robots" content="index,follow" />
<meta name="googlebot" content="index,follow" />
<link rel="shortcut icon" type="images/x-icon" href="favicon.ico" />
<link type="text/css" rel="Stylesheet" href="../css/oipcustomer_metro.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<link type="text/css" rel="stylesheet" href="../css/responsive.css" />
<link type="text/css" rel="stylesheet" href="../css/nivoslider.css" />
<link type="text/css" rel="stylesheet" href="../css/color_<%=skinColor%>.css" />

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
				<a href="../welcome.d">MorePip</a>
			</h1>
			<div class="sub-navigation"></div>
			<div>
				<ul id="meta-navigation">
					<li id="meta-navContact"><a href="../contactus.d">Contact</a></li>
				</ul>
			</div>
			<!--div id="ticker" class="empty"-->
		</div>
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
		<div id="left-column">
			<div id="left-column-content">
				<div id="navigation">
					<ul>
						<li ><a href="../welcome.d">WELCOME</a></li>
						<li><a href="../performance.d">PERFORMANCE SUMMARY</a></li>
						<li><a href="investment.jsp">INVESTMENT STRATEGY</a></li>
						<li class="active"><a href="openanaccount.jsp">OPEN AN ACCOUNT</a></li>
						<li><a href="../contactus.d">CONTACT US</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="wide" id="text">
			<h2 class="left" id="BackToTop">Open Account </h2>
			<!-- 2012-12-18 delete 1-7 -->
			<p>MorePip Invest uses FxPro MultiTerminal MT4 trading platform to manage clients’ accounts. </br> 
			please send your account credential to <span style="font-style:italic;font-weight:bold;font-color:#000000;">account@morepip.com</span> when your account is ready.</p>
			<p>
			To apply for a managed account, Please download the <a href="../pdf/Asset Management Agreement.pdf">pdf form</a>, sign it and send it to us.
			</p>
			<p>
			At the first day of every month, please transfer the performance fee to our account at Final-pay. (<a href="http://www.final-pay.com">www.final-pay.com</a>).
			</p>
			<p>
				If you have questions about how to set up your account, <a
					href="../contactus.d">contact us</a>
			</p>
			<!-- <p>
			To get your no obligation Zipsignals PRO account, simply open a new client trading account with one of our compatible brokers. 
			Opening an account through this offer has no affect on spreads. 
			Your trading account will have the exact same spreads as an account opened individually. Every Forex broker listed has been selected for excellent spreads, 
			fast execution and outstanding customer service. Get started today.
			</p> -->
		</div>
		<div id="oac_add" >
		<div id="add_fxPro">
			<div class="add_img"><a href="https://direct.fxpro.com/ib/en/usd/312666"><img  src="../images/station/fxPro.jpg"></img></a></div>
			<div class="c_info">
				<ul>
					<li>Tight Spreads / Fast Execution</li>
					<li>Leverage up to 1:500</li>
					<li>Hedging Allowed - No FIFO Rules</li>
					<li>Offer not Available to U.S. Clients</li>
				</ul>
				<span style="font-weight:bold;color:#000000;">Open FxPro Account - 
				<a href="https://direct.fxpro.com/ib/en/usd/312666">LIVE</a></span> 
			</div>
			<div style="line-height:2em;font-size:xx-small;">
			<p>
				**FXPRO Financial Services Ltd is authorised and regulated by 
				the Cyprus Securities and Exchange Commission under the
				&nbsp;&nbsp;license number 078/07.</br>
				&nbsp;&nbsp;We are Introducers of FxPro.
				</p>
			</div>
			</div>
			<!-- div id="backtotop">
			<h4>
			<a class="anchorsTop1" href="#" >Back to top</a>
			</h4>
			</div> -->
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
