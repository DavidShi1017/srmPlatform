$(document).ready(function() {
	$('#material_id').html($('#material_id').html().replace( /^0*/, ''));
	$('#hideFrame').bind('load', promgtMsg);	
	$('#material_id').html($('#material_id').html());
	
	
	
});


function cancel() {
	window.parent.closeMaintWindow();
}

//����
$('#material_groupId').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/dictAction!getCmsTbDictJsonList.jspa?dictTypeId=544',
	idField : 'itemValue',
	textField : 'itemName',
	columns : [[{
				field : 'itemValue',
				title : 'Code',
				width : 100
			}, {
				field : 'itemName',
				title : 'Name',
				width : 120
			}]],
});





document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if(event.keyCode == 8) {
	     // �������textarea�ڲ�ִ���κβ���
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // �����readOnly����disable��ִ���κβ���
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};

$.extend($.fn.validatebox.defaults.rules, {  
    /*�����ĳ���ֶ����*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ��'
    }
});