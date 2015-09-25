<%@ page language="java" pageEncoding="utf-8"%>



<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
%>
<!--[if lt IE 9]>
    <script src='<%=basePath%>js/sys/html5shiv.js' type='text/javascript'></script>
    <![endif]-->
   
<link href='<%=basePath%>css/easyui/icon.css'	media='all' rel='stylesheet' type='text/css' />
<link href='<%=basePath%>css/jquery.loadmask.css'	media='all' rel='stylesheet' type='text/css' />
<link href='<%=basePath%>css/easyui/black/easyui.css'	media='all' rel='stylesheet' type='text/css' /> 
<link href='<%=basePath%>css/fontawesome/css/font-awesome.min.css'	media='all' rel='stylesheet' type='text/css' />  
<link href='<%=basePath%>css/dialog.default.css'	media='all' rel='stylesheet' type='text/css' /> 

<script src='<%=basePath%>script/jquery-1.11.1.min.js'	type='text/javascript'></script>
<script src='<%=basePath%>script/easyui/jquery.easyui.1.4.1.min.js'	type='text/javascript'></script>
<script src='<%=basePath%>script/jquery.loadmask/jquery.loadmask.min.js'	type='text/javascript'></script>
<script src='<%=basePath%>script/jquery.artDialog.js'	type='text/javascript'></script>

<script src='<%=basePath%>script/json2.js' type='text/javascript'></script>
<script src='<%=basePath%>script/easyui/easyui-lang-zh_CN.js' type='text/javascript'></script>
<script src='<%=basePath%>script/common.js' type='text/javascript'></script>
<input type="hidden" id="userName" name="userName" value="${requestScope.userName}">
<input type="hidden" id="userId" name="userId" value="${requestScope.userId}">
<input type="hidden" id="organName" name="pageStart" value="${requestScope.organName}">
<input type="hidden" id="organId" name="organName" value="${requestScope.organId}">
<input type="hidden" id="userMark" name="userMark" value="${requestScope.userMark}"> 
<input type="hidden" id="years" name="years" value="${requestScope.years}"> 
<input type="hidden" id="months" name="months" value="${requestScope.month}"> 
<input type="hidden" id="date" name="months" value="${requestScope.date}"> 
<input type="hidden" id="htitle" name="months" value="${requestScope.title}"> 
<input type="hidden" id="msgId" name="months" value="${requestScope.msgId}"> 

