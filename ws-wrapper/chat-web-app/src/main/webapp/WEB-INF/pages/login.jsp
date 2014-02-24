<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mr.zoom
  Date: 23.02.14
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Login to chat</title>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.xml2json.js" />"></script>
    <script src="<c:url value="/resources/js/json2xml.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-cookie.js" />"></script>
    <script src="<c:url value="/resources/js/login.js" />"></script>
    <%--<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>--%>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<section class="container">
    <div class="login">
        <h1>Login to Chat Web app</h1>
            <p><input id="login" type="text" name="login" value="" placeholder="Username or Email"></p>
            <p></p>
            <p class="remember_me">
                <label>
                    <p id="info">Start type login</p>
                </label>
            </p>
            <p class="submit"><input id="signin" type="submit" name="commit" value="Login"></p>
    </div>

</section>

<section class="about">
    <p class="about-author">
        &copy; 2014 Oleg Burshinov
</section>
</body>
</html>
