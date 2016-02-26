package com.mob.sharesdk;

import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIImage;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

@NativeClass
public class SSUIShareActionSheetItem extends NSObject{

	/* 分享菜单项
	 * 可自定义菜单的图标和名字
	 * 可指定按钮执行的内容
	 * */
	
	@Method (selector = "setIcon:")
	public native static void setIcon(UIImage image);
	
	@Method (selector = "icon")
	public native static UIImage getIcon();
	
	@Method (selector = "setLabel:")
	public native static void setLable(NSString label);
	
	@Method (selector = "label")
	public native static NSString getLabel();
	
	@Method (selector = "itemWithPlatformType:")
	public native static SSUIShareActionSheetItem itemWithPlatformType(SSDKPlatformType type);
	
	@Method (selector = "itemWithIcon:label:onClick:")
	public native static SSUIShareActionSheetItem itemWithCustom(UIImage icon, NSString label, ClickHandler click);
	
	public interface ClickHandler {
        void invoke ();
    }
	
}
