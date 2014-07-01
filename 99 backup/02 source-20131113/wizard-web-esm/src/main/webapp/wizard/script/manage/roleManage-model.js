var roleRowEditingPlugin = Ext.create('RowEditingPlugin');

var roleStore = new Ext.data.Store({
    pageSize : 20,
    autoLoad : {
        params : {
            start : 0,
            limit : 20
        }
    },
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/RoleManage/searchRoleInfo.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'roleName', 'roleDetail', 'orgName', 'fkOrgId' ]
});

var comboRoleStore = new Ext.data.Store({
    autoLoad : true,
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/RoleManage/getRoleInfoList.do',
        reader : {
            type : 'json',
            root : 'data'
        }
    },
    fields : [ 'pkId', 'roleName' ]
});