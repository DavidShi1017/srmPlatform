$(document).ready(function() {
	loadGrid();
});

function loadGrid(){
	$("#datagrid").datagrid({
		method: 'get',
		async : false,
		url:appUrl+ '/inOutStockAction!getCustmNoJson.jspa?ran='+Math.random(),
		//queryParams: {},
		columns:[[ 
		          	{
									field : 'ck',
									checkbox : true,
								 
								},{field:'ID',title:'ID',width:80,align:'center',hidden:true}, 
					{field:'KUNNR',title:'�ͻ�����',width:80,align:'center'}, 
					{field:'KUNNM',title:'�ͻ�����',width:250}, 
					{field:'CREATEDATE',title:'����ʱ��',width:130,align:'center'},
					{field:'STATE',title:'״̬',width:60,align:'center'}, 
					{field:'MEMO',title:'����ԭ��',width:400,align:'center'}
				]],
		fit:true,
		rownumbers: true,
		showFooter:true,
		pagination : true,
		pageSize:30,
		singleSelect:true,
		toolbar: [{
			iconCls: "icon-add",
			text: "����",
			handler:function(){
				createRecord();
			}
		},"-",{
			iconCls: "icon-remove",
			text: "ɾ��",
			handler:function(){
				deleteRecord();
			}
		},"-",{
			iconCls: "icon-edit",
			text: "�༭",
			handler:function(){
				editRecord();
			}
		}]
	});
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 30,
		pageList : [ 30, 50, 100 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}
function createRecord() {
	initWindows('�½���Ч�ͻ�', '/inOutStockAction!custmNoEdit.jspa',600,400);
}
function editRecord(){
	var ids="";
	//��ȡ��ѡ��
	 var rows = $('#datagrid').datagrid("getSelections");	    //��ȡ��ѡ���������	 //ѭ����ѡ����
	 if(!(rows.length>0)){
		 $.messager.alert('��ʾ��', '��ѡ��Ҫ�༭���м�¼!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 ids = rows[i].ID;
		 }else{
			 ids =ids+","+rows[i].ID;
		 }
	 }
	 initWindows('�޸���Ч�ͻ�', '/inOutStockAction!custmNoEdit.jspa?id='+ids,600,400);
}
function deleteRecord(){
	 var ids="";
	//��ȡ��ѡ��
	 var rows = $('#datagrid').datagrid("getSelections");	    //��ȡ��ѡ���������	 //ѭ����ѡ����
	 if(!(rows.length>0)){
		 $.messager.alert('��ʾ��', '��ѡ��Ҫɾ�����м�¼!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 ids = rows[i].ID;
		 }else{
			 ids =ids+","+rows[i].ID;
		 }
	 }
	 $.messager.confirm('��ʾ', 'ȷ��ɾ�� ?', function(r) {
			if(r){
				 $.ajax({
						type : "post",
						async : false,
						url : appUrl+ "/inOutStockAction!deleteCustmNo.jspa",
						data : {
							id : ids
						},
						success : function(obj) {
							loadGrid();
							$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
						}
					});
			}
		});
}

function initWindows(title, url,WWidth,WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
						fit : false,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}
function closeWindows() {
	$("#hiddenWin").window('close');
}
