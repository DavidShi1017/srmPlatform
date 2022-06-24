$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	$("#status").combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : '0', 'flagText' : 'δ����'},
			{'flagValue' : '1', 'flagText' : '������'},
			{'flagValue' : '2', 'flagText' : '�����'}
		],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
		onLoadSuccess : function() {
			if(flag == 'Y'){
				$('#status').combobox("setValue", '2');
			}		
		}
		
	});
	loadGrid();
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
		search();
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

function loadGrid() {
		$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/account/accountAction!searchTraReimbur.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height*0.88,
						columns : [ [
								{
									field : 'plan_id',
									title : '��������',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},{
									field : 'transaction_id',
									title : '���뵥��',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'write_eventId',
									title : '��������',
									width : setColumnWidth(0.05),
									align : 'center',
									hidden: true
								},{
									field : 'title',
									title : '�������',
									width : setColumnWidth(0.2),
									align : 'center'
								},{
									field : 'pay_ee',
									title : '�տ���',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'audit_money',
									title : '�ܽ��',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'pay_type',
									title : '֧����ʽ',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(v){
										if(v == '1'){
											return "�ֽ�";
										}else if(v == '2'){
											return "����";
										}else if(v == '3'){
											return "����";
										}
									}
								},{
									field : 'has_play_money',
									title : '�Ƿ���',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden: true,
									formatter : function(v){
										if(v == 'Y'){
											return "�Ѵ��";
										}else if(v == 'N'){
											return "δ���";
										}
									}
								},{
									field : 'status',
									title : '����״̬',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "δ����";
										}
										if (value == 1) {
											return "������";
										}
										if (value == 2) {
											return "�����";
										}
										if (value == 3) {
											return "�Ѿܾ�";
										}
										if (value == 4) {
											return "��ȡ��";
										}
									}
								},{
									field : 'write_status',
									title : '����״̬',
									width : setColumnWidth(0.05),
									align : 'center',
									hidden: true,
									formatter : function(value) {
										if (value == 0) {
											return "δ����";
										}else if (value == 1) {
											return "������";
										}else if (value == 2) {
											return "�����";
										}else if (value == 3) {
											return "�Ѿܾ�";
										}else if (value == 4) {
											return "��ȡ��";
										}else{
											return "δ����";
										}
									}
								},{
									field : 'create_date',
									title : '�ᱨʱ��',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"));
									}
								},{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.plan_id;
										var html = "";
										if(row.status == "2"){
											 html = '<img style="cursor:pointer"  onclick=searchSingleDetail("'
													+ rid
													+ '","'
													+ row.transaction_id 
													+ '") title="�鿴��ϸ" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>'
													+ '&nbsp;&nbsp;<img style="cursor:pointer"  onclick=printTraReimbur("'
													+ rid
													+ '","'
													+ row.transaction_id 
													+ '") title="��ӡ������" src='
													+ imgUrl
													+ '/images/actions/action_print.png align="absMiddle"></img>';
										}else {
											 html = '<img style="cursor:pointer"  onclick=searchSingleDetail("'
													+ rid
													+ '","'
													+ row.transaction_id 
													+ '") title="�鿴��ϸ" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
										}
										return html;
									}
								}] ],
								toolbar : [ "-", {
									text : '����',
									iconCls : 'icon-add',
									handler : function() {
										createTraReimbur();
									}
								}, "-"]
					});

	
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/**
 * ��ʼ���ûҵ�ѡ��
 * */
function selectedFile(grid, rows){
	for(var j=0;j<rows.length;j++){
		if(2 != rows[j]['status']){
			$(".datagrid-row[datagrid-row-index="+j+"] input[type='checkbox']")[0].disabled=true;
		}
	}
}

function search() {
	$("#datagrid").datagrid('reload',
		{'status' : $('#status').combobox('getValue'),
		 'eventId' : encodeURIComponent($("#eventId").val()),
		 'title' : encodeURIComponent($("#title").val()),
		 'userName' : encodeURIComponent($("#userName").val()),
		 'startDate' : $("#startDate").datebox('getValue'),
		 'endDate' : $("#endDate").datebox('getValue')
		}
	 );
}

function toImport(){
	initMainFrame('���ƾ֤�ŵ���', '/account/accountAction!toImportFinancialDocNum.jspa', 500, 200);
}


function searchSingleDetail(rid,transaction_id){
	initMainFrame(true,'ְ�ܷ��ò鿴', '/account/accountAction!searchExpenseDetailForm.jspa?planId='+rid+'&transaction_id='+transaction_id, 800, 500,0,0);
}

function printTraReimbur(rid, transaction_id){
	initMainFrame(true,'ְ�ܷ��ô�ӡ', '/account/accountAction!printTraReimbur.jspa?planId='+rid+'&transaction_id='+transaction_id, 800, 500,0,0);
}

function createTraReimbur(){
	initMainFrame(true,'ְ�ܷ��ô���', '/account/accountAction!createTraReimburPrepare.jspa', 800, 500,0,0);
}


function playMoney(rids){
	$.messager.confirm('Confirm', '�Ƿ��������?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '��ѡ������!', 'warning');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].plan_id);
			}
			$("#planId").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + "/account/accountAction!playMoney.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function downLoadExcel(){
	var form = window.document.forms[0];
	form.action = appUrl + "/account/accountAction!downLoadExcel.jspa";
	form.target = "hideFrame";
	form.submit();
}

function closeMainFrame(){
	$("#maintFrame").window('close');
}

//�������ڶ���
function initMainFrame(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintFrame")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});			
	$win.window('open');
}

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

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function save(target) {
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if (rows.length == 0) {
		return;
	}
	var	transaction_id = rows[0].transaction_id;
	var	financial_doc_num = rows[0].financial_doc_num;
	var pattern=  /^\d{10}$/;
	if (!pattern.test(financial_doc_num)) {
		$.messager.alert('Tips', '���ƾ֤�ű���Ϊ10λ����������', 'warning');
		search();
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/account/accountAction!updateFinancialDocNum.jspa?transaction_id=" + transaction_id + "&financial_doc_num=" + financial_doc_num;
		form.target = "hideFrame";
		form.submit();
	}
}
function cancel(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
function toWriteExpenseAccount(plan_id,transaction_id){
	initMainFrame(true,'��Ҫ����', '/account/accountAction!createWriteExpenseFormPrepare.jspa?planId='+plan_id+"&transaction_id="+transaction_id+"&flag="+$("#flag").val(), 800, 500,0,0);
}
//�������ڶ���
function initMainFrame(ffit,title,url,widte,height,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintFrame")
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
