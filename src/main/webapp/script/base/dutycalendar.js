/*
 * 报备首页，日历页面业务逻辑操作
 * 
 * 包括：日历的展示；
 * 报备情况统计
 * 报备明细；
 * 
 */
var y;
var m;
var m_year;
var m_month; 
var m_userMark;
var m_userName; 
var m_message_id = 0;
var m_isHost = true;
$(function() {
	// 获取地址栏参数，获取组织结构信息； 
	m_userId = $("#userId").val();
	m_userMark = $("#userMark").val();
	m_userName = $("#userName").val(); 
	m_date = $("#date").val();
	m_year = $("#years").val();
	m_month = $("#months").val();
 
	y = m_year;
	m = m_month;
	$("#sp_years").text(y+"年");
	$("#sp_month").text(m+"月");
	changeDivHeight(); // 表格自动高度设置
	getDateData(y + "-" + m + "-" + 1);// 初始化默认月份数据 
	InitData();
}); 
function InitData(){
	clearFrom();
}
// 设置日历窗体的高度
function changeDivHeight() {
	var bodyHeight = $(window).height(); 
	$("#dateBoxMainDate").height(bodyHeight-40);
} 
// 点击日期上月下月事件
function getDateClick(action) {
	if (action == 'next') {
		m++;
		if (m > 12) {
			y++;
			m = 1;
		} 
	} else {
		m--;
		if (m < 1) {
			m = 12;
			y--;
		} 
	}
	var date = y + "-" + m + "-" + 1;
	$("#sp_years").text(y+"年");
	$("#sp_month").text(m+"月");
	getDateData(date);
	
}
// 根据日期，获取后台数据
function getDateData(date) {
	$.ajax({
		url : '/casebbs/caseBBS/getCalender.do?date=' + date,
		type : "POST", 
		dataType : "json", 
		success : function(req) {
			if (req) {
				setDateData(req);
			} else { 
				$("body").popjs({"title":"提示","content":"获取数据失败"});   
			}
		}
	});
}
// 初始化日期数据显示
function setDateData(result) { 
	var dateArray = new Array();
	// var json = eval("(" + result + ")");
	var json = result;
	for ( var i = 0; i < 6; i++) { 
		dateArray[i] = new Array();
		for ( var j = 0; j < 7; j++) {
			dateArray[i][j] = 0;
		}
	}
	var lineIndexOf = 0; 
	for ( var i = 0; i < json.length; i++) {
		dateArray[lineIndexOf][parseInt(json[i]["week"])] = new Array();
		var today = new Array(); 
		today['y'] = json[i]["y"];
		today['m'] = json[i]["m"];
		today['d'] = json[i]["d"];
		today['totalpolice'] = json[i]["totalpolice"]; 
		dateArray[lineIndexOf][parseInt(json[i]["week"])] = today;
		if (parseInt(json[i]["week"]) == 6) { 
			lineIndexOf++;

		}
	}
	PlanArray = dateArray;
	creatHtml(dateArray); 
	// p(dateArray);
}
//创建日历html
function creatHtml(arr) {
	 
	var html = "";
	for ( var i = 0; i < 6; i++) { 
		var trHtml = "<tr  style='vertical-align:top'>";
		for ( var j = 0; j < 7; j++) {

			var tdHtml = '';
			var isHaveData = arr[i][j]["d"] == null ? false : true;

			if (isHaveData == false) {

				tdHtml = '<td><div class="dateBoxMainDateTD"><div class="dateBoxMainDateTDLibOff"></div><div class="dateBoxMainDateTDBox"><ul><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li></ul></div></div></td>';

				trHtml = trHtml + tdHtml;

				continue;
			}
			var d = "";
			if (arr[i][j]["d"] < 10) {
				d = "0" + arr[i][j]["d"];
			} else {
				d = arr[i][j]["d"];
			} 
			var nm = m;
			if (m < 10) {
				nm = "0" + m;
			} 
			tdHtml = '<td doc="td_'
					+ i
					+ '_'
					+ j
					+ '" id="date_'
					+ y
					+ '-'
					+ nm
					+ '-'
					+ d
					+ '"><div class="DateBoxbg" id="modeldiv_'
					+ i
					+ '_'
					+ j
					+ '"></div><div '; 
			tdHtml += '  onclick=onClickData("'
					+ y
					+ '-'
					+ nm
					+ '-'
					+ d
					+ '") class="dateBoxMainDateTD">';
					if (arr[i][j]["totalpolice"] != "<li class='nobaobei' style='display: list-item;'>无报备</li>") {
						tdHtml +='<div class="dateBoxMainDateTDLib">';
					}else{
						tdHtml +='<div class="dateBoxMainDateTDLibOff">';
					}
			tdHtml +=arr[i][j]["d"]+ '</div><div class="dateBoxMainDateTDBox"><ul id="ulcontent_'
					+ i + '_' + j + '">' + arr[i][j]["totalpolice"]
					+ '<li class="baoBeiBtn">' + '</li>' + '</div>'
					+ ' </ul></div></div>'; 
			tdHtml += '</td>';

			trHtml = trHtml + tdHtml;
		}
		html = html + trHtml + "</tr>";
	}
	$("#dateBody").empty(); 
	$("#dateBody").append(html);
	var nowDate = new Date();
	var nowm = nowDate.getMonth();
	var nowd = nowDate.getDate();
	if(nowm < 10)nowm="0"+(nowm+1);
	if(nowd < 10)nowd="0"+nowd;
	var nd = nowDate.getFullYear()+"-"+nowm+"-"+nowd;
	$("#date_"+nd+" .dateBoxMainDateTD .dateBoxMainDateTDLibOff").css({"background":"#87020B"});
	 
}

var dtime = null;
// 点击日历号数，进入详细报备页面
function onClickData(date) { 
	//2015-9-10;
	//2015-09-10;
	//var dt = date.replace(/-/gm, '');
	//if (dt.length == 7) {
	//	dt = dt.substr(0, 4) + "0" + dt.substr(4, 7);
	//} 
	//$.post("/BPHCenter/dutyRouteWeb/gotoDutyItem.do?sessionId="+sessionId+"&organId="+m_dutyCalendar_Org.id+"&ymd="+dtime);
	window.navigate("/casebbs/caseBBS/gotoArticleList.do?date="+date+"&userName="+m_userName+"&userMark="+m_userMark);
	//window.location.href="/casebbs/caseBBS/gotoArticleList.do?date="+date+"&userName="+m_userName+"&userMark="+m_userMark;
}; 
/**
 * 查询按钮事件，查询结果直接跳转到列表页面
 */
function btnSearchAction() { 
	
};
/**
 * 发表新帖按钮事件
 */
var m_publishInfo_dlg;
function publishNewMessage(){
	clearFrom();
	m_publishInfo_dlg = art
	.dialog({
		id : 'dlgPublishInfo',
		title : '发布新帖',   
		content : document.getElementById("div_newMessage"),
		lock : false,
		initFn : function() {
		},
        button: [
                 {
                     name: '发布',
                     callback: function () { 
                    	 saveMessageAction();
                         return false;
                     },
                     focus: true
                 },
                 {
                     name: '关闭'
                 }
            ]
	}); 
	$("#messageid").val(0);
}; 
function onCheckShop(){
	var s = $("input[type='checkbox']:checked");
	if(s.length>0){  
		 m_isHost = true;
	}else{
		m_isHost = false;
	}
}
function saveMessageAction(){ 
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
	messageObj.ishost=m_isHost; 
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
				$("#messageid").val(m_message_id);
				excelChange();
			}else{
				$.messager.alert("系统提示","<span style='color:black'>发帖失败咯~</span>","error");
			}
		}
	}); 
}
function clearFrom(){
	m_message_id = 0;
	$("#msgId").val(0);
	$("#messageid").val(0);
	$("#txisHost").attr("checked","false");
	$("#txttitle").val("");
	$("#txtarticalType").combobox("setValue",1);
	$("#txtdescription").val("");
	$("#txtcontent").val(""); 
}
function searchAction(){ 
	var title = $.trim($("#sch_name").val());
	if(title.length==0||title == ""){
		return;
	}else{
		window.location.href="/casebbs/caseBBS/gotoArticleList.do?date="+m_date+"&userName="+m_userName+"&userMark="+m_userMark+"&title="+title;
	}
}
 
function deleteItem(id){
	$.ajax({
		url : "caseBBS/deleteMessageAttch.do?id="+id,
		type : "POST",
		dataType : "json",  
		success : function(req) {
			if (req.isSuccess) { 
				$.messager.alert("操作提示","<span style='color:black'>删除成功~</span>","info");
				var msgId = $("#msgId").val(); 
			}else{
				$.messager.alert("操作提示","<span style='color:black'>删除失败~</span>","error");
			}
		}
	});
}


function excelChange(){ 
	$('#fileForms').form('submit',{
		success : function(data) {
			$.messager.alert("系统提示","<span style='color:black'>发帖成功~</span>","info");
			m_publishInfo_dlg.close();
			window.location.href="/casebbs/caseBBS/gotoIndex.do?userName="+m_userName;
		}
	});	  
}