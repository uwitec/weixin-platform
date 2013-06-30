/**abframe开发中用到的一些JS
 * auth：Dai
 * 2012-3-28
 */
var abf = {version:1.0};

/**设置参数
 * 可用控件 grid
 *obj:要操作的对象
 *newparms：参数数组
 */
 abf.setParms = function(obj,newparms){
     var parms = newparms;
     obj.set('parms',parms);
};

/**追加新参数
 * 可用控件 grid
 *obj:要操作的对象
 *newparms：添加的数组
 */
abf.addParms = function(obj,newparms){
     var old_parms = obj.get('parms');
     var parms = old_parms.concat(newparms);
     obj.set('parms',parms);
};
/**
 * 查询参数 id为包含查询表单的div
 */
abf.p_parms = function(id){
    var $search = $('#'+id);
    var parms = [];
    $search.find('input,select').each(function(i){
        $this = $(this);
        var idString = $this.attr('id');
        var nameString = $this.attr('name');
        var valueString = $this.val();
        parms[i] = {name:nameString,value:valueString};
    });
    return parms;
};

/**
 * 显示成功提示窗口
 */
abf.showSuccess = function (message, callback){
    if (typeof (message) == "function" || arguments.length == 0)
    {
        callback = message;
        message = "操作成功!";
    }
    $.ligerDialog.success(message, '提示信息', callback);
};

/**
 * 显示失败提示窗口
 */
abf.showError = function (message, callback)
{
    if (typeof (message) == "function" || arguments.length == 0)
    {
        callback = message;
        message = "操作失败!";
    }
    $.ligerDialog.error(message, '提示信息', callback);
};
/**
 * 添加新的tab页
 */
abf.addTab = function(tabid,text, url)
{
	tab.addTabItem({ tabid : tabid,text: text, url: url });
};
/**
 * 删除行
 * 可用控件 grid
 * 可用以下方式调用
  function p_deleteRow()
	{
	   var obj = g; //操作的表格对象
	   var selected = obj.getSelected();//取得行数据
	   if(selected==null){
	       $.ligerDialog.warn("请选择一行");
	       return;
	   }
	   var url = '<%=request.getContextPath()%>/aclSysparameterAction/delete.action'; //action
	   var delData = {'aclSysparameter\.bankorgId':selected.bankorgId};//删除的数据
	   var callback = p_getlist();//回调函数
	   abf.deleterow(url,delData,callback);
	}
 * 
/**
 * 删除表格的行，
 
abf.deleterow = function(url,delData,callback){
    $.ajax({
       cache:false,//设置false，不缓存页面
       data:delData,
       url:url,
       success: function () {
           abf.showSuccess('删除成功');
           callback;
       },
       error: function (message) {
           abf.showError(message);
       }
    });
};
 */

//快速设置表单底部默认的按钮:保存、取消
abf.setFormDefaultBtn = function (cancleCallback, savedCallback)
{
    //表单底部按钮
    var buttons = [];
    if (cancleCallback)
    {
        buttons.push({ text: '取消', onclick: cancleCallback });
    }
    if (savedCallback)
    {
        buttons.push({ text: '保存', onclick: savedCallback });
    }
    abf.addFormButtons(buttons);
};

//增加表单底部按钮,比如：保存、取消
abf.addFormButtons = function (buttons)
{
    if (!buttons) return;
    var formbar = $("body > div.form-bar");
    if (formbar.length == 0)
        formbar = $('<div class="form-bar"><div class="form-bar-inner"></div></div>').appendTo('body');
    if (!(buttons instanceof Array))
    {
        buttons = [buttons];
    }
    $(buttons).each(function (i, o)
    {
        var btn = $('<div class="l-dialog-btn"><div class="l-dialog-btn-l"></div><div class="l-dialog-btn-r"></div><div class="l-dialog-btn-inner"></div></div> ');
        $("div.l-dialog-btn-inner:first", btn).html(o.text || "BUTTON");
        if (o.onclick)
        {
            btn.bind('click', function ()
            {
                o.onclick(o);
            });
        }
        if (o.width)
        {
            btn.width(o.width);
        }
        $("> div:first", formbar).append(btn);
    });
};
/**
 * 表单验证绑定 
 * form.valid()方法可返布尔值，
 * if(!form.valid()){}表单验证是否通过
 * 表单里写:validate="{required:true,minlength:1,maxlength:10,digits:true,number:true,min:1,max:2}" 
 * 最后一个不能有分号（;）区分大小写,表单输入框的需要id属性，id属性值不能带点(.)
 */
abf.validateBindForm = function(form){
    $.validator.addMethod(
            "notnull",
        function (value, element, regexp)
        {
            if (!value) return true;
            return !$(element).hasClass("l-text-field-null");
        },
        "不能为空"
    );
    $.metadata.setType("attr", "validate");
	var v = form.validate({
	    //调试状态，不会提交数据的
	    //debug: true,
	    errorPlacement: function (lable, element)
	    {
	 
	        if (element.hasClass("l-textarea"))
	        {   
	            element.addClass("l-textarea-invalid");
	        }
	        else if (element.hasClass("l-text-field"))
	        {
	            element.parent().addClass("l-text-invalid");
	        }
	        $(element).removeAttr("title").ligerHideTip();
	        $(element).attr("title", lable.html()).ligerTip();
	    },
	    success: function (lable)
	    {
	        var element = $("#" + lable.attr("for"));
	        if (element.hasClass("l-textarea"))
	        {
	            element.removeClass("l-textarea-invalid");
	        }
	        else if (element.hasClass("l-text-field"))
	        {
	            element.parent().removeClass("l-text-invalid");
	        }
	        $(element).removeAttr("title").ligerHideTip();
	    }
	});
	form.ligerForm();
};
/**
 * 时间对象的格式化
 */
Date.prototype.format = function(format) {
 /*
  * format="yyyy-MM-dd hh:mm:ss";
  */
 var o = {
  "M+" : this.getMonth() + 1,
  "d+" : this.getDate(),
  "h+" : this.getHours(),
  "m+" : this.getMinutes(),
  "s+" : this.getSeconds(),
  "q+" : Math.floor((this.getMonth() + 3) / 3),
  "S" : this.getMilliseconds()
 };
 if (/(y+)/.test(format)) {
  format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
      - RegExp.$1.length));
 }
 for (var k in o) {
  if (new RegExp("(" + k + ")").test(format)) {
   format = format.replace(RegExp.$1, RegExp.$1.length == 1
       ? o[k]
       : ("00" + o[k]).substr(("" + o[k]).length));
  }
 }
 return format;
} ;
/**
 * 格式化后台返回的json日期时间格式
 */
abf.formateTime = function(obj){
//	var date = new Date();
//	 date.setTime(obj.time);
//	 date.setHours(obj.hours);
//	 date.setMinutes(obj.minutes);
//	 date.setSeconds(obj.seconds);
//	 return date.format("yyyy-MM-dd hh:mm:ss");  
	return obj.toString();
};
/**
 * 格式化后台返回的json日期格式
 */
abf.formateDate = function(obj){
//	var date = new Date();
//	 date.setTime(obj.time);
//	 date.setHours(obj.hours);
//	 date.setMinutes(obj.minutes);
//	 date.setSeconds(obj.seconds);
//	 return date.format("yyyy-MM-dd");
	return obj.toString().substring(0, 10);
};

/**
 * 关闭当前tab
 */
abf.closeCurrentTab = function (tabid)
{
    if (!tabid)
    {
        tabid = $("#framecenter > .l-tab-content > .l-tab-content-item:visible").attr("tabid");
    }
    if (tab)
    {
        tab.removeTabItem(tabid);
    }
};

/**
 * 将jason数组对象转换成查询字符串
 * json数组例子  var listAclRolemenus = {'listAclRolemenus':[{'modiUser':'wei','id.nodeId':'cc'},{'id.nodeId':'bb'}]};
 * 
 */
abf.arrayToQuery = function(jsonArray){
     var srcString = decodeURIComponent($.param(jsonArray,false));
     var result = '';
     var list = srcString.split('&'); 
     for(var i=0;i<list.length;i++){
         var everyString = list[i];
         var paramKey = everyString.substring(0,everyString.indexOf('='));
         var paramValue = everyString.substring(everyString.indexOf('='))+'&';
         var bracket = paramKey.indexOf(']')+1;
         var sub_prefix = paramKey.substring(0,bracket);
         var sub_suffix = paramKey.substring(bracket);
         sub_suffix = sub_suffix.replace('[','.');
         sub_suffix = sub_suffix.replace(']','');
         var resultevery = sub_prefix + sub_suffix + paramValue;
         result += resultevery;
     } 
     result = result.substring(0,result.length-1);
     return result;
 };
 
 /**
  * function getSelectedRows(){
        //1.定义要操作的实体，在后台要定义这个list形式的变量(LIST)//12.定义要操作的对象的数据结构，是个数组 
        var entity = 'aclChildsystem'; //1
        var struct = ['id.systemId','id.bankorgId']; //2
     }
     参数obj是grid对象
  */
abf.qListString = function(obj,entity,struct){
     var rows = obj.getSelectedRows();
     var selArray = [];
      //定义每个要操作的对象
      for(var i=0;i<rows.length;i++){
          var row = rows[i];
          var objt = new Object();
          for(var j=0;j<struct.length;j++){
              var attr = struct[j];
              var attrString = 'row'+'.'+attr;
              var value = eval(attrString)||'';
              objt[attr] = value;
          }
          selArray.push(objt);
      }
      var returnObj = {};
      returnObj[entity] = [];
      returnObj[entity] = selArray;
      return abf.arrayToQuery(returnObj);
};
/**
 * function getSelectedRows(){
       //1.定义要操作的实体，在后台要定义这个变量//12.定义要操作的对象的数据结构，是个数组 
       var entity = 'aclChildsystem'; //1
       var struct = ['id.systemId','id.bankorgId']; //2
    }
    参数obj是grid对象
 */
abf.qObjString = function(obj,entity,struct){
    var row = obj.getSelected();
     //定义每个要操作的对象
    var objt = new Object();
    for(var j=0;j<struct.length;j++){
        var attr = struct[j];
        var attrString = 'row'+'.'+attr;
        var value = eval(attrString)||'';
        objt[entity+'.'+attr] = value;
    }
    return decodeURIComponent($.param(objt,false));    
};
//按钮固定在底部
abf.formbar = function(){
	$(".pageContent:first").height($("body").height()-40).css("overflow:scroll"); //底部固定
};
//窗口大小改变时触发
abf.resize = function(){
	$(window).resize(function(){
	    $(".pageContent:first").height($("body").height()-40).css("overflow:scroll");
	});
};
/*关闭指定的标签页*/
abf.tabClose = function(tabid){
	var tabTmp = window.parent.topTab;
	if(tabTmp.isTabItemExist(tabid)){
       tabTmp.removeTabItem(tabid);
	}
};

/**
 * 根据struct结构将没有ID的主键分装成id.xx的形式
 * obj是grid对象封装的ID字符串，如：<div id="maingrid"></div>，
 * strut是操作的对象，是数组形式，如：var struct = ['id.bankorgId','id.DepartmentID']
 * 返回 data，是json数组
 */
abf.getAllData = function(gridId,struct)
 {
     var structOb = [];
     for(var k=0;k<struct.length;k++){
         if(struct[k].substring(0,3)=='id.'){
             structOb.push(struct[k].substring(3));
         }
     }
     var manager = $("#"+gridId).ligerGetGridManager();
     var data = manager.getData();
     for(var i=0;i<data.length;i++){
         //alert(i+":"+JSON2.stringify(data[i]));
         for(var j=0;j<structOb.length;j++){
             var id = new Object();
             for(var ar in data[i]){
                 if(ar == structOb[j]){
                    //alert('({'+ar+":"+data[i][ar]+'})');
                    var idar = 'id.'+ar;
                    var idarVal= data[i][ar];
                    //id = eval('({'+ar+":"+data[i][ar]+'})');
                    delete data[i][ar];
                 }
                 data[i][idar] = idarVal;
             }
         }
         for(var h in data[i]){
        	 if(data[i].hasOwnProperty(h)){
        		 if(data[i][h]==null){
            		 delete data[i][h];
            	 }
        	 }
         }
     }
     //alert(JSON2.stringify(data));     
     return data;
 };
 /**
  * 主从表的子表，combo若有ID的转换
  */
abf.idTransaction = function(data){
      var newData = [];
      for(var i=0;i<data.length;i++){
          var row = new Object();
          row = data[i];
          if(row.hasOwnProperty('id')){
              for(var tmp in row['id']){
                  if(row['id'].hasOwnProperty(tmp)){
                      data[i][tmp] = row['id'][tmp];
                  }
              }
              delete data[i]['id'];
          }
      }
      //alert(JSON2.stringify(data));
      return data;
  };
  /**
   * 将后台传来的数据，转化成没有id的数据
   * data是数组
   * strut是操作的对象，是数组形式，如：var struct = ['id.bankorgId','id.DepartmentID']
   * 返回 data，是json数组
   */
  abf.getNoIdData = function(data,struct)
   {
	   var structOb = [];
	   for(var k=0;k<struct.length;k++){
	       if(struct[k].substring(0,3)=='id.'){
	           structOb.push(struct[k].substring(3));
	       }
	   }
	  // alert(JSON2.stringify(structOb));
      // alert(JSON2.stringify(data));
       for(var i=0;i<data.length;i++){
    	 if(data[i].hasOwnProperty('id')){
    		 for(var j=0;j<structOb.length;j++){
    			 data[i][structOb[j]] = data[i]['id'][structOb[j]];
    		 }
    		 delete data[i]['id'];
    	 }
       }
     //  alert(JSON2.stringify(data));     
       return data;
   };  
   
   /**
    * 将子表的ID（主键）数据转换成无ID的数据
    * dataString 是子表的数据（action返回，通过'${xx}'表达式取得）（json形式的字符串）
    * struct 定义子表的结构(主键)
    */
   abf.transformData = function(dataString,struct){
       
       if(dataString==null||dataString==''){
           dataString = "[]";
       }
       data = eval('('+dataString+')');
       data = abf.getNoIdData(data,struct);
       data = {Rows:data};
       return data;
   };