jQuery.support.cors = true;
var golbalOperation;// ȫ�ֱ��� �����������
var actionId = $("#actionId").val();
var eventId = $("#eventId").val();
var subFolders = $("#subFolders").val();
var modelId = $("#modelId").val();
var operationType = $("#operationType").val();
var flag = "";
var userUtil;
$(document).ready(function() {
	$('#hideFrameWfe').bind('load', promgtMsg);
});
function iframeResize() {
	var iframe = document.getElementById("iframe2");
	try {
		var bHeight = iframe.contentWindow.window.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.window.document.documentElement.scrollHeight;
		iframe.height = Math.max(bHeight, dHeight);
	} catch (e) {
		iframe.height = "350px";
	}
	window.setTimeout("iframeResize()", 200);
}

function chooseFlags(flag) {
	if (flag == "M") {
		var showflag = document.getElementById("showflag");
		$(showflag).show();
	} else {
		var showflag = document.getElementById("showflag");
		$(showflag).hide();

	}
}

function refuse() {
	sub('N');
}

function agree() {
	if (modelId.substr(0, 3) == "any") {
		if ($("#chooseFlag").combobox("getValue") == "M"
				&& $("#nextUserName").val() == "") {
			$.messager.alert('Tips', '��ѡ����һ������!');
			return;
		}
	}

	sub('Y');
}

function sub(operation) {
	var title = "";
	if (operation == 'Y') {
		title = "�Ƿ�ͬ�������";
	} else {
		title = "�Ƿ�ܾ�������";
	}
	$.messager.confirm('Confirm',title,function(r) {
						if (r) {
							golbalOperation = operation;
							var fix_key = modelId.substr(0, 3);
							$.messager.progress();
							var content = $("#memo").val();
							if (fix_key == "fix") {
								$.ajax({    type : "post",
											url : appUrl
													+ "/wfe/eventAction!selectProcessNexUser.jspa?time="
													+ new Date(),
											data : {
												memo : encodeURIComponent(content),
												operation : operation,
												eventId : $("#eventId").val(),
												keys : $("#keys").val(),
												toDoDetail : $("#toDoDetail")
														.val(),
												modelId : $("#modelId").val(),
												curStaId : $("#curStaId").val()
											},
											success : function(userUtil1) {
											   userUtil = userUtil1;
											   if (userUtil == null || userUtil == "" || userUtil.processInstanceId == undefined) {
													$.messager.progress('close');
													$.messager.alert('Tips',"���̳�����ά����",'error');
													return;
												}else if (userUtil.processInstanceId == "-1") {
													$.messager.progress('close');
													$.messager.alert('Tips',"���̳�����ά����",'error');
													return;
												}
												else if (userUtil != null&& userUtil.processInstanceId == "success") { // ���һ��������ʱ����
													if (userUtil.executeAction != null&& userUtil.executeAction != "") {
														flag = "1";
														$.ajax({    type : "get",
																	url : userUtil.executeAction,
																	data : {
																		eventId : eventId,
																		subFolders : subFolders,
																		operation : operation
																	},
																	dataType : 'jsonp',
																	jsonpCallback : "callback"
																});
													} else {
														var form = window.document.forms[0];
														form.action = appUrl
																+ "/wfe/eventAction!uploadAttachments.jspa";
														form.target = "hideFrameWfe";
														form.submit();
													}
												} else if (userUtil != null&& userUtil.processInstanceId == "last") {
													if (userUtil.executeAction != null&& userUtil.executeAction != "") {
														flag = "2";
														$.ajax({    type : "get",
																	url : userUtil.executeAction,
																	data : {
																		eventId : eventId,
																		subFolders : subFolders,
																		operation : operation
																	},
																	dataType : 'jsonp',
																	jsonpCallback : "callback"
																});
													} else {
														$.messager.progress('close');
														$.messager.alert("����ɹ���",	'info',function() {
															window.parent.search();
															window.parent.closeMaintWindow();
														});
													}
												}else if ($("#curStaId").val() == "start" && userUtil != null 
													&& userUtil.executeAction != null&& userUtil.executeAction != "") {
														flag = "4";
														$.ajax({    type : "get",
																	url : userUtil.executeAction,
																	data : {
																		eventId : eventId,
																		subFolders : subFolders,
																		operation : operation
																	},
																	dataType : 'jsonp',
																	jsonpCallback : "callback"
																});
												} else{
													contineProcess();
												}
											},
											error : function() {
												$.messager.progress('close');
												$.messager.alert('Tips',
														"ϵͳ����", 'error');
											}
										});
								
							} else if (fix_key == "sem") {
										$.ajax({
											type : "post",
											url : appUrl
													+ "/wfe/eventAction!selectSemUser.jspa?time="
													+ new Date(),
											data : {
												eventId : $("#eventId").val()
											},
											success : function(userUtil) {
												if (userUtil != null&& userUtil.processInstanceId == "success") { // ���һ��������ʱ����
													if (userUtil.executeAction != null&& userUtil.executeAction != "") {
														flag = "3";
														$.ajax({
																	type : "get",
																	url : userUtil.executeAction,
																	data : {
																		eventId : eventId,
																		subFolders : subFolders,
																		operation : operation
																	},
																	dataType : 'jsonp',
																	jsonpCallback : "callback"
																});
													} else {
														var form = window.document.forms[0];
														form.action = appUrl
																+ "/wfe/eventAction!processEvent.jspa?operation="
																+ operation;
														form.target = "hideFrameWfe";
														form.submit();
													}
												}
											},
											error : function() {
												$.messager.progress('close');
												$.messager.alert('Tips',"ϵͳ����", 'error');
											}
										});

							} else {
									var form = window.document.forms[0];
									form.action = appUrl
											+ "/wfe/eventAction!processEvent.jspa?operation="
											+ operation;
									form.target = "hideFrameWfe";
									form.submit();
							}
						}
					});
}

function back() {
	initWindow('ѡ���ɫ', appUrl
			+ '/wfe/eventAction!toSearchStationId.jspa?eventId='
			+ $("#eventId").val(), 'maintWindow', 370, 400);
}

function contineProcess(){
	var total = 0;
	var nextUserId = "";
	$.each(userUtil.result,function(i,	v) {
		total = i+1;
		nextUserId = v.userId;
	});
	if(total == 1){
		var form = window.document.forms[0];
		form.action = appUrl+ "/wfe/eventAction!processCommit.jspa?nextUserId="+ nextUserId;
		form.target = "hideFrameWfe";
		form.submit();
	}else{
		$.messager.progress('close');
		var positionHtml = "<div class='easyui-panel' title='�¸�����' data-options='collapsible:true'>"
				+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
				+ "<tr><td class='head' noWrap>������</td>"
				+ "<td><select id='nextUserId' name='nextUserId'>";
		$.each(userUtil.result,function(i,	v) {
			positionHtml += "<option value='"
						 + v.userId
						 + "'>"
						 + v.userName
						 + "----"
						 + v.stationName
						 + "</option>";
		});
		positionHtml += "</select></td></tr></table></div>";
		if ($('#nextUserDialog').length < 1) {
			$('<div/>',{
						id : 'nextUserDialog',
						title : 'ѡ���¸�������',
						html : "<div id='nextUser'>"
								+ positionHtml
								+ "</div>"
								+ "</div>"
					}).appendTo('body');
		} else {
			$('#nextUser').html(positionHtml);
		}
		$('#nextUserDialog').dialog(
						{
							modal : true,
							resizable : false,
							dragable : false,
							closable : false,
							open : function() {
								$('#nextUserDialog').css('padding','0.4em');
								$('#nextUserDialog .ui-accordion-content').css('padding','0.4em').height(
								$('#nextUserDialog').height() - 75);
							},
							buttons : [{
										text : 'ȷ��',
										handler : function() {
											if ($("#nextUserId").val() == ""|| $("#nextUserId").val() == null) {
												$.messager.alert('Tips',"û���¸������ˣ���ά����",'error');
												return;
											}
											$.messager.progress();
											var form = window.document.forms[0];
											form.action = appUrl+ "/wfe/eventAction!processCommit.jspa?nextUserId="+ $("#nextUserId").val();
											form.target = "hideFrameWfe";
											form.submit();
										}
									},
									{
										text : 'ȡ��',
										handler : function() {
											$('#nextUserDialog').dialog('close');
											//self.location.reload();
										}
									} ],
									width : document.documentElement.clientWidth * 0.50,
									height : document.documentElement.clientHeight * 0.40
		});
	}

}

/*
 * function cencel() { window.parent.closeMaintWindow(); }
 */

function promgtMsg() {
	$.messager.progress('close');
	var hideFrameWfe = document.getElementById("hideFrameWfe");
	var failResult = hideFrameWfe.contentWindow.failResult;
	var successResult = hideFrameWfe.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#nextUserDialog').dialog('close');
			window.parent.search();
			window.parent.closeMaintWindow();
		});
	}
}
/*******************************************************************************
 * �ɹ�����ת������ҳ��
 */
function successRe() {
	if ($("#operationType").val() == "process") {
		var form = document.getElementById("processed");
		form.action = appUrl + "/wfe/eventAction!toSearchProcessEvent.jspa ";
		form.target = "_self";
		form.submit();
	} else {
		history.go(-1);
	}

}

function openOrgtree() {
	initWindow('ѡ����ϵ��', appUrl + '/allUserAction!toShowUserByOrgId.jspa',
			'maintWindow', 660, 430);
}

function linkMan() {
	initWindow('������ϵ��', appUrl + '/wfe/eventAction!searchLinkMan.jspa',
			'maintWindow', 340, 430);
}

/**
 * ������ϵ��
 * 
 * @param {}
 *            x
 */
function saveUser(x) {
	$("#nextUserId").val(x[0]);
	$("#nextUserName").val(x[1]);
	$("#nextOrgId").val(x[2]);
	closeMaintEvent();
}

function searchAllUserInfo(userId) {
	initWindow("�鿴��ϵ��", appUrl
			+ "/alluser/allUserAction!searchAllUserInfo.jspa?userId=" + userId,
			"maintWindow", 460, 450);
}
function initWindow(title, url, id, WWidth, WHeight) {
	var $win = $("#" + id)
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

/**
 * �رյ�������
 */
function closeMaintEvent() {
	$("#maintWindow").window('close');
}
function shortcuts(userName, userId, orgId) {
	document.getElementById("nextUserName").value = userName;
	document.getElementById("nextUserId").value = userId;
	document.getElementById("nextOrgId").value = orgId;
}
function closeStation(){
	$("#maintWindow").window('close');
	window.parent.search();
	window.parent.closeMaintWindow();
}
function callback(executeResult) {
	var res = executeResult.result;
	var msg = executeResult.code;
	if (flag == "1") {
		if (res == "true") {
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/eventAction!completeEndTask.jspa?operation="
					+ golbalOperation + "&successResult="
					+ encodeURIComponent(msg);
			form.target = "hideFrameWfe";
			form.submit();
		} else {
			if(msg != null){
				$.messager.alert('Tips', msg, 'error', function() {
					$.messager.progress('close');
				});
			}else{
				$.messager.alert('Tips', "�������", 'error', function() {
					$.messager.progress('close');
				});
			}
			
		}
	} else if (flag == "2") {
		$.messager.alert('Tips', "����ɹ���", 'info', function() {
			window.parent.search();
			window.parent.closeMaintWindow();
		});
	} else if (flag == "3"){
		if (res == "true") {
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/eventAction!processEvent.jspa?operation="
					+ golbalOperation;
			form.target = "hideFrameWfe";
			form.submit();
		} else {
			$.messager.alert('Tips', "�������", 'error', function() {
				$.messager.progress('close');
			});
		}

	}else if (flag == "4"){
		if (res == "true") {
			contineProcess();
		} else {
			if(msg != null){
				$.messager.alert('Tips', msg, 'error', function() {
					$.messager.progress('close');
				});
			}else{
				$.messager.alert('Tips', "�������", 'error', function() {
					$.messager.progress('close');
				});
			}
		}

	}else if (flag == "5"){
		if (res == "true") {
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/eventAction!processEvent.jspa?operation="
					+ operation;
			form.target = "hideFrameWfe";
			form.submit();
		} else {
			if(msg != null){
				$.messager.alert('Tips', msg, 'error', function() {
					$.messager.progress('close');
				});
			}else{
				$.messager.alert('Tips', "�������", 'error', function() {
					$.messager.progress('close');
				});
			}
		}

	}

}
function downLoad(fileId) {
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!downLoadFile.jspa?fileId="
			+ fileId;
	form.target = "hideFrameWfe";
	form.submit();
}
function getCkeditor() {
	if (!CKEDITOR.instances.memo) { // �ж�memo�Ƿ����
		editor = CKEDITOR.replace("memo");
	} else {
		addCkeditor("memo");
	}
}
function addCkeditor(id) {
	var editor2 = CKEDITOR.instances[id];

	if (editor2)
		editor2.destroy(true);// ���ٱ༭�� memo,Ȼ������һ��

	editor = CKEDITOR.replace(id);
}
function iFrameHeight() {
	var ifm = document.getElementById("iframepage");
	var subWeb = document.frames ? document.frames["iframepage"].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight + 130;
	}
}
function cc(){
	initWindow('ѡ������', appUrl+'/wfe/eventAction!toSemiAutomatic.jspa?eventId='+$("#eventId").val()
			+"&toDoDetail="+$("#toDoDetail").val(),"maintWindow",750, 450);
}
function saveMemo(){
	var form = window.document.forms[0];
	form.action = appUrl
			+ "/wfe/eventAction!updateCc.jspa";
	form.target = "hideFrameWfe";
	form.submit();
}
function getRepaly(id){
	initWindow('�鿴�ظ���Ϣ', appUrl+'/wfe/eventAction!toRepalyMemo.jspa?eventId='+$("#eventId").val()
			+"&toDoDetail="+id,"maintWindow",750, 450);
}