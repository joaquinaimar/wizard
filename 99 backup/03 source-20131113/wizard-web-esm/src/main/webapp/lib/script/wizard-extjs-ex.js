Ext.ns("wizard.lib.extjs.ex");

Ext.form.field.File.override({
    buttonText : '浏览'
});

Ext.form.TextField.override({
    initComponent : function() {
        if (this.allowBlank === false && this.fieldLabel) {
            this.fieldLabel += '<font color=red>*</font>';
        }
    }
});

Ext.define('wizard.lib.extjs.ex.RowEditingPlugin', {
    extend : 'Ext.grid.plugin.RowEditing',
    alias : 'RowEditingPlugin',
    clicksToEdit : 2,
    saveBtnText : '保存',
    cancelBtnText : '取消',
    completeEdit : function() {
        var me = this;
        if (me.editing && me.validateEdit()) {
            me.editing = false;
            me.fireEvent('edit', me.context);
        }
        me.adding = false;
    }
});

Ext.define('wizard.lib.extjs.ex.RowNumberer', {
    extend : 'Ext.grid.RowNumberer',
    alias : 'RowNumberer',
    header : 'NO.',
    width : 30
});