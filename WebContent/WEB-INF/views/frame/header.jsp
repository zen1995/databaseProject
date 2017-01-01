<%--
  Created by IntelliJ IDEA.
  User: czn
  Date: 11/11/2015
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >

<html>
<head>
<title>utopialab</title>
<script src="/resource/static/js/jquery-1.8.3.min.js"></script>
<!--
  <script src="/resource/static/js/angular.min.js"></script>-->
<script src="//cdn.bootcss.com/angular.js/1.5.0/angular.js"></script>
<script src="//cdn.bootcss.com/angular.js/1.5.0/angular-sanitize.js"></script>
<script src="/resource/static/js/myAngular.js"></script>
<script src="/resource/static/js/library.js"></script>
<script src="/resource/static/js/webDatabase.js"></script>
<script src="/resource/static/js/remarkable.min.js"></script>
<link rel="stylesheet" href="/resource/static/css/blog.css" />
<%@include file="/WEB-INF/views/resource/materializeResource.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!--
  <meta property="wb:webmaster" content="89dfa924841825c4" />-->

</head>
<body>
	<header ng-controller="header" style="margin-bottom: 100px">
		<div class="navbar-fixed" style="position: fixed">
			<nav class="light-blue lighten-1 z-depth-0" role="navigation"
				style="">
				<div class="nav-wrapper container ">
					<a id="logo-container" href="/" class="brand-logo navbar-left">utopiaLab</a>
					<a href="#" data-activates="mobile-demo" class="button-collapse">
						<i class="material-icons">menu</i>
					</a>
					<ul class="right hide-on-med-and-down">
						<!--hide-on-med-and-down  right hide-on-small-only-->
						<c:if test="${user.status == true}">
							<li><a id="loginStatus" href="/user/"><c:out value="${user.name}"></c:out></a></li>
						</c:if>
						<c:if test="${user.status == false}">
							<li><a id="registerBtn" href="/user/register">register</a></li>
							<li id="signInBtn"><a href="/user/login">sign in</a></li>
						</c:if>
						<c:if test="${user.status == true}">
							<li><a class="signOutBtn" onClick="signOut()">sign out</a></li>
							<!--<li id="signOutBtn"><a href="#">sign out</a></li>-->
							<!--onclick="signOut()"-->
						</c:if>
						
					</ul>
					<ul class="side-nav" id="mobile-demo">
						<c:if test="${user.status == true}">
							<li><a href="/user/"><c:out value="${user.name }"></c:out></a></li>
						</c:if>
						<c:if test="${user.status == false}">
							<li><a href="/user/register">register</a></li>
							<li><a href="/user/login">sign in</a></li>
						</c:if>	
				    	<c:if test="${user.status == true}">
							<li><a class="signOutBtn" onClick="signOut()">sign out</a></li>
							<!--onclick="signOut()"-->
						</c:if>
						<c:if test="${user.status == true}">
				    	<ul id="editself-p" class="dropdown-content">
						<li><a href="/article/addPage" class='blue-text'>纂写文章</a></li>
			      		<li class="divider" ></li>
			      		<li><a href="/user/" class='blue-text'>个人信息</a></li>
						</ul>
    					<li><a class="dropdown-button" href="#!" data-activates="editself-p">
    					个人操作<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
						</c:if>
							<ul id="articleSearch-p" class="dropdown-content">
							<li><a href="/?showType=bylike" class='black-text'>点赞数</a></li>
			      			<li class="divider" ></li>
			      			<li><a href="/?showType=bytime" class='black-text'>最新</a></li>
			      			<li><a href="/article/byuser" class='black-text'>按用户</a></li>
							</ul>
     						<li><a class="dropdown-button" href="#!" data-activates="articleSearch-p">
     						查看文章<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
							<ul id="userSearch-p" class="dropdown-content">
							<li class="divider" ></li>
			     			<!-- <li><a href="/?showType=bytime" class='black-text'>新加入</a></li> -->
			     			<li><a onClick="" class='black-text'>精确查找</a></li>
							</ul>
     						<li><a class="dropdown-button" href="#!" data-activates="userSearch-p">
     						查找用户<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
							
							<!-- <li><a href="footer">页脚</a></li>
							<li><a href="mobile">手机</a></li> -->
						
						
					</ul>
				</div>
			</nav>
		</div>
		<div class="navbar-header light-blue lighten-1 z-depth-0" 
			style="min-height: 50px;">
			<nav class="light-blue lighten-1 z-depth-0">
			    <div class="nav-wrapper" style="margin-top:50px">
			    	<a href="#" data-activates="mobile-demo" class="button-collapse"></a>
				    <ul class="hide-on-med-and-down">
				    	<c:if test="${user.status == true}">
				    	<ul id="editself" class="dropdown-content">
						<li><a href="/article/addPage" class='blue-text'>纂写文章</a></li>
			      		<li class="divider" ></li>
			      		<li><a href="/user/" class='blue-text'>个人信息</a></li>
						</ul>
    					<li><a class="dropdown-button" href="#!" data-activates="editself">
    					个人操作<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
						</c:if>
				    	
				    	
				    	
				    	
			      		<ul id="articleSearch" class="dropdown-content">
						<li><a href="/?showType=bylike" class='blue-text'>点赞数</a></li>
			      		<li class="divider" ></li>
			      		<li><a href="/?showType=bytime" class='blue-text'>最新</a></li>
			      		<li><a href="/article/byuser" class='blue-text'>按用户</a></li>
						</ul>
    					<li><a class="dropdown-button" href="#!" data-activates="articleSearch">
    					查看文章<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
						<ul id="userSearch" class="dropdown-content">
						<li class="divider" ></li>
			     	<!--	<li><a href="/?showType=bytime" class='blue-text'>新加入</a></li> -->
			     		<li><a onClick="findUser()" class='blue-text'>精确查找</a></li>
						</ul>
    					<li><a class="dropdown-button" href="#!" data-activates="userSearch">
    					查找用户<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
				   </ul>
				    
			    </div>
		  	</nav>
		</div>
		

		<script>
			//isLogin();
			$('.dropdown-button').dropdown({
			    inDuration: 300,
			    outDuration: 225,
			    constrain_width: false, // Does not change width of dropdown to that of the activator
			    hover: false, // Activate on hover
			    gutter: 0, // Spacing from edge
			    belowOrigin: false // Displays dropdown below the button
			    }
			  );
			$(document).ready(function() {
				$(".button-collapse").sideNav();
			});
			$(".button-collapse").sideNav();
		</script>
	</header>
	<main>