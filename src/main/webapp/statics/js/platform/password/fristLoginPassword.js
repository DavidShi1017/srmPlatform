$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});
function save() {
	var p = $("#password").val();
	var t = $("#suer_pass").val();
	if (!(p && t)) {
		$.messager.alert('Tips', "At least 6 characters!", 'warning');
		return;
	}
	if(p.length < 6 || t.length < 6){
		$.messager.alert('Tips', "At least 6 characters!", 'warning');
		return;
	}
	if (!(p == t)) {
		$.messager.alert('Tips', "Passwords must match!", 'warning');
		return;
	}
	
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction1/allUserAction1!updateFristPass.jspa";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				self.location.href = appUrl;
			}
		});

	}
}
$.extend($.fn.validatebox.defaults.rules, {
	/* �����ĳ���ֶ���� */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '���벻һ�£�'
	}
});
