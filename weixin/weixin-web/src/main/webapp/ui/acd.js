		//中文名验证
	     function validateChinise(A) {
	      	if(a.value != null && a.value.size() != 0){
	      		return true;
	      	}
			if(/^[\u4e00-\u9fa5]+(\([\u4e00-\u9fa5]\))?/.test(A.value)) {
				return true;
			}
			alert("中文名不正确");
			return false;
	      }
		  //验证手机号
		  function validateMonbileNo(A){
		  	if(/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/.test(A.value)){
		  		return true;
		  	}
			alert("手机号码格式不正确");		  	
		  	return false;
		  }
		  //验证身份证
		  function validateIdCard(A){
		  	if(/^\d{15}|\d{18}$/.test(A.value)){
		  		return true;
		  	}
		  	alert("身份证格式不正确");
		  	return false;
		  }
		  
		  //验证邮编
		function validatePostCode(a){
			if(/^[1-9][0-9]{5}$/.test(a.value)){
				return true;
			}
			alert('邮编格式不正确');
			return false;
		}
		//验证电话号码
		function validatePhoneNo(a){
			if(/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(a.value)){
				return true;
			}
			alert('电话号码格式不正确');
			return false;
		}
		
		//验证邮箱
		function validateEmail(a){
			if(/^(\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)$/.test(a.value)){
				return true;
			}
			alert('邮箱格式不正确');
			return false;
		}
		//验证网址
		function validateInternet(a){
			if(/^(?:\w+?)=(?:[^&]+)$/.test(a.value)){
				return true;
			}
			alert('网址不正确');
			return false;
		}
		
		//格式化日期
      function formatDate(dateStr) {

		var dateObj=new Date(dateStr);
		var year,month,day;
		
		if(isNaN(dateObj))
	   	{
	   		return dateStr;
	    }
		
		year=dateObj.getYear();
		month=dateObj.getMonth() + 1;
		if(month<10) month='0'+month;
		day=dateObj.getDate();
		if(day<10) day = '0'+day;
		var d = year+"-"+month+"-"+day;
		//var date = new Date(d);
		
		return d;

      }
		
      /*
		*string:原始字符串
		*substr:子字符串
		*isIgnoreCase:忽略大小写
		*/
		function contains(string,substr,isIgnoreCase) {
			if(isIgnoreCase) {
				string=string.toLowerCase();
				substr=substr.toLowerCase();
			}
			var startChar=substr.substring(0,1);
			var strLen=substr.length;
			for(var j=0;j<string.length-strLen+1;j++) {
				//如果匹配起始字符,开始查找
				if(string.charAt(j)==startChar) {
				//如果从j开始的字符与str匹配，那ok
					if(string.substring(j,j+strLen)==substr) {
						return true;
					}   
				}
			}
			return false;
		}
      
		//格式化日期
		function formatDateTemp(dateStr) {
			if(contains(dateStr," ",false)) {
				dateStr = dateStr.substring(0,dateStr.indexOf(" "));
			}
			return dateStr;
		}
		
		/**
		 * iframe内嵌页面自动调整高度
		 **/
		function iframeAutoSize() {
			if(top.location != self.location){
				var a = window.parent.document.getElementsByTagName('iframe');
				for (var i=0; i<a.length; i++) {
					if (a[i].name == self.name) {
						a[i].height = document.body.scrollHeight;
						return;
					}
				}
			} 
		}
		
	/*
	    用途：检查输入字符串是否为空或者全部都是空格
	    输入：str
	    返回：
	    如果全是空返回true,否则返回false
	 */
	    function isNull( str ){
	    	if ( str == "" ) return true;
	    	var regu = "^[ ]+$";
	    	var re = new RegExp(regu);
	    	return re.test(str);
	    }
	 /*
	    带符号验证是否为数字
	   如果是数字返回true,否则返回false
	*/
	function isNumAddMark(number){
		number = number+"";
		var regu = "^[0-9]|[-][0-9]|[+][0-9]+$";
		var re = new RegExp(regu);
		if (number.search(re) != -1) {
		  return true;
		} else {
		  return false;
		} 
	}
	    /*计算长度
		 (包含数字,字母,汉子)
		 */
		 function mkLength(str){
			 var len=0;
			 var a=str.split("");
			 for(var i=0;i<str.length;i++){
				 if(a[i].charCodeAt(0)<299){
					 len++;
				 }else {
					 len+=2;
				 }
			 }
			return len;
		 }
		
