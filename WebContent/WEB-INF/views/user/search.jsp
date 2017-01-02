<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>

<div class="container">
  <div class="row">
    <div class="col s12 m6 offset-m3">
    <ul class="collection">
     	<div class="row" style="margin-left:20%">
       			<form action="/user/s" method="post">
			        <div class="input-field col s6">
						<input id="userName" name="userName" type="text" class="validate">
						<label for="userName">User Name</label>
			        </div>
			        <div class="input-field col s6">
			            <button class="waves-effect waves-light blue btn" type="submit"><i class="mdi-file-cloud left"></i>Search</button>
			        </div>
			    </form>
	      	</div>
     </ul>
  </div>
	</div>
	</div>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>