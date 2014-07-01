function submitRoleSearchPanel() {
    var form = Ext.getCmp("roleSearchPanel").getForm();
    var params = {
        roleName : form.findField("roleName").getValue(),
        fkOrgId : selectId.org,
        start : 0,
        limit : 20
    };
    Ext.apply(roleStore.proxy.extraParams, params);
    roleStore.load();
}

function resetRoleSearchPanel() {
    Ext.getCmp("roleSearchPanel").getForm().reset();
}

function addRole() {
    roleRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        roleName : '',
        roleDetail : '',
        fkOrgId : ''
    };

    var row = roleStore.data.length;

    roleStore.add(data);
    roleRowEditingPlugin.startEdit(row, 0);
}

function reloadRoleInfo() {
    roleStore.reload();
    comboRoleStore.reload();
    userStore.reload();
}

function insertRoleInfo(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/RoleManage/insertRoleInfo.do',
        params : {
            fkOrgId : data.get('fkOrgId'),
            roleName : data.get('roleName'),
            roleDetail : data.get('roleDetail')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadRoleInfo();
            }
        }
    });
}

function updateRoleInfo(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/RoleManage/updateRoleInfo.do',
        params : {
            pkId : data.get('pkId'),
            fkOrgId : data.get('fkOrgId'),
            roleName : data.get('roleName'),
            roleDetail : data.get('roleDetail')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadRoleInfo();
            }
        }
    });
}

function deleteRoleInfo() {

    var rows = Ext.getCmp("roleGridPanel").getSelectionModel().getSelection();
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
            deleteRole(pkIds);
        }
    }, this);

}

function deleteRole(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/RoleManage/deleteRoleInfo.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadRoleInfo();
            }
        }
    });
}