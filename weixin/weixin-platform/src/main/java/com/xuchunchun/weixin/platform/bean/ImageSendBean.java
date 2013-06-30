package com.xuchunchun.weixin.platform.bean;


public class ImageSendBean {
	private String touser;
	private String msgtype = "image";
	private ImageBean image;
	
	public void setMediaId(String media_id){
		ImageBean imageBean = new ImageBean();
		imageBean.setMedia_id(media_id);
		this.setImage(imageBean);
	}
	
	public class ImageBean{
		private String media_id;
		
		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
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
	public ImageBean getImage() {
		return image;
	}
	public void setImage(ImageBean image) {
		this.image = image;
	}
	
}
