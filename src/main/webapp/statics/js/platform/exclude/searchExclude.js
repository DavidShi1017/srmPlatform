$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/excludeAction!getExcludeList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						selectOnCheck: true,
						fit:true,
						pageSize:20,
						queryParams: {
							matnr :  $("#matnr").val(),
							kunnr :  $("#kunnr").val() 
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
								 
								},{
									field : 'id',
									title : '����',
									width : 100,
									align : 'center',
									sortable : true
								},{
									field : 'kappl',
									title : 'Ӧ�ó���',
									width : 100,
									align : 'center',
									sortable : true,
									hidden:true
								},{
									field : 'kschl',
									title : '��������',
									width : 100,
									align : 'center',
									sortable : true,
									hidden:true
								},{
									field : 'kunnr',
									title : '�ͻ�',
									width : 185,
									align : 'center', 
									sortable:true
							   },{
									field : 'matnr',
									title : '���ϱ���',
									width : 100,
									align : 'left', 
									sortable:true
							   },{
									field : 'datab1',
									title : '��Ч��ʼ����',
									width :100,
									align : 'center',
									hidden:true,
									sortable:true
								},{
									field : 'datbi1',
									title : '��Ч��������',
									width :100,
									align : 'center',
									sortable:true,
									hidden:true
								},{
									field : 'dtype',
									title : '����',
									width :100,
									align : 'center',
									sortable:true,
									hidden:true,
									formatter : function(v){
										if(v=="01"){
											return "�ͻ�";
										}else if(v=="02"){
											return "�ͻ���";
										}else if(v=="03"){
											return "�ش���";
										}else if(v=="04"){
											return "���д���";
										}
									}
								}
								 ] ],
						toolbar : [ "-", {
											text : 'ͬ��',
											iconCls : 'icon-add',
											handler : function() {
												getExcludeFromSap();
											}
									}, "-" ] 
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
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
	queryParams.matnr =  $("#matnr").val() ;
	queryParams.kunnr =  $("#kunnr").val();
	$("#datagrid").datagrid('load');
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

function getExcludeFromSap(){
	$.messager.confirm('Confirm', 'ȷ��ͬ�� ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/excludeAction!getExcludeFromSap.jspa?",
					success : function(obj) {
							loadGrid();
					}
				});
		}
	});
}
 