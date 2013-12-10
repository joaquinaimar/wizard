$(document).ready(function() {
	var menu = [ {
		id : '20',
		name : '首页',
		url : 'wizard/homepage'
	}, {
		id : '40',
		name : '系统维护',
		child : [ {
			id : '40401',
			name : '系统维护1',
			url : '40401'
		}, {
			id : '40402',
			name : '系统维护2',
			url : '40402'
		} ]
	} ];
	createMenu(menu);

	addTabItem("首页", "wizard/homepage", true);
	$("#btnLogout").bind("click", funLogout);
});

var createMenu = function(menu) {
	if (menu instanceof String)
		menu = eval("(" + menu + ")");
	if (!(menu instanceof Array))
		return;

	var menuTag = '';

	for ( var i = 0; i < menu.length; i++) {
		menuTag += '<div class="panel panel-default"><div class="panel-heading"><h4 class="panel-title">';
		menuTag += '<a class="head" href="#menu' + menu[i].id + '" data-toggle="collapse" ';
		if (menu[i].child && menu[i].child instanceof Array)
			menuTag += '';
		else if (menu[i].url)
			menuTag += ' onclick="addTabItem(\'' + menu[i].name + '\', \''
					+ menu[i].url + '\');" ';
		menuTag += ' data-parent="#menu" >' + menu[i].name + '</a>';
		menuTag += '</h4></div>';

		if (menu[i].child && menu[i].child instanceof Array) {
			menuTag += '<div id="menu' + menu[i].id
					+ '" class="panel-collapse collapse">';
			menuTag += '<div class="list-group">';
			for ( var j = 0; j < menu[i].child.length; j++) {
				menuTag += '<a href="#" class="list-group-item list-group-item-warning" onclick="addTabItem(\''
						+ menu[i].child[j].name + '\', \''
						+ menu[i].child[j].url + '\');" >'
						+ menu[i].child[j].name + '</a>';
			}
			menuTag += '</div></div>';
		}
	}
	$('#menu').append(menuTag);
};

var addTabItem = function(name, path, flg) {

	var id = path.replaceAll("/", "");

	var tab = $("a[href=#" + id + "]");
	if (tab.length) {
		tab.tab("show");
		return;
	}

	var tabTitle = $("#tab-title");

	var liwidth = (name.length * 18) + 26;

	liwidth = flg ? liwidth : (liwidth + 3);

	var strTitle = "<li style='width:" + liwidth + "px;'><a href='#" + id
			+ "' data-toggle='tab'>" + name;
	if (!flg) {
		strTitle = strTitle + "<button class='close' onClick='removeTabItem(\""
				+ id + "\")'>&times;</button>";
	}
	strTitle = strTitle + "</a></li>";

	tabTitle.append(strTitle);

	var tabBody = $("#tab-body");
	tabBody.append("<div class='body-panel' id='" + id
			+ "'><iframe frameBorder='0' class='frame-style' src="
			+ contextPath + "/" + path + "></iframe></div>");

	$("a[href=#" + id + "]").tab("show");

};

var removeTabItem = function(id) {
	var parent = $("a[href=#" + id + "]").parent();

	if ("active" == parent[0].className) {
		var tab = parent.prev().children()[0].href;
		tab = tab.substr(tab.indexOf("#"));

		$("a[href=" + tab + "]").tab("show");
	}

	$("a[href=#" + id + "]").parent().remove();
	$("#" + id).remove();

};

var funLogout = function() {
	$.ajax({
		type : "post",
		url : contextPath + "/wizardframework/login/logout.do",
		success : function(msg) {
			if ("logout" === msg)
				location.href = contextPath + "/";
		}
	});
};