package com.mob.sharesdk;

import org.robovm.apple.foundation.NSDate;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;

@NativeClass
public class SSDKCredential extends NSObject{

	@Property(selector = "uid")
	public native String getUid ();

	@Property(selector = "setUid:")
	public native void setUid (String id);
	
	@Property(selector = "token")
	public native String getToken ();

	@Property(selector = "setToken:")
	public native void setToken (String token);
	
	@Property(selector = "secret")
	public native String getSecret ();

	@Property(selector = "setSecret:")
	public native void setSecret (String secret);
	
	@Property(selector = "expired")
	public native NSDate getExpired ();

	@Property(selector = "setExpired:")
	public native void setExpired (NSDate expired);
	
	@Property(selector = "type")
	public native int getType ();

	@Property(selector = "setType:")
	public native void setType (int type);
	
	@Property(selector = "rawData")
	public native NSDictionary<?,?> getRawData ();

	@Property(selector = "setRawData:")
	public native void setRawData (NSDictionary<?,?> rawData);
	
	@Property(selector = "available")
	public native boolean getAvailable ();

}
