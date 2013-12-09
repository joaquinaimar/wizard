Wizard = {
	ajaxSubmit : function(form, option) {
		var id = "loading-win-" + Math.floor(Math.random() * 1000000);
		var tagStr = '<div id="' + id
				+ '" class="modal fade" role="dialog" aria-hidden="true">';
		tagStr += '<div class="modal-dialog">';
		tagStr += '<div class="modal-content alert alert-info">';
		tagStr += '<div class="modal-header">';
		tagStr += '<div><h4>' + (option.loadstr || 'loading')
				+ '···</h4></div>';
		tagStr += '</div>';
		tagStr += '<div class="modal-body">';
		tagStr += '<div class="progress progress-striped active">';
		tagStr += '<div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;">';
		tagStr += '</div>';
		tagStr += '</div></div></div></div>';

		$(document.body).append(tagStr);

		$('#' + id).modal({
			backdrop : 'static'
		});

		var success = option.success;
		option.success = function(responseText, statusText, xhr) {
			success(responseText, statusText, xhr);
			$('#' + id).modal('hide');
			setTimeout('$("#' + id + '").remove()', 2000);
		};
		option.error = option.error || function() {
			$('#' + id).modal('hide');
			setTimeout('$("#' + id + '").remove()', 2000);
			Wizard.alertDanger("异常", "与服务器通信异常！");
		};

		$(form).ajaxSubmit(option);
	},
	alertSuccess : function(title, content, fnClose) {
		Wizard.alert(title, content, "success", fnClose);
	},
	alertInfo : function(title, content, fnClose) {
		Wizard.alert(title, content, "info", fnClose);
	},
	alertWarning : function(title, content, fnClose) {
		Wizard.alert(title, content, "warning", fnClose);
	},
	alertDanger : function(title, content, fnClose) {
		Wizard.alert(title, content, "danger", fnClose);
	},
	alert : function(title, content, type, fnClose) {

		var id = "alert-win-" + Math.floor(Math.random() * 1000000);
		var options = {
			fnClose : fnClose
		};

		var tagStr = '<div id="' + id
				+ '" class="modal fade" role="dialog" aria-hidden="true">';
		tagStr += '<div class="modal-dialog wizard-alert">';
		tagStr += '<div class="modal-content alert alert-' + (type || 'danger')
				+ '">';
		tagStr += '<div class="modal-header">';
		tagStr += '<button type="button" class="close" data-dismiss="modal">&times; </button>';
		tagStr += '<div><h4><span class="glyphicon glyphicon-warning-sign"></span>'
				+ title + '</h4></div>';
		tagStr += '</div>';
		tagStr += '<div class="modal-body">';
		tagStr += '<div>' + content + '</div>';
		tagStr += '</div></div></div></div>';
		$(document.body).append(tagStr);

		$("[data-dismiss=modal]").click(function() {
			$('#' + id).modal('hide');
			setTimeout('$("#' + id + '").remove()', 2000);
			if (fnClose)
				options.fnClose();
		});

		$('#' + id).modal({
			backdrop : 'static'
		});
	}

};