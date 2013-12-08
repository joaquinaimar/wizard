<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE>
<html>
<head>
<title>Wizard</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/resource/image/wizard-small.ico" />

<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>

<!-- jquery-ui -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-ui/themes/base/minified/jquery-ui.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-ui/ui/minified/jquery-ui.min.js"></script>

<!-- jquery-form -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-form/jquery.form.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>

<!-- common -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/common.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/common.js"></script>

<!-- custom -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}<tiles:insertAttribute name="style" />">
<script type="text/javascript"
	src="${pageContext.request.contextPath}<tiles:insertAttribute name="script" />"></script>

</head>
<body>
	<tiles:insertAttribute name="body" />
</body>
</html>