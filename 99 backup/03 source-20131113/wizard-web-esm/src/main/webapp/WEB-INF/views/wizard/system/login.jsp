<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ include file="/lib/lib.html" %>

<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/wizard/style/system/login.css"/>

<script type="text/javascript">

<%
    String loginInfo = (String)session.getAttribute("loginInfo");
    loginInfo = (null == loginInfo) ? "" : loginInfo;
%>
var loginInfo = '<%=loginInfo %>';
</script>
<script type="text/javascript" src="<%=application.getContextPath()%>/wizard/script/system/login-action.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/wizard/script/system/login-view.js"></script>

<title>登录</title>