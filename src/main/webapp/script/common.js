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
	return mydate.toLocaleDateString().replace("/", "-").replace("/", "-")+" "+mydate.getHours()+":"+mydate.getMinutes()+":00"; 
}