Ext.ns("wizard.applet.canvas");

var menuTabPanel;

Ext.onReady(function() {
    Ext.QuickTips.init();
    var mainPanel = new wizard.applet.canvas.MainPanel();

    new Ext.Viewport({
        layout : 'fit',
        items : mainPanel
    });

    menuTabPanel.add(new wizard.applet.canvas.ToolsPanel()).show();
    menuTabPanel.add(new wizard.applet.canvas.ScanPanel());

    initCanvas("wizardCanvas");

});

wizard.applet.canvas.MainPanel = Ext.extend(Ext.Panel, {
    id : 'mainPanel',
    layout : 'border',
    constructor : function(config) {
        menuTabPanel = new wizard.applet.canvas.MenuTabPanel();
        var canvasPanel = new wizard.applet.canvas.CanvasPanel();

        var group = {
            items : [ menuTabPanel, canvasPanel ]
        };

        wizard.applet.canvas.MainPanel.superclass.constructor.call(this, group);

    }
});

wizard.applet.canvas.MenuTabPanel = Ext.extend(Ext.TabPanel, {
    id : 'menuTabPanel',
    layout : 'fit',
    region : 'north',
    xtype : 'tabpanel',
    height : 150,
    collapsible : true,
    defaults : {
        closable : false,
        xtype : 'container',
        layout : 'fit'
    }
});

wizard.applet.canvas.CanvasPanel = Ext.extend(Ext.Panel, {
    id : 'canvasPanel',
    title : '画布',
    region : 'center',
    layout : 'fit',
    html : '<canvas id="wizardCanvas" width="1200px" height="550px" class="canvasStyle">您的浏览器不支持canvas</canvas>'
});

wizard.applet.canvas.ToolsPanel = Ext.extend(Ext.Panel, {
    id : 'toolsPanel',
    title : '工具',
    layout : 'fit',
    html : '工具'
});

wizard.applet.canvas.ScanPanel = Ext.extend(Ext.Panel, {
    id : 'scanPanel',
    title : '查看',
    layout : 'fit',
    html : '查看'
});