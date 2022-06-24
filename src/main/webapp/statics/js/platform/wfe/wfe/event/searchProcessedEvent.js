$(document).ready(function() {
	loadGrid();
	//����״̬��0Ϊδ����1Ϊ�����У�2Ϊ������ɣ�3Ϊ�Ѿܾ���4��ʾȡ����
	var data=[{'text':'δ����','value':'0'},{'text':'������','value':'1'},{'text':'�����','value':'2'},{'text':'�Ѿܾ�','value':'3'},{'text':'��ȡ��','value':'4'}];
	$('#status').combobox(
			{
				data:data,
				valueField : 'value',
				textField : 'text',
				onLoadSuccess : function() {
				},
				editable : false
			});
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getProcessedEventJsonList.jspa',
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
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '�������',
									width : setColumnWidth(0.11),
									align : 'center'
									
								},
								{
									field : 'initator',
									title : '�����id',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'empName',
									title : '�����',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'orgId',
									title : '�������֯Id',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'orgName',
									title : '�������֯',
									width : setColumnWidth(0.10),
									align : 'center'
									
								},
								{
									field : 'modelName',
									title : '����ģ��',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'status',
									title : '����״̬',
									width : setColumnWidth(0.08),
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
									field : 'curUserName',
									title : '��ǰ������',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'creatdate',
									title : '���ʱ��',
									width : setColumnWidth(0.14),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.20),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '<a href= javascript:searchEventDetail("'
												+ row.eventId + '")>�鿴������� </a>|'
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
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
	initWindow('��������˹���', '/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='
	+ eventId, 'maintWindow', 800, 480);
}

function searchEventDetail(eventId) {
	initMaintEvent(true,800, 480,'��������', "/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId,0,0);
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