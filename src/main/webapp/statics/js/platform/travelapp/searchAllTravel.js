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
						url : appUrl + '/travelApp/travelAppAction!searchTravelJson.jspa?searchUserRole=all',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : false ,
						height : height*0.88,
						columns : [ [
								{
									field : 'travelId',
									title : '��������',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},{
									field : 'transactionId',
									title : '���뵥��',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										return "SQ"+v;
									}
								},{
									field : 'writeEventId',
									title : '��������',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										if(v !=undefined){
											return "HX"+v;
										}
									}
								},{
									field : 'title',
									title : '�����������',
									width : setColumnWidth(0.15),
									align : 'center'
								},{
									field : 'writeEvent',
									title : '�����������',
									width : setColumnWidth(0.15),
									align : 'center'
								},{
									field : 'payee',
									title : '�տ���',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'auditMoney',
									title : '�ܽ��',
									width : setColumnWidth(0.05),
									align : 'center'
								},{
									field : 'payType',
									title : '֧����ʽ',
									width : setColumnWidth(0.05),
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
									field : 'writeStatus',
									title : '����״̬',
									width : setColumnWidth(0.05),
									align : 'center',
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
									field : 'createDate',
									title : '�ᱨʱ��',
									width : setColumnWidth(0.12),
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
										var rid = row.travelId;
										var html = "";
											html = '<img style="cursor:pointer"  onclick=searchTravelDetail("'
													+ rid
													+ '","'
													+ row.transactionId 
													+ '") title="�鿴��ϸ" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
											if(row.writeStatus!=undefined){		
													html=html
													+ '&nbsp;&nbsp;<img style="cursor:pointer"  onclick=print("'
													+ rid
													+ '","'
													+ row.writeStatus
													+ '","'
													+ row.writeEventId 
													+ '") title="��ӡ������" src='
													+ imgUrl
													+ '/images/actions/action_print.png align="absMiddle"></img>';
									        }
										return html;
									}
								}] ]
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
		var bhxjFlag="";
		$("[name='bhxjFlag']").each(function() {
			if (this.checked) {
				bhxjFlag = this.value;
			} else {
				bhxjFlag = "N";
			}
		});
		var queryParams = $('#datagrid').datagrid('options').queryParams;
		queryParams.writeStatus = $('#status').combobox('getValue');
		var eventId=$("#eventId").val();
		if(isNaN(eventId)){
			eventId=$("#eventId").val().substring(2);
			var ev2=$("#eventId").val().substring(0,2);
			if(ev2!='hx'&&ev2!='HX'){
				$.messager.alert('Tips', "����ȷ�������Ĳ�ѯ�������񵥺�", 'warning');
				return;
			}
		}
		queryParams.writeEventId = encodeURIComponent(eventId);
		queryParams.writeEvent = encodeURIComponent($("#title").val());
		queryParams.startDate =$("#startDate").datebox('getValue');
		queryParams.endDate = $("#endDate").datebox('getValue');
		queryParams.orgId = $("#orgId").val();
		queryParams.bhxjFlag = bhxjFlag;
		$("#datagrid").datagrid('reload');
}



function searchTravelDetail(rid,transactionId){
	initMainFrame(false,'���÷�����ϸ�鿴', '/travelApp/travelAppAction!travelDetail.jspa?travelId='+rid+"&eventId="+transactionId, 800, 500,150,80);
}

function print(rid,writeStatus,writeEventId){
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/travelApp/travelAppAction!print.jspa?travelId='+rid+'&eventId='+writeEventId+'&writeStatus='+writeStatus, "printTravel",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
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

function cancel(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
function toTravelWta(travelId,transactionId){
	initMainFrame(true,'��Ҫ����', '/travelApp/travelAppAction!travelWta.jspa?travelId='+travelId+"&transactionId="+transactionId+"&flag="+$("#flag").val(), 800, 500,0,0);
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

function selectOrgTree() {
	initMaintWindowForOrg('ѡ����֯', '/orgAction!orgTreePage.jspa');
}


function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}



