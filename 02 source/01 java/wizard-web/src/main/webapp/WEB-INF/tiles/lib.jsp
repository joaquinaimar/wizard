<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Wizard</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/resource/image/wizard-small.ico" />

<!-- jquery-ui -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/jquery-ui/themes/base/minified/jquery-ui.min.css" />
<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css" />
<!-- datatables -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/datatables/media/css/jquery.dataTables.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/datatables/media/css/jquery.dataTables_themeroller.css" />
<!-- expand -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/lib/wizard.css" />
<!-- common -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/common.css" />
<!-- custom -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}<tiles:insertAttribute name="style" />">

<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery/jquery.min.js"></script>
<!-- jquery-ui -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<!-- jquery-form -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery-form/jquery.form.js"></script>
<!-- bootstrap -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- datatables -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/datatables/media/js/jquery.dataTables.min.js"></script>
<!-- highcharts -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/highcharts/highcharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/highcharts/highcharts-more.js"></script>
<!-- expand -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/scriptx.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/wizard.js"></script>
<!-- common -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/common.js"></script>

<!-- custom -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}<tiles:insertAttribute name="script" />"></script>

</head>
<body>
	<tiles:insertAttribute name="body" />
</body>
</html>