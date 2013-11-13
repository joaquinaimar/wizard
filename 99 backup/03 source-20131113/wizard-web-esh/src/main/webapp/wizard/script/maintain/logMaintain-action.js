function submitLogSearchPanel() {
	var form = Ext.getCmp("logSearchPanel").getForm();

	var params = {
		logTitle : form.findField("logTitle").getValue(),
		userName : form.findField("userName").getValue(),
		logLevel : form.findField("logLevel").getValue(),
		logTimeStart : form.findField("logTimeStart").getValue(),
		logTimeEnd : form.findField("logTimeEnd").getValue(),
		start : 0,
		limit : 20
	};

	Ext.apply(logStore.proxy.extraParams, params);

	logStore.load();

}

function resetLogSearchPanel() {
    Ext.getCmp("logSearchPanel").getForm().reset();
}

function clearLogInfo() {
	Ext.Msg.confirm("提示", "确定清空日志表吗？", function(btn) {
		if ("yes" == btn) {
			Ext.Ajax.request({
				url : contextPath
						+ '/wizardframework/LogMaintain/clearLog.do',
				method : 'POST',
				success : function(response, options) {
					Ext.Msg.alert("结果",
							Ext.JSON.decode(response.responseText).message);
					logStore.reload();
				}
			})
		}
		return;
	}, this);

}

function getRowClass(record, rowIndex, rowParams, store) {
	var level = record.data.LOG_LEVEL_VALUE;
	if ("fatal" == level) {
		return "red-font-row";
	} else if ("error" == level) {
		return "orange-font-row";
	} else if ("warn" == level) {
		return "blue-font-row";
	} else if ("debug" == level) {
		return "green-font-row";
	}
}