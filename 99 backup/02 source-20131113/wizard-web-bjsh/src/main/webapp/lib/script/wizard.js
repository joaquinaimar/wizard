Wizard = {
    ajaxSubmit : function(form, option) {
        var tagStr = '<div class="wizard-overlay"></div>';
        tagStr = tagStr + '<div class="wizard-loading">' + (option.loadstr || 'loading') +'...</div>';
        $(document.body).append(tagStr);
        var success = option.success;
        option.success = function(responseText, statusText, xhr) {
            success(responseText, statusText, xhr);
            $(".wizard-loading").remove();
            $(".wizard-overlay").remove();
        };
        option.error = option.error || function () {
            $(".wizard-loading").remove();
            $(".wizard-overlay").remove();
            Wizard.alert("异常", "与服务器通信异常！");
        };

        $(form).ajaxSubmit(option);
    },
    alert : function(title, content, fnClose) {

        var options = {
            fnClose : fnClose
        };

        var tagStr = '<div class="wizard-overlay"></div>';
        tagStr = tagStr + '<div class="alert alert-error fade in wizard-dialog-win">';
        tagStr = tagStr + '<button type="button" class="close" data-dismiss="alert">&times;</button>';
        tagStr = tagStr + '<h4 class="alert-heading">' + title + '</h4>';
        tagStr = tagStr + '<div class="wizard-dialog-content">' + content + '</div></div>';
        $(document.body).append(tagStr);

        $("[data-dismiss=alert]").click(function() {
            $(".wizard-overlay").remove();
            if (fnClose) {
                options.fnClose();
            }
        });

        $(".alert").alert();
    }

};