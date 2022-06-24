$(document).ready(function() {
	loadGrid(); // Ȩ�޵!ѯ
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl
						+ '/endCustomer/endCustomerAction!getECAliasListJson.jspa?ec_id='+$('#ec_id').val(),
				        loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,

						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : "ID",
									field : 'id',
									width : 130,
									hidden : true,
									align : 'center'
								},
								{
									title : "Customer ID",
									field : 'ec_id',
									width : 130,
									align : 'center',
									hidden:true
								}, {
									title : "Customer Name",
									field : 'ec_name',
									width : 130,
									align : 'center'
								}, {
									title : "Customer Group",
									field : 'ec_group',
									width : 130,
									align : 'center',
									hidden:true
								}, {
									title : "Customer Group",
									field : 'ec_groupName',
									width : 130,
									align : 'center'
								}, {
									title : "Customer City",
									field : 'ec_city',
									width : 130,
									align : 'center'
								}, {
									title : "Alias Name",
									field : 'ec_alias_name',
									width : 130,
									align : 'center'
								}, {
									title : "Alias City",
									field : 'alias_city',
									width : 130,
									align : 'center'
								} ] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'Delete',
							iconCls : 'icon-remove',
							handler : function() {
								dele();
							}
						}
/*						, "-", {
							text : 'Edit',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}
						, "-", {
							text : 'View',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						}*/
						],
						onDblClickRow : function(rowIndex, rowData) {
							initMaintAccount(false, '500','400', 'Detail Information','/endCustomerAction!toViewECAlias.jspa?id='
											+ rowData.id,20,20);
						},
						onSelect : function(rowIndex, rowData) {

						},
						onLoadSuccess:function(data){
							var loginRole = $('#loginRole').val().split("*");
							for(var i=0;i<loginRole.length;i++){
								var role = loginRole[i];
								if(role=="JXS"){
									$(".datagrid-toolbar").hide();
									break;
								}
							}
						}
					});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	//queryParams.country_code = $("#country_code").val();
	$("#datagrid").datagrid('load');
}

function add() {
	initMaintAccount(false, '500','400', 'Create ECAlias',
			'/endCustomerAction!toCreateECAlias.jspa?ec_id='+$('#ec_id').val()+
			'&ec_name='+encodeURIComponent($('#ec_name').val())
			+'&ec_group='+encodeURIComponent($('#ec_group').val())
			+'&ec_city='+encodeURIComponent($('#ec_city').val()),20,20);
}

function edit() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '500','400',  'Edit Country',
				'/endCustomerAction!toUpdateECAlias.jspa?id=' + rows[0].id,20,20);
	}
}

function check() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '500','400', 'Detail Information',
				'/endCustomerAction!toViewECAlias.jspa?id=' + rows[0].id,20,20);
	}
}

/**
 * ɾ!
 */
function dele() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('info', 'Please select the data item!');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about  delete?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/endCustomerAction!delECAlias.jspa?id='
					+ rows[0].id;
			form.target = "hideFrame";
			form.submit();
		}
	});

}

function initMaintAccount(ffit, widte, height, title, url, l, t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit : FFit,
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');

}

// �رմ!�ҳ!
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	if(event.keyCode == 8) {
	     // !!!�textarea�ڲ�ִ!�κβ!�
	        if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // !!�readOnly!!disable!ִ!�κβ!�
	if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
