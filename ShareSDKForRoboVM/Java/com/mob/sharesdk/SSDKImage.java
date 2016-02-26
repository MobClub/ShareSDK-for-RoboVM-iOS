package com.mob.sharesdk;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.UIImage;
import org.robovm.objc.annotation.Block;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Pointer;

@NativeClass
public class SSDKImage extends NSObject{

	/* 创建图片(网络)
	 * url 	    网络图片URL
	 * */
	public SSDKImage(NSURL url){
		super((SkipInit)null);
        initObject(init (url));
	}
	
	/* 创建图片（本地）
	 * image   	UIImage，图片对象
	 * format  	指定分享出去的图片格式,选择有三:1. "JPEG"  2."PNG" 3."JPEG-IMAGE-QUALITY" 如传入其他则默认为JPEG
	 * setting	配置选项,无特殊情况传入null即可
	 * */
	public SSDKImage(UIImage image, NSString format, NSDictionary<?,?> settings){
		super((SkipInit)null);
        initObject(init (image, format, settings));
	}
	
	/*绑定图片初始化方法(网络)*/
	@Method (selector = "initWithURL:")
	private native @Pointer long init (NSURL url);
	
	/*绑定图片初始化方法(本地)*/
	@Method (selector = "initWithImage:format:settings:")
	private native @Pointer long init (UIImage image,NSString format,NSDictionary<?,?> settings);
	
	/* 获取本地图片
	 * 如果构建的SSDKImage对象通过网络图片构建，可通过本方法获取其本地图片对象
	 * */
	@Method (selector = "getNativeImage:")
	public static native void getNativeImage(@Block ImageHandler handler );
	
	/*获取本地图片之回调*/
	public interface ImageHandler {
        void invoke (UIImage image);
    }
	
}
