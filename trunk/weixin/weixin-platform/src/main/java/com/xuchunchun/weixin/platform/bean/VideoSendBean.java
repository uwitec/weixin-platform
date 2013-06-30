package com.xuchunchun.weixin.platform.bean;

public class VideoSendBean {
	private String touser;
	private String msgtype = "video";
	private VideoBean video;
	
	public void setMediaId(String media_id,String thumb_media_id){
		VideoBean videoBean = new VideoBean();
		videoBean.setMedia_id(media_id);
		videoBean.setThumb_media_id(thumb_media_id);
		this.setVideo(videoBean);
	}
	
	public class VideoBean{
		private String media_id;
		private String thumb_media_id;
		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		public String getThumb_media_id() {
			return thumb_media_id;
		}
		public void setThumb_media_id(String thumb_media_id) {
			this.thumb_media_id = thumb_media_id;
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
	public VideoBean getVideo() {
		return video;
	}
	public void setVideo(VideoBean video) {
		this.video = video;
	}
	
}
