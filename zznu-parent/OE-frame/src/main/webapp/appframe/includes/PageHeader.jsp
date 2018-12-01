<%@ page language="java" pageEncoding="UTF-8"%>
<%   
	String path = request.getContextPath();   
	String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>  

<%-- <%=request.getScheme() %>
<%=request.getServerName() %>
<%=request.getServerPort() %>
<%=request.getContextPath() %>  --%>

<base href="<%=basePath%>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">   -->
<!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<script type="text/javascript" src="appframe/js/common.js"></script>
<script type="text/javascript" src="appframe/js/PageEvent.js"></script>
<script type="text/javascript" src="appframe/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/appframe/css/customize.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/appframe/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/appframe/css/bootstrap/animate.min.css" />

<script type="text/javascript">
	$(document).ready(function(){
		$('body').data('appPath','<%=path%>');
	});
</script>