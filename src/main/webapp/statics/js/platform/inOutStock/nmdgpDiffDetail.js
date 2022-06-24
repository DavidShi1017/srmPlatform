$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function loadGrid(){
	$("#datagrid").datagrid({
		columns:[[ 
			{field:'mvgr1_txt',title:'ϵ��',width:70,align:'center',rowspan:2},
			{field:'nmdgp',title:'�������<br/>��(��)',width:70,align:'center',rowspan:2},
			{field:'nmdgp_pri',title:'�������<br/>���(��Ԫ)',width:70,align:'center',rowspan:2},
			{title:'����Ŀ��',colspan:3},
			{title:'��սĿ��',colspan:3},
			{title:'����ԭ��',rowspan:2,width:200,field:'difftxt',editor:'text'}
		],[
			{field:'jbmb_pri',title:'����Ŀ��<br/>(��Ԫ)',align:'right',width:70},
			{field:'jbmb_diff',title:'<a title="�����=����������-����Ŀ����" style="color:blue;text-decoration:underline;">�����<br>(��Ԫ)</a>',align:'center',width:70},
			{field:'jbmb_cyl',title:'<a title="������=������/����Ŀ����*100%" style="color:blue;text-decoration:underline;">������</a>',align:'center',width:60	},
			{field:'tzmb_pri',title:'����Ŀ��<br/>(��Ԫ)',align:'right',width:70},
			{field:'tzmb_diff',title:'<a title="�����=����������-����Ŀ����"  style="color:blue;text-decoration:underline;">�����<br>(��Ԫ)</a>',align:'center',width:70},
			{field:'tzmb_cyl',title:'<a title="������=������/����Ŀ����*100%" style="color:blue;text-decoration:underline;">������</a>',align:'center',width:60}

		]],
		method: 'get',
		singleSelect: true,
		border:false,
		fit:true,
		rownumbers: true,
		onClickRow: onClickRow,
		url:appUrl+ '/inOutStockAction!getNmdgpDiffDetailJson.jspa?ran='+Math.random(),
		queryParams: {reaid:$('#hdReaid').val()}
	});
}

var editIndex = undefined;
function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			$('#datagrid').datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#datagrid').datagrid('selectRow', editIndex);
		}
	}
}
function endEditing(){
	if (editIndex == undefined){return true;}
	if ($('#datagrid').datagrid('validateRow', editIndex)){
		$('#datagrid').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function searchGrid(){
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

function saveVkgrpDiff(){
	endEditing();
	var msg=checkVkgrpDiff();
	if(msg==""){
		var rows=$('#datagrid').datagrid('getRows'); //��ȡ���ڵ���Ϣ
		var diffList = [];
		$.each(rows, function(index, item){
			if(item.mvgr1!=""){
				var obj = {};
				obj.datid=$('#hdDatid').val();
				obj.vkgrp=$('#hdVkgrp').val();
				obj.vkbur="";
				obj.bzirk="";
				obj.spart=item.spart;
				obj.spart_txt=encodeURIComponent(item.spart_txt);
				obj.mvgr1=item.mvgr1;
				obj.mvgr1_txt=encodeURIComponent(item.mvgr1_txt);
				obj.jbmb_pri=item.jbmb_pri;
				obj.tzmb_pri=item.tzmb_pri;
				obj.difftxt=encodeURIComponent(item.difftxt);
				obj.reaid=$('hdReaid').val();
				obj.nmdgp=item.nmdgp;
				obj.nmdgp_pri=item.nmdgp_pri;
				diffList.push(obj);
			}
		});
		var reason=$('#reason').val();
		$.ajax({
			type : "post",
			async : false,
			url : appUrl + "/inOutStockAction!saveVkgrpDiff.jspa",
			data : {
				diffList:JSON.stringify(diffList),
				reason:encodeURIComponent(reason),
				reaid:$('hdReaid').val(),
				datid:$('#hdDatid').val()
			},
			success : function(obj) {
				if(obj=="1"){
					alert('�����ɹ���');
					location.href=appUrl+"/inOutStockAction!vkgrpAudit.jspa";
				}else if("0"){
					$.messager.alert('��ʾ��', '��������ˣ������ٱ���', 'info');
				}else{
					$.messager.alert('��ʾ��', '����ʧ��!', 'info');
				}
			}
		});
	}else{
		alert(msg);
	}
}

function checkVkgrpDiff(){
	var a="";
	var rows=$('#datagrid').datagrid('getRows'); //��ȡ���ڵ���Ϣ
	$.each(rows, function(index, item){
		if(item.mvgr1!=""){
			
			var planPri=item.nmdgp_pri;
			var jbmb=item.jbmb_pri;
			var difftxt=item.difftxt;

			var b=0;
			if(jbmb>0){
				b=(planPri-jbmb)/jbmb;
			}
			if(b>0.1||b<-0.1){
				if(difftxt.length==0){
					a+=item.mvgr1_txt+"����ԭ��δ��д,\n";
				}
			}	
		}
	});
	
	if(a.length>0){
		a=a+"������ϵ�в���ԭ��";
	}
	var reason=$('#reason').val();
	if(a==""&&reason.length==0){
		a="�������ԭ��δ��д";
		$('#cc').layout('expand','south'); 
		$('#reason').focus();
	}
	
	return a;
}

(function($){
	 var buttonDir = {north:'down',south:'up',east:'left',west:'right'};
	    $.extend($.fn.layout.paneldefaults,{
	        onBeforeCollapse:function(){
	            /**/
	            var popts = $(this).panel('options');
	            var dir = popts.region;
	            var btnDir = buttonDir[dir];
	            if(!btnDir) return false;
	 
	            setTimeout(function(){
	                var pDiv = $('.layout-button-'+btnDir).closest('.layout-expand').css({
	                    textAlign:'center',lineHeight:'18px',fontWeight:'bold'
	                });
	 
	                if(popts.title){
	                    var vTitle = popts.title;
	                    if(dir == "east" || dir == "west"){
	                        var vTitle = popts.title.split('').join('<br/>');
	                        pDiv.find('.panel-body').html(vTitle);
	                    }else{
	                        $('.layout-button-'+btnDir).closest('.layout-expand').find('.panel-title')
	                        .css({textAlign:'left'})
	                        .html(vTitle)
	                    }
	                     
	                }   
	            },100);
	             
	        }
	    });
	})(jQuery);