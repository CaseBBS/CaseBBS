<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.casebbs.viewmodel.UserQuery"%>


<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/view/lib.jsp"%>
	<base href="<%=basePath%>">
	<%-- <script src='<%=basePath%>script/ajaxupload.3.9.js' 	type='text/javascript'></script> 
	<script src='<%=basePath%>script/base/fileUpload.js' 	type='text/javascript'></script>   --%>
	<script src='<%=basePath%>script/base/dutycalendar.js' 	type='text/javascript'></script>   
	<link href='<%=basePath%>resource/calendarImage/dateStyle.css'	media='all' rel='stylesheet' type='text/css' /> 
    <title>案件侦查互动交流平台</title> 
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  </head>
  
  <body style="padding:0"> 
   <!--日历 s-->
	<div class="dateBox">
		<!--日历主窗体 s-->
    	<div class="dateBoxMain"> 
    		<div class="dateBoxMainTItle">
        		<div class="dateBoxMainTItleTxt1">发帖汇总</div>
	            <div class="dateBoxMainTItleBox">
	            	<div class="dateBoxMainTItleBoxMain">
	                	<ul>
	                    	<li style="margin-top: 10px;"><img src="<%=basePath %>resource/calendarImage/dateLast.png" onclick="getDateClick('last')" /></li>
	                        <li><span class="lispan" id="sp_years"></span></li>
	                        <li><span class="lispan" id="sp_month"></span></li>
	                        <li style="margin-top: 10px;"><img  class="liimg" src="<%=basePath %>resource/calendarImage/dateNext.png" onclick="getDateClick('next')" /></li>
                    	</ul>
	                </div>
	            </div>	 
	            <!-- 清楚本月报备数据以及清空剪切板 -->
	            <div class="dateBoxMainTItleTxt">
	            	<div class="dateBoxMainTItleTxtDIv">
	            		<img src="<%=basePath %>resource/calendarImage/newmessage.png"  height="30px" onclick="publishNewMessage()" />
	            		<input id="sch_name" type="text" style="height:35px" /> 
	            		<a id="btnSearch" href="javascript:void(0);" onclick="searchAction()" class="easyui-linkbutton" iconcls="icon-search" plain="true">查询</a>
	            			            		<!-- 
	                    <a href="javascript:void(0);" onclick="clearAlldutyData()"><img src="<%=basePath %>dutydata/dutyprepare/calendarImage/canelpast.png" alt="清除当月所有报备数据" title="清除当月所有报备数据" /></a>
	                    <a href="javascript:void(0);" onclick="clearClipbord()"><img src="<%=basePath %>dutydata/dutyprepare/calendarImage/past.png" alt="取消剪贴板数据" title="取消剪贴板数据"  /></a>
	                	 -->
	                </div>
	            </div>
        	</div>
        	<!-- 日历主界面 -->
        	<div id="dateBoxMain" class="dateBoxMain">
	        	 <table width="100%" height="16px;" cellpadding="0" cellspacing="0">
                    <tr>
	                    <th style="text-align:center;color:red"> 日</th>
	                    <th style="text-align:center;color:black"> 一</th>
	                    <th style="text-align:center;color:black"> 二</th>
	                    <th style="text-align:center;color:black"> 三</th>
	                    <th style="text-align:center;color:black"> 四 </th>
	                    <th style="text-align:center;color:black"> 五</th>
	                    <th style="text-align:center;color:red"> 六</th>
                    </tr>
	            </table>
		        <div class="dateBoxMainDate" id="dateBoxMainDate" style="overflow:hidden">
		            <table id="dateTable" class="" width="100%" height="100%" style="font-size:12px" >
		                <tbody id="dateBody"> 
		                </tbody> 
		            </table>
		        </div> 
        	</div>
    	</div>
	</div> 
	<div style="display:none"> 
		<div id="div_newMessage" style="width:100%;height:100%">
					<table id="newmessageInfo" width="900px" height="300px" cellspacing="0">
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
								<input id="txtarticalType"  style="width:204px" class="easyui-combobox"  data-options="valueField:'id',editable:false,textField:'name',data:[{id: 1,name: '盗抢机动车'},{id: 2,name: '盗窃车内物品'},{id: 3,name: '入室盗窃'},{id: 4,name: '抢夺、抢劫'},{id: 5,name: '诈骗'},{id: 6,name: '故障报送'},{id: 7,name: '工作需求'}]" />
							</td>
							<td style="width:100px;text-align:right;">
								<label style="font-size:20px;font-weight:bold">标注：</label> <span style="color:red;font-size:10px;">*勾选后发帖标题显著提示</span>
							</td>
							<td style="text-align:left">
								<input id="txisHost" type="checkbox"  onclick="onCheckShop();"  />
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
					<div id="attchMentsGrid" style="padding:10px; width:900px;heigth:100%">
					
					</div>
   		 </div>
   	</div>
   	<div style="display:none">
   		<div id="attch_toolbar">
   			<p  style="height:30px;margin-top:15px">
   				<span>上传附件
	   				<form id="fileForms" name="fileForms" action="fileUpload/uploadAttch.do"  enctype="multipart/form-data" method="post" style="margin:0;padding:0;">
					       	<input type="hidden" name="messageid" id="messageid" />
					       	<input type="file" name="file" id="jfile"  onChange="excelChange(this);">
					</form>
				</span>
   				<!-- <input type="text" class="easyui-validatebox" readonly="readonly" id="txtfilename"><a id="btnfindFile"  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"   >选择文件</a><span style="font-size:10px;color:red;margin-left:5px">*请先保存发帖内容再上传附件</span> -->
   			</p>
   		</div>
   	</div>
  </body>
</html>
