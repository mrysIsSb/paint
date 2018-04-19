//是否有中文
function isHaveChinese(str){
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	if(reg.test(str)){     
		return true;  
	}else{
		return false;
	}
}
//是否有标点
function isHaveMark(str){
	var reg=new RegExp("[,.!\\u3002\\uff0c]","g")
	if (reg.test(str)) {
		return true;
	}else{
		return false;
	}
}