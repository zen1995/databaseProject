<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<div class="container" ng-controller = "register">
    <div class="row">
        <div class="col m6 offset-m3 s10 offset-s1">

            <form name="input" action="/user/register" method="POST">
            
                <div class="input-field">
                    <label for="useraccount">user account</label>
                    <input id = "useraccount" type="text" name="userAccount"autocomplete="off"/>
               		<p class="red-text" id="warning3" style="display:none">用户名重复</p>
                </div>
                
                <div class="input-field">
                    <label for="password" >password(至少六位)</label>
                    <input class="password" id = "password" type="password" name="password" autocomplete="off"/>
                </div>
                <div class="input-field">
                    <label for="password_check" >password repeat</label>
                    <input class="password_check"id = "password_check" type="password" name="password" autocomplete="off"/>
                    <p class="red-text" id="warning1" style="display:none">两次输入密码不一致</p>
                    <p class="red-text" id="warning2" style="display:none">密码长度最短为六位</p>
                </div>
                <div class="input-field">
                    <label for="username" >user name</label>
                    <input id = "username" type="text" name="username" autocomplete="off"/>
                </div>
                <p>sex:</p>
				<p>
				   <input name="sex" type="radio" value="1" id="male" checked/>
				   <label for="male">Man</label>
				</p>
				<p>
				   <input name="sex" type="radio" value="2" id="femal" />
				   <label for="femal">Woman</label>
				</p>
				
			 <!-- 	<div class="divider"></div>-->
				<!-- <div class="input-field col s12"> -->
		      <!--   <textarea id="content" name="content" class="materialize-textarea" length="120"></textarea>
		          <label for="content">personal signature</label>
		        </div> -->
               <!--  <a class="waves-effect waves-light btn blue white-text" onclick="checkUserInfo()">检查用户</a> -->
                <a class="waves-effect waves-light btn blue white-text" onclick="insertUser()">注册</a>
            </form>
        </div>
    </div>
</div>

<script >



</script>

<%@include file="/WEB-INF/views/frame/footer.jsp"%>