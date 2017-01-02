<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<div class="container" ng-controller = "register">
    <div class="row">
        <div class="col m6 offset-m3 s10 offset-s1">
            <form name="input" action="/user/register" method="POST">
            
                <div class="input-field">
                    <label for="useraccount">user account</label>
                    <input id = "useraccount" type="text" name="userAccount" disabled="disabled"
                    value="<c:out value="${user.account }"></c:out>" autocomplete="off"/>
                </div>
                
                <div class="input-field">
                    <label for="password" >password</label>
                    <input id = "password" type="password" name="password" 
                    value="<c:out value="${user.password }"></c:out>" autocomplete="off"/>
                </div>
                <div class="input-field">
                    <label for="password_check" >password repeat</label>
                    <input class="password_check"id = "password_check" type="password" name="password" autocomplete="off"/>
                    <p class="red-text" id="warning1" style="display:none">两次输入密码不一致</p>
                    <p class="red-text" id="warning2" style="display:none">密码长度最短为六位</p>
                </div>
                <div class="input-field">
                    <label for="username" >user name</label>
                    <input id = "username" type="text" name="username" 
                    value="<c:out value="${user.name }"></c:out>" autocomplete="off"/>
                </div>
                <div class="input-field">
                    <label for="userage">user age</label>
                    <input id = "userage" type="text" name="userAge" 
                    value="<c:out value="${user.age }"></c:out>" autocomplete="off"/>
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
				<!-- <div class="input-field col s12"> -->
		        <textarea id="content" name="content" class="materialize-textarea" length="1200"></textarea>
		          <label for="content">personal signature</label>
		        </div>
                <a class="waves-effect waves-light btn blue white-text" onclick="editUser()">确定修改</a>
            </form>
        </div>
</div>

<script >
    $(document).ready(function(){
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15 // Creates a dropdown of 15 years to control year
        });

    });

</script>

<%@include file="/WEB-INF/views/frame/footer.jsp"%>