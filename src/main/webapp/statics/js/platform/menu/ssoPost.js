//ģ���ύ
function formSubmit() {
	var logonForm = window.document.logonForm;
	var params = $("#params").val().split('&');
	for ( var i = 0; i < params.length; i++) {
		var p = params[i];
		var myInput = document.createElement("input");
		var kv = p.split('=');
		myInput.setAttribute("type", 'hidden');
		myInput.setAttribute("name", kv[0]);
		myInput.setAttribute("value", kv[1]);
		logonForm.appendChild(myInput);
	}
	logonForm.submit();
}

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