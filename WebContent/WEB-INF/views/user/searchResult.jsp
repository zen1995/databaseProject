<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>

<div class="container">
<h3 class="blue-text center-align">RESULT</h3> 
  <div class="row">
    <div class="col s12 m6 offset-m3">
    
    <ul class="collection">
    	
   <!--  <c:out value="${users }"> </c:out>  -->
     	<c:forEach items = "${users}" var = "user">
     	  <a class="collection-item" 
     	  href='/user/space/<c:out value="${user.id }"></c:out>'>
     	  name: <c:out value="${user.name }"></c:out>
     	  age: <c:out value="${user.age }"></c:out>
     	  sex: <c:out value="${user.sex }"></c:out></a>
     	</c:forEach>
     </ul>
  </div>
</div>
</div>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>