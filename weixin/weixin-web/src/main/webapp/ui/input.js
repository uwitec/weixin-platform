			var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
			var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
			
			//15位身份证验证
			function IdCardValidate(idCard) {   
			    idCard = trim(idCard.replace(/ /g, ""));   
			    if (idCard.length == 15) {   
			        return isValidityBrithBy15IdCard(idCard);   
			    } else if (idCard.length == 18) {   
			        var a_idCard = idCard.split("");// 得到身份证数组   
			        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   
			            return true;   
			        }else {   
			            return false;   
			        }   
			    } else {   
			        return false;   
			    }   
			}
			
			//18位身份验证
			function isTrueValidateCodeBy18IdCard(a_idCard) {   
			    var sum = 0; // 声明加权求和变量   
			    alert(a_idCard[17]);
			    if (a_idCard[17].toLowerCase() == 'x') {   
			        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
			    }   
			    for ( var i = 0; i < 17; i++) {   
			        sum += Wi[i] * a_idCard[i];// 加权求和   
			    }   
			    valCodePosition = sum % 11;// 得到验证码所位置   
			    if (a_idCard[17] == ValideCode[valCodePosition]) {   
			        return true;   
			    } else {   
			        return false;   
			    }   
			}
			
			 /**  
			  * 验证18位数身份证号码中的生日是否是有效生日  
			  * @param idCard 18位书身份证字符串  
			  * @return  
			  */  
			function isValidityBrithBy18IdCard(idCard18){   
			    var year =  idCard18.substring(6,10);   
			    var month = idCard18.substring(10,12);   
			    var day = idCard18.substring(12,14);   
			    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
			    // 这里用getFullYear()获取年份，避免千年虫问题   
			    if(temp_date.getFullYear()!=parseFloat(year)   
			          ||temp_date.getMonth()!=parseFloat(month)-1   
			          ||temp_date.getDate()!=parseFloat(day)){   
			            return false;   
			    }else{   
			        return true;   
			    }   
			}   
			  /**  
			   * 验证15位数身份证号码中的生日是否是有效生日  
			   * @param idCard15 15位书身份证字符串  
			   * @return  
			   */  
			  function isValidityBrithBy15IdCard(idCard15){   
			      var year =  idCard15.substring(6,8);   
			      var month = idCard15.substring(8,10);   
			      var day = idCard15.substring(10,12);   
			      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
			      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
			      if(temp_date.getYear()!=parseFloat(year)   
			              ||temp_date.getMonth()!=parseFloat(month)-1   
			              ||temp_date.getDate()!=parseFloat(day)){   
			                return false;   
			        }else{   
			            return true;   
			        }   
			  } 
			
			//去掉字符串头尾空格   
			function trim(str) {   
			    return str.replace(/(^\s*)|(\s*$)/g, "");   
			} 

			//禁用backspace键
	    	document.onkeydown = check;   
			function check(e) {   
			    var code;   
			    if (!e) var e = window.event;   
			    if (e.keyCode) code = e.keyCode;   
			    else if (e.which) code = e.which;   
			    if (((event.keyCode == 8) &&                                                    //BackSpace    
			         ((event.srcElement.type != "text" &&    
			         event.srcElement.type != "textarea" &&    
			         event.srcElement.type != "password") ||    
			         event.srcElement.readOnly == true)) ||    
			        ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||    //CtrlN,CtrlR    
			        (event.keyCode == 116) ) {                                                   //F5    
			        event.keyCode = 0;    
			        event.returnValue = false;    
			    }   
			    return true;   
			}   
	        
			//验证请求额度
			function validateLimit(Limit){
				var parent = Limit.parentNode;
				var temp = /^\d{1,18}(\.\d{0,2})?$/;
				var span = document.createElement('SPAN');
				var bol = false; 
				if(Limit.value == "" || Limit.value == null){
					bol=true;
					span.innerHTML="<font color='red'size='1'>该项为必填项</font>";
			  		showSpan(span,bol,parent);
				 	return false;
				}else if(Limit.value != "" || Limit.value != null){
					if(!temp.test(Limit.value)){
			  			bol=true;
			  			span.innerHTML="<font color='red'size='1'>请输18位以内小数点后2位的数字</font>";
			  			showSpan(span,bol,parent);
				 		return false;
			  		}else{
						bol = false;
						span.innerHTML = "";
						showSpan(span,bol,parent);
						return true;
					}
				}
			}
			
			//验证身份证号
			function validateIdNo(No,idName){
				var parent = No.parentNode;
				var temp = /^\d{15}|\d{18}$/;
				var span = document.createElement('SPAN');
				var bol = false; 
				if(No.value == ""){
			  		bol=true;
			  		span.innerHTML="<font color='red'size='1'>该项为必填项</font>";
			  		showSpan(span,bol,parent);
				 	return false;
			  	}else if(No.value != null && No.value != ""){
			  		if(No.value.length==18){
			  			/*if(!isTrueValidateCodeBy18IdCard(No.value)){
			  				bol=true;
				  			span.innerHTML="<font color='red'size='1'>18位身份证号不合法</font>";
				  			showSpan(span,bol,parent);
					 		return false;
			  			}
			  			if(!isValidityBrithBy18IdCard(No.value)){
			  				bol=true;
				  			span.innerHTML="<font color='red'size='1'>18位身份证号生日不合法</font>";
				  			showSpan(span,bol,parent);
					 		return false;
			  			}*/
			  			span.innerHTML="";
			  			getBorn(No.value,bol,span,parent,idName);
			  			if(parent.childNodes.length==2)
			    			parent.removeChild(parent.lastChild);
			  			return true;
			  		}else if(No.value.length == 15){
			  			if(!IdCardValidate(No.value)){
				  			bol=true;
				  			span.innerHTML="<font color='red'size='1'>15位身份证号不合法</font>";
				  			showSpan(span,bol,parent);
					 		return false;
				  		}
			  			span.innerHTML="";
			  			showSpan(span,bol,parent);
			  			return true;
			  		} else {
			  			bol=true;
			  			span.innerHTML="<font color='red'size='1'>证件号码不合法</font>";
			  			showSpan(span,bol,parent);
				 		return false;
			  		}
			  	}
			}
			
			//获取18位生日
			function getBorn(idNo,bol,span,paren,idName){
				if(idNo != null){
				   var str = idNo.substr(6,13);
	    		   var d1 = str.substr(0,4);
		    	   var d2 = str.substr(4,2);
		    	   var d3 = str.substr(6,2);
		    	//   alert(idName);
		    	   if(idName=="id_no1"){
		    		   var born = document.getElementById("dateOfBorn");
		    		   born.value=d1+"-"+d2+"-"+d3;
		    		   //born.value=d1+d2+d3;
		    	   }
		    	   if(idName=="id_no3"){
		    		   var born1 = document.getElementById("dateOfBorn1");
		    		   born1.value=d1+"-"+d2+"-"+d3;
		    		   //born1.value=d1+d2+d3;
		    	   }
				}
			//	showSpan(span,bol,parent);
			}
			
	        //页面文本脚本验证
	        //拼音/英文名大小写转换
	        function exchanged(aliasName){
	        	var temp = /^[a-zA-Z]{1}(\p{Blank}){0,1}[a-zA-Z]{1}/;
		        var me = false;
		        var parent = aliasName.parentNode;
		        var span = document.createElement('SPAN');
		        
				 if(aliasName.value == ""){
				 	me=true;
				 	span.innerHTML="<font color='red'size='1'>该项为必填项</font>";
				 	showSpan(span,me,parent);
				 	return false;
				 }
				 if (!temp.test(aliasName.value)){
			 		me=true;
			 		span.innerHTML="<font color='red'size='1'>请输入字母</font>";
				 	showSpan(span,me,parent);
				 	return false;
				 }
				 if((aliasName.value != "" && aliasName.value !=null) && temp.test(aliasName.value) ){
			 		me=false;
					span.innerHTML="";
				 	showSpan(span,me,parent);
			 		//alert(aliasName.value);
					aliasName.value=aliasName.value.toUpperCase();
					return true;
				 }
				
				return true;
	        }
	      //span显示错误提示
		  function showSpan(span,bol,parent){
	    	var tdLen = parent.childNodes;
	    //	alert((typeof(tdLen) == "undefined"));
	    //	alert(tdLen.length);
	    //	alert(tdLen);
	    	if(bol){
	    		if(tdLen.length>=2){
	    			parent.removeChild(parent.lastChild);
	    		}
	    		parent.appendChild(span);
	    	}else{
	    		if(typeof(tdLen) == "undefined" || tdLen.length==2){
	    			parent.removeChild(parent.lastChild);
	    		}
	    	}
		 }
		  
		  //span显示错误提示
		  function changeRemoveSpan(my){
			  if(my.value!=null && my.value!="") {
				  var parent = my.parentNode
				  var tdLen = parent.childNodes;
			  	  if(tdLen.length>=2){
			  		parent.removeChild(parent.lastChild);
			  	  }
			  }
		 }
		  
		  //中文名验证
	      function validateChinese(A) {
	      	var parent = A.parentNode;
	      	var span = document.createElement('SPAN');
	      	var temp = /^[\u4e00-\u9fa5]+(\([\u4e00-\u9fa5]\))?/;
			var bol = false;
			
			if(A.value == ""){
				bol=true;
				span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
			}else if(A.value != "" && A.value != null){
				if(!temp.test(A.value)){
					bol = true;
					span.innerHTML = "<font color='red'size='1'>必须为汉字</font>";
					showSpan(span,bol,parent);
					return false;
				}else{
					bol = false;
					span.innerHTML = "";
					showSpan(span,bol,parent);
					return true;
				}
			}
	      }
	        
	      //检查生日，过期日期等非空日期
	      function validateDate(B){
	    	var parent = B.parentNode;
	    	var bol = false;
	    	var span = document.createElement('SPAN');
	      	if(B.value == ""){
	      		bol=true;
	      		span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
	      		//showSpan(span,bol,parent);
	      		var tdLen = parent.childNodes;
	    		if(tdLen.length>2){
	    			parent.removeChild(parent.lastChild);
	    		}
	    		parent.appendChild(span);
				return false;
	      	}else{
	      		var a=/^(\d{1,4})(-)(\d{1,2})(-)(\d{1,2})/ 
	      		if (!a.test(B.value)){
	      			span.innerHTML = "<font color='red'size='1'>日期要符合yyyy-MM-dd格式</font>";
	      			var tdLen = parent.childNodes;
	      			if(tdLen.length>2){
		    			parent.removeChild(parent.lastChild);
		    		}
		    		parent.appendChild(span);
	      			return false 
	      		}
	      		bol=false;
	      		var tdLen = parent.childNodes;
	    		if(tdLen.length>2){
	    			parent.removeChild(parent.lastChild);
	    		}
				return true;
	      	}
	      }
	      
	      function stringToDate(date){
	    	  var str = date.value;
	    	  if(str != null){
	    		  var d1 = str.substr(0,4);
		    	  var d2 = str.substr(4,2);
		    	  var d3 = str.substr(6,2);
		    	  date.value=d1+"-"+d2+"-"+d3;
	    	  }
	    	  return false;
	      }
	      
	     //验证公司名称
	      function validateCompany(Company){
	    	  var parent = Company.parentNode;
	    	  var bol = false;
	    	  var span = document.createElement('SPAN');
	    	  if(Company.value == "" || Company.value == null){
	      		 bol=true;
	      		 span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
	      		 showSpan(span,bol,parent);
				 return false;
	      	  }else{
	      		 bol=false;
	      		 showSpan(span,bol,parent);
				 return true;
	      	}
	      }
	     
	      //详细地址区
	      function validateDistrict(D){
	      	var parent = D.parentNode;
	      	var span = document.createElement('SPAN');
			var bol = false;
			
			if(D.value == ""){
				bol=true;
				span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
			}else{
				bol=false;
	      		showSpan(span,bol,parent);
				return true;
			}
	      }
	      
	      //地址
	      function validateAddress(addr){
	      	var parent = addr.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
			
			if(addr.value == ""){
				bol=true;
				span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
			}else{
				bol=false;
	      		showSpan(span,bol,parent);
				return true;
			}
	      }
	      
	      //验证联系电话
	      function validatePhone1(Phone){
	      	var parent = Phone.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
			var temp = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
			
			if(Phone.value == ""){
				bol=true;
				span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
			}else{
				if(!temp.test(Phone.value)){
					bol=true;
					span.innerHTML = "<font color='red'size='1'>联系电话输入不正确</font>";
					showSpan(span,bol,parent);
					return false;
				}else{
					bol=false;
		      		showSpan(span,bol,parent);
					return true;
				}
			}
	      }
	      
	      //验证手机号
	      function validateNmMobile(Mobile){
	      	var parent = Mobile.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
	      	var temp = /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
			
			if(Mobile.value == ""){
				bol=true;
				span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
			}else{
				if(!temp.test(Mobile.value)){
					bol=true;
					span.innerHTML = "<font color='red'size='1'>手机号码输入不正确</font>";
					showSpan(span,bol,parent);
					return false;
				}else{
					bol=false;
					span.innerHTML="";
		      		showSpan(span,bol,parent);
					return true;
				}
			}
	      }
	      //验证邮箱
	      function validateEmail(Email){
	      	var parent = Email.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
	      	var temp = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	      	
	      	if(Email.value != "" && Email.value != null){
		      	if(!temp.test(Email.value)){
		      		bol=true;
		      		span.innerHTML = "<font color='red'size='1'>邮箱号输入不正确</font>";
					showSpan(span,bol,parent);
					return false;
		      	}else{
		      		bol=false;
		      		showSpan(span,bol,parent);
					return true;
		      	}
	      	}
	      }
	      
	      //验证工作年限
	      function validateServiceYear(Service){
	      	var parent = Service.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
	      	var temp = /^\d{1,2}$/;
	      	
	      	if(Service.value == "" || Service.value == null){
	      		bol = true;
	      		span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
	      	}else{
		      	if(!temp.test(Service.value)){
		      		bol=true;
		      		span.innerHTML = "<font color='red'size='1'>请输入两位数字的年限</font>";
					showSpan(span,bol,parent);
					return false;
		      	}else{
		      		bol=false;
		      		showSpan(span,bol,parent);
					return true;
		      	}
	      	}
	      }
	      //验证年收入
	      function validateIncome(Income){
	      	var parent = Income.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
	      	var temp = /^\d{1,18}(\.\d{0,2})?$/;
	      	
	      	if(Income.value == ""){
	      		bol = true;
	      		span.innerHTML = "<font color='red'size='1'>该项为必填项</font>";
				showSpan(span,bol,parent);
				return false;
	      	}else{
		      	if(!temp.test(Income.value)){
		      		bol=true;
		      		span.innerHTML = "<font color='red'size='1'>请输18位以内小数点后2位的数字</font>";
					showSpan(span,bol,parent);
					return false;
		      	}else{
		      		bol=false;
		      		showSpan(span,bol,parent);
					return true;
		      	}
				
	      	}
	      }
	      
	      //验证公司传真
	      function validateCompanyFax(fax){
		      if (fax.value != ""){
		        if((fax.value).indexOf('-')==-1) {
		         	fax.value="0769-"+fax.value
		        }
		        var parent = fax.parentNode;
	      		var span = document.createElement('SPAN');
		        var p1 = /(0[1-9]{1}\d{1,2})-(\d{7,8}$)/;
		        span.innerHTML="<font color='red'size='1'>传真号码格式错误(如：029-3454354)</font>";
		        var bol = false;
		        if (!p1.test(fax.value)){
		        	bol=true;
		        	showSpan(span,bol,parent);
		        	return false;
		        }else{
		        	bol=false;
		      		showSpan(span,bol,parent);
					return true;
		        }
		     }
		     return true;
	      }
	      
	      
	      
	      //验证邮政编码
	      function validatePostCode(PC){
	      	var parent = PC.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
	      	var temp = /^[1-9]\d{5}(?!\d)$/;
	      	
	      	if(PC.value != null){
	      		if(!temp.test(PC.value)){
	      			bol=true;
	      			span.innerHTML = "<font color='red'size='1'>请输入六位数的邮政号码</font>";
					showSpan(span,bol,parent);
					return false;
	      		}else{
		        	bol=false;
		      		showSpan(span,bol,parent);
					return true;
		        }
	      	}
	      }
	      
	      //直系亲属/其他联系人电话号码验证
	      function checkPhoneNo(PN){
	      	var parent = PN.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
			var temp = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
			
			if(PN.value != "" && PN.value!=null){
				if(!temp.test(PN.value)){
					bol=true;
					span.innerHTML = "<font color='red'size='1'>请输入正确的电话号码如:(021-82120622)</font>";
					showSpan(span,bol,parent);
					return false;
				}else{
					bol=false;
		      		showSpan(span,bol,parent);
					return true;
				}
			}
	      }
	      
	      //直系亲属/其他联系人手机号码验证
	      function checkMobileNo(MN){
	      	var parent = MN.parentNode;
	      	var span = document.createElement('SPAN');
	      	var bol = false;
			var temp = /^(13[0-9]|14[7]|15[0|1|2|3|5|6|7|8|9]|18[2|6|7|8|9])\d{8}$/;
			
			if(MN.value != "" && MN.value!=null){
				if(!temp.test(MN.value)){
					bol=true;
					span.innerHTML = "<font color='red'size='1'>请输入正确的手机号码</font>";
					showSpan(span,bol,parent);
					return false;
				}else{
					bol=false;
		      		showSpan(span,bol,parent);
					return true;
				}
			}
	      }
	      
	      	//直系亲属联系人检查电话号码和手机号码是否至少填一项
			function checkPhoneMobile() {
				var telNo = document.getElementById("r_telNo");
				var mobileNo = document.getElementById("r_mobileNo");
				var parent = mobileNo.parentNode;
		      	var span = document.createElement('SPAN');
	      		var bol = false;
			 	if ((telNo.value == null || telNo.value == "")&& (mobileNo.value == null || mobileNo.value == "")) {
			 		bol=true;
					span.innerHTML="<font color='red'size='1'>电话号码和手机号码至少要填一项</font>";
					showSpan(span,bol,parent);
					return false;
			 	}
			 	return true;
			}	
	     	
	     	//其他联系人检查电话号码和手机号码是否至少填一项
			function checkPhoneMobile1() {
				var telNo = document.getElementById("o_telNo");
				var mobileNo = document.getElementById("o_mobileNo");
				var parent = mobileNo.parentNode;
		      	var span = document.createElement('SPAN');
	      		var bol = false;
			 	if ((telNo.value == null || telNo.value == "")&& (mobileNo.value == null || mobileNo.value == "")) {
			 		bol=true;
					span.innerHTML="<font color='red'size='1'>电话号码和手机号码至少要填一项</font>";
					showSpan(span,bol,parent);
					return false;
			 	}
			 	return true;
			}	
	      
	      //密码校验指示
	      function validatePin(Pin){
	      	var check_amount = document.getElementById("pin_amount");
	      	var parent = check_amount.parentNode;
		    var span = document.createElement('SPAN');
	      	var bol = false;
	      	
	      	if(Pin.value == "B"){
	      		check_amount.disabled = true;
	      		check_amount.value = "";
	      		span.innerHTML = "";
	      		showSpan(span,bol,parent);
	      	}else if(Pin.value =="L"){
	      		check_amount.disabled = false;
	      		bol=true;
	      		span.innerHTML="<font color='red'size='1'>请填写密码校验金额</font>";
	      		showSpan(span,bol,parent);
	      		return false;
	      	}
	      	return true;
	      }
	      
	      //密码校验指示
	      function validatePin1(Pin){
	      	var check_amount = document.getElementById("pin_amount1");
	      	var parent = check_amount.parentNode;
		    var span = document.createElement('SPAN');
	      	var bol = false;
	      	
	      	if(Pin.value == "B"){
	      		check_amount.disabled = true;
	      		check_amount.value = "";
	      		span.innerHTML = "";
	      		showSpan(span,bol,parent);
	      	}else if(Pin.value =="L"){
	      		check_amount.disabled = false;
	      		bol=true;
	      		span.innerHTML="<font color='red'size='1'>请填写密码校验金额</font>";
	      		showSpan(span,bol,parent);
	      	}
	      }
	      
	      //密码校验金额下限
	      function checkAmount(a){
	      	var parent = a.parentNode;
		    var span = document.createElement('SPAN');
	      	var bol = false;
	      	var temp = /^\d{1,18}(\.\d{0,2})?$/;
	      	if(a.value !="" && a.value !=null){
	      		if(!temp.test(a.value)){
	      			bol=true;
	      			span.innerHTML = "<font color='red'size='1'>请输18位以内小数点后2位的数字</font>";
					showSpan(span,bol,parent);
					return false;
	      		}else{
	      			span.innerHTML="";
	      			showSpan(span,bol,parent);
	      			return true;
	      		}
	      		
	      	}
	      }
	      
	    //分行领取校验
	      function validateDeliverBranch(branch,banchId){
	      	var b_deliveryBranchId = document.getElementById(banchId);
	      	var parent = b_deliveryBranchId.parentNode;
		    var span = document.createElement('SPAN');
	      	var bol = false;
	      	
	      	if(branch.value != "B"){
	      		span.innerHTML = "";
	      		showSpan(span,bol,parent);
	      	}else if(branch.value =="B"){
	      		if(b_deliveryBranchId.value!=null && b_deliveryBranchId.value!="") {
	      			showSpan(span,bol,parent);
	      			return true;
	      		}
	      		bol=true;
	      		span.innerHTML="<font color='red'size='1'>请选择卡领取分行</font>";
	      		showSpan(span,bol,parent);
	      		return false;
	      	}
	      	return true;
	      }
	      
	      //自动还款本币指示校验
	      function validateLocalAutoPay(localAutoPay) {
	    	  var b_localAutopay_accNo = document.getElementById("b_localAutopay_accNo");
		      	var parent = b_localAutopay_accNo.parentNode;
			    var span = document.createElement('SPAN');
		      	var bol = false;
		      	if(localAutoPay.value != "Y"){
		      		span.innerHTML = "";
		      		showSpan(span,bol,parent);
		      	}else if(localAutoPay.value =="Y"){
		      		if(b_localAutopay_accNo.value != null && b_localAutopay_accNo.value != "") {
		      			showSpan(span,bol,parent);
		      			return true;
		      		}
		      		bol=true;
		      		span.innerHTML="<font color='red'size='1'>请填写自动还款账号</font>";
		      		showSpan(span,bol,parent);
		      		return false;
		      	}
		      	return true;
	      }
	      
	      //自动还款外币指示校验
	      function validateFrgnAutopayAccno(frgnAutoPay) {
	    	  var b_frgnAutopayAccno = document.getElementById("b_frgnAutopayAccno");
		      	var parent = b_frgnAutopayAccno.parentNode;
			    var span = document.createElement('SPAN');
		      	var bol = false;
		      	if(frgnAutoPay.value != "Y"){
		      		span.innerHTML = "";
		      		showSpan(span,bol,parent);
		      	}else if(frgnAutoPay.value =="Y"){
		      		if(b_frgnAutopayAccno.value!=null && b_frgnAutopayAccno.value!="") {
		      			showSpan(span,bol,parent);
		      			return true;
		      		}
		      		bol=true;
		      		span.innerHTML="<font color='red'size='1'>请填写自动还款账号</font>";
		      		showSpan(span,bol,parent);
		      		return false;
		      	}
		      	return true;
	      }
	      
	      //comboSelect必填
	      function comboSelectCheckNull(comboSelect) {
	      	var parent = comboSelect.parentNode;
	      	var span = document.createElement('SPAN');
	      	var tdLen = parent.childNodes;
	      	var bol=false;
	      	if(comboSelect.value=='' || comboSelect.value==null) {
	      		bol=false;
	      		span.innerHTML="<font color='red'size='1'>该项必填</font>";
	      		showSpan(span,bol,parent);
	      		return false;
	      	}
	      	return true;
	      }
	      
	      
	      /*****************************************************************************/
	      /*****************************************************************************/
	      /*
			 * 以下是申请主卡、副卡、主副卡来进行判断的js
			 */
			
			//获取卡号
		  	function getCardNo( card_product_id,customer_idType, customer_idNo,cardNo,no_url,cardNoid) {
	        	var reinputInd = $("#reinputInd").val();
	        	if(reinputInd == "Y") {
	        		return true;
	        	}
	        	var no_param = "?cardNo=" + cardNo + "&cardProductId=" + card_product_id + "&customerIdType=" + customer_idType + "&customerIdNo=" + customer_idNo;
	       // 	alert(no_url + no_param);
	        	var cardNoInd = false;
	        	var msg = "卡号分配失败,不能保存";
	        	if(cardNoid =="cardNo"){
	        		msg = "主卡"+msg;
	        	}else{
	        		msg = "副卡"+msg;
	        	}
	        	$.ajax({
	        		type:"post",
	        		cache:false,
	        		processData:false,
	        		async:false,
	        		url:no_url + no_param,
	        		success:function(back){
	        			var data = eval("(" + back + ")");
	        			//$("#cardNo").attr("value",data);
	        			if(data.error == "ERROR"){
	        				alert(msg);
	        				cardNoInd = false;
	        			}else{
	        				$("#"+cardNoid).attr("value",data);
	        				document.getElementById(cardNoid).value=data;
	        				//alert("cardNo="+document.getElementById("cardNo").value);
	        				cardNoInd = true;
	        			}
	        		},
	        		error:function(){
	        			alert("系统错误......");
	        			cardNoInd = false;
	        		}
	        	});
	        	//alert("卡号获取情况="+cardNoInd);
	        	return cardNoInd;
	        }
		  	
		  	//是否登记检查
		  	function checkRegister( applTypeId , idTypeId ,idNo ,fullname ,mobileNo ,url) {
		  		var reinputInd = $("#reinputInd").val();
	        	if(reinputInd == "Y") {
	        		return true;
	        	}
		  		
	        //	var url = "<%=request.getContextPath() %>/acdNewApplication/checkRegister.action";
	        	var param = "?applTypeId=" + applTypeId + "&idTypeId=" + idTypeId + "&idNo=" + idNo + "&fullname=" + encodeURI((encodeURI(fullname)))+"&mobileNo="+mobileNo;
	        	//alert(no_url + no_param);
	        	var registerInd = false;
	        	$.ajax({
	        		type:"post",
	        		cache:false,
	        		processData:false,
	        		async:false,
	        		contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        		url:url + param,
	        		success:function(back){
	        			var data = eval("(" + back + ")");
	        			//$("#cardNo").attr("value",data);
	        			if(data.msg == "register"){
	        				registerInd=true;
	        			}else{
	        				if(window.confirm("该进件未登记是否继续？")) {
	        					registerInd = true;
	        				} else {
	        					registerInd = false;
	        				}
	        			}
	        		},
	        		error:function(){
	        			alert("系统错误......");
	        			registerInd = false;
	        		}
	        	});
	        	//alert("登记情况="+registerInd);
	        	return registerInd;
		  	}
		  	
		  	//验证副卡卡产品必填
		    function requiredProduct(applProductId1) {
		    	var parent = applProductId1.parentNode;
		      	var span = document.createElement('SPAN');
		      	var bol = false;
		    	if(applProductId1.value == "" || applProductId1.value == null) {
		    		bol=true;
					span.innerHTML = "<font color='red'size='1'>请选择卡产品代码</font>";
					showSpan(span,bol,parent);
					return false;
		    	} else {
		    		bol=false;
		      		showSpan(span,bol,parent);
					return true;
		    	}
		    }
		    
		    
		    //直系亲属/其他联系人手机号码验证
		      function checkMobileNo(MN){
		      	var parent = MN.parentNode;
		      	var span = document.createElement('SPAN');
		      	var bol = false;
				var temp = /^(13[0-9]|14[7]|15[0|1|2|3|5|6|7|8|9]|18[2|6|7|8|9])\d{8}$/;
				
				if(MN.value != "" && MN.value!=null){
					if(!temp.test(MN.value)){
						bol=true;
						span.innerHTML = "<font color='red'size='1'>请输入正确的手机号码</font>";
						showSpan(span,bol,parent);
						return false;
					}else{
						bol=false;
			      		showSpan(span,bol,parent);
						return true;
					}
				}
		      }
		        
		    //表单提交
		  //表单提交
		    function formSubmit(){
		    	var b_limit = document.getElementById("b_limit");
		    	var id_no1 = document.getElementById("id_no1");
		    	var expiryDate = document.getElementById("expiryDate");
		    	var b_chinese = document.getElementById("b_chinese");
		    	var aliasName = document.getElementById("aliasName");
		    	var dateOfBorn = document.getElementById("dateOfBorn");
		   // 	var district = document.getElementById("district");
		    	var addressLine= document.getElementById("addressLine");
		    	var addressPhone1= document.getElementById("addressPhone1");
		    	var nm_mobile_no = document.getElementById("nm_mobile_no");
		    	var b_companyName = document.getElementById("b_companyName");
		    	var serverYear = document.getElementById("serverYear");
		    	var annual_imcome = document.getElementById("annual_imcome");
		    	var companyFax= document.getElementById("companyFax");
		    	var companyPhone= document.getElementById("companyPhone");
		    	var r_contactName= document.getElementById("r_contactName");
		 //   	var r_telNo = document.getElementById("r_telNo");
		    	var o_contactName= document.getElementById("o_contactName");
		 //   	var o_telNo= document.getElementById("o_telNo");
		    	var o_mobileNo= document.getElementById("o_mobileNo");
		    	var o_homeAddr= document.getElementById("o_homeAddr");
		  //  	var nm_email_address = document.getElementById("nm_email_address");
		   // 	var pin_verifcation = document.getElementById("pin_verifcation");
		    	var b_cardCollectionCd = document.getElementById("b_cardCollectionCd");
		    	var b_localAutopayInd = document.getElementById("b_localAutopayInd");
		    	var applProductId = document.getElementById("applProductId");
		    	
		    	var validateInd = false;
		    	
		    	if(requiredProduct(applProductId) && validateLimit(b_limit) && validateIdNo(id_no1,"id_no1") && validateDate(expiryDate) && validateChinese(b_chinese) && exchanged(aliasName)
		    		&& validateDate(dateOfBorn) && validateAddress(addressLine) && validateServiceYear(serverYear)
		    		&& validatePhone1(addressPhone1) && validateNmMobile(nm_mobile_no) && validateCompany(b_companyName) && validateServiceYear(serverYear)
		    		&& validateCompanyFax(companyFax) && validatePhone1(companyPhone)  
		    		&& validateChinese(r_contactName) && validateChinese(o_contactName)
		    		&& validateLocalAutoPay(b_localAutopayInd) && validateDeliverBranch(b_cardCollectionCd,"b_deliveryBranchId"))
		    	{
		    		
		    		validateInd = true;
		    	}
		    	if(!validateInd) {
		    		alert("录入信息填写不完整请按要求重新填写");
		    	}
		    	return validateInd;
		    }

		    
		 
		 /*
		  * 以下是申请副卡保存时的js方法
		  */
		    //校验主卡是否存在
		  	function checkBasicCardNo(url) {
		  	//	alert(url);
		  		var reinputInd = $("#reinputInd").val();
	        	if(reinputInd == "Y") {
	        		return true;
	        	}
		        var basicCardNo = $("#basicCardNo").val();
		        if(basicCardNo=="") {
		        	alert("请输入主卡号！");
		        	return false;
		        }
				var param = "?basicCardNo=" + basicCardNo;
				//	alert(url + param);
				var haveCardInd = false;
				$.ajax({
					type : "post",
					cache : false,
					async : false,
					processData : false,
					url : url + param,
					success : function(back) {
						//	alert(back);
						var data = eval("(" + back + ")");
						if (data.error == "ERROR") {
							alert("卡号不存在！");
							haveCardInd = false;
						} else {
							$("#applProductId1").attr("value", data.msg);
							document.getElementById("applProductId1").value = data.msg;
							haveCardInd = true;
						}
					},
					error : function() {
						alert("系统错误......");
						haveCardInd = false;
					}
				});
			return haveCardInd;
		}

		//副卡表单提交
		function suppEnteringSubmit() {
			var s_limit = document.getElementById("s_limit");
			var id_no3 = document.getElementById("id_no3");
			var expiryDate3 = document.getElementById("expiryDate3");
			var s_chinese = document.getElementById("s_chinese");
			var aliasName1 = document.getElementById("aliasName1");
			var dateOfBorn1 = document.getElementById("dateOfBorn1");
			var homeAddressPhone = document.getElementById("homeAddressPhone");
			var s_nmMobileNo = document.getElementById("s_nmMobileNo");
			var s_companyName = document.getElementById("s_companyName");
			var companyContactPhone = document
					.getElementById("companyContactPhone");
			var s_cardCollectionCd = document.getElementById("s_cardCollectionCd");
			var companyAddressPhone = document
					.getElementById("companyAddressPhone");
			var applProductId1 = document.getElementById("applProductId1");
			var validateInd = false;
		
			if (requiredProduct(applProductId1) && validateLimit(s_limit) && validateIdNo(id_no3,"id_no3")
					&& validateDate(expiryDate3) && validateChinese(s_chinese)
					&& exchanged(aliasName1) && validateDate(dateOfBorn1)
					&& validatePhone1(homeAddressPhone)
					&& validateNmMobile(s_nmMobileNo)
					&& validateCompany(s_companyName)
					&& validatePhone1(companyContactPhone)
					&& validateDeliverBranch(s_cardCollectionCd,"s_deliveryBranchId")) {
				copyValue(companyContactPhone, companyAddressPhone);
				validateInd = true;
			}
			if(!validateInd) {
				alert("录入信息填写不完整请按要求重新填写");
			}
			return validateInd;
		}
		
		//复制信息
		function copyValue(a, b) {
			b.value = a.value;
		}
		
		

		
		
		 /*
	      *进行汉字转换
	      */
	      function hansToPY(arg,pyId) {
              var msg = isChinese(arg);
              if (!msg) {
               //   alert("请输入需要转换的内容！");
                  return;
              }
              var str = toPinyinOnly(arg);
              document.getElementById(pyId).value = str;
          }
          
          function isChinese(str) {
              if ("" == str) {
                  return false;
              }
              return true;
          }
          
          //进行汉字转换
          function pinyin(chars){
              var spellArray = new Array();
              var tx = chars
              execScript("ascCode=hex(asc(\""+chars+"\"))", "vbscript");
              ascCode = eval("0x"+ascCode);
              if(event.keyCode==13) event.keyCode=9; 
              else if(!chars.charCodeAt(0) || chars.charCodeAt(0) < 1328){
                  return tx;
              } else if (!(ascCode > 0xB0A0 && ascCode < 0xD7FC)){
                  return tx;
              } else {
                 for (var i = ascCode; !spell[i] && i > 0;) i--;
                 return spell[i];
              }
          }
          
          function toPinyinOnly(str) {
              var pStr = ""
              for (var i = 0; i < str.length; i++) {
                  if (str.charAt(i) == "\r") {//重要！解决回车输入的Bug！！
                      pStr += "\r";
                      i++;
                  } else {
                      pStr += pinyin(str.charAt(i))+" ";
                  }
              }
              return pStr.toUpperCase();
          }
          
         var spell = { 
			    0xB0A1: "a", 
			    0xB0A3: "ai", 
			    0xB0B0: "an", 
			    0xB0B9: "ang", 
			    0xB0BC: "ao", 
			    0xB0C5: "ba", 
			    0xB0D7: "bai", 
			    0xB0DF: "ban", 
			    0xB0EE: "bang", 
			    0xB0FA: "bao", 
			    0xB1AD: "bei", 
			    0xB1BC: "ben", 
			    0xB1C0: "beng", 
			    0xB1C6: "bi", 
			    0xB1DE: "bian", 
			    0xB1EA: "biao", 
			    0xB1EE: "bie", 
			    0xB1F2: "bin", 
			    0xB1F8: "bing", 
			    0xB2A3: "bo", 
			    0xB2B8: "bu", 
			    0xB2C1: "ca", 
			    0xB2C2: "cai", 
			    0xB2CD: "can", 
			    0xB2D4: "cang", 
			    0xB2D9: "cao", 
			    0xB2DE: "ce", 
			    0xB2E3: "ceng", 
			    0xB2E5: "cha", 
			    0xB2F0: "chai", 
			    0xB2F3: "chan", 
			    0xB2FD: "chang", 
			    0xB3AC: "chao", 
			    0xB3B5: "che", 
			    0xB3BB: "chen", 
			    0xB3C5: "cheng", 
			    0xB3D4: "chi", 
			    0xB3E4: "chong", 
			    0xB3E9: "chou", 
			    0xB3F5: "chu", 
			    0xB4A7: "chuai", 
			    0xB4A8: "chuan", 
			    0xB4AF: "chuang", 
			    0xB4B5: "chui", 
			    0xB4BA: "chun", 
			    0xB4C1: "chuo", 
			    0xB4C3: "ci", 
			    0xB4CF: "cong", 
			    0xB4D5: "cou", 
			    0xB4D6: "cu", 
			    0xB4DA: "cuan", 
			    0xB4DD: "cui", 
			    0xB4E5: "cun", 
			    0xB4E8: "cuo", 
			    0xB4EE: "da", 
			    0xB4F4: "dai", 
			    0xB5A2: "dan", 
			    0xB5B1: "dang", 
			    0xB5B6: "dao", 
			    0xB5C2: "de", 
			    0xB5C5: "deng", 
			    0xB5CC: "di", 
			    0xB5DF: "dian", 
			    0xB5EF: "diao", 
			    0xB5F8: "die", 
			    0xB6A1: "ding", 
			    0xB6AA: "diu", 
			    0xB6AB: "dong", 
			    0xB6B5: "dou", 
			    0xB6BC: "du", 
			    0xB6CB: "duan", 
			    0xB6D1: "dui", 
			    0xB6D5: "dun", 
			    0xB6DE: "duo", 
			    0xB6EA: "e", 
			    0xB6F7: "en", 
			    0xB6F8: "er", 
			    0xB7A2: "fa", 
			    0xB7AA: "fan", 
			    0xB7BB: "fang", 
			    0xB7C6: "fei", 
			    0xB7D2: "fen", 
			    0xB7E1: "feng", 
			    0xB7F0: "fo", 
			    0xB7F1: "fou", 
			    0xB7F2: "fu", 
			    0xB8C1: "ga", 
			    0xB8C3: "gai", 
			    0xB8C9: "gan", 
			    0xB8D4: "gang", 
			    0xB8DD: "gao", 
			    0xB8E7: "ge", 
			    0xB8F8: "gei", 
			    0xB8F9: "gen", 
			    0xB8FB: "geng", 
			    0xB9A4: "gong", 
			    0xB9B3: "gou", 
			    0xB9BC: "gu", 
			    0xB9CE: "gua", 
			    0xB9D4: "guai", 
			    0xB9D7: "guan", 
			    0xB9E2: "guang", 
			    0xB9E5: "gui", 
			    0xB9F5: "gun", 
			    0xB9F8: "guo", 
			    0xB9FE: "ha", 
			    0xBAA1: "hai", 
			    0xBAA8: "han", 
			    0xBABB: "hang", 
			    0xBABE: "hao", 
			    0xBAC7: "he", 
			    0xBAD9: "hei", 
			    0xBADB: "hen", 
			    0xBADF: "heng", 
			    0xBAE4: "hong", 
			    0xBAED: "hou", 
			    0xBAF4: "hu", 
			    0xBBA8: "hua", 
			    0xBBB1: "huai", 
			    0xBBB6: "huan", 
			    0xBBC4: "huang", 
			    0xBBD2: "hui", 
			    0xBBE7: "hun", 
			    0xBBED: "huo", 
			    0xBBF7: "ji", 
			    0xBCCE: "jia", 
			    0xBCDF: "jian", 
			    0xBDA9: "jiang", 
			    0xBDB6: "jiao", 
			    0xBDD2: "jie", 
			    0xBDED: "jin", 
			    0xBEA3: "jing", 
			    0xBEBC: "jiong", 
			    0xBEBE: "jiu", 
			    0xBECF: "ju", 
			    0xBEE8: "juan", 
			    0xBEEF: "jue", 
			    0xBEF9: "jun", 
			    0xBFA6: "ka", 
			    0xBFAA: "kai", 
			    0xBFAF: "kan", 
			    0xBFB5: "kang", 
			    0xBFBC: "kao", 
			    0xBFC0: "ke", 
			    0xBFCF: "ken", 
			    0xBFD3: "keng", 
			    0xBFD5: "kong", 
			    0xBFD9: "kou", 
			    0xBFDD: "ku", 
			    0xBFE4: "kua", 
			    0xBFE9: "kuai", 
			    0xBFED: "kuan", 
			    0xBFEF: "kuang", 
			    0xBFF7: "kui", 
			    0xC0A4: "kun", 
			    0xC0A8: "kuo", 
			    0xC0AC: "la", 
			    0xC0B3: "lai", 
			    0xC0B6: "lan", 
			    0xC0C5: "lang", 
			    0xC0CC: "lao", 
			    0xC0D5: "le", 
			    0xC0D7: "lei", 
			    0xC0E2: "leng", 
			    0xC0E5: "li", 
			    0xC1A9: "lia", 
			    0xC1AA: "lian", 
			    0xC1B8: "liang", 
			    0xC1C3: "liao", 
			    0xC1D0: "lie", 
			    0xC1D5: "lin", 
			    0xC1E1: "ling", 
			    0xC1EF: "liu", 
			    0xC1FA: "long", 
			    0xC2A5: "lou", 
			    0xC2AB: "lu", 
			    0xC2BF: "lv", 
			    0xC2CD: "luan", 
			    0xC2D3: "lue", 
			    0xC2D5: "lun", 
			    0xC2DC: "luo", 
			    0xC2E8: "ma", 
			    0xC2F1: "mai", 
			    0xC2F7: "man", 
			    0xC3A2: "mang", 
			    0xC3A8: "mao", 
			    0xC3B4: "me", 
			    0xC3B5: "mei", 
			    0xC3C5: "men", 
			    0xC3C8: "meng", 
			    0xC3D0: "mi", 
			    0xC3DE: "mian", 
			    0xC3E7: "miao", 
			    0xC3EF: "mie", 
			    0xC3F1: "min", 
			    0xC3F7: "ming", 
			    0xC3FD: "miu", 
			    0xC3FE: "mo", 
			    0xC4B1: "mou", 
			    0xC4B4: "mu", 
			    0xC4C3: "na", 
			    0xC4CA: "nai", 
			    0xC4CF: "nan", 
			    0xC4D2: "nang", 
			    0xC4D3: "nao", 
			    0xC4D8: "ne", 
			    0xC4D9: "nei", 
			    0xC4DB: "nen", 
			    0xC4DC: "neng", 
			    0xC4DD: "ni", 
			    0xC4E8: "nian", 
			    0xC4EF: "niang", 
			    0xC4F1: "niao", 
			    0xC4F3: "nie", 
			    0xC4FA: "nin", 
			    0xC4FB: "ning", 
			    0xC5A3: "niu", 
			    0xC5A7: "nong", 
			    0xC5AB: "nu", 
			    0xC5AE: "nv", 
			    0xC5AF: "nuan", 
			    0xC5B0: "nue", 
			    0xC5B2: "nuo", 
			    0xC5B6: "o", 
			    0xC5B7: "ou", 
			    0xC5BE: "pa", 
			    0xC5C4: "pai", 
			    0xC5CA: "pan", 
			    0xC5D2: "pang", 
			    0xC5D7: "pao", 
			    0xC5DE: "pei", 
			    0xC5E7: "pen", 
			    0xC5E9: "peng", 
			    0xC5F7: "pi", 
			    0xC6AA: "pian", 
			    0xC6AE: "piao", 
			    0xC6B2: "pie", 
			    0xC6B4: "pin", 
			    0xC6B9: "ping", 
			    0xC6C2: "po", 
			    0xC6CB: "pu", 
			    0xC6DA: "qi", 
			    0xC6FE: "qia", 
			    0xC7A3: "qian", 
			    0xC7B9: "qiang", 
			    0xC7C1: "qiao", 
			    0xC7D0: "qie", 
			    0xC7D5: "qin", 
			    0xC7E0: "qing", 
			    0xC7ED: "qiong", 
			    0xC7EF: "qiu", 
			    0xC7F7: "qu", 
			    0xC8A6: "quan", 
			    0xC8B1: "que", 
			    0xC8B9: "qun", 
			    0xC8BB: "ran", 
			    0xC8BF: "rang", 
			    0xC8C4: "rao", 
			    0xC8C7: "re", 
			    0xC8C9: "ren", 
			    0xC8D3: "reng", 
			    0xC8D5: "ri", 
			    0xC8D6: "rong", 
			    0xC8E0: "rou", 
			    0xC8E3: "ru", 
			    0xC8ED: "ruan", 
			    0xC8EF: "rui", 
			    0xC8F2: "run", 
			    0xC8F4: "ruo", 
			    0xC8F6: "sa", 
			    0xC8F9: "sai", 
			    0xC8FD: "san", 
			    0xC9A3: "sang", 
			    0xC9A6: "sao", 
			    0xC9AA: "se", 
			    0xC9AD: "sen", 
			    0xC9AE: "seng", 
			    0xC9AF: "sha", 
			    0xC9B8: "shai", 
			    0xC9BA: "shan", 
			    0xC9CA: "shang", 
			    0xC9D2: "shao", 
			    0xC9DD: "she", 
			    0xC9E9: "shen", 
			    0xC9F9: "sheng", 
			    0xCAA6: "shi", 
			    0xCAD5: "shou", 
			    0xCADF: "shu", 
			    0xCBA2: "shua", 
			    0xCBA4: "shuai", 
			    0xCBA8: "shuan", 
			    0xCBAA: "shuang", 
			    0xCBAD: "shui", 
			    0xCBB1: "shun", 
			    0xCBB5: "shuo", 
			    0xCBB9: "si", 
			    0xCBC9: "song", 
			    0xCBD1: "sou", 
			    0xCBD4: "su", 
			    0xCBE1: "suan", 
			    0xCBE4: "sui", 
			    0xCBEF: "sun", 
			    0xCBF2: "suo", 
			    0xCBFA: "ta", 
			    0xCCA5: "tai", 
			    0xCCAE: "tan", 
			    0xCCC0: "tang", 
			    0xCCCD: "tao", 
			    0xCCD8: "te", 
			    0xCCD9: "teng", 
			    0xCCDD: "ti", 
			    0xCCEC: "tian", 
			    0xCCF4: "tiao", 
			    0xCCF9: "tie", 
			    0xCCFC: "ting", 
			    0xCDA8: "tong", 
			    0xCDB5: "tou", 
			    0xCDB9: "tu", 
			    0xCDC4: "tuan", 
			    0xCDC6: "tui", 
			    0xCDCC: "tun", 
			    0xCDCF: "tuo", 
			    0xCDDA: "wa", 
			    0xCDE1: "wai", 
			    0xCDE3: "wan", 
			    0xCDF4: "wang", 
			    0xCDFE: "wei", 
			    0xCEC1: "wen", 
			    0xCECB: "weng", 
			    0xCECE: "wo", 
			    0xCED7: "wu", 
			    0xCEF4: "xi", 
			    0xCFB9: "xia", 
			    0xCFC6: "xian", 
			    0xCFE0: "xiang", 
			    0xCFF4: "xiao", 
			    0xD0A8: "xie", 
			    0xD0BD: "xin", 
			    0xD0C7: "xing", 
			    0xD0D6: "xiong", 
			    0xD0DD: "xiu", 
			    0xD0E6: "xu", 
			    0xD0F9: "xuan", 
			    0xD1A5: "xue", 
			    0xD1AB: "xun", 
			    0xD1B9: "ya", 
			    0xD1C9: "yan", 
			    0xD1EA: "yang", 
			    0xD1FB: "yao", 
			    0xD2AC: "ye", 
			    0xD2BB: "yi", 
			    0xD2F0: "yin", 
			    0xD3A2: "ying", 
			    0xD3B4: "yo", 
			    0xD3B5: "yong", 
			    0xD3C4: "you", 
			    0xD3D9: "yu", 
			    0xD4A7: "yuan", 
			    0xD4BB: "yue", 
			    0xD4C5: "yun", 
			    0xD4D1: "za", 
			    0xD4D4: "zai", 
			    0xD4DB: "zan", 
			    0xD4DF: "zang", 
			    0xD4E2: "zao", 
			    0xD4F0: "ze", 
			    0xD4F4: "zei", 
			    0xD4F5: "zen", 
			    0xD4F6: "zeng", 
			    0xD4FA: "zha", 
			    0xD5AA: "zhai", 
			    0xD5B0: "zhan", 
			    0xD5C1: "zhang", 
			    0xD5D0: "zhao", 
			    0xD5DA: "zhe", 
			    0xD5E4: "zhen", 
			    0xD5F4: "zheng", 
			    0xD6A5: "zhi", 
			    0xD6D0: "zhong", 
			    0xD6DB: "zhou", 
			    0xD6E9: "zhu", 
			    0xD7A5: "zhua", 
			    0xD7A7: "zhuai", 
			    0xD7A8: "zhuan", 
			    0xD7AE: "zhuang", 
			    0xD7B5: "zhui", 
			    0xD7BB: "zhun", 
			    0xD7BD: "zhuo", 
			    0xD7C8: "zi", 
			    0xD7D7: "zong", 
			    0xD7DE: "zou", 
			    0xD7E2: "zu", 
			    0xD7EA: "zuan", 
			    0xD7EC: "zui", 
			    0xD7F0: "zun", 
			    0xD7F2: "zuo" 
			}; 
       //格式化数字100.00-->100
 		function cutDouble(number){
 			if(number == null || number == "")
 				return "";
        	 var pointIndex = number.indexOf(".");
        	 if(pointIndex > 0){
        		 return number.substring(0,pointIndex);
        	 }else{
        		 return "";
        	 }
         }
         function cutDate(time) {
        	 var blankIndex = time.indexOf(" ");
        	 if(blankIndex > 0){
        		 return time.substring(0,blankIndex);
        	 }
        	 return time;
         }
         
         