var currentDate = getNowFormatDate();
var debit_endStr_oldHtml;
$(document).ready(function() {
    loadGrid();
    $('#hideFrame').bind('load', promgtMsg);
});

if ($('#forWho').val() == "Disti") {
    $('#cusGroup_id').attr('disabled', 'disabled');
}
$('#clearV').click(function() {
    $('#endCustomer_id').combogrid('clear');
    $('#purchaseCustomer_id').combogrid('clear');
    $('#start_dateStr').datebox('setValue', "");
    $('#end_dateStr').datebox('setValue', "");
});
function loadGrid() {
    var noCMM = false;
    var isDisti = false;
    if ($('#roleId').val() == 'JXS') {
        isDisti = true;
    }
    if ($('#roleId').val() == 'JXS' || $('#roleId').val() == "HK10_H_Sale_Mgmt") {
        noCMM = true;
    }
    else if ($('#roleControl').val() == '0') {
    	noCMM = true;
    }
    else {
    	noCMM = false;
    }
    
    $('#datagrid')
            .datagrid(
                    {
                        iconCls : 'icon-list',
                        title : '',
                        fit : true,
                        striped : true,
                        queryParams : {
                            states : $('#state').val(),
                            isAgrees : $('#isAgree').val(),
                            myself : $('#myself').val(),
                        },
                        loadMsg : 'Loading...',
                        singleSelect : true,
                        nowrap : true,
                        // idField : 'id',
                        pagination : true,
                        rownumbers : true,
                        fitColumns : false,
                        frozenColumns : [ [
                                {
                                    field : 'ck',
                                    align : 'center',
                                    checkbox : true
                                },
                                {
                                    title : "Agreement",
                                    field : 'isAgree',
                                    width : 70,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        /**
                                         * ?????? 0????????????1??????
                                         */
                                        var flag = row.isAgree;
                                        if (flag == '0') {
                                            return "";
                                        } else if (flag == '1') {
                                            return "<font color='green'>Agree</font>";
                                        } else if (flag == '2') {
                                            return "<font color='gray'>Expired</font>";
                                        }
                                    },
                                },
                                {
                                    title : "Quote status",
                                    field : 'state',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.state;
                                        if (flag == '9') {
                                            return "<font color='red'>Deleted</font>";
                                        } else if (flag == '0') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '1') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '2') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '3') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '4') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '5') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '6') {
                                            return "<font color='red'>Reject</font>";
                                        } else if (flag == '7') {
                                            return "<font color='red'>Reject</font>";
                                        } else if (flag == '8') {
                                            return "<font color='red'>Reject</font>";
                                        } else {
                                            return flag;
                                        }
                                    }
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
                                    title : 'flag',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
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
                                    align : 'center',
                                },
                                {
                                    field : 'debit_num',
                                    title : 'Debit Num',
                                    width : 120,
                                    align : 'center',
                                },

                                {
                                    field : 'material_id',
                                    title : '12NC',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.material_id;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            var str = $.trim(flag);
                                            str = str
                                                    .substring(str.length - 12,
                                                            str.length);
                                            return str;
                                        }
                                    }
                                }, {
                                    field : 'material_name',
                                    title : 'BookPart',
                                    width : 120,
                                    align : 'left',
                                },

                        ] ],
                        columns : [ [
                                {
                                    field : 'isDRItem',
                                    title : 'DR Indicator',
                                    width : 80,
                                    align : 'center',
                                },
                                {
                                    field : 'drNum',
                                    title : 'DR Number',
                                    width : 80,
                                    align : 'center',

                                },
                                {
                                    field : 'qty',
                                    title : 'QTY',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.qty;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            return formatNumber(flag * 1, 0, 1);
                                        }
                                    }
                                },
                                {
                                    field : 'res_qty',
                                    title : 'Remain QTY',
                                    width : 85,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.res_qty;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            return formatNumber(flag, 0, 1);
                                        }
                                    },
                                },
                                {
                                    field : 'target_cost',
                                    title : 'Target Cost',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.target_cost;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'target_resale',
                                    title : 'Target Resale',
                                    width : 95,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.target_resale;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined
                                                || row.target_resale == 0) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'target_margin',
                                    title : 'Target Margin',
                                    width : 95,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.target_margin;
                                        if (flag == '' || flag == undefined
                                                || row.target_resale == 0) {
                                            return "";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            flag = flag.toFixed(2) + "%";
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'sale_price',
                                    title : 'Regional Min',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.sale_price;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                    hidden : isDisti
                                },
                                {
                                    field : 'stop_price',
                                    title : 'CMM',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.stop_price;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                    hidden : noCMM

                                },
                                {
                                    title : "Price Region",
                                    field : 'price_region',
                                    width : 90,
                                    align : 'center',
                                    hidden : isDisti
                                },
                                {
                                    title : "Disti",
                                    field : 'cusGroup_id',
                                    width : 150,
                                    align : 'left'
                                },
                                {
                                    title : "Disti Branch",
                                    field : 'disti_branch',
                                    width : 250,
                                    align : 'left'
                                },
                                {
                                    title : "Disti Region",
                                    field : 'disti_region',
                                    width : 80,
                                    align : 'center',
                                    hidden : isDisti
                                },
                                {
                                    title : "PC Group",
                                    field : 'pcGroup_id',
                                    width : 110,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    title : "PC Group",
                                    field : 'pcGroup_name',
                                    width : 110,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    title : "purchasecustomer id",
                                    field : 'purchasecustomer_id',
                                    width : 150,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    title : "Purchasing Customer",
                                    field : 'purchaseCustomer_name',
                                    width : 150,
                                    align : 'center'
                                },
                                {
                                    title : "PC Region",
                                    field : 'pc_region',
                                    width : 80,
                                    align : 'center',
                                    hidden : isDisti
                                },
                                {
                                    title : "PC LOCATION",
                                    field : 'pc_city',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "PC ZIP CODE",
                                    field : 'pc_zip_code',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "PC STATE",
                                    field : 'pc_state',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "EC Group",
                                    field : 'ecGroup_name',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    title : "End Customer",
                                    field : 'endCustomer_name',
                                    width : 150,
                                    align : 'left',
                                },
                                {
                                    title : "EC Region",
                                    field : 'ec_region',
                                    width : 80,
                                    align : 'center',
                                    hidden : isDisti
                                },
                                {
                                    title : "EC LOCATION",
                                    field : 'ec_city',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "EC ZIP CODE",
                                    field : 'ec_zip_code',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "EC STATE",
                                    field : 'ec_state',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "PROJECT",
                                    field : 'project_name',
                                    width : 90,
                                    align : 'center'
                                },

                                {
                                    title : "CURRENCY",
                                    field : 'currency_code',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    field : 'pbMpp',
                                    title : 'PB/MPP',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.pbMpp;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                    hidden : isDisti
                                },
                                {
                                    field : 'suggest_cost',
                                    title : 'Final Quoted Cost',
                                    width : 120,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.suggest_cost;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'suggest_resale',
                                    title : 'Final Quoted Resale',
                                    width : 150,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.suggest_resale;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'cus_profits_percent',
                                    title : 'Final Quoted Disti Margin',
                                    width : 170,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.cus_profits_percent;
                                        if (flag == '' || flag == undefined
                                                || flag == 'undefined') {
                                            return "";
                                        } else {

                                            return flag + "%";
                                        }
                                    }
                                },
                                {
                                    field : 'profits_percent',
                                    title : 'Mfr Margin',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.profits_percent;
                                        if (flag == '' || flag == undefined
                                                || flag == 'undefined') {
                                            return "";
                                        } else {

                                            return flag + "%";
                                        }
                                    },
                                    hidden : noCMM
                                },
                                {
                                    field : 'amount',
                                    title : 'Final Quoted Amount',
                                    width : 150,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        return formatNumber(value * 1, 0, 1);
                                    }
                                },
                                {
                                    field : 'cost',
                                    title : 'Cost ',
                                    width : 80,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    field : 'create_user',
                                    title : 'Creater',
                                    width : 120,
                                    align : 'center',

                                },
                                {
                                    field : 'create_time',
                                    title : 'Create Date',
                                    width : 120,
                                    align : 'center',
                                    formatter : function(date) {
                                        return utcToDate(date);
                                    }
                                },

                                {
                                    field : 'start_dateStr',
                                    title : 'Effective Date',
                                    width : 120,
                                    align : 'center',
                                },
                                {
                                    field : 'end_dateStr',
                                    title : 'Expire Date',
                                    width : 120,
                                    align : 'center',
                                },

                                {
                                    field : 'remark',
                                    title : 'Internal Comments',
                                    width : 150,
                                    align : 'left',
                                    formatter : function(value, row, rec) {
                                        var flag = row.remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else if (flag.length >= 30) {
                                            flag = flag.substring(0, 24);
                                            flag += "...";
                                            return flag;
                                        } else {
                                            return flag;
                                        }
                                    },
                                    hidden : isDisti
                                },
                                {
                                    field : 'data_from',
                                    title : 'Data Source',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.data_from;
                                        if (flag == '2') {
                                            return "<font color='blue'>EDI</font>";
                                        } else {
                                            return "<font color='black'>NonEDI</font>";
                                        }
                                    },
                                    hidden : true
                                },
                                {
                                    field : 'debit_startStr',
                                    title : 'Debit Start',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'debit_endStr',
                                    title : 'Debit End',
                                    width : 100,
                                    align : 'center',
                                    editor : {
                                        type : 'datebox',
                                        options : {// 1
                                            onSelect : function(d) {
                                                var aa = $(this).datebox(
                                                        'getValue');
                                                if (debit_endStr_oldHtml < aa) {
                                                    $.messager
                                                            .alert(
                                                                    'Tips',
                                                                    'The Debit End cannot be later than the orginal debit end date???',
                                                                    'warning');
                                                    $(this)
                                                            .datebox(
                                                                    'setValue',
                                                                    debit_endStr_oldHtml);
                                                    return;
                                                }
                                                var sd = new Date();
                                                sd.setHours(0, 0, 0, 0);
                                                var endDay = sd
                                                        .format('yyyy-MM-dd');
                                                if (debit_endStr_oldHtml < endDay) {
                                                    $(this)
                                                            .datebox(
                                                                    'setValue',
                                                                    debit_endStr_oldHtml);
                                                    $.messager
                                                            .alert(
                                                                    'Tips',
                                                                    'The Debit End must later than the current date???',
                                                                    'warning');
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                },
                                {
                                    field : 'cus_remark',
                                    title : ' Comments',
                                    width : 150,
                                    align : 'left',
                                    formatter : function(value, row, rec) {
                                        var flag = row.cus_remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else if (flag.length >= 30) {
                                            flag = flag.substring(0, 24);
                                            flag += "...";
                                            return flag;
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'Represent',
                                    title : 'Represent',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.isRepresent;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            if (flag == 'Represent'
                                                    || flag == 'represent')
                                                return "Y";
                                            else
                                                return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'competitor',
                                    title : 'Competitor',
                                    width : 150,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.competitor;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'reason',
                                    title : 'Justification',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.reason;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                }, {
                                    field : 'product_dateStr',
                                    title : 'Start of Production',
                                    width : 120,
                                    align : 'center',
                                }

                        ] ],
                        toolbar : [
                                "-",
                                {
                                    text : 'Output',
                                    iconCls : 'icon-outport',
                                    handler : function() {
                                        downLoad();
                                        $('div.datagrid-toolbar a').eq(0)
                                                .linkbutton('disable');
                                        setTimeout(function() {
                                            $('div.datagrid-toolbar a').eq(0)
                                                    .linkbutton('enable');
                                        }, 2000);
                                    }
                                }, "-", {
                                    text : 'QuoteLog',
                                    iconCls : 'icon-list',
                                    handler : function() {
                                        quoteLog();
                                    }
                                }, "-", {
                                    text : 'UpdateDebitDate',
                                    iconCls : 'icon-edit',
                                    handler : function() {
                                        updateDebitDate();
                                    }
                                } ],
                        rowStyler : function(index, row) {
                            var rows = $('#datagrid').datagrid('getRows');
                            var quote_id = rows[0].quote_id;
                            var flag = 0;
                            for (var i = 0; i < rows.length; i++) {
                                if (rows[i].quote_id != quote_id) {
                                    if (flag == 0) {
                                        flag = 1;
                                    } else if (flag == 1) {
                                        flag = 0;
                                    }
                                }
                                rows[i].flag = flag;
                                quote_id = rows[i].quote_id;
                            }
                            if (row.flag == 1) {
                                return 'background:#ffffcc;';
                            } else {
                                return 'background:#ffffff;';
                            }
                        },
                        onDblClickRow : function(rowIndex, rowData) {
                            initMaintAccount(false, '840', '300', 'Quote Log',
                                    '/quoteAction!toSearchQuoteLog.jspa?quote_id='
                                            + rowData.quote_id
                                            + '&material_id='
                                            + rowData.material_id, 20, 20);
                        },
                        onBeforeLoad : function(param) {
                            if ($('#roleId').val() == 'JXS') {
                                $('div.datagrid-toolbar a').eq(1).hide();
                            } else {
                                $('div.datagrid-toolbar a').eq(1).show();
                            }
                            if ($('#roleId').val() == 'admin') {
                                $('div.datagrid-toolbar a').eq(2).show();
                            } else {
                                $('div.datagrid-toolbar a').eq(2).hide();
                            }
                        },
                        onClickCell : function(rowIndex, field, value) {
                            var rows = $('#datagrid').datagrid('getRows');
                            if (($('#roleId').val() == 'admin' || $('#roleId')
                                    .val() == 'HK10_H_Marketing_Mgmt')
                                    && rows[rowIndex].isAgree == 1) {
                                $('div.datagrid-toolbar a').eq(2).linkbutton(
                                        'enable');
                                beginEditing(rowIndex, field, value);
                            } else {
                                $('div.datagrid-toolbar a').eq(2).linkbutton(
                                        'disable');
                            }
                            if (field == "cus_remark"
                                    && rows[rowIndex].cus_remark != undefined
                                    && rows[rowIndex].cus_remark != "") {
                                var str = rows[rowIndex].cus_remark;
                                $.messager.alert('Remark', str, '');
                            }
                            if (field == "remark"
                                    && rows[rowIndex].remark != undefined
                                    && rows[rowIndex].remark != "") {
                                var str = rows[rowIndex].remark;
                                $.messager.alert('Remark', str, '');
                            }
                        },
                        onLoadSuccess : function() {
                            if ($('#roleId').val() == 'JXS') {
                                $('div.datagrid-toolbar a').eq(1).hide();
                            } else {
                                $('div.datagrid-toolbar a').eq(1).show();
                            }
                            if ($('#roleId').val() == 'admin'
                                    || $('#roleId').val() == 'HK10_H_Marketing_Mgmt') {
                                $('div.datagrid-toolbar a').eq(2).show();
                            } else {
                                $('div.datagrid-toolbar a').eq(2).hide();
                            }
                            if ($('#roleId').val() == 'HK10_H_Marketing_Mgmt') {
                                $('div.datagrid-toolbar a').eq(2).show();
                            } else {
                                $('div.datagrid-toolbar a').eq(2).hide();
                            }
                        },

                    });
}

function downLoad() {
    var url = '';
    url = '/quoteAction!downloadQuoteList.jspa';
    $.messager.confirm('Confirm', 'Confirmed about  OutPort?', function(r) {
        if (r) {
            var exportExcelUrl = url;
            var form = window.document.forms[0];
            form.action = appUrl + exportExcelUrl;
            form.target = "hideFrame";
            form.submit();
        }
    });
}

/**
 * ??????excel??????????????????
 * 
 * @param exportExcelUrl
 * @param scanTime
 *            ???????????????????????????????????? ????????????
 * @param interval
 *            ??????????????????????????????????????????10%??? ???????????? ?????????????????? ??????????????? 200 ??????2???????????????
 */
function exportExcWithprogress(isExportUrl, exportExcelUrl, scanTime, interval) {
    if (scanTime < 1000 || scanTime == undefined) {
        scanTime = 1000;
    }
    $.messager.progress({
        title : 'Please waiting',
        msg : 'export data...',
        interval : interval
    });
    var form = window.document.forms[0];
    form.action = appUrl + exportExcelUrl;
    form.target = "hideFrame";
    form.submit();
    var timer = setInterval(function() {
        $.ajax({
            url : appUrl + isExportUrl,
            success : function(data) {
                if (data == "true") {
                    $.messager.progress('close');
                    clearInterval(timer);
                }
            },
            error : function(e) {
                console.log(e.responseText);
            }
        });
    }, scanTime);
}

function quoteLog() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item???', 'warning');
        return;
    } else {
        initMaintAccount(false, '840', '300', 'Quote Log',
                '/quoteAction!toSearchQuoteLog.jspa?quote_id='
                        + rows[0].quote_id + '&material_id='
                        + rows[0].material_id, 20, 20);
    }
}

// ????????????
function updateDebitDate() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item???', 'warning');
        return;
    }
    var rowIndex = $('#datagrid').datagrid('getRowIndex',
            $("#datagrid").datagrid('getSelected'));
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var debit_endStr = editors[0];
    var aa = debit_endStr.oldHtml;
    var rows1 = $('#datagrid').datagrid('getRows');
    for (var i = 0; i < rows1.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
    }

    quoteDetailJson = [];
    var rows = $('#datagrid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        if (aa < currentDate) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Debit end  can not earlier than today???", 'error');
            return;
        }
        if (rows[i].debit_endStr == undefined || rows[i].debit_endStr == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + ": Debit End is not completed yet???", 'error');
            return;
        } else if (rows[i].debit_endStr > aa) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + ": Debit End  can not earlier than Old Debit end???",
                    'error');
            return;
        }

        var row = "{" + "id:" + "'" + rows[i].id + "',debit_startStr:'"
                + rows[i].debit_startStr + "',debit_endStr:'"
                + rows[i].debit_endStr + "'}";

        quoteDetailJson.push(row);
    }
    $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
        if (r) {
            $('#quoteDetailJson').val("[" + quoteDetailJson + "]");
            var form = window.document.forms[0];
            form.action = appUrl + "/quoteAction!updateDebitDate.jspa";
            form.target = "hideFrame";

            form.submit();
        }
    });

}

function search() {
    var queryParams = $('#datagrid').datagrid('options').queryParams;
    queryParams.cusGroup_id = $("#cusGroup_id").val();
    queryParams.debit_num = $("#debit_num").val();
    queryParams.quote_id = $("#quote_id").val();
    queryParams.disti_branch = $("#disti_branch").val();
    queryParams.endCustomer_name = $("#endCustomer_name").val();
    queryParams.purchaseCustomer_name = $("#purchaseCustomer_name").val();
    queryParams.project_name = $("#project_name").val();
    queryParams.material_id = $("#material_id").val();
    queryParams.material_name = $("#material_name").val();
    queryParams.states = $('#state').val();
    queryParams.isAgrees = $('#isAgree').val();
    queryParams.create_userName = $('#create_userName').val();
    queryParams.start_dateStr = $("#start_dateStr").datebox('getValue');
    queryParams.end_dateStr = $("#end_dateStr").datebox('getValue');
    $('#datagrid').datagrid({
        url : appUrl + '/quote/quoteAction!getOutPortQuoteList.jspa',
        queryParams : queryParams,
        pageNumber : 1
    })
}

function initMaintAccount(ffit, widte, height, title, url, l, t) {
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
                        fit : FFit,
                        draggable : true,
                        left : l,
                        top : t
                    });

    $win.window('open');

}

// ??????????????????
function closeMaintWindow() {
    $("#hiddenWin").window('close');
}

function promgtMsg() {
    var hideFrame = document.getElementById("hideFrame");
    var failResult = hideFrame.contentWindow.failResult;
    var successResult = hideFrame.contentWindow.successResult;
    if (failResult != "") {
        if (failResult == undefined || failResult == 'undefined') {
            return;
        }
        $.messager.alert('Tips', failResult, 'warning');
    } else {
        $.messager.alert('Tips', successResult, 'info');
        search();
    }
}

// ???????????????
$('#customer_id')
        .combogrid(
                {
                    panelHeight : 280,
                    panelWidth : 350,
                    pagination : true,
                    pageSize : 10,
                    multiple : false,
                    editable : false,
                    method : 'post',
                    singleSelect : true,
                    url : appUrl
                            + '/customer/customerAction!getCustomerList.jspa?customer_code='
                            + $('#customer_id').val(),
                    idField : 'customer_code',
                    textField : 'customer_name',
                    columns : [ [
                            {
                                field : 'customer_code',
                                title : 'Code',
                                width : 70,
                                formatter : function(value, row, rec) {
                                    var flag = row.customer_code;
                                    if (flag == '' || flag == undefined) {
                                        return "";
                                    } else {
                                        var str = $.trim(flag);
                                        str = str.substring(str.length - 6,
                                                str.length);
                                        return str;
                                    }
                                }
                            }, {
                                field : 'customer_name',
                                title : 'Customer Name',
                                width : 250,
                            } ] ],
                    toolbar : '#toolbarCustomer',
                    onSelect : function(index, record) {
                        ;
                    }
                });
function searcherCustomer(name1) {
    var queryParams = $('#customer_id').combogrid("grid").datagrid('options').queryParams;
    queryParams.customer_name = encodeURIComponent(name1);
    $('#customer_id').combogrid("grid").datagrid('reload');
}

// PC??????
$('#purchaseCustomer_id')
        .combogrid(
                {
                    panelHeight : 280,
                    panelWidth : 330,
                    pagination : true,
                    pageSize : 10,
                    multiple : false,
                    editable : false,
                    method : 'post',
                    singleSelect : true,
                    url : appUrl
                            + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
                    idField : 'end_customer_id',
                    textField : 'end_customer_name',
                    columns : [ [ {
                        field : 'end_customer_groupId',
                        title : 'PC Group',
                        width : 100
                    }, {
                        field : 'end_customer_name',
                        title : 'PC Name',
                        width : 200
                    }, {
                        field : 'end_customer_id',
                        title : 'PC Code',
                        width : 200,
                        hidden : true
                    } ] ],
                    onSelect : function(index, record) {
                    },
                    toolbar : '#toolbarPurCustomer',
                });
function searcherPurCustomer(name1) {
    var queryParams = $('#purchaseCustomer_id').combogrid("grid").datagrid(
            'options').queryParams;
    queryParams.end_customer_name = encodeURIComponent(name1);
    $('#purchaseCustomer_id').combogrid("grid").datagrid('reload');
}

var editIndex = undefined;

function beginEditing(rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
            editIndex = rowIndex;
        }
    } else {
        $('#datagrid').datagrid('endEdit', rowIndex);
        $('#datagrid').datagrid('beginEdit', rowIndex);
    }

    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var debit_endStr = editors[0];
    console.log(debit_endStr)
    debit_endStr_oldHtml = debit_endStr.oldHtml;
}
function endEditing() {
    if (editIndex == undefined) {
        return true;
    }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
        $('#datagrid').datagrid('endEdit', editIndex);// ?????????????????????
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

document.onkeydown = function(e) {
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        search();
        return false;
    }
    if (event.keyCode == 8) {
        // ????????????textarea????????????????????????
        if (event.srcElement.tagName.toLowerCase() != "input"
                && event.srcElement.tagName.toLowerCase() != "textarea"
                && event.srcElement.tagName.toLowerCase() != "password")
            event.returnValue = false;
        // ?????????readOnly??????disable?????????????????????
        if (event.srcElement.readOnly == true
                || event.srcElement.disabled == true)
            event.returnValue = false;
    }
    return true;
};

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1
            + strDate;
    return currentdate;

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

    str = utcCurrTime.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2];
    return date;
}