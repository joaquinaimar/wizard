function expand(tool, type) {
    var panel = Ext.getCmp(type + "SearchPanel");
    if ("down" == tool.type) {
        panel.show();
        tool.setType("up");
    } else if ("up" == tool.type) {
        panel.hide();
        tool.setType("down");
    }
}

function maximize(tool, panel) {

    var panels = [ "org", "role", "user" ];

    if ("maximize" == tool.type) {
        for ( var i = 0; i < 3; i++) {
            if (panels[i] != panel) {
                Ext.getCmp(panels[i] + "ManagePanel").hide();
            }
        }
        tool.setType("restore");
    } else if ("restore" == tool.type) {
        for ( var i = 0; i < 3; i++) {
            if (panels[i] != panel) {
                Ext.getCmp(panels[i] + "ManagePanel").show();
            }
        }
        tool.setType("maximize");
    }
}

function submitOrgSearchPanel() {
    var form = Ext.getCmp("orgSearchPanel").getForm();
    var params = {
        orgName : form.findField("orgName").getValue(),
        start : 0,
        limit : 20
    };
    Ext.apply(orgStore.proxy.extraParams, params);
    orgStore.load();
}

function resetOrgSearchPanel() {
    Ext.getCmp("orgSearchPanel").getForm().reset();
}

function addOrg() {
    orgRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        orgName : '',
        orgDetail : ''
    };

    var row = orgStore.data.length;

    orgStore.add(data);
    orgRowEditingPlugin.startEdit(row, 0);
}

function reloadOrgInfo() {
    orgStore.reload();
    comboOrgStore.reload();
    roleStore.reload();
    comboRoleStore.reload();
    userStore.reload();
}

function insertOrgInfo(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/OrgManage/insertOrgInfo.do',
        params : {
            orgName : data.get('orgName'),
            orgDetail : data.get('orgDetail')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadOrgInfo();
            }
        }
    });
}

function updateOrgInfo(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/OrgManage/updateOrgInfo.do',
        params : {
            pkId : data.get('pkId'),
            orgName : data.get('orgName'),
            orgDetail : data.get('orgDetail')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadOrgInfo();
            }
        }
    });
}

function deleteOrgInfo() {

    var rows = Ext.getCmp("orgGridPanel").getSelectionModel().getSelection();
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
            deleteOrg(pkIds);
        }
    }, this);

}

function deleteOrg(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/OrgManage/deleteOrgInfo.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                reloadOrgInfo();
            }
        }
    });
}