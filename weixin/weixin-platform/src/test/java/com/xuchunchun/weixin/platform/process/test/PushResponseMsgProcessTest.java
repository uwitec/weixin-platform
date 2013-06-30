package com.xuchunchun.weixin.platform.process.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.xuchunchun.weixin.platform.msg.rsp.ArticleMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.ArticleSubMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.MusicMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.MusicSubMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.TextMsgResponse;
import com.xuchunchun.weixin.platform.process.PushResponseMsgProcess;

public class PushResponseMsgProcessTest {

	@Test
	public void testParseObjToXmlWithText() {
		TextMsgResponse text = new TextMsgResponse();
		text.setContent("this is Content");
		text.setCreateTime(new Date(2013,5,6));
		text.setFromUserName("xuchunchun");
		text.setFuncFlag("0");
		text.setMsgType("text");
		text.setToUserName("xuwei");
		
		try {
			System.out.println(PushResponseMsgProcess.parseObjToXml(text));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseObjToXmlWithMusic() {
		MusicMsgResponse music = new MusicMsgResponse();
		MusicSubMsgResponse subMusic = new MusicSubMsgResponse();
		subMusic.setDescription("this is sub music desc");
		subMusic.setHqMusicUrl("http://hq.music.com");
		subMusic.setMusicUrl("http://music.com");
		subMusic.setTitle("this is title");
		music.setMusic(subMusic);
		music.setCreateTime(new Date(2013,5,6));
		music.setFromUserName("xuchunchun");
		music.setFuncFlag("0");
		music.setMsgType("music");
		music.setToUserName("xuwei");
		
		try {
			System.out.println(PushResponseMsgProcess.parseObjToXml(music));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseObjToXmlWithArticle() {
		ArticleMsgResponse article = new ArticleMsgResponse();
		ArticleSubMsgResponse subArticl = new ArticleSubMsgResponse();
		subArticl.setDescription("this is sub music desc");
		subArticl.setPicUrl("http://piture.com");
		subArticl.setUrl("http://url.com");
		subArticl.setTitle("this is title");
		ArticleSubMsgResponse subArticl1 = new ArticleSubMsgResponse();
		subArticl1.setDescription("this is sub music desc 1 ");
		subArticl1.setPicUrl("http://piture.com 1");
		subArticl1.setUrl("http://url.com 1");
		subArticl1.setTitle("this is title 1");
		article.addArticle(subArticl);
		article.addArticle(subArticl1);
		article.setCreateTime(new Date(2013,5,6));
		article.setFromUserName("xuchunchun");
		article.setFuncFlag("0");
		article.setMsgType("article");
		article.setToUserName("xuwei");
		
		try {
			System.out.println(PushResponseMsgProcess.parseObjToXml(article));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
