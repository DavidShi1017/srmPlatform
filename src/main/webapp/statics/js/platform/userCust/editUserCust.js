$(document) .ready( function() {
	empCodeBox();	
	customerCostBox();
	//$('#empCode').combogrid('setValue', $('#empCodes').val());
	$('#customerNumber').combogrid('setValue',$('#customerNumbers').val());
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		close();
		window.parent.location.reload();
	}
}

function submit() {

	var a = $("#empCode").combobox('isValid');
	var c = $("#customerNumber").combobox('isValid');
	var b = $("#beginDate").datebox('isValid');
	var d = $("#endDate").datebox('isValid');
	var id =  $("#id").val();
	if (!(a && b && c && d)) {
		 $.messager.alert('Tips', "��ʾ ������ȷ��д����Ϣ ��", 'warning');
		 return ;
	}
	var cust =$('#customerNumber').combobox('getText');
	var empCode = $('#empCode').combobox('getValue');
	
	if(id>0){
		var arr = cust.split(",");
		if(arr.length>1){
			$.messager.alert('Tips', "��ʾ ���޸�ʱ��ѡ��һ���ŵ� ��", 'warning');
			return ;
		}
	}
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/userCustAction!createUserCust.jspa",
		data : {
			id:$("#id").val(),
			customerNumber:cust,
			empCode:empCode,
			beginDate:$('#beginDate').datebox('getValue'),
			endDate:$('#endDate').datebox('getValue')
		},
		success : function(obj) { 
			var id =$("#id").val();
			if(obj>0){
				$.messager.alert('��ʾ��', '�����ɹ�!', 'info');
				window.parent.searchList();
				window.parent.closeMaterial();
//				if(!(id>0)){
//					$("#id").val(obj);
//				}
			}else if(obj<0){
				$.messager.alert('��ʾ��', '����������ϵͳ���Ѵ��ڣ��޷����棡', 'info');
			}else{
				$.messager.alert('��ʾ��', '����ʧ��!', 'info');
			}
		}
	}); 
}


document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};

function empCodeBox(){
	
	$('#empCode').combogrid({
		panelHeight : 280,
		panelWidth : 370,
		pagination : true,
		multiple : false,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/allUserAction!getUserInfoList.jspa?bhxjFlag=C&orgId=10000000&ran='+ Math.random(),
		idField : 'loginId',
		textField : 'userName',
		columns : [[{
					field : 'loginId',
					title : 'Ա�����',
					width : 100
				}, {
					field : 'userName',
					title : 'Ա������',
					width : 250
				}]],
		toolbar : '#toolbarEmpCode',
		onSelect : function(index, record) {
			 
		}
	});
	
}
//�ϼ������̲�ѯ��
function searcherEmp(name1) {
	var queryParams = $('#empCode').combogrid("grid").datagrid('options').queryParams;
	queryParams.loginId = encodeURIComponent(name1);
	$('#empCode').combogrid("grid").datagrid('reload');
}

 
function customerCostBox(){
	$('#customerNumber').combogrid({
		panelHeight : 280,
		panelWidth : 370,
		pagination : true,
		multiple : true,
		method : 'post',
		singleSelect : false,
		url : crmUrl + '/customerAction!customerSearch.jspa',
		checkbox:true,
		editable:true,
		idField : 'custNumber',
		textField : 'custNumber',
		columns : [[{
					field : 'ck',
					checkbox : true 
				},{
					field : 'custNumber',
					title : '�ŵ����',
					width : 100
				}, {
					field : 'custName',
					title : '�ŵ�����',
					width : 250
				}]],
		toolbar : '#toolbarCust',
		onSelect : function(index, record) {
			 
		}
	});
	if($('#id').val()>0){
		$('#customerNumber').combogrid({multiple : false,singleSelect : true});
	}
}
//�ϼ������̲�ѯ��
function searcherCust(name1) {
	var queryParams = $('#customerNumber').combogrid("grid").datagrid('options').queryParams;
	queryParams.custNumber = encodeURIComponent(name1);
	$('#customerNumber').combogrid("grid").datagrid('reload');
}
function close() {
	window.parent.closeMaterial();
}