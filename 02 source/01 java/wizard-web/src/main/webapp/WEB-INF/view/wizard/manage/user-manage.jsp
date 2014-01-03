<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-3">
			<form id="confirmForm" class="form-confirm" method="post"
				action="${pageContext.request.contextPath}/wizard/manage/user-manager/confirmPassword.do">
				<div class="input-group">
					<span class="input-group-addon">请输入密码</span> <input type="password"
						name="password" class="form-control">
					<div class="input-group-btn">
						<button id="btnConfirm" class="btn btn-primary" type="button">确认</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="userManage" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">用户信息管理</h4>
			</div>
			<div class="modal-body">
				<form id="updateForm" class="form-horizontal form-update"
					method="post"
					action="${pageContext.request.contextPath}/wizard/manage/user-manager/updateUser.do">
					<input type="hidden" name="oldPassword" class="form-control">
					<div class="form-group">
						<div class="input-group col-md-6 col-md-offset-2">
							<span class="input-group-addon">用户名</span> <input type="text"
								name="username" class="form-control" readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group col-md-6 col-md-offset-2">
							<span class="input-group-addon">新密码</span> <input type="password"
								name="newPassword" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group col-md-6 col-md-offset-2">
							<span class="input-group-addon">确认密码</span> <input
								type="password" name="confirmPassword" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group col-md-8 col-md-offset-2">
							<span class="input-group-addon">详细信息</span>
							<textarea name="userDetail" class="form-control no-resize"
								rows="5"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button id="btnSave" class="btn btn-primary" type="button">保存</button>
				<button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>