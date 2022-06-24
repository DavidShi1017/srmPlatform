$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
	$('#updatePwd').dialog('close');
});
function loadGrid() {
	
	$('#updatePwd').dialog({
		title:"�����޸�",
		modal : true,
		buttons:[{
			text:'ȷ��',
			iconCls:'icon-ok',
			handler:function(){
				updatePassWord();
			}
		}]
	});

}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$('#updatePwd').dialog('close');
	}
}

function close() {
	window.parent.closeMaintWindow();
	window.parent.search();
}

function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 350;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						left : ($(window).width() - 400) * 0.8,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}

function updatePwd(val) {
	$('#updatePwd').dialog('open');
}
function updatePassWord(){
	var val = $('#userId').val();
	var repassWd = $('#repassWd').val();
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!resetPWdToKunnr.jspa?userId=" + val+"&repassWd="+repassWd;
	form.submit();
}

$.extend($.fn.validatebox.defaults.rules, {
	/* �����ĳ���ֶ���� */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '�ֶβ�ƥ��'
	}
});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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