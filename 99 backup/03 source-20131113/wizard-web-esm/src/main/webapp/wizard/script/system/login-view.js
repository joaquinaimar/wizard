Ext.ns("wizard.framework.login");

Ext.onReady(function() {
    Ext.QuickTips.init();
    var loginPanel = new wizard.framework.login.LoginPanel();

    new Ext.Viewport({
        layout : 'fit',
        items : loginPanel
    });

    Ext.getCmp("user").focus(true);

    Ext.getCmp("user").setValue('zhanglizhi');
    Ext.getCmp("pwd").setValue('042888');
});

wizard.framework.login.LoginPanel = Ext.extend(Ext.Panel, {
    id : 'loginPanel',
    bodyStyle : 'background: url(' + contextPath + '/wizard/image/zard-forever.jpg) no-repeat fixed center',
    layout : {
        type : 'hbox',
        align : 'middle',
        pack : 'center'
    },
    constructor : function(config) {
        var loginFormPanel = new wizard.framework.login.LoginFormPanel();
        var group = {
            items : loginFormPanel
        };

        wizard.framework.login.LoginPanel.superclass.constructor.call(this, group);
    }

});

wizard.framework.login.LoginFormPanel = Ext.extend(Ext.form.FormPanel, {
    id : 'loginFormPanel',
    title : '登录',
    height : 150,
    width : 300,
    labelWidth : 60,
    labelAlign : 'right',
    frame : true,
    standardSubmit : true,
    url : contextPath + '/wizardframework/Login/login.do',
    method : 'post',
    html : '<div class="loginInfo">' + loginInfo + '</div>',
    items : [ {
        xtype : 'textfield',
        id : 'user',
        name : 'user',
        fieldLabel : '用户名',
        allowBlank : false
    }, {
        xtype : 'textfield',
        id : 'pwd',
        name : 'pwd',
        fieldLabel : '密码',
        inputType : 'password',
        allowBlank : false
    }, {
        xtype : 'combobox',
        editable : false,
        fieldLabel : '数据源',
        id : 'dataSource',
        name : 'dataSource',
        queryMode : 'local',
        triggerAction : 'all',
        store : getDataSources(),
        valueNotFoundText : 0,
        valueField : 'valueField',
        displayField : 'displayField'
    } ],
    buttons : [ {
        text : '提交',
        handler : function() {
            submitLoginFormPanel();
        }
    }, {
        text : '取消',
        handler : function() {
            resetLoginFormPanel();
        }
    } ]
});
