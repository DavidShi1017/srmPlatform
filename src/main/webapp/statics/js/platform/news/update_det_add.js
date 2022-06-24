$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	addUploadButton(editor);
});
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning',function(){
		close();
		window.parent.search();
		});
		
	}
}
function close() {
	window.parent.closeNewsDetail();
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	if(event.keyCode == 8) {
	     // 如果是在textarea内不执行任何操作
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // 如果是readOnly或者disable不执行任何操作
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};

function checkFile() {
	var file = document.all.file0.value;
	if (file == null || file == "") {
		return true;
	}
	if (file.lastIndexOf(".") == -1) {
		$.messager.alert('Tips', "路径不正确", 'warning');
		return false;
	}
	var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
	var extName = file.substring(file.lastIndexOf("."));

	// f.lastIndexOf(".")返回file的最后那个“点”的位置（数字），f.substring（n 数字）返回字符串开始到第n+1个字符；
	if (allImgExt.indexOf(extName + "|") == -1) {
		errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
		$.messager.alert('Tips', errMsg, 'warning');
		return false;
	}
	return true;
	/* document.uploadForm.submit() ; */
}

function save() {
	var s = $("#totalParentTotal").combobox("getValue");
	$("#totalShow").val(s.split(",")[1]);
	$("#totalParentId").val(s.split(",")[0])
	if ($("#delailTitle").val() == "") {
		warn("请输入标题！");
		return;
	}
	var t = s.split(",")[1];
	if (t == "Y") {
		if (checkFile()) {
		} else {
			return;
		}
	}
	var data = CKEDITOR.instances.delail_content.getData();
	/* $("#delail_content").val(CKEDITOR.instances.delail_content.getData()); */
	if(data !=""){
	$("#delail_content").val(data);
		var form = window.document.forms[0];
		form.action = "newsAction!updateNewsDetail.jspa";
		form.target = "hideFrame";
		form.submit();
	}else{
		$.messager.alert('Tips', "请填写内容", 'warning');
		return;
	}
}
/*
 * function gettype(){ var cc=$("#totalParentTotal").combobox("getValue");
 * $("#totalShow").val(cc.split(",")[1]);
 * $("#totalParentId").val(cc.split(",")[0]) alert(cc); $.ajax({//
 * 数据库比对身份证号码是否重复 async : false, type : 'post', url : 'RyxxBean.jsp', data : {
 * 'service' : 'isUserCardRepeat', 'param' : param.value }, success :
 * function(date) { if (date === 'true') { return true; } } });
 *  }
 */
function isimg(src) {
	var ext = [ '.gif', '.jpg', '.jpeg', '.png' ];
	var s = src.toLowerCase();
	var r = false;
	for ( var i = 0; i < ext.length; i++) {
		if (s.indexOf(ext[i]) > 0) {
			alert(ext[i]);
			r = true;
			break;
		}
	}
	return r;
}
