Ext.ns("wizard.framework.memberManage.user");

wizard.framework.memberManage.user.UserManagePanel = Ext
        .extend(
                Ext.Panel,
                {
                    id : 'userManagePanel',
                    title : '用户',
                    flex : 35,
                    layout : 'border',
                    tools : [ {
                        type : 'down',
                        handler : function() {
                            expand(this, "user");
                        }
                    }, {
                        type : 'maximize',
                        handler : function() {
                            maximize(this, "user");
                        }
                    } ],
                    constructor : function(config) {
                        var userSearchPanel = new wizard.framework.memberManage.user.UserSearchPanel();
                        var userGridPanel = new wizard.framework.memberManage.user.UserGridPanel();

                        var group = {
                            items : [ userSearchPanel, userGridPanel ]
                        };
                        userSearchPanel.hide();
                        wizard.framework.memberManage.user.UserManagePanel.superclass.constructor
                                .call(this, group);
                    }
                });

wizard.framework.memberManage.user.UserSearchPanel = Ext.extend(
        Ext.form.FormPanel, {
            id : 'userSearchPanel',
            height : 70,
            frame : true,
            layout : 'form',
            region : 'north',
            items : [ {
                xtype : 'textfield',
                labelWidth : 70,
                fieldLabel : '用户名',
                id : 'userName',
                name : 'userName'
            } ],
            buttons : [ {
                text : '查询',
                handler : function() {
                    submitUserSearchPanel();
                }
            }, {
                text : '重置',
                handler : function() {
                    resetUserSearchPanel();
                }
            } ]
        });

wizard.framework.memberManage.user.UserGridPanel = Ext.extend(
        Ext.grid.GridPanel, {
            id : 'userGridPanel',
            frame : true,
            layout : 'fit',
            region : 'center',
            loadMask : true,
            store : userStore,
            tbar : {
                xtype : 'form',
                frame : true,
                items : [ {
                    xtype : 'button',
                    text : '新增',
                    handler : function() {
                        addUser();
                    }
                }, {
                    xtype : 'button',
                    text : '删除',
                    handler : function() {
                        deleteUserInfo();
                    }
                } ]
            },
            bbar : {
                xtype : 'pagingtoolbar',
                store : userStore,
                displayInfo : true
            },
            selModel : new Ext.selection.CheckboxModel(),
            columns : [ Ext.create('RowNumberer'), {
                text : "编号",
                width : 70,
                dataIndex : 'pkId'
            }, {
                text : "所属角色",
                width : 100,
                dataIndex : 'fkRoleId',
                renderer : function(value, metaData, record) {
                    return record.get("roleName");
                },
                editor : {
                    xtype : 'combobox',
                    editable : false,
                    queryMode : 'local',
                    triggerAction : 'all',
                    store : comboRoleStore,
                    valueField : 'pkId',
                    displayField : 'roleName'
                }
            }, {
                text : "用户名",
                width : 100,
                dataIndex : 'userName',
                editor : {
                    xtype : 'textfield',
                    allowBlank : false
                }
            }, {
                text : "用户详细",
                width : 200,
                dataIndex : 'userDetail',
                editor : {
                    xtype : 'textfield'
                }
            } ],
            selType : 'rowmodel',
            plugins : [ userRowEditingPlugin ],
            listeners : {
                edit : function(editor, e) {
                    if (editor.record.get('pkId')) {
                        updateUserInfo(editor.record);
                    } else {
                        insertUserInfo(editor.record);
                    }
                },
                canceledit : function(editor, e) {
                    userStore.reload();
                }
            }
        });