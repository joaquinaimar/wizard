function submitUserSearchPanel() {
    var form = Ext.getCmp("userSearchPanel").getForm();
    var params = {
        userName : form.findField("userName").getValue(),
        fkRoleId : selectId.role,
        start : 0,
        limit : 20
    };
    Ext.apply(userStore.proxy.extraParams, params);
    userStore.load();
}

function resetUserSearchPanel() {
    Ext.getCmp("userSearchPanel").getForm().reset();
}

function addUser() {
    userRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        userName : '',
        userDetail : '',
        fkRoleId : ''
    };

    var row = userStore.data.length;

    userStore.add(data);
    userRowEditingPlugin.startEdit(row, 0);
}

function reloadUserInfo() {
    userStore.reload();
}

function insertUserInfo(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/UserManage/insertUserInfo.do',
        params : {
            fkRoleId : data.get('fkRoleId'),
            userName : data.get('userName'),
            userDetail : data.get('userDetail')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadUserInfo();
            }
        }
    });
}

function updateUserInfo(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/UserManage/updateUserInfo.do',
        params : {
            pkId : data.get('pkId'),
            fkRoleId : data.get('fkRoleId'),
            userName : data.get('userName'),
            userDetail : data.get('userDetail')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadUserInfo();
            }
        }
    });
}

function deleteUserInfo() {

    var rows = Ext.getCmp("userGridPanel").getSelectionModel().getSelection();
    var length = rows.length;
    if (0 == length) {
        Ext.Msg.alert("提示", "请至少选择一条！");
        return;
    }
    Ext.Msg.confirm('删除', '确定删除吗？', function(btn) {
        if (btn == 'yes') {
            var pkIds = [];
            for ( var i = 0; i < length; i++) {
                pkIds.push(rows[i].get("pkId"));
            }
            deleteUser(pkIds);
        }
    }, this);

}

function deleteUser(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/UserManage/deleteUserInfo.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadUserInfo();
            }
        }
    });
}