$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	//��֤��ʽ
	$('#validateType').combobox({
		valueField : 'value',
		textField : 'text',
		data : [ {
			value : 'post',
			text : 'post'
		}, {
			value : 'get',
			text : 'get'
		} ]
	});

});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		close();
		window.parent.location.reload();
	}
}

function submit() {
	var form = window.document.forms[0];
	form.action = appUrl + "/menuAction!ssoCreate.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintSso();
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
		if(event.keyCode == 8) {
		     // �������textarea�ڲ�ִ���κβ���
		  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
		            event.returnValue = false; 
		        // �����readOnly����disable��ִ���κβ���
		  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
		            event.returnValue = false;                             
		}
	return true;
};