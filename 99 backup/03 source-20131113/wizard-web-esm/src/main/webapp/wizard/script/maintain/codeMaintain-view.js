Ext.ns("wizard.framework.codeMaintain");

var selectTypeId;

Ext.onReady(function() {
    Ext.QuickTips.init();
    var codeMaintainPanel = new wizard.framework.codeMaintain.CodeMaintainPanel();

    new Ext.Viewport({
        layout : 'fit',
        items : codeMaintainPanel
    });

});

wizard.framework.codeMaintain.CodeMaintainPanel = Ext.extend(Ext.Panel, {
    id : 'codeMaintainPanel',
    layout : 'border',
    constructor : function(config) {
        var codeSearchPanel = new wizard.framework.codeMaintain.CodeSearchPanel();
        var codeGridPanel = new wizard.framework.codeMaintain.CodeGridPanel();

        var group = {
            items : [ codeSearchPanel, codeGridPanel ]
        };

        wizard.framework.codeMaintain.CodeMaintainPanel.superclass.constructor.call(this, group);
    }
});

wizard.framework.codeMaintain.CodeSearchPanel = Ext.extend(Ext.form.FormPanel, {
    id : 'codeSearchPanel',
    region : 'north',
    height : 60,
    title : '批量更新',
    frame : true,
    layout : 'column',
    fileUpload : true,
    items : [ {
        xtype : 'filefield',
        id : 'excelFile',
        name : 'excelFile',
        fieldLabel : '文件',
        labelWidth : 40,
        allowBlank : false,
        columnWidth : .6
    }, {
        xtype : 'button',
        text : '批量更新',
        columnWidth : .1,
        handler : function() {
            batchUpdate();
        }
    }, {
        xtype : 'button',
        text : '下载',
        columnWidth : .1,
        handler : function() {
            downloadExcel();
        }
    } ]
});

wizard.framework.codeMaintain.CodeGridPanel = Ext.extend(Ext.Panel, {
    id : 'codeGridPanel',
    region : 'center',
    frame : true,
    layout : {
        type : 'hbox',
        align : 'stretch',
        pack : 'start'
    },
    constructor : function(config) {
        var codeContentTypePanel = new wizard.framework.codeMaintain.CodeTypeGridPanel();
        var codeContentGridPanel = new wizard.framework.codeMaintain.CodeContentGridPanel();

        var group = {
            items : [ codeContentTypePanel, codeContentGridPanel ]
        };

        wizard.framework.codeMaintain.CodeGridPanel.superclass.constructor.call(this, group);
    }
});

wizard.framework.codeMaintain.CodeTypeGridPanel = Ext.extend(Ext.grid.GridPanel, {
    id : 'codeContentTypePanel',
    title : '编码类型',
    flex : 4,
    frame : true,
    layout : 'fit',
    loadMask : true,
    store : codeTypeStore,
    tbar : {
        xtype : 'form',
        frame : true,
        items : [ {
            xtype : 'button',
            text : '新增',
            handler : function() {
                addType();
            }
        }, {
            xtype : 'button',
            text : '删除',
            handler : function() {
                deleteType();
            }
        } ]
    },
    bbar : {
        xtype : 'pagingtoolbar',
        store : codeTypeStore,
        displayInfo : true
    },
    selModel : new Ext.selection.CheckboxModel(),
    columns : [ Ext.create('RowNumberer'), {
        text : "编号",
        dataIndex : 'newId',
        editor : {
            xtype : 'textfield',
            allowBlank : false
        }
    }, {
        text : "内容",
        width : 200,
        dataIndex : 'content',
        editor : {
            xtype : 'textfield'
        }
    } ],
    selType : 'rowmodel',
    plugins : [ codeTypeRowEditingPlugin ],
    listeners : {
        select : function(grid, record, index, eOpts) {
            if (selectTypeId == record.get("pkId")) {
                return;
            }
            selectTypeId = record.get("pkId");
            loadCode(selectTypeId);
        },
        edit : function(editor, e) {
            if (editor.record.get('pkId')) {
                updateCodeType(editor.record);
            } else {
                insertCode(editor.record, true);
            }
        },
        canceledit : function(editor, e) {
            codeTypeStore.reload();
        }
    }
});

wizard.framework.codeMaintain.CodeContentGridPanel = Ext.extend(Ext.grid.GridPanel, {
    id : 'codeContentGridPanel',
    title : '编码内容',
    flex : 6,
    frame : true,
    layout : 'fit',
    loadMask : true,
    store : codeContentStore,
    tbar : {
        xtype : 'form',
        frame : true,
        items : [ {
            xtype : 'button',
            text : '新增',
            handler : function() {
                addContent();
            }
        }, {
            xtype : 'button',
            text : '删除',
            handler : function() {
                deleteContent();
            }
        } ]
    },
    bbar : {
        xtype : 'pagingtoolbar',
        store : codeContentStore,
        displayInfo : true
    },
    selModel : new Ext.selection.CheckboxModel(),
    columns : [ Ext.create('RowNumberer'), {
        text : "编号",
        dataIndex : 'newId',
        editor : {
            xtype : 'textfield',
            allowBlank : false
        }
    }, {
        text : "类型",
        width : 100,
        dataIndex : 'typeId',
        renderer : function(value, metaData, record) {
            return record.get("type");
        },
        editor : {
            xtype : 'combobox',
            editable : false,
            queryMode : 'local',
            triggerAction : 'all',
            store : getCodeList("0", false),
            valueField : 'pkId',
            displayField : 'content'
        }
    }, {
        text : "内容",
        width : 200,
        dataIndex : 'content',
        editor : {
            xtype : 'textfield',
            allowBlank : false
        }
    } ],
    selType : 'rowmodel',
    plugins : [ codeContentRowEditingPlugin ],
    listeners : {
        edit : function(editor, e) {
            if (editor.record.get('pkId')) {
                updateCodeContent(editor.record);
            } else {
                insertCode(editor.record, false);
            }
        },
        canceledit : function(editor, e) {
            codeContentStore.reload();
        }
    }
});