var parentMenuRowEditingPlugin = Ext.create('RowEditingPlugin');

var childMenuRowEditingPlugin = Ext.create('RowEditingPlugin');

var parentMenuStore = new Ext.data.Store({
    pageSize : 20,
    autoLoad : {
        params : {
            start : 0,
            limit : 20
        }
    },
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/MenuManage/getParentMenu.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'newId', 'pPkId', 'menuName', 'display' ]
});

var childMenuStore = new Ext.data.Store({
    pageSize : 20,
    autoLoad : false,
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/MenuManage/getChildMenu.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'newId', 'pPkId', 'menuName', 'menuPath', 'display' ]
});

var displayStore = new Ext.data.SimpleStore({
    fields : [ 'flg', 'content' ],
    data : [ [ '1', '显示' ], [ '0', '隐藏' ] ]
});