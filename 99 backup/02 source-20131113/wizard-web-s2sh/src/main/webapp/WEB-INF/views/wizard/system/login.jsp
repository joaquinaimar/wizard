<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登陆画面</title>
	</head>
	<body>
		<s:form action="/login">
			<s:textfield name="user" label="用户名" />
			<s:textfield name="pwd" label="密  码" />
			<s:submit name="submit" value="提交" />
		</s:form>
	</body>
</html>