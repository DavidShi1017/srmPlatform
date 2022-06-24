$(document).ready(function() {
	
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getProcessEventJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.11),
									align : 'center'
								},
								{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '�������',
									width : setColumnWidth(0.13),
									align : 'center'
									
								},
								{
									field : 'initator',
									title : '�����id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'empName',
									title : '�����',
									width : setColumnWidth(0.13),
									align : 'center'
									
								},
								{
									field : 'curStaId',
									title : '��ɫId',
									hidden : true
									
								},
								{
									field : 'modelId',
									title : '����ģ��id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'keys',
									title : 'keys',
									hidden : true
									
								},
								{
									field : 'modelName',
									title : '����ģ��',
									width : setColumnWidth(0.15),
									align : 'center'
									
								},
								{
									field : 'status',
									title : '����״̬',
									width : setColumnWidth(0.11),
									align : 'center',
									formatter : function(value){
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
								},
								{
									field : 'creatdate',
									title : '���ʱ��',
									width : setColumnWidth(0.16),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.18),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '<a href= javascript:processEvent("'
												+ row.eventId + '","'+ row.currentDetailid + '","'
												+ row.keys + '","'
												+ row.modelId +'","'
												+ row.curStaId +'")>���� </a>|'
												+'<a href=javascript:graphTrace("'
												+ row.eventId + '") > ���� </a>|'
												+ '<a href=javascript:searchProEventReader("'
												+ row.eventId + '") > ��Ȩ�鿴</a>';
										return strReturn;
									}
								} 
								] ]
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
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	$("#datagrid").datagrid('load');
}

function searchEventDetail(eventId) {
	var WWidth = 860;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + "/wfe/eventAction!searchEventDetail.jspa?eventId="
			+ eventId, "searchEventDetail", "left=" + WLeft + ",top=20"
			+ ",width=" + WWidth + ",height=" + (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}


//�������ڶ���
function initWindow(title, url, id, WWidth, WHeight) {
	
	var url = appUrl + url;
	var $win = $("#"+id)
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

function searchProEventReader(eventId) {
	initMaintEvent(true,'700','400','��������˹���','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
}

function processEvent(eventId, toDoDetail,keys,modelId,curStaId) {
	initMaintEvent(true,800, 480,'��������',"/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId+"&operationType=process",0,0);
}
//�������ڶ���
function initMaintEvent(ffit,widte,height,title, url,l,t) {
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
function closeMaintWindow(){
	$("#maintWindow").window("close");
}


