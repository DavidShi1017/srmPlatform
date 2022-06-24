$(document).ready(function() {
			loadGrid();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	$('#datagrid1').datagrid({
				iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/station!searchStationUserMore.jspa?userId='+$('#userId').val(),
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : 270,
				columns : [[{
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'id',
							title : 'ְλID',
							width : 100,
							align : 'center',
							hidden:true
						}, {
							field : 'empCode',
							title : '�û�ID',
							width : 100,
							align : 'center'
						}, {
							field : 'userName',
							title : '�û���',
							width : 100,
							align : 'center'
						}
						, {
							field : 'orgName',
							title : '��֯',
							width : 170,
							align : 'center'
						}
						, {
							field : 'stationName',
							title : '��λ����',
							width : 173,
							align : 'center'
						}
						]]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid1').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '��',
				afterPageText : 'ҳ    �� {pages} ҳ',
				displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
			});
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function close() {
	window.parent.closeMaintStation();
}


function search() {
	$("#datagrid1").datagrid('reload');
}



/**
 * 
 * @param {} e
 * @return {Boolean}
 */

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
