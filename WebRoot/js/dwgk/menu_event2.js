$(function(){
			$('#slide').iFadeSlide();
			var num1 = 1;
			var num2 = 5;
			var timer ;
			function Init(d){
			
			if(Number(d)){//如果触动计时器
			clearTimeout(timer);
			num1 = d;//当前图片显示
			}
			for(var i =1;i<num2+1;i++){
			if(i == num1){
			document.getElementById(num1).style.display = "block";//下一张图片显示
			document.getElementById("1"+ num1).style.backgroundColor = "blue";
			}else{
			document.getElementById(i).style.display = "none";//当前图片隐藏
			document.getElementById("1"+i).style.backgroundColor = "";
			}
			}
			
			if(num1 == num2)
			num1 = 1;
			else
			num1++;
			
			timer = setTimeout("Init()",1000)
			}

			
		});




