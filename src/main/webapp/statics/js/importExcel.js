$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}else {
		alert(1+"www"+successResult);
		var odList = eval(successResult);
//		for(var i=0,i<odList.length,i++){
//			var od = successResult[i];
//		}
		window.parent.returnOrderDetail(successResult);
//		$.messager.alert('Tips', successResult, 'info', function(){
//			
//			window.parent.closeMaintWindow();
//			window.parent.search();		
//		});
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function importData(){
 		var epath = $('#uploadFile').val();
 		if(epath==""){
			$.messager.alert('Tips', '�����ļ�����Ϊ��!', 'warning');
			return;
		}
		
		/*if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
			$.messager.alert('Tips', '03���ϰ汾Excel�����ݲ�֧��!', 'warning');
			return;
		}
		if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
			$.messager.alert('Tips', '�����ļ����ͱ���Ϊexcel!', 'warning');
			return;
		}*/
		$.messager.progress();
		var form = window.document.forms[0];
		form.action = appUrl + "/orderAction!findOrderExcel.jspa?path="+$('#uploadFile').val();
		form.submit();
}