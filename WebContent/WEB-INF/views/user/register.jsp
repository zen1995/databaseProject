<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<div class="container" ng-controller = "register">
    <div class="row">
        <div class="col m6 offset-m3 s10 offset-s1">

            <form name="input" action="/user/register" method="POST">
            
                <div class="input-field">
                    <label for="useraccount">user account</label>
                    <input id = "useraccount" type="text" name="userAccount"autocomplete="off"/>
                </div>
                
                <div class="input-field">
                    <label for="password" >password</label>
                    <input id = "password" type="password" name="password" autocomplete="off"/>
                </div>
                <div class="input-field">
                    <label for="password_check" >password repeat</label>
                    <input id = "password_check" type="password" name="password" autocomplete="off"/>
                </div>
                <div class="input-field">
                    <label for="username" >user name</label>
                    <input id = "username" type="text" name="username" autocomplete="off"/>
                </div>
                <p>sex:</p>
				<p>
				   <input name="sex" type="radio" id="male" />
				   <label for="male">Red</label>
				</p>
				<p>
				   <input name="sex" type="radio" id="femal" />
				   <label for="femal">Yellow</label>
				</p>
				 
                <a class="waves-effect waves-light btn blue white-text" onclick="checkUserInfo()">检查用户</a>
                <a class="waves-effect waves-light btn blue white-text" onclick="insertUser()">注册</a>
            </form>
        </div>
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