Ext.ns("wizard.framework.menu");

Ext.onReady(function() {
    Ext.QuickTips.init();
    var treePanel = new wizard.framework.menu.TreePanel();

    new Ext.Viewport({
        layout : 'fit',
        items : treePanel
    });
});

wizard.framework.menu.TreePanel = Ext.extend(Ext.tree.TreePanel, {
    id : 'treePanel',
    frame : true,
    animate : true,
    enableDD : false,
    border : true,
    rootVisible : false,
    store : treeStore,
    root : {
        id : '0',
        expanded : true
    },
    listeners : {
        itemclick : function(view, rec, node) {
            if (rec.raw.url && top) {
                top.addTabItem(rec.raw.text, rec.raw.url);
            }
        }
    }
});