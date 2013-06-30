package com.xuchunchun.abframe.util.compare;

import java.util.Comparator;

import com.xuchunchun.abframe.web.vo.AclMenutreeVo;


public class AclMenutreeCompareVo implements Comparator<AclMenutreeVo>{
	@Override
	public int compare(AclMenutreeVo m1, AclMenutreeVo m2) {
		return (int) (m1.getNodeSeq() - m2.getNodeSeq());
	}
}
