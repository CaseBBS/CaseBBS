<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>发帖列表</title>
<%-- 	<script src='<%=basePath%>script/ajaxupload.3.9.js' 	type='text/javascript'></script> 
	<script src='<%=basePath%>script/base/fileUpload.js' 	type='text/javascript'></script>   --%>
<script src='<%=basePath%>script/base/messageList.js'
	type='text/javascript'></script>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
</head>

<body>
	<div class="easyui-layout" style="width:100%;height:1200px">
		<div data-options="region:'north',split:true,border:false"
			style="height:60px">
			<h2 id="h_title"></h2>
			<!-- <a onclick='MessageManage.backToMessageList()' style="float:right;width:80px;line-height:3;color:white;text-decoration: none;" >返回列表</a> --><a onclick='MessageManage.backToCalendar()'  style="float:right;width:80px;line-height:3;color:black;text-decoration: none;">返回首页</a>
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
						<div data-options="region:'south',split:true"  title="工作需求" style="height:40%;">
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
					<table id="newmessageInfo" width="900px" height="300px" cellspacing="0" border="0" >
						<tr>
							<td style="width:80px;text-align:right;">
								<input type="hidden" id="msgId" />
								<label style="font-size:20px;font-weight:bold">标题：</label>
							</td>
							<td style="width:800px" colspan="3">
								<input type="text" id="txttitle" style="width:100%" class="easyui-validatebox" />
							</td>
						</tr>
						<tr>
							<td style="width:80px;text-align:right;">
								<label style="font-size:20px;font-weight:bold">类型：</label>
							</td>
							<td style="width:200px">
								<input id="txtarticalType"  style="width:204px" class="easyui-combobox"  data-options="valueField:'id',textField:'name',editable:false,data:[{id: 1,name: '机动车盗窃'},{id: 2,name: '车内物品盗窃'},{id: 3,name: '入室盗窃'},{id: 4,name: '抢夺、抢劫'},{id: 5,name: '诈骗'},{id: 6,name: '故障报送'},{id: 7,name: '工作需求'}]" />
							</td>
							<td style="width:80px;text-align:right">
								<label style="font-size:20px;font-weight:bold">标注：</label>
							</td>
							<td style="text-align:left">
								<input id="txisHost" type="checkbox" onclick="MessageManage.onCheckShop();" /><span style="color:red;font-size:10px;">*勾选后发帖标题显示新标签提示</span>
							</td>
						</tr>
						<tr>
							<td style="width:80px;text-align:right;"> 
								<label style="font-size:20px;font-weight:bold">内容：</label>
							</td>
							<td colspan="3">
								<textarea id="txtcontent" style="width:100%" rows="10"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width:80px;text-align:right;">
								<label style="font-size:20px;font-weight:bold">备注：</label>
							</td>
							<td colspan="3">
								<textarea id="txtdescription" style="width:100%" rows="2"></textarea>
							</td>
						</tr>
					</table>  
						<div> 
				   			<p  style="height:30px;margin-top:15px">
				   				<span style="font-size:20px;font-weight:bold">上传附件
								</span>
								<div style="width:100%;margin-left:50px;">
					   				<form id="fileForms" name="fileForms" action="fileUpload/uploadAttch.do"  enctype="multipart/form-data" method="post" style="margin:0;padding:0;">
									       	<input type="hidden" name="messageid" id="messageid" />
									       	<p>
									       		<input type="file" style="width:300px" name="file1" />
								       		</p>
									       	<p>
									       	<input type="file" style="width:300px"  name="file2" /> 
								       		</p>
									       	<p>
									       	<input type="file"  style="width:300px" name="file3" /> 
								       		</p>
									       	<p>
									       	<input type="file"  style="width:300px" name="file4" />
									       	</p>
								       		<p>
									       	<input type="file"  style="width:300px" name="file5" />
								       		</p>
									</form>
								</div>
				   				<!-- <input type="text" class="easyui-validatebox" readonly="readonly" id="txtfilename"><a id="btnfindFile"  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"   >选择文件</a><span style="font-size:10px;color:red;margin-left:5px">*请先保存发帖内容再上传附件</span> -->
				   			</p>
				   		</div>
   		 </div>
   	</div> 
</body>
</html>
