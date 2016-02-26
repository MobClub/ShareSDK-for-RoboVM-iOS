package com.mob.sharesdk;

import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSURL;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Pointer;

@NativeClass
public class SSDKData extends NSObject{

	/* 初始化数据
	 * url URL地址
	 * */
	public SSDKData(NSURL url){
		super((SkipInit)null);
        initObject(init (url));
	}
	
	/* 初始化数据
	 * data NSData数据源
	 * */
	public SSDKData(NSData data){
		super((SkipInit)null);
        initObject(init (data));
	}
	
	/*绑定数据初始化方法(NSURL)*/
	@Method (selector = "initWithURL:")
	private native @Pointer long init (NSURL url);
	
	/*绑定数据初始化方法(NSData)*/
	@Method (selector = "initWithURL:")
	private native @Pointer long init (NSData data);
	
}
