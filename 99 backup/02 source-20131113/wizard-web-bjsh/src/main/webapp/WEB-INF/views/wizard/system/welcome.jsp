<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>wizard-web</title>
<%@ include file="/lib/lib.html"%>
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/lib/jMonthCalendar-1.3.2-beta2/css/core.css" />
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/lib/jMonthCalendar-1.3.2-beta2/css/cupertino/jquery-ui.cupertino.css" />
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/page/wizard/style/system/welcome.css" />

<script type="text/javascript"
	src="<%=application.getContextPath()%>/lib/jMonthCalendar-1.3.2-beta2/js/jMonthCalendar.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/page/wizard/script/system/welcome.js"></script>
</head>
<body>
	<div class="div-body wizard-bg">
		<div id="jMonthCalendar"></div>
	</div>
</body>