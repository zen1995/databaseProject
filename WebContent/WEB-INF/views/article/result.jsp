<%--
  Created by IntelliJ IDEA.
  User: czn
  Date: 11/11/2015
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<c:if test="${articles = 0}">
<c:out value="NOFOUND"/>
</c:if>
<%@include file="/WEB-INF/views/frame/articleshow.jsp" %>
测试 v2016.8.19.1
<%@include file="/WEB-INF/views/frame/footer.jsp"%>
