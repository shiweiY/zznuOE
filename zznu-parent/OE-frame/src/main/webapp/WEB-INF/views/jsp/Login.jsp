<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
<head>
<title>Login</title>
<jsp:include page="/appframe/includes/PageHeader.jsp"></jsp:include>
</head>

<body>
	<div id="login" class="panel panel-default">
		<div class="panel-heading">
			登录
		</div>
		<div class="panel-body">
			<div class="input-group">
            	<span class="input-group-addon">用户名</span>
				<input type="text" id="username" name="username" datakey="user" class="form-control" placeholder="6位以上字符或数字组成" />
        	</div>
        	<br><br>
        	<div class="input-group">
        		<span class="input-group-addon">密码&nbsp;&nbsp;&nbsp;</span>
				<input type="password" class="form-control" id="password" name="password" placeholder="6位以上字符和数字组成" />
        	</div>
        	<br>
			<button id="signIn" name="signIn" class="btn btn-primary btn-block" onclick="signIn()">登录</button>
			<button id="signUp" name="signUp" class="btn btn-primary btn-block" onclick="signUp()">注册</button>
			
		</div>
	</div>
</body>
</html>