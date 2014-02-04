$(document).ready(function() {
	getMenu();
	getHomePage();
	$("#btnLogout").bind("click", funLogout);
});

var createMenu = function(menu) {
	if (menu instanceof String)
		menu = eval("(" + menu + ")");
	if (!(menu instanceof Array))
		return;

	var menuTag = '';

	for (var i = 0; i < menu.length; i++) {
		menuTag += '<div class="panel panel-default"><div class="panel-heading"><h4 class="panel-title">';
		menuTag += '<a class="head" href="#menu' + menu[i].id
				+ '" data-toggle="collapse" ';
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
			for (var j = 0; j < menu[i].child.length; j++) {
				menuTag += '<a href="#" class="list-group-item" onclick="addTabItem(\''
						+ menu[i].child[j].name
						+ '\', \''
						+ menu[i].child[j].url
						+ '\');" >'
						+ menu[i].child[j].name + '</a>';
			}
			menuTag += '</div>';
		}
		menuTag += '</div>';
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

	var liwidth = (name.charLength() * 0.5) + 2.5;

	liwidth = flg ? liwidth : (liwidth + 1);

	var strTitle = "<li style='width:" + liwidth + "em;'><a href='#" + id
			+ "' data-toggle='tab'>" + name;
	if (!flg) {
		strTitle = strTitle + "<button class='close' onClick='removeTabItem(\""
				+ id + "\")'>&times;</button>";
	}
	strTitle = strTitle + "</a></li>";

	tabTitle.append(strTitle);

	var tabBody = $("#tab-body");
	tabBody.append("<div class='tab-pane fade' id='" + id
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

var getMenu = function() {
	Wizard.ajax({
		type : "post",
		dataType : "json",
		url : contextPath + "/wizard/menu/getMenu.do",
		success : function(obj) {
			var data = obj.data;
			if (data)
				createMenu(data);
		}
	});
};

var getHomePage = function() {

	Wizard.ajax({
		type : "post",
		dataType : "json",
		url : contextPath + "/wizard/menu/getHomePage.do",
		success : function(obj) {
			var data = obj.data;
			if (data)
				addTabItem(data.name, data.url, true);
		}
	});
};

var funLogout = function() {
	$.ajax({
		type : "post",
		url : contextPath + "/wizard/login/logout.do",
		success : function(msg) {
			if ("logout" === msg)
				location.href = contextPath + "/";
		}
	});
};