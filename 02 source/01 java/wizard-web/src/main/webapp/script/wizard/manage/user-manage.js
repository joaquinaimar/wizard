$(function() {
	$("#btnConfirm").bind("click", funConfirm);
	$("#btnSave").bind("click", funSave);
});

var funConfirm = function() {
	Wizard.ajaxSubmit("#confirmForm", {
		dataType : "json",
		timeout : 10000,
		loadstr : "正在验证，请稍候",
		success : function(responseText, statusText, xhr) {
			if (responseText.data) {
				var password = $('#confirmForm input[name=password]').val();
				$('#confirmForm').resetForm();
				$('#updateForm').resetForm();
				$('#userManage').modal({
					backdrop : 'static'
				});
				$('#userManage input[name=oldPassword]').val(password);
				funGetUserInfo();
			} else {
				Wizard.alertDanger("错误", "密码不正确！");
			}
		}
	});
};
var funGetUserInfo = function() {
	Wizard
			.ajax({
				type : "post",
				dataType : "json",
				url : contextPath
						+ "/wizard/manage/user-manager/getUserInfoById.do",
				success : function(obj) {
					var data = obj.data;
					if (data) {
						$('#userManage input[name=username]')
								.val(data.username);
						$('#userManage textarea[name=userDetail]').val(
								data.userDetail);
					}
				}
			});
};
var funSave = function() {
	if ($('#updateForm input[name=newPassword]').val() !== $(
			'#updateForm input[name=confirmPassword]').val()) {
		Wizard.alertDanger("错误", "两次密码不一致！");
		return;
	}
	Wizard.ajaxSubmit("#updateForm", {
		dataType : "json",
		timeout : 10000,
		loadstr : "正在保存，请稍候",
		success : function(responseText, statusText, xhr) {
			if (responseText.data) {
				Wizard.alertSuccess("成功", "保存成功！");
				$('#userManage').modal('hide');
			} else {
				Wizard.alertDanger("错误", "保存失败！");
			}
		}
	});
};