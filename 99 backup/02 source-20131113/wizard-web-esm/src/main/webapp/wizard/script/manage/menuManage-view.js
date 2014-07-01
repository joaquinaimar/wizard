Ext.ns("wizard.framework.menuManage");

var selectMenuId;

Ext.onReady(function() {
    Ext.QuickTips.init();
    var menuManagePanel = new wizard.framework.menuManage.MenuManagePanel();

    new Ext.Viewport({
        layout : 'fit',
        items : menuManagePanel
    });

    getFirstPage();

});

wizard.framework.menuManage.MenuManagePanel = Ext.extend(Ext.Panel, {
    id : 'MenuManagePanel',
    layout : 'border',
    constructor : function(config) {
        var welocomePanel = new wizard.framework.menuManage.WelocomePanel();
        var menuPanel = new wizard.framework.menuManage.MenuPanel();

        var group = {
            items : [ welocomePanel, menuPanel ]
        };

        wizard.framework.menuManage.MenuManagePanel.superclass.constructor
                .call(this, group);
    }
});

wizard.framework.menuManage.WelocomePanel = Ext.extend(Ext.form.FormPanel, {
    id : 'welocomePanel',
    region : 'north',
    title : '首页管理',
    height : 70,
    frame : true,
    layout : 'column',
    items : [ {
        xtype : 'textfield',
        labelWidth : 80,
        fieldLabel : '首页地址',
        id : 'welcomePath',
        name : 'welcomePath',
        columnWidth : .7,
        allowBlank : false
    }, {
        xtype : 'button',
        text : '保存',
        columnWidth : .1,
        handler : function() {
            updateFirstPage();
        }
    }, {
        xtype : 'button',
        text : '重置',
        columnWidth : .1,
        handler : function() {
            getFirstPage();
        }
    } ]
});

wizard.framework.menuManage.MenuPanel = Ext
        .extend(
                Ext.Panel,
                {
                    id : 'menuPanel',
                    region : 'center',
                    frame : true,
                    layout : {
                        type : 'hbox',
                        align : 'stretch',
                        pack : 'start'
                    },
                    constructor : function(config) {
                        var parentMenuGridPanel = new wizard.framework.menuManage.ParentMenuGridPanel();
                        var childMenuGridPanel = new wizard.framework.menuManage.ChildMenuGridPanel();

                        var group = {
                            items : [ parentMenuGridPanel, childMenuGridPanel ]
                        };

                        wizard.framework.menuManage.MenuPanel.superclass.constructor
                                .call(this, group);
                    }
                });

wizard.framework.menuManage.ParentMenuGridPanel = Ext.extend(
        Ext.grid.GridPanel, {
            id : 'parentMenuGridPanel',
            title : '父菜单管理',
            flex : 35,
            frame : true,
            layout : 'fit',
            loadMask : true,
            store : parentMenuStore,
            tbar : {
                xtype : 'form',
                frame : true,
                items : [ {
                    xtype : 'button',
                    text : '新增',
                    handler : function() {
                        addParent();
                    }
                }, {
                    xtype : 'button',
                    text : '删除',
                    handler : function() {
                        deleteParent();
                    }
                } ]
            },
            bbar : {
                xtype : 'pagingtoolbar',
                displayInfo : true,
                store : parentMenuStore
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
                text : "名称",
                width : 100,
                dataIndex : 'menuName',
                editor : {
                    xtype : 'textfield',
                    allowBlank : false
                }
            }, {
                text : "是否显示",
                width : 70,
                dataIndex : 'display',
                renderer : function(value, metaData, record) {
                    return getDisplay(value);
                },
                editor : {
                    xtype : 'combobox',
                    editable : false,
                    queryMode : 'local',
                    triggerAction : 'all',
                    store : displayStore,
                    valueField : 'flg',
                    displayField : 'content'
                }
            } ],
            selType : 'rowmodel',
            plugins : [ parentMenuRowEditingPlugin ],
            listeners : {
                select : function(grid, record, index, eOpts) {
                    if (selectMenuId == record.get("pkId")) {
                        return;
                    }
                    selectMenuId = record.get("pkId");
                    loadChild(selectMenuId);
                },
                edit : function(editor, e) {
                    if (editor.record.get('pkId')) {
                        updateParentMenu(editor.record);
                    } else {
                        insertMenu(editor.record, true);
                    }
                },
                canceledit : function(editor, e) {
                    parentMenuStore.reload();
                }
            }
        });

wizard.framework.menuManage.ChildMenuGridPanel = Ext.extend(Ext.grid.GridPanel,
        {
            id : 'childMenuGridPanel',
            title : '子菜单管理',
            flex : 65,
            frame : true,
            layout : 'fit',
            loadMask : true,
            store : childMenuStore,
            tbar : {
                xtype : 'form',
                frame : true,
                items : [ {
                    xtype : 'button',
                    text : '新增',
                    handler : function() {
                        addChild();
                    }
                }, {
                    xtype : 'button',
                    text : '删除',
                    handler : function() {
                        deleteChild();
                    }
                } ]
            },
            bbar : {
                xtype : 'pagingtoolbar',
                displayInfo : true,
                store : childMenuStore
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
                text : "名称",
                width : 100,
                dataIndex : 'menuName',
                editor : {
                    xtype : 'textfield',
                    allowBlank : false
                }
            }, {
                text : "路径",
                width : 300,
                dataIndex : 'menuPath',
                editor : {
                    xtype : 'textfield',
                    allowBlank : false
                }
            }, {
                text : "是否显示",
                width : 70,
                dataIndex : 'display',
                renderer : function(value, metaData, record) {
                    return getDisplay(value);
                },
                editor : {
                    xtype : 'combobox',
                    editable : false,
                    queryMode : 'local',
                    triggerAction : 'all',
                    store : displayStore,
                    valueField : 'flg',
                    displayField : 'content'
                }
            } ],
            selType : 'rowmodel',
            plugins : [ childMenuRowEditingPlugin ],
            listeners : {
                edit : function(editor, e) {
                    if (editor.record.get('pkId')) {
                        updateChildMenu(editor.record);
                    } else {
                        insertMenu(editor.record, false);
                    }
                },
                canceledit : function(editor, e) {
                    childMenuStore.reload();
                }
            }
        });