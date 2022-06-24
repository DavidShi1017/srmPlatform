var globalStringSyMoneyId = null;
var globalStringSyMoney = null;
$(document).ready(function() {

	$('#hideFrame').bind('load', promgtMsg);	
 	loadKunnrReceivePayDetail();
	
	/*
	$('#kunnr').combogrid('textbox').keydown(function (e) {
  		var theEvent = e || window.event;
  		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
         if(code == '13')    
         {
            newCodeKunnr($('#kunnr').combogrid('textbox').val());
         }
     });*/
});
 
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	alert(failResult);
	alert(successResult);
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else{
		$.messager.alert('Tips', successResult, 'info',function(){
			window.parent.closeWerk();
			window.parent.loadGrid();
 		});
	}
}
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}

function close() {
	window.parent.closeWerk();
	window.parent.loadGrid();
}




function loadKunnrReceivePayDetail(){
	
	 
	 
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				url : appUrl
				+ '/posAction!getReceivePosListB.jspa?ran='
						+ Math.random(),
				queryParams:{
					'branchId':$('#dist_name').val(),
					'disti_branch':$('#disti_branch').val(),
					'debitsOrRedits':$('#debitsOrRedits').val(),
					'currency_name':$('#currency_code').combobox('getValue'),
					},
				loadMsg : 'load...',
				singleSelect : false,
				pagination : true,
				nowrap : true,
				rownumbers : true,
				fit:true,
//				pageSize:20,
				columns : [ [
						{
							field : 'ck',
							checkbox : true
						},{
							field : 'id',
							title : '序号',
							align : 'center',
							hidden:true,
							width : 100
						},{
							field : 'transaction_code',
							title : 'Status',
							align : 'center',
							width : 100,
							hidden:true,
						},{
							field : 'disti_name',
							title : 'Disti Name ',
							align : 'center',
							//hidden:true,
							width : 100
						},{
							field : 'disti_branch',
							title : 'Paycode ',//同步sap的Branchid
							align : 'center',
							//hidden:true,
							width : 70
						},{
							field : 'disti_claimnbr',
							title : 'disti claimnbr',//同步sap的Branchid
							align : 'center',
							//hidden:true,
							width : 70
						},{
							field : 'ship_to',
							title : 'ship_to ',//同步sap的Branchid
							align : 'center',
							hidden:true,
							width : 100
						},{
							field : 'payer_to',
							title : 'payer_to ',//同步sap的Branchid
							align : 'center',
							hidden:true,
							width : 100
						},{
							field : 'billing_to',
							title : 'Billing_to ',//同步sap的Branchid
							align : 'center',
							hidden:true,
							width : 100
						},{
							field : 'book_part',
							title : 'Book Part ',
							align : 'left',
							width : 150
						},{
							field : 'ship_date',
							title : 'Ship Date',
							align : 'center',
							width : 80
						},{
							field : 'ship_qty',
							title : 'SHIP QTY ',
							align : 'center',
							width : 80,
							formatter : function(value, row, rec) {
									return formatNumber(value,0,1);
								}
						},{
							field : 'remainQty',
							title : 'Remain QTY ',
							align : 'center',
							width : 100,
							hidden:true,
							formatter : function(value, row, rec) {
 								return "<font color='red'>"+row.remainQty+"</font>";
 							}
						}
						,{
							field : 'debit_number',
							title : 'QUOTE NUM ',
							align : 'center',
							width : 80
						}
						,{
							field : 'disti_invoice_nbr',
							title : 'INVOICE ',
							align : 'left',
							width : 100
						}
						,{
							field : 'disti_invoice_item_number',
							title : 'INVOICE item number ',
							align : 'center',
							//hidden:true,
							width : 100
						}
//						,{
//							field : 'disti_bookcost',
//							title : 'Buy Price USD ',//
//							align : 'center',
//							width : 100 
//							
//						} ,{
//							field : 'disti_cost',
//							title : 'FINAL_PRICE ',//
//							align : 'center',
//							width : 100
//						}
						,{
							field : 'cost_denom',
							title : 'Cost ',//用于计算价差DEBIT PRICE = Cost - DBC
							align : 'center',
							width : 80 							
						} ,{
							field : 'dbc_denom',
							title : 'DBC ',//用于计算价差DEBIT PRICE = Cost - DBC
							align : 'center',
							width : 80
						}
						,{
						
							field : 'rebatePrice',
							title : 'DEBIT PRICE ',
							align : 'center' ,
							width : 80
						}
							,{
								field : 'disti_cost_currency',//同步sap的currency
								title : 'CURRENCY ',
								align : 'center',
								width : 100,
								//hidden:true
							}
						,{	field : 'rebateTotal',
							title : 'PRICE TOTAL ',
							align : 'center' ,
							width : 100
						},{
							field : 'Remarks',
							title : 'Remarks ',
							align : 'center' ,
							width : 100,
							hidden:true
						},{
							field : 'purchasing_customer_name',
							title : 'PC Name ',
							align : 'left',
							width : 150
 						},{
							field : 'endcust_name',
							title : 'EC Name ',
							align : 'left',
							width : 150	
 						},{
							field : 'quote_totalqty',
							title : 'quote_totalqty ',
							hidden:true,
							align : 'center',
							width : 100	
 						},{
							field : 'm_12nc',
							title : 'm_12nc ',
							hidden:true,
							align : 'center',
							width : 100	
 						},{
							field : 'rebateOver',
							title : 'rebateOver ',
							hidden:true,
							align : 'center',
							width : 100	
 						}] ],
// 						toolbar : [  "-", {
//							text : 'Create',
//							iconCls : 'icon-excel',
//							handler : function() {
//								confirmRepayment();
//							}
//						},      "-", {
//							text : 'Export Excel',
//							iconCls : 'icon-excel',
//							handler : function() {
//								outport();
//							}
//						}, 
//						"-", {
//							text : 'Check',
//							iconCls : 'icon-excel',
//							handler : function() {
//								check();
//							}
//						}
//						], 
						
// 						onLoadSuccess : function(data) {
// 							var repaymentDetails = $('#datagrid').datagrid('getRows');  //getRows
//  							var record=0;
// 							for(var i=0;i<repaymentDetails.length;i++){
// 								record=record*1+(repaymentDetails[i].rebateTotal)*1;  	
//  							}
// 							$('#rebate_total').val(record);
// 							
// 						},
//						onSelect : function(rowIndex, rowData) { 					
//							var record=$('#rebate_total').val()*1;
//								record=record*1+(rowData.rebateTotal)*1;  	
//							$('#rebate_total').val(record);
//						},
//						onUnselect :function(rowIndex, rowData) {
//							var record=$('#rebate_total').val()*1;
//							record=record*1 - (rowData.rebateTotal)*1;  	
//							$('#rebate_total').val(record);
//						},
						onSelectAll:function(rowData){
							
						}
//                            
							
			});
}


//还款确认

function confirmRepayment(){
	
	
	$('#repayBtn1').linkbutton('disable');
	setTimeout(function() { $('#repayBtn1').linkbutton("enable"); }, 10000);
	
	
	
	var id;
	var book_part;
	var remainQty;
	var ship_qty;	
	var debit_number;		
	var material_id;	
	var rebatePrice;	
	var quote_totalqty;		
	var rebateTotal; 
	var disti_branch;
	var disti_cost_currency;
	var repaymentDetailJson = [];
	
 	var repaymentDetails = $('#datagrid').datagrid('getSelections');  //getRows
	if(repaymentDetails==undefined ||repaymentDetails.length==null||repaymentDetails.length==0 ){
		$.messager.alert('Tips',"No data can be operated!", 'warning');
		return;
	}
	
	var row_no;
	var record=1;
	
	//暂时屏蔽（只传一行，金额为表头）
	for(var i=0;i<repaymentDetails.length;i++){
		row_no = (i*1+1)*10;
		id = repaymentDetails[i].id;
		book_part = repaymentDetails[i].book_part;
		remainQty = repaymentDetails[i].remainQty;
		debit_number = repaymentDetails[i].debit_number;			
		material_id = repaymentDetails[i].m_12nc;		
		rebatePrice = repaymentDetails[i].rebatePrice;		
		quote_totalqty = repaymentDetails[i].quote_totalqty;	
		ship_qty = repaymentDetails[i].ship_qty;	
		ship_to=repaymentDetails[i].ship_to;
		payer_to=repaymentDetails[i].payer_to;
		billing_to=repaymentDetails[i].billing_to;
		disti_cost_currency=repaymentDetails[i].disti_cost_currency;
		disti_branch = repaymentDetails[i].disti_branch;
		var disti_claimnbr = "";
		if (repaymentDetails[i].disti_claimnbr==null && repaymentDetails[i].disti_claimnbr==undefined){
			disti_claimnbr ="";
		}else{
			disti_claimnbr = repaymentDetails[i].disti_claimnbr;
		}
		rebateTotal=$('#rebate_total').val(); 
		var row  = "{row_no:'"+row_no
		+"',id:'"+id
		+"',book_part:'"+book_part
		+"',remainQty:'"+remainQty
		+"',debit_number:'"+debit_number
		+"',m_12nc:'"+material_id
		+"',rebatePrice:'"+rebatePrice
		+"',disti_branch:'"+disti_branch
		+"',disti_claimnbr:'"+disti_claimnbr
		+"',disti_cost_currency:'"+disti_cost_currency
		+"',ship_qty:'"+ship_qty
		+"',ship_to:'"+ship_to
		+"',payer_to:'"+payer_to
		+"',billing_to:'"+billing_to
		+"',quote_totalqty:'"+quote_totalqty+"'}";
			
		repaymentDetailJson.push(row);
	}
		
	 
	repaymentDetailJson = '['+repaymentDetailJson+']';
	
	var win = $.messager.progress({
		title:'Please waiting',
		msg:'create rebate order data...'
		});
	
	$.ajax({
		url:appUrl + "/posToSapAction!getPosToSAPB.jspa",
		data:{'repaymentDetailJson':repaymentDetailJson},
		type:"POST",
//		async : false,
		contentType:'application/x-www-form-urlencoded;charset=UTF-8', 
//		beforeSend:function(data){
//			$.messager.progress({
//	 			text:'create rebate order data...'
//			});
//		},
		success: function(ss){			
			$.messager.progress('close');
			var msg =ss.result+"";
 			if(msg==undefined||msg == null || msg == ''){
 				$.messager.alert('Tips', "Failed!" , 'warning');
			}else{
				if(msg.lastIndexOf("Failed")>-1){
					$.messager.alert('Tips', msg, 'warning');
				}else{
					$.messager.alert('Tips', msg, 'info',function(){
						$("#datagrid").datagrid('load');
			 		});				
				}
			}
 	    },
 	    error : function(r) {
			$.messager.progress('close');
			$.messager.alert('Tips', "create rebate order data Failure", 'info');
		}
	});
}

function outport(){
	$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/posAction!downloadExcelForRebateOrder.jspa';
 			form.target = "hideFrame";
			form.submit();
		}
	});
}

  function endEditing () {
    if (editIndex == undefined) { return true; }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        
        $('#datagrid').datagrid('endEdit', editIndex).datagrid('refreshRow', editIndex); 
		
        
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

//货币
  $('#currency_code').combobox({
  	url : appUrl
  			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
  	valueField : 'itemValue',
  	textField : 'itemName',
  	multiple : false,
  	editable : false,
  	panelHeight : 120,
  	width : 170,
  	onSelect : function(r){
  		
   	} 
  });
  
  
/*  
//代理商客户
  $('#branch_id').combogrid({
  	panelHeight : 280,
  	panelWidth : 320,
  	width:250,
  	pagination : true,
  	pageSize:10,
  	multiple : false,
  	editable : false,
  	method : 'post',
  	singleSelect : true,
  	url : appUrl + '/customer/customerAction!getCustomerList.jspa?customer_code='+$('#customer_code').val(),
  	idField : 'customer_code',
  	textField : 'customer_name',
  	columns : [[{
  				field : 'customer_code',
  				title : 'CustomerCode',
  				width : 100,
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
  				title : 'CustomerName',
  				width : 200
  			},
  			{
  				field : 'country',
  				title : 'city/country',
  				width : 80,
  				//hidden:true
  			}]],
  			toolbar : '#toolbarCustomer',
  			onSelect : function(index, record){
  				$('#currency_code').val(record.currency_code);
  				$('#dist_name').val(record.customer_name);
  				$('#currency_name').val(record.currency_code);

  				//送达方
  				$('#ship_to').combogrid({
  					panelHeight : 280,
  					panelWidth : 320,
  					pagination : true,
  					pageSize:10,
  					multiple : false,
  					editable : false,
  					method : 'post',
  					singleSelect : true,
//  					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+''+'&partnerType=WE',
  					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=WE',
  					idField : 'partnerId',
  					textField :'address',
  					columns : [[{
  								field : 'partnerId',
  								title : '代码',
  								width : 100,
  								formatter : function(value, row, rec) {
  									var flag = row.partnerId;
  									if (flag == ''||flag==undefined) {
  										return "";
  									} else{
  										var str = $.trim(flag);
  										str = str.substring(str.length-6, str.length);
  										return str;
  									} 
  								}
  							}, {
  								field : 'address',
  								title : '地址',
  								width : 200
  							}]],
  							onLoadSuccess:function(){
  								var rows =  $('#ship_to').combogrid("grid").datagrid('getRows');

  								if(rows!=undefined && rows.length!=0){
  									 $('#ship_to').combogrid("grid").datagrid('selectRow',0);
  								}
  							}
  				});
  				//付款方
  				$('#payer_to').combogrid({
  					panelHeight : 280,
  					panelWidth : 320,
  					pagination : true,
  					pageSize:10,
  					multiple : false,
  					editable : false,
  					method : 'post',
  					singleSelect : true,
//  					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+''+'&partnerType=RG',
  					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=RG',
  					idField : 'partnerId',
  					textField :'partnerName',
  					columns : [[{
  								field : 'partnerId',
  								title : '代码',
  								width : 100,
  								formatter : function(value, row, rec) {
  									var flag = row.partnerId;
  									if (flag == ''||flag==undefined) {
  										return "";
  									} else{
  										var str = $.trim(flag);
  										str = str.substring(str.length-6, str.length);
  										return str;
  									} 
  								}
  							}, {
  								field : 'partnerName',
  								title : '名称',
  								width : 200
  							}]],
  							onLoadSuccess:function(){
  								var rows =  $('#payer_to').combogrid("grid").datagrid('getRows');

  								if(rows!=undefined && rows.length!=0){
  									 $('#payer_to').combogrid("grid").datagrid('selectRow',0);
  								}
  								
  							}
  				});
  				//开票方
  				$('#billing_to').combogrid({
  					panelHeight : 280,
  					panelWidth : 320,
  					pagination : true,
  					pageSize:10,
  					multiple : false,
  					editable : false,
  					method : 'post',
  					singleSelect : true,
//  					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+''+'&partnerType=RE',
  					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=RE',
  					idField : 'partnerId',
  					textField :'nameAddress',
  					columns : [[{
  								field : 'partnerId',
  								title : '代码',
  								width : 100,
  								formatter : function(value, row, rec) {
  									var flag = row.partnerId;
  									if (flag == ''||flag==undefined) {
  										return "";
  									} else{
  										var str = $.trim(flag);
  										str = str.substring(str.length-6, str.length);
  										return str;
  									} 
  								}
  							}, {
  								field : 'partnerName',
  								title : '名称',
  								width : 200
  							}, {
  								field : 'address',
  								title : '名称',
  								width : 200
  							}]],
  							onLoadSuccess:function(){
  								var rows =  $('#billing_to').combogrid("grid").datagrid('getRows');

  								if(rows!=undefined && rows.length!=0){
  									 $('#billing_to').combogrid("grid").datagrid('selectRow',0);
  								}	
  							}
  				});
  		 	},
		 	onChange : function(newValue, oldValue) {
		 		if(oldValue!=undefined && oldValue!=""){
		 			$('#ship_to').combogrid('setValue','');
		 			$('#payer_to').combogrid('setValue','');
		 			$('#billing_to').combogrid('setValue','');		 			
		 		}
		 	}
  });
  function searcherCustomer(name1) {
  	var queryParams = $('#branch_id').combogrid("grid").datagrid('options').queryParams;
  	queryParams.search = encodeURIComponent(name1);
  	$('#branch_id').combogrid("grid").datagrid('reload');
  } 
  
  */