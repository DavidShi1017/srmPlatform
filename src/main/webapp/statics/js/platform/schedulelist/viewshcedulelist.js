$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/schedulelistAction!viewshcedulelist.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						fit:true,
						selectOnCheck: true,
						pageSize:20,
						queryParams: {
							vstel : encodeURI($("#vstel").val()),
							bolnr : encodeURI($("#bolnr").val())
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
								 
								},{
									field : 'id',
									title : 'id',
									width : 80,
									align : 'center',
									sortable : true,
									hidden:true
								},
								{
									field : 'bolnr',
									title : 'װ�˵���',
									width : 180,
									align : 'center',
									sortable : true
								},{
									field : 'vstel',
									title : 'װ�˵�',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'brgew',
									title : '����',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'team',
									title : '���˶�',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'workstart_date',
									title : '��ʼװж',
									width : 150,
									align : 'center',
									sortable:true 
							   },{
									field : 'workend_date',
									title : '����װж',
									width : 150,
									align : 'center',
									sortable:true 
							   },
								{
									field : 'operation',
									title : '����',
									width : 180,
									align : 'center',
									formatter : function(value, row, index) {
										var id =  row.id;
										return '<img style="cursor:pointer" onclick=editShceduleList('
												+ id
												+ ') title="������Ϣ" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img> &nbsp;&nbsp;'
												;
									}
								}
								 ] ],
						
							onDblClickRow: function (rowIndex, rowData) {
 								 
							}
					});
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,
		pageList : [ 20, 40, 60 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}


document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		searchList();
		return false;
	}
	return true;
};
 
function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.vstel = encodeURI($("#vstel").val());
	queryParams.bolnr = encodeURI($("#bolnr").val());
	$("#datagrid").datagrid('load');
}



function closeMaterial(){
	// �رմ���ҳ��
$("#werkPlan").window('close');
}


function initMaterial(title, url,WWidth,WHeight) {
	var url = appUrl + url;
//	var WWidth = 850;
//	var WHeight = 450;
	var $win = $("#werkPlan")
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


//�޸�
function editShceduleList(id) {
	initMaterial('װж���', '/schedulelistAction!toeditShceduleList.jspa?ids='+id,500,350);
}
 