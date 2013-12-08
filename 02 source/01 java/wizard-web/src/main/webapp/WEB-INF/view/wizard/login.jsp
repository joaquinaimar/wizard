<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<header class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<%=application.getContextPath()%>">Wizard</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">语言<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">English</a></li>
						<li><a href="#">简体中文</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</header>
<div class="container">
	<div class="row bs-doc">
		<div class="col-md-8 col-md-offset-1">
			<div id="Banner_simple">
				<ul class="Banner_list">
					<li
						data-bottom-thumb="${pageContext.request.contextPath}/resource/image/login/buffon.jpg"
						data-text-id="#buffon"><img
						src="${pageContext.request.contextPath}/resource/image/login/buffon.jpg"
						alt="" /></li>
					<li
						data-bottom-thumb="${pageContext.request.contextPath}/resource/image/login/pirlo.jpg"
						data-text-id="#pirlo"><img
						src="${pageContext.request.contextPath}/resource/image/login/pirlo.jpg"
						alt="" /></li>
				</ul>
			</div>
		</div>
		<div class="col-md-3">
			<form id="loginForm" class="form-signin" method="post"
				action="wizardframework/login/login.do">
				<fieldset>
					<legend>
						<h3>请登录</h3>
					</legend>
					<div class="form-row">
						<input type="text" name="username" class="form-control"
							placeholder="用户名" autocomplete="off" />
					</div>
					<div class="form-row">
						<input type="password" name="password" class="form-control"
							placeholder="密码" autocomplete="off" />
					</div>
					<div class="form-row">
						<button id="btnLogin" class="btn btn-primary" type="button">登录</button>
						<button id="btnReset" class="btn btn-primary" type="reset">重置</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>