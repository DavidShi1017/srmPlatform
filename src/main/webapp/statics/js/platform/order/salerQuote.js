$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});
$('#clearV').click(function(){
	$('#customer_id').combogrid('clear');
	$('#endCustomer_id').combogrid('clear');
});
function loadGrid() {
	$('#datagrid').datagrid(
		{
			iconCls : 'icon-list',
			title : '',
			//height : 370,
			fit:true,
			striped : true,
			url : appUrl
			+ '/quote/quoteAction!getAuditQuoteList.jspa?states=(0,1,2,3,4,5,6,7,8)',
	        loadMsg : 'Loading...',
			singleSelect : true,
			nowrap : true,
			// idField : 'dictTypeId',
			pagination : true,
			rownumbers : true,
			fitColumns : false,
			frozenColumns:[[
					{
						field : 'ck',
						align : 'center',
						checkbox : true
					},
					{
						title : "ID",
						field : 'id',
						width : 80,
						hidden : true,
						align : 'center'
					},
					{
						field : 'flag',
						title : 'flag<br>ͬһ�����ű��',
						width : 100,
						align : 'center',
						hidden:true
					},
					{
						title : "Status",
						field : 'state',
						width : 70,
						align : 'center',
						formatter : function(value, row, rec) {
							var flag = row.state;
							if (flag == '9') {
								return "<font color='red'>Deleted</font>";
							}else if (flag == '0') {
								return "<font color='black'>Sales Pending</font>";
							}else if (flag == '1') {
								return "<font color='black'>Business Pending</font>";
							}else if (flag == '2') {
								return "<font color='black'>Director Pending</font>";
							}else if (flag == '3') {
								return "<font color='green'>Sales Approved</font>";
							}else if (flag == '4') {
								return "<font color='green'>Business Approved</font>";
							}else if (flag == '5') {
								return "<font color='green'>Director Approved</font>";
							}else if (flag == '6') {
								return "<font color='red'>Sales Reject</font>";
							}else if (flag == '7') {
								return "<font color='red'>Business Reject</font>";
							}else if (flag == '8') {
								return "<font color='red'>Director Reject</font>";
							} else{
								return flag;
							} 
						}
					},
					{
						title : "OrderID",
						field : 'main_id',
						width : 80,
						hidden : true,
						align : 'center'
					},
					{
						title : "Quote Num",
						field : 'quote_id',
						width : 80,
						align : 'center'
					},
					{
						field : 'material_name',
						title : 'BookPart<br>��������',
						width : 120,
						align : 'center',
						//hidden:true
					},
					{
						field : 'material_id',
						title : '12NC<br>���ϱ���',
						width : 100,
						align : 'center',
						formatter : function(value, row, rec) {
							var flag = row.material_id;
							if (flag == ''||flag==undefined) {
								return "";
							} else{
								var str = $.trim(flag);
								str = str.substring(str.length-12, str.length);
								return str;
							} 
						}
					},
					{
						field : 'drNum',
						title : 'DR Number<br>��Ŀע��ı���',
						width : 80,
						align : 'center',
				
					},
					{
						field : 'qty',
						title : 'QTY<br>��������',
						width : 80,
						align : 'center',
					} ,
					{
						field : 'target_cost',
						title : 'Target Cost <br>Ŀ������۸�', 
						width : 80,
						align : 'center',	
					} ,
					{
						field : 'target_resale',
						title : 'Target Resale<br>Ŀ�����ۼ۸�',
						width : 85,
						align : 'center',	
					} ,
					{
						field : 'sale_price',
						title : 'Regional Min <br>������ͼۼ۸�', 
						width : 80,
						align : 'center',	
						formatter : function(value, row, rec) {
							var flag = row.sale_price;
							if (flag == 0||flag == ''||flag==undefined) {
								return "<font color='red'>No Price</font>";
							} else{
								return flag*1;
							} 
						}
					} ,
				]],
			columns : [ [
						{
							title : "Disti",
							field : 'customer_name',
							width : 250,
							align : 'left'
						},
						{
							title : "Customer Group",
							field : 'cusGroup_id',
							width : 110,
							align : 'center',
							hidden:true
						},
						{
							title : "PC Group",
							field : 'pcGroup_id',
							width : 110,
							align : 'center',
						},
						{
							title : "PC",
							field : 'purchaseCustomer_name',
							width : 100,
							align : 'center'
						},
						{
							title : "EC Group",
							field : 'ecGroup_name',
							width : 100,
							align : 'center',
							hidden:true
						},
						{
							title : "End Customer",
							field : 'endCustomer_name',
							width : 150,
							align : 'left',
						},
					{
						title : "Project",
						field : 'project_name',
						width : 90,
						align : 'center'
					},
					{
						title : "Currency",
						field : 'currency_code',
						width : 90,
						align : 'center'
					},
					{
						field : 'suggest_cost',
						title : 'Suggest Cost <br>���������۸�', 
						width : 80,
						align : 'center',	
						editor: {type:'numberbox',options:{precision:4}},
					} ,
					{
						field : 'suggest_resale',
						title : 'Suggest Resale <br>�������ۼ�', 
						width : 80,
						align : 'center',	
						editor: {type:'numberbox',options:{precision:4}},
					} ,
					{
						field : 'cus_profits_percent',
						title : 'Disti Margin<br>�ͻ�����', 
						width : 90,
						align : 'center',	
						editor: {type:'text',options:{disabled:true}},
					} ,
					{
						field : 'profits_percent',
						title : 'Mfr Margin <br>��������', 
						width : 90,
						align : 'center',	
						editor: {type:'text',options:{disabled:true}},
					} ,
					{
						field : 'amount',
						title : 'Value <br>����Ŀ�ܼ�',
						width : 80,
						align : 'center',
						editor: {type:'numberbox',options:{precision:4}},
						//editor: {type:'numberbox',options:{precision:4,disabled:true}},
					} ,
					{
						field : 'cost',
						title : 'Cost <br>���ϳɱ�',
						width : 80,
						align : 'center',
						hidden:true
					} ,
					{
						field : 'reason',
						title : 'Justification<br>����ԭ��', 
						width : 100,
						align : 'center',	
					} ,
					{
						field : 'competitor',
						title : 'Competitor<br>��������',
						width : 150,
						align : 'center',
					},
					{
						field : 'start_dateStr',
						title : 'Start of Production<br>������������',
						width : 150,
						align : 'center',
					},		
					{
						field : 'cus_remark',
						title : 'Cus Remark<br>�ͻ����',
						width : 100,
						align : 'center',					
					},		
					{
						field : 'remark',
						title : ' Remark<br>�������',
						width : 100,
						align : 'center',
						editor:'text',					
					},
					] ],
			toolbar : [
//						           "-", {
//							text : 'Check',
//							iconCls : 'icon-view',
//							handler : function() {
//								check();
//								
//							}
//						} ,
			     "-", {
				text : 'Approve',
				iconCls : 'icon-ok',
				handler : function() {
					approve();
				}
			}, "-", {
				text : 'Reject',
				iconCls : 'icon-cancel',
				handler : function() {
					reject();
				}
			}, "-", {
				text : 'Escalation',
				iconCls : 'icon-up',
				handler : function() {
					escalation();
				}
			}
			 ],
			onClickCell: function (rowIndex, field, value) {
				var rows = $('#datagrid').datagrid('getRows');
				if(rows[rowIndex].state==0){					
					beginEditing(rowIndex, field, value);
					$('#datagrid').datagrid('beginEdit', rowIndex);
					setEditing(rowIndex); 					
				}
		     },
			onDblClickRow:function(rowIndex,rowData){
				initMaintWindow('Quote Detail','/quoteAction!toViewQuote.jspa?id='+rowData.main_id);
			},
			onSelect : function(rowIndex, rowData) {
				var state = rowData.state;
				 if(state=="0"){
					$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
					$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
					$('div.datagrid-toolbar a').eq(2).linkbutton('enable');
				}else{
					$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
					$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
					$('div.datagrid-toolbar a').eq(2).linkbutton('disable');
				}
			},
			rowStyler: function(index,row){
				var rows = $('#datagrid').datagrid('getRows');
				var quote_id=rows[0].quote_id;
				var flag=0;
				for(var i=0;i<rows.length;i++){
					if(rows[i].quote_id != quote_id){
						if(flag==0){
							flag=1;
						}else if(flag==1){
							flag=0;
						}
					}
					rows[i].flag=flag;
					quote_id=rows[i].quote_id;
				}
				if (row.flag==1){
					return 'background:#ffffcc;';
				}else{
					return 'background:#ffffff;';
				}
			}
		});
}


//��������Ŀ�ܼ�=��������*�����ɱ���
//�ͻ�����=(����resale*1-����cost*1)/����resale*1;;
//��������Mfr Margin= (Suggest Cost-���ϳɱ�)/ Suggest Cost
function setEditing(rowIndex){    
	var rows = $('#datagrid').datagrid('getRows');
	var regionalMin = rows[rowIndex].sale_price;
	var materialCost = rows[rowIndex].cost;
	var qty = rows[rowIndex].qty;
    var editors = $('#datagrid').datagrid('getEditors', rowIndex); 
    var weenCostEditor = editors[0]; 
    var weenResaleEditor = editors[1];   
    var cusProfitEditor = editors[2];
    var weenProfitEditor = editors[3]; 
    var amountEditor = editors[4];    
    weenCostEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }});  
    weenResaleEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }}); 
    amountEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }});  
    weenProfitEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }}); 
    cusProfitEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }}); 
    function calculate(rowIndex){  
    	var rows = $('#datagrid').datagrid('getRows');
    	var weenCost = weenCostEditor.target.val();
    	var weenResale = weenResaleEditor.target.val();
        var amount = weenCost*qty;  
    	var weenProfit = ((weenCost-materialCost)*1.0/weenCost*1.0)*100;
        var cusProfit = ((weenResale*1.00-weenCost*1.00)/weenResale*1.00)*100;
        weenProfit = weenProfit.toFixed(2);
        cusProfit = cusProfit.toFixed(2);
//        if(weenCost*1.0 < regionalMin*1.0){      	
//        	$(costEditor.target).numberbox('setValue','0'); 
//        }else{
//        	$(weenProfitEditor.target).val(weenProfit+'%');   
//         	$(cusProfitEditor.target).val(cusProfit+'%'); 
//         	$(amountEditor.target).val(amount);          	
//        }  
        if(weenResale*1<weenCost*1){
      	  $(weenResaleEditor.target).val(""); 
        }
    	$(weenProfitEditor.target).val(weenProfit+'%');   
     	$(cusProfitEditor.target).val(cusProfit+'%'); 
     	$(amountEditor.target).val(amount);   
//     	rows[rowIndex].amount=amount;
        if(weenProfit=='NaN'||weenProfit=='-Infinity'){
      	  $(weenProfitEditor.target).val(""); 
        }
        if(cusProfit=='NaN'||cusProfit=='-Infinity'){
        	  $(cusProfitEditor.target).val(""); 
         }
    }  
}    
//

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.quote_id = $("#quote_id").val();
	queryParams.customer_id = $("#customer_id").combobox('getValue');
	queryParams.endCustomer_id = $("#endCustomer_id").combobox('getValue');
	queryParams.project_name = encodeURIComponent($("#project_name").val());
	//queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}


function approve() {
	var rows1 = $('#datagrid').datagrid('getRows');
	for(var i=0;i<rows1.length;i++){
		$("#datagrid").datagrid("endEdit",i);
	} 	
	var rows = $('#datagrid').datagrid('getSelections');
	var quoteDetailJson=[];
	for(var i=0;i<rows.length;i++){
		if(rows[i].state!=0){//ֻ�ܴ��������״̬��
			continue;
		}
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].suggest_resale==0) {
			$.messager.alert('Tips', "�������ۼ�δ����߸�ʽ����ȷ��", 'error');
			return;
		}
		if (rows[i].suggest_cost==0) {
			$.messager.alert('Tips', "����������δ����߸�ʽ����ȷ��", 'error');
			return;
		}
	    if(rows[i].suggest_cost*1.0 < rows[i].sale_price*1.0){      	
				$.messager.alert('Tips', "���������۲��õ���������ͼۣ�", 'error');
				return;
	    }
	    if(rows[i].sale_price*1==0||rows[i].sale_price==undefined){      	
			$.messager.alert('Tips', "Please Contact Price Administrator to upload price��", 'error');
			return;
	    }
		var remark = rows[i].remark ==undefined?'':rows[i].remark; 
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
		+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(rows[i].profits_percent)
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark+"',remark:'"+remark+"',state:'3'}";
		
		quoteDetailJson.push(row);				
	}
	  $.messager.confirm('Confirm', '��˶���Ϣ,ȷ���ύ?', function(r) {
		if(r){		
			$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
			var form = window.document.forms[0];
			form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
}

function reject() {
	var rows1 = $('#datagrid').datagrid('getRows');
	for(var i=0;i<rows1.length;i++){
		$("#datagrid").datagrid("endEdit",i);
	} 	
	
	var rows = $("#datagrid").datagrid("getSelections");					
	
	var quoteDetailJson=[];
	for(var i=0;i<rows.length;i++){
		if(rows[i].state!=0){
			continue;
		}
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
//		if (rows[i].suggest_resale==0) {
//			$.messager.alert('Tips', "�������ۼ�δ����߸�ʽ����ȷ��", 'error');
//			return;
//		}
//		if (rows[i].suggest_cost==0) {
//			$.messager.alert('Tips', "����������δ����߸�ʽ����ȷ��", 'error');
//			return;
//		}
		var remark = rows[i].remark ==undefined?'':rows[i].remark; 
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
		+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(rows[i].profits_percent)
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark
		+"',remark:'"+remark+"',state:'6'}";
		
		quoteDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', '��˶���Ϣ,ȷ���ύ?', function(r) {
		if(r){						
			$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
			var form = window.document.forms[0];
			form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
}
 function escalation(){
		var rows1 = $('#datagrid').datagrid('getRows');
		for(var i=0;i<rows1.length;i++){
			$("#datagrid").datagrid("endEdit",i);
		} 	
		
		var rows = $("#datagrid").datagrid("getSelections");					
		
		var quoteDetailJson=[];
		for(var i=0;i<rows.length;i++){
			if(rows[i].state!=0){
				continue;
			}
			row_no = (i*1+1)*10;
			var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
//			if (rows[i].suggest_resale==0) {
//				$.messager.alert('Tips', "�������ۼ�δ����߸�ʽ����ȷ��", 'error');
//				return;
//			}
//			if (rows[i].suggest_cost==0) {
//				$.messager.alert('Tips', "����������δ����߸�ʽ����ȷ��", 'error');
//				return;
//			}
			var remark = rows[i].remark ==undefined?'':rows[i].remark; 
			var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
			+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
			+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
			+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
			+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
			+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(rows[i].profits_percent)
			+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
			+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark
			+"',remark:'"+remark+"',state:'1'}";
			
			quoteDetailJson.push(row);				
		}

		  $.messager.confirm('Confirm', '��˶���Ϣ,ȷ���ύ?', function(r) {
			if(r){						
				$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
				var form = window.document.forms[0];
				form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
				form.target = "hideFrame";
				form.submit();			  						
			}
		});	
 }
var editIndex = undefined;
function beginEditing (rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
             editIndex = rowIndex;
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
         }
    }
}
function endEditing () {
    if (editIndex == undefined) { return true; }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
 }


function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'δѡ�����ݣ�', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rows[0].id);
	}	
}


function initMaintAccount(ffit,widte,height,title,url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');

}


// �رմ���ҳ��
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}



//�����̿ͻ�
$('#customer_id').combogrid({
	panelHeight : 280,
	panelWidth : 350,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa?customer_code='+$('#customer_id').val(),
	idField : 'customer_code',
	textField : 'customer_name',
	columns : [[{
				field : 'customer_code',
				title : '�ͻ�����',
				width : 70,
				formatter : function(value, row, rec) {
					var flag = row.customer_code;
					if (flag == ''||flag==undefined) {
						return "";
					} else{
						var str = $.trim(flag);
						str = str.substring(str.length-6, str.length);
						return str;
					} 
				}
			}, {
				field : 'customer_name',
				title : '�ͻ�����',
				width : 250
			}]],
			toolbar : '#toolbarCustomer',
			onSelect : function(index, record){
		 	},
});
function searcherCustomer(name1) {
	var queryParams = $('#customer_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.customer_name = encodeURIComponent(name1);
	$('#customer_id').combogrid("grid").datagrid('reload');
} 
//�ն˿ͻ�
$('#endCustomer_id').combogrid({
	panelHeight : 280,
	panelWidth : 330,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	idField : 'end_customer_id',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_groupId',
				title : '�ͻ�����',
				width : 100
			}, {
				field : 'end_customer_name',
				title : '�ͻ�����',
				width : 200
			}]],
			toolbar : '#toolbarEndCustomer',
			onSelect : function(index, record) {
			},
});
function searcherEndCustomer(name1) {
	var queryParams = $('#endCustomer_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#endCustomer_id').combogrid("grid").datagrid('reload');
} 



document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
function utcToDate(utcCurrTime) {
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
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}	  