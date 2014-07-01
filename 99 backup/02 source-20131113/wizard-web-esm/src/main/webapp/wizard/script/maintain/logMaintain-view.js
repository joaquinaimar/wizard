Ext.ns("wizard.framework.logMaintain");

Ext.onReady(function() {
    Ext.QuickTips.init();
    var logMaintainPanel = new wizard.framework.logMaintain.LogMaintainPanel();

    new Ext.Viewport({
        layout : 'fit',
        items : logMaintainPanel
    });

});

wizard.framework.logMaintain.LogMaintainPanel = Ext.extend(Ext.Panel, {
    id : 'logMaintainPanel',
    layout : 'border',
    constructor : function(config) {
        var logSearchPanel = new wizard.framework.logMaintain.LogSearchPanel();
        var logGridPanel = new wizard.framework.logMaintain.LogGridPanel();

        var group = {
            items : [ logSearchPanel, logGridPanel ]
        };

        wizard.framework.logMaintain.LogMaintainPanel.superclass.constructor.call(this, group);
    }
});

wizard.framework.logMaintain.LogSearchPanel = Ext.extend(Ext.form.FormPanel, {
    id : 'logSearchPanel',
    region : 'north',
    height : 140,
    title : '日志查询',
    collapsible : true,
    frame : true,
    layout : 'fit',
    items : [ {
        frame : true,
        layout : {
            type : 'table',
            columns : 3
        },
        items : [ {
            xtype : 'textfield',
            labelWidth : 50,
            fieldLabel : '标题',
            id : 'logTitle',
            name : 'logTitle'
        }, {
            xtype : 'textfield',
            labelWidth : 50,
            fieldLabel : '用户名',
            id : 'userName',
            name : 'userName'
        }, {
            xtype : 'combobox',
            editable : false,
            labelWidth : 50,
            fieldLabel : '级别',
            id : 'logLevel',
            name : 'logLevel',
            emptyText : '请选择',
            queryMode : 'local',
            triggerAction : 'all',
            store : getCodeList("10", true),
            valueField : 'pkId',
            displayField : 'content'
        }, {
            xtype : 'datefield',
            labelWidth : 50,
            fieldLabel : '时间',
            id : 'logTimeStart',
            name : 'logTimeStart',
            format : 'Y-m-d',
            editable : false
        }, {
            xtype : 'datefield',
            labelWidth : 50,
            fieldLabel : '至',
            id : 'logTimeEnd',
            name : 'logTimeEnd',
            format : 'Y-m-d',
            editable : false
        } ]
    } ],
    buttons : [ {
        text : '查询',
        handler : function() {
            submitLogSearchPanel();
        }
    }, {
        text : '重置',
        handler : function() {
            resetLogSearchPanel();
        }
    } ]
});

wizard.framework.logMaintain.LogGridPanel = Ext.extend(Ext.grid.GridPanel, {
    id : 'logGridPanel',
    region : 'center',
    frame : true,
    layout : 'fit',
    loadMask : true,
    store : logStore,
    tbar : {
        xtype : 'form',
        frame : true,
        items : [ {
            xtype : 'button',
            text : '清空日志记录',
            handler : function() {
                clearLogInfo();
            }
        } ]
    },
    bbar : {
        xtype : 'pagingtoolbar',
        displayInfo : true,
        store : logStore
    },
    viewConfig : {
        getRowClass : function(record, rowIndex, rowParams, store) {
            return getRowClass(record, rowIndex, rowParams, store);
        }
    },
    columns : [ new Ext.grid.RowNumberer({
        header : 'NO.',
        width : 50
    }), {
        text : "编号",
        dataIndex : 'pkId'
    }, {
        text : "级别",
        width : 80,
        dataIndex : 'logLevelValue'
    }, {
        text : "日志标题",
        width : 180,
        dataIndex : 'logTitle'
    }, {
        text : "详细内容",
        width : 350,
        dataIndex : 'logDetail'
    }, {
        text : "时间",
        width : 150,
        xtype : 'datecolumn',
        format : 'Y-m-d H:i:s',
        dataIndex : 'logTime'
    }, {
        text : "用户名",
        width : 120,
        dataIndex : 'userName'
    } ]
});