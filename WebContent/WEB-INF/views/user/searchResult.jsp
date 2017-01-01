<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>

<div class="container">
  <div class="row">
    <div class="col s12 m6 offset-m3">
    <ul class="collection">
    <c:out value="${users }"> </c:out>
     	<c:forEach items = "${users}" var = "user">
     	  <a class="collection-item" 
     	  href='/user/space/<c:out value="${user.id }"></c:out>'><c:out value="${user.name }"></c:out></a>
     	</c:forEach>
     </ul>
  </div>
</div>
</div>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>