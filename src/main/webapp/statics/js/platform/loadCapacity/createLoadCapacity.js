$(document).ready(function() {
	$('#hideFrameDetail').bind('load', promgtMsg);
});


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrameDetail");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('��ʾ', failResult, 'warning');
	} else if (successResult) {
 		//$.messager.alert('��ʾ', successResult, 'info', function() {
  			close();
 			window.parent.loadGrid();
 		//});
	}
}

function close() {
	window.parent.closeMaterial();
}

function submit() {
		var cc = $('#vstel').val();
		var c1 = $('#hand_team').val();
		var c2=$('#start_date').val();
		var c3=$('#end_date').val();
		var c4=$('#scheduling').val();
		if(!(cc.length>0 ) || !(c1.length>0 )  || !(c2.length>0 )  || !(c3.length>0 )  || !(c4.length>0 )){
				$.messager.alert('Tips', '������Ϊ��,����ȷ��д����Ϣ!', 'warning');
		} else {
			$.messager.confirm('Confirm', '�Ƿ��ύ?', function(r) {
				if (r) {
					var form = window.document.forms[0];
					form.action = appUrl + "/loadCapacityAction!saveLoadCapacity.jspa";
					form.target = "hideFrameDetail";
					form.submit();
				}});
		}
}
