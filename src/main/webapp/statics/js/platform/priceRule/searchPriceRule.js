$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
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
						+ '/priceRule/priceRuleAction!getPriceRuleList.jspa',
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
									width : 80,
									hidden : true,
									align : 'center'
								},
//								{
//									title : "Status",
//									field : 'state',
//									width : 80,
//									align : 'center',
//									formatter : function(value, row, rec) {
//										var flag = row.state;
//										if (flag == '9') {
//											return "<font color='red'>Deleted</font>";
//										}else if (flag == '0') {
//											return "<font color='black'>Pendding</font>";
//										} else{
//											return flag;
//										} 
//									}
//								},
								{
									title : "Price Type",
									field : 'price_type',
									width : 65,
									align : 'center',
								},
								{
									title : "12NC",
									field : 'material_id',
									width : 110,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.material_id;
										if (flag == ''||flag==undefined) {
											return "";
										} else{
											var str = $.trim(flag);
											str = str.substring(str.length-12, str.length);
											return str;
										} 
									}
								},
								{
									title : "Book Part",
									field : 'material_desc',
									width : 150,
									align : 'left'
								},
//								{
//									title : "Sales Org",
//									field : 'org_id',
//									width : 110,
//									align : 'center'
//								},
								{
									title : "Sales Office",
									field : 'office_id',
									width : 80,
									align : 'center'
								},
								{
									title : "PerUnit",
									field : 'perUnit',
									width : 70,
									align : 'center',
								},
								{
									title : "Currency",
									field : 'currency_code',
									width : 70,
									align : 'center'
								},
								{
									title : "Price",
									field : 'sale_price',
									width : 80,
									align : 'center'
								},
								{
									title : "Start Date",
									field : 'start_dateStr',
									width : 100,
									align : 'center'
								},
								{
									title : "End Date",
									field : 'end_dateStr',
									width : 100,
									align : 'center'
								},
								{
									title : "ForCustomer",
									field : 'customer_code',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.customer_code;
										if (flag == ''||flag==undefined) {
											return "";
										} else{
											var str = $.trim(flag);
											str = str.substring(str.length-6, str.length);
											return str;
										} 
									}
								},
								{
									title : "Customer Name",
									field : 'customer_name',
									width : 180,
									align : 'center',
								},


								
//								{
//									title : "Modify User",
//									field : 'latest_userId',
//									width : 100,
//									align : 'center',
//								},
//								{
//									title : "Modify Time",
//									field : 'latest_time',
//									width : 100,
//									align : 'center',
//									formatter : function(value, row, rec) {
//										if(row.latest_time==undefined){
//											return "";
//										}else{
//											return utcToDate(row.latest_time);
//											
//										}
//									}
//									
//								}
								] ],
						toolbar : [  "-", {
							text : 'Add Pricerule',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}
//						, "-", {
//							text : 'Delete',
//							iconCls : 'icon-remove',
//							handler : function() {
//								dele();
//							}
//						}
						, "-", {
							text : 'Edit Pricerule',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}, "-", {
							text : 'View',
							iconCls : 'icon-view ',
							handler : function() {
								check();
							}
						}, "-" , {
							text : 'Sync',
							iconCls : 'icon-remove',
							handler : function() {
								sync();
							}
						}],
						onDblClickRow:function(rowIndex,rowData){
							initMaintAccount(false,'800','200','Detail Information', '/priceRuleAction!toViewPriceRule.jspa?id='+rowData.id,100,100);
						},
						//onLoadSuccess:'fixRownumber'
					});
	// ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
}

//�۸�����
/*$('#price_type').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=550',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});*/

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.material_id = encodeURIComponent($("#material_id").val());
	queryParams.material_name = encodeURIComponent($("#material_name").val());
	queryParams.price_type = encodeURIComponent($("#price_type").val());
	queryParams.customer_name = encodeURIComponent($("#customer_name").val());
//	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}
function sync(){
	initMaintAccount(false,'400','180','Sync', '/priceRuleAction!toSyncPrice.jspa',100,100);	
}
function add(){
	initMaintAccount(false,'800','350','Add Pricerule', '/priceRuleAction!toCreatePriceRule.jspa',100,100);	
}
function edit(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate��', 'warning');
		return;
	} else {
		initMaintAccount(false,'800','350','Modify PriceRule', '/priceRuleAction!toUpdatePriceRule.jspa?id='+rows[0].id,100,100);	
	}
}
function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate��', 'warning');
		return;
	} else {
		initMaintAccount(false,'800','200','Detail Information', '/priceRuleAction!toViewPriceRule.jspa?id='+rows[0].id,100,100);
	}	
}

function importExcel(){
	initMaintAccount(false,'700','350','Import Price Rule', '/priceRuleAction!importExcel.jspa',100,100);
}
//ģ������
function downloadExcelModel(){
		$.messager.confirm('Tip', 'Sure to download the template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("priceRule.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}
/**
 * ɾ��
 */
function dele() {
	$.messager.confirm('Confirm', 'Sure to delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', 'No data selected!');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/priceRuleAction!deletePriceRule.jspa?id='+rows[0].id+'&state=-1';
			form.target = "hideFrame";
			form.submit();
		}
	});

}

function initMaintAccount(ffit,widte,height,title,url,l,t) {
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
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');

}


// �رմ���ҳ��
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
	date = str[5] + "-";
//	date = date + month[str[1]] + "-" + str[2] + " " + str[3];//��ʱ����
	date = date + month[str[1]] + "-" + str[2];//������
	return date;
}	
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
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
$.extend($.fn.datagrid.methods, {  
    fixRownumber : function (jq) {  
        return jq.each(function () {  
            var panel = $(this).datagrid("getPanel");  
            var clone = $(".datagrid-cell-rownumber", panel).last().clone();  
            clone.css({  
                "position" : "absolute",  
                left : -1000  
            }).appendTo("body");  
            var width = clone.width("auto").width();  
            if (width > 25) {  
                //���5������,����һ��߾�  
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);  
                $(this).datagrid("resize");  
                //һЩ������  
                clone.remove();  
                clone = null;  
            } else {  
                //��ԭ��Ĭ��״̬  
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");  
            }  
        });  
    }  
});  