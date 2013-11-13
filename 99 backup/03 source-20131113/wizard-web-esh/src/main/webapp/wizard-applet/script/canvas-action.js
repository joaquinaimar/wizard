var canvasObj = {};

var mouse = {};

function initCanvas(id) {

    canvasObj.canvas = document.getElementById(id);
    canvasObj.context = canvasObj.canvas.getContext('2d');
    canvasObj.x = wizard.applet.canvas.Util.getPosLeft(canvasObj.canvas);
    canvasObj.y = wizard.applet.canvas.Util.getPosTop(canvasObj.canvas);

    canvasObj.graphType = "line";

    canvasObj.canvasEvent = new CanvasEvent();
    canvasObj.canvas.onmousedown = canvasObj.canvasEvent.mousedown;
    canvasObj.canvas.onmouseup = canvasObj.canvasEvent.mouseup;
    canvasObj.canvas.onmousemove = canvasObj.canvasEvent.mousemove;
    canvasObj.canvas.onmouseout = canvasObj.canvasEvent.mouseout;

    canvasObj.canvasOperate = new CanvasOperate(canvasObj.context);
    canvasObj.canvasOperate.clearCanvas();

    canvasObj.context.globalAlpha = 1;

}

function CanvasEvent() {

    this.mousedown = function() {
        mouse.down = wizard.applet.canvas.Util.getMouseSite(canvasObj);
    }

    this.mouseup = function() {
        if ("pen" == canvasObj.graphType || "rubber" == canvasObj.graphType) {
            mouse.down = null;
            return;
        }
        if ("line" == canvasObj.graphType) {
            mouse.up = wizard.applet.canvas.Util.getMouseSite(canvasObj);
            canvasObj.canvasOperate.paintLine(mouse.down, mouse.up);
        }
        mouse.down = null;
    }

    this.mousemove = function() {

        mouse.move = wizard.applet.canvas.Util.getMouseSite(canvasObj);

        if ("pen" == canvasObj.graphType && null != mouse.down) {
            canvasObj.canvasOperate.paintLine(mouse.down, mouse.move);
            mouse.down = mouse.move;
        } else if ("rubber" == canvasObj.graphType && null != mouse.down) {
            var size = 20;
            canvasObj.canvasOperate.rubber(mouse.down, size);
            mouse.down = mouse.move;
        }
    }

    this.mouseout = function() {

    }

}

function CanvasOperate(context) {

    var paint = new Paint2D(context);

    function init() {
        context.beginPath();
    }
    function destory() {
        context.closePath();
        context.strokeStyle = "red";
        context.fill();
        context.stroke();
    }

    this.paintLine = function(start, end) {
        init();
        paint.createLine(start, end);
        destory();
    }

    this.rubber = function(site, size) {
        context.fillStyle = "#ffffff";
        context.fillRect(site.x - size / 2, site.y - size / 2, size, size);
    }

    this.clearCanvas = function() {
        context.fillStyle = "#ffffff";
        context.fillRect(0, 0, 1200, 550);
    }

}