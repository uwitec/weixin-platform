$(function() {
	var test = document.createElement("input");
	test.setAttribute("type", "date");
	if (test.type === "text") {
		//浏览器不支持日期控件，使用JQuery的日期控件
		$('input[type=date]').datepicker();
		$('input[type=date]').css('width', '70px');
	}
});

function makeTable(k){
	$("#"+k+"[hover=true] table.data tbody tr").hover(function(){$(this).addClass("hover")},function(){$(this).removeClass("hover")});
	function g(){
		$("#"+k+"[stripe=true] table.data tbody tr").removeClass("odd").removeClass("even");
		$("#"+k+"[stripe=true] table.data tbody tr:nth-child(odd)").addClass("odd");
		$("#"+k+"[stripe=true] table.data tbody tr:nth-child(even)").addClass("even")
	}
	g();
	var h=$("#"+k+"[resizable=true]");
	var f=$("#"+k+"[resizable=true] table.data thead tr th");
	h.resizable({maxHeight:h.height(),minWidth:h.width()});
	f.resizable({maxHeight:f.height(),minWidth:f.width(),autoHide:true});
	h=null;f=null;var b=$("#"+k+" table.data thead tr th");
	var d=$("#"+k+" table.data tbody tr td")
	;var e=$("#"+k+" table.data tbody");
	var a=-1;var i="ASC";var j=k+"sortIconSpan";var l='<span id="'+j+'" class="ui-icon ui-icon-triangle-1-n" style="display:inline"></span>';var c='<span id="'+j+'" class="ui-icon ui-icon-triangle-1-s" style="display:inline"></span>';$("#"+j+"").remove();b.each(function(m){this.onclick=function(){if(!b.get(m).sort){return}if(a!=m){i="ASC";a=m}else{i=(i=="ASC")?"DESC":"ASC"}var r=d.filter("td:nth-child("+(m+1)+")");var q=b.get(m).attributes("sort").value;var t=new Array();var p=0;r.each(function(){t[p++]=$(this).text()});if(q=="number"){t.sort(compareNumber)}else{if(q=="date"){t.sort(compareDate)}else{t.sort()}}if(i=="DESC"){t.reverse()}for(var p=0;p<t.length;p++){var o=e.attr("childNodes");for(var n=0;n<o.length-p;n++){if(o[n].childNodes[m].innerText==t[p]){e.append(o[n]);break}}}$("#"+j+"").remove();var s=$((i=="ASC")?l:c);s.insertAfter(b.get(m).childNodes[0]);g()}})}	