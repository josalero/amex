<%@page import="java.util.Locale"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.bac.oee.struts.ViewConstants"%>
<%@page import="com.amex.srt.AppConstants"%>
<%@page import="com.bac.oee.model.PageData"%>
<%@page trimDirectiveWhitespaces="true"%>

<%@ include file="/WEB-INF/jsp/include/imports-taglibs.jsp"%>
<%
	String sessionID = request.getSession().getId();

	Locale localeObject = request.getLocale();
	
	String hostName = request.getScheme() + "://"
			+ request.getServerName();
	
	String language = localeObject.getLanguage();
	String country = localeObject.getCountry();

	if (language.equals("en") && StringUtils.isEmpty(country)) {
		country = "US";
	}

 	String locale = language + "_" + country;

 	String basePath = request.getContextPath();

	String brand = request.getParameter("brand");
	if (brand == null) {
		
		brand = (String)session.getAttribute("brand");
		
		if (brand == null) {
			brand = "bank-of-america";
		}
	}
	
	Boolean devMode = Boolean.TRUE;
	
	if (request.getParameter("PROD") != null) {		
		pageContext.setAttribute("devMode", Boolean.FALSE);
	}
	else {
		pageContext.setAttribute("devMode", devMode);
	}	
	
	String googleAnalyticsSiteID = (String)request.getAttribute(ViewConstants.GA_SITE_ID);	
%>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Bank of America | Employee Experience</title>	
	<meta name="keywords" content="about bank of america employee experience" />
	<meta name="description" content="description goes here." />
	
	<tiles:insertAttribute name="common-page-head" />	 

	<meta name="viewport" content="width=device-width">
    
	<link rel="stylesheet" href="/css/fonts.css" type="text/css" />
	
	<script>
    	
		if ( ! window.console ) console = { log: function(){} };

    	var hostAndContextName = "<%=hostName%>";
    	var devMode = "<%=devMode%>";
  	
    	steal = { 
			
    			loaded: ['oee/production.css']
    	};
    	
    </script>
  	
	<c:if test="${!devMode}">			
		<link rel="stylesheet" href="/jmvc/oee/production.css" type="text/css" />
	</c:if>  	
	
	<link rel="stylesheet" href="/css/oee-ie.css" type="text/css" />

	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', '<%=googleAnalyticsSiteID%>' ]);
		_gaq.push([ '_trackPageview' ]);
	
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
				 				
 </head> 	
 
 
<!--HTML body starts-->
<body id="home" >

	<div>
		<!-- place holder for including the page header -->
		<tiles:insertAttribute name="header" />
		<!-- place holder for including the page navigation menu -->
		<tiles:insertAttribute name="navigation" />
		<!-- place holder for including the page body -->
		<tiles:insertAttribute name="body" />
		<!-- place holder for including the page footer -->
		<tiles:insertAttribute name="footer" />
	</div>
	
	<c:if test="${!devMode}">					
		<script type='text/javascript' src='/jmvc/steal/steal.production.js?oee'></script>
	</c:if>
	
	<c:if test="${devMode}">
		<script type='text/javascript' src='/jmvc/steal/steal.js?oee'></script>
	</c:if>	  		
		
</body>
<!--HTML body ends -->

</html>