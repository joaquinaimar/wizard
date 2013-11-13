<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ include file="/lib/lib.html"%>
<html>
<head>
<script type="text/javascript">
    function getImg() {
        var img = document.getElementById("VcodeLogin");
        var ctx = document.getElementById("canvas").getContext('2d');
        ctx.drawImage(img, 0, 0);
        var c = ctx.getImageData(0, 0, img.width, img.height);

        $.ajax({
            type : 'post',
            url : './GridTable.aspx/call',
            contentType : "application/json; charset=utf-8",
            data : '{"userinfo":[{"name":"zs","age":"21"},{"name":"ls","age":"25"}]}',
            dataType : 'json',
            success : function(result) {
                
                
                
            }
        });

    }
</script>
</head>
<body>
	<div class="height:100px;width:400px;">
		<button id="btnGetImg" onClick="getImg()">获取图片</button>
		<canvas id="canvas"></canvas>
	</div>
	<img id="VcodeLogin"
		src="http://tp.tjzzb.gov.cn/controls/ValidateCodeImage.aspx">
</body>
</html>