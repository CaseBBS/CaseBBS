<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title> 
	<script src='<%=basePath%>script/base/messageInfo.js' 	type='text/javascript'></script>
  </head>
  
  <body>
   <div class="easyui-layout"  style="width:100%;height:100%" >
   		<div data-options="region:'center',split:true" title="详细信息" > 
  	 		<div class="easyui-layout" data-options="fit:true">
  	 			<div data-options="region:'north',split:false,border:true" style="height:100px">
  	 				<h2 id="h_title" style="color:black"></h2>
  	 				<a onclick='MessageManage.backToMessageList()' style="float:right;width:80px;line-height:3;color:black;text-decoration: none;" href="javascript:void(0);">返回列表</a><a onclick='MessageManage.backToCalendar()'  href="javascript:void(0);" style="float:right;width:80px;line-height:3;color:black;text-decoration: none;">返回首页</a>
  	 				<h3 id="h_creator" style="float:left;width:250px;color:black"></h3><h3 id="h_createTime" style="float:left;color:black"></h3>
  	 				<img src="<%=basePath %>resource/calendarImage/feed.png" style="float:right;margin-right:30px"  height="30px" onclick="MessageManage.feedMessage()" /> 
  	 			</div>
				<div data-options="region:'south',split:true,border:true" title="回复列表" style="height:230px">
					<table id="tbl_feedItems" style="width:100%;color:black"> 
					</table>
				</div>
				<div data-options="region:'center',split:true,border:false" style="overflow:hidden" > 
					<div class="easyui-tabs" data-options="border:false" style="width:100%;height:350px;overflow-x:hidden">
						<div title="内容详情" style="padding:3px">
							<h2 id="h_content" style="color:black"></h2>
							<h3 id="h_desc" style="color:black"></h3>
						</div>
						<div title="相关附件" style="padding:3px">
							<div id="attchMentsGrid"></div>
						</div>
					</div>
				</div> 
  	 		</div>
   		</div>
   </div>
   
   <div style="display:none;">
   		<div id="div_feedMessage">
   			<span id="h_title1" style="font-size:20px;font-weight:bold"></span>
   			<table style="width:580px;height:100px" >
   				<tr>
   					<td style="width:75px">
   						<label>回复内容:</label>
   					</td>
   					<td>
   						<textarea id="feedContent" style="width:100%" rows="4" ></textarea>
   					</td>
   				</tr> 
   			</table>
   		</div>
   </div>
  </body>
</html>
