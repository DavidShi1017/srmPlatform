









$(document).ready(function() {
	if(checkIsKunnr()){
		search1();
	}
	//loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function checkIsKunnr(){
	var kunnr=$('#hdKunnr').val();
	if(kunnr.length>5){//�ǿͻ�
		$('#table2').css('display','block'); 
		$('#kunnr').combogrid('setValue',kunnr);
		//search1();
		return true;
	}else{
		$('#table1').css('display','block'); 
		return false;
	}
}

$('#datagrid').treegrid({
	method: 'get',
	idField: 'id',
	treeField:'productName', 
	//loadMsg : '����Զ��������,��ȴ�...',
	columns:[[ 
		{field:'productName',width:350,title:'��Ʒ����',rowspan:2},
		{field:'matnr',width:80,title:'��Ʒ����',rowspan:2}, 
		{field:'kbetr',width:100,title:'����',hidden:true,rowspan:2},
		{field:'spart',width:100,title:'Ʒ��',hidden:true,rowspan:2}, 
		{field:'mvgr1',width:100,title:'ϵ��',hidden:true,rowspan:2}, 
		{field:'preps',title:$("#hdMonth1").val()+'<br>��ĩ���',width:70,align:'right',rowspan:2},
		{field:'tpiwh',title:$("#hdMonth2").val()+'<br>�����',width:70,align:'right',rowspan:2},
		{field:'tpowh',title:$("#hdMonth2").val()+'<br>�³���',width:70,align:'right',rowspan:2},
		{title:$("#hdMonth2").val()+'��ĩ���(��)',width:80,align:'right',colspan:2}
	],[{field:'finps',title:'�ϼ�',width:80,align:'right',editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!="") {return 'background-color:#FFF7bb';}}},
	   {field:'finps_pre',title:'���и����',width:80,align:'right',editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!=""){return 'background-color:#FFF7bb';}}}
	   ]],
	//border:false,
	fit:true,
	rownumbers: true,
	//fitColumns:true,
	animate: true,
	collapsible: true,
	//showFooter:true,
	toolbar: [{
		iconCls: "icon-save",
		text: "����",
		handler:function(){
			saveKunnrStock(1);
		}
	},"-",{
		iconCls: "icon-ok",
		text: "���沢���������ϱ�",
		handler:function(){
			saveKunnrStock(2);
		}
	},"-",{
		iconCls: "icon-add",
		text: "��Ӳ�Ʒ",
		handler : function() {
			createKunnrProduct();
		}
	},"-",{
		iconCls: "icon-excel",
		text: "Excel����",
		handler:function(){
			importKunnrProduct();
		}
	}],
	
	onClickCell: function (value, row) {
		editValue(value,row);
	},
	onAfterEdit:function(row,changes){
		changeValue(row,changes);
	}
});


function loadGrid() {
	_loadwaiting();
//	var data;
//	$.ajax({
//		type : "get",
//		async : false,
//		url : appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
//		data : {kunnr:$('#kunnr').combogrid('getValue')},
//		loadMsg : '����Զ��������,��ȴ�...',
//		success : function(obj) {
//			data=obj;
//			$('#datagrid').treegrid({
//				idField: 'id',
//				treeField:'productName', 
//				//url:appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
//				//queryParams: {kunnr:$('#kunnr').combogrid('getValue')},
//				data:eval('('+data+')'),
//				//loadMsg : '����Զ��������,��ȴ�...'
//			});
//		}
//	});
	//alert(data);
	setTimeout(test,500);//500�����ʼ���ƹ���
	
	//_removeloading();
}
function test(){
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getKunnrProduct.jspa?ran='+Math.random(),
		data : {kunnr:$('#kunnr').combogrid('getValue')},
		loadMsg : '����Զ��������,��ȴ�...',
		success : function(obj) {
			$('#datagrid').treegrid({
				idField: 'id',
				treeField:'productName', 
				//url:appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
				//queryParams: {kunnr:$('#kunnr').combogrid('getValue')},
				data:eval('('+obj+')')
				//loadMsg : '����Զ��������,��ȴ�...'
			});
		}
	});
	_removeloading();
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
						$('#kunnr').combogrid({
							width:250,
							panelHeight : 340,
							panelWidth : 420,
							pagination : false,
							multiple : false,
							method : 'post',
							singleSelect : true,
							url : appUrl + '/inOutStockAction!getKunnrList.jspa',
							queryParams: {vkgrp:recordVkgrp.orgCode},
							idField : 'kunnr',
							textField : 'kunnm',
							columns : [[{
										field : 'kunnr',
										title : '�ͻ�����',
										width : 100
									}, {
										field : 'kunnm',
										title : '�ͻ�����',
										width : 300
									}]],
							onSelect : function(r) {
								search1();
				  			}
						}); 
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
							$('#kunnr').combogrid({
								width:250,
								panelHeight : 340,
								panelWidth : 420,
								pagination : false,
								multiple : false,
								method : 'post',
								singleSelect : true,
								url : appUrl + '/inOutStockAction!getKunnrList.jspa',
								queryParams: {vkgrp:recordVkgrp.orgCode},
								idField : 'kunnr',
								textField : 'kunnm',
								columns : [[{
											field : 'kunnr',
											title : '�ͻ�����',
											width : 100
										}, {
											field : 'kunnm',
											title : '�ͻ�����',
											width : 300
										}]],
								onSelect : function(r) {
									search1();
					  			}
							}); 
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

function endEditing(){
	if (editingId == undefined){return true;}
	var row2 = $('#datagrid').treegrid('getSelected');
	if(row2){
		$('#datagrid').treegrid('endEdit', editingId);
	}
}

//var editIndex = undefined;
//function onClickRow(index){
//	if (editIndex != index){
//		if (endEditing()){
//			$('#datagrid').datagrid('selectRow', index).datagrid('beginEdit', index);
//			editIndex = index;
//		} else {
//			$('#datagrid').datagrid('selectRow', editIndex);
//		}
//	}
//}
//function endEditing(){
//	if (editIndex == undefined){return true;}
//	if ($('#datagrid').datagrid('validateRow', editIndex)){
//		$('#datagrid').datagrid('endEdit', editIndex);
//		editIndex = undefined;
//		return true;
//	} else {
//		return false;
//	}
//}



function changeValue(row,changes){
//	var finps=parseFloat(row.finps);
//	var finps_this=parseFloat(row.finps_this);
//	var tpiwh=parseFloat(row.tpiwh);
//	var preps=parseFloat(row.preps);
//	
//	var finps_pre=finps-finps_this;
//	row.finps_pre=finps_pre;
//	var tpowh=preps+tpiwh-finps;
//	row.tpowh=tpowh;
	$('#datagrid').treegrid('refresh',row.id);  
}



function search1(){
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
}

function createKunnrProduct(){
	var inTime=$("#hdInTime").val();//�ж��Ƿ���������
	if(inTime=="in"){
		var kunnr=$("#kunnr").combogrid("getValue");
		if(kunnr==""){
			alert("����ѡ��ͻ�");
		}else{
			var productIdList="0";
			var rootRows=$('#datagrid').treegrid('getRoots'); //��ȡ���ڵ���Ϣ
			for(var s=0;s<rootRows.length;s++){
				var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//��ȡ�ӽڵ���Ϣ
				$.each(rows, function(index, item){
					productIdList+=","+item.matnr;
				}); 
			}
			initWindows('��Ʒ���', '/inOutStockAction!addKunnrProduct.jspa?productIdList='+productIdList,800,500);
		}
	}else{
		$.messager.alert('��ʾ��', '������Ч�����ڷ�Χ�ڣ���Ч���ᱨ����Ϊ'+$("#hdBeref").val()+'��'+$("#hdEnref").val()+'��', 'info');
		return;
	}
	//initWindows('��Ʒ���', '/inOutStockAction!addKunnrProduct.jspa',800,600);
}
function importKunnrProduct(){//����excel
	var inTime=$("#hdInTime").val();//�ж��Ƿ���������
	if(inTime=="in"){
		var kunnr=$("#kunnr").combogrid("getValue");
		if(kunnr==""){
			alert("����ѡ��ͻ�");
		}else{
			initWindows('�����Ʒ���', '/inOutStockAction!importKunnrStock.jspa?kunnr='+kunnr,500,300);
		}
	}
	else{
		$.messager.alert('��ʾ��', '������Ч�����ڷ�Χ�ڣ���Ч���ᱨ����Ϊ'+$("#hdBeref").val()+'��'+$("#hdEnref").val()+'��', 'info');
		return;
	}
}


function addProduct(names){
	var newProduct=new Array();
	var names2=new Array();
	names2=names;
	if(names2.length>0){
		for(var i=0;i<names2.length;i++){
			var mvgr1=names2[i].split("#")[0];
			var matnr=names2[i].split("#")[1];
			var maktx=names2[i].split("#")[2];
			var mvgr1_txt=names2[i].split("#")[3];
			var kbetr=names2[i].split("#")[4];
			var spart=names2[i].split("#")[5];
			
			
			var rows=$('#datagrid').treegrid('getChildren',mvgr1);
			if(rows.length==0){//û���ڵ�
				//��Ӹ��ڵ�
				$('#datagrid').treegrid('append',{
					data: [{
						productName: mvgr1_txt,
						id: mvgr1
					}]
				});
				$('#datagrid').treegrid('append',{
					parent: mvgr1,  
					data: [{
						id:matnr,
						productName: maktx,
						matnr: matnr,
						preps:0,
						tpiwh:0,
						tpowh:0,
						finps_pre:0,
						finps_this:0,
						finps:0,
						kbetr:kbetr,
						mvgr1:mvgr1,
						spart:spart
					}]
				});
				newProduct.push(matnr);
			}else{//�и��ڵ�
				var count=0;
				$.each(rows, function(index, item){
					if(item.matnr==matnr){
						count++;
					}
				}); 
				if(count==0){
					$('#datagrid').treegrid('append',{
						parent: mvgr1,  
						data: [{
							id:matnr,
							productName: maktx,
							matnr: matnr,
							preps:0,
							tpiwh:0,
							tpowh:0,
							finps_pre:0,
							finps_this:0,
							finps:0,
							kbetr:kbetr,
							mvgr1:mvgr1,
							spart:spart
						}]
					});
					newProduct.push(matnr);
				}
			}
		}
	}
	closeWindows();
	
	$('#datagrid').treegrid({
		rowStyler: function(row){
			for(var i=0;i<newProduct.length;i++){
				if (row.matnr==newProduct[i]){
					return 'background-color:#6293BB;';
				}
			}
		}
	});

}

function saveKunnrStock(type){
	endEditing();
	var postStr="";//�����洢list�����ݣ����post��ȥ
	var inTime=$("#hdInTime").val();//�ж��Ƿ���������
	if(inTime=="in"){
		var rootRows=$('#datagrid').treegrid('getRoots'); //��ȡ���ڵ���Ϣ
		if(rootRows.length==0){
			$.messager.alert('��ʾ��', 'û������!', 'info');
			return;
		}
		for(var s=0;s<rootRows.length;s++){
			var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//��ȡ�ӽڵ���Ϣ
			$.each(rows, function(index, item){
				postStr+=item.matnr+"@@";
				postStr+=item.preps+"@@";
				postStr+=item.tpiwh+"@@";
				postStr+=item.tpowh+"@@";
				postStr+=item.finps_pre+"@@";
				postStr+=item.finps+"@@";
				postStr+=encodeURIComponent(item.productName)+"@@";
				postStr+=item.kbetr+"@@";
				postStr+=item.spart+"@@";
				postStr+=item.mvgr1+"##";
			}); 
		}
		
		if(checkIsKunnr()){
			var temp="-1";
			var datid=$('#hdDatid').val();
			$.ajax({
				type : "get",
				async : false,
				url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
				data : {kunnr:$('#hdKunnr').val(),datid:datid},
				success : function(obj) {
					temp=obj;
				}
			});
			if(temp=="0"){
				$.ajax({
					type : "post",
					async : false,
					url : appUrl + "/inOutStockAction!saveKunnrStock.jspa",
					data : {
						stockListStr:postStr,
						kunnr:$('#hdKunnr').val(),
						vkgrp:$('#hdVkgrp').val(),
						vkbur:$('#hdVkbur').val(),
						bzirk:$('#hdBzirk').val(),
						datid:$('#hdDatid').val(),
						bedat:$('#hdBedat').val(),
						endat:$('#hdEndat').val(),
						month:$('#hdMonth').val()
					},
					success : function(obj) {
						if(obj=="1"){
							if(type==2){
								$.messager.alert('', '�����ɹ�!', 'info',function(){window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#hdBzirk').val()+"&vkbur="+$('#hdVkbur').val()+"&vkgrp="+$('#hdVkgrp').val()+"&kunnr="+$('#hdKunnr').val();});
							}else{
								$.messager.alert('', '�����ɹ�!', 'info');
							}
						}else{
							$.messager.alert('��ʾ��', '����ʧ��!', 'info');
						}
					}
				});
			}else{
				$.messager.alert('��ʾ��', '�ͻ�����Ƭ������ˣ������ύ��', 'info');
			}
		}
		else{
			var temp="-1";
			var datid=$('#hdDatid').val();
			$.ajax({
				type : "get",
				async : false,
				url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
				data : {kunnr:$('#kunnr').combogrid('getValue'),datid:datid},
				success : function(obj) {
					temp=obj;
				}
			});
			if(temp=="0"){
				$.ajax({
					type : "post",
					async : false,
					url : appUrl + "/inOutStockAction!saveKunnrStock.jspa",
					data : {
						stockListStr:postStr,
						kunnr:$('#kunnr').combogrid('getValue'),
						vkgrp:$('#vkgrp').combogrid('getValue'),
						vkbur:$('#vkbur').combogrid('getValue'),
						bzirk:$('#bzirk').combogrid('getValue'),
						datid:$('#hdDatid').val(),
						bedat:$('#hdBedat').val(),
						endat:$('#hdEndat').val(),
						month:$('#hdMonth').val()
					},
					success : function(obj) {
						if(obj=="1"){
							//$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
							alert('�����ɹ�');
							if(type==2){
								window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#bzirk').combogrid('getValue')+"&vkbur="+$('#vkbur').combogrid('getValue')+"&vkgrp="+$('#vkgrp').combogrid('getValue')+"&kunnr="+$('#kunnr').combogrid('getValue');
							}
						}else{
							$.messager.alert('��ʾ��', '����ʧ��!', 'info');
						}
					}
				});
			}else{
				$.messager.alert('��ʾ��', '�ͻ�����Ƭ������ˣ������ύ��', 'info');
				return;
			}
		}
	}else{
		$.messager.alert('��ʾ��', '������Ч�����ڷ�Χ�ڣ���Ч���ᱨ����Ϊ'+$("#hdBeref").val()+'��'+$("#hdEnref").val()+'��', 'info');
		return;
	}
}


function _loadwaiting(){//��ʾ�ȴ���Ϣ  
    var wrap = $("#cc");  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:wrap.width(),height:wrap.height()}).appendTo(wrap);  
    $("<div class=\"datagrid-mask-msg\"></div>").html("�����У���ȴ�...").appendTo(wrap).css({display:"block",left:(wrap.width()-$("div.datagrid-mask-msg",wrap).outerWidth())/2,top:(wrap.height()-$("div.datagrid-mask-msg",wrap).outerHeight())/2});  
}  
function _removeloading(){//���صȴ���Ϣ  
    var wrap = $("#cc");  
    wrap.find("div.datagrid-mask-msg").remove();  
    wrap.find("div.datagrid-mask").remove();  
} 


function importSuccess(){
	if(confirm('����ɹ�������������Ҫ���������ϱ���')){
		if(checkIsKunnr()){
			window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#hdBzirk').val()+"&vkbur="+$('#hdVkbur').val()+"&vkgrp="+$('#hdVkgrp').val()+"&kunnr="+$('#hdKunnr').val();
		}else{
			window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#bzirk').combogrid('getValue')+"&vkbur="+$('#vkbur').combogrid('getValue')+"&vkgrp="+$('#vkgrp').combogrid('getValue')+"&kunnr="+$('#kunnr').combogrid('getValue');
		}
	}
}


