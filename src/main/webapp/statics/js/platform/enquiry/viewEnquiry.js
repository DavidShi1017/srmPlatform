$(document).ready(function() {
			loadGrid();
 			$('#hideFrame').bind('load', promgtMsg);
		});
function cancel() {
	window.parent.closeMaintWindow();
}
function loadGrid() {
	$('#datagrid').datagrid({   
		iconCls : 'icon-list',
		title : '������ϸ',
		url : appUrl +  '/enquiryAction!getEnquiryDetailList.jspa?enquiry_id='+$('#enquiry_id').val(),
		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : true,
		nowrap : true,
		checkbox : true,
	 	required : true,
		rownumbers : true,
		height:200,
		fitColumns:false,
		striped : true,
	     columns : [[{
	 		field : 'ck',
	 		align : 'center',
	 		checkbox : true
	 	},{
	 			field : 'id',
	 			title : '����',
	 			width : 60,
	 			align : 'center',
	 			hidden:true
	 		},
	 		{
	 			field : 'material_name',
	 			title : 'BookPart��������',
	 			width : 150,
	 			align : 'center',
	 			hidden:true
	 		},
	 		{
	 			field : 'material_id',
	 			title : '12NC���ϱ���',
	 			width : 150,
	 			align : 'center',
	 		},
	 		{
	 			field : 'drNum',
	 			title : 'DR Number��Ŀע��ı���',
	 			width : 60,
	 			align : 'center',
	 			
	 		},

	 		{
	 			field : 'qty',
	 			title : 'QTY��������',
	 			width : 100,
	 			align : 'center',
	 		} ,
	 		{
	 			field : 'target_resale',
	 			title : 'Target ResaleĿ�����ۼ۸�',
	 			width : 100,
	 			align : 'center',	
	 		} ,
	 		{
	 			field : 'target_cost',
	 			title : 'Target Cost Ŀ������۸�', 
	 			width : 100,
	 			align : 'center',	
	 		} ,
	 		{
	 			field : 'amount',
	 			title : 'Value ����Ŀ�ܼ�',
	 			width : 60,
	 			align : 'center',				
	 		} ,
	 		{
	 			field : 'reason',
	 			title : 'Justification����ԭ��', 
	 			width : 60,
	 			align : 'center',	

	 		} ,
	 		{
	 			field : 'competitor',
	 			title : 'Competitor��������',
	 			width : 150,
	 			align : 'center',
	 		},
	 		{
	 			field : 'start_date',
	 			title : 'Start of Production������������',
	 			width : 150,
	 			align : 'center',
				formatter : function(date){
					return utcToDate(date);
				}
	 		},		
	 		{
	 			field : 'cus_remark',
	 			title : 'Cus Remark�ͻ����',
	 			width : 100,
	 			align : 'center',				
	 		}]],
	}); 
 
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	
	$("#datagrid").datagrid('load');
}

function closeMain() {
	$("#hiddenWin").window('close');
}

//function dele(){
//	
//	var ids = '(';
//	var rows = $('#priceinfo_list').datagrid('getSelections');
//	for ( var i = 0; i < rows.length; i++) {
//		if(i==rows.length-1){
//			ids += rows[i].id+')';
//		}else{
//			ids += rows[i].id+',';
//		}
//	}
// 	if (ids == '') {
//		$.messager.alert('Tips', 'δѡ�����ݣ�', 'warning');
//		return;
//	} else {
//		var form = window.document.forms[0];
//		form.action = appUrl + '/priceInfoAction!updatePriceInfoState.jspa?ids='+ ids+'&state=5';
//		form.target = "hideFrame";
//		form.submit();
//	}	
//}

//����
$('#currency_code').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/dictAction!getCmsTbDictJsonList.jspa?dictTypeId=548',
	idField : 'itemValue',
	textField : 'itemName',
	columns : [[{
				field : 'itemValue',
				title : '���Ҵ���',
				width : 100
			}, {
				field : 'itemName',
				title : '��������',
				width : 120
			}]],
});
//$('#currency_code').combobox({
//	url : appUrl
//			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeValue=currency',
//	valueField : 'itemValue',
//	textField : 'itemName',
//	multiple : false,
//	editable : false,
//	required : true,
//	panelHeight : 120,
//	width : 153,
//	onSelect : function(r){
// 	} 
//});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
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
 