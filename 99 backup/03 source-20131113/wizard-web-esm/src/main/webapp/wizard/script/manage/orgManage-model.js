var orgRowEditingPlugin = Ext.create('RowEditingPlugin');

var orgStore = new Ext.data.Store({
    pageSize : 20,
    autoLoad : {
        params : {
            start : 0,
            limit : 20
        }
    },
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/OrgManage/searchOrgInfo.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'orgName', 'orgDetail' ]
});

var comboOrgStore = new Ext.data.Store({
    autoLoad : true,
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/OrgManage/getOrgInfoList.do',
        reader : {
            type : 'json',
            root : 'data'
        }
    },
    fields : [ 'pkId', 'orgName' ]
});