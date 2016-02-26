package com.mob.demo;

import java.util.HashMap;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;
import com.mob.sharesdk.SSDKPlatformType;
import com.mob.sharesdk.ShareSDK;


public class ShareSDKDemo extends UIApplicationDelegateAdapter {
    private UIWindow window;
    private MyViewController rootViewController;
 
    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        // Set up the view controller.
        rootViewController = new MyViewController();

        // Create a new window at screen size.
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        // Set the view controller as the root controller for the window.
        window.setRootViewController(rootViewController);
        // Make the window visible.
        window.makeKeyAndVisible();

        this.initShareSDK();
        
        //ShareSDKInitConfiguration.ShareSDKInit();
        
        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, ShareSDKDemo.class);
        }
    }
    
    
    private void initShareSDK()
    {
        //总HashMap 
        HashMap<Number, HashMap<String, String>> totalMap = new HashMap<Number, HashMap<String, String>> ();
        
        //新浪HashMap
        HashMap<String, String> sinaHashMap = new HashMap<String, String>();
        sinaHashMap.put("app_key", "568898243");
        sinaHashMap.put("app_secret", "38a4f8204cc784f81f9f0daaf31e02e3");
        sinaHashMap.put("redirect_uri", "http://www.sharesdk.cn");
        sinaHashMap.put("auth_type", "both");
        totalMap.put(SSDKPlatformType.SinaWeibo.value(), sinaHashMap);
        
        //腾讯微博HashMap
        HashMap<String, String> tencentHashMap = new HashMap<String, String>();
        tencentHashMap.put("app_key", "801307650");
        tencentHashMap.put("app_secret", "ae36f4ee3946e1cbb98d6965b0b2ff5c");
        tencentHashMap.put("redirect_uri", "http://www.sharesdk.cn");
        totalMap.put(SSDKPlatformType.TencentWeibo.value(), sinaHashMap);
        
        //微信HashMap
        HashMap<String, String> wechatHashMap = new HashMap<String, String>();
        wechatHashMap.put("app_id", "wx4868b35061f87885");
        wechatHashMap.put("app_secret", "64020361b8ec4c99936c0e3999a9f249");
        totalMap.put(SSDKPlatformType.Wechat.value(), wechatHashMap);
        
        //QQHashMap
        HashMap<String, String> QQHashMap = new HashMap<String, String>();
        QQHashMap.put("app_id", "100371282");
        QQHashMap.put("app_key", "aed9b0303e3ed1e27bae87c33761161d");
        QQHashMap.put("auth_type", "both");
        totalMap.put(SSDKPlatformType.QQ.value(), QQHashMap);
        
        //AliPayHashMap
        HashMap<String, String> AliPaySocialHashMap = new HashMap<String, String>();
        AliPaySocialHashMap.put("app_id", "2015072400185895");
        totalMap.put(SSDKPlatformType.AliPaySocial.value(), AliPaySocialHashMap);
        
        ShareSDK.registerApp("iosv1101", totalMap);
            
    }
    
}
