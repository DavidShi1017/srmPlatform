$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function getKunnrColumns1(){//���datagrid�ı���
	var strTitle="";
	strTitle+="{field:'kbetr',title:'����<br/>(Ԫ/��)',width:60,align:'center',rowspan:2,formatter: function(value,row,index){return rightView(value);}},";
	var kunnms= new Array(); 
	kunnms=$("#hdKunnmListStr").val().split(",");
	strTitle+="{title:'Ƭ������ᱨ',align:'center',colspan:5},";
	for(var i=0;i<kunnms.length;i++){
		strTitle+="{title:'"+kunnms[i]+"',align:'center',colspan:4},";
	}
	strTitle+="{title:'û�е�',align:'right',width:75,hidden:true}";
	return strTitle;
}
function getKunnrColumns2(){//���datagrid�ı���
	var strTitle="";
	var kunnrs= new Array(); 
	kunnrs=$("#hdKunnrListStr").val().split(",");
	strTitle+="{field:'vkgrpCount',title:'Ƭ���������<br>�����',align:'right',width:75,editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!=\"\") {return 'background-color:#FFF7bb';}}},";
	strTitle+="{field:'vkgrpPrice',title:'Ƭ���������<br>������(��Ԫ)',align:'right',width:80},";
	strTitle+="{field:'totalCount',title:'�ͻ��������<br>����(��)',align:'right',width:75},";
	strTitle+="{field:'totalPrice',title:'�ͻ�������<br>����(��Ԫ)',align:'right',width:75},";
	strTitle+="{field:'vkgrpTarget',title:'���»���<br>Ŀ��(��Ԫ)',align:'right',width:70},";
	
	for(var i=0;i<kunnrs.length;i++){
		strTitle+="{field:'target"+kunnrs[i]+"',title:'���»���<br/>Ŀ��(��Ԫ)',align:'right',width:65},";
		strTitle+="{field:'count"+kunnrs[i]+"',title:'�������<br>����(��)',align:'right',width:65},";
		strTitle+="{field:'price"+kunnrs[i]+"',title:'�������<br>���(��Ԫ)',align:'right',width:65},";
		strTitle+="{field:'diff"+kunnrs[i]+"',title:'������<br>(��Ԫ)',align:'right',width:65},";
	}
	strTitle+="{title:'û�е�',align:'right',width:75,hidden:true}";
	return strTitle;
}

function rightView(v){
	if(v!=null){
		return "<span style=\"float:right\">"+v+"</span>";
	}
}

function getKunnrColumns3(){
	var columns="";
	columns="[["+getKunnrColumns1()+"],["+getKunnrColumns2()+"]]";
	return columns;
}


function loadGrid(){
	_loadwaiting();
	setTimeout(test,500);//500�����ʼ���ƹ���
}
function test(){
	var obj = eval('(' + getKunnrColumns3() + ')');
	$.ajax({
		type : "get",
		async : false,
		url:appUrl+ '/inOutStockAction!getVkgrpAuditListJson.jspa?ran='+Math.random(),
		data : {vkgrp:$('#hdVkgrp').val(),datid:$('#hdDatid').val()},
		loadMsg : '����Զ��������,��ȴ�...',
		success : function(obj2) {
			data=obj;
			$('#datagrid').treegrid({  
				loadMsg : '����Զ��������,��ȴ�...',
			    fit:true,
			    method: 'get',
				idField: 'id',
				treeField:'productName', 
				rownumbers:true,
				showFooter:true,
				nowrap:true,
				animate:true,
				data:eval('('+obj2+')'),
				frozenColumns:[[
					{field:'productName',title:'��Ʒ����',width:300},
					{field:'matnr',title:'��Ʒ����',width:80}
				]],
				columns:obj,
				toolbar: [{
					iconCls: "icon-add",
					text: "ȫչ��",
					handler:function(){
						$('#datagrid').treegrid('expandAll');
					}
				},"-",{
					iconCls: "icon-remove",
					text: "ȫ����",
					handler:function(){
						$('#datagrid').treegrid('collapseAll');
					}
				},"-",{
					iconCls: "icon-ok",
					text: "��һ��",
					handler:function(){
						history.go(-1);
					}
				},"-",{
					iconCls: "icon-excel",
					text: "Excel����",
					handler:function(){
						importVkgrpAudit();
					}
				},"-",{
					iconCls: "icon-save",
					text: "�ύ",
					handler : function() {
						saveVkgrpAudit();
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
			
			
			
//			$('#datagrid').treegrid({
//				idField: 'id',
//				treeField:'productName', 
//				//url:appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
//				//queryParams: {kunnr:$('#kunnr').combogrid('getValue')},
//				data:eval('('+obj+')'),
//				//loadMsg : '����Զ��������,��ȴ�...'
//			});
		}
	});
	_removeloading();
	
	
	
	
//	$('#datagrid').treegrid({  
//		queryParams: {vkgrp:$('#hdVkgrp').val(),datid:$('#hdDatid').val()},
//		loadMsg : '����Զ��������,��ȴ�...',
//	    fit:true,
//	    method: 'get',
//		idField: 'id',
//		treeField:'productName', 
//		rownumbers:true,
//		showFooter:true,
//		nowrap:true,
//		animate:true,
//		url:appUrl+ '/inOutStockAction!getVkgrpAuditListJson.jspa',
//		
//		frozenColumns:[[
//			{field:'productName',title:'��Ʒ����',width:300}
//		]],
//		columns:obj,
//		toolbar: [{
//			iconCls: "icon-ok",
//			text: "��һ��",
//			handler:function(){
//				saveKunnrStock();
//			}
//		},"-",{
//			iconCls: "icon-save",
//			text: "����",
//			handler : function() {
//				saveVkgrpAudit();
//			}
//		}],
//		onClickCell: function (value, row) {
//			editValue(value,row);
//		},
//		onAfterEdit:function(row,changes){
//			changeValue(row,changes);
//		},rowStyler:function(row){     
//			if (row.id.length==3){     
//				return 'background-color:#FDF9BB;';     
//			}else if(row.id.length==7) {
//				return 'background-color:#9DDBF4';    
//			}     
//		}  
//	}); 
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
		$('#vkbur').combogrid({
			width:120,
			panelHeight : 340,
			panelWidth : 420,
			pagination : false,
			multiple : false,
			method : 'post',
			singleSelect : true,
			url : appUrl + '/inOutStockAction!getOrgList.jspa',
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
					url : appUrl + '/inOutStockAction!getOrgList.jspa',
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
							}]],
					onSelect : function(indexVkgrp,recordVkgrp) {
						loadGrid();
		  			}
				}); 
  			}
		}); 
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

function saveVkgrpAudit(){
	//var postStr="";//�����洢list�����ݣ����post��ȥ
	var temp="-1";
	var datid=$('#hdDatid').val();
	var vkgrp=$('#hdVkgrp').val();
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {vkgrp:vkgrp,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>1){
		alert('Ƭ�������г�������ˣ������ύ!');
	}else{
		_loadwaiting();
		setTimeout(saveVkgrpAudit2,500);//500�����ʼ���ƹ���
	}
}
function saveVkgrpAudit2(){
	var rootRows=$('#datagrid').treegrid('getRoots'); //��ȡ���ڵ���Ϣ
	var nndgpList = [];
	for(var s=0;s<rootRows.length;s++){
		var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//��ȡ�ӽڵ���Ϣ
		$.each(rows, function(index, item){
			var obj = {};
			obj.matnr=item.id;
			//obj.maktx=item.productName;
			obj.maktx=encodeURIComponent(item.productName);
			obj.spart=item.spart;
			obj.mvgr1=item.mvgr1;
			obj.nmdgp=item.vkgrpCount==null?0:item.vkgrpCount;
			obj.nmdgp_pri=item.vkgrpPrice==null?0:item.vkgrpCount*item.kbetr;
			obj.datid=$('#hdDatid').val();
			obj.vkgrp=$('#hdVkgrp').val();
			nndgpList.push(obj);
		}); 
	}

	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveVkgrpAudit.jspa",
		data : {
			nmdgpList:JSON.stringify(nndgpList)
		},
		success : function(obj) {
			if(obj=="1"){
				alert('�����ɹ�');
				location.href=appUrl+"/inOutStockAction!vkgrpAuditDiff.jspa?vkgrp="+$("#hdVkgrp").val()+"&datid="+$("#hdDatid").val();
			}else if("0"){
				$.messager.alert('��ʾ��', '��������ˣ������ٱ���', 'info');
			}else{
				$.messager.alert('��ʾ��', '����ʧ��!', 'info');
			}
		}
	});
	
	_removeloading();
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

function searchSubmit(){
	var vkgrp=$('#vkgrp').combogrid('getValue');
	if(null!=vkgrp&&""!=vkgrp){
		var rows = $("#datagrid").datagrid("getRows");	
		var strMsg="";
		for(var r=0;r<rows.length;r++){
			if(rows[r].state!=1){
				strMsg+=rows[r].kunnr+rows[r].kunnm+",";
			}
		}
		if(strMsg!=""){
			alert("Ƭ���»������¿ͻ�û���ᱨ���¼ƻ���"+strMsg+"����ɿͻ���������ƻ�����ˣ�");
		}else{
			location.href=appUrl+ '/inOutStockAction!vkgrpAuditList.jspa?vkgrp='+vkgrp;
		}
	}else{
		alert('����ѡ��Ƭ��');
	}
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


function importVkgrpAudit(){//����excel
	var vkgrp=$("#hdVkgrp").val();
	var datid=$('#hdDatid').val();
	var vkbur=$('#hdVkbur').val();
	
	var temp="-1";
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {vkgrp:vkgrp,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>1){
		alert('�г�������ˣ����ܵ��룡');
	}else{
		initWindows('Ƭ����˽������', '/inOutStockAction!importVkgrpAudit.jspa?vkbur='+vkbur+'&vkgrp='+vkgrp+"&datid="+datid,500,300);
	}
}

function importSuccess(){
	if(confirm('����ɹ�������������Ҫ���в����Է������')){
		location.href=appUrl+"/inOutStockAction!vkgrpAuditDiff.jspa?vkgrp="+$("#hdVkgrp").val()+"&datid="+$("#hdDatid").val();
	}
}
