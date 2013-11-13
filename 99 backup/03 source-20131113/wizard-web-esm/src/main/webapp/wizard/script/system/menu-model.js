var treeStore = new Ext.data.TreeStore({
    autoLoad : true,
    autoSync : false,
    proxy : {
        type : 'ajax',
        url : contextPath + '/wizardframework/Main/showMenu.do',
        reader : {
            type : 'json',
            root : 'data'
        }
    },
    sorters : [ {
        property : 'id',
        direction : 'ASC'
    } ],
    listeners : {
        beforeload : function(ds, opration, opt) {
            opration.params.pid = opration.node.data.id;
        }
    }
});