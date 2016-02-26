package com.mob.sharesdk;

public enum SSDKContentType {
	
	/* 分享类型枚举
	 * */
	
	Auto 	(0), 	/*自动*/
	Text	(1), 	/*文字*/
	Image	(2),	/*图片*/
	WebPage (3),	/*网页*/
	App		(4),	/*应用*/
	Audio 	(5),	/*音频*/
	Video 	(6);	/*视频*/

	private final int n;

	private SSDKContentType (int n) {
	   this.n = n;
	}

	public int value () {
	   return n;
	}
	
}