function showConstructionLineInfo(){
	$.ajax({
		 url:	"/Construction/TConstructionNotice/showLineInfo.action?random="+Math.random(),
		type:	"post",
	   cache: 	false,
	   error:	function(){alert('系统连接失败，请稍后再试！')},
	 success: 	function(obj){	
					var html = obj.value;		//html，即要显示的信息，后台已完成拼接
				}
	});
}