$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});


function loadGrid() {
	var orgId = $("#orgId").val();
	var expType = $("#expType").val();
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '���ñ���б�',
						url : appUrl + '/account/accountAction!bNumberSearch.jspa?expType='+expType
						+'&orgId='+orgId,
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 300,
						width : 'auto',
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'bnumber_id',
									title : '���ID',
									width : 60,
									align : 'center',
									hidden : true,
									sortable : true
								},
								{
									field : 'budget_number',
									title : '���ñ��',
									width : 120,
									align : 'center'
								},
								{
									field : 'subjectName',
									title : '��Ŀ����',
									width : 100,
									align : 'center'
								},
								{
									title : "��ǰ���",
									field : 'budget_money',
									width : 100,
									sortable : false,
									align : 'center'
								},
								{
									title : "��ʼ���",
									field : 'init_money',
									width : 100,
									sortable : false,
									align : 'center'
								},{
									title : "�ɱ�����",
									field : 'costCenterName',
									width : 100,
									sortable : false,
									align : 'center'
								},
								{
									title : "��",
									field : 'theYear',
									width : 80,
									sortable : false,
									align : 'center'
								},
								{
									title : "��",
									field : 'theMonth',
									width : 80,
									sortable : false,
									align : 'center'
								}] ],
								toolbar : [ "-", {
									text : 'ȷ��',
									iconCls : 'icon-add',
									handler : function() {
										submit();
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

function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}
function search() {
	$("#datagrid").datagrid('load');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
//function createAccount() {
//	initMaintAccount('��Ŀ����', '/subjectSearch!toCreateSubject.jspa');
//}
function choseOrg(){
	initMaintAccount('400','400','��֯ѡ��', '/activityPlan!orgTreePage.jspa');
}
// �������ڶ���
function initMaintAccount(wWidth,wHeight,title, url) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var $win = $("#maintAccountant")
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
						maximizable : true,
						collapsible : false,
						draggable : true
					});

	$win.window('open');

}
function closeMaintBudgetNumber() {
	$("#maintBudgetNumber").window('close');
}
function queryBNumberDetail(id) {
	initMaintAccount('900','400','�˻��굥', '/budgetNumber!toqueryBNumberDetail.jspa?bnumber_id='+id);
}
function toAddBNumberMoney(id){
	initMaintAccount('400','400','���ñ�Ž���޸�', '/budgetNumber!toAddBNumberMoney.jspa?bnumber_id='+id);
}
function submit(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  no selected rows!');
		return;
	}
	var id = [];
	var name = [];
	var val = [];
	for ( var i = 0; i < rows.length; i++) {
		id.push(rows[i].budget_number);
		name.push(rows[i].budget_money);
		val.push(rows[i].bnumber_id);
	}
	window.parent.returnBudgetValue(id[0],name[0],val[0]);
	window.parent.closeMaintEvent();
}
function returnValue(selectedId, selectedName) {
	$("#costCentId").val(selectedId);
	$("#costCenterName").val(selectedName);
}
function selectCostCenter() {
	initMaintAccount('900','500','�ɱ�����ѡ��', '/subjectSearch!toSelectCostCenter.jspa');
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}
function closeOrgTree() {
	$("#maintAccountant").window('close');
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