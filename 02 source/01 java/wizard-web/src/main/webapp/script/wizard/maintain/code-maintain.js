$(document).ready(function() {
	searchCodeType();
	$("#btnSearch").bind("click", function() {
		searchCodeType({
			content : $("#txtSearchContent").val()
		});
	});

	$("#btnInsertType").bind("click", function() {
		$("#frmCodeType")[0].reset();
		$("#frmCodeType input[name=pkId]").attr("readonly", false);
		$("#code-info-button").hide();
		$("#code-info-zone").hide();
		Wizard.showModal('#codeListModal');
	});

	$("#btnDeleteType").bind("click", deleteTypeById);

	$("#btnSaveType").bind("click", function() {
		var oType = ($("#frmCodeType input[name=pkId]").attr("readonly")) ? "update" : "insert";

		Wizard.ajaxSubmit("#frmCodeType", {
			url : contextPath + '/wizard/maintain/code-maintain/' + oType + 'Code.do',
			type : "POST",
			dataType : "json",
			timeout : 10000,
			loadstr : "正在保存，请稍候",
			success : function(responseText, statusText, xhr) {
				Wizard.closeModal("#codeListModal");
				searchCodeType({
					content : $("#txtSearchContent").val()
				});
				Wizard.alertSuccess("成功", "保存成功！");
			}
		});
	});

	$("#btnInsertCode").bind("click", function() {
		$("#frmCodeInfo")[0].reset();
		$("#frmCodeInfo input[name=pkId]").attr("readonly", false);
		$("#frmCodeInfo input[name=typeId]").val($("#frmCodeType input[name=pkId]").val());
		Wizard.showModal('#codeInfoModal');
	});

	$("#btnDeleteCode").bind("click", deleteCodeById);

	$("#btnSaveCode").bind("click", function() {
		var oType = ($("#frmCodeInfo input[name=pkId]").attr("readonly")) ? "update" : "insert";

		Wizard.ajaxSubmit("#frmCodeInfo", {
			url : contextPath + '/wizard/maintain/code-maintain/' + oType + 'Code.do',
			type : "POST",
			dataType : "json",
			timeout : 10000,
			loadstr : "正在保存，请稍候",
			success : function(responseText, statusText, xhr) {
				Wizard.closeModal("#codeInfoModal");
				searchCodeInfo({
					typeId : $("#frmCodeType input[name=pkId]").val()
				});
				Wizard.alertSuccess("成功", "保存成功！");
			}
		});
	});

});

var searchCodeType = function(pars) {
	var params = pars || {};
	Wizard.ajaxDataTable("#code-type", {
		url : contextPath + '/wizard/maintain/code-maintain/getCodeTypeList.do',
		params : params,
		pageList : [10, 20],
		sort : true,
		method : 'GET',
		reader : {
			totalProperty : 'totalCount',
			root : 'result'
		},
		fields : [{
			header : '#',
			name : 'pkId',
			type : 'checkbox'
		}, {
			header : 'Id',
			name : 'pkId'
		}, {
			header : '内容',
			name : 'content'
		}, {
			header : '操作',
			name : 'modify-type',
			value : '修改',
			type : 'button',
			cssClass : "btn btn-info btn-xs",
			handler : function(index, rowData) {
				Wizard.showModal('#codeListModal');
				$("#code-info-button").show();
				$("#code-info-zone").show();
				getCodeById(rowData.pkId, function(data) {
					$("#frmCodeType input[name=pkId]").val(data.data.pkId);
					$("#frmCodeType input[name=pkId]").attr("readonly", true);
					$("#frmCodeType input[name=content]").val(data.data.content);
				});
				searchCodeInfo({
					typeId : rowData.pkId
				});
			}
		}]
	});
};

var searchCodeInfo = function(pars) {
	var params = pars || {};
	Wizard.ajaxDataTable("#code-info", {
		url : contextPath + '/wizard/maintain/code-maintain/getCodeInfoList.do',
		params : params,
		pageList : [5],
		sort : true,
		method : 'GET',
		reader : {
			totalProperty : 'totalCount',
			root : 'result'
		},
		fields : [{
			header : '#',
			name : 'pkId',
			type : 'checkbox'
		}, {
			header : 'Id',
			name : 'pkId'
		}, {
			header : '内容',
			name : 'content'
		}, {
			header : '操作',
			name : 'modify-info',
			value : '修改',
			type : 'button',
			cssClass : "btn btn-info btn-xs",
			handler : function(index, rowData) {
				Wizard.showModal('#codeInfoModal');
				getCodeById(rowData.pkId, function(data) {
					$("#frmCodeInfo input[name=pkId]").val(data.data.pkId);
					$("#frmCodeInfo input[name=pkId]").attr("readonly", true);
					$("#frmCodeInfo input[name=typeId]").val(data.data.typeId);
					$("#frmCodeInfo input[name=content]").val(data.data.content);
				});
			}
		}]
	});
};

var getCodeById = function(id, success) {
	Wizard.ajax({
		type : 'post',
		loadstr : "请稍候",
		url : contextPath + '/wizard/maintain/code-maintain/getCodeById.do',
		data : {
			id : id
		},
		dataType : 'json',
		success : success
	});
};

var deleteTypeById = function() {

	var table = $("#code-type input[type=checkbox][name=checkbox-pkId]:checked");
	var ids = [];
	for (var i = 0; i < table.length; i++)
		ids.push(table[i].value);

	Wizard.ajax({
		type : 'POST',
		loadstr : "正在删除，请稍候",
		url : contextPath + '/wizard/maintain/code-maintain/deleteTypeById.do',
		data : {
			ids : ids
		},
		dataType : 'json',
		success : function(data) {
			searchCodeType({
				content : $("#txtSearchContent").val()
			});
			Wizard.alertSuccess("成功", "删除成功！");
		}
	});
};

var deleteCodeById = function() {

	var table = $("#code-info input[type=checkbox][name=checkbox-pkId]:checked");
	var ids = [];
	for (var i = 0; i < table.length; i++)
		ids.push(table[i].value);

	Wizard.ajax({
		type : 'POST',
		loadstr : "正在删除，请稍候",
		url : contextPath + '/wizard/maintain/code-maintain/deleteCodeById.do',
		data : {
			ids : ids
		},
		dataType : 'json',
		success : function(data) {
			searchCodeInfo({
				typeId : $("#frmCodeType input[name=pkId]").val()
			});
			Wizard.alertSuccess("成功", "删除成功！");
		}
	});
};
