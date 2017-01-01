<%--
  Created by IntelliJ IDEA.
  User: czn
  Date: 11/11/2015
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<script data-main = "/resource/static/js/mychart.js" src="//cdn.bootcss.com/require.js/1.0.8/require.min.js"></script>

<div class="container">
	<div class="row">
		<div class="col s4">
			<div id="sexRate" style="width: 100%;height:400px;"></div>
		</div>
		<div class="col s8">
			<div id="popularUser" style="width: 100%;height:400px;"></div>
		</div>
		<div class="col s6">
			<div id="popularArticle" style="width: 100%;height:400px;"></div>
		</div>
		<div class="col s6">
			<div id="mostFan" style="width: 100%;height:400px;"></div>
		</div>
	</div>
</div>

<script>
var sexData = <c:out value="${sexData}" escapeXml= "false"></c:out>
var userData = <c:out value="${popularUser}" escapeXml= "false"></c:out>
var articleData = <c:out value="${popularArticle}" escapeXml= "false"></c:out>
var mostFan = <c:out value="${mostFan}" escapeXml= "false"></c:out>
</script>

<%@include file="/WEB-INF/views/frame/footer.jsp"%>
