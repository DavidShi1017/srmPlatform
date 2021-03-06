$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('提示', failResult, 'warning');
	}else {
		$.messager.alert('提示', successResult, 'info', function(){
			window.parent.closeWindows();
			window.parent.loadGrid();
			window.parent.importSuccess();
		});
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function closeMaintFrame(){
	$("#maintFrame").window('close');
}


function importData(){
	var vkgrp=$('#hdVkgrp').val();
	var datid=$('#hdDatid').val();
	    
	var epath = $('#uploadFile').val();
	
	if(epath==""){
		$.messager.alert('提示', '导入文件不能为空!', 'warning');
		return;
	}
	
	if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
		$.messager.alert('提示', '03以上版本Excel导入暂不支持!', 'warning');
		return;
	}
	if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
		$.messager.alert('提示', '导入文件类型必须为excel!', 'warning');
		return;
	}
	$.messager.progress();
	
	var form = window.document.forms[0];
	form.action = appUrl + "/inOutStockAction!saveImportVkgrpAudit.jspa?vkgrp="+vkgrp+"&datid="+datid;
	form.submit();	
}

function downloadExcel(){
	var vkgrp=$('#hdVkgrp').val();
	var datid=$('#hdDatid').val();
	var form = window.document.forms[0];
	form.action = appUrl + '/inOutStockAction!exportVkgrpAudit.jspa?vkgrp='+vkgrp+'&datid='+datid;
	form.target = "hideFrame";
	form.submit();
}
