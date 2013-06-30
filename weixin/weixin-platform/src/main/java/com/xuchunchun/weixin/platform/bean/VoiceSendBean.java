package com.xuchunchun.weixin.platform.bean;

public class VoiceSendBean {
	private String touser;
	private String msgtype = "voice";
	private VoiceBean voice;
	
	public void setMediaId(String media_id){
		VoiceBean voiceBean = new VoiceBean();
		voiceBean.setMedia_id(media_id);
		this.setVoice(voiceBean);
	}
	
	public class VoiceBean{
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
	public VoiceBean getVoice() {
		return voice;
	}
	public void setVoice(VoiceBean voice) {
		this.voice = voice;
	}
	
}
