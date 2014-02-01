$(document).ready(
		function() {
			Wizard.ajaxDataTable("#code", {
				url : contextPath
						+ '/wizard/maintain/code-maintain/getCodeInfoList.do',
				params : {},
				sort : true,
				method : 'GET',
				reader : {
					totalProperty : 'totalCount',
					root : 'result'
				},
				fields : [ {
					header : '#',
					name : 'select',
					type : 'checkbox'
				}, {
					header : 'Id',
					name : 'pkId'
				}, {
					header : '类型',
					name : 'typeId'
				}, {
					header : '内容',
					name : 'content'
				}, {
					header : '操作',
					name : 'modify',
					value : '修改',
					type : 'button',
					cssClass : "btn btn-primary",
					handler : function(index, rowData) {
					}
				} ]
			});
		});