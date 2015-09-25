<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<script src='<%=basePath%>script/dutycalendar.js' 	type='text/javascript'></script>  
	<link href='<%=basePath%>resource/calendarImage/dateStyle.css'	media='all' rel='stylesheet' type='text/css' /> 
    <title>案件侦查互动交流平台</title> 
  </head>
  
  <body>
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
	            		<a id="btnSearch" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true">查询</a>
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
  </body>
</html>
