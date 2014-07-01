Ext.ns("wizard.framework.memberManage.role");

wizard.framework.memberManage.role.RoleManagePanel = Ext.extend(Ext.Panel, {
    id : 'roleManagePanel',
    title : '角色',
    flex : 35,
    layout : 'border',
    tools : [ {
        type : 'down',
        handler : function() {
            expand(this, "role");
        }
    }, {
        type : 'maximize',
        handler : function() {
            maximize(this, "role");
        }
    } ],
    constructor : function(config) {
        var roleSearchPanel = new wizard.framework.memberManage.role.RoleSearchPanel();
        var roleGridPanel = new wizard.framework.memberManage.role.RoleGridPanel();

        var group = {
            items : [ roleSearchPanel, roleGridPanel ]
        };
        roleSearchPanel.hide();
        wizard.framework.memberManage.role.RoleManagePanel.superclass.constructor.call(this, group);
    }
});

wizard.framework.memberManage.role.RoleSearchPanel = Ext.extend(Ext.form.FormPanel, {
    id : 'roleSearchPanel',
    height : 70,
    frame : true,
    layout : 'form',
    region : 'north',
    items : [ {
        xtype : 'textfield',
        labelWidth : 70,
        fieldLabel : '角色名',
        id : 'roleName',
        name : 'roleName'
    } ],
    buttons : [ {
        text : '查询',
        handler : function() {
            submitRoleSearchPanel();
        }
    }, {
        text : '重置',
        handler : function() {
            resetRoleSearchPanel();
        }
    } ]
});

wizard.framework.memberManage.role.RoleGridPanel = Ext.extend(Ext.grid.GridPanel, {
    id : 'roleGridPanel',
    frame : true,
    layout : 'fit',
    region : 'center',
    loadMask : true,
    store : roleStore,
    tbar : {
        xtype : 'form',
        frame : true,
        items : [ {
            xtype : 'button',
            text : '新增',
            handler : function() {
                addRole();
            }
        }, {
            xtype : 'button',
            text : '删除',
            handler : function() {
                deleteRoleInfo();
            }
        } ]
    },
    bbar : {
        xtype : 'pagingtoolbar',
        store : roleStore,
        displayInfo : true
    },
    selModel : new Ext.selection.CheckboxModel(),
    columns : [ Ext.create('RowNumberer'), {
        text : "编号",
        width : 70,
        dataIndex : 'pkId'
    }, {
        text : "所属组织",
        width : 100,
        dataIndex : 'fkOrgId',
        renderer : function(value, metaData, record) {
            return record.get("orgName");
        },
        editor : {
            xtype : 'combobox',
            editable : false,
            queryMode : 'local',
            triggerAction : 'all',
            store : comboOrgStore,
            valueField : 'pkId',
            displayField : 'orgName'
        }
    }, {
        text : "角色名",
        width : 100,
        dataIndex : 'roleName',
        editor : {
            xtype : 'textfield',
            allowBlank : false
        }
    }, {
        text : "角色详细",
        width : 200,
        dataIndex : 'roleDetail',
        editor : {
            xtype : 'textfield'
        }
    } ],
    selType : 'rowmodel',
    plugins : [ roleRowEditingPlugin ],
    listeners : {
        select : function(grid, record, index, eOpts) {
            if (selectId.role == record.get("pkId")) {
                return;
            }
            selectId.role = record.get("pkId");
            submitUserSearchPanel();
        },
        edit : function(editor, e) {
            if (editor.record.get('pkId')) {
                updateRoleInfo(editor.record);
            } else {
                insertRoleInfo(editor.record);
            }
        },
        canceledit : function(editor, e) {
            roleStore.reload();
        }
    }
});