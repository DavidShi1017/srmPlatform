$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);	
});

function cancel() {
	window.parent.closeMaintWindow();
}
//������ϸģ��
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
			editor: 'text',
			hidden:true
		},
		{
			field : 'material_id',
			title : '12NC���ϱ���',
			width : 150,
			align : 'center',
			editor: 'text'
		},
		{
			field : 'drNum',
			title : 'DR Number��Ŀע��ı���',
			width : 60,
			align : 'center',
			editor: 'text'
			
		},

		{
			field : 'qty',
			title : 'QTY��������',
			width : 100,
			align : 'center',
			editor: {type:'numberbox',options:{precision:2}}
		} ,
		{
			field : 'target_resale',
			title : 'Target ResaleĿ�����ۼ۸�',
			width : 100,
			align : 'center',	
			editor: {type:'numberbox',options:{precision:4}}
		} ,
		{
			field : 'target_cost',
			title : 'Target Cost Ŀ������۸�', 
			width : 100,
			align : 'center',	
			editor: {type:'numberbox',options:{precision:4}}
		} ,
		{
			field : 'amount',
			title : 'Value ����Ŀ�ܼ�',
			width : 60,
			align : 'center',
			editor: {type:'numberbox',options:{precision:4}}					
		} ,
		{
			field : 'reason',
			title : 'Justification����ԭ��', 
			width : 60,
			align : 'center',	
			editor:'text'
		} ,
		{
			field : 'competitor',
			title : 'Competitor��������',
			width : 150,
			align : 'center',
			editor:'text'
		},
		{
			field : 'start_dateStr',
			title : 'Start of Production������������',
			width : 150,
			align : 'center',
			editor:'datebox',
		},		
		{
			field : 'cus_remark',
			title : 'Cus Remark�ͻ����',
			width : 100,
			align : 'center',
			editor:'text'						
		}]],
			toolbar : [ "-", {
				text : '����',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			},"-", {
				text : 'ɾ��',
				iconCls : 'icon-remove',
				handler : function() {
					dele();
				}
			}], 
			onClickCell: function (rowIndex, field, value) {
			    beginEditing(rowIndex, field, value);
			    $('#datagrid').datagrid('beginEdit', rowIndex);
		     },
}); 


function add(){	
	$('#datagrid').datagrid('appendRow',{
		material_name:'',
		material_id:'',
		drNum:'',
		qty:'',
		target_resale:'',
		target_cost:'',
		amount:'',
		reason:'',
		competitor:'',
		start_dateStr:'',
		cus_remark:'',
     });
}

var delEnquiry="0";
function dele(){
	var row = $('#datagrid').datagrid('getSelected');
	if (row.length==0){
		$.messager.alert('Tips', 'δѡ�����ݣ�', 'warning');
		return;
	}
	 $.messager.confirm('Confirm', 'ȷ��ɾ��ѡ������?', function(r) {
		 if(r){
			 {				
				 if(row.id!=undefined){
					 delEnquiry+=","+row.id;
				}				 
				 var rowIndex = $('#datagrid').datagrid('getRowIndex', row);
				 $('#datagrid').datagrid('deleteRow', rowIndex); 
				
			 }		 
		 }
	 });
}




function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
 	} else{
 		$.messager.alert('Tips', successResult, 'info',function(){			
 			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}



function submit() {
	var rows = $("#datagrid").datagrid("getRows");			
	for(var i=0;i<rows.length;i++){
		$("#datagrid").datagrid("endEdit",i);        
	} 	
//	if ($("#orgCode").val()=='') {
//		$.messager.alert('Tips', "�ɹ���֯����δ����ʽ����ȷ��", 'error');
//		return;
//	}
	var url=undefined;
	if($('#id').val()=='0'||$('#id').val()==''){
		 url="/enquiryAction!createEnquiry.jspa";
	}else{
		url="/enquiryAction!updateEnquiry.jspa";
	}
	
	var enquiryDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
//		if(rows[i].delivery_date_str==undefined||rows[i].delivery_date_str==""){
//			$.messager.alert('Tips', "��������δ����߸�ʽ����ȷ��", 'error');
//			return;
//		}
//		if (rows[i].num*rows[i].price!=rows[i].amount) {
//			$.messager.alert('Tips', "�ɹ���������δ����߸�ʽ����ȷ��", 'error');
//			return;
//		}
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark+"'}";
		
		enquiryDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', '��˶���Ϣ,ȷ���ύ?', function(r) {
		if(r){						
			$('#enquiryDetailJson').val("["+enquiryDetailJson+"]");
			$('#delEnquiry').val("("+delEnquiry+")");	 
			var form = window.document.forms[0];
			form.action = appUrl + url;
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
	
 
}


var editIndex = undefined;
function beginEditing (rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
             editIndex = rowIndex;
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
         }
    }
}
function endEditing () {
    if (editIndex == undefined) { return true; }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
 }

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
//�������ڶ���
function initMaintAccount(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');
}


$.extend($.fn.validatebox.defaults.rules, {  
    /*�����ĳ���ֶ����*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ��'
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