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
						url : appUrl + '/userCustAction!getUserCustList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						//height : height,
						selectOnCheck: true,
						fit:true,
						queryParams: {
							empCode : encodeURI($("#empCode").val()),
							customerNumber :  $("#customerNumber").val(),
							empName:encodeURI($("#empName").val())
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
								},
								
								{
									field : 'empCode',
									title : 'Ա�����',
									width : 100,
									align : 'center',
									sortable : true
								},{
									field : 'empName',
									title : 'Ա������',
									width : 100,
									align : 'center',
									sortable : true
								},{
									field : 'customerNumber',
									title : '�ŵ����',
									width : 100,
									align : 'center', 
									sortable:true
							   },{
									field : 'customerName',
									title : '�ŵ�����',
									width : 220,
									align : 'left', 
									sortable:true
							   },{
									field : 'beginDate',
									title : '��Ч��ʼʱ��',
									width :100,
									align : 'center',
									formatter : function(value) {
										if(value !=null){
										  return value;
										//return utcToDate(value);
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
										//return utcToDate(value);
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
										return '<img style="cursor:pointer" onclick=deleteUserCust('
												+ id
												+ ') title="ɾ����Ϣ" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img> &nbsp;&nbsp;<img style="cursor:pointer" onclick=createUserCust('
												+ id
												+ ') title="������Ϣ" src='
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
												createUserCust(0);
											}
									}, "-",{
											text : 'ɾ��',
											iconCls : 'icon-remove',
											handler : function() {
												delUserCust();
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

function closeMaterial(){
	// �رմ���ҳ��
$("#werkPlan").window('close');
}
function createUserCust(id) {
	initMaterial('������Ʒ����Ϣ', '/userCustAction!editUserCust.jspa?id='+id,650,400);
	
}
 
 
function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.empCode = encodeURI($("#empCode").val());
	queryParams.customerNumber =  $("#customerNumber").val();
	queryParams.empName = encodeURI($("#empName").val());
	$("#datagrid").datagrid('load');
}



function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	//date = str[5] + "-";
	//date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	//date = str[0];//��ʾ������
	date = utcCurrTime;//��ʾ��ʱ����
	return date;
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

function delProductGroup(id){
	  this.deleteProductGroup(id);
}
function delUserCust(){
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
	this.deleteUserCust(ids);
}
function deleteUserCust(ids){
	$.messager.confirm('Confirm', 'ȷ��ɾ�� ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/userCustAction!deleteUserCust.jspa?",
					data : {
						ids : ids
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