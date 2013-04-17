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
<link type="text/css" rel="Stylesheet" href="css/oipcustomer_metro.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/responsive.css" />
<link type="text/css" rel="stylesheet" href="css/nivoslider.css" />
<link type="text/css" rel="stylesheet" href="css/color_<%=skinColor%>.css" />

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

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<!-- Start Header -->
			<h1 id="title">
				<a href="welcome.d">OlsenInvest</a>
			</h1>
			<div class="sub-navigation"></div>
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
			<div id="left-column">
				<div id="left-column-content">
					<div id="navigation">
						<ul>
							<li ><a href="welcome.d">WELCOME</a></li>
							<li class="active"><a href="performance.d">PERFORMANCE SUMMARY</a></li>
							<li ><a href="pages/investment.jsp">INVESTMENT STRATEGY</a></li>
							<li><a href="pages/openanaccount.jsp">OPEN AN ACCOUNT</a></li>
							<li><a href="contactus.d">CONTACT US</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="wide1" id="text">
				<h2 id="BackToTop" class="left">Performance Summary</h2>
				<p>
					For a more detailed performance history, click on the name of an MorePip Managed Account. To open an account follow the 
					instruction on <a href="pages/openanaccount.jsp">"Open an Account"</a>.
				</p>

				<p>This track record shows gross return including interest. No Performance Fees or other fees have been deducted. 
				All results considered to be hypothetical unless otherwise specified.</p>

				<div id="accountInfos"></div>

				<div id='accountViewDiv'></div>
				<script language='javascript'>
					var accountViewOpts = {
						ssid : 'd16wpKFNLV4JDatTM3DC',
						historyDays : '365',
						width : '450',
						height : '320',
						products : 'AA,AB,AC,AD,AF'
					};
				</script>
				<script language='javascript'
					src='accountview/accountview.nocache.js'></script>
				<br />
				<p>*Past performance is not indicative of future results and a positive performance cannot be guaranteed. 
				Some of the products and services mentioned may, due to local regulations, 
				not be available to individuals resident in certain countries. The products 
				described herein may not necessarily be suitable for you. You should consult 
				an adviser for your choice. This page does not constitute an offer, 
				a recommendation or an invitation to apply for the product. 
				The availability of the product depends, amongst other things, on your
				 investment product profile or on the legislation to which you are subject. 
				 This information is issued by MorePip Invest.</p>
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
