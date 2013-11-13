Ext.ns("wizard.framework.permissionManage");

var authorityObj = {};
var roleId;

Ext
        .onReady(function() {
            Ext.QuickTips.init();
            var memuManagePanel = new wizard.framework.permissionManage.PermissionManagePanel();

            new Ext.Viewport({
                layout : 'fit',
                items : memuManagePanel
            });
            getAuthority();
        });

wizard.framework.permissionManage.PermissionManagePanel = Ext
        .extend(
                Ext.Panel,
                {
                    id : 'permissionManagePanel',
                    layout : 'border',
                    constructor : function(config) {
                        var savePanel = new wizard.framework.permissionManage.SavePanel();
                        var permissionPanel = new wizard.framework.permissionManage.PermissionPanel();

                        var group = {
                            items : [ savePanel, permissionPanel ]
                        };

                        wizard.framework.permissionManage.PermissionManagePanel.superclass.constructor
                                .call(this, group);
                    }
                });

wizard.framework.permissionManage.SavePanel = Ext.extend(Ext.form.FormPanel, {
    region : 'north',
    height : 40,
    layout : 'fit',
    items : [ {
        xtype : 'button',
        text : '保存',
        handler : function() {
            saveAuthority();
        }
    } ]
});

wizard.framework.permissionManage.PermissionPanel = Ext
        .extend(
                Ext.Panel,
                {
                    id : 'permissionPanel',
                    region : 'center',
                    frame : true,
                    layout : {
                        type : 'hbox',
                        align : 'stretch',
                        pack : 'start'
                    },
                    constructor : function(config) {
                        var roleGridPanel = new wizard.framework.permissionManage.RoleGridPanel();
                        var menuGridPanel = new wizard.framework.permissionManage.MenuGridPanel();

                        var group = {
                            items : [ roleGridPanel, menuGridPanel ]
                        };

                        wizard.framework.permissionManage.PermissionPanel.superclass.constructor
                                .call(this, group);
                    }
                });

wizard.framework.permissionManage.RoleGridPanel = Ext.extend(
        Ext.grid.GridPanel, {
            id : 'RoleGridPanel',
            title : '角色列表',
            flex : 50,
            frame : true,
            layout : 'fit',
            loadMask : true,
            store : roleStore,
            bbar : {
                xtype : 'pagingtoolbar',
                displayInfo : true,
                store : roleStore
            },
            columns : [ Ext.create('RowNumberer'), {
                text : "编号",
                dataIndex : 'pkId'
            }, {
                text : "名称",
                width : 100,
                dataIndex : 'roleName'
            }, {
                text : "详细说明",
                dataIndex : 'roleDetail'
            }, {
                text : "组织名",
                dataIndex : 'orgName'
            } ],
            listeners : {
                select : function(grid, record, index, eOpts) {
                    if (roleId == record.get("pkId")) {
                        return;
                    }
                    saveAuthorityObj();
                    roleId = record.get("pkId");
                    checkAuthority();
                }
            }
        });

wizard.framework.permissionManage.MenuGridPanel = Ext.extend(
        Ext.grid.GridPanel, {
            id : 'MenuGridPanel',
            title : '菜单列表',
            flex : 50,
            frame : true,
            layout : 'fit',
            loadMask : true,
            store : menuStore,
            bbar : {
                xtype : 'pagingtoolbar',
                displayInfo : true,
                store : menuStore
            },
            selModel : new Ext.selection.CheckboxModel(),
            columns : [ Ext.create('RowNumberer'), {
                text : "编号",
                dataIndex : 'pkId'
            }, {
                text : "名称",
                width : 100,
                dataIndex : 'menuName'
            }, {
                text : "父菜单名称",
                width : 100,
                dataIndex : 'parentMenuName'
            } ]
        });