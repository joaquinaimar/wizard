function loadCode(typeId) {
    var params = {
        typeId : typeId,
        start : 0,
        limit : 20
    };

    Ext.apply(codeContentStore.proxy.extraParams, params);

    codeContentStore.load();
}

function insertCode(data, flg) {

    Ext.Ajax.request({
        url : contextPath + '/wizardframework/CodeMaintain/insertCode.do',
        params : {
            pkId : data.get('newId'),
            typeId : data.get('typeId'),
            content : data.get('content')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                if (flg) {
                    codeTypeStore.reload();
                } else {
                    codeContentStore.reload();
                }
            }
        }
    });

}

function updateCodeType(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/CodeMaintain/updateCodeType.do',
        params : {
            pkId : data.get('pkId'),
            newId : data.get('newId'),
            content : data.get('content')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                codeTypeStore.reload();
                if (data.get('pkId') != data.get('newId')) {
                    loadCode(data.get('newId'));
                }
            }
        }
    });
}

function updateCodeContent(data) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/CodeMaintain/updateCodeContent.do',
        params : {
            pkId : data.get('pkId'),
            newId : data.get('newId'),
            typeId : data.get('typeId'),
            content : data.get('content')
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                codeContentStore.reload();
            }
        }
    });
}

function addType() {
    codeTypeRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        newId : '',
        typeId : '0',
        content : ''
    };

    var row = codeTypeStore.data.length;

    codeTypeStore.add(data);
    codeTypeRowEditingPlugin.startEdit(row, 0);
}

function addContent() {

    var typeId = codeContentStore.proxy.extraParams.typeId;

    if (!typeId) {
        return;
    }

    codeContentRowEditingPlugin.cancelEdit();

    var data = {
        pkId : '',
        newId : '',
        typeId : typeId,
        content : ''
    };

    var row = codeContentStore.data.length;

    codeContentStore.add(data);
    codeContentRowEditingPlugin.startEdit(row, 0);
}

function deleteType() {

    var rows = Ext.getCmp("codeContentTypePanel").getSelectionModel().getSelection();
    var length = rows.length;
    if (0 == length) {
        Ext.Msg.alert("提示", "请至少选择一条！");
        return;
    }
    Ext.Msg.confirm('删除', '确定删除吗？', function(btn) {
        if (btn == 'yes') {
            var pkIds = [];
            for ( var i = 0; i < length; i++) {
                pkIds.push(rows[i].get("pkId"));
            }
            deleteCodeType(pkIds);
        }
    }, this);

}

function deleteCodeType(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/CodeMaintain/deleteTypeCode.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                codeTypeStore.reload();
                codeContentStore.reload();
            }
        }
    });
}

function deleteContent() {

    var rows = Ext.getCmp("codeContentGridPanel").getSelectionModel().getSelection();
    var length = rows.length;
    if (0 == length) {
        Ext.Msg.alert("提示", "请至少选择一条！");
        return;
    }
    Ext.Msg.confirm('删除', '确定删除吗？', function(btn) {
        if (btn == 'yes') {
            var pkIds = [];
            for ( var i = 0; i < length; i++) {
                pkIds.push(rows[i].get("pkId"));
            }
            deleteCodeContent(pkIds);
        }
    }, this);

}

function deleteCodeContent(pkIds) {
    Ext.Ajax.request({
        url : contextPath + '/wizardframework/CodeMaintain/deleteContentCode.do',
        params : {
            pkIds : pkIds
        },
        method : 'POST',
        success : function(response, options) {
            if (response.responseText) {
                var result = Ext.JSON.decode(response.responseText).message;
                Ext.Msg.alert("提示", result + '！');
                codeContentStore.reload();
            }
        }
    });
}

function batchUpdate() {

    var codeSearchForm = Ext.getCmp("codeSearchPanel").getForm();

    if (!codeSearchForm.isValid()) {
        Ext.Msg.alert("错误", "请选择文件！");
        return;
    }

    var excelFile = codeSearchForm.findField("excelFile").getValue();
    if (".xls" != excelFile.substr(excelFile.length - 4)) {
        Ext.Msg.alert("错误", "请选择正确的文件类型（Excel 2003）！");
        return;
    }

    codeSearchForm.submit({
        waitMsg : '正在进行批量更新...',
        url : contextPath + '/wizardframework/CodeMaintain/batchUpdateCode.do',
        method : 'POST',
        success : function(form, action) {
            Ext.Msg.alert("提示", "处理成功：" + action.result.message, function() {
                codeTypeStore.reload();
                codeContentStore.reload();
            });
        },
        failure : function(form, action) {
            Ext.Msg.alert("提示", "处理出错：" + action.result.message, function() {
                codeTypeStore.reload();
                codeContentStore.reload();
            });
        },
        scope : this
    });

}

function downloadExcel() {

    var downloadIframe = document.getElementById("downloadExcel");

    if (downloadIframe) {
        downloadIframe.parentNode.removeChild(downloadIframe);
    }

    downloadIframe = document.createElement("iframe");
    downloadIframe.id = "downloadExcel";
    downloadIframe.src = contextPath + "/wizardframework/CodeMaintain/downloadExcel.do";
    downloadIframe.style.display = "none";
    document.body.appendChild(downloadIframe);
}