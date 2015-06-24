function selectPersonMultiple(url,idField,nameField,root){
	window.idField=idField;
	window.nameField=nameField;
	var left=(window.screen.width-400)/2;
	var top=(window.screen.height-500)/2;
	window.open(url + '/choosePerson.action?root=' + root ,'',
	'height=500,width=450,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,left=' + left + ',top=' + top);
}