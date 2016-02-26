package com.mob.sharesdk;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSURL;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;

@NativeClass
public class SSDKContentEntity extends NSObject{

	/* 分享内容实体
	 * */
	
	@Property(selector = "cid")
	public native String getCid ();

	@Property(selector = "setCid:")
	public native void setCid (String id);
	
	@Property(selector = "text")
	public native String getText ();

	@Property(selector = "setText:")
	public native void setText (String text);
	
	@Property(selector = "images")
	public native NSArray<SSDKImage> getImages ();

	@Property(selector = "setImages:")
	public native void setImages (NSArray<SSDKImage> images);
	
	@Property(selector = "urls")
	public native NSArray<NSURL> getUrls ();

	@Property(selector = "setUrls:")
	public native void setUrls (NSArray<NSURL> id);
	

}
