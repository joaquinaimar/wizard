function getCodeList(typeId, flg) {
    var appStore = new Ext.data.Store({
        autoLoad : {
            params : {
                typeId : typeId,
                flg : flg ? '1' : '0'
            }
        },
        proxy : {
            type : 'ajax',
            url : contextPath + '/wizardframework/WizardCommon/getCodeList.do',
            reader : {
                type : 'json',
                root : 'data'
            }
        },
        fields : [ 'pkId', 'content' ]
    });
    return appStore;
}

function getDataSources() {
    var appStore = new Ext.data.Store({
        autoLoad : true,
        proxy : {
            type : 'ajax',
            url : contextPath + '/wizardframework/WizardCommon/getDataSources.do',
            reader : {
                type : 'json',
                root : 'data'
            }
        },
        fields : [ 'valueField', 'displayField' ]
    });
    return appStore;
}

function equalsObject(obj1, obj2) {

    if ("object" != typeof (obj1) || "object" != typeof (obj2)) {
        return false;
    }

    for ( var o in obj1) {
        if (obj1[o] != obj2[o]) {
            return false;
        }
    }

    for ( var o in obj2) {
        if (obj1[o] != obj2[o]) {
            return false;
        }
    }

    return true;
}