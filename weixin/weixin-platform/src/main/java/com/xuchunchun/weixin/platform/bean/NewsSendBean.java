package com.xuchunchun.weixin.platform.bean;

import java.util.List;

public class NewsSendBean {
	private String touser;
	private String msgtype = "news";
	private NewsBean news;
	
	public class NewsBean{
		private List<ArticlesBean> articles = null;
		
		public List<ArticlesBean> getArticles() {
			return articles;
		}
		public void setArticles(List<ArticlesBean> articles) {
			this.articles = articles;
		}
		
		public class ArticlesBean{
			private String title;
			private String description;
			private String url;
			private String picurl;
			
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}
			public String getPicurl() {
				return picurl;
			}
			public void setPicurl(String picurl) {
				this.picurl = picurl;
			}
		}
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public NewsBean getNews() {
		return news;
	}
	public void setNews(NewsBean news) {
		this.news = news;
	}
	
	

}
