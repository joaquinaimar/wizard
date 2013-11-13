$(document).ready(function() {
    addTabItem("首页", "wizard/system/welcome", true);
});

function addTabItem(name, path, flg) {

    var id = path.replaceAll("/", "");

    var tab = $("a[href=#" + id + "]");
    if (tab.length) {
        tab.tab("show");
        return;
    }

    var tabTitle = $("#tab-title");

    var liwidth = (name.length * 17) + 22;

    liwidth = flg ? liwidth : (liwidth + 3);

    var strTitle = "<li style='width:" + liwidth + "px;'><a href='#" + id + "' data-toggle='tab'>" + name;
    if (!flg) {
        strTitle = strTitle + "<button class='close' onClick='removeTabItem(\"" + id
                + "\")'><sup>&times;</sup></button>";
    }
    strTitle = strTitle + "</a></li>";

    tabTitle.append(strTitle);

    var tabBody = $("#tab-body");
    tabBody.append("<div class='tab-pane body-pane' id='" + id + "'><iframe frameBorder='0' class='frame-style' src="
            + contextPath + "/" + path + "></iframe></div>");

    $("a[href=#" + id + "]").tab("show");

}

function removeTabItem(id) {
    var parent = $("a[href=#" + id + "]").parent();

    if ("active" == parent[0].className) {
        var tab = parent.prev().children()[0].href;
        tab = tab.substr(tab.indexOf("#"));

        $("a[href=" + tab + "]").tab("show");
    }

    $("a[href=#" + id + "]").parent().remove();
    $("#" + id).remove();

}