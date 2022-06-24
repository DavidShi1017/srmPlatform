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
						url : appUrl + '/loadCapacityAction!getLoadCapacityList.jspa',
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
							hand_team : encodeURI($("#hand_team").val())
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
									field : 'vstel',
									title : 'װ�˵�',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'hand_team',
									title : '���˶�',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'start_date',
									title : '��ʼʱ��',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'end_date',
									title : '����ʱ��',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'scheduling',
									title : 'װж����',
									width : 80,
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
										return '<img style="cursor:pointer" onclick=createLoadCapacity('
												+ id
												+ ') title="������Ϣ" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img> &nbsp;&nbsp;'
												+'<img style="cursor:pointer" onclick=deleteLoadCapacity('
												+ id
												+ ') title="ɾ����Ϣ" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img>'
												;
									}
								} 
								 ] ],
						toolbar : [ "-", {
											text : '����',
											iconCls : 'icon-add',
											handler : function() {
												createLoadCapacity(0);
											}
									}, "-",{
											text : 'ɾ��',
											iconCls : 'icon-remove',
											handler : function() {
												delLoadCapacity();
											}
									}],
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
	queryParams.hand_team = encodeURI($("#hand_team").val());
	$("#datagrid").datagrid('load');
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


//����
function createLoadCapacity(id) {
	initMaterial('װж����', '/loadCapacityAction!eidtLoadCapacity.jspa?ids='+id,500,350);
}

function delLoadCapacity(){
	var ids="";
	 //��ȡ��ѡ��
	 var rows = $('#datagrid').datagrid("getSelections");	    //��ȡ��ѡ���������	 //ѭ����ѡ����
	 if(!(rows.length>0)){
		 $.messager.alert('��ʾ��', '��ѡ��Ҫɾ�����м�¼!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 ids = rows[i].id;
		 }else{
			 ids =ids+","+rows[i].id;
		 }
	 }
	this.deleteLoadCapacity(ids);
	
}


function deleteLoadCapacity(strids){
	$.messager.confirm('Confirm', 'ȷ��ɾ�� ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/loadCapacityAction!deleteLoadCapacity.jspa?",
					data : {
						strids : strids
					},
					success : function(obj) {
						if(obj>0){
							loadGrid();
							$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
						}else{
							$.messager.alert('��ʾ��', '����ʧ��!', 'info');
						}
					}
				});
		}
	});

}

function closeMaterial(){
	// �رմ���ҳ��
$("#werkPlan").window('close');
}

 