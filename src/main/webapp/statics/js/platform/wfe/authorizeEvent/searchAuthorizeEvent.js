$(document).ready(function() {
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/authorizeEventAction!getAuthorizeEventJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : true,
						idField : 'eventId',
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.12),
									align : 'center',
									sortable : true
								},
								{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.12),
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '�������',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'initator',
									title : '�����',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'empName',
									title : '�����',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'modelId',
									title : '����ģ��',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
									
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
									width : setColumnWidth(0.12),
									align : 'center',
									sortable : true,
									formatter : function(value){
										if (value == 0) {
											return "δ����";
										}
										if (value == 1) {
											return "������";
										}
										if (value == 2) {
											return "�Ѵ���";
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
									width : setColumnWidth(0.2),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.17),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '<a href= javascript:searchEventDetail("'
											+ row.eventId + '","'+ row.currentDetailid + '","'
											+ row.keys + '","'
											+ row.modelId +'","'
											+ row.curStaId +'")>�鿴������� </a>';
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



function searchEventDetail(eventId, toDoDetail,keys,modelId,curStaId) {
	var form = document.getElementById("searchAuthorizeEvent");
	form.action = appUrl + "/wfe/eventAction!toProcessEvent.jspa?event_id="
	+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId;
	form.submit();
}
