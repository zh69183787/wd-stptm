//追加子节点
function addNode(returnText, parentId) {
	if (returnText == null || returnText == '') {//无下级节点
		return false;
	}

	var nodeArray = returnText.split('!');
	var subTree = tree.getNodeID(parentId);
	var node, treeNode;
	for ( var i = 0; i < nodeArray.length; i++) {
		node = nodeArray[i].split(",");
		if (node[2] == 0) {
			// 非叶节点
			treeNode = new WebFXTreeItem(decodeURI(node[1]), '#', node[0], '1',
					'0');
			subTree.add(treeNode);
		} else {
			// 叶节点-
			// 为了保证控件id的唯一性，叶节点的id由 node[0](人员id)和parentid(所属部门id)组成，
			// 用以应对一个人属于多个部门；
			treeNode = new WebFXTreeItem(decodeURI(node[1]), '#', node[0] + '-'
					+ parentId, '0', '0');
			subTree.add(treeNode);
		}
	}
	subTree.expand();
	for ( var i = 0; i < nodeArray.length; i++) {
		node = nodeArray[i].split(",");
		if (node[2] == 1) {
			// 叶节点图标更换，为了显示效果
			if(document.getElementById(node[0] + '-' + parentId + '-plus') != null){
				document.getElementById(node[0] + '-' + parentId + '-plus').src = '../images/forTree/T.png';
			}			
		}
	}
}

// 展开树结构
function expandMenu(parentId) {
	// 根据id判断节点是否为人员叶节点
	// 人员id 格式：人员id-上级节点id
	var index = parentId.lastIndexOf("-");
	if (index != -1) {// 人员叶节点情况
		return;
	}

	// 如果为非叶节点,进行异步请求
	var subTree = tree.getNodeID(parentId);
	var length = subTree.childNodes.length;
	if (length == 0) {
		doAjaxRequest(url, parentId);
		subTree = tree.getNodeID(parentId);
		length = subTree.childNodes.length;
		if (length == 0) {// 如果无子节点，将该节点图标更换
			if(document.getElementById(parentId + '-plus') != null) {
				document.getElementById(parentId + '-plus').src = '../images/forTree/T.png';
			}			
		}
	} else {
		subTree.toggle();
	}
}

// 响应确定按钮， 返回选择结果
function returnText(type) {
	// 取得存放人员name,id,部门name,id 控件
	var selectNodeName = document.getElementById('returnText');
	var selectNodeId = document.getElementById('returnId');

	if (selectNodeName.value == "") {
		alert("请选择人员！");
		return;
	}
	
	if(type == 'radio') {
		// 将人员id添加到opener页面中相应的控件
		if (opener.idField.value != null) {
			opener.idField.value = selectNodeId.value;
		} 	
		// 将人员name添加到opener页面中相应的控件
		if (opener.nameField.value != null) {
			opener.nameField.value = selectNodeName.value;
		} 
	}else if(type == 'checkbox') {
		// 将人员id添加到opener页面中相应的控件
		if (opener.idField.value != null && opener.idField.value != "") {
			opener.idField.value += "," + selectNodeId.value;
		} else {
			opener.idField.value = selectNodeId.value;
		}
	
		// 将人员name添加到opener页面中相应的控件
		if (opener.nameField.value != null && opener.nameField.value != "") {
			opener.nameField.value += "," + selectNodeName.value;
		} else {
			opener.nameField.value = selectNodeName.value;
		}
	}
	window.close();
}

// 响应单选框和复选框的onclick事件
function checkChecked() {
	var ids = "";
	var names = "";

	// 选中人员name,id存放控件
	var selectNodeName = document.getElementById('returnText');
	var selectNodeId = document.getElementById('returnId');
	selectNodeName.value = '';
	selectNodeId.value = '';

	// 人员信息叶节点，人员叶节点的id
	var peopleNode,leafId;
	if (tree.childNodes.toString() != "") {
		if (typeof (document.all("proceeding").length) != "undefined") {
			// 多选情况
			for ( var i = 0; i < document.all("proceeding").length; i++) {
				if (document.all("proceeding")[i].checked) {
					// 人员叶节点的id由 人员id和部门id 组成，将人员id取出
					leafId = document.all("proceeding")[i].value.split('-');
					ids += leafId[0] + ",";
					// 按叶节点id取得人员叶节点，将人员name取出
					peopleNode = tree
							.getNodeID(document.all("proceeding")[i].value);
					names += peopleNode.text + ",";
				}
			}
			// 将取得的人员id,人员name放入到控件
			selectNodeName.value = names.substr(0, names.length - 1);
			selectNodeId.value = ids.substr(0, ids.length - 1);
		} else {
			// 单选
			if (document.all("proceeding").checked) {
				// 人员叶节点的id由 人员id和部门id 组成，将人员id取出
				leafId = document.all("proceeding").value.split('-');
				ids = leafId[0];
				// 按叶节点id取得人员叶节点，将人员name取出
				peopleNode = tree.getNodeID(document.all("proceeding").value);
				names = peopleNode.text;
			}
			// 将取得的人员id,人员name放入到控件
			selectNodeName.value = names;
			selectNodeId.value = ids;
		}
	}
}


//人员全选或反选
function check_all() {
	var targetobj = document.getElementsByName('proceeding');

	if (targetobj != undefined) {
		for ( var i = 0; i < targetobj.length; i++) {
			if (targetobj[i].value.toLowerCase().indexOf('aaaa') == -1)
				targetobj[i].checked = !targetobj[i].checked;
			}
	}
	checkChecked();
}
