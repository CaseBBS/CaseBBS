<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发帖列表</title>
	<script src='<%=basePath%>script/ajaxupload.3.9.js' 	type='text/javascript'></script> 
	<script src='<%=basePath%>script/base/fileUpload.js' 	type='text/javascript'></script>  
<script src='<%=basePath%>script/base/messageList.js'
	type='text/javascript'></script>
</head>

<body>
	<div class="easyui-layout" style="width:100%;height:120%">
		<div data-options="region:'north',split:true,border:false"
			style="height:60px">
			<h2 id="h_title"></h2>
			<!-- <a onclick='MessageManage.backToMessageList()' style="float:right;width:80px;line-height:3;color:white;text-decoration: none;" href="javascript:void(0);">返回列表</a> --><a onclick='MessageManage.backToCalendar()'  href="javascript:void(0);" style="float:right;width:80px;line-height:3;color:black;text-decoration: none;">返回首页</a>
  	 				<img src="<%=basePath %>resource/calendarImage/newmessage.png" style="float:right;margin-right:30px"  height="30px" onclick="MessageManage.publishNewMessage()" />
			</div>
		<div data-options="region:'center',split:true,border:false">
			<div class="easyui-layout" style="width:100%;height:100%">
				<div data-options="region:'north',split:true"
					style="height:30%;">
					<div class="easyui-layout" style="width:100%;height:100%">
						<div data-options="region:'west',split:true" title="抢劫、抢夺"
							style="width:50%">
							<div id="qjGrid" style="padding:1px"></div>
						</div>
						<div data-options="region:'center',split:true" title="诈骗">
							<div id="zpGrid"></div>
						</div>
					</div>
				</div>
				<div data-options="region:'south',split:true"
					style="height:43%;">
					<div class="easyui-layout" style="width:100%;height:100%">
						<div data-options="region:'north',split:true" style="height:60%;">
							<div class="easyui-layout" style="width:100%;height:100%">
								<div data-options="region:'center',split:true" title="故障报送"  style="width:50%;">
									 <div id="gzGrid"></div>
								</div>
								<div data-options="region:'west',split:true"  title="入室盗窃"  style="width:50%;" >
									 <div id="rdGrid"></div>
								</div>
							</div>
						</div>
						<div data-options="region:'south',split:true"  title="工作建议" style="height:40%;">
							 <div id="jyGrid"></div>
						</div>
					</div>
				</div>
				<div data-options="region:'west',split:true" title="盗抢机动车"
					style="width:50%;overflow:hidden">
							<div id="jdcGrid"></div>
				</div>
				<div data-options="region:'center',split:true" title="盗窃车内物品"
					style="overflow:hidden">
					<div id="jdwGrid"></div>
				</div>
			</div>
		</div>
	</div> 
	<div style="display:none"> 
		<div id="div_newMessage" style="width:100%;height:100%">
					<table id="newmessageInfo" width="900px" height="300px" cellspacing="0" >
						<tr>
							<td style="width:80px;text-align:right;border:1px solid black;color:white;background:#555">
								<input type="hidden" id="msgId" />
								<label style="font-size:20px;font-weight:bold">标题：</label>
							</td>
							<td style="width:800px" colspan="3">
								<input type="text" id="txttitle" style="width:100%" class="easyui-validatebox" />
							</td>
						</tr>
						<tr>
							<td style="width:80px;text-align:right;border:1px solid black;color:white;background:#555">
								<label style="font-size:20px;font-weight:bold">类型：</label>
							</td>
							<td style="width:200px">
								<input id="txtarticalType"  style="width:204px" class="easyui-combobox"  data-options="valueField:'id',textField:'name',editable:false,data:[{id: 1,name: '机动车盗窃'},{id: 2,name: '车内物品盗窃'},{id: 3,name: '入室盗窃'},{id: 4,name: '抢夺、抢劫'},{id: 5,name: '诈骗'},{id: 6,name: '故障报送'},{id: 7,name: '工作建议'}]" />
							</td>
							<td style="width:80px;text-align:right">
								<label style="font-size:20px;font-weight:bold">标注：</label>
							</td>
							<td style="text-align:left">
								<input id="txisHost" type="checkbox" onclick="MessageManage.onCheckShop();" /> 
							</td>
						</tr>
						<tr>
							<td style="width:80px;text-align:right;border:1px solid black;color:white;background:#555"> 
								<label style="font-size:20px;font-weight:bold">内容：</label>
							</td>
							<td colspan="3">
								<textarea id="txtcontent" style="width:100%" rows="10"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width:80px;text-align:right;border:1px solid black;color:white;background:#555">
								<label style="font-size:20px;font-weight:bold">备注：</label>
							</td>
							<td colspan="3">
								<textarea id="txtdescription" style="width:100%" rows="2"></textarea>
							</td>
						</tr>
					</table> 
					<div id="attchMentsGrid" style="padding:10px;">
					
					</div>
   		 </div>
   	</div>
   	<div style="display:none">
   		<div id="attch_toolbar">
   			<p  style="height:30px;margin-top:15px">
   				<input type="text" class="easyui-validatebox" readonly="readonly" id="txtfilename"><a id="btnfindFile"  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"   >选择文件</a><span style="font-size:10px;color:red;margin-left:5px">*请先保存发帖内容再上传附件</span>
   			</p>
   		</div>
   	</div>
</body>
</html>
