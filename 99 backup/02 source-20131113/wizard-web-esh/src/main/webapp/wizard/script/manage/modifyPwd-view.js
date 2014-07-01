Ext.ns("wizard.framework.userManage");

Ext.onReady(function() {
    Ext.QuickTips.init();
    var userManagePanel = new wizard.framework.userManage.UserManagePanel();

    new Ext.Viewport({
        layout : 'fit',
        items : userManagePanel
    });

    getUserInfo();

});

wizard.framework.userManage.UserManagePanel = Ext
    .extend(
        Ext.Panel,
        {
            id : 'userManagePanel',
            layout : {
                type : 'hbox',
                align : 'middle',
                pack : 'center'
            },
            frame : true,
            constructor : function(config) {
                var userManageFormPanel = new wizard.framework.userManage.UserManageFormPanel();
                var group = {
                    items : userManageFormPanel
                };

                wizard.framework.userManage.UserManagePanel.superclass.constructor
                    .call(this, group);
            }

        });

wizard.framework.userManage.UserManageFormPanel = Ext.extend(
    Ext.form.FormPanel, {
        id : 'userManageFormPanel',
        title : '修改密码',
        height : 250,
        width : 300,
        labelWidth : 60,
        labelAlign : 'right',
        frame : true,
        items : [ {
            xtype : 'textfield',
            id : 'pkId',
            name : 'pkId',
            fieldLabel : '用户ID',
            readOnly : true
        }, {
            xtype : 'textfield',
            id : 'user',
            name : 'user',
            fieldLabel : '用户名',
            readOnly : true
        }, {
            xtype : 'textfield',
            id : 'oldPassword',
            name : 'oldPassword',
            fieldLabel : '旧密码',
            inputType : 'password',
            allowBlank : false
        }, {
            xtype : 'textfield',
            id : 'newPassword',
            name : 'newPassword',
            fieldLabel : '新密码',
            inputType : 'password',
            allowBlank : false
        }, {
            xtype : 'textfield',
            id : 'confirmPassword',
            name : 'confirmPassword',
            fieldLabel : '确认密码',
            inputType : 'password',
            allowBlank : false
        } ],
        buttons : [ {
            text : '提交',
            handler : function() {
                submitUserManageFormPanel();
            }
        }, {
            text : '取消',
            handler : function() {
                resetUserManageFormPanel();
            }
        } ]

    });