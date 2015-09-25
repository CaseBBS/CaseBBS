/*
 * 涓婁紶鎻掍欢搴旂敤锛�
 */

function InitUploadFun() {
	var button = $('#btnfindFile'), interval;

	new AjaxUpload(button, {
		action : 'fileUpload/uploadFile.do',
		name : 'file',
		onSubmit : function(file, ext) { 
			if (m_message_id==0) {
				$.messager.alert("操作提示", "<span style='color:black'>请先保存发帖信息，再上传相关附件!</span>", 'error');
				return false;
			} 
			var data = {};
			data.id = m_message_id;
			this.setData(data);
			var text = "文件上传中";
			interval = window.setInterval(function() {
				if (text.length < 20) {
					text += ".";
				} else {
					text = "文件上传中";
				}
			}, 200);
		},
		onComplete : function(file, response) {// 上传成功的函数；response代表服务器返回的数据
			// button.text('上传图片(只允许上传大小不得大于10M)');
			// 清楚按钮的状态
			button.text('文件上传');
			window.clearInterval(interval);
			this.enable();
			button.text('选择文件');
			reloadListAction(m_message_id);
		}
	});
}; 