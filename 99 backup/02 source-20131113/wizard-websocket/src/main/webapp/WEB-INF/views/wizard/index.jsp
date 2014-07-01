<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>WEBSOCKET</title>
<script type="text/javascript">
    window.onload = function() {
        if ('WebSocket' in window) {
            // 创建websocket实例
            var socket = new WebSocket('ws://localhost:8080/wizard-websocket/entrance');
            //打开
            socket.onopen = function(event) {
                // 发送
                socket.send('I am the client and I\'m listening!');
                // 监听
                socket.onmessage = function(event) {
                    console.log('Client received a message', event);
                };
                // 关闭监听
                socket.onclose = function(event) {
                    console.log('Client notified socket has closed', event);
                };
                // 关闭
                //socket.close()
            };
        } else {
            alert('本浏览器不支持WebSocket哦~');
        }
    };
</script>
</head>
<body>
</body>
</html>