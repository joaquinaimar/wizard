Wizard = {
	ajaxSubmit : function(form, option) {
		var tagStr = '<div id="loading-overlay" class="wizard-overlay"></div>';
		tagStr = tagStr
				+ '<div class="alert alert-info wizard-loading"><div class="progress progress-striped active" style="margin-top:10px;">';
		tagStr = tagStr
				+ '<div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;">';
		tagStr = tagStr + '</div></div><div class="wizard-dialog-content">'
				+ (option.loadstr || 'loading') + '...</div></div>';

		$(document.body).append(tagStr);
		$(".alert").alert();
		var success = option.success;
		option.success = function(responseText, statusText, xhr) {
			success(responseText, statusText, xhr);
			$(".wizard-loading").remove();
			$("#loading-overlay").remove();
		};
		option.error = option.error || function() {
			$(".wizard-loading").remove();
			$("#loading-overlay").remove();
			Wizard.alert("异常", "与服务器通信异常！");
		};

		$(form).ajaxSubmit(option);
	},
	alert : function(title, content, fnClose) {

		var options = {
			fnClose : fnClose
		};

		var tagStr = '<div id="alert-overlay" class="wizard-overlay"></div>';
		tagStr = tagStr
				+ '<div class="alert alert-danger fade in wizard-dialog-win">';
		tagStr = tagStr
				+ '<button type="button" class="close" data-dismiss="alert">&times;</button>';
		tagStr = tagStr + '<h4 class="alert-heading">' + title + '</h4>';
		tagStr = tagStr + '<div class="wizard-dialog-content">' + content
				+ '</div></div>';
		$(document.body).append(tagStr);

		$("[data-dismiss=alert]").click(function() {
			$("#alert-overlay").remove();
			if (fnClose) {
				options.fnClose();
			}
		});

		$(".alert").alert();
	}

};