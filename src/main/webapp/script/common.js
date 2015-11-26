var m_artical_title;

function getUrlArgs() {
	var url = decodeURI(location.search);
	// var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);

		}
	}
	return theRequest;
}
function getCurrentDate(){
	var mydate = new Date();
	var m = mydate.getMonth()+1;
	var month = m>9?m:"0"+m;
	var d = mydate.getDay();
	var day = d>9?d:"0"+d;
	var h = mydate.getHours();
	var hour = h>9?h:"0"+9;
	var mt = mydate.getMinutes();
	var minites = mt>9?mt:"0"+mt;
	return mydate.getFullYear()+"-"+month+"-"+day+" "+hour+":"+minites+":00";  
}