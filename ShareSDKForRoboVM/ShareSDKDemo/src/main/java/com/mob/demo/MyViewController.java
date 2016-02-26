package com.mob.demo;

import java.io.File;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSBundle;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSMutableDictionary;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.UIAlertView;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControl.OnTouchUpInsideListener;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIViewController;

import com.mob.sharesdk.SSDKContentEntity;
import com.mob.sharesdk.SSDKContentType;
import com.mob.sharesdk.SSDKImage;
import com.mob.sharesdk.SSDKPlatformType;
import com.mob.sharesdk.SSDKResponseState;
import com.mob.sharesdk.SSDKUser;
import com.mob.sharesdk.ShareParamsManager;
import com.mob.sharesdk.ShareSDK;

@SuppressWarnings("deprecation")
public class MyViewController extends UIViewController {
	
	private UIButton authBtn, getUserInfoBtn,checkAuthorizedBtn,cancelAuthorizeBtn, 
	shareContentBtn, showShareViewBtn, showShareMenuBtn;
	
	private OnTouchUpInsideListener authBtnClick, getUserInfoBtnClick, checkAuthorizedBtnClick, 
	cancelAuthorizeBtnClick, shareContentBtnClick, showShareViewBtnClick, showShareMenuBtnClick;
	
    public MyViewController() {
    	
    	this.initUI();
    	this.excute();

    }
       
    private void excute()
    {
    	//授权
    	authBtnClick = new OnTouchUpInsideListener() {
             @Override
             public void onTouchUpInside (UIControl control, UIEvent event) {
  
            	 ShareSDK.authorize(SSDKPlatformType.SinaWeibo, null, new ShareSDK.SSDKAuthorizeStateChangedHandler() {
					
					@Override
					public void invoke(SSDKResponseState state, SSDKUser user, NSError error) {
	
						switch (state)
						{
						case Begin:
							break;
						case Success:
						{
							UIAlertView alert = new UIAlertView("授权成功","",null,"确定");
							alert.show();
							break;
						}
						case Fail:
						{
							UIAlertView alert = new UIAlertView("授权失败","",null,"确定");
							alert.show();
							break;
						}
						case Cancel:
						{
							UIAlertView alert = new UIAlertView("授权取消","",null,"确定");
							alert.show();
							break;
						}
						}
					}
				});
             }
         };
        authBtn.addOnTouchUpInsideListener(authBtnClick);
    	
        //获取用户信息
        getUserInfoBtnClick = new OnTouchUpInsideListener() {
             @Override
             public void onTouchUpInside (UIControl control, UIEvent event) {
         
                ShareSDK.getUserInfo(SSDKPlatformType.QQ, new ShareSDK.SSDKGetUserStateChangedHandler() {
					
					@Override
					public void invoke(SSDKResponseState state, SSDKUser user, NSError error) {
						
						switch (state)
						{
						case Begin:
							break;
						case Success:
						{
							UIAlertView alert = new UIAlertView("授权成功","",null,"确定");
							alert.show();
							System.out.println("用户uid:" + user.getUid());
							System.out.println("用户昵称:" + user.getNickname());
							break;
						}
						case Fail:
						{
							UIAlertView alert = new UIAlertView("授权失败！","",null,"确定");
							alert.show();
							String errorInfo =  error.getUserInfo().toString();
							System.out.println("errorInfo:" + errorInfo);
							break;
						}
						case Cancel:
						{
							UIAlertView alert = new UIAlertView("授权取消！","",null,"确定");
							alert.show();
							break;
						}
						}
						
						
					}
				});
            	 
             }
         };
        getUserInfoBtn.addOnTouchUpInsideListener(getUserInfoBtnClick);
         
        //检测是否已经授权
        checkAuthorizedBtnClick =  new OnTouchUpInsideListener(){
        	 @Override
             public void onTouchUpInside (UIControl control, UIEvent event) {
        		 
        		 boolean hasAuthorized = ShareSDK.hasAuthorized(SSDKPlatformType.SinaWeibo);
        		 
        		 UIAlertView alert = new UIAlertView("是否已授权 : " + hasAuthorized,"",null,"确定");
				 alert.show();
        	 }	
        };
        checkAuthorizedBtn.addOnTouchUpInsideListener(checkAuthorizedBtnClick);
        
        //取消授权
        cancelAuthorizeBtnClick = new OnTouchUpInsideListener(){
        	@Override
        	public void onTouchUpInside (UIControl control, UIEvent event) {
    		 
    		ShareSDK.cancelAuthorize(SSDKPlatformType.SinaWeibo);
    		 
       	 	}	
        };
        cancelAuthorizeBtn.addOnTouchUpInsideListener(cancelAuthorizeBtnClick);
        
        //简单分享
        shareContentBtnClick = new OnTouchUpInsideListener() {
             @Override
             public void onTouchUpInside (UIControl control, UIEvent event) {
             
            	
            	NSMutableDictionary<NSString, NSObject> shareParams = new NSMutableDictionary<> ();
            	
            	NSString text = new NSString("Simple Share Content");
            	NSString title = new NSString("Simple Share Title");
            	NSURL url = new NSURL("http://mob.com");
            	
            	
            	//网络图片
             	NSURL imageUrl = new NSURL("http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg");
             	SSDKImage urlImage = new SSDKImage(imageUrl);
             	NSArray<SSDKImage> imagesArray = new NSArray<SSDKImage>(urlImage);
             	
             
             	ShareParamsManager.setBasicShareParams(shareParams, text, imagesArray, url, title, SSDKContentType.Image);
                
                
                ShareSDK.shareContent(SSDKPlatformType.SinaWeibo, shareParams, new ShareSDK.SSDKShareOnStageChange() {
					
					@Override
					public void invoke(SSDKResponseState state, NSDictionary<?, ?> userData,
							SSDKContentEntity shareContent, NSError error) {
						
						switch (state)
						{
						case Begin:
							break;
						case Success:
						{
							UIAlertView alert = new UIAlertView("分享成功","",null,"确定");
							alert.show();
							break;
						}
						case Fail:
						{
							UIAlertView alert = new UIAlertView("分享失败","",null,"确定");
							alert.show();
							System.out.println("失败！");
							String errorInfo =  error.getUserInfo().toString();
							System.out.println("errorInfo:" + errorInfo);
							break;
						}
						case Cancel:
						{
							UIAlertView alert = new UIAlertView("取消分享","",null,"确定");
							alert.show();
							break;
						}
						}
						
					}
				});
                  
             }
         };
        shareContentBtn.addOnTouchUpInsideListener(shareContentBtnClick);
         
         //编辑页面分享
         showShareViewBtnClick = new OnTouchUpInsideListener() {
             @Override
             public void onTouchUpInside (UIControl control, UIEvent event) {
            	       	
                NSString text = new NSString("This is Share Text Content With Share View");
                NSURL url = new NSURL("http://mob.com");
                NSString title = new NSString("This is Title");
                
            	//本地图片
            	String localImgPath = NSBundle.getMainBundle().findResourcePath("D09", "jpg");
            	NSString JPEG = new NSString("JPEG");
            	File imgFile = new File(localImgPath);
            	UIImage localImg = new UIImage(imgFile);
            	SSDKImage localSDKImg = new SSDKImage (localImg,JPEG,null);
            	NSArray<SSDKImage> localImgArr = new NSArray<SSDKImage>(localSDKImg); 
       
          
            	//网络图片
//              	NSURL imageUrl = new NSURL("http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg");
//              	SSDKImage urlImage = new SSDKImage(imageUrl);
//              	NSArray<SSDKImage> urlImagesArray = new NSArray<SSDKImage>(urlImage);
                
              	//建议建立一个分享参数
                NSMutableDictionary<NSString,NSObject> shareParams = new NSMutableDictionary<NSString,NSObject>();
                
                //定制基本分享内容
                ShareParamsManager.setBasicShareParams(shareParams, text, localImgArr, url, title, SSDKContentType.Image);
                  
                
               ShareSDK.showShareView(SSDKPlatformType.SinaWeibo, null, shareParams, new ShareSDK.SSDKShareViewOnStageChange() {
     			
     			@Override
     			public void invoke(SSDKResponseState state, int plat,
     					NSDictionary<?, ?> userData, SSDKContentEntity shareContent,
     					NSError error, boolean end) {
     				
     				switch (state)
					{
					case Begin:
						break;
					case Success:
					{
						UIAlertView alert = new UIAlertView("分享成功","",null,"确定");
						alert.show();
						break;
					}
					case Fail:
					{
						UIAlertView alert = new UIAlertView("分享失败","",null,"确定");
						alert.show();
						String errorInfo =  error.getUserInfo().toString();
						System.out.println("errorInfo:" + errorInfo);
						break;
					}
					case Cancel:
					{
						UIAlertView alert = new UIAlertView("取消分享","",null,"确定");
						alert.show();
						break;
					}
					}
     				
     			}
     		});
                 
             }
         };
         showShareViewBtn.addOnTouchUpInsideListener(showShareViewBtnClick);
         
         //菜单分享
         showShareMenuBtnClick = new OnTouchUpInsideListener() {
             @Override
             public void onTouchUpInside (UIControl control, UIEvent event) {
               
            	NSString text = new NSString("This is Share Text Content With Share Menu");
                NSURL url = new NSURL("http://mob.com");
                NSString title = new NSString("This is Title");
            	 
            	//本地图片
             	String localImgPath = NSBundle.getMainBundle().findResourcePath("D09", "jpg");
             	NSString JPEG = new NSString("JPEG");
             	File imgFile = new File(localImgPath);
             	UIImage localImg = new UIImage(imgFile);
             	SSDKImage localSDKImg = new SSDKImage (localImg,JPEG,null);
             	NSArray<SSDKImage> localImgArr = new NSArray<SSDKImage>(localSDKImg);
            	 
            	//网络图片
//            	NSURL imageUrl = new NSURL("http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg");
//            	SSDKImage urlImage = new SSDKImage(imageUrl);
//            	NSArray<SSDKImage> urlImagesArray = new NSArray<SSDKImage>(urlImage);
               	
            	 
            	NSMutableDictionary<NSString,NSObject> shareParams = new NSMutableDictionary<NSString,NSObject>();
                  
                ShareParamsManager.setBasicShareParams(shareParams, text, localImgArr, url, title, SSDKContentType.Image);

                 
                //指定定制新浪微博分享内容
                NSString sinaText = new NSString("This is Sina Text");
                NSString sinaTitle = new NSString("This is Sina Title"); 
                String sinaImgPath = NSBundle.getMainBundle().findResourcePath("DAR2", "jpg");
              	File sinaImgFile = new File(sinaImgPath);
              	UIImage sinaImg = new UIImage(sinaImgFile);
              	SSDKImage sinaSDKImg = new SSDKImage (sinaImg,JPEG,null);

                ShareParamsManager.setSinaWeiboShareParams(sinaText, sinaTitle, sinaSDKImg,
                		url, 44.30, 33.46, null, SSDKContentType.Image);
                 
                
                
            	 ShareSDK.showShareMenu(null, null, shareParams, new ShareSDK.SSDKShareViewOnStageChange() {
					
					@Override
					public void invoke(SSDKResponseState state, int plat,
							NSDictionary<?, ?> userData, SSDKContentEntity shareContent,
							NSError error, boolean end) {
			
						switch (state)
						{
						case Begin:
							break;
						case Success:
						{
							UIAlertView alert = new UIAlertView("分享成功","",null,"确定");
							alert.show();
							break;
						}
						case Fail:
						{
							UIAlertView alert = new UIAlertView("分享失败","",null,"确定");
							alert.show();
							String errorInfo =  error.getUserInfo().toString();
							System.out.println("errorInfo:" + errorInfo);
							break;
						}
						case Cancel:
						{
							UIAlertView alert = new UIAlertView("取消分享","",null,"确定");
							alert.show();
							break;
						}
						}
					}
				});
                
             }
         };
         showShareMenuBtn.addOnTouchUpInsideListener(showShareMenuBtnClick);
    	
    }
     
    private void initUI()
    {
    	double midX = UIScreen.getMainScreen().getBounds().getWidth()/2 - 75;
    	
    	this.getView().setBackgroundColor(UIColor.white());
    	
    	authBtn = new UIButton(UIButtonType.RoundedRect);
    	authBtn.setFrame(new CGRect(midX, 100, 150, 40));
    	authBtn.setTitle("授权", UIControlState.Normal);
    	this.getView().addSubview(authBtn);
    	
    	getUserInfoBtn = new UIButton(UIButtonType.RoundedRect);
    	getUserInfoBtn.setFrame(new CGRect(midX, 150, 150, 40));
    	getUserInfoBtn.setTitle("获取用户信息", UIControlState.Normal);
    	this.getView().addSubview(getUserInfoBtn);
    	
    	checkAuthorizedBtn = new UIButton(UIButtonType.RoundedRect);
    	checkAuthorizedBtn.setFrame(new CGRect(midX, 200, 150, 40));
    	checkAuthorizedBtn.setTitle("检测授权", UIControlState.Normal);
    	this.getView().addSubview(checkAuthorizedBtn);
    	
    	cancelAuthorizeBtn = new UIButton(UIButtonType.RoundedRect);
    	cancelAuthorizeBtn.setFrame(new CGRect(midX, 250, 150, 40));
    	cancelAuthorizeBtn.setTitle("取消授权", UIControlState.Normal);
    	this.getView().addSubview(cancelAuthorizeBtn);
    	
    	shareContentBtn = new UIButton(UIButtonType.RoundedRect);
    	shareContentBtn.setFrame(new CGRect(midX, 300, 150, 40));
    	shareContentBtn.setTitle("简单分享", UIControlState.Normal);
    	this.getView().addSubview(shareContentBtn);
    	
    	showShareViewBtn = new UIButton(UIButtonType.RoundedRect);
    	showShareViewBtn.setFrame(new CGRect(midX, 350, 150, 40));
    	showShareViewBtn.setTitle("显示编辑界面分享", UIControlState.Normal);
    	this.getView().addSubview(showShareViewBtn); 
    	
    	showShareMenuBtn = new UIButton(UIButtonType.RoundedRect);
    	showShareMenuBtn.setFrame(new CGRect(midX, 400, 150, 40));
    	showShareMenuBtn.setTitle("显示菜单分享", UIControlState.Normal);
    	this.getView().addSubview(showShareMenuBtn);
    	
    }
}
