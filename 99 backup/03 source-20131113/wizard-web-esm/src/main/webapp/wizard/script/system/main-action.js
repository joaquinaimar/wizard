function getFirstPage() {

	Ext.Ajax.request({
		url : contextPath
				+ '/wizardframework/MenuManage/getFirstPage.do',
		method : 'GET',
		success : function(response, options) {
			var firstPage = Ext.JSON.decode(response.responseText).message;
			addTabItem('首页', firstPage, false);
		}
	});

}

function showModify() {
	addTabItem("修改密码", "wizard/manage/modifyPwd");
}

function logout(flg) {

	Ext.Msg.confirm("提示", "确定要退出吗？", function(btn) {
		if ("yes" == btn) {
			Ext.Ajax
					.request({
						url : contextPath
								+ '/wizardframework/Main/logout.do',
						method : 'POST',
						success : function(response, options) {
							if (flg) {
								window.location.href = contextPath;
							}
						}
					})
		}
		return;
	}, this);

}

function addTabItem(title, url, flg) {

	if (Ext.getCmp(url)) {
		Ext.getCmp(url).show();
		return;
	}

	var item = {
		title : title,
		id : url,
		closable : flg,
		autoEl : {
			tag : 'iframe',
			name : url,
			frameBorder : 0,
			src : contextPath + "/" + url
		}
	};
	mainTabPanel.add(item).show();
}