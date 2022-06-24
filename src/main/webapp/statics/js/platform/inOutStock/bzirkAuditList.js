$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function getVkburColumns1(){//���datagrid�ı���
	var strTitle="";
	strTitle+="{field:'kbetr',title:'����<br/>(Ԫ/��)',width:60,align:'center',rowspan:2,formatter: function(value,row,index){return rightView(value);}},";
	var vkburTxts= new Array(); 
	vkburTxts=$("#hdVkburTxtListStr").val().split(",");
	for(var i=0;i<vkburTxts.length;i++){
		strTitle+="{title:'"+vkburTxts[i]+"',align:'center',colspan:4},";
	}
	strTitle+="{title:'�г�������ᱨ',align:'center',colspan:5}";
	return strTitle;
}
function getVkburColumns2(){//���datagrid�ı���
	var strTitle="";
	var vkburs= new Array(); 
	vkburs=$("#hdVkburListStr").val().split(",");
	for(var i=0;i<vkburs.length;i++){
		strTitle+="{field:'target"+vkburs[i]+"',title:'���»���<br/>Ŀ��(��Ԫ)',align:'right',width:65},";
		strTitle+="{field:'count"+vkburs[i]+"',title:'�������<br>����(��)',align:'right',width:65},";
		strTitle+="{field:'price"+vkburs[i]+"',title:'�������<br>���(��Ԫ)',align:'right',width:65},";
		strTitle+="{field:'diff"+vkburs[i]+"',title:'������<br>(��Ԫ)',align:'right',width:65},";
	}
	strTitle+="{field:'totalCount',title:'�г����������<br>����(��)',align:'right',width:75},";
	strTitle+="{field:'totalPrice',title:'�г���������<br>����(��Ԫ)',align:'right',width:75},";
	strTitle+="{field:'bzirkTarget',title:'���»���<br>Ŀ��(��Ԫ)',align:'right',width:70}";;
	return strTitle;
}

function rightView(v){
	if(v!=null){
		return "<span style=\"float:right\">"+v+"</span>";
	}
}

function getVkburColumns3(){
	var columns="";
	columns="[["+getVkburColumns1()+"],["+getVkburColumns2()+"]]";
	return columns;
}


function loadGrid(){
	_loadwaiting();
	setTimeout(test,500);//500�����ʼ���ƹ���
}
function test(){
	$.ajax({
		type : "get",
		async : false,
		url:appUrl+ '/inOutStockAction!getBzirkAuditListJson.jspa?ran='+Math.random(),
		data : {bzirk:$('#hdBzirk').val(),datid:$('#hdDatid').val()},
		loadMsg : '����Զ��������,��ȴ�...',
		success : function(obj2) {
			var obj = eval('(' + getVkburColumns3() + ')');
			$('#datagrid').treegrid({  
			    fit:true,
			    method: 'get',
				idField: 'id',
				treeField:'productName', 
				rownumbers:true,
				showFooter:true,
				data:eval('('+obj2+')'),
				frozenColumns:[[
					{field:'productName',title:'��Ʒ����',width:300}
				]],
				columns:obj,
				toolbar: [{
					iconCls: "icon-ok",
					text: "��һ��",
					handler:function(){
						history.go(-1);
					}
				},"-",{
					iconCls: "icon-save",
					text: "���",
					handler : function() {
						saveBzirkAudit();
					}
				}],
				onClickCell: function (value, row) {
					editValue(value,row);
				},
				onAfterEdit:function(row,changes){
					changeValue(row,changes);
				},rowStyler:function(row){     
					if (row.id.length==3){     
						return 'background-color:#FDF9BB;';     
					}else if(row.id.length==7) {
						return 'background-color:#9DDBF4';    
					}     
				}  
			}); 
		}
	});
	_removeloading();
}

function searchGrid(){
	loadGrid();
}




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
function changeValue(row,changes){
	var kbetr=parseFloat(row.kbetr);
	var count=parseFloat(row.vkgrpCount);
	if(count>=0){
		row.vkgrpPrice=(kbetr*count/10000).toFixed(2);
	}
	$('#datagrid').treegrid('refresh',row.id); 
	updateValue(row.parentid);
	updateFootValue();
}
function updateValue(rowid){//����ϵ�л��ܵ�����
	var row1=$('#datagrid').treegrid('find',rowid);//�ҳ����ڵ�
	var parentId=rowid;
	var row2=$('#datagrid').treegrid('getChildren',parentId);//�ҳ����ڵ��µ������ӽڵ�
	var count=0,price=0;
	$.each(row2, function(index, item){
		if(!isNaN(item.vkgrpPrice)&&item.vkgrpPrice!=""){//�����ֵ�ʱ��ִ��
			price+=parseFloat(item.vkgrpPrice);
		}
		if(!isNaN(item.vkgrpCount)&&item.vkgrpCount!=""){//�����ֵ�ʱ��ִ��
			count+=parseFloat(item.vkgrpCount);
		}
	}); 
	row1.vkgrpPrice=parseFloat(price).toFixed(2);
	row1.vkgrpCount=parseFloat(count).toFixed(2);
	$('#datagrid').treegrid('refresh',parentId);  
}
function updateFootValue(){
	var rowFoot=$('#datagrid').treegrid('getFooterRows');
	var rowRoot=$('#datagrid').treegrid('getRoots');
	var count=0,price=0;
	$.each(rowRoot, function(index, item){
		if(!isNaN(item.vkgrpPrice)&&item.vkgrpPrice!=""){//�����ֵ�ʱ��ִ��
			price+=parseFloat(item.vkgrpPrice);
		}
		if(!isNaN(item.vkgrpCount)&&item.vkgrpCount!=""){//�����ֵ�ʱ��ִ��
			count+=parseFloat(item.vkgrpCount);
		}
	});
	var obj=[{"productName":"�ϼƣ�","vkgrpCount":count,"vkgrpPrice":price,"iconCls":"icon-sum"}];
	$('#datagrid').treegrid('reloadFooter',obj);  
}


function search1(){
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
}

function saveBzirkAudit(){
	var temp="-1";
	var datid=$('#hdDatid').val();
	var bzirk=$("#hdBzirk").val();
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {bzirk:bzirk,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>2){
		alert('�ô�������ˣ������ظ��ύ');
	}else{
		location.href=appUrl+"/inOutStockAction!bzirkAuditDiff.jspa?bzirk="+$("#hdBzirk").val()+"&datid="+$("#hdDatid").val();
	}
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

function getStateValue(value,type){
	if(type=="stock"){
		if(value>=0){
			return "<font color=red>���ϱ�</font>";
		}else{
			return "<font color=black>δ�ϱ�</font>";
		}
	}
	else if(type=="plan"){
		if(value>=1){
			return "<font color=red>���ϱ�</font>";
		}else{
			return "<font color=black>δ�ϱ�</font>";
		}
	}else if(type=="vkgrp"){
		if(value>0){
			return "<font color=red>�����</font>";
		}else{
			return "<font color=black>δ���</font>";
		}
	}
	return "";
}

function _loadwaiting(){//��ʾ�ȴ���Ϣ  
    var wrap = $("#cc");  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:wrap.width(),height:wrap.height()}).appendTo(wrap);  
    $("<div class=\"datagrid-mask-msg\"></div>").html("������,��ȴ�...").appendTo(wrap).css({display:"block",left:(wrap.width()-$("div.datagrid-mask-msg",wrap).outerWidth())/2,top:(wrap.height()-$("div.datagrid-mask-msg",wrap).outerHeight())/2});  
}  
function _removeloading(){//���صȴ���Ϣ  
    var wrap = $("#cc");  
    wrap.find("div.datagrid-mask-msg").remove();  
    wrap.find("div.datagrid-mask").remove();  
} 
