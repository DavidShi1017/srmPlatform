$(document).ready(function() {
	//loadGrid();
	$('#hideFrame').bind('load', promgtMsg); 
});
$('#datagrid').datagrid({
	iconCls : 'icon-list',
	
	singleSelect : false,
	pagination : true,
	pageSize:20,
	nowrap : false,
	striped:true ,
	remoteSort : true,
	fit:true,
	selectOnCheck: true,
	rownumbers:true,
	columns : [[
			{
				field : 'ck',
				checkbox : true
			},{
				field : 'spart',
				title : 'Ʒ�����',
				width : 70,
				align : 'center',
				hidden:true
			},{
				field : 'mvgr1',
				title : 'ϵ�б���',
				width : 70,
				align : 'center',
				hidden:true
			},{
				field : 'kbetr',
				title : '��Ǯ',
				width : 70,
				align : 'center',
				hidden:true
			},{
				field : 'matnr',
				title : '��Ʒ����',
				width : 80,
				align : 'center',
				sortable : true
			},{
				field : 'maktx',
				title : '��Ʒ����',
				width : 400,
				align : 'center', 
				sortable:true
		   },{
				field : 'spart_txt',
				title : 'Ʒ��',
				width : 70,
				align : 'center',
				sortable : true
			},{
				field : 'mvgr1_txt',
				title : 'ϵ��',
				width : 70,
				align : 'center',
				sortable : true
			}
			 ]],
	toolbar : [ "-", {
						text : 'ȷ��',
						iconCls : 'icon-add',
						handler : function() {
							createProduct();
						}
				}] 
});

function loadGrid() {
	var txt=$('#productName').val();
	$('#datagrid').datagrid({
				iconCls : 'icon-list',
				url : appUrl + '/inOutStockAction!getAllProductList.jspa',
				queryParams: {maktx:encodeURIComponent(txt),productIdList:$('#hdProductIdList').val()},
				loadMsg : '����Զ��������,��ȴ�...',
				singleSelect : false,
				pagination : true,
				pageSize:20,
				nowrap : false,
				striped:true ,
				remoteSort : true,
				fit:true,
				selectOnCheck: true,
				rownumbers:true,
				columns : [ [
						{
							field : 'ck',
							checkbox : true
						 
						},{
							field : 'spart',
							title : 'Ʒ�����',
							width : 70,
							align : 'center',
							hidden:true
						},{
							field : 'mvgr1',
							title : 'ϵ�б���',
							width : 70,
							align : 'center',
							hidden:true
						},{
							field : 'kbetr',
							title : '��Ǯ',
							width : 70,
							align : 'center',
							hidden:true
						},{
							field : 'matnr',
							title : '��Ʒ����',
							width : 80,
							align : 'center',
							sortable : true
						},{
							field : 'maktx',
							title : '��Ʒ����',
							width : 400,
							align : 'center', 
							sortable:true
					   },{
							field : 'spart_txt',
							title : 'Ʒ��',
							width : 70,
							align : 'center',
							sortable : true
						},{
							field : 'mvgr1_txt',
							title : 'ϵ��',
							width : 70,
							align : 'center',
							sortable : true
						}
						 ] ],
				toolbar : [ "-", {
									text : 'ȷ��',
									iconCls : 'icon-add',
									handler : function() {
										createProduct();
									}
							}] 
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



function search(){
	loadGrid();
}



function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}

function createProduct(){
	var selRows = $('#datagrid').datagrid('getChecked'); 
	var names = [];
	$.each(selRows, function(index, item){
		names.push(item.mvgr1+"#"+item.matnr+"#"+item.maktx+"#"+item.mvgr1_txt+"#"+item.kbetr+"#"+item.spart);
	}); 
	window.parent.addProduct(names);
}



