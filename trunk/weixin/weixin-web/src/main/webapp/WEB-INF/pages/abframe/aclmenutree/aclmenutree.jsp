<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
  
    <style type="text/css">
    .l-panel td.l-grid-row-cell-editing { padding-bottom: 2px;padding-top: 2px;}
    </style>
</head>
<body style="padding:2px;height:100%; text-align:center;">
  
  <div id="layout" style="width:100%">
    <div position="left" title="<fmt:message key="menulist"/>" id="mainmenu" >
        <div id="leftTree" style="height:400px;overflow-y:scroll;padding-bottom:10px" class="l-scroll">
            <ul id="maintree" ></ul>
        </div>
     </div>
    <div position="center" title="<fmt:message key="detailInfo"/>" style="width:100%"> 
        <form id="mainform" >
        <div id="maingrid"  style="margin-left:2px;" style="height:60%;width:98%"></div> 
        </form>
        <div id="formtable" style="border:1px solid #84a0c4 ;margin:2px;height:160px;width:99%">
        </div>
    </div>
  </div>
  
  <script type="text/javascript">
 
  var layout = $("#layout").ligerLayout({ leftWidth:'240',height:'100%',heightDiff:-34});
//树
  var tree = $("#maintree").ligerTree({
      url: '<%=request.getContextPath()%>/aclMenuTree/loadtree.action',
      checkbox: false,
      slide: false,
      nodeWidth: 120,
      attribute: ['nodename', 'url'],
      onSelect: function (node)
      {
    	  if (!node.data.nodeId) return;
    	  var queryString = 'aclMenutree.id.nodeId='+node.data.nodeId;
    	  var url = '<%=request.getContextPath()%>/aclMenuTree/gettable.action?'+queryString;
          grid.set('url',url);
      }
  });
  function reloadtree(){
	  var url = '<%=request.getContextPath()%>/aclMenuTree/loadtree.action';
	  tree.clear();
      tree.loadData(null, url);
  }
  
  var p_load= function(){
	  $("#formtable").load('<%=request.getContextPath()%>/aclMenuTree/toadd.action');
  };
  var p_toaddtopmenu = function(){
	  $("#formtable").load('<%=request.getContextPath()%>/aclMenuTree/toaddtopmenu.action');
  };
  function p_detail(data){
	  var nodeId = data.id.nodeId;
	  url = "<%=request.getContextPath()%>/aclMenuTree/detail.action?aclMenutree.id.nodeId="+nodeId;
	  $("#formtable").load(url);
  };
      function itemclick(item)
      {
          switch (item.id)
          {
              case "add":
            	  p_load();
                  break;
              case "toaddtopmenu":
            	  p_toaddtopmenu();
            	  break;
              case "update":
            	  f_update();
                  break;
              case "delete": 
                  f_delete();
                  break;
          }
      }
      
      function f_update(){
    	  var selected = grid.getSelected();
          if (!selected)
          {
        	  $.ligerDialog.alert("<fmt:message key="selectUpdateRow"/>");
          }else{
        	  
          }
      }
      
      function f_delete()
      { 
          var selected = grid.getSelected();
          if (!selected)
          {
        	  $.ligerDialog.alert("<fmt:message key="selectDeleteRow"/>");
          }else{
        	  $.ligerDialog.confirm('确定删除吗?', function (confirm) {
                  if (confirm){
                	  var queryString ='aclMenutree.id.nodeId='+selected.id.nodeId;
                	  grid.deleteRow(selected);
                	  $.ajax({
                		  type:'GET',
                		  url:'<%=request.getContextPath()%>/aclMenuTree/deletemenutree.action?'+queryString,
                		  success:function(){
                			  $.ligerDialog.alert("删除成功");
                			  reloadtree();
                		  },
                		  error:function(){
                			  $.ligerDialog.alert("操作失败");
                		  }
                	  });
                  };
              });
          }
      };
      var toolbarOptions = { 
        items: [ 
            { text: '新增',id:'add', click: itemclick, icon: 'add'}, 
            { line: true },
            { text: '新增顶级菜单',id:'toaddtopmenu', click: itemclick, icon: 'add'},
            { line: true },
            { text: '修改',id:'update', click: itemclick, icon: 'modify'},
            { line: true },
            { text: '删除',id:'delete', click: itemclick, icon: 'delete'} 
        ]
    };
     
    var grid = $("#maingrid").ligerGrid({
    	root:'rows',
        record:'total',
        columns: [
                { display: '节点Id', name: 'id.nodeId', align: 'left', width: 150, minWidth: 60
                , validate: { required: true }
                , editor: { type: 'text' }
                , render:function(item){
                	return item.id.nodeId;
                }
                },
                { display: '节点序号', name: 'nodeSeq', align: 'center', width: 60, minWidth: 60
                , validate: { required: true }
                , editor: { type: 'text' }
                },
                { display: '节点名称', name: 'nodeName', align: 'left', width: 150, minWidth: 60
                , validate: { required: true }
                , editor: { type: 'text' }
                }
                , { display: '父节点', name: 'parentId', align: 'left', width: 150, minWidth: 50
                , editor: { type: 'select'}
                },
                { display: '是否叶子节点', name: 'isLeaf', align: 'left', width: 150, minWidth: 50
                    , editor: { type: 'select'}
                    },
                { display: '功能ID', name: 'functionId', align: 'left', width: 150, minWidth: 60
                    , validate: { required: true }
                    , editor: { type: 'text' }
                    }
                ], dataAction: 'server', pageSize: 999, toolbar: toolbarOptions, sortName: 'MenuID',
        width: '98%', height: '60%', heightDiff: -5, checkbox: false, usePager: false, enabledEdit: true, clickToEdit: false,
        fixedCellHeight: true, rowHeight: 25,
        onSelectRow: function (data, rowindex, rowobj)
        {
        	p_detail(data);
        }
    });
  </script>
</body>
</html> 
