$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	// �ͻ����� ����
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + 'K',
		valueField : 'channelId',
		textField : 'channelName'
	});
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val(),
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						fit:true,
						striped : true,
						columns : [ [{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.15),
									align : 'center',
									hidden:true,
									formatter : function(value, row, index) {
										var kunnr = row.kunnr;
										var status = row.status;
										var license = "<a href='#' onclick=viewLicense('"+ kunnr+ "')>֤��</a>| ";
		
										var scan = "  <a href='#' onclick=viewInfo('"
												+ kunnr
												+ "')>����</a>|";
										var edit = status == 1 ? " <a href='#' onclick=editInfo('"
												+ kunnr
												+ "')>�޸�</a>|"
												: "&nbsp;&nbsp;&nbsp;&nbsp;";
										var close = status == 1 ? "  <a href='#' onclick=closeKunnr('"
												+ kunnr
												+ "') >�ػ�</a>"
												: "&nbsp;&nbsp;&nbsp;&nbsp;";
										return license + scan + edit + close;
									}
								},
								{
									field : 'kunnr',
									title : '�����̴���',
									//width : setColumnWidth(0.08),
									width : 80,
									align : 'center',
									styler : function(value, record, index) {
										return "background:pink";
									}
								},
								{
									field : 'kunnrErp',
									title : 'ERP�ͻ�����',
									//width : setColumnWidth(0.08),
									width : 80,
									hidden:true,
									align : 'center',
									styler : function(value, record, index) {
										return "background:pink";
									}
								},
								{
									field : 'name1',
									title : '������������',
									//width : setColumnWidth(0.24),
									width : 200,
									align : 'left',
									sortable:true
								},
								{
									field : 'orgId',
									title : '��֯ID',
									width : setColumnWidth(0.05),
									hidden:true,
									sortable:true
								},
								{
									field : 'orgName',
									title : '������֯',
									//width : setColumnWidth(0.08),
									width : 80,
									sortable:true
								},
								{
									field : 'name3',
									title : '����',
									width : setColumnWidth(0.05),
									hidden:true,
									sortable:true
								},
								{
									field : 'telNumber',
									title : '��˾�绰',
									//width : setColumnWidth(0.08),
									width : 80,
									sortable:true
								},
								{
									field : 'bukrs',
									title : '��˾����',
									//width : setColumnWidth(0.06),
									sortable:true,
									hidden : true
								},
								{
									field : 'customerType',
									//title : '�ͻ�����',
									title : '����������',
									//width : setColumnWidth(0.1),
									width : 100,
									sortable:true
								},
								{
									field : 'street1',
									title : '��˾��ַ',
									//width : setColumnWidth(0.26),
									width : 250,
									align : 'left',
									sortable:true
								},
								{
									field : 'businessManager',
									title : 'ҵ����',
									//width : setColumnWidth(0.06),
									align : 'center',
									hidden:true,
									sortable:true
								},
								{
									field : 'businessCompetent',
									title : 'ҵ������',
									//width : setColumnWidth(0.06),
									align : 'center',
									hidden:true,
									sortable:true
								},
								{
									field : 'kaManager',
									title : 'ҵ������',
									width : 100,
									align : 'center',
 									sortable:true
								},
								{
									field : 'status',
									title : '״̬',
									//width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 1) {
											return '�����͑�';
										} else if (value == 2) {
											return '<font color="red">����͑�</font>';
										}else if (value == 3) {
											return '<font color="red">�ѹػ�</font>';
										}
									},
									sortable:true
								},
								{
									field : 'isautodemand',
									title : '�Ƿ��Զ��ֻ�',
									//width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'Y') {
											return '��';
										} else if (value == 'N') {
											return '��';
										}else {
											return '��';
										}
									},
									sortable:true
								},
								{
									field : 'maximum',
									title : '����ͨ�г���',
									//width : setColumnWidth(0.07),
									align : 'center'
								},
								{
									field : 'billType',
									title : '�Ƿ��ܿ���ֵ˰��Ʊ',
									//width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'Y') {
											return '����';
										} else if (value == 'N') {
											return '������';
										}else {
											return '';
										}
									},
									sortable:true
								},
								{
									field : 'isMortgage',
									title : '�Ƿ��Ѻ',
									//width : setColumnWidth(0.07),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'Y') {
											return '��';
										} else if (value == 'N') {
											return '��';
										}else {
											return '';
										}
									},
									sortable:true
								},{
									field : 'operation',
									title : '����',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										var kunnr = row.kunnr; 
										return '<img style="cursor:pointer" onclick=autoDemand('
												+ kunnr
												+ ') title="�Ƿ�ֻ�" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>'
												+'<img style="cursor:pointer" onclick=getByRoleStation("'
												+ kunnr
												+'") title="ά����ɫ" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>'
												;
									}
								}
								] ],  
						pageSize : 20,		
						toolbar : [ "-", 
						{
							text : 'ͬ��',
							iconCls : 'icon-reload',
							handler : function() {
								getSapToKunnr();
							}
						}
//						,"-",{
//							text : '����',
//							iconCls : 'icon-add',
//							handler : function() {
//								createKunnr();
//							}
//						}
						]
					});

//	 ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 20,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
	
	//$('div.datagrid-toolbar a').eq(1).hide();
   // $('div.datagrid-toolbar div').eq(1).hide();
    //$('div.datagrid-toolbar a').eq(2).hide();
    //$('div.datagrid-toolbar div').eq(2).hide();
   // $('div.datagrid-toolbar a').eq(3).hide();
   // $('div.datagrid-toolbar div').eq(3).hide();

}

function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrId = $("#kunnr").val();
	queryParams.name1 = encodeURIComponent($("#name1").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.businessManager = encodeURIComponent($("#businessManager")
			.val());
	queryParams.businessCompetent = encodeURIComponent($("#businessCompetent")
			.val());
	queryParams.status = $("#status").combobox("getValue");
	queryParams.orgId=$("#orgId").val();
	if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	}else{
		queryParams.bhxjFlag='';
	}
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	//var width=1000;
	return $(this).width() * percent;
	//return width * percent;
}
/**
 * ��ɫά��
 * 
 * */
function getByRoleStation(id) {
   	initMaintRole('ά����ɫ','/roleAction!searchSelectedRole.jspa?stationId='
			+ (id)+'&kunnrSign=Y', 850, 450,100,50);
 
}
//�������ڶ���
function initMaintRole(title, url, WWidth, WHeight,left,top) {
	var url = wfeUrl+url;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : false,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true,
						left : left,
						top : top
					});

	$win.window('open');
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

function viewLicense(kunnr) {
	openWindow(false,"������֤�ղ鿴",
			"/kunnrAction!kunnrViewLicense.jspa?kunnrId=" + kunnr, 800, 480,100,100);
}

function viewInfo(kunnr) {
	openWindow(true,"��������Ϣ�鿴", "/kunnrAction!kunnrViewInfo.jspa?kunnrId=" + kunnr
			+ "&opera=view", 1120, 480,0,0);
}
function createKunnr(){
	openWindow(true,"�����̿�������", "/kunnrAction!kunnrApplyPre.jspa", 1120, 480,0,0);
}
function editInfo(kunnr) {
	openWindow(true,"��������Ϣ�޸�", "/kunnrAction!kunnrViewInfo.jspa?kunnrId=" + kunnr
			+ "&opera=edit", 1120, 480,0,0);
}

function freezeKunnr(kunnr) {
	openWindow(true,"�����̹ػ�����", "/kunnrAction!kunnrFreezeOrClosePre.jspa?kunnrId="
			+ kunnr + "&freezeOrClose=freeze", 600, 450,0,0);
}

function closeKunnr(kunnr) {
	openWindow(true,"�����̹ػ�����", "/kunnrAction!kunnrFreezeOrClosePre.jspa?kunnrId="
			+ kunnr + "&freezeOrClose=close", 600, 320,0,0);
}
function autoDemand(kunnr) {
	initMaterial("�Ƿ�ֻ�","/kunnrAction!kunnrAutoDemand.jspa?kunnrId="
			+ kunnr , 400, 350);
}

function initMaterial(title, url,WWidth,WHeight) {
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
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}

// �������ڶ���
function openWindow(ffit,title, url, WWidth, WHeight,l,t) {
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
// �رմ���
function closeWindow() {
	$("#hiddenWin").window('close');
}
//�̵�ģ������
function downloadExcelModel(){
	$.messager.confirm('Confirm', 'ȷ�����ؾ����̵���ģ��?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/stock/stockManageAction!downloadExcelModel.jspa?excelModel='+encodeURI("kunnrImport.xlsx");
			form.target = "hideFrame";
			form.submit();
		}
	});
}

// excel����
function excel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!kunnrExport.jspa';
	form.submit();

}

	/**
	 * ����֯��
	 */
	function selectOrgTree() {
		openWindow(false,'ѡ����֯', '/customerAction!orgTreePage.jspa?orgId='+$('#orgId').val(), 400, 460,100,50);
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
function getSapToKunnr(){
	$.messager.confirm('Confirm', 'ͬ��SAP�ľ�����������,Ԥ��ʱ������10����,ȷ���Ƿ�ͬ��?', function(r) {
		if (r) { 
			var form = window.document.forms[0];
			form.action = sapUrl+"/kunnrsAction!getKunnrListFromSAP.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}