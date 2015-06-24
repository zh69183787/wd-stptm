var http_request = false;

//ajax请求
function doAjaxRequest(url,parentId) {
	http_request = false;

	// 开始初始化XMLHttpRequest对象
	if (window.XMLHttpRequest) {
		// Mozilla, IE
		if (window.ActiveXObject) {
			http_request = new ActiveXObject("Microsoft.XMLHTTP"); // IE 7
		} else {
			http_request = new XMLHttpRequest(); // Mozilla
			if (http_request.overrideMimeType) {
				http_request.overrideMimeType("text/xml"); // 设置Mime类别
			}
		}
	} else if (window.ActiveXObject) { // IE
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}

	if (!http_request) {
		window.alert("创建XMLHttpRequest对象失败,请检查您的浏览器版本及配置！");
		return false;
	}

	var ajaxurl = url + parentId;

	http_request.onreadystatechange = function(){
		if (http_request.readyState == 4) { // 判断对象状态
			if (http_request.status == 200) { // 信息成功返回，开始处理信息
				var returnText = http_request.responseText.replace(/(^\s+|\s+$)/g, "");
				// 返回结果作为子节点插入组织树
				if (returnText != null && returnText.length > 0) {
					addNode(returnText, parentId);
				}
			} else {
				alert('您所请求的页面有异常！');
			}
		}
	}

	http_request.open("GET", ajaxurl, true);
	http_request.send(null);
}
