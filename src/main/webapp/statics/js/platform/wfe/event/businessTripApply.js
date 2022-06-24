$(document).ready(function() {
	$("#key").val("fix_travel");
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid(){
	$('#beginDate').datebox({
			required : true,
			editable : false,
			onSelect: function(date){
				var v = $("#endDate").datebox("getValue");
				if(v != ""){
					var day = DateDiff($("#beginDate").datebox("getValue"),v);
					$("#tripDays").val(day+1);
					$("#stayDays").val(day);
				}
			}
	});
	$('#endDate').datebox({
			required : true,
			editable : false,
			onSelect: function(date){
				var v = $("#beginDate").datebox("getValue");
				if(v != ""){
					var day = DateDiff(v,$("#endDate").datebox("getValue"));
					$("#tripDays").val(day+1);
					$("#stayDays").val(day);
				}
			}
	});
	
	$('#departure').combobox(
			{
				textField : 'text',
				valueField : 'text',
				onChange : function(newValue, oldValue) {
					if (newValue != null) {
						var urlStr = appUrl
								+ "/account/accountAction!searchRegion.jspa?level=2&text="
								+ encodeURIComponent(newValue);
						$("#departure").combobox("reload", urlStr);
						
					}
				}
			});
	
	$('#place').combobox(
			{
				textField : 'text',
				valueField : 'text',
				onChange : function(newValue, oldValue) {
					if (newValue != null) {
						var urlStr = appUrl
								+ "/account/accountAction!searchRegion.jspa?level=2&text="
								+ encodeURIComponent(newValue);
						$("#place").combobox("reload", urlStr);
						
					}
				}
			});
	
	
	/*$.ajax({
		type : "post",
		url : appUrl + '/wfe/eventAction!getTripWayJsonList.jspa',
		success : function(tripWayList) {
			$.each(stringResultList, function(i, v) {
				var obj = document.createElement('input');
				obj.type="checkbox";
				obj.value = v.value;
				obj.text = v.name;
				document.getElementById("ck").appendChild(obj);
			});
		}
	});*/
	
	/*$('#tripWay').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'value',
		textField : 'name',
		pagination : true,// �Ƿ��ҳ
		rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		fit : true,// �Զ���С
		method : 'post',
		multiple : false,
		editable : false,
		url : appUrl + '/wfe/eventAction!getTripWayJsonList.jspa',
		columns : [ [ {
			field : 'value',
			title : 'ֵ',
			width : 150,
			align : 'center'
		}, {
			field : 'name',
			title : '����',
			width : 250,
			align : 'center'
		} ] ],
		toolbar : '#toolbar1',
		required: true
	});*/
}

function submit() {
	var d = $('#departure').validatebox('isValid');
	var p = $('#place').validatebox('isValid');
	var a = $('#amount').numberbox('isValid');
    var t = $('#title').numberbox('isValid');
	if(!(t && $('#beginDate').datebox('isValid') && $('#endDate').datebox('isValid') && d && p && a)) {
		return;
	}
	var str=document.getElementsByName("businessTripApply.tripWay");
	var sp = document.getElementsByTagName('lable');
	var objarray=str.length;
	var chestr="";
	for (var i=0;i<objarray;i++)
	{
	  if(str[i].checked == true && str[i].type == 'checkbox')
	  {
		  chestr+=sp[i].innerHTML+",";
	  }
	}
	if(chestr == ""){
		$.messager.alert('Tips', "��ѡ����з�ʽ!", 'warning');
		return;
	}
	$("#tripWayName").val(chestr);
	$.messager.progress();
	$.ajax({
				type : "post",
				url : appUrl
						+ "/wfe/eventAction!selectNexUser.jspa?time="
						+ new Date(),
				data : {
					userId : $("#curUserId").val(),
					key : $("#key").val()
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
									+ "/wfe/eventAction!processTripApplyWorkflowFix.jspa?eventId="
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
													$('#nextUserDialog').css('padding', '0.4em');
													$('#nextUserDialog .ui-accordion-content').css('padding','0.4em').height($('#nextUserDialog').height() - 75);
												},
												buttons : [
														{
															text : 'ȷ��',
															handler : function() {
																if ($("#nextUserId1").val() == ""|| $("#nextUserId1").val() == null) {
																	$.messager.alert('Tips',"û���¸������ˣ���ά����",'error');
																	return;
																}
																$.messager.progress();
																$("#nextUserId").val($("#nextUserId1").val());
																var form = window.document.forms[0];
																form.action = appUrl
																        + "/kunnr/kunnrCostAction!processWorkflowFix.jspa?eventId="
																		+ userUtil.processInstanceId;
																form.submit();
															}
														},
														{
															text : 'ȡ��',
															handler : function() {
																$('#nextUserDialog').dialog('close');
															}
														} ],
												width : document.documentElement.clientWidth * 0.50,
												height : document.documentElement.clientHeight * 0.40
											});
						}
					} else {
						$.messager.alert('Tips', "���̳�������ϵ����Ա", 'error');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

}

function close() {
	window.parent.closeMaintEvent();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.search();
			window.parent.closeMainFrame();
		});
	}
}
function  DateDiff(sDate1,  sDate2){    //sDate1��sDate2��2006-12-18��ʽ  
    var  aDate,  oDate1,  oDate2,  iDays ; 
    aDate  =  sDate1.split("-")  ;
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);  //ת��Ϊ12-18-2006��ʽ  
    aDate  =  sDate2.split("-");
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24); //�����ĺ�����ת��Ϊ����  
    return  iDays;  
} 
