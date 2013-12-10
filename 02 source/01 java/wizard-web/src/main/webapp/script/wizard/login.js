$(function(){
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
