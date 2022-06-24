$(document).ready(function() {
	//loadGrid();
});

var columns1=[[
               	{field:'BZIRK_TXT',title:'����',width:100,align:'center'},
               	{field:'CRUSR',title:'�ᱨ��',width:100,align:'center'},
               	{field:'CRDAT',title:'�ᱨʱ��',width:130,align:'center'},
               	{field:'REASON',title:'ԭ��',width:300,align:'center'},
               	{field : 'operation',title : '����',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						var id = encodeURIComponent(row.REAID);
						return '<img style="cursor:pointer" onclick=showDiff('
						+ id
						+ ') title="�鿴��ϸ" src='
						+ imgUrl
						+ '/images/actions/action_edit.png align="absMiddle"></img>'
						;
					}
				} 
               ]];

var columns2=[[
               	{field:'BZIRK_TXT',title:'����',width:100,align:'center'},
          		{field:'VKBUR_TXT',title:'�г���',width:100,align:'center'},
          		{field:'CRUSR',title:'�ᱨ��',width:100,align:'center'},
               	{field:'CRDAT',title:'�ᱨʱ��',width:130,align:'center'},
               	{field:'REASON',title:'ԭ��',width:300,align:'center'},
               	{field : 'operation',title : '����',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						var id = encodeURIComponent(row.REAID);
						return '<img style="cursor:pointer" onclick=showDiff('
						+ id
						+ ') title="�鿴��ϸ" src='
						+ imgUrl
						+ '/images/actions/action_edit.png align="absMiddle"></img>'
						;
					}
				} 
               ]];
var columns3=[[
           		{field:'BZIRK_TXT',title:'����',width:100,align:'center'},
           		{field:'VKBUR_TXT',title:'�г���',width:100,align:'center'},
           		{field:'VKGRP_TXT',title:'Ƭ��',width:80,align:'center'},
           		{field:'CRUSR',title:'�ᱨ��',width:100,align:'center'},
               	{field:'CRDAT',title:'�ᱨʱ��',width:130,align:'center'},
               	{field:'REASON',title:'ԭ��',width:300,align:'center'},
               	{field : 'operation',title : '����',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						var id = encodeURIComponent(row.REAID);
						return '<img style="cursor:pointer" onclick=showDiff('
						+ id
						+ ') title="�鿴��ϸ" src='
						+ imgUrl
						+ '/images/actions/action_edit.png align="absMiddle"></img>'
						;
					}
				} 
               ]];

function loadGrid(){
	var vkgrp=$('#vkgrp').combogrid('getValue');
	var vkbur=$('#vkbur').combogrid('getValue');
	var bzirk=$('#bzirk').combogrid('getValue');
	var datid=$('#datid').combobox('getValue');
	var reportType=$('input[name="orgType"]:checked ').val();
	var columns=[[]];
	if(reportType=="bzirk"){
		columns=columns1;
	}else if(reportType=="vkbur"){
		columns=columns2;
	}else if(reportType=="vkgrp"){
		columns=columns3;
	}
	
	$("#datagrid").datagrid({
		columns:columns,
		method: 'get',
		singleSelect: true,
		border:false,
		fit:true,
		rownumbers: true,
		url:appUrl+ '/inOutStockAction!getNmdgpDiffReportJson.jspa?ran='+Math.random(),
		queryParams: {bzirk:bzirk,vkbur:vkbur,vkgrp:vkgrp,datid:datid,reportType:reportType}
	});
}

function searchGrid(){
	var datid=$('#datid').combobox('getValue');
	if(datid==""){
		alert('��ѡ��ʱ��');
		return;
	}
	loadGrid();
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


function initWindows(title, url,WWidth,WHeight) {
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
						fit : false,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}
function closeWindows() {
	$("#hiddenWin").window('close');
}


function utcToDate(utcCurrTime, type) {
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
	date = date + month[str[1]] + "-" + str[2];
	if (type == "timestamp") {
		date = date + " " + str[3];
	}
	return date;
}

$('#bzirk').combogrid({
	width:120,
	panelHeight : 340,
	panelWidth : 420,
	pagination : false,
	multiple : false,
	method : 'post',
	singleSelect : true,
	url:appUrl+ '/inOutStockAction!getOrgList2.jspa?parentId=10000002',
	//queryParams: {parentId:'10000002'},
	idField : 'orgCode',
	textField : 'orgName',
	columns : [[{
				field : 'orgCode',
				title : '��������',
				width : 100
			}, {
				field : 'orgName',
				title : '��������',
				width : 300
			}]],
	onSelect : function(index, record) {
		$('#vkbur').combogrid({
			width:120,
			panelHeight : 340,
			panelWidth : 420,
			pagination : false,
			multiple : false,
			method : 'post',
			singleSelect : true,
			url : appUrl + '/inOutStockAction!getOrgList2.jspa',
			queryParams: {parentId:record.orgCode},
			idField : 'orgCode',
			textField : 'orgName',
			columns : [[{
						field : 'orgCode',
						title : '�г�������',
						width : 100
					}, {
						field : 'orgName',
						title : '�г�������',
						width : 300
					}]],
			onSelect : function(indexVkbur,recordVkbur) {
				$('#vkgrp').combogrid({
					width:120,
					panelHeight : 340,
					panelWidth : 420,
					pagination : false,
					multiple : false,
					method : 'post',
					singleSelect : true,
					url : appUrl + '/inOutStockAction!getOrgList2.jspa',
					queryParams: {parentId:recordVkbur.orgCode},
					idField : 'orgCode',
					textField : 'orgName',
					columns : [[{
								field : 'orgCode',
								title : 'Ƭ������',
								width : 100
							}, {
								field : 'orgName',
								title : 'Ƭ������',
								width : 300
							}]]
				}); 	
  			}
		}); 
	}
});

$('#datid').combobox({  
    url:appUrl + '/inOutStockAction!getReportDateJson.jspa',
    valueField:'value',  
    textField:'text'  
}); 

function exportExcel(){
	var vkgrp=$('#vkgrp').combogrid('getValue');
	var vkbur=$('#vkbur').combogrid('getValue');
	var bzirk=$('#bzirk').combogrid('getValue');
	var datid=$('#datid').combobox('getValue');
	var reportType=$('input[name="orgType"]:checked ').val();
	if(datid==""){
		alert('��ѡ���·�');
		return;
	}

	var form = window.document.forms[0];
	form.action = appUrl + '/inOutStockAction!exportNmdgpDiff.jspa?bzirk='+bzirk+'&vkbur='+vkbur+"&vkgrp="+vkgrp+"&datid="+datid+"&reportType="+reportType;
	form.target = "hideFrame";
	form.submit();
}

function clearInput(){
	$('#vkgrp').combogrid('setValue','');
	$('#vkbur').combogrid('setValue','');
	$('#bzirk').combogrid('setValue','');
	$('#datid').combobox('setValue','');
}

function showDiff(id){
	initWindows('������ϸ', '/inOutStockAction!nmdgpDiffDetail.jspa?reaid='+id,800,650);
}

