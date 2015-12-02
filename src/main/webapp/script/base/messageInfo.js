var m_messageId;
var m_feedInfo_dlg;
var m_userName;
var m_userMark;
var m_dates ;
$(function() { 
	 
	m_messageId = $("#msgId").val(); 
	$("#messageid").val(m_messageId);
	$("#itemmessageid").val(m_messageId);
	m_dates =$("#date").val();
	m_userMark = $("#userMark").val();
	
	m_userName = $("#userName").val();
	$("#itemCreator").val(m_userName);
	
	if(m_messageId==""||m_messageId == undefined){
		$.messager.alert("提示","<span style='color:black'>传入帖子id为空，获取数据失败，返回之前页面</span>","warning");
		window.history.back(-1);
	}
	MessageManage.initCtrlData(); 
	MessageManage.loadFeedItems(); 
	MessageManage.loadFeedAttchs(); 
	MessageManage.loadFeedItemAttchs();
});
var MessageManage = {
	initCtrlData:function(){
		$.ajax({
			url : "caseBBS/getMessageInfoById.do?id="+m_messageId,
			type : "POST",
			dataType : "json",
			async : true,
			success : function(req) {
				if (req.isSuccess) {
					 $("#h_title").html("标题："+req.data.title);
					 $("#h_title1").html("标题："+req.data.title);
					 $("#h_creator").html("作者：    "+req.data.creatorName);
					 if(req.data.creatorName==m_userName||m_userName == 'xzj001'){
							$("#div_uploadAttch").removeAttr("style");
					 } 
					 $("#h_createTime").html("发表时间："+req.data.createTime);
					 $("#h_content").html(req.data.summary);
					 $("#h_desc").html(req.data.description); 
				}else{
					$.messager.alert("提示","<span style='color:black'>获取发帖内容出错，返回之前页面</span>","warning");
					window.history.back(-1);
				}
			}
		});
	},
	loadFeedItems:function(){
		$.ajax({
			url : "caseBBS/getMessageItemById.do?id="+m_messageId,
			type : "POST",
			dataType : "json",
			async : true,
			success : function(req) {
				if (req.isSuccess) {
					var html = '';
					for(var i = 0;i<req.rows.length;i++){
						html += '<tr style="height:45px">';
						html += '<td style="width:80px;text-align:right;font-size:12px;">';
						html += req.rows[i].creatorName+":";
						html += '</td><td style="text-align:left;"><span style="font-size:16px;color:blue;font-weight:bold;">';
						html += req.rows[i].contents;
						html += '</span></td>';
						html += '<td>'+req.rows[i].createTime+'</td></tr>';
					}
					$("#tbl_feedItems").append(html);
				}else{
					$.messager.alert("提示","<span style='color:black'>获取发帖内容出错，返回之前页面</span>","warning");
					window.history.back(-1);
				}
			}
		}); 
	},
	loadFeedAttchs:function(){
		$('#attchMentsGrid').datagrid({
			url : 'caseBBS/getMessageAttchs.do?id='+m_messageId,
			fitColumns : true,	
			rownumbers : true,
			pagination : false,
			pageNumber : 1,
			pageSize : 10,
			nowrap : false,
			idField : 'id',          
			checkOnSelect: false,
	        selectOnCheck: true, 
	        onClickRow: MessageManage.attchsGridclickRow, 
			columns : [ [ 
			              { title : 'id', field : 'id', hidden : true },
			              { title : '附件名称', field : 'name', align : 'left', width : 150 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }}, 
			              { title : '附件类型', field : 'attchType', align : 'left', width : 100 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }},
			              { title : '上传时间', field : 'uploadTime', align : 'left', width : 100 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }},
			              { title : '操作',   field : 'operation', align : 'center', width : 100,formatter:function(value,rowData,index){
		            		  return "<a style='color:blue;text-decoration: none;' onclick=MessageManage.downLoadItem('"+rowData.attchUrl+"') >下载</a>";
			              }}
			          ] ]
		});
	},
	loadFeedItemAttchs:function(){
		$('#itemAttchGrid').datagrid({
			url : 'caseBBS/getMessageItemAttchs.do?id='+m_messageId,
			fitColumns : true,	
			rownumbers : true,
			pagination : false,
			pageNumber : 1,
			pageSize : 10,
			nowrap : false,
			idField : 'id',          
			checkOnSelect: false,
	        selectOnCheck: true, 
	        onClickRow: MessageManage.itemAttchsGridclickRow, 
			columns : [ [ 
			              { title : 'id', field : 'id', hidden : true },
			              { title : '附件名称', field : 'name', align : 'left', width : 150 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }}, 
			              { title : '附件类型', field : 'attchType', align : 'left', width : 100 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }},
			              { title : '上传时间', field : 'createTime', align : 'left', width : 100 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }},
			              { title : '上传人员', field : 'creator', align : 'left', width : 100 ,formatter:function(value,rowData,index){
			            	  return "<span style='color:black'>"+value+"</span>";
			              }},
			              { title : '操作',   field : 'operation', align : 'center', width : 100,formatter:function(value,rowData,index){
		            		  return "<a style='color:blue;text-decoration: none;' onclick=MessageManage.downLoadItem('"+rowData.attchUrl+"') >下载</a>";
			              }}
			          ] ]
		});
	},
	attchsGridclickRow:function(index, data){ 
		   $("#attchMentsGrid").datagrid("unselectRow", index); 
	},
	itemAttchsGridclickRow:function(index,data){
		 $("#itemAttchGrid").datagrid("unselectRow", index); 
	},
	backToCalendar:function(){ 
		window.navigate("/casebbs/caseBBS/gotoIndex.do?date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark);
		//window.location.href="/casebbs/caseBBS/gotoIndex.do?date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark;
	},
	backToMessageList:function(){
		window.navigate("/casebbs/caseBBS/gotoArticleList.do?date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark);
		//window.location.href="/casebbs/caseBBS/gotoArticleList.do?date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark;
	},
	feedMessage:function(){
		$("#feedContent").val("");
		m_feedInfo_dlg = art
		.dialog({
			id : 'dlgfeedInfo',
			title : '消息回复',  
			height: 100,
			width: 390,
			content : document.getElementById("div_feedMessage"),
			lock : false,
            button: [
                     {
                         name: '回复',
                         callback: function () {
                        	 MessageManage.feedAction();
                        	 m_feedInfo_dlg.close();
                             return false;
                         },
                         focus: true
                     },
                     {
                         name: '取消'
                     }
                ]
		});
	},
	feedAction:function(){
		var feedInfo ={};
		feedInfo.id = 0;
		feedInfo.articalId = m_messageId;
		var contents = $.trim($("#feedContent").val());
		if(contents.length==0){
			$.messager.alert("系统提示","<span style='color:black'>回复内容不能为空</span>","info",function(){
				return;
			}); 
			return;
		}
		feedInfo.contents = $("#feedContent").val();
		feedInfo.isneeded = false;
		feedInfo.needUnitId = 1;
		feedInfo.needUnitName ="案件处理交流机构";
		feedInfo.creator = 1;
		feedInfo.creatorName = m_userName;
		feedInfo.organId = 1;
		feedInfo.organName = "案件处理交流机构";
		feedInfo.createTime = getCurrentDate();
		$.ajax({
			url : "caseBBS/saveMessageItem.do",
			type : "POST",
			dataType : "json",
			data:feedInfo,
			async : true,
			success : function(req) {
				if (req.isSuccess) { 
					$.messager.alert("系统提示","<span style='color:black'>回复成功</span>","info",function(){
						excelItemChange();
					}); 
					var html = "";
					html += '<tr style="height:45px">';
					html += '<td style="width:80px;text-align:right;font-size:12px;">';
					html += feedInfo.creatorName+":";
					html += '</td><td style="text-align:left;"><span style="font-size:16px;color:blue;font-weight:bold;">';
					html += feedInfo.contents;
					html += '</span></td>';
					html += '<td>'+feedInfo.createTime+'</td></tr>';
					$("#tbl_feedItems").append(html);
				}else{
					$.messager.alert("系统提示","<span style='color:black'>回复失败~</span>","error");
				}
			}
		});
	},
	downLoadItem:function(urlStr){
		window.location.href = "/casebbs/fileUpload/downfile.do?filepath="+urlStr;
	}
};

function reloadListAction(){
	var msgId = $("#messageid").val();
	$('#attchMentsGrid').datagrid("reload",{"id":msgId});
}
function excelChange(file){ 
    	$('#fileForms').form('submit',{
			success : function(data) {
				reloadListAction(); 
			}
		});	  
}
function excelItemChange(){ 
	$('#itemfileForms').form('submit',{
		success : function(data) {
			reloadItemAttchAction(); 
		}
	});	  
}
function reloadItemAttchAction(){
	var msgId = $("#itemmessageid").val();
	$('#itemAttchGrid').datagrid("reload",{"id":msgId});
}; 