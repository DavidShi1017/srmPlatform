var selectedId;
var selectedName;
$(document).ready(
				function() {
					/*$('#orgTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
							selectedId = node.id;
							selectedName = node.text;
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});*/

					/*$("#treeMenu").menu({
						onClick : function(item) {
							select(selectedId, selectedName);
						}
					});*/
					 var orgTreeUrl = '';
	                 if($('#orgIdIn').val() != ''){
	                	 orgTreeUrl = appUrl+ '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node='+$('#orgIdIn').val()+"&flag="+"Y";
	                 }else{
	                     orgTreeUrl = appUrl+ '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0';
	                 }
					$('#orgTree').tree({
										animate : true,
										url : orgTreeUrl,
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
													+ "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node="
													+ node.id;
										},
										onClick : function(node) {// �����¼�
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
										},
										onDblClick : function(node) {
											select(node.id, node.text);
										}
									});
				});

function select(selectedId, selectedName) {
	document.getElementById("orgId").value = selectedId;
	document.getElementById("orgName").value = selectedName;
	window.parent.returnValue(selectedId, selectedName);
	window.parent.closeOrgTree();
};
         
function synchronization(){
	//location.href= crmUrl +'/webserviceAction!synchronizationOrg.jspa';
	 $.messager.progress({
		 title:'���Ե�',
		 msg:'��OAͬ�����ݣ�',
		 text:'Loading...',
		 value:0
		 });
	$.ajax({
		type: "POST",
        url: crmUrl +'/webserviceAction!synchronizationOrg.jspa', //������Ҫ�����action, paperId ��url����
        contentType:'application/x-www-form-urlencoded;charset=UTF-8', 
        method:'post',
        error: function(msg){
            alert(msg);
         },
         success: function(msg){
        	 if(msg!="" && msg != null){
        		 var json = eval(msg);
        		 if(json != null && json != "" && json[0].result != null && json[0].result=="false"){
        			 alert(json[0].info);
        			 return false;
        		 }
        	 }
        	 $.messager.progress('close');
        	 $('#orgTree').tree('options').url = appUrl+ '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0';
        	 $('#orgTree').tree('reload');
         }
	});
}
