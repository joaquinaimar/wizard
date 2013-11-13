<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<%@ include file="/lib/lib.html"%>
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/page/wizard/style/system/login.css" />

<script type="text/javascript"
	src="<%=application.getContextPath()%>/page/wizard/script/system/login.snow.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/page/wizard/script/system/login.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="login-title"></div>
		</div>
		<div class="row-fluid login-center">
			<div class="col-sm-4 col-md-offset-8 login-login">
				<form id="loginForm" class="form-signin" method="post"
					action="wizardframework/Login/login.do">
					<fieldset>
						<legend>
							<span class="form-signin-heading">请登录</span>
						</legend>
						<div>
							<input type="text" name="user" class="form-control"
								placeholder="用户名" />
						</div>
						<div>
							<input type="password" name="pwd" class="form-control"
								placeholder="密码" />
						</div>
						<button id="btnLogin" class="btn btn-primary" type="button">登录</button>
						<button id="btnReset" class="btn btn-primary" type="reset">重置</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>