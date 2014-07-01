Ext.ns("wizard.framework.memberManage");

var selectId = {};

Ext.onReady(function() {
    Ext.QuickTips.init();
    var memberPanel = new wizard.framework.memberManage.MemberManagePanel();

    new Ext.Viewport({
        layout : 'fit',
        items : memberPanel
    });

});

wizard.framework.memberManage.MemberManagePanel = Ext.extend(Ext.Panel, {
    id : 'memberPanel',
    frame : true,
    layout : {
        type : 'hbox',
        align : 'stretch',
        pack : 'start'
    },
    constructor : function(config) {
        var orgManagePanel = new wizard.framework.memberManage.org.OrgManagePanel();
        var roleManagePanel = new wizard.framework.memberManage.role.RoleManagePanel();
        var userManagePanel = new wizard.framework.memberManage.user.UserManagePanel();

        var group = {
            items : [ orgManagePanel, roleManagePanel, userManagePanel ]
        };

        wizard.framework.memberManage.MemberManagePanel.superclass.constructor.call(this, group);
    }

});

Ext.ns("wizard.framework.memberManage.org");

wizard.framework.memberManage.org.OrgManagePanel = Ext.extend(Ext.Panel, {
    id : 'orgManagePanel',
    title : '组织',
    flex : 30,
    layout : 'border',
    tools : [ {
        type : 'down',
        handler : function() {
            expand(this, "org");
        }
    }, {
        type : 'maximize',
        handler : function() {
            maximize(this, "org");
        }
    } ],
    constructor : function(config) {
        var orgSearchPanel = new wizard.framework.memberManage.org.OrgSearchPanel();
        var orgGridPanel = new wizard.framework.memberManage.org.OrgGridPanel();

        var group = {
            items : [ orgSearchPanel, orgGridPanel ]
        };
        orgSearchPanel.hide();
        wizard.framework.memberManage.org.OrgManagePanel.superclass.constructor.call(this, group);
    }
});

wizard.framework.memberManage.org.OrgSearchPanel = Ext.extend(Ext.form.FormPanel, {
    id : 'orgSearchPanel',
    height : 70,
    frame : true,
    layout : 'form',
    region : 'north',
    items : [ {
        xtype : 'textfield',
        labelWidth : 70,
        fieldLabel : '组织名',
        id : 'orgName',
        name : 'orgName'
    } ],
    buttons : [ {
        text : '查询',
        handler : function() {
            submitOrgSearchPanel();
        }
    }, {
        text : '重置',
        handler : function() {
            resetOrgSearchPanel();
        }
    } ]
});

wizard.framework.memberManage.org.OrgGridPanel = Ext.extend(Ext.grid.GridPanel, {
    id : 'orgGridPanel',
    frame : true,
    layout : 'fit',
    region : 'center',
    loadMask : true,
    store : orgStore,
    tbar : {
        xtype : 'form',
        frame : true,
        items : [ {
            xtype : 'button',
            text : '新增',
            handler : function() {
                addOrg();
            }
        }, {
            xtype : 'button',
            text : '删除',
            handler : function() {
                deleteOrgInfo();
            }
        } ]
    },
    bbar : {
        xtype : 'pagingtoolbar',
        store : orgStore,
        displayInfo : true
    },
    selModel : new Ext.selection.CheckboxModel(),
    columns : [ Ext.create('RowNumberer'), {
        text : "编号",
        width : 70,
        dataIndex : 'pkId'
    }, {
        text : "组织名",
        width : 100,
        dataIndex : 'orgName',
        editor : {
            xtype : 'textfield',
            allowBlank : false
        }
    }, {
        text : "组织详细",
        width : 200,
        dataIndex : 'orgDetail',
        editor : {
            xtype : 'textfield'
        }
    } ],
    selType : 'rowmodel',
    plugins : [ orgRowEditingPlugin ],
    listeners : {
        select : function(grid, record, index, eOpts) {
            if (selectId.org == record.get("pkId")) {
                return;
            }
            selectId.org = record.get("pkId");
            submitRoleSearchPanel();
        },
        edit : function(editor, e) {
            if (editor.record.get('pkId')) {
                updateOrgInfo(editor.record);
            } else {
                insertOrgInfo(editor.record);
            }
        },
        canceledit : function(editor, e) {
            orgStore.reload();
        }
    }
});