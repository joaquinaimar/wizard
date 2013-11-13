function getFirstPage() {

    Ext.Ajax.request({
        url : contextPath + '/wizardframework/MenuManage/getFirstPage.do',
        method : 'GET',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                var userManageForm = Ext.getCmp("welocomePanel").getForm();
                userManageForm.findField("welcomePath").setValue(result);
            }
        }
    });

}

function updateFirstPage() {

    var welocomeForm = Ext.getCmp("welocomePanel").getForm();

    if (!welocomeForm.isValid()) {
        Ext.Msg.alert("错误", "请正确输入信息！");
        return;
    }

    welocomeForm.submit({
        waitMsg : '正在修改首页...',
        url : contextPath + '/wizardframework/MenuManage/updateFirstPage.do',
        method : 'POST',
        success : function(form, action) {
            Ext.Msg.alert("提示", action.result.message + '，在下次登录时生效！');
        },

        scope : this
    });

}

function loadChild(pPkId) {
    var params = {
        pPkId : pPkId,
        start : 0,
        limit : 20
    };

    Ext.apply(childMenuStore.proxy.extraParams, params);

    childMenuStore.load();
}

function insertMenu(data, flg) {

    Ext.Ajax.request({
        url : contextPath + '/wizardframework/MenuManage/insertMenu.do',
        params : {
            pkId : data.get('newId'),
            pPkId : data.get('pPkId'),
            menuName : data.get('menuName'),
            menuPath : data.get('menuPath'),
            display : data.get('display')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '，在下次登录时生效！');
                if (flg) {
                    parentMenuStore.reload();
                } else {
                    childMenuStore.reload();
                }
            }
        }
    });

}

function updateParentMenu(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/MenuManage/updateParentMenu.do',
        params : {
            pkId : data.get('pkId'),
            newId : data.get('newId'),
            menuName : data.get('menuName'),
            display : data.get('display')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '，在下次登录时生效！');
                parentMenuStore.reload();
                if (data.get('pkId') != data.get('newId')) {
                    loadChild(data.get('newId'));
                }
            }
        }
    });
}

function updateChildMenu(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/MenuManage/updateChildMenu.do',
        params : {
            pkId : data.get('pkId'),
            newId : data.get('newId'),
            menuName : data.get('menuName'),
            menuPath : data.get('menuPath'),
            display : data.get('display')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '，在下次登录时生效！');
                childMenuStore.reload();
            }
        }
    });
}

function addParent() {
    parentMenuRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        newId : '',
        pPkId : '0',
        menuName : '',
        display : '1'
    };

    var row = parentMenuStore.data.length;

    parentMenuStore.add(data);
    parentMenuRowEditingPlugin.startEdit(row, 0);
}

function addChild() {

    var pPkId = childMenuStore.proxy.extraParams.pPkId;

    if (!pPkId) {
        return;
    }

    childMenuRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        newId : '',
        pPkId : pPkId,
        menuName : '',
        menuPath : '',
        display : '1'
    };

    var row = childMenuStore.data.length;

    childMenuStore.add(data);
    childMenuRowEditingPlugin.startEdit(row, 0);
}

function deleteParent() {

    var rows = Ext.getCmp("parentMenuGridPanel").getSelectionModel().getSelection();
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
            deleteParentMenu(pkIds);
        }
    }, this);

}

function deleteParentMenu(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/MenuManage/deleteParentMenu.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '，在下次登录时生效！');
                parentMenuStore.reload();
                childMenuStore.reload();
            }
        }
    });
}

function deleteChild() {

    var rows = Ext.getCmp("childMenuGridPanel").getSelectionModel().getSelection();
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
            deleteChildMenu(pkIds);
        }
    }, this);

}

function deleteChildMenu(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/MenuManage/deleteChildMenu.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '，在下次登录时生效！');
                childMenuStore.reload();
            }
        }
    });
}

function getDisplay(value) {
    if (1 == value) {
        return "显示";
    } else {
        return "隐藏";
    }
}