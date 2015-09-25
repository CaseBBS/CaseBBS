var isdelete = true; 
var m_title;
var m_dates ;
var m_publishInfo_dlg;
var m_userName;
var m_userMark;
var m_isHost = true;
var m_message_id = 0;
$(function() {  
	m_title =$("#htitle").val();
	m_dates =$("#date").val();
	m_userMark = $("#userMark").val();
	m_userName = $("#userName").val();
	if(m_userMark==1||m_userMark == "1"){
		isdelete = false;
	}
	MessageManage.initCtrlData();
	$("#newMessage").bind("click", MessageManage.newMessage);
	$("#deleteMessage").bind("click", MessageManage.deleteMessage); 
});

var MessageManage = { 
		initCtrlData:function(){
			MessageManage.loadQJGridList();
			MessageManage.loadJDCGridList();
			MessageManage.loadJDWGridList();
			MessageManage.loadZPGridList();
			MessageManage.loadRDGridList();
			MessageManage.loadGZGridList();
			MessageManage.loadJYGridList();
		},
		qjGridclickRow:function(index, data) {
		    $("#qjGrid").datagrid("unselectRow", index);
		},
		jdcGridclickRow:function(index, data) {
		    $("#jdcGrid").datagrid("unselectRow", index);
		},
		jdwGridclickRow:function(index, data) {
		    $("#jdwGrid").datagrid("unselectRow", index);
		},
		zpGridclickRow:function(index, data) {
		    $("#zpGrid").datagrid("unselectRow", index);
		},
		rdGridclickRow:function(index, data) {
		    $("#rdGrid").datagrid("unselectRow", index);
		},
		gzGridclickRow:function(index, data) {
		    $("#gzGrid").datagrid("unselectRow", index);
		},
		jyGridclickRow:function(index, data) {
		    $("#jyGrid").datagrid("unselectRow", index);
		},
		loadQJGridList:function(){
			$('#qjGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=4&title='+m_title+"&date="+m_dates, 
				fitColumns : true,	
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',          
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.qjGridclickRow,
				toolbar : "#qj_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80 ,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              }},
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls = "";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",4)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",4)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		loadJDCGridList:function(){
			$('#jdcGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=1&title='+m_title+"&date="+m_dates, 
				fitColumns : true,
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.jdcGridclickRow,
				idField : 'id',  
				toolbar : "#jdc_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40 ,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              }},
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls = "";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",1)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",1)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		loadJDWGridList:function(){
			$('#jdwGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=2&title='+m_title+"&date="+m_dates, 
				fitColumns : true,
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				pageSize : 10,
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.jdwGridclickRow,
				nowrap : false,
				idField : 'id',  
				toolbar : "#jdw_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80 ,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              }},
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40 ,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              }},
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls = "";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",2)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",2)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		loadZPGridList:function(){
			$('#zpGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=5&title='+m_title+"&date="+m_dates, 
				fitColumns : true,
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.zpGridclickRow,
				pageSize : 10,
				nowrap : false,
				idField : 'id',  
				toolbar : "#zq_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40 ,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              }},
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls ="";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",5)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",5)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		loadRDGridList:function(){
			$('#rdGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=3&title='+m_title+"&date="+m_dates, 
				fitColumns : true,
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				pageSize : 10,
				selectOnCheck:false,
				singleSelect:true,
				nowrap : false,
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.rdGridclickRow,
				idField : 'id',  
				toolbar : "#rd_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls = "";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",3)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",3)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		loadGZGridList:function(){
			$('#gzGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=6&title='+m_title+"&date="+m_dates, 
				fitColumns : true,
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				pageSize : 10,
				selectOnCheck:false,
				singleSelect:true,
				nowrap : false,
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.gzGridclickRow,
				idField : 'id',  
				toolbar : "#rd_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls = "";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",6)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",6)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		loadJYGridList:function(){
			$('#jyGrid').datagrid({
				url : 'caseBBS/getMessageList.do?param=7&title='+m_title+"&date="+m_dates, 
				fitColumns : true,
				rownumbers : true,
				pagination : false,
				pageNumber : 1,
				pageSize : 10,
				selectOnCheck:false,
				singleSelect:true,
				nowrap : false,
				checkOnSelect: false,
		        selectOnCheck: true, 
		        onClickRow: MessageManage.jyGridclickRow,
				idField : 'id',  
				toolbar : "#rd_toolbar",
				columns : [ [ 
				              { title : 'id', field : 'id', hidden : true },
				              { title : '标题', field : 'title', align : 'left', width : 200,formatter:function(value,rowData,index){
				            	  var html =  "<a href='javascript:void(0)' style='color:black;text-decoration: none;' onclick='MessageManage.clickAction("+rowData.id+")'>"+value+"</a>";
				            	  if(rowData.ishost){
				            		  html+="<img src='resource/calendarImage/new2.png' />";
				            	  }
				            	  return html;
				              } }, 
				              { title : '创建时间', field : 'createTime', align : 'left', width : 80,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '回复数量', field : 'itemCount', align : 'right', width : 40,formatter:function(value,rowData,index){
				            	  return "<span style='color:black'>"+value+"</span>";
				              } },
				              { title : '操作',   field : 'operation', align : 'right', width : 60,formatter:function(value,rowData,index){
				            	  var htmls = "";
				            	  if(rowData.ishost){
				            		  htmls += "<a onclick='MessageManage.changeMessageStatus("+rowData.id+",7)' href='javascript:void(0)'  style='color:blue;text-decoration: none;margin-right:15px' >取消置顶</a>";
				            	  }
				            	  htmls += "<a href='javascript:void(0)' style='color:red;text-decoration: none;' onclick='MessageManage.deleteMessage("+rowData.id+",7)' >删除</a>";
			            		  return htmls;
				              },hidden: isdelete}
				          ] ]
			});
		},
		clickAction:function(msgId){
			window.location.href="/casebbs/caseBBS/gotoArticleInfo.do?id="+msgId+"&date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark;
		},
		doSearch:function(){
			var t_title ="";// $.trim($("#").val());
			if(t_title.length==0){
				return;
			}
			$('#casePushListGrid').datagrid("reload",{'title' : t_title});
		} ,
		backToCalendar:function(){
			window.location.href="/casebbs/caseBBS/gotoIndex.do?date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark; 
		},
		backToMessageList:function(){
			window.location.href="/casebbs/caseBBS/gotoArticleList.do?date="+m_dates+"&userName="+m_userName+"&userMark="+m_userMark;
		},
		publishNewMessage:function(){
			m_publishInfo_dlg = art
			.dialog({
				id : 'dlgPublishInfo',
				title : '发布新帖',   
				width: "100%", 
				height: "100%",
				content : document.getElementById("div_newMessage"),
				lock : false,
				initFn : function() {
					InitUploadFun();
					MessageManage.clearFrom();
					$('#attchMentsGrid').datagrid({
						url : 'caseBBS/getMessageAttchs.do',
						param:{
							"id" :m_message_id
						},
						fitColumns : true,	
						rownumbers : true,
						pagination : false,
						pageNumber : 1,
						pageSize : 10,
						nowrap : false,
						height:250,
						idField : 'id',          
						checkOnSelect: false,
				        selectOnCheck: true, 
				        onClickRow: MessageManage.attchsGridclickRow,
						toolbar : "#attch_toolbar",
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
					            		  return "<a href='javascript:void(0)' style='color:blue;text-decoration: none;' onclick='MessageManage.deleteItem("+rowData.id+")' >删除</a>";
						              }}
						          ] ]
					});
				},
		        button: [
		                 {
		                     name: '发布',
		                     callback: function () { 
		                    	 MessageManage.saveMessageAction();
		                         return false;
		                     },
		                     focus: true
		                 },
		                 {
		                     name: '关闭'
		                 }
		            ]
			});
		},
		attchsGridclickRow:function(index, data) {
			$("#attchMentsGrid").datagrid("unselectRow", index);
		},
		onCheckShop:function(){
			var s = $("input[type='checkbox']:checked");
			if(s.length>0){  
				 m_isHost = true;
			}else{
				m_isHost = false;
			}
		},
		saveMessageAction:function(){
			var messageObj = {};
			messageObj.title=$("#txttitle").val();
			messageObj.typeId=$("#txtarticalType").combobox("getValue");
			messageObj.description=$("#txtdescription").val();
			messageObj.creator=1;
			messageObj.creatorName=m_userName;
			messageObj.organId=1;
			messageObj.organName="案件处理交流机构";
			messageObj.createTime = getCurrentDate();
			messageObj.summary=$("#txtcontent").val();
			//messageObj.ishost=false;
			//if($("input:checkbox").attr("checked")=="checked"){
				messageObj.ishost=m_isHost;
			//}
			$.ajax({
				url : "caseBBS/saveMessage.do",
				type : "POST",
				dataType : "json",
				data:messageObj,
				async : true,
				success : function(req) {
					if (req.isSuccess) { 
						m_message_id = req.data.id;
						$("#msgId").val(m_message_id);
						$.messager.alert("系统提示","<span style='color:black'>发帖成功~若有相关附件，请点击上传附件，若无附件，请点击“关闭”按钮关闭当前页面</span>","info");
						//MessageManage.clearFrom();
						//m_publishInfo_dlg.close();
						if(messageObj.typeId == 1 || messageObj.typeId == "1"){
							$("#jdcGrid").datagrid("reload");
						}else if(messageObj.typeId == 2 || messageObj.typeId == "2"){
							$("#jdwGrid").datagrid("reload");
						}else if(messageObj.typeId == 3 || messageObj.typeId == "3"){
							$("#rdGrid").datagrid("reload");
						}else if(messageObj.typeId == 4 || messageObj.typeId == "4"){
							$("#qjGrid").datagrid("reload");
						}else if(messageObj.typeId == 5 || messageObj.typeId == "5"){
							$("#zpGrid").datagrid("reload");
						}else if(messageObj.typeId == 6 || messageObj.typeId == "6"){
							$("#gzGrid").datagrid("reload");
						}else if(messageObj.typeId == 7 || messageObj.typeId == "7"){
							$("#jyGrid").datagrid("reload");
						}
					}else{
						$.messager.alert("系统提示","<span style='color:black'>发帖失败咯~</span>","error");
					}
				}
			});
		},
		clearFrom:function(){
			$("#txisHost").attr("checked","false");
			$("#txttitle").val("");
			$("#txtarticalType").combobox("setValue",1);
			$("#txtdescription").val("");
			$("#txtcontent").val("");
		},
		deleteMessage:function(id,typeId){
			$.messager.confirm('操作提示', "确定要删除?",
					function(r) {
						if (r) {
							MessageManage.deleteAction(id,typeId);
						}
					});
		},
		deleteAction:function(id,typeId){
			$.ajax({
				url : "caseBBS/deleteMessage.do?id="+id,
				type : "POST",
				dataType : "json",  
				success : function(req) {
					if (req.isSuccess) { 
						$.messager.alert("操作提示","<span style='color:black'>删除成功~</span>","info");
						if(typeId == 1 || typeId == "1"){
							$("#jdcGrid").datagrid("reload");
						}else if(typeId == 2 || typeId == "2"){
							$("#jdwGrid").datagrid("reload");
						}else if(typeId == 3 || typeId == "3"){
							$("#rdGrid").datagrid("reload");
						}else if(typeId == 4 || typeId == "4"){
							$("#qjGrid").datagrid("reload");
						}else if(typeId == 5 || typeId == "5"){
							$("#zpGrid").datagrid("reload");
						}else if(typeId == 6 || typeId == "6"){
							$("#gzGrid").datagrid("reload");
						}else if(typeId == 7 || typeId == "7"){
							$("#jyGrid").datagrid("reload");
						}
					}else{
						$.messager.alert("操作提示","<span style='color:black'>删除失败~</span>","error");
					}
				}
			});
		},
		changeMessageStatus:function(id,typeId){
			$.ajax({
				url : "caseBBS/cancelHostMessage.do?id="+id,
				type : "POST",
				dataType : "json",  
				success : function(req) {
					if (req.isSuccess) { 
						$.messager.alert("操作提示","<span style='color:black'>取消成功~</span>","info");
						if(typeId == 1 || typeId == "1"){
							$("#jdcGrid").datagrid("reload");
						}else if(typeId == 2 || typeId == "2"){
							$("#jdwGrid").datagrid("reload");
						}else if(typeId == 3 || typeId == "3"){
							$("#rdGrid").datagrid("reload");
						}else if(typeId == 4 || typeId == "4"){
							$("#qjGrid").datagrid("reload");
						}else if(typeId == 5 || typeId == "5"){
							$("#zpGrid").datagrid("reload");
						}else if(typeId == 6 || typeId == "6"){
							$("#gzGrid").datagrid("reload");
						}else if(typeId == 7 || typeId == "7"){
							$("#jyGrid").datagrid("reload");
						}
					}else{
						$.messager.alert("操作提示","<span style='color:black'>取消失败~</span>","error");
					}
				}
			});
		},
		deleteItem:function(id){
			$.ajax({
				url : "caseBBS/deleteMessageAttch.do?id="+id,
				type : "POST",
				dataType : "json",  
				success : function(req) {
					if (req.isSuccess) { 
						$.messager.alert("操作提示","<span style='color:black'>删除成功~</span>","info");
						var msgId = $("#msgId").val();
						$('#attchMentsGrid').datagrid("reload",{"id":msgId});
					}else{
						$.messager.alert("操作提示","<span style='color:black'>删除失败~</span>","error");
					}
				}
			});
		}
};


function reloadListAction(){
	var msgId = $("#msgId").val();
	$('#attchMentsGrid').datagrid("reload",{"id":msgId});
}