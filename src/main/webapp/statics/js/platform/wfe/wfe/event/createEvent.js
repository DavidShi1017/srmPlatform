$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	addUploadButton(editor);
});

function loadGrid(){
/*	$('#stationId').combobox({
		url : appUrl + '/wfe/processChooseAction!getStations.jspa',
		valueField : 'stationId',
		textField : 'stationName',
		onLoadSuccess : function() {
			$('#stationId').combobox("setText", '--��ѡ��--');
		},
		editable : false,
		onSelect : function(re) {
			choiceOld(re.stationId);
		}
	});
*/
	selectModelCombox();
	$("#nextinfolist").hide();
}

function addUploadButton(editor){     
	CKEDITOR.on('dialogDefinition', function(ev){         
		var dialogName = ev.data.name;         
		var dialogDefinition = ev.data.definition;         
		if ( dialogName == 'image' ){             
			var infoTab = dialogDefinition.getContents( 'info' );             
			infoTab.add({                 
				type : 'button',                 
				id : 'upload_image',                 
				align : 'center',                 
				label : '�ϴ�',                 
				onClick : function( evt ){
					var thisDialog = this.getDialog();                     
					var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');                     
					var txtUrlId = txtUrlObj.getInputElement().$.id;                     
					addUploadImage(txtUrlId);                 
				}             
			}, 'browse'); 
		}
	});
}

function addUploadImage(theURLElementId){
	//���Ǵ����ļ�/ͼƬ�ϴ���ҳ��URL ;scroll:yes;status:yes
	var uploadUrl = appUrl + '/file/fileAction!uploadImagPrepare.jspa?Rnd='+Math.random();	
	//��upload������ͨ��js����window.returnValue=...���Խ�ͼƬurl���ظ�imgUrl����
	var imgUrl = window.showModalDialog(uploadUrl, null, "dialogWidth=400px;dialogHeight=200px");
	
	var urlObj = document.getElementById(theURLElementId);     
	urlObj.value = imgUrl;     
	urlObj.fireEvent("onchange"); //����url�ı����onchange�¼����Ա�Ԥ��ͼƬ 
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close'); 
	}else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

/**
 * һ�������б�����
 * 
 * @param {}
 *            id
 */
/*function choiceOld(stationId) {
	var dataHtml = "<input id='curStationId' name='curStationId' readonly />"
			+ "<div id='modelReturn' name='modelReturn'></div>";
	document.getElementById("roleReturn").innerHTML = dataHtml;
	bindSecondCombox('curStationId');
}
*/
/**
 * �󶨶��������б�
 *//*
function bindSecondCombox(docId) {
	$('#' + docId).combobox({
		url : appUrl + '/station!getCompanyJsonList.jspa',
		valueField : 'orgId',
		textField : 'orgName',
		onLoadSuccess : function() {
			$('#' + docId).combobox("setText", '--��ѡ��--');
		},
		editable : false,
		onSelect : function(re) {
			choiceNew(re.orgId);
		}
	});
}*/

/**
 * ���������б�����
 * 
 * @param {}
 *            id
 *//*
function choiceNew(orgId) {
	var dataHtml = "<input id='modelId' name='key' readonly/>";
	document.getElementById("modelReturn").innerHTML = dataHtml;
	bingThirdCombox('modelId');
}
*/
/**
 * �����������б�
 * 
 * @param {}
 *            id
 */
function selectModelCombox() {
	$('#modelId').combobox(
			{
				url : appUrl + '/wfe/processChooseAction!getModels.jspa?from=',
				valueField : 'key',
				textField : 'name',
				onLoadSuccess : function() {
					$('#modelId').combobox("setText", '--��ѡ��--');
				},
				editable : false,
				onSelect : function(re) {
					modifyModel(re.key);
					if ("semiautomatic" == re.key) {
						initMaintEvent('���Զ������̻���', '/wfe/eventAction!toSemiAutomatic.jspa', 750,450);
					}
				}
			});
}

function modifyModel(key) {
	$('#addContent').html(
					"<A class=\"icon_but\" href=\"javascript:addContent("
							+ "'"
							+ key
							+ "'"
							+ ",0"
							+ ",0)\">"
							+ "<img height=16 width=16 src=\""
							+ imgUrl
							+ "/images/actions/action_edit.png\" align=absMiddle border=0>�������</A>");
	if ("any" == key.substr(0,3)) {
		$("#nextinfolist").show();
	} else {
		$("#nextinfolist").hide();
	}
	flag = false;
}

/**
 * �������
 * 
 * @param {}
 *            key
 * @param {}
 *            planAttId
 */
function addContent(key, planAttId, tempFielName) {
	if("fix_travel" == key) {
		initMaintEvent('���ݴ���',
			'/wfe/eventAction!toBusinessTripApply.jspa?key=' + key
					+ "&planAttId=" + planAttId + "&xmlTemp_FileName=" + tempFielName, 580, 400);
	} else {
		initMaintEvent('���ݴ���',
			'/wfe/eventAction!updateEventContentPrepare.jspa?key=' + key
					+ "&planAttId=" + planAttId + "&xmlTemp_FileName=" + tempFielName, 580, 400);
	}
}

/**
 * ѡ����ϵ��
 */
function createProEventReader() {
	initMaintEvent('ѡ����ϵ��', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}

function searchLinkMan() {
	initMaintEvent('������ϵ��', '/wfe/eventAction!searchLinkMan.jspa', 340, 430);
}

/**
 * ������ϵ��
 * 
 * @param {}
 *            x
 */
function saveUser(x) {
	$("#nextUserId").val(x[0]);
	$("#nextUserName").val(x[1]);
	$("#nextOrgId").val(x[2]);
	closeMaintEvent();
}

// �������ڶ���
function initMaintEvent(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintEvent")
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
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});
	$win.window('open');
}

/**
 * �رյ�������
 */
function closeMaintEvent() {
	$("#maintEvent").window('close');
}

/**
 * ������д���֮��
 */
function closeContent(planAttId) {
	var key = $('#modelId').combobox('getValue');
	var tempFielName = $("#xmlTemp_FileName").val();
	flag = true;
	$('#addContent').html(
					"<A class=\"icon_but\" href=\"javascript:addContent("
							+ "'"
							+ key
							+ "'"
							+ ","
							+ planAttId
							+ ",'"
							+ tempFielName
							+ "')\">"
							+ "<img height=16 width=16 src=\""
							+ imgUrl
							+ "/images/actions/action_edit.png\" align=absMiddle border=0>�༭����</A>");
	closeMaintEvent();
}

function submit() {
	var n = $("#eventTitle").validatebox('isValid');
	if (!n) {
		return;
	}
	if (!flag) {
		$.messager.alert('Tips', '����д����', 'warning');
		return;
	}
	$("#memo").val(editor.document.getBody().getText()); // ȡ�ô��ı�
	var key = $('#modelId').combobox('getValue');
	if ("any" == key.substr(0,3)) {
		var nextuser = $("#nextUserId").val();
		if (nextuser == '' || nextuser.length == 0) {
			$.messager.alert('Tips', '��ѡ������', 'warning');
			return;
		}
	}
	var fix_key = key.substr(0,3);
	if ("fix" == fix_key) {
		$.messager.progress();
		$.ajax({
			type : "post",
			url : appUrl + "/wfe/eventAction!selectNexUser.jspa?time="+new Date() + "&projectId=" + $("#projectId").val(),
			data : {
				key : key,
				userId   : $("#curUserId").val(),
				modelKey : $("#modelKey").val(),
				modelValues:$("#modelValues").val()
			},
			success : function(userUtil) {
						$.messager.progress('close');
						var positionHtml = "<div class='easyui-panel' title='�¸�����' data-options='collapsible:true'>"
							+"<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
						+"<tr><td class='head' noWrap>������</td>"
						+"<td><select id='nextUserId1' name='nextUserId1'>";
						$.each(userUtil.result, function(i, v) {
							positionHtml += "<option value='"+ v.userId +"'>"+v.userName+"----"+v.stationName+"</option>";
						});
						positionHtml +="</select></td></tr></table></div>";
						if ($('#nextUserDialog').length<1) {
						       $('<div/>', {
						           id: 'nextUserDialog',
						           title: 'ѡ���¸�������',
						           html: "<div id='nextUser'>" 
						           +positionHtml+
						           "</div>" +
						           "</div>"
						       }).appendTo('body');
						   } else {
							   $('#nextUser').html(positionHtml);
						       
						   }
						   $('#nextUserDialog').dialog({
						       modal: true,
						       resizable: false,
						       dragable: false,
						       closable:false,
						       open: function() {
						           $('#nextUserDialog').css('padding', '0.4em');
						           $('#nextUserDialog .ui-accordion-content').css('padding', '0.4em').height($('#nextUserDialog').height() - 75);
						       },
						       buttons:[{ 
						    	   text:'ȷ��',
						    	   handler:function(){ 
						    		   	$.messager.progress();
						    		   	if($("#nextUserId1").val() == "" || $("#nextUserId1").val() == null){
											$.messager.alert('Tips', "û���¸������ˣ���ά����", 'error');
										    return;
										}
						    		    $("#nextUserId").val($("#nextUserId1").val());
						    			var form = window.document.forms[0];
						    			form.action = appUrl + "/wfe/eventAction!processWorkflowFix.jspa?eventId="+userUtil.processInstanceId;
						    			form.submit();
						    	   }},{
						    		   text:'ȡ��',
						    		   handler:function(){ 
						    			   	var form = window.document.forms[0];
						    				form.action = appUrl + "/wfe/eventAction!cancelNextUser.jspa?eventId="+userUtil.processInstanceId;
						    				form.target = "hideFrame";
						    				form.submit();
								    	} 
						    	   } 
						    	   ],

						       width: document.documentElement.clientWidth * 0.50,
						       height: document.documentElement.clientHeight * 0.40
						   });
					},
				error: function (XMLHttpRequest, textStatus, errorThrown){
				  	$.messager.alert('Tips', textStatus, 'error');
			  	}
				});
		
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/wfe/eventAction!createEvent.jspa";
		form.submit();
	}
}

function reset() {
	$.messager.alert('Tips', "ȡ��", 'error');
}

