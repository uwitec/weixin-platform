package com.xuchunchun.abframe.util.compare;

import java.util.Comparator;

import com.xuchunchun.abframe.db.entity.AclMenutree;


public class AclMenutreeCompare implements Comparator<AclMenutree>{


	@Override
	public int compare(AclMenutree m1, AclMenutree m2) {
		return (int) (m1.getNodeSeq() - m2.getNodeSeq());
	}
	
}
