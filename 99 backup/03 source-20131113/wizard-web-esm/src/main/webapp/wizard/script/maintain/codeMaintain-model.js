var codeTypeRowEditingPlugin = Ext.create('RowEditingPlugin');

var codeContentRowEditingPlugin = Ext.create('RowEditingPlugin');

var codeTypeStore = new Ext.data.Store({
    pageSize : 20,
    autoLoad : {
        params : {
            start : 0,
            limit : 20
        }
    },
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/CodeMaintain/searchType.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'newId', 'typeId', 'content' ]
});

var codeContentStore = new Ext.data.Store({
    pageSize : 20,
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/CodeMaintain/searchCode.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'newId', 'typeId', 'type', 'content' ]
});