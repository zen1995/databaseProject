<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>

<div class="container" ng-controller = "login">
  <div class="row">
    <div class="col s12 m6 offset-m3">
    <form name="input" action="" method="POST">
       	user name:
      <input id = "userName" type="text" name="userName" ng-model = "userName"/>
     	password:
      <input id = "password" type="password" name="password" ng-model = "password"/>
      <a class="waves-effect waves-light btn blue white-text" onclick="login()">登陆</a>
    </form>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>