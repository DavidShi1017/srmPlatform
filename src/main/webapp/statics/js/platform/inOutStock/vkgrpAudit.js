$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});


function loadGrid(){
	var datid=$('#hdDatid').val();
	var bzirk=$('#bzirk').combogrid('getValue');
	var vkbur=$('#vkbur').combogrid('getValue');
	var vkgrp=$('#vkgrp').combogrid('getValue');
	
	
	$("#datagrid").datagrid({
		method: 'get',
		url:appUrl+ '/inOutStockAction!getKunnrSubmitListJson.jspa?ran='+Math.random(),
		queryParams: {datid:datid,bzirk:bzirk,vkbur:vkbur,vkgrp:vkgrp},
		columns:[[ 
			{field:'kunnr',title:'�ͻ�����',width:60,align:'center'}, 
			{field:'kunnm',title:'�ͻ�����',width:250}, 
			{field:'kunnr_tel',title:'�绰',width:100,align:'center'}, 
			{field:'bzirk_txt',title:'����',width:100,align:'center'}, 
			{field:'vkbur_txt',title:'�г���',width:100,align:'center'}, 
			{field:'vkgrp_txt',title:'Ƭ��',width:80,align:'center'}, 
			{field:'state',title:'����ϱ�״̬',width:80,align:'center',formatter:function(v1){return getStateValue(v1,"stock");}}, 
			{field:'state1',title:'�����ϱ�״̬',width:80,align:'center',formatter:function(v2){return getStateValue(v2,"plan");}}, 
			{field:'crdat',title:'�ϱ�ʱ��',width:120,align:'center',formatter : function(v3) {
				if (v3!=null && v3!=undefined) {
					return utcToDate(v3.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
				}
			}}, 
			{field:'austa',title:'Ƭ�����״̬',width:80,align:'center',formatter:function(value,row,index){return getVkgrpStateValue(row);}}, 
			{field:'autim',title:'Ƭ�����ʱ��',width:120,align:'center',formatter : function(v5) {
				if (v5!=null && v5!=undefined) {
					return utcToDate(v5.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
				}
			}}
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
		  			},
		  			onLoadSuccess:function(){
		  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
		  				if(rows.length==1){
		  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
		  				}
		  			}
				}); 
  			},
  			onLoadSuccess:function(){
  				var rows=$('#vkbur').combogrid('grid').datagrid('getRows');
  				if(rows.length==1){
  					$('#vkbur').combogrid('setValue',rows[0].orgCode);
  					$('#vkgrp').combogrid({
  						width:120,
  						panelHeight : 340,
  						panelWidth : 420,
  						pagination : false,
  						multiple : false,
  						method : 'post',
  						singleSelect : true,
  						url : appUrl + '/inOutStockAction!getOrgList.jspa',
  						queryParams: {parentId:rows[0].orgCode},
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
  			  			},
  			  			onLoadSuccess:function(){
  			  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
  			  				if(rows.length==1){
  			  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
  			  				}
  			  			}
  					});
  				}
  			}
		}); 
	},
	onLoadSuccess:function(){
		var rows=$('#bzirk').combogrid('grid').datagrid('getRows');
		if(rows.length==1){
			$('#bzirk').combogrid('setValue',rows[0].orgCode);
			$('#vkbur').combogrid({
				width:120,
				panelHeight : 340,
				panelWidth : 420,
				pagination : false,
				multiple : false,
				method : 'post',
				singleSelect : true,
				url : appUrl + '/inOutStockAction!getOrgList.jspa',
				queryParams: {parentId:rows[0].orgCode},
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
			  			},
			  			onLoadSuccess:function(){
			  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
			  				if(rows.length==1){
			  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
			  				}
			  			}
					}); 
	  			},
	  			onLoadSuccess:function(){
	  				var rows=$('#vkbur').combogrid('grid').datagrid('getRows');
	  				if(rows.length==1){
	  					$('#vkbur').combogrid('setValue',rows[0].orgCode);
	  					$('#vkgrp').combogrid({
	  						width:120,
	  						panelHeight : 340,
	  						panelWidth : 420,
	  						pagination : false,
	  						multiple : false,
	  						method : 'post',
	  						singleSelect : true,
	  						url : appUrl + '/inOutStockAction!getOrgList.jspa',
	  						queryParams: {parentId:rows[0].orgCode},
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
	  			  			},
	  			  			onLoadSuccess:function(){
	  			  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
	  			  				if(rows.length==1){
	  			  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
	  			  				}
	  			  			}
	  					});
	  				}
	  			}
			}); 
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

function createKunnrProduct(){
	var kunnr=$("#kunnr").combogrid("getValue");
//	if(kunnr==""){
//		alert("����ѡ��ͻ�");
//	}else{
//		initWindows('��Ʒ���', '/inOutStockAction!addKunnrProduct.jspa',800,600);
//	}
	initWindows('��Ʒ���', '/inOutStockAction!addKunnrProduct.jspa',800,600);
}


function saveKunnrStock(){
	var postStr="";//�����洢list�����ݣ����post��ȥ
	var rootRows=$('#datagrid').treegrid('getRoots'); //��ȡ���ڵ���Ϣ
	for(var s=0;s<rootRows.length;s++){
		var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//��ȡ�ӽڵ���Ϣ
		$.each(rows, function(index, item){
			postStr+=item.matnr+"@@";
			postStr+=item.count1+"@@";
			postStr+=item.count2+"@@";
			postStr+=item.count3+"@@";
			postStr+=item.count4+"@@";
			postStr+=encodeURIComponent(item.productName)+"@@";
			postStr+=item.kbetr+"@@";
			postStr+=item.spart+"@@";
			postStr+=item.mvgr1+"##";
		}); 
	}
	
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveKunnrPlan.jspa",
		data : {
			stockListStr:postStr,
			kunnr:$('#kunnr').combogrid('getValue'),
			datid:$('#hdDatid').val(),
			bedat:$('#hdBedat').val(),
			endat:$('#hdEndat').val(),
			month:$('#hdMonth').val()
		},
		success : function(obj) {
			if(obj=="1"){
				$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
			}else if("0"){
				$.messager.alert('��ʾ��', '��������ˣ������ٱ���', 'info');
			}else{
				$.messager.alert('��ʾ��', '����ʧ��!', 'info');
			}
		}
	});
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
function getVkgrpStateValue(row){
	var austa=row.austa;
	var autim=row.autim;
	if(austa>0){
		return "<font color=red>�����</font>";
	}else{
		if(autim!=""&&autim!=null&&autim!=undefined){
			return "<font color=blue>���˻�</font>";
		}else{
			return "<font color=black>δ���</font>";
		}
	}
	
	return "";
}
function searchSubmit(){
	var inTime=$("#hdInTime").val();//�ж��Ƿ���������
	if(inTime=="in"){
		var vkgrp=$('#vkgrp').combogrid('getValue');
		var vkbur=$('#vkbur').combogrid('getValue');
		if(null!=vkgrp&&""!=vkgrp){
			var rows = $("#datagrid").datagrid("getRows");	
			if(rows.length==0){
				alert("û�����ݣ������ύ");
				return;
			}
			var strMsg="";
			for(var r=0;r<rows.length;r++){
				if(rows[r].state!=1){
					strMsg+=rows[r].kunnr+rows[r].kunnm+",";
				}
			}
			if(strMsg!=""){
				alert("Ƭ���»������¿ͻ�û���ᱨ���¼ƻ���"+strMsg+"����ɿͻ���������ƻ�����ˣ�");
			}else{
				location.href=appUrl+ '/inOutStockAction!vkgrpAuditList.jspa?vkgrp='+vkgrp+"&vkbur="+vkbur;
			}
		}else{
			alert('����ѡ��Ƭ��');
		}
	}else{
		$.messager.alert('��ʾ��', '������Ч�����ڷ�Χ�ڣ���Ч���ᱨ����Ϊ'+$("#hdBeplt").val()+'��'+$("#hdEnplt").val()+'��', 'info');
		return;
	}
}

function clearInput(){
	$('#vkgrp').combogrid('setValue','');
	$('#vkbur').combogrid('setValue','');
	$('#bzirk').combogrid('setValue','');
}
