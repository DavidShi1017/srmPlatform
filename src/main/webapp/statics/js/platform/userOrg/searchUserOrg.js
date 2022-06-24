$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						url : appUrl + '/userOrgAction!getUserOrgList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
//						height : height,
						fit:true,
						selectOnCheck: true,
						pageSize:20,
						queryParams: {
							emp_code : encodeURI($("#emp_code").val()),
							org_code :  $("#orgId").val()
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
									sortable : true,
									hidden:true
								},
								
								{
									field : 'emp_code',
									title : 'Ա�����',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'emp_name',
									title : 'Ա������',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'org_code',
									title : '��֯����',
									width : 100,
									align : 'center', 
									sortable:true
							   },
							   {
									field : 'org_name',
									title : '��֯����',
									width : 100,
									align : 'center', 
									sortable:true
							   },
							   {
									field : 'begin_date',
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
									field : 'end_date',
									title : '��Ч����ʱ��',
									width :setColumnWidth(0.1),
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
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var id = encodeURIComponent(row.id);
										return '<img style="cursor:pointer" onclick=delUserCust('
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
	initMaterial('����������֯', '/userOrgAction!editUserOrg.jspa?id='+id,550,430);
}
 
 
function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.emp_code = encodeURI($("#emp_code").val());
	queryParams.org_code =  $("#orgId").val();
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
					url : appUrl+ "/userOrgAction!deleteUserOrg.jspa?",
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


function empCodeBox(){
	$('#emp_code').combogrid({
		panelHeight : 280,
		panelWidth : 450,
		pagination : true,
		multiple : false,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/allUserAction!getUserInfoList.jspa?bhxjFlag=C&orgId=10000000&ran='+ Math.random(),
		idField : 'loginId',
		textField : 'userName',
		columns : [[{
					field : 'loginId',
					title : 'Ա�����',
					width : 120
				}, {
					field : 'userName',
					title : 'Ա������',
					width : 250
				}]],
		toolbar : '#toolbarEmpCode',
		onSelect : function(index, record) {
			 
		}
	});
}

/**
 * ����֯��
 */
function selectOrgTree() {
	openWindow(false,'ѡ����֯', '/customerAction!orgTreePage.jspa?orgId=10000000', 400, 400,100,20);
}

/**
 * ��֯��ѡ��֯���ص������
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
/**
 * ��֯��ѡ����֮��ر�
 */
function closeOrgTree() {
	closeWindow();
}


//�������ڶ���
function openWindow(ffit,title, url, WWidth, WHeight,l,t) {
	var url = crmUrl + url;
	var $win = $("#hiddenWin1")
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
						draggable : true,
						fit:ffit,
						left : l,
						top: t
					});

	$win.window('open');

}

//�رմ���
function closeWindow() {
	$("#hiddenWin").window('close');
}