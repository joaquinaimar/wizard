function getUserInfo() {

    Ext.Ajax.request({
        url : contextPath + '/wizardframework/UserManage/getUserInfo.do',
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).data;
                var userManageForm = Ext.getCmp("userManageFormPanel").getForm();
                userManageForm.findField("pkId").setValue(result.pkId);
                userManageForm.findField("user").setValue(result.userName);
            }
        }
    });
}

function submitUserManageFormPanel() {
    var userManageForm = Ext.getCmp("userManageFormPanel").getForm();
    if (!userManageForm.isValid()) {
        Ext.Msg.alert("错误", "请正确输入信息！");
        return;
    }

    if (userManageForm.findField("newPassword").getValue() != userManageForm.findField("confirmPassword").getValue()) {
        Ext.Msg.alert("错误", "两次密码输入不一致！");
        return;
    }

    userManageForm.submit({
        waitMsg : '正在修改密码...',
        url : contextPath + '/wizardframework/UserManage/modifyPassword.do',
        method : 'POST',
        success : function(form, action) {
            Ext.Msg.alert("提示", "处理成功：" + action.result.message, function() {
                logout();
            });
        },
        failure : function(form, action) {
            Ext.Msg.alert("提示", "处理出错：" + action.result.message, function() {
                resetUserManageFormPanel();
            });
        },
        scope : this
    });
}

function resetUserManageFormPanel() {
    Ext.getCmp("userManageFormPanel").getForm().reset();
    getUserInfo();
}

function logout() {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/Main/logout.do',
        method : 'POST',
        success : function(response, options) {
            window.top.location.href = contextPath;
        }
    });
}