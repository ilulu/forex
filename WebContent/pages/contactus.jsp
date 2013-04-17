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
<link type="text/css" rel="Stylesheet" href="css/oipcustomer_metro.css"/>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/responsive.css" /> 
<link type="text/css" rel="stylesheet" href="css/nivoslider.css" />
<link type="text/css" rel="stylesheet" href="css/color_<%=skinColor%>.css" />
<style type="text/css" media="print">
  #header, #nav, #mainnav, #subnav, #meta, #playermessage, .tipOwner,
  .button, .wideButton, .narrowButton, div.excludefromprinting {
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
						<li><a href="performance.d">PERFORMANCE SUMMARY</a></li>
						<li><a href="pages/investment.jsp">INVESTMENT STRATEGY</a></li>
						<li><a href="pages/openanaccount.jsp">OPEN AN ACCOUNT</a></li>
						<li class="active"><a href="contactus.d">CONTACT US</a></li>
					</ul>
				</div>
			</div>
		</div>
			<!-- START content-container -->
			<div id="content-container" class="clearfix" style="margin-left:210px;padding-top:7px;">
				<div id="main-wrap" class="clearfix">
					<div class="page_content">
						<div class="img-frame small-banner">
							<img src="images/station/woman-banner.png"
								alt="Sterling - Responsive Web Template" width="650"
								height="169" />
						</div>
						<div class="large-callout">
							<p>
								Thank you so much for your interest in the forex Website.</br> It's
								our pleasure that if you can leave you info here for us to
								introduce our business.
						</div>
						<div id="contact">
							<form action="" id="contactForm">
								<div id="message">&nbsp;</div>
								<label for="name" accesskey="U"><span>*</span> Your Name</label>
								<input name="name" type="text" id="name" size="30" value="" /> <br />
								<label for="email" accesskey="E"><span>*</span> Email</label> <input
									name="email" type="text" id="email" size="30" value="" /> <br />
								<label for="comments" accesskey="C"><span>*</span> Your
									comments</label>
								<textarea name="comments" cols="40" rows="3" id="comments"></textarea>
	
								<br />
								<br />
									<div id="submitInfo"></div>
							</form>
						</div>
						<!-- END contact -->
					</div>
					<!-- END of page_content-->
					<div class="sidebar-widget">
						<!-- p class="widget-heading">Business Hours</p> -->
						<div class="business-hours">
							
						</div>
					</div>
					<div class="sidebar-widget">
						<!-- p class="widget-heading">Contact Details</p> -->
						<div class="contact_details">
							
						</div>
					</div>
				</div>
				<!-- END main-wrap -->
			</div>
			<!-- END content-container -->

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
