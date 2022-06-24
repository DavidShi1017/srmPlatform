$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

var i = 1;
var orgIndex = 1;
var expTypeIndex = 1;
var outsideArr = new Array();
var budgetIndex = 1;
function loadGrid() {
	$('#modelId').val("fix_bx");
	$('#payee').combogrid(
					{
						panelWidth : 450,
						panelHight : 500,
						idField : 'payee',
						textField : 'payee',
						pagination : true,// �Ƿ��ҳ
						rownumbers : true,// ���
						collapsible : false,// �Ƿ���۵���
						fit : true,// �Զ���С
						method : 'post',
						editable : false,
						multiple : false,
						url : appUrl
								+ '/account/accountAction!getPayeeJsonList.jspa',
						columns : [ [ {
							field : 'payee',
							title : '�տλ',
							width : 400,
							align : 'center'
						} ] ],
						onLoadSuccess : function() {
							if ($('#num').val() > 0) {
								$('#payee').combogrid('setText',$('#defaultPayee').val());
								$('#payAccount').combobox({
													url : appUrl
															+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
															+ encodeURIComponent($(
																	'#defaultPayee')
																	.val()),
													valueField : 'id',
													textField : 'payAccount',
													editable : false,
													width : 160,
													onLoadSuccess : function() {
														$('#payAccount').combobox('setValue',$('#defaultPayeeId').val());
													}
												});
							}
						},
						onSelect : function(record) {
							var g = $('#payee').combogrid('grid'); // get				// object
							var r = g.datagrid('getSelected'); // get the
																// selected row
							$.ajax({
										type : 'post',
										url : appUrl
												+ '/account/accountAction!getDefaultPayAccount.jspa?payee='
												+ encodeURIComponent(r.payee),
										success : function(id) {
											$('#payAccount').combobox(
															{
																url : appUrl
																		+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
																		+ encodeURIComponent(r.payee),
																valueField : 'id',
																textField : 'payAccount',
																editable : false,
																width : 160,
																onLoadSuccess : function() {
																	$('#payAccount').combobox('setValue',id);
																}
															});
										}
									});

						},
						toolbar : '#toolbar_payee',
						required : true
					});
	
	/*$('#travleName').combogrid(
			{
				panelWidth : 450,
				panelHight : 500,
				idField : 'eventTitle',
				textField : 'eventTitle',
				pagination : false,// �Ƿ��ҳ
				rownumbers : true,// ���
				collapsible : false,// �Ƿ���۵���
				fit : true,// �Զ���С
				method : 'post',
				editable : false,
				multiple : false,
				url : appUrl
						+ '/account/accountAction!getTravleJsonList.jspa',
				columns : [ [ {
					field : 'eventId',
					title : '������',
					width : 100,
					align : 'center'
				},{
					field : 'eventTitle',
					title : '����',
					width : 200,
					align : 'center'
				},{
					field : 'money',
					title : '������',
					width : 100,
					align : 'center'
				}] ],
				onSelect : function(record) {
					var g = $('#travleName').combogrid('grid'); // get				// object
					var r = g.datagrid('getSelected'); // get the
					$("#applayMoney").val(r.money);
					$("#travel_id").val(r.eventId);
				}
			});*/
	addRow();
}

function searcher_payee(val) {
	val = encodeURIComponent(val);
	$('#payee').combogrid(
					{
						url : appUrl
								+ '/account/accountAction!getPayeeJsonList.jspa?searchStr='
								+ val
					});
	$('#payee').combogrid("grid").datagrid('reload');
}

function searcher_reference(val) {
	val = encodeURIComponent(val);
	$('#reference')
			.combogrid(
					{
						url : appUrl
								+ '/account/accountAction!getHisEventJsonList.jspa?searchStr='
								+ val
					});
	$('#reference').combogrid("grid").datagrid('reload');
}

/**
 * �����
 */
function addRow() {
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\">";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f2f2f2\">";

	var htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
			+ i
			+ "\" type=\"checkbox\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"14\" id=\"expDate_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"30\" id=\"memo_"
			+ i + "\" type=\"text\"/></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" id=\"expTypeName_"
			+ i
			+ "\"  readOnly/> </input><input size=\"9\" id=\"expType_"
			+ i
			+ "\" type=\"hidden\" /><input size=\"10\" type=\"button\"  onclick=\"getExpType("
			+ i
			+ ");\" value=\"ѡ��\"/></td>"
			+ "<td style=\"text-align: center\"><input size=\"15\" id=\"invoiceAmount_"
			+ i + "\" onblur=\"checkIsNumber(" + i
			+ ", 'amount');\" type=\"text\" /></td>"
		    + "</tr>";

	var htmlData = "";
	if (i % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addHandler();
	i++;
}

/**
 * �����Ԫ���¼�
 */
function addHandler() {
	$("#expDate_" + i).datebox({
		required : true,
		editable : false
	});

	$("#expType_" + i).validatebox({
		required : true
	});

	$("#invoiceAmount_" + i).validatebox({
		required : true
	});
	/*$("#budgetNumber_" + i).validatebox({
		required : true
	});
	$("#orgId_" + i).validatebox({
		required : true
	});*/
}
/**
 * ѡ���������
 * 
 * @param i
 */
function getExpType(i) {
	expTypeIndex = i;
	initMaintWindow('ѡ���������', '/account/accountAction!selectCostType.jspa',
			400, 460,50,50);
}
/*******************************************************************************
 * ѡ����֯
 * 
 * @param i
 */
function getOrg(i) {
	orgIndex = i;
	initMaintWindow('ѡ��ɱ�����', '/orgAction!orgTreePage.jspa?orgIdIn='
			+ $("#orgIdIn").val(), 400, 460,100,50);
}
/*******************************************************************************
 * �ı�������ͻ��߳ɱ�����ʱ
 */
function changeBudget(i) {
	$("#budgetNumber_" + i).val("");
	$("#budgetNumberBalance_" + i).val("");
}
/**
 * ɾ����
 */
function removeRow() {
	for ( var k = 1; k < i; k++) {
		if ($('#item_' + k).attr('checked') == 'checked') {
			$("#tr_" + k).remove();
			outsideArr.push(k);
		}
	}
	$('#item_all').attr('checked', false);
	countTotalMoney();
}

/**
 * ѡ��ȫ��
 * 
 * @param {}
 *            module
 */
function checkAll() {
	for ( var k = 1; k < i; k++) {
		if (!checkInOutsideArr(k)) {
			$('#item_' + k).attr('checked',
					($('#item_all').attr('checked') == 'checked'));
		}
	}
}

function payAccountReload() {
	$.ajax({
				type : 'post',
				url : appUrl
						+ '/account/accountAction!getDefaultPayAccount.jspa?payee='
						+ encodeURIComponent($('#payee').combogrid('getText')),
				success : function(id) {
					$('#payAccount')
							.combobox(
									{
										url : appUrl
												+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
												+ encodeURIComponent($('#payee')
														.combogrid('getText')),
										valueField : 'id',
										textField : 'payAccount',
										editable : false,
										onLoadSuccess : function() {
											$('#payAccount').combobox(
													'setValue', id);
										}
									});
				}
			});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

function createProEventReader() {
	initMaintWindow('ѡ����ϵ��', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}
/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindow('ѡ����֯', '/orgAction!orgTreePage.jspa?orgIdIn='
			+ $("#orgIdIn").val(), 400, 460,50,50);
}
/**
 * ��ȡ���ñ��
 * 
 * @param i
 */
function getBudgetNumber(i) {
	budgetIndex = i;
	if ($("#orgId_" + i).val() == "" || $("#expType_" + i).val() == "") {
		$.messager.alert('Tips', '����ѡ��������ͺͳɱ�����!', 'warning');
		return;
	}
	var orgId = $("#orgId_" + i).val();
	var expType = $("#expType_" + i).val();
	initMaintWindow('���ñ��',
			'/account/accountAction!bNumberSearchPrepare.jspa?orgId=' + orgId
					+ '&expType=' + expType, 800, 380,150,50);
}

function addNewAccount() {
	var payee = $('#payee').combobox('getText');
	if (payee == '') {
		$.messager.alert('Tips', '����ѡ���տ���!', 'warning');
		return;
	}
	initMaintWindow('������˺�',
			'/account/accountAction!toAddNewAccount.jspa?payee='
					+ encodeURIComponent(payee), 500, 400,200,100);
}

/**
 * ��֯���ķ���ֵ����
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	$("#orgId_" + orgIndex).val(selectedId);
	$("#orgName_" + orgIndex).val(selectedName);
	changeBudget(orgIndex);
}
/**
 * �������͵ķ���ֵ
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnCostTypeValue(selectedId, selectedName) {
	$("#expType_" + expTypeIndex).val(selectedId);
	$("#expTypeName_" + expTypeIndex).val(selectedName);
	changeBudget(expTypeIndex);

}
/**
 * ����Ԥ����
 * 
 * @param selectedId
 * @param selectedName
 * @param month
 */
function returnBudgetValue(selectedId, selectedName, bndetailId) {
	$("#budgetNumber_" + budgetIndex).val(selectedId);
	$("#budgetNumberBalance_" + budgetIndex).val(selectedName);
	$("#bndetailId_" + budgetIndex).val(bndetailId);

}

/**
 * ��������ҳ��ر�
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function closeMaintEvent() {
	closeMaintWindow();
}

/**
 * ������֯ҳ��ر�
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function closeOrgTree() {
	closeMaintWindow();
}

// �������ڶ���
function initMaintWindow(title, url, WWidth, WHeight,l,t) {
	var url = appUrl + url;
	var $win = $("#maintDiv")
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
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');
}

function closeMaintWindow() {
	$("#maintDiv").window('close');
}

/**
 * У����������
 * 
 * @param {}
 *            k
 * @param {}
 *            type
 */
function checkIsNumber(k, type) {
	var regAmount = /^\d+(.\d+)?$/;
	if ("amount" == type) {
		if (!regAmount.test($("#invoiceAmount_" + k).val())) {
			$("#invoiceAmount_" + k).val(0);
		}
		if ($("#invoiceAmount_" + k).val() * 1 > $("#budgetNumberBalance_" + k)
				.val() * 1) {
			$("#invoiceAmount_" + k).val(0);
			$.messager.alert('Tips', '��Ʊ���ܳ���Ԥ�������!', 'warning');
			return;
		}
		countTotalMoney();
	}
}

function countTotalMoney() {
	var total = 0;
	for ( var r = 1; r < i; r++) {
		if (!checkInOutsideArr(r)) {
			var value = $("#invoiceAmount_" + r).val();
			if (value.length > 0) {
				total += Number(value);
			}
		}
	}
	$("#totalMoney").val(RoundNumber(total, 2));
}

// ���� ָ��λ���� ��������
function RoundNumber(num, pos) {
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);
}

/**
 * У��Boolean���������Ƿ�ȫ��Ϊ��
 * 
 * @param {}
 *            arr
 * @return {}
 */
function checkBooleanArr(arr) {
	var flag = true;
	for ( var t = 0; t < arr.length; t++) {
		if (!arr[t]) {
			flag = false;
			break;
		}
	}
	return flag;
}

/**
 * ���index�Ƿ�����ɾ��������֮��
 * 
 * @param {}
 *            index
 */
function checkInOutsideArr(index) {
	var flag = false;
	for ( var a = 0; a < outsideArr.length; a++) {
		if (outsideArr[a] == index) {
			flag = true;
			break;
		}
	}
	return flag;
}

function submit() {
	var n = $("#title").validatebox('isValid');
	// var m = $('#costCenter').combogrid('isValid');
	var o = $('#memo').validatebox('isValid');
	var p = $('#payee').combogrid('isValid');

	var key = $('#modelId').val();

	var dateArr = new Array();
	var amountArr = new Array();
	var expTypeArr = new Array();

	var detailStr = "";

	for ( var r = 1; r < i; r++) {
		if (!checkInOutsideArr(r)) {
			dateArr.push($("#expDate_" + r).datebox('isValid'));
			amountArr.push($("#invoiceAmount_" + r).validatebox('isValid'));
			expTypeArr.push($("#expType_" + r).validatebox('isValid'));
		}
	}
	if (!(n && o && p && checkBooleanArr(dateArr) && checkBooleanArr(amountArr)
			&& checkBooleanArr(expTypeArr))) {
		return;
	}
	$("#pay_ee").val($("#payee").combogrid("getText"));
	$("#pay_account").val($("#payAccount").combobox("getText"));

	var x = 0;
	detailStr += "[";
	for ( var k = 1; k < i; k++) {
		if (!checkInOutsideArr(k)) {
			x++;
			detailStr += "{" 
				    + "\"cost_type\" : \"" + $("#expType_" + k).val()+ "\"," 
				    + "\"cost_type_content\" : \""+ $("#expTypeName_" + k).val() + "\","
					+ "\"cost_date\" : \""+ $("#expDate_" + k).datebox("getValue") + "\","
				    + "\"invoice_amount\" : \""+ $("#invoiceAmount_" + k).val() + "\","
					+ "\"cost_memo\" : \"" + $("#memo_" + k).val()
					+ "\"" + "},";
		}
	}
	if (x > 0) {
		detailStr = detailStr.substring(0, detailStr.length - 1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);

	$.messager.progress();
	$
			.ajax({
				type : "post",
				url : appUrl
						+ "/account/accountAction!selectNexUser.jspa?time="
						+ new Date(),
				data : {
					key : key,
					userId : $("#curUserId").val(),
					auditMoney : $("#totalMoney").val(),
					flag : $("#flag").val()
				},
				success : function(userUtil) {
					$.messager.progress('close');
					if (userUtil == null || userUtil == "") {
						$.messager.alert('Tips', "û���¸������ˣ���ά����", 'error');
						return;
					}
					if (userUtil != null && userUtil.processInstanceId != '-2'
							&& userUtil.processInstanceId != undefined) {
						var nextUser1 = "";
						var total = 0;
						$.each(userUtil.result, function(i, v) {
							total = i + 1;
							nextUser1 = v.userId;
						});
						if (total == 1) {
							$("#nextUserId").val(nextUser1);
							var form = window.document.forms[0];
							form.action = appUrl
									+ "/account/accountAction!processWorkflowFix.jspa?eventId="
									+ userUtil.processInstanceId;
							form.submit();
						} else if (total == 0) {
							$.messager.alert('Tips', "û��ά���¸������ˣ�����ϵ����Ա",
							'error');
							return;
						} else {
							if (userUtil.processInstanceId == "-1") {
								$.messager.alert('Tips', "û��ά���¸������ˣ�����ϵ����Ա",
										'error');
								return;
							}
							var positionHtml = "<div class='easyui-panel' title='�¸�����' data-options='collapsible:true'>"
									+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
									+ "<tr><td class='head' noWrap>������</td>"
									+ "<td><select id='nextUserId1' name='nextUserId1'>";
							$.each(userUtil.result, function(i, v) {
								positionHtml += "<option value='" + v.userId
										+ "'>" + v.userName + "----"
										+ v.stationName + "</option>";
							});
							positionHtml += "</select></td></tr></table></div>";
							if ($('#nextUserDialog').length < 1) {
								$(
										'<div/>',
										{
											id : 'nextUserDialog',
											title : 'ѡ���¸�������',
											html : "<div id='nextUser'>"
													+ positionHtml + "</div>"
													+ "</div>"
										}).appendTo('body');
							} else {
								$('#nextUser').html(positionHtml);
							}
							$('#nextUserDialog')
									.dialog(
											{
												modal : true,
												resizable : false,
												dragable : false,
												closable : false,
												open : function() {
													$('#nextUserDialog').css(
															'padding', '0.4em');
													$(
															'#nextUserDialog .ui-accordion-content')
															.css('padding',
																	'0.4em')
															.height(
																	$(
																			'#nextUserDialog')
																			.height() - 75);
												},
												buttons : [
														{
															text : 'ȷ��',
															handler : function() {
																if ($(
																		"#nextUserId1")
																		.val() == ""
																		|| $(
																				"#nextUserId1")
																				.val() == null) {
																	$.messager
																			.alert(
																					'Tips',
																					"û���¸������ˣ���ά����",
																					'error');
																	return;
																}
																$.messager
																		.progress();
																$("#nextUserId")
																		.val(
																				$(
																						"#nextUserId1")
																						.val());
																var form = window.document.forms[0];
																form.action = appUrl
																		+ "/account/accountAction!processWorkflowFix.jspa?eventId="
																		+ userUtil.processInstanceId;
																form.submit();
															}
														},
														{
															text : 'ȡ��',
															handler : function() {
																$(
																		'#nextUserDialog')
																		.dialog(
																				'close');
															}
														} ],
												width : document.documentElement.clientWidth * 0.50,
												height : document.documentElement.clientHeight * 0.40
											});
						}
					} else {
						$.messager.alert('Tips', "���̳�������ϵ����Ա",
						'error');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
}