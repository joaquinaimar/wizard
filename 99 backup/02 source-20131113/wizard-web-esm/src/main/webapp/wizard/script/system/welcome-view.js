Ext.ns("wizard.framework.welcome");

Ext.onReady(function() {
    Ext.QuickTips.init();

    var welcomePanel = new wizard.framework.welcome.WelcomePanel();

    new Ext.Viewport({
        layout : 'fit',
        items : welcomePanel
    });
});

wizard.framework.welcome.WelcomePanel = Ext.extend(Ext.Panel, {
    id : 'mainPanel',
    frame : true,
    layout : {
        type : 'hbox',
        align : 'middle',
        pack : 'center'
    },
    constructor : function(config) {
        var datePickerPanel = new wizard.framework.welcome.DatePickerPanel();
        wizard.framework.welcome.WelcomePanel.superclass.constructor.call(this,
            {
                items : datePickerPanel
            })
    }
});

wizard.framework.welcome.DatePickerPanel = Ext.extend(Ext.DatePicker, {
    id : 'datePicker'
});