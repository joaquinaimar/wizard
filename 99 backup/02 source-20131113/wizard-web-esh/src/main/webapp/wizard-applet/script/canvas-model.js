Ext.ns("wizard.applet.canvas.Util");

wizard.applet.canvas.Util.getPosTop = function(obj) {
    var top;
    top = obj.offsetTop;
    while (obj.tagName != "BODY") {
        obj = obj.offsetParent;
        top = top + obj.offsetTop;
    }
    return top;
}

wizard.applet.canvas.Util.getPosLeft = function(obj) {
    var left;
    left = obj.offsetLeft;
    while (obj.tagName != "BODY") {
        obj = obj.offsetParent;
        left = left + obj.offsetLeft;
    }
    return left;
}

wizard.applet.canvas.Util.getMouseSite = function(obj) {

    var mouse = {};

    if (window.event) {
        mouse.x = window.event.clientX;
        mouse.y = window.event.clientY;
    } else {
        mouse.x = event.clientX;
        mouse.y = event.clientY;
    }

    if (obj) {
        mouse.x = mouse.x - obj.x;
        mouse.y = mouse.y - obj.y;
    }

    return mouse;
}

function Paint2D(context) {
    this.createLine = function(start, end) {
        context.moveTo(start.x, start.y);
        context.lineTo(end.x, end.y);
    };

    this.createRectangle = function(start, end) {
        context.moveTo(start.x, start.y);
        context.lineTo(end.x, start.y);
        context.lineTo(end.x, end.y);
        context.lineTo(start.x, end.y);
    };
}