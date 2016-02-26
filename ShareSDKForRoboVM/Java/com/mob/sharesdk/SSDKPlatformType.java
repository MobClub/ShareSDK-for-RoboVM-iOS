package com.mob.sharesdk;

public enum SSDKPlatformType {
	
	/* 分享平台类型枚举
	 * */
	
	Unknown			(0), 
	SinaWeibo		(1), 
	TencentWeibo	(2),
	DouBan			(5),
	QZone			(6),
	Renren			(7),
	Kaixin			(8),
	Facebook		(10),
	Twitter 		(11),
	YinXiang		(12),
	GooglePlus		(14),
	Instagram 		(15),
	LinkedIn		(16),
	Tumblr  		(17),
	Mail    		(18),
	SMS     		(19),
	Print   		(20),
	Copy    		(21),
	WechatSession	(22),
	WechatTimeline	(23),
	QQFriend    	(24),
	Instapaper		(25),
	Pocket  		(26),
	YouDaoNote		(27),
	Pinterest 		(30),
	Flickr  		(34),
	Dropbox 		(35),
	VKontakte 		(36),
	WechatFav       (37),
	YiXinSession 	(38),
	YiXinTimeline	(39),
	YiXinFav     	(40),
	MingDao 		(41),
	Line    		(42),
	WhatsApp		(43),
	KakaoTalk    	(44),
	KakaoStory   	(45),
	FacebookMessenger 	(46),
	AliPaySocial    	(50),
	YiXin   			(994),
	Kakao  				(995),
	Evernote			(996),
	Wechat  			(997),
	QQ      			(998),
	Any     			(999);


	private final int n;

	private SSDKPlatformType (int n) {
		this.n = n;
	}

	public int value () {
		return n;
	}
}
