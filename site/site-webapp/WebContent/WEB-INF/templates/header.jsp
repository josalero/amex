<%@page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="header">
	
	<ul id="nav">
  	
  		<!-- Header: Floating Navigation Bar -->
     	<li class="logo"><a href="http://flagscape.bankofamerica.com/">FLAGSCAPE</a></li>
	
		<c:if test="${!empty siteNavigationData && !empty siteNavigationData.primaryNav && !empty siteNavigationData.primaryNav.navItems && !empty siteNavigationData.primaryNav.navItems.navItemList}">
		 	
		 	<c:forEach var="column" items="${siteNavigationData.primaryNav.navItems.navItemList}" varStatus="index">
	 		
	 			<c:set var="columnValue" value="${fn:toLowerCase(fn:replace(column.name, ' ', '-'))}" />
			
				 <li class="${column.id}">
				 
				 	<a href="#!${column.link.linkText}" 
				 		data-gacategory="Overview" 
				 		data-gaaction="Select" 
				 		data-galabel="Nav-bar-${column.link.linkText}">${column.name}</a></li>
			
			</c:forEach>
		</c:if>	   
				   
	</ul>
</div>