$(document).ready(function() {
	loadGrid();
	$('#hideFrameSemiAutomatic').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��Ա�б�',
						url : appUrl
								+ '/wfe/authorizeEventAction!getEmpListByOrgId.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : 380,
						onDblClickRow : function(rowIndex, rowData) {
							// $("#source").append("<div class='drag-item'
							// userId='"+rowData.userId+"'>"+rowData.userName+"</div>");
							$("#source")
									.append(
											'<div class="drag-item" userId="'
													+ rowData.userId
													+ '"><img src="'
													+ imgUrl
													+ '/images/actions/action_roles.png" align="absMiddle"></img>'
													+ rowData.userName
													+ '</div>');
							reloadST();
						},
						columns : [ [ {
							field : 'ck',
							checkbox : false
						}, {
							field : 'userId',
							title : '��ԱID',
							align : 'center',
							hidden : true
						}, {
							field : 'userName',
							title : '��Ա����',
							width : 150,
							align : 'center'
						} ] ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/*
 * function createAuthorization(rowIndex){ var rows =
 * $('#datagrid').datagrid('getSelected'); $("#source").append("<div
 * class='drag-item' userId='"+rows[0].userId+"'>"+rows[0].userName+"</div>");
 * $("#source").append("<div class='drag-item'><img
 * src='statics/images/actions/action_roles.png'></img></div>"); reloadST(); }
 */

$(document)
		.ready(
				function() {
					$('#orgTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});
					$('#orgTree')
							.tree(
									{
										method : 'post',
										animate : true,
										url : appUrl
												+ '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0',
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
													+ "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node="
													+ node.id;
										},
										onClick : function(node) {// �����¼�
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
											search(node.id);

										}
									});
				});

// function select(selectedId, selectedName) {
// document.getElementById("orgId").value = selectedId;
// document.getElementById("orgName").value = selectedName;
// window.parent.returnValue(selectedId, selectedName);
// window.parent.closeOrgTree();
// }

function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	// queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.orgId = encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}
// ///
function reloadST() {
	$('.drag-item').draggable({
		revert : true,
		deltaX : 0,
		deltaY : 0,
		proxy : 'clone',
		revert : true,
		cursor : 'auto',
		onStartDrag : function() {
			$(this).draggable('options').cursor = 'not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag : function() {
			$(this).draggable('options').cursor = 'auto';
		}
	}).droppable({
		onDragOver : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(source).insertAfter(this);
			$(source).removeClass('over');
		}
	});
	$('#target,#source').droppable({
		onDragEnter : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(this).append(source);
			$(this).removeClass('over');
		}
	});
}
function save() {
	var context = "";
	$("#source div").each(function() {
		if (context.length > 0) {
			context = context + "," + $(this).attr("userId");
		} else {
			context = $(this).attr("userId");
		}
	});
	if ("" == context) {
		alert("��ѡ�������б�");
		return;
	} else {
		if($("#eventId").val() != ""){
			$.messager.confirm('Confirm', 'ȷ�ϳ���?', function(r) {
				if (r) {
					$("#userList").val(context);
					$.messager.progress();
					var form = window.document.forms[0];
					form.action = "eventAction!creatCc.jspa";
					form.target = "hideFrameSemiAutomatic";
					form.submit();
				}
			});
		}else{
			window.parent.document.getElementById("userList").value = context;
			close();
		}
	}
}
function close() {
	window.parent.closeMaintEvent();
}
function promgtMsg() {
	$.messager.progress('close');
	var hideFrameSemiAutomatic = document.getElementById("hideFrameSemiAutomatic");
	var failResult = hideFrameSemiAutomatic.contentWindow.failResult;
	var successResult = hideFrameSemiAutomatic.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeMaintEvent();
		});
	}
}