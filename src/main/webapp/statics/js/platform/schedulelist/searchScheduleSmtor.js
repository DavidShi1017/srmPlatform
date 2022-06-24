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
						url : appUrl + '/schedulelistAction!getScheduleSmtor.jspa',
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
									field : 'bolnr_level',
									title : '�˵����ȼ�',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'location_level',
									title : '��ַ���ȼ�',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'states',
									title : '״̬',
									width : 80,
									align : 'center',
									sortable:true,
									formatter : function(v){
										if(v=="1")
										{
											return "���ų�";
										}else 
										{
											return "--"; 
										} 
									}
							   },{
									field : 'is_lock',
									title : '����',
									width : 80,
									align : 'center',
									sortable:true,
									formatter : function(v){
										if(v=="1")
										{
											return "������";
										}else 
										{
											return "--"; 
										} 
									}
							   },{
									field : 'operation',
									title : '����',
									width : 180,
									align : 'center',
									formatter : function(value, row, index) {
										var id =  row.id;
										return '<img style="cursor:pointer" onclick=uplevel('
												+ id
												+ ') title="���ȼ���" src='
												+ imgUrl
												+ '/images/platform/activitiWebEdit/icons/action.up.png align="absMiddle"></img> &nbsp;&nbsp;'
												+'<img style="cursor:pointer" onclick=downlevel('
												+ id
												+ ') title="���ȼ���" src='
												+ imgUrl
												+ '/images/platform/activitiWebEdit/icons/action.down.png align="absMiddle"></img>'
												;
									}
								}
								 ] ],
						toolbar : [ "-", {
											text : 'ͬ��',
											iconCls : 'icon-reload',
											handler : function() {
												synchroSmtor();
											}
									}, "-",{
											text : '�ų�',
											iconCls : 'icon-add',
											handler : function() {
												shcedulelist();
											}
									}, "-",{
										text : '�鿴�ų��嵥',
										iconCls : 'icon-add',
										handler : function() {
											viewshcedulelist();
										}
								}, "-",{
									text : '����',
									iconCls : 'icon-add',
									handler : function() {
										islock();
									}
							}, "-",{
								text : '����',
								iconCls : 'icon-add',
								handler : function() {
									unlock();
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
	queryParams.bolnr = encodeURI($("#bolnr").val());
	$("#datagrid").datagrid('load');
}



//ͬ��װ�˵�
function synchroSmtor() {
	$.messager.confirm('Confirm', 'ȷ��ͬ��?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!synchroSmtor.jspa?",
					data : {
						strids : 0
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

//�ų�
function shcedulelist(strids){
	$.messager.confirm('Confirm', 'ȷ���ų�?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!shcedulelist.jspa?",
					data : {
						strids : 0
					},
					success : function(obj) {
						if(obj=="1"){
							loadGrid();
							$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
						}else{
							$.messager.alert('��ʾ��', '����ʧ��!'+obj, 'info');
						}
					}
				});
		}
	});

}
//�鿴�ų��嵥
function viewshcedulelist(){
	var url = appUrl + '/schedulelistAction!toviewshcedulelist.jspa?';
	var WWidth = 850;
	var WHeight = 450;
	var $win = $("#werkPlan")
			.window(
					{
						title : "�ų��嵥",
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
function closeMaterial(){
	// �رմ���ҳ��
$("#werkPlan").window('close');
}

//�˵����ȼ�����
function uplevel(strids){
//	$.messager.confirm('Confirm', 'ȷ��ɾ�� ?', function(r) {
//		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!uplevel.jspa?",
					data : {
						strids : strids
					},
					success : function(obj) {
						if(obj>0){
							loadGrid();
							//$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
						}else{
							$.messager.alert('��ʾ��', '����ʧ��!', 'info');
						}
					}
				});
//		}
//	});
}
 
//�˵����ȼ�����
function downlevel(strids){
//	$.messager.confirm('Confirm', 'ȷ��ɾ�� ?', function(r) {
//		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!downlevel.jspa?",
					data : {
						strids : strids
					},
					success : function(obj) {
						if(obj>0){
							loadGrid();
							//$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
						}else{
							$.messager.alert('��ʾ��', '����ʧ��!', 'info');
						}
					}
				});
//		}
//	});
}		
//����			 
	function islock(){
			var ids="";
			//��ȡ��ѡ��
			var rows = $('#datagrid').datagrid("getSelections");	    //��ȡ��ѡ���������	 //ѭ����ѡ����
			if(!(rows.length>0)){
				$.messager.alert('��ʾ��', '��ѡ��Ҫ�������м�¼!', 'info');
				return false;
			}
			for(var i =0;i<rows.length;i++){
						 if(i==0){
							 ids = rows[i].id;
						 }else{
							 ids =ids+","+rows[i].id;
						 }
			 }
			$.messager.confirm('Confirm', 'ȷ��ɾ����?', function(r) {
							if(r){
								 $.ajax({
										type : "post",
										async : false,
										url : appUrl+ "/schedulelistAction!islock.jspa?",
										data : {
											strids : ids
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
	
	
	//����		 
	function unlock(){
			var ids="";
			//��ȡ��ѡ��
			var rows = $('#datagrid').datagrid("getSelections");	    //��ȡ��ѡ���������	 //ѭ����ѡ����
			if(!(rows.length>0)){
				$.messager.alert('��ʾ��', '��ѡ��Ҫ�������м�¼!', 'info');
				return false;
			}
			for(var i =0;i<rows.length;i++){
						 if(i==0){
							 ids = rows[i].id;
						 }else{
							 ids =ids+","+rows[i].id;
						 }
			 }
			$.messager.confirm('Confirm', 'ȷ��ɾ����?', function(r) {
							if(r){
								 $.ajax({
										type : "post",
										async : false,
										url : appUrl+ "/schedulelistAction!unlock.jspa?",
										data : {
											strids : ids
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
