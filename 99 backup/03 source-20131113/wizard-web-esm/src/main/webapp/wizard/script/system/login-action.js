function submitLoginFormPanel() {

    var loginForm = Ext.getCmp("loginFormPanel").getForm();

    if (!loginForm.isValid()) {
        Ext.Msg.alert("错误", "请正确输入信息！");
        return;
    }
    loginForm.submit();
}

function resetLoginFormPanel() {
    Ext.getCmp("loginFormPanel").getForm().reset();
}