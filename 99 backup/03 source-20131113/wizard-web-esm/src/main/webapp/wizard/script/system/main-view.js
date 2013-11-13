Ext.ns("wizard.framework.main");

Ext.onReady(function() {
	Ext.QuickTips.init();
	var mainPanel = new wizard.framework.main.MainPanel();

	new Ext.Viewport({
		layout : 'fit',
		items : mainPanel
	});

	getFirstPage();

});

var mainTabPanel = new Ext.TabPanel({
	id : 'mainMain',
	layout : 'fit',
	region : 'center',
	xtype : 'tabpanel',
	defaults : {
		closable : true,
		xtype : 'container',
		layout : 'fit'
	}
});

wizard.framework.main.MainPanel = Ext
		.extend(
				Ext.Panel,
				{
					id : 'mainPanel',
					layout : 'border',
					items : [
							{
								id : 'mainHead',
								region : 'north',
								layout : 'border',
								height : 100,
								html : '<img src="'
										+ contextPath
										+ '/wizard/image/header.jpg" width=100% height=100%>',
								items : [ {
									xtype : 'panel',
									title : '欢迎 [' + loginName + '] 进入系统',
									region : 'east',
									width : 200,
									frame : true,
									collapsible : true,
									items : [
											{
												xtype : 'panel',
												frame : true,
												bodyStyle : 'text-align : center;',
												html : '<a href="#" onclick="showModify();" >[修改密码]</a>'
											},
											{
												xtype : 'panel',
												frame : true,
												bodyStyle : 'text-align : center;',
												html : '<a href="#" onclick="logout(true)">[退出登录]</a>'
											} ]
								} ]
							},
							{
								id : 'mainMenu',
								title : '菜单',
								layout : 'fit',
								region : 'west',
								xtype : 'panel',
								width : 200,
								frame : true,
								collapsible : true,
								items : [ {
									xtype : 'container',
									layout : 'fit',
									autoEl : {
										tag : 'iframe',
										name : 'menu',
										frameBorder : 0,
										src : contextPath
												+ '/wizard/system/menu'
									}
								} ]
							}, mainTabPanel, {
								id : 'mainFoot',
								region : 'south',
								height : 50,
								frame : true,
								html : '<div class="foot">版权所有</div>'
							} ]

				});
