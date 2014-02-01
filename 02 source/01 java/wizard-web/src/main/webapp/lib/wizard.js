Wizard = {
	ajaxSubmit : function(form, option) {
		var id;
		if (option.loadstr)
			id = Wizard.createLoadingWin(option.loadstr);
		var success = option.success;
		option.success = function(responseText, statusText, xhr) {
			success(responseText, statusText, xhr);
			if (id)
				Wizard.removeLoadingWin(id);
		};
		option.error = option.error ||
		function() {
			if (id)
				Wizard.removeLoadingWin(id);
			Wizard.alertDanger("异常", "与服务器通信异常！");
		};
		$(form).ajaxSubmit(option);
	},
	ajax : function(option) {
		$.ajax(option);
	},
	ajaxDataTable : function(dataTable, option) {
		if (option.fields) {
			var columns = [];
			var thead = '<thead>';
			for (var i = 0; i < option.fields.length; i++) {
				var o = option.fields[i];
				thead += '<th';

				if (o.css)
					thead += ' class="' + o.css + '"';
				if (o.style)
					thead += ' style="' + o.style + '"';
				thead += '>' + option.fields[i].header + '</th>';

				var column = {};

				if (o.type && 'text' !== o.type) {
					column.asSorting = [];
					column.mDataProp = function(source, type, val) {
						return "";
					};
				} else {
					column.mDataProp = option.fields[i].name;
				}
				columns.push(column);

			}
			thead += '</thead>';
		}

		$(dataTable).append(thead);

		var language = {
			sProcessing : "处理中...",
			sLengthMenu : "_MENU_ 记录/页",
			sZeroRecords : "没有匹配的记录",
			sInfo : "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
			sInfoEmpty : "显示第 0 至 0 项记录，共 0 项",
			sInfoFiltered : "(由 _MAX_ 项记录过滤)",
			sInfoPostFix : "",
			sSearch : "过滤:",
			sUrl : "",
			oPaginate : {
				sFirst : "首页",
				sPrevious : "上页",
				sNext : "下页",
				sLast : "末页"
			}
		};

		var events = [];
		$(dataTable).dataTable({
			bFilter : false,
			bProcessing : true,
			bServerSide : true,
			bSortClasses : false,
			sAjaxSource : option.url,
			aoColumns : columns,
			oLanguage : language,
			fnServerData : function(sSource, aoData, fnCallback, oSettings) {
				var params = option.params;
				var sortCols = [];
				var sortTypes = [];
				for (var i = 0; i < aoData.length; i++) {
					if ("iDisplayStart" === aoData[i].name)
						params.start = aoData[i].value;
					else if ("iDisplayLength" === aoData[i].name)
						params.limit = aoData[i].value;
					else if (option.sort && aoData[i].name && aoData[i].name.indexOf("iSortCol_") === 0) {
						var field = option.fields[aoData[i].value];
						if (!field.type || 'text' === field.type)
							sortCols.push(field.name);
						else
							sortCols.push(null);
					} else if (option.sort && aoData[i].name && aoData[i].name.indexOf("sSortDir_") === 0) {
						sortTypes.push(aoData[i].value);
					}
				}

				var requestParams = [];

				for (var n in params);
				requestParams.push({
					name : n,
					value : params[n]
				});

				for (var i = 0; i < sortCols.length; i++) {
					if (!sortCols[i])
						break;
					requestParams.push({
						name : 'sortCols',
						value : sortCols[i]
					});
					requestParams.push({
						name : 'sortTypes',
						value : sortTypes[i]
					});
				}

				oSettings.jqXHR = $.ajax({
					dataType : 'json',
					type : option.method,
					url : sSource,
					data : requestParams,
					success : function(json) {
						if (option.reader) {
							var o = {
								iTotalRecords : json[option.reader.totalProperty],
								iTotalDisplayRecords : json[option.reader.totalProperty],
								aaData : json[option.reader.root]
							};
							json = o;
						}
						fnCallback(json);
					}
				});
			},
			fnRowCallback : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
				for (var i = 0; i < option.fields.length; i++) {
					var o = option.fields[i];
					if ("checkbox" === o.type) {
						$("td:eq(" + i + ")", nRow).html("<input type='checkbox' id='checkbox-" + o.name + iDisplayIndex + "' name='checkbox-" + o.name + "' value='" + aData[o.name] + "'>");
					} else if ("button" === o.type) {
						var buttonId = "button-" + o.name + iDisplayIndex;
						$("td:eq(" + i + ")", nRow).html("<input type='button' id='" + buttonId + "' name='button-" + o.name + "' class='" + o.cssClass + "' value='" + o.value + "'>");

						events.push({
							target : "#" + buttonId,
							name : "click",
							handler : function() {
								o.handler(iDisplayIndex, aData);
							}
						});

					}
				}
			},
			fnDrawCallback : function(oSettings) {
				for (var i = 0; i < events.length; i++) {
					$(events[i].target).bind(events[i].name, events[i].handler);
				}
			}
		});
	},
	createLoadingWin : function(loadstr) {
		var id = "loading-win-" + Math.floor(Math.random() * 1000000);
		var tagStr = '<div id="' + id + '" class="modal fade" role="dialog" aria-hidden="true">';
		tagStr += '<div class="modal-dialog">';
		tagStr += '<div class="modal-content alert alert-info">';
		tagStr += '<div class="modal-header">';
		tagStr += '<div><h4>' + (loadstr || 'loading') + '···</h4></div>';
		tagStr += '</div>';
		tagStr += '<div class="modal-body">';
		tagStr += '<div class="progress progress-striped active">';
		tagStr += '<div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;">';
		tagStr += '</div>';
		tagStr += '</div></div></div></div>';

		$(document.body).append(tagStr);

		$('#' + id).modal({
			backdrop : 'static'
		});
		return id;
	},
	removeLoadingWin : function(id) {
		$('#' + id).modal('hide');
		setTimeout('$("#' + id + '").remove()', 2000);
	},
	alertSuccess : function(title, content, fnClose) {
		Wizard.alert(title, content, "success", fnClose);
	},
	alertInfo : function(title, content, fnClose) {
		Wizard.alert(title, content, "info", fnClose);
	},
	alertWarning : function(title, content, fnClose) {
		Wizard.alert(title, content, "warning", fnClose);
	},
	alertDanger : function(title, content, fnClose) {
		Wizard.alert(title, content, "danger", fnClose);
	},
	alert : function(title, content, type, fnClose) {

		var id = "alert-win-" + Math.floor(Math.random() * 1000000);
		var options = {
			fnClose : fnClose
		};

		var tagStr = '<div id="' + id + '" class="modal fade" role="dialog" aria-hidden="true">';
		tagStr += '<div class="modal-dialog wizard-alert">';
		tagStr += '<div class="modal-content alert alert-' + (type || 'danger') + '">';
		tagStr += '<div class="modal-header">';
		tagStr += '<button type="button" class="close" data-dismiss="modal">&times; </button>';
		tagStr += '<div><h4><span class="glyphicon glyphicon-warning-sign"></span>' + title + '</h4></div>';
		tagStr += '</div>';
		tagStr += '<div class="modal-body">';
		tagStr += '<div>' + content + '</div>';
		tagStr += '</div></div></div></div>';
		$(document.body).append(tagStr);

		$("[data-dismiss=modal]").click(function() {
			$('#' + id).modal('hide');
			setTimeout('$("#' + id + '").remove()', 2000);
			if (fnClose)
				options.fnClose();
		});

		$('#' + id).modal({
			backdrop : 'static'
		});
	}
};
