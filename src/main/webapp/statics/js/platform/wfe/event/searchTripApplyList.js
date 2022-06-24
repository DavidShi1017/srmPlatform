$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getTripApplyJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'orgName',
									title : '��������',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'departure',
									title : '������',
									width : setColumnWidth(0.05),
									align : 'center'

								},
								{
									field : 'place',
									title : 'Ŀ�ĵ�',
									width : setColumnWidth(0.05),
									align : 'center'

								},
								{
									field : 'stop',
									title : '��ͣ��',
									width : setColumnWidth(0.05),
									align : 'center'

								},
								{
									field : 'tripWayName',
									title : '���з�ʽ',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'beginDate',
									title : '��ʼ����',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value) {
										return utcToDate(value);
									}

								},
								{
									field : 'endDate',
									title : '��������',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value) {
										return utcToDate(value);
									}

								},{
									field : 'amount',
									title : '������',
									width : setColumnWidth(0.05),
									align : 'center'

								},
								{
									field : 'reason',
									title : '����',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'userName',
									title : '������',
									width : setColumnWidth(0.05),
									align : 'center',
								},
								{
									field : 'createDate',
									title : '��������',
									width : setColumnWidth(0.08),
									align : 'center',
								},
								{
									field : 'status',
									title : '����״̬',
									width : setColumnWidth(0.08),
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
								}] ],
								toolbar : [ "-", {
									text : '����',
									iconCls : 'icon-add',
									handler : function() {
										createTripApply();
									}
								}, "-" ]
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
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.userName = encodeURIComponent($("#userName").val());
	$("#datagrid").datagrid('load');
}

function  createTripApply(){
	initWindow(true,"�������봴��", "/wfe/eventAction!toBusinessTripApply.jspa", "maintWindow", 200, 300,0,0);
}
function closeMainFrame(){
	$("#maintWindow").window("close");
}
// �������ڶ���
function initWindow(FFit,title, url, id, WWidth, WHeight,l, t) {
	var url = appUrl + url;
	var $win = $("#" + id)
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
						draggable : true,
						fit : FFit,
						left : l,
						top : t
					});

	$win.window('open');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
function download(){
	var form = window.document.forms[0];
	form.action = appUrl + "/wfe/eventAction!downloadExecel.jspa";
	form.submit();
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
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
