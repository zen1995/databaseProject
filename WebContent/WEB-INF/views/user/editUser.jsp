<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<div class="container" ng-controller = "register">
    <div class="row">
        <div class="col m6 offset-m3 s10 offset-s1">
            <form name="input" action="/user/register" method="POST">           
                <div class="input-field">
                    <label for="userName">user name</label>
                    <input id = "userName" type="text" name="userName"autocomplete="off" ng-model = "userName"/>
                </div>
                
                <div class="input-field">
                    <label for="password" >password</label>
                    <input id = "password" type="password" name="password" autocomplete="off" ng-model = "password"/>
                </div>
                <div class="input-field">
                    <label for="password_check" >password repeat</label>
                    <input id = "password_check" type="password" name="password" autocomplete="off" ng-model = "password"/>
                </div>
                <a class="waves-effect waves-light btn blue white-text" ng-click="checkUserInfo()">检查用户</a>
                <a class="waves-effect waves-light btn blue white-text" ng-click="insertUser()">注册</a>
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