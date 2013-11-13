function getAuthority() {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/PermissionManage/getAuthority.do',
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                authorityObj = Ext.JSON.decode(response.responseText).data;
            }
        }
    });
}

function saveAuthorityObj() {
    if (!roleId) {
        return;
    }
    var rows = Ext.getCmp("MenuGridPanel").getSelectionModel().getSelection();
    authorityObj[roleId] = [];
    for ( var i = 0; i < rows.length; i++) {
        authorityObj[roleId].push(rows[i].get("pkId"));
    }

}

function checkAuthority() {
    var authorityList = authorityObj[roleId];
    var sm = Ext.getCmp("MenuGridPanel").getSelectionModel();
    sm.deselectAll();
    menuStore.each(function(rec) {
        if (authorityList && authorityList.length) {
        for ( var i = 0; i < authorityList.length; i++) {
            if (rec.get("pkId") == authorityList[i]) {
                sm.select(rec, true);
                break;
            }
        }
        }
    });
}

function saveAuthority() {
    saveAuthorityObj();
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/PermissionManage/saveAuthority.do',
        method : 'POST',
        params : {
            authority : Ext.JSON.encode(authorityObj)
        },
        success : function(response, options) {
            Ext.Msg.alert('提示', '保存成功！');
        }
    });
}