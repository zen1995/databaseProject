<%--
  Created by IntelliJ IDEA.
  User: czn
  Date: 11/11/2015
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >

<html >
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
  <link rel="stylesheet" href="/resource/static/css/blog.css"/>
  <%@include file="/WEB-INF/views/resource/materializeResource.jsp"%>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!--
  <meta property="wb:webmaster" content="89dfa924841825c4" />-->

</head>
<body >
<header ng-controller = "header" style="margin-bottom: 100px">
  <div class="navbar-fixed" style="position: fixed">
    <nav class="light-blue lighten-1 z-depth-0" role="navigation" style="background-image: url('/resource/static/img/headerBackground.png')">
      <div class="nav-wrapper container ">
        <a id="logo-container" href="/" class="brand-logo navbar-left" >utopiaLab</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse">
          <i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down"><!--hide-on-med-and-down  right hide-on-small-only-->
          <li><a  id = "loginStatus" href="/user/" ></a></li>
          <li ><a  id = "registerBtn" href="/user/register" >register</a></li>
          <li id = "signInBtn" ><a href="/user/login" >sign in</a></li>
          <li  id = "signOutBtn" ><a href="#">sign out</a></li><!--onclick="signOut()"-->
        </ul>
        <ul class="side-nav" id="mobile-demo">
          <li><a  href="/user/" ></a></li>
          <li ><a   href="/user/register" >register</a></li>
          <li  ><a href="/user/login" >sign in</a></li>
          <li  ><a href="#">sign out</a></li><!--onclick="signOut()"-->
        </ul>
      </div>

    </nav>
  </div>
  <div class="navbar-header light-blue lighten-1 z-depth-1" style="min-height: 150px;background-image: url('/resource/static/img/headerBackground.png')">
    <div class="container" >
      <!--<h1 style="margin-top: 0px; color: white" >Utopialab</h1>  position: absolute;-->
    </div>
  </div>
  
  <script>
    //isLogin();

    $(document).ready(function(){
      $(".button-collapse").sideNav();
    });
  </script>
</header>
<main>

