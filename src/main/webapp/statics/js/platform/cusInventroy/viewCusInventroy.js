$(document).ready(function() {
 	$('#hideFrame').bind('load', promgtMsg);
});

function cancel() {
	window.parent.closeMaintWindow();
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}



document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};



//�����̿ͻ�
$('#customer_id').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa',
	idField : 'global_account',
	textField : 'customer_name',
	columns : [[{
				field : 'global_account',
				title : '�ͻ�����',
				width : 100
			}, {
				field : 'customer_name',
				title : '�ͻ�����',
				width : 120
			}]],
});

//�ն˿ͻ�
$('#endCustomer_id').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa',
	idField : 'end_customer_id',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_id',
				title : '�ͻ�����',
				width : 100
			}, {
				field : 'end_customer_name',
				title : '�ͻ�����',
				width : 120
			}]],
});
//����
$('#currency_code').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeValue=dictTypeId=548',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 153,
	onSelect : function(r){
 	} 
});


function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
 