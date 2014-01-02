$(function() {
	$("#btnConfirm").bind("click", funConfirm);
});

var funConfirm = function() {
	Wizard.ajaxSubmit("#confirmForm", {
		dataType : "json",
		timeout : 10000,
		loadstr : "正在验证，请稍候",
		success : function(responseText, statusText, xhr) {
			if (responseText.data) {
				$('#userManage').modal({ backdrop: 'static' });
			} else {
				Wizard.alert("错误", "密码不正确！");
			}
		}
	});
};