package com.mob.sharesdk;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

@NativeClass
public class ShareSDKConnector extends NSObject{

	/**对于部分社交平台,需要依赖他们的平台SDK才能进行授权或分享**/
	/**因此需要绑定其主要类(包 com.mob.platforms就是对所需的平台的SDK主要类进行绑定)**/
	
	/* 链接微信
	 * */
	@Method (selector = "connectWeChat:")
	public static native void connnectWeChat(ObjCClass wxApiClass);
	
	/* 链接QQ
	 * */
	@Method (selector = "connectQQ:tencentOAuthClass:")
	public static native void connectQQ(ObjCClass qqApiInterfaceClass, ObjCClass tencentOAuthClass);
	
	/* 链接微博
	 * */
	@Method (selector = "connectWeibo:")
	public static native void connectWeibo(ObjCClass weiboSDKClass);
	
	/* 链接人人
	 * */
	@Method (selector = "connectRenren:")
	public static native void connectRenren(ObjCClass rennClientClass);
	
	/* 链接支付宝好友
	 * */
	@Method (selector = "connectAliPaySocial:")
	public static native void connectAlipaySocial(ObjCClass apOpenApiClass);
	
	/* 链接Kakao
	 * */
	@Method (selector = "connectKaKao:")
	public static native void connectKakao(ObjCClass koSessionClass);
	
	/* 链接易信
	 * */
	@Method (selector = "connectYiXin:")
	public static native void connectYiXin(ObjCClass yxApiClass);
	
	/* 链接FacebookMessenger
	 * */
	@Method (selector = "connectFacebookMessenger:")
	public static native void connectFacebookMessenger(ObjCClass fbmApiClass);
	
}
