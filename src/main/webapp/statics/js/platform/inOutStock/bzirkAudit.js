$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function loadGrid(){
	var datid=$('#hdDatid').val();
	var bzirk=$('#bzirk').combogrid('getValue');
	$("#datagrid").datagrid({
		method: 'get',
		url:appUrl+ '/inOutStockAction!getVkburSubmitListJson.jspa?ran='+Math.random(),
		queryParams: {datid:datid,bzirk:bzirk},
		async : false,
		columns:[[ 
			{field:'bzirk_txt',title:'����',width:100,align:'center'}, 
			{field:'vkbur_txt',title:'�г���',width:100,align:'center'},  
			{field:'austa',title:'�г������״̬',width:80,align:'center',formatter:function(value, row, index){return getStateValue(row);}}, 
			{field:'autim1',title:'�г������ʱ��',width:120,align:'center',formatter : function(v) {
				if (v!=null && v!=undefined) {
					return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
				}
			}},
			{field:'diff',title:'�г�������ԭ��',width:90,align:'center',formatter : function(value, row, index) {
				return getDiff(value,row,index);
			}},
			{field:'austatemp',title:'�������״̬',align:'center',formatter:function(value, row, index){return getBzirkState(value, row, index);}}, 
			{field:'autim2',title:'�������ʱ��',width:120,align:'center',formatter : function(v5) {
				if (v5!=null && v5!=undefined) {
					return utcToDate(v5.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
				}
			}}, 
			{field:'vkbur',title:'����',width:100,align:'center',formatter:function(value, row, index){return getBzirkBack(value,row,index);}}
		]],
		border:false,
		fit:true,
		rownumbers: true,
		fitColumns:false,
		showFooter:true,
		toolbar: "#toolbar"
	});
}

function searchGrid(){
	loadGrid();
}

$('#bzirk').combogrid({
	width:120,
	panelHeight : 340,
	panelWidth : 420,
	pagination : false,
	multiple : false,
	method : 'post',
	singleSelect : true,
	url:appUrl+ '/inOutStockAction!getOrgList.jspa',
	queryParams: {parentId:'10000002'},
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
		loadGrid();
	},
	onLoadSuccess:function(){
		var rows=$('#bzirk').combogrid('grid').datagrid('getRows');
		if(rows.length==1){
			$('#bzirk').combogrid('setValue',rows[0].orgCode);
		}
	}
});

$('#kunnr').combogrid({
	onChange:function(r) {
		searchList();
	}
});


function setSearchValue(){
	
}
function searchList(){
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
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


var editingId;
function editValue(value,row){
	if (editingId != undefined){
		$('#datagrid').treegrid('select', editingId);
	}
	var row2 = $('#datagrid').treegrid('getSelected');
	if(row2){
		$('#datagrid').treegrid('endEdit', editingId);
	}
	editingId = row.id;
	if(editingId.length>3){
		$('#datagrid').treegrid('beginEdit', editingId);
	}
}


function search1(){
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
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

function getStateValue(row){
	var austa=row.austa;
	var autim1=row.autim1;
	if(austa>1){
		return "<font color=red>�����</font>";
	}else{
		if(autim1!=""&&autim1!=null&&autim1!=undefined){
			return "<font color=blue>���˻�</font>";
		}else{
			return "<font color=black>δ���</font>";
		}
	}
	
	return "";
}

function getBzirkState(value, row, index){
	var st=row.austa;
	if(st>=3){
		return "<font color=red>�����</font>";
	}else{
		return "<font color=black>δ���</font>";
	}
	
}
function getBzirkBack(value,row,index){
	var state=row.austa;
	if(state==2){
		return "<a href=\"javascript:bzirkBack("+row.vkbur+")\">�˻��г���</a>";
	}
}
function getDiff(value,row,index){
	var datid=$('#hdDatid').val();
	var vkbur=row.vkbur;
	var reportType="vkbur";
	var r="0";
	$.ajax({
		type : "get",
		async : false,
		url : appUrl + "/inOutStockAction!getReaidByOrgId.jspa",
		data : {
			vkbur:vkbur,
			datid:datid,
			reportType:reportType
		},
		success : function(obj) {
			r=obj
		}
	});
	if(r!="0"){
		return "<a href=\"javascript:showDiff("+r+");\">�鿴����ԭ��</a>";
	}
}
function showDiff(id){
	initWindows('������ϸ', '/inOutStockAction!nmdgpDiffDetail.jspa?reaid='+id,800,650);
}


function bzirkBack(vkbur){
	if(confirm('ȷ��Ҫ�˻���')){
		$.ajax({
			type : "post",
			async : false,
			url : appUrl + "/inOutStockAction!backToVkbur.jspa",
			data : {
				vkbur:vkbur,
				datid:$('#hdDatid').val()
			},
			success : function(obj) {
				if(obj=="1"){
					$.messager.alert('��ʾ��', '�˻سɹ�!', 'info');
					loadGrid();
				}else{
					$.messager.alert('��ʾ��', '����ʧ��!', 'info');
				}
			}
		});
	}
}



function searchSubmit(){
	var bzirk=$('#bzirk').combogrid('getValue');
	if(null!=bzirk&&""!=bzirk){
		var rows = $("#datagrid").datagrid("getRows");
		if(rows.length==0){
			alert("û�����ݣ������ύ");
			return;
		}
		var strMsg="";
		for(var r=0;r<rows.length;r++){
			if(rows[r].austa<2){
				strMsg+=rows[r].vkbur_txt+",";
			}
		}
		if(strMsg!=""){
			alert("�����»��������г���û���ᱨ���¼ƻ���"+strMsg+"������г�������������ƻ�����˺����������");
		}else{
			location.href=appUrl+ '/inOutStockAction!bzirkAuditList.jspa?bzirk='+bzirk;
		}
		//location.href=appUrl+ '/inOutStockAction!bzirkAuditList.jspa?bzirk='+bzirk;
	}else{
		alert('����ѡ�����');
	}
}

