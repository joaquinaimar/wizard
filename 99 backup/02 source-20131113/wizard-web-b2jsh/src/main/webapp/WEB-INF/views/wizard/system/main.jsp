<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>wizard-web</title>
<%@ include file="/lib/lib.html"%>
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/page/wizard/style/system/main.css" />

<script type="text/javascript"
	src="<%=application.getContextPath()%>/page/wizard/script/system/main.js"></script>
</head>
<body>
	<div id="main-head" class="wizard-bg">
		<div id="title-userinfo">欢迎${userInfo.roleName}-${userInfo.userName}进入系统</div>
		<svg id="title-svg" xmlns="http://www.w3.org/2000/svg">
			<text x="450" y="30" font-size="40" font-family="Script MT Bold"
				fill="yellow" stroke="red">
				Welcome Wizard Web
			</text>
		</svg>
	</div>
	<div id="main">
		<div id="main-menu" class="wizard-bg">
			<h4>菜单</h4>
			<div id="menu">
				<wizard:menu />
			</div>
		</div>
		<div id="main-main" class="wizard-bg">
			<ul id="tab-title" class="nav nav-tabs"></ul>
			<div id="tab-body" class="tab-content"></div>
		</div>
	</div>
	<div id="main-foot" class="wizard-bg">
		<h4>版权所有</h4>
	</div>
</body>