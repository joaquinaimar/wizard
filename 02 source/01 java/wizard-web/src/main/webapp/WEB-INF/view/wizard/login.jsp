<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<header class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<%=application.getContextPath()%>/main">Wizard</a>
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
		<div class="col-md-6 col-md-offset-1">
			<div id="carousel-example-captions" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-captions" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-captions" data-slide-to="1"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img
							src="${pageContext.request.contextPath}/resource/image/login/buffon.jpg"
							alt="Gianluigi Buffon">
						<div class="carousel-caption">
							<h3>Gianluigi Buffon</h3>
							<p>吉安路易吉·布冯</p>
						</div>
					</div>
					<div class="item">
						<img
							src="${pageContext.request.contextPath}/resource/image/login/pirlo.jpg"
							alt="Andrea Pirlo">
						<div class="carousel-caption">
							<h3>Andrea Pirlo</h3>
							<p>安德雷·皮尔洛</p>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#carousel-example-captions"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span>
				</a> <a class="right carousel-control" href="#carousel-example-captions"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>
		</div>
		<div class="col-md-4 col-md-offset-1">
			<form id="loginForm" class="form-signin" method="post"
				action="wizard/login/login.do">
				<fieldset>
					<legend>
						<h3>请登录</h3>
					</legend>
					<div class="form-row">
						<label for="username"><h4>用户名:</h4></label><input type="text" id="username"
							name="username" class="form-control" placeholder="用户名"
							autocomplete="off" />
					</div>
					<div class="form-row">
						<label for="password"><h4>密码:</h4></label> <input type="password"
							id="password" name="password" class="form-control"
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