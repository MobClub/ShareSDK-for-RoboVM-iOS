package com.mob.sharesdk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSMutableDictionary;
import org.robovm.apple.foundation.NSNumber;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIView;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCClassNotFoundException;
import org.robovm.objc.annotation.Block;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

import com.mob.platforms.APOpenAPI;
import com.mob.platforms.FBSDKMessengerSharer;
import com.mob.platforms.KOSession;
import com.mob.platforms.QQApiInterface;
import com.mob.platforms.RennClient;
import com.mob.platforms.TencentOAuth;
import com.mob.platforms.WXApi;
import com.mob.platforms.WeiboSDK;
import com.mob.platforms.YXApi;

@NativeClass
public class ShareSDK extends NSObject {

	
	/*********************************************************************************/
	/*以下为ShareSDK主要用法接口*/
	
	/**初始化ShareSDK**/
	/* 
	 * appKey           ShareSDK应用标识，可在http://mob.com中登录并创建App后获得
	 * configuration			平台配置信息
	 * */
	public static void registerApp(String appKey, HashMap<Number, HashMap<String, String>> configuration){
		initShareSDK(appKey, configuration);
	}
	
	/**授权**/
	/* type           		平台类型
	 * settings  	 		授权设置,目前只接受SSDKAuthSettingKeyScopes属性设置，
	 * 						如新浪微博关注官方微博：@{SSDKAuthSettingKeyScopes : @[@"follow_app_official_microblog"]}，
	 * 						类似“follow_app_official_microblog”这些字段是各个社交平台提供的。[一般情况下填写null即可]
	 * stateChangeHandler   授权状态变更回调处理
	 * */
	public static void authorize(SSDKPlatformType type,
			NSDictionary<?,?> settings,
			@Block SSDKAuthorizeStateChangedHandler stateChangeHandler) {
		authorize(type.value(), settings, stateChangeHandler);
	}
	
	/**获取用户信息**/
	/* type           		平台类型
	 * stateChangeHandler   状态变更回调处理
	 * */
	public static void getUserInfo(SSDKPlatformType type,
			@Block SSDKGetUserStateChangedHandler stateChangedHandler){
		getUserInfo(type.value(), stateChangedHandler);
	}
	
	/**简单分享内容**/
	/* platformType     平台类型
	 * parameters		分享参数
	 * onStateChange   	状态变更回调处理
	 * */
	public static void shareContent(SSDKPlatformType type, 
			NSMutableDictionary<?,?> shareParams,
			@Block SSDKShareOnStageChange onStateChange){
		shareContent(type.value(), shareParams, onStateChange);
	}
	
	/**显示编辑页面分享**/
	/* platformType     平台类型
	 * otherPlatforms	其他平台
	 * shareParams		分享参数
	 * onStateChange   	状态变更回调处理
	 * */
	public static void showShareView(SSDKPlatformType type, 
			NSArray<NSNumber> otherPlatforms,
			NSMutableDictionary<?,?> shareParams,
			@Block SSDKShareViewOnStageChange onStateChange){
		showShareView(type.value(), otherPlatforms, shareParams, onStateChange);
	}
	
	/**显示分享菜单分享**/
	/* view         	要显示菜单的视图, iPad版中此参数作为弹出菜单的参照视图(仅ipad下才需要,否则传入null)
	 * items            菜单项，通常传入null即可.传入null显示已集成的平台列表
	 * shareParams   	分享内容参数
	 * onStateChange   	状态变更回调处理
	 * */
	public static void  showShareMenu(UIView view, 
			NSArray<SSUIShareActionSheetItem> items,
			NSMutableDictionary<?,?> shareParams,
			@Block SSDKShareViewOnStageChange onStateChange){
		showShareActionSheet(view, items, shareParams, onStateChange);
	}
	
	/**判断分享平台是否授权**/
	/* 
     *	platformType	平台类型
	 *  return 			true表示已授权，false表示尚未授权
	 */
	public static boolean hasAuthorized(SSDKPlatformType type){
		return hasAuthorized(type.value());
	}
	
	/**取消授权**/
	/* 
     *	platformType	平台类型
	 *  return 			true表示已授权，false表示尚未授权
	 */
	public static void cancelAuthorize(SSDKPlatformType type){
		cancelAuthorize(type.value());
	}
	
	
	/***以下为对应以上几个接口的回调***/
	
	/*	授权方法回调
	 * 	state		授权状态
	 * 	user		用户
	 * 	error		错误
	 * */
	public interface SSDKAuthorizeStateChangedHandler{
		void invoke (SSDKResponseState state, 
				SSDKUser user, 
				NSError error);
	}
	
	/*	获取用户信息方法方法回调
	 * 	state		授权状态
	 * 	user		用户
	 * 	error		错误
	 * */
	public interface SSDKGetUserStateChangedHandler{
		void invoke (SSDKResponseState state, 
				SSDKUser user, 
				NSError error);
	}
	
	/*	简单分享方法回调
	 * 	state				状态
	 * 	userData			用户资料
	 * 	shareContent		分享内容
	 * 	error				错误
	 * */
	public interface SSDKShareOnStageChange {
        void invoke (SSDKResponseState state,
        		NSDictionary<?,?> userData,
        		SSDKContentEntity shareContent,
        		NSError error);
    }
	
	/*	带有视图分享方法回调（带有编辑界面或菜单）
	 * 	state				状态
	 * 	userData			用户资料
	 * 	shareContent		分享内容
	 * 	error				错误
	 * 	end					是否结束分享
	 * */
	public interface SSDKShareViewOnStageChange {
        void invoke (SSDKResponseState state,
        		int plat,
        		NSDictionary<?,?> userData,
        		SSDKContentEntity shareContent,
        		NSError error ,
        		boolean end);
    }
	
	/*********************************************************************************/
	
	
	/* 绑定初始化SDK方法 */
	@Method(selector = "registerApp:activePlatforms:onImport:onConfiguration:")
	private static native void registerShareSDK(NSString appKey, 
			NSArray<?> activePlatforms,
			@Block SSDKImportHandler importHandler,
			@Block SSDKConfigurationHandler configHandler);

	/* 绑定授权方法 */
	@Method(selector = "authorize:settings:onStateChanged:")
	private static native void authorize(int type,
			NSDictionary<?,?> settings,
			@Block SSDKAuthorizeStateChangedHandler stateChangeHandler);
	
	/* 绑定获取用户信息方法 */
	@Method(selector = "getUserInfo:onStateChanged:")
	private static native void getUserInfo(int type,
			@Block SSDKGetUserStateChangedHandler stateChangedHandler);
	
	/* 绑定简单分享方法 */
	@Method(selector = "share:parameters:onStateChanged:")
	private static native void shareContent(int type, 
			NSMutableDictionary<?,?> shareParams,
			@Block SSDKShareOnStageChange onStateChange);
		
	/* 绑定显示编辑框分享方法 */
	@Method(selector = "showShareEditor:otherPlatformTypes:shareParams:onShareStateChanged:")
	private static native void showShareView(int type, 
			NSArray<NSNumber> otherPlatforms,
			NSMutableDictionary<?,?> shareParams,
			@Block SSDKShareViewOnStageChange onStateChange);
	
	/* 绑定显示菜单分享方法 */
	@Method (selector = "showShareActionSheet:items:shareParams:onShareStateChanged:")
	private static native void showShareActionSheet(UIView view, 
			NSArray<SSUIShareActionSheetItem> items,
			NSMutableDictionary<?,?> shareParams,
			@Block SSDKShareViewOnStageChange onStateChange );
	
	/* 绑定检测是否已经授权方法 */
	@Method (selector = "hasAuthorized:")
	private static native boolean hasAuthorized(int type);
	
	/* 绑定取消授权方法 */
	@Method (selector = "cancelAuthorize:")
	private static native void cancelAuthorize(int type);
	
	private interface SSDKImportHandler {
        void invoke (int platform);
    }
	
	private interface SSDKConfigurationHandler {
        void invoke (int platform, NSMutableDictionary<?,?> appInfo);
    }

	private static HashMap<Number, HashMap<String, String>> myConfiguration;
		
	private static void initShareSDK(String appKey, HashMap<Number, HashMap<String, String>> configuration){
		
		myConfiguration = configuration;
		
		NSString SDKKey = new NSString(appKey);
		NSMutableArray<NSNumber> activePlats = new NSMutableArray<NSNumber> ();
		
		Collection<Number> keys = myConfiguration.keySet();
		for (Iterator<?> iterator = keys.iterator();iterator.hasNext();)
		{
			 
			Number key = (Number)iterator.next();
			NSNumber platform = NSNumber.valueOf(key);
			activePlats.add(platform);	
		}
	
		ShareSDK.registerShareSDK(SDKKey, activePlats, new ShareSDK.SSDKImportHandler() {
				
				@Override
				public void invoke(int platform) {

					switch(platform)
					{
			
					case 1: /*新浪*/
					{
						try{
							ObjCClass WeiboSDKClass = ObjCClass.getByName(WeiboSDK.class.getSimpleName());
							ShareSDKConnector.connectWeibo(WeiboSDKClass);
							
						}catch(ObjCClassNotFoundException exception){
							if(exception != null)
							{
								System.out.println("can't find Weibo SDK ,please check out robovm.xml if you need SinaWeibo SSO ,"
										+ "otherwise you can ignore it.");
							}
						}
						
						break;
					}
					
					case 7:/*人人网*/
					{
						
						try{
							ObjCClass RenrenSDKClass = ObjCClass.getByName(RennClient.class.getSimpleName());
							ShareSDKConnector.connectRenren(RenrenSDKClass);
							
						}catch(ObjCClassNotFoundException exception){
							if(exception != null)
							{
								System.out.println("can't find Weibo SDK ,please check out robovm.xml if you need SinaWeibo SSO ,"
										+ "otherwise you can ignore it.");
							}
						}
						
						break;
					}
					
					case 46:/*FacebookMessenger*/
					{
						try{
							ObjCClass FacebookMesgClass = ObjCClass.getByName(FBSDKMessengerSharer.class.getSimpleName());
							ShareSDKConnector.connectFacebookMessenger(FacebookMesgClass);
						}catch(ObjCClassNotFoundException exception){
							System.out.println("can't find Facebook SDK ,please check out robovm.xml");
						}
						
						break;
					}
					
					case 50:/*支付宝好友*/
					{
						try{
							
							ObjCClass APOpenApiClass = ObjCClass.getByName(APOpenAPI.class.getSimpleName());
							ShareSDKConnector.connectAlipaySocial(APOpenApiClass);
							
						}catch(ObjCClassNotFoundException exception){
			
							if(exception != null)
							{
								System.out.println("can't find Alipay Social SDK ,please check out robovm.xml");
							}
						}
						
						break;
					}
					case 994:/*YiXin*/
					{
						
						try{
							ObjCClass YixinApiClass = ObjCClass.getByName(YXApi.class.getSimpleName());
							ShareSDKConnector.connectYiXin(YixinApiClass);
						}catch(ObjCClassNotFoundException exception){
							if(exception != null)
							{
								System.out.println("can't find Yixin SDK ,please check out robovm.xml");
							}
						}
						
						break;
					}
					
					case 995:/*Kakao*/
					{
						
						try{
							
							ObjCClass KOSessionClass = ObjCClass.getByName(KOSession.class.getSimpleName());
							ShareSDKConnector.connectKakao(KOSessionClass);
							
						}catch(ObjCClassNotFoundException exception){
							if(exception != null)
							{
								System.out.println("can't find Kakao SDK, please check out robovm.xml");
							}
						}
						
						break;
					}
					
					case 998:/*QQ*/
					{
						try{
							
							ObjCClass QQApiClass = ObjCClass.getByName(QQApiInterface.class.getSimpleName());
							ObjCClass TencentOauthClass = ObjCClass.getByName(TencentOAuth.class.getSimpleName());
							
							ShareSDKConnector.connectQQ(QQApiClass, TencentOauthClass);
						
						}catch(ObjCClassNotFoundException exception){
							
							if(exception != null)
							{
								System.out.println("can't find QQ SDK, please check out robovm.xml");
							}
							
						}

						break;
					}
					
					case 997:/*微信*/	
					{	
						try{
					
							ObjCClass WeChatClass = ObjCClass.getByName(WXApi.class.getSimpleName());
							ShareSDKConnector.connnectWeChat(WeChatClass);
							
						}catch (ObjCClassNotFoundException exception){
							
							if(exception != null)
							{
								System.out.println("can't find Wechat SDK, please check out robovm.xml");
							}
						}
						break;
					}
					
					
					default:
						break;	

					}
				}
			}, new ShareSDK.SSDKConfigurationHandler() {
				
				@Override
				public void invoke(int platform,
						NSMutableDictionary<?, ?> appInfo) {
	
					if(myConfiguration.containsKey(platform))
					{
										
						HashMap<String, String> platformMap = myConfiguration.get(platform);
						
						 for(Iterator<?> iter = platformMap.entrySet().iterator();iter.hasNext();)
						 { 
					            Map.Entry<?,?> element = (Map.Entry<?,?>)iter.next(); 
					            
					            String strKey = (String)element.getKey();
					            String strValue = (String)element.getValue();
					            
					            appInfo.put(strKey, strValue);
					            

						 }
		
					}
										
				}
			});
	
	}

}
