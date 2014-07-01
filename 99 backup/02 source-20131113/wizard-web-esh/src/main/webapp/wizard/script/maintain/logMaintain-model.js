var logStore = new Ext.data.Store({
    pageSize : 20,
    autoLoad : {
        params : {
            start : 0,
            limit : 20
        }
    },
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/LogMaintain/searchLog.do',
        reader : {
            type : 'json',
            totalProperty : "totalCount",
            root : 'result'
        }
    },
    fields : [ 'pkId', 'logTitle', 'logLevelValue', {
        name : 'logTime',
        type : 'date',
        dateFormat : 'time'
    }, 'logDetail', 'userName' ]
});