package com.xuchunchun.abframe.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.web.vo.AclMenutreeVo;

import com.xuchunchun.abframe.bean.ListMenuTreeBase;
import com.xuchunchun.abframe.db.dao.AclMenuTreeDao;
import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclMenutreeId;
import com.xuchunchun.abframe.service.AclMenuTreeService;
import com.xuchunchun.abframe.util.compare.AclMenutreeCompare;
import com.xuchunchun.abframe.util.compare.AclMenutreeCompareVo;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclMenuTreeAction")
public class AclMenuTreeAction extends DefaultAction{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private AclMenuTreeService aclMenuTreeService;
	@Autowired
	private AclMenuTreeDao aclMenutreeDao;
	private AclMenutree aclMenutree;
	private String topMenu;
	
	//加载树
	public void loadtree(){
		List<ListMenuTreeBase> list = aclMenuTreeService.allListTreeForm();
		returnJSONLISTOBJ(list);
	}
	//只加载父级的树
	public void loadparenttree(){
		List<ListMenuTreeBase> list = aclMenuTreeService.parentListMenuTreeForm();
		returnJSONLISTOBJ(list);
	}
	//跳转到添加顶级菜单的页面
	public String toaddtopmenu(){
		topMenu = "topMenu";
		return SUCCESS;
	}
	public void add(){
		aclMenuTreeService.add(aclMenutree);
		String ok = "{ok:'OK'}";
		returnJSON(ok);
	//	return SUCCESS;
	}
	
	public void update(){
		aclMenuTreeService.update(aclMenutree);
		String ok = "{ok:'OK'}";
		returnJSON(ok);
	//	return SUCCESS;
	}
	
	public void delete(){
		aclMenuTreeService.delete(aclMenutree);
	}
	
	public void gettable(){
		aclMenutree = aclMenuTreeService.detail(aclMenutree);
		String parentId = aclMenutree.getId().getNodeId();
		AclMenutreeVo aclVo = new AclMenutreeVo();
		aclVo.setId(aclMenutree.getId());
		aclVo.setFunctionId(aclMenutree.getFunctionId());
		aclVo.setNodeSeq(aclMenutree.getNodeSeq());
		aclVo.setNodeName(aclMenutree.getNodeName());
		aclVo.setIsLeaf("Y".equals(aclMenutree.getIsLeaf())?"是":"否");
		aclVo.setMenuType(aclMenutree.getMenuType());
		String parentName = "";
		if("0".equals(aclMenutree.getParentId()))
			parentName = "根节点";
		else{
			List<AclMenutree> aclMenus = aclMenutreeDao.findAll();
			if(aclMenus != null && aclMenus.size() > 0){
				for(AclMenutree acls : aclMenus){
					if(aclMenutree.getParentId().equals(acls.getId().getNodeId())){
						parentName = acls.getNodeName();
					}
				}
			}
		}
		aclVo.setParentId(parentName);
		
		List<AclMenutree> list = new ArrayList<AclMenutree>();
		List<AclMenutreeVo> listVos = new ArrayList<AclMenutreeVo>();
		if(!"Y".equalsIgnoreCase(aclMenutree.getIsLeaf())){
			AclMenutree aclMenutreeTmp = new AclMenutree();
			aclMenutreeTmp.setParentId(parentId);
			/*list = aclMenuTreeService.getlist(aclMenutreeTmp);
			Collections.sort(list, new AclMenutreeCompare());*/
			listVos = aclMenuTreeService.getlistVo(aclMenutreeTmp);
			Collections.sort(listVos, new AclMenutreeCompareVo());
		}
		/*list.add(0,aclMenutree);
		returnListJSON(list,new Long(0));*/
		listVos.add(0,aclVo);
		returnListJSON(listVos,new Long(0));
	}
	
	public String detail(){
		detailTag = "detail";
		aclMenutree = aclMenuTreeService.detail(aclMenutree);
		return SUCCESS;
	}
	
	

	public void setAclMenutree(AclMenutree aclMenutree) {
		this.aclMenutree = aclMenutree;
	}

	public AclMenutree getAclMenutree() {
		return aclMenutree;
	}
	public void setTopMenu(String topMenu) {
		this.topMenu = topMenu;
	}
	public String getTopMenu() {
		return topMenu;
	}
}
