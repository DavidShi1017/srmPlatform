$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('��ʾ', failResult, 'warning');
	}else {
//		$.messager.alert('��ʾ', successResult, 'info', function(){
//			window.parent.closeWindows();
//			window.parent.loadGrid();
//			window.parent.importSuccess();
//		});
		alert('����ɹ�!');
		window.parent.closeWindows();
		window.parent.loadGrid();
		window.parent.importSuccess();
		
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function closeMaintFrame(){
	$("#maintFrame").window('close');
}


function importData(){
	var kunnr=$('#hdKunnr').val();
	var datid=$('#hdDatid').val();
	
	    
	var epath = $('#uploadFile').val();
	
	if(epath==""){
		$.messager.alert('��ʾ', '�����ļ�����Ϊ��!', 'warning');
		return;
	}
	
	if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
		$.messager.alert('��ʾ', '03���ϰ汾Excel�����ݲ�֧��!', 'warning');
		return;
	}
	if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
		$.messager.alert('��ʾ', '�����ļ����ͱ���Ϊexcel!', 'warning');
		return;
	}
	$.messager.progress();
	
	var form = window.document.forms[0];
	form.action = appUrl + "/inOutStockAction!saveImportKunnrStockMid.jspa?kunnr="+kunnr+"&datid="+datid;
	form.submit();	
	
}

function downloadExcel(){
	//alert($('#hdKunnr').val());
	var kunnr=$('#hdKunnr').val();
	var datid=$('#hdDatid').val();
	var form = window.document.forms[0];
	form.action = appUrl + '/inOutStockAction!exportKunnrStockMid.jspa?kunnr='+kunnr+'&datid='+datid;
	form.target = "hideFrame";
	form.submit();
}