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
<title>MorePip Invest - Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15" />
<meta name="keywords" content="currency investment" />
<meta http-equiv="Content-Language" content="en" />
<meta name="robots" content="index,follow" />
<meta name="googlebot" content="index,follow" />
<link rel="shortcut icon" type="images/x-icon" href="favicon.ico" />
<link type="text/css" rel="StyleSheet"
	href="css/oipcustomer_metro.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/responsive.css" />
<link rel="stylesheet" href="css/nivoslider.css" />
<link type="text/css" rel="stylesheet" href="css/color_<%=skinColor%>.css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.bxSlider.min.js"></script>
<script src="js/jquery.nivo.slider.js"></script>

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
<script>
	$(function() {
		$('#slider').nivoSlider({
			controlNav : false
		});
		$('.post_slider').bxSlider({
			auto : false,
			speed : 1000
		});
		$('.clients_slider').bxSlider({
			auto : false,
			controls : false,
			mode : 'fade',
			pager : true
		});
	})
</script>
<!--<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/jquery.lightbox_me.js"></script>-->
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
			<div id="changeSkin" ></div>
		</div>
		<!-- nivo slider -->
		<div class="nivo-wrap">
			<div class="wraper">
				<!--<div class="fallback"><img src="images/sliders/home_page_4/fallback.png" alt="" /></div>-->
				<div class="slider-wrapper theme-default">
					<div id="slider" class="nivoSlider">
						<img src="images/station/home4_slide_1.jpg" width="933"
							height="377" data-thumb="images/features/img.png" alt=""
							title="#htmlcaption" /> 
					</div>
				</div>
			</div>
		</div>
		<!-- /nivo slider -->
		<div id="content">
			<div id="left-column">
				<div id="left-column-content">
					<div id="navigation">
						<ul>
							<li class="active"><a href="welcome.d">WELCOME</a></li>
							<li><a href="performance.d">PERFORMANCE SUMMARY</a></li>
							<li><a href="pages/investment.jsp">INVESTMENT STRATEGY</a></li>
							<li><a href="pages/openanaccount.jsp">OPEN AN ACCOUNT</a></li>
							<li><a href="contactus.d">CONTACT US</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="" id="text">
				<h2>THE MorePip MANAGED ACCOUNTS - MANAGED FOR YOU</h2>
				<p>MorePip Invest offers managed accounts with carefully defined risk and return, coverage of all major exchange rates, and reliable performance at low cost.</p>
				<p>We offer separately managed accounts, where we, as a professional Forex account manager, handle your investments for you.</p>
				<p>MorePip Invest managed accounts earn money-market interest and generate returns for specific risk targets. They offer continuous liquidity with no hidden administrator fees. The investor allocates his assets in the products they like.</p>
				<!-- h3 class="table-title">MorePip Invest Investment Products</h3> -->
				<ul>
					<li>No entry and exit fees</li>
					<li>Performance Fee 30%</li>
					<li>Online reporting</li>
					<li>All accounts are available in USD.</li>
				</ul>
				<div style="margin-top: 30px;">
					**All results considered to be hypothetical unless otherwise specified. Past performance is no guarantee of future results. Investments may fall as well as rise. An investor must be aware that the investment strategy involves risk, where the losses can exceed expected profits.
				</div>
				<div style="margin-top: 30px;" class="center">
    &nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;
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