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
<title>MorePip Invest - Investment Strategy</title>
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
				<a href="../welcome.d">OlsenInvest</a>
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
	<div id="content" style="margin-top:22px;">
		<div id="left-column">
			<div id="left-column-content">
				<div id="navigation">
					<ul>
						<li ><a href="../welcome.d">WELCOME</a></li>
						<li><a href="../performance.d">PERFORMANCE SUMMARY</a></li>
						<li class="active"><a href="investment.jsp">INVESTMENT STRATEGY</a></li>
						<li><a href="openanaccount.jsp">OPEN AN ACCOUNT</a></li>
						<li><a href="../contactus.d">CONTACT US</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="wide" id="text">
			<h2 id="InvestmentStrategy">
				<a class="anchorsTop" href="#BackToTop">Investment Strategy</a>
			</h2>
			<p>MorePip Invest trades spot currencies following a purely quantitative trading approach.</p>
			<p>
				Investment decisions are made through quantitative trading models.
				These models comprise of short- and medium-term components, 
				taking positions from a few minutes to several days.
			</p>
			<p>Our competitive edge does not depend on any particular market conditions: 
			our trading models are stress-tested across all currencies and perform equally 
			well in volatile and sideways markets. Manual Intervention eliminates any 
			delay in detecting market opportunity and automated execution enables us to 
			take advantage of even the smallest price move, which contributes to 
			consistency of returns.</p>
			
			<p>Instead of betting on high returns from a few speculative positions, we make many smaller, 
			more informed trades with a higher likelihood of success and lower risk.</p>
			<p>The objective of this strategy is to deliver superior, consistent risk-adjusted returns 
			from portfolios with low correlation to other asset classes.</p>
			<p>Past returns are no indication of future success. An Investor must be aware that the 
			investment strategy involves risk, where the losses can exceed expected profits.</p>
			<p class="separatorSmall">&nbsp;</p>
			
			<p class="separatorSmall">&nbsp;</p>
			
			<p class="separatorSmall">&nbsp;</p>
			<h4>
				<a class="anchorsTop" href="#">Back to top</a>
			</h4>
			<div style="margin-top: 30px;" class="center">
    <a href='media/Invest_TermsUse.pdf'>Terms of use</a> 
    &nbsp;&nbsp;&nbsp;
    <a href='media/Invest_Disclaimer.pdf'>Hypothetical Results Disclaimer</a>  
    &nbsp;&nbsp;&nbsp;
    <a href='media/PrivacyPolicy.pdf'>Privacy Policy</a></div>
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
