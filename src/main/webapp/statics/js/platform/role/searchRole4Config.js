$(document).ready(function() {
	load_role_data(); // Ȩ�޵��ѯ
//	$('#hideFrame').bind('load', promgtMsg);
});

function load_role_data() {
	$('#role_list').datagrid({
		iconCls : 'icon-list',
		title : 'Role List',
		height : 350,
		striped : true,
		url : appUrl + '/role/roleAction!getRole4ConfigJsonList.jspa'
		+ '?conpointId=' + conpointId+'&ran=' + Math.random(),

		loadMsg : 'Loading...',
		singleSelect : true,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		// frozenColumns : [ [ ] ],
		columns : [ [

		{
			id : 'roleId',
			title : 'Role ID',
			field : 'roleId',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			field : 'roleName',
			title : 'Role Name',
			align : 'center',
			width : 250,
			sortable : true
		}, {
			field : 'descn',
			title : 'Description',
			width : 150,
			align : 'center',
			sortable : true
		} ] ]
	});
	
	// ��ҳ�ؼ�
	var p = $('#role_list').datagrid('getPager');
/*	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});*/
}

function s_role_search() {
	var queryParams = $('#role_list').datagrid('options').queryParams;
	queryParams.roleId = $("#s_role_id").val();
	queryParams.roleName = encodeURIComponent($("#s_role_name").val());
	$("#role_list").datagrid('load');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if(event.keyCode == 8) {
	     // �������textarea�ڲ�ִ���κβ���
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // �����readOnly����disable��ִ���κβ���
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};
