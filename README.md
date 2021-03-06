# ShareSDK-for-RoboVM-iOS

###### 现在ShareSDK也支持基于 RoboVM 的 iOS 应用了。本项目将ShareSDK的多种功能和类已经绑定完毕，并且简化部分绑定的原生接口。详细使用方法请参考如下文档。(本项目是在Eclipse中编写,项目使用的ShareSDK为v3.2.0)
###### Please View The Document In English At The Bottom
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

##RoboVM项目快速集成[ShareSDK](http://mob.com)步骤##

### 一、引入文件并添加ShareSDK库

##### 1.首先将两个包文件夹添加到自身的项目中

将项目中”Java”文件夹下的“com”文件夹复制到自身项目src/main/java 目录下,项目中刷新一下就见到添加了两个包(分别是com.mob.sharesdk和com.mob.platforms)

包内容说明：

-com.mob.sharesdk - ShareSDK 主要的功能接口的绑定

-com.mob.platforms - 对使用到的第三方社交平台的SDK绑定

##### 2.项目添加ShareSDK库文件

i. 到http://mob.com ,下载iOS ShareSDK  3.X 版本（本项目完成时为3.2.0)，解压文件后得到名为ShareSDK的文件夹，将该文件夹复制到Eclipse项目根目录下。

ii. 将ShareSDK/Support/Required 中的ShareSDK.bundle 和 ShareSDK/Support/Optional 中的ShareSDKUI.bundle 移动至 robovm 项目的 resources 文件夹下.

iii.配置robovm.xml 中引用各种系统库和第三方库的路径

**.a文件类:**

        <libs>
            <lib>z</lib>       	 <!--(系统库,必须)-->
            <lib>sqlite3</lib> 	 <!--(系统库,必须)-->
            <lib>stdc++</lib>	 <!--(系统库,必须)-->
            <lib>icucore</lib>	 <!--(系统库,必须)-->
            <lib>ShareSDK/Support/PlatformSDK/WeChatSDK/libWeChatSDK.a</lib>   <!--(微信)-->
            <lib>ShareSDK/Support/PlatformSDK/APSocialSDK/libAPOpenSdk.a</lib> <!--(支付宝好友)-->
            <lib>ShareSDK/Support/PlatformSDK/YiXinSDK/libYixinSDK.a</lib>     <!--(易信)-->
        </libs>

**.framework类:**

*路径:*

        <frameworkPaths>
            <path>ShareSDK</path>
            <path>ShareSDK/Support/Required</path>
            <path>ShareSDK/Support/Optional</path>
            <path>ShareSDK/Support/PlatformSDK/QQSDK</path>
            <path>ShareSDK/Support/PlatformSDK/RenRenSDK</path>
            <path>ShareSDK/Support/PlatformSDK/FacebookMessengerSDK</path>
            <path>ShareSDK/Support/PlatformSDK/KaKaoSDK</path>
            <path>ShareSDK/Support/PlatformSDK/RennSDK</path>
        </frameworkPaths>

*名称:*

        <frameworks>
            <framework>JavaScriptCore</framework>	 <!--(系统库,必须)-->
            <framework>ShareSDK</framework>          <!--(ShareSDK基本库,必须)-->
            <framework>ShareSDKUI</framework>		 <!--(ShareSDK基本库,必须)-->
            <framework>ShareSDKExtension</framework> <!--(ShareSDK基本库,必须)-->
            <framework>MOBFoundation</framework>	 <!--(ShareSDK基本库,必须)-->
            <framework>ShareSDKConnector</framework> <!--(ShareSDK基本库,必须)-->
            <framework>ImageIO</framework>           <!--(如需新浪SDK，则需要)-->
            <framework>TencentOpenAPI</framework>    <!--(QQSDK)-->
            <framework>RennSDK</framework>           <!--(人人网SDK)-->
            <framework>FBSDKMessengerShareKit</framework>  <!--(FBSDKMessenger SDK)-->
            <framework>KakaoOpenSDK</framework>            <!--(Kakao SDK)-->
        </frameworks>

### 二、添加ShareSDK的代码

##### 1.初始化ShareSDK

在RoboVM 项目的主类(mainclass)文件中会有重写的**didFinishLaunching**方法,这个一般就是iOS应用启动时首先运行的方法。
在此方法中添加如下代码进行ShareSDK的初始化。

        //总HashMap 
        HashMap<Number, HashMap<String, String>> totalMap = new HashMap<Number, HashMap<String, String>> ();

        //新浪
        HashMap<String, String> sinaHashMap = new HashMap<String, String>();
        sinaHashMap.put("app_key", "568898243");
        sinaHashMap.put("app_secret", "38a4f8204cc784f81f9f0daaf31e02e3");
        sinaHashMap.put("redirect_uri", "http://www.sharesdk.cn");
        sinaHashMap.put("auth_type", "both");
        totalMap.put(SSDKPlatformType.SinaWeibo.value(), sinaHashMap);

        //腾讯微博
        HashMap<String, String> tencentHashMap = new HashMap<String, String>();
        tencentHashMap.put("app_key", "801307650");
        tencentHashMap.put("app_secret", "ae36f4ee3946e1cbb98d6965b0b2ff5c");
        tencentHashMap.put("redirect_uri", "http://www.sharesdk.cn");
        totalMap.put(SSDKPlatformType.TencentWeibo.value(), tencentHashMap);

        //其他平台...

        //初始化ShareSDK(其中第一个参数在[ShareSDK官网](http://mob.com)申请获得)
        ShareSDK.registerApp("iosv1101", totalMap);

##### 2.授权方法示范:

        //新浪微博用户授权
        ShareSDK.authorize(SSDKPlatformType.SinaWeibo, null, new ShareSDK.SSDKAuthorizeStateChangedHandler() {

            @Override
            public void invoke(SSDKResponseState state, SSDKUser user, NSError error) {

                switch (state)
                {
                    case Begin:
                    break;
                    case Success:
                    {
                    UIAlertView alert = new UIAlertView("Success!","",null,"OK");
                    alert.show();
                    break;
                    }
                    case Fail:
                    {
                    UIAlertView alert = new UIAlertView("Authorize failed!","",null,"OK");
                    alert.show();
                    break;
                    }
                    case Cancel:
                    {
                    UIAlertView alert = new UIAlertView("Cancel!","",null,"OK");
                    alert.show();
                    break;
                    }
                }
            }
        });

##### 3.获取用户信息方法示范:

        //获取QQ用户信息
        ShareSDK.getUserInfo(SSDKPlatformType.QQ, new ShareSDK.SSDKGetUserStateChangedHandler() {

            @Override
            public void invoke(SSDKResponseState state, SSDKUser user, NSError error) {

                switch (state)
                {
                    case Begin:
                    break;
                    case Success:
                    {
                    UIAlertView alert = new UIAlertView("Success!","",null,"OK");

                    System.out.println("用户uid:" + user.getUid());
                    System.out.println("用户昵称:" + user.getNickname());
                    alert.show();
                    break;
                    }
                    case Fail:
                    {
                    UIAlertView alert = new UIAlertView("Authorize failed!","",null,"OK");
                    alert.show();

                    String errorInfo =  error.getUserInfo().toString();

                    System.out.println("errorInfo:" + errorInfo);
                    break;
                    }
                    case Cancel:
                    {
                    UIAlertView alert = new UIAlertView("Cancel!","",null,"OK");
                    alert.show();
                    break;
                    }
                }
            }
        });

##### 4. 分享内容方法示范:

        //分享到新浪微博
        //构建一个分享内容参数NSMutableDictionary
        NSMutableDictionary<NSString, NSObject> shareParams = new NSMutableDictionary<> ();

        //定制分享内容字段
        NSString text = new NSString("Simple Share Content");
        NSString title = new NSString("Simple Share Title");
        NSURL url = new NSURL("http://mob.com");

        //定制分享内容只图片(此处为网络图片)
        NSURL imageUrl = new NSURL("http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg");
        SSDKImage urlImage = new SSDKImage(imageUrl);
        NSArray<SSDKImage> imagesArray = new NSArray<SSDKImage>(urlImage);

        //设定分享内容
        ShareParamsManager.setBasicShareParams(shareParams, text, imagesArray, url, title, SSDKContentType.Image);

        ShareSDK.shareContent(SSDKPlatformType.SinaWeibo, shareParams, new ShareSDK.SSDKShareOnStageChange() {

            @Override
            public void invoke(SSDKResponseState state, NSDictionary<?, ?> userData, SSDKContentEntity shareContent, NSError error) {

                switch (state)
                {
                    case Begin:
                    break;
                    case Success:
                    {
                    UIAlertView alert = new UIAlertView("Success!","",null,"OK");
                    alert.show();
                    break;
                    }
                    case Fail:
                    {
                    UIAlertView alert = new UIAlertView("Failed!","",null,"OK");
                    alert.show();
                    NSErrorUserInfo userInfo = error.getUserInfo();
                    System.out.println("errorInfo:" + userInfo.getDictionary());
                    break;
                    }
                    case Cancel:
                    {
                    UIAlertView alert = new UIAlertView("Cancel!","",null,"OK");
                    alert.show();
                    break;
                    }
                }

            }
        });

### 三、关于项目的Info.plist.xml配置

##### 1.URL Sheme的配置:

部分需要跳转到社交平台进行授权或分享,例如微信,QQ等，需要配置URL Scheme才能成功返回自身的应用,在项目的Info.plist.xml文件中配置，示例如下:

        <key>CFBundleURLTypes</key>
            <array>
                <dict>
                    <key>CFBundleURLSchemes</key>
                        <array>				
                            <string>QQ05FB8B52</string>
                            <string>tencent100371282</string>
                            <string>wx4868b35061f87885</string>
                        </array>
                </dict>
            </array>


##### 2.如果是在iOS 9.0 以上的设备运行的话,还需要对需要跳转的平台配置白名单，如:

        <key>LSApplicationQueriesSchemes</key>
            <array>
                <string>wechat</string>
                <string>weixin</string>
            </array>

##### 3.如果是在iOS 9.0 以上的设备运行的话,因为iOS9.0以上是默认只允许https的网络请求,但有相当一部分的社交平台的网站不支持https，所以进行对NSAppTransportSecurity的设置，例如:

直接允许项目的所有http请求:

        <key>NSAppTransportSecurity</key>
        <dict>
            <key>NSAllowsArbitraryLoads</key>
            <true />
        </dict>


或者仅允许指定的域名能够进行http请求:

        <key>NSAppTransportSecurity</key>
        <dict>
            <key>NSExceptionDomains</key>
            <dict>
                <key>qq.com</key>
                <dict>
                    <key>NSIncludesSubdomains</key>
                    <true/>
                    <key>NSExceptionRequiresForwardSecrecy</key>
                    <false/>
                    <key>NSExceptionAllowsInsecureHTTPLoads</key>
                    <true/>
                </dict>
            </dict>
        </dict>

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
如需要技术支持,请联系QQ:46131514

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# ShareSDK-for-RoboVM-iOS (Document In English)

##### [ShareSDK](https://github.com/MobClub/ShareSDK3.x-for-iOS) now is supported to iOS project base on RoboVM.Most basic classes and methods have been bound and simplified.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

##How To Integrate ShareSDK To Your RoboVM iOS Project 

### Step.1 - Import ShareSDK

##### 1.add the packages to your RoboVM project

Copy the folder ’com‘ to path src/main/java of your project.It includes to package,com.mob.sharesdk and com.mob.platforms.

- com.mob.sharesdk : the main function bindings of ShareSDK

- com.mob.platforms : the bindings of some necessary third part SDKs(such as WeChat SDK).

##### 2.add the ShareSDK lib/framework to your RoboVM project

i.Download [ShareSDK For iOS](https://github.com/MobClub/ShareSDK3.x-for-iOS).Unzip it and you will get a folder ShareSDK.Copy the folder to the root directory of your Eclipse RoboVM project.

ii.Move ShareSDK.bundle(in ShareSDK/Support/Required) and ShareSDKUI.bundle(in ShareSDK/Support/Optional) to the resources folder of you project.

iii.set the right path for the libs and framework in robovm.xml,like this:

**.a:**

        <libs>
            <lib>z</lib>       	 <!--(basic lib,necessary)-->
            <lib>sqlite3</lib> 	 <!--(basic lib,necessary)-->
            <lib>stdc++</lib>	 <!--(basic lib,necessary)-->
            <lib>icucore</lib>	 <!--(basic lib,necessary)-->
            <lib>ShareSDK/Support/PlatformSDK/WeChatSDK/libWeChatSDK.a</lib>   <!--(wechat)-->
            <lib>ShareSDK/Support/PlatformSDK/APSocialSDK/libAPOpenSdk.a</lib> <!--(alipaySocial)-->
            <lib>ShareSDK/Support/PlatformSDK/YiXinSDK/libYixinSDK.a</lib>     <!--(yixin)-->
        </libs>

**.framework:**

*path:*

        <frameworkPaths>
            <path>ShareSDK</path>
            <path>ShareSDK/Support/Required</path>
            <path>ShareSDK/Support/Optional</path>
            <path>ShareSDK/Support/PlatformSDK/QQSDK</path>
            <path>ShareSDK/Support/PlatformSDK/RenRenSDK</path>
            <path>ShareSDK/Support/PlatformSDK/FacebookMessengerSDK</path>
            <path>ShareSDK/Support/PlatformSDK/KaKaoSDK</path>
            <path>ShareSDK/Support/PlatformSDK/RennSDK</path>
        </frameworkPaths>
*name:*

        <frameworks>
            <framework>JavaScriptCore</framework>	 <!--(basic framework,necessary)-->
            <framework>ShareSDK</framework>          <!--(ShareSDK framework,necessary)-->
            <framework>ShareSDKUI</framework>		 <!--(ShareSDK framework,necessary)-->
            <framework>ShareSDKExtension</framework> <!--(ShareSDK framework,necessary)-->
            <framework>MOBFoundation</framework>	 <!--(ShareSDK framework,necessary)-->
            <framework>ShareSDKConnector</framework> <!--(ShareSDK framework,necessary)-->
            <framework>ImageIO</framework>           <!--(Sina SDK need)-->
            <framework>TencentOpenAPI</framework>    <!--(QQSDK)-->
            <framework>RennSDK</framework>           <!--(Renren SDK)-->
            <framework>FBSDKMessengerShareKit</framework>  <!--(FBSDKMessenger SDK)-->
            <framework>KakaoOpenSDK</framework>            <!--(Kakao SDK)-->
        </frameworks>

### Step.2 - Add the code of ShareSDK

##### 1.Init ShareSDK

In your project's mainclass, you will find a override method name **didFinishLaunching**.Add the flollowing code in this method.Like this:

        //total HashMap 
        HashMap<Number, HashMap<String, String>> totalMap = new HashMap<Number, HashMap<String, String>> ();

        //Sina
        HashMap<String, String> sinaHashMap = new HashMap<String, String>();
        sinaHashMap.put("app_key", "568898243");
        sinaHashMap.put("app_secret", "38a4f8204cc784f81f9f0daaf31e02e3");
        sinaHashMap.put("redirect_uri", "http://www.sharesdk.cn");
        sinaHashMap.put("auth_type", "both");
        totalMap.put(SSDKPlatformType.SinaWeibo.value(), sinaHashMap);

        //Tencent 
        HashMap<String, String> tencentHashMap = new HashMap<String, String>();
        tencentHashMap.put("app_key", "801307650");
        tencentHashMap.put("app_secret", "ae36f4ee3946e1cbb98d6965b0b2ff5c");
        tencentHashMap.put("redirect_uri", "http://www.sharesdk.cn");
        totalMap.put(SSDKPlatformType.TencentWeibo.value(), tencentHashMap);

        //other platforms...

        //Init ShareSDK(The first param,appkey,you can get it from [mob.com](http://mob.com))
        ShareSDK.registerApp("iosv1101", totalMap);

##### 2.Authorization example:

        //authorize Sina
        ShareSDK.authorize(SSDKPlatformType.SinaWeibo, null, new ShareSDK.SSDKAuthorizeStateChangedHandler() {

            @Override
            public void invoke(SSDKResponseState state, SSDKUser user, NSError error) {

                switch (state)
                {
                    case Begin:
                    break;
                    case Success:
                    {
                    UIAlertView alert = new UIAlertView("Success!","",null,"OK");
                    alert.show();
                    break;
                    }
                    case Fail:
                    {
                    UIAlertView alert = new UIAlertView("Authorize failed!","",null,"OK");
                    alert.show();
                    break;
                    }
                    case Cancel:
                    {
                    UIAlertView alert = new UIAlertView("Cancel!","",null,"OK");
                    alert.show();
                    break;
                    }
                }
            }
        });

##### 3.Get user info example:

        //Get user info of QQ user
        ShareSDK.getUserInfo(SSDKPlatformType.QQ, new ShareSDK.SSDKGetUserStateChangedHandler() {

            @Override
            public void invoke(SSDKResponseState state, SSDKUser user, NSError error) {

                switch (state)
                {
                    case Begin:
                    break;
                    case Success:
                    {
                    UIAlertView alert = new UIAlertView("Success!","",null,"OK");

                    System.out.println("user uid:" + user.getUid());
                    System.out.println("nickname:" + user.getNickname());
                    alert.show();
                    break;
                    }
                    case Fail:
                    {
                    UIAlertView alert = new UIAlertView("Authorize failed!","",null,"OK");
                    alert.show();

                    String errorInfo =  error.getUserInfo().toString();

                    System.out.println("errorInfo:" + errorInfo);
                    break;
                    }
                    case Cancel:
                    {
                    UIAlertView alert = new UIAlertView("Cancel!","",null,"OK");
                    alert.show();
                    break;
                    }
                }
            }
        });


##### 4.Sharing example:

        //Share to Sina 
        //Make a Share Param - NSMutableDictionary
        NSMutableDictionary<NSString, NSObject> shareParams = new NSMutableDictionary<> ();

        //Custom your share content
        NSString text = new NSString("Simple Share Content");
        NSString title = new NSString("Simple Share Title");
        NSURL url = new NSURL("http://mob.com");

        //custom your share image (url image)
        NSURL imageUrl = new NSURL("http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg");
        SSDKImage urlImage = new SSDKImage(imageUrl);
        NSArray<SSDKImage> imagesArray = new NSArray<SSDKImage>(urlImage);

        //set all the content
        ShareParamsManager.setBasicShareParams(shareParams, text, imagesArray, url, title, SSDKContentType.Image);
    
        //then share
        ShareSDK.shareContent(SSDKPlatformType.SinaWeibo, shareParams, new ShareSDK.SSDKShareOnStageChange() {

            @Override
            public void invoke(SSDKResponseState state, NSDictionary<?, ?> userData, SSDKContentEntity shareContent, NSError error) {

                switch (state)
                {
                    case Begin:
                    break;
                    case Success:
                    {
                    UIAlertView alert = new UIAlertView("Success!","",null,"OK");
                    alert.show();
                    break;
                    }
                    case Fail:
                    {
                    UIAlertView alert = new UIAlertView("Failed!","",null,"OK");
                    alert.show();
                    NSErrorUserInfo userInfo = error.getUserInfo();
                    System.out.println("errorInfo:" + userInfo.getDictionary());
                    break;
                    }
                    case Cancel:
                    {
                    UIAlertView alert = new UIAlertView("Cancel!","",null,"OK");
                    alert.show();
                    break;
                    }
                }

            }
        });


### Step.3 - About setting the Info.plist.xml

##### 1.URL Scheme：

Some social platforms will jumps to their apps when authorizing or sharing.For there platfroms,you will need to set right the URL Scheme.Like this:

        <key>CFBundleURLTypes</key>
            <array>
                <dict>
                    <key>CFBundleURLSchemes</key>
                        <array>				
                            <string>QQ05FB8B52</string>         <!--(QQ Share)-->
                            <string>tencent100371282</string>   <!--(QQ Authorization)-->
                            <string>wx4868b35061f87885</string> <!--(WeChat)-->
                        </array>
                </dict>
            </array>

##### 2.If running above iOS 9.0,you will need to set the  LSApplicationQueriesSchemes:

        <key>LSApplicationQueriesSchemes</key>
            <array>
                <string>wechat</string> <!--(For wechat)-->
                <string>weixin</string> <!--(For wechat)-->
            </array>

##### 3.If running above iOS 9.0,you will need to set NSAppTransportSecurity

allow http request for all:

        <key>NSAppTransportSecurity</key>
            <dict>
                <key>NSAllowsArbitraryLoads</key>
                <true />
            </dict>

allow http request only for the exception domains:

        <key>NSAppTransportSecurity</key>
        <dict>
            <key>NSExceptionDomains</key>
            <dict>
                <key>qq.com</key>
                <dict>
                    <key>NSIncludesSubdomains</key>
                    <true/>
                    <key>NSExceptionRequiresForwardSecrecy</key>
                    <false/>
                    <key>NSExceptionAllowsInsecureHTTPLoads</key>
                    <true/>
                </dict>
            </dict>
        </dict>

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
If you have any question,please contact me by QQ:46131514


