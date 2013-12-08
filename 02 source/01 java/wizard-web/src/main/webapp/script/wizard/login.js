document.write('<link rel="stylesheet" type="text/css" href="' + contextPath
		+ '/lib/allinone_thumbnailsBanner/allinone_thumbnailsBanner.css">');
document
		.write('<script type="text/javascript" src="'
				+ contextPath
				+ '/lib/allinone_thumbnailsBanner/js/allinone_thumbnailsBanner.js"></script>');

$(function(){
	$('#Banner_simple').allinone_thumbnailsBanner({
		skin: 'simple',
		numberOfThumbsPerScreen:4,
		width:600,
		height:375,
		thumbsReflection:0,
		defaultEffect: 'random'
	});
    $("#btnLogin").bind("click", funLogin);
});

var funLogin = function() {
    Wizard.ajaxSubmit("#loginForm", {
        dataType : "json",
        timeout : 10000,
        loadstr : "正在登录，请稍候",
        success : function(responseText, statusText, xhr) {
            if (responseText.data) {
                location.href = contextPath + "/main";
            } else {
                Wizard.alert("错误", "用户名或密码不正确！");
            }
        }
    });
};
