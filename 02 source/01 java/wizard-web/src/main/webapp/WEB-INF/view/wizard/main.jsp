<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<header class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/main">Wizard</a>
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
			<form class="navbar-form navbar-right">
				<button id="btnLoginUser" type="button" class="btn btn-default"
					data-toggle="popover" data-placement="bottom" title="用户详细信息"
					data-content="${sessionScope.loginInfo.detail}">${sessionScope.loginInfo.username}</button>
				<button id="btnLogout" type="button" class="btn btn-warning">退出</button>
			</form>
			<p class="navbar-text navbar-right">用户名：</p>
		</div>
	</div>
</header>
<div id="doc">
	<div id="doc-menu" class="well well-sm">
		<div class="panel-heading">
			<h4>菜单</h4>
		</div>
		<div class="panel-group" id="menu"></div>
	</div>
	<div id="doc-tab">
		<ul id="tab-title" class="nav nav-tabs"></ul>
		<div id="tab-body" class="tab-content"></div>
	</div>
</div>
<div id="foot" class="well well-sm">
	<h4>版权所有</h4>
</div>