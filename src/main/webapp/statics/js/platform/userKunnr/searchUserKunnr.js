$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						url : appUrl + '/userKunnrAction!getUserKunnrList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						fit:true,
						height : height,
						selectOnCheck: true,
						queryParams: {
							empCode : encodeURI($("#empCode").val()),
							kunnr :  $("#kunnr").val()
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
								 
								},{
									field : 'id',
									title : '����',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'empCode',
									title : 'Ա�����',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'empName',
									title : 'Ա������',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'kunnr',
									title : '�ͻ�����',
									width : 80,
									align : 'center', 
									sortable:true
							   },{
									field : 'kunnrName',
									title : '�ͻ�����',
									width : 250,
									align : 'center', 
									sortable:true
							   },{
									field : 'beginDate',
									title : '��Ч��ʼʱ��',
									width :100,
									align : 'center',
									formatter : function(value) {
										if(value !=null){
										  return value;
										}else{
										return "";
										}
									}
								},{
									field : 'endDate',
									title : '��Ч����ʱ��',
									width :100,
									align : 'center',
									formatter : function(value) {
										if(value !=null){
											return value;
										}else{
										return "";
										}
									}
								},
								{
									field : 'operation',
									title : '����',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										var id = encodeURIComponent(row.id);
										return '<img style="cursor:pointer" onclick=deleteUserKunnr('
										+ id
										+ ') title="ɾ����Ϣ" src='
										+ imgUrl
										+ '/images/actions/action_del.png align="absMiddle"></img> &nbsp;&nbsp;<img style="cursor:pointer" onclick=editUserKunnr('
										+ id
										+ ') title="�޸���Ϣ" src='
										+ imgUrl
										+ '/images/actions/action_edit.png align="absMiddle"></img>'
										;
									}
								} 
								 ] ],
						toolbar : [ "-", {
											text : '����',
											iconCls : 'icon-add',
											handler : function() {
												createUserKunnr(0);
											}
									}, "-",{
											text : 'ɾ��',
											iconCls : 'icon-remove',
											handler : function() {
												delUserKunnr();
											}
									}] 
					});
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

//�½��ͻ�����
function createUserKunnr(id) {
	initMaterial('�����ͻ�������Ϣ', '/userKunnrAction!editUserKunnr.jspa?id='+id,600,400);
}

function editUserKunnr(id){
	initMaterial('�޸Ŀͻ�������Ϣ', '/userKunnrAction!editUserKunnr.jspa?id='+id,600,400);
}
function delUserKunnr(){
	 var userKunnrIds="";
	//��ȡ��ѡ��
	 var rows = $('#datagrid').datagrid("getSelections");	    //��ȡ��ѡ���������	 //ѭ����ѡ����
	 if(!(rows.length>0)){
		 $.messager.alert('��ʾ��', '��ѡ��Ҫɾ�����м�¼!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 userKunnrIds = rows[i].id;
		 }else{
			 userKunnrIds =userKunnrIds+","+rows[i].id;
		 }
	 }
	this.deleteUserKunnr(userKunnrIds);
}
function deleteUserKunnr(userKunnrIds){
	$.messager.confirm('Confirm', 'ȷ��ɾ�� ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/userKunnrAction!deleteUserKunnr.jspa",
					data : {
						userKunnrIds : userKunnrIds
					},
					success : function(obj) {
						loadGrid();
						$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
					}
				});
		}
	});
}
 
function initMaterial(title, url,WWidth,WHeight) {
	var url = appUrl + url;
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
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}
function closeMaterial(){
	// �رմ���ҳ��
	$("#werkPlan").window('close');
}

function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.empCode = encodeURIComponent($("#empCode").val());
	queryParams.kunnr =  encodeURIComponent($("#kunnr").val());
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

