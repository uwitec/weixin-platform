package com.xuchunchun.weixin.platform.msg.rsp;

import java.util.ArrayList;

import com.xuchunchun.base.annotation.ResponseMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class ArticleMsgResponse extends PushMsg {
	
	@ResponseMsgField(nodeName="Articles",getValMethod="getArticles",isComp=true,isArray=true)
	private ArrayList<ArticleSubMsgResponse> articles = new ArrayList<ArticleSubMsgResponse>();
	
	@ResponseMsgField(nodeName="FuncFlag",getValMethod="getFuncFlag")
	private String funcFlag;
	
	@ResponseMsgField(nodeName="ArticleCount",getValMethod="getArticleCount")
	private int articleCount;

	public ArrayList<ArticleSubMsgResponse> getArticles() {
		return articles;
	}

	public void addArticle(ArticleSubMsgResponse article) {
		articles.add(article);
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	
	
}
