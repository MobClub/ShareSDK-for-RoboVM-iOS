package com.mob.sharesdk;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSDate;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;

@NativeClass
public class SSDKUser extends NSObject{

	/* 用户信息类型
	 * */
	
	public SSDKUser(){
		super((SkipInit)null);
  
	}
	
	@Property(selector = "platformType")
	public native SSDKPlatformType getPlatformType ();

	@Property(selector = "setPlatformType:")
	public native void setPlatformType (SSDKPlatformType platformType);
	
	@Property(selector = "credential")
	public native SSDKCredential getCredential ();

	@Property(selector = "setCredential:")
	public native void setCredential (SSDKCredential credential);
	
	@Property(selector = "uid")
	public native String getUid ();

	@Property(selector = "setUid:")
	public native void setUid (String uid);
	
	@Property(selector = "nickname")
	public native String getNickname ();

	@Property(selector = "setNickname:")
	public native void setNickname (String nickname);
	
	@Property(selector = "icon")
	public native String getIcon ();

	@Property(selector = "setIcon:")
	public native void setIcon (String icon);
	
	@Property(selector = "gender")
	public native int getGender ();

	@Property(selector = "setGender:")
	public native void setGender (int gender);
	
	@Property(selector = "url")
	public native String getUrl ();

	@Property(selector = "setUrl:")
	public native void setUrl (String url);
	
	@Property(selector = "aboutMe")
	public native String getAboutMe ();

	@Property(selector = "setAboutMe:")
	public native void setAboutMe (String aboutMe);

	@Property(selector = "verifyType")
	public native long getVerifyType ();

	@Property(selector = "setVerifyType:")
	public native void setVerifyType (long verifyType);
	
	@Property(selector = "verifyReason")
	public native String getVerifyReason ();

	@Property(selector = "setVerifyReason:")
	public native void setVerifyReason (String verifyReason);
	
	@Property(selector = "birthday")
	public native NSDate getBirthday ();

	@Property(selector = "setBirthday:")
	public native void setBirthday (NSDate birthday);
	
	@Property(selector = "followerCount")
	public native long getFollowerCount ();

	@Property(selector = "setFollowerCount:")
	public native void setFollowerCount (long followerCount);
	
	@Property(selector = "friendCount")
	public native long getFriendCount ();

	@Property(selector = "setFriendCount:")
	public native void setFriendCount (long friendCount);
	
	@Property(selector = "shareCount")
	public native long getShareCount ();

	@Property(selector = "setShareCount:")
	public native void setShareCount (long shareCount);
	
	@Property(selector = "regAt")
	public native double getRegAt ();

	@Property(selector = "setRegAt:")
	public native void setRegAt (double regAt);
	
	@Property(selector = "level")
	public native long getLevel ();

	@Property(selector = "setLevel:")
	public native void setLevel (long level);
	
	@Property(selector = "educations")
	public native NSArray<?> getEducations ();

	@Property(selector = "setEducations:")
	public native void setEducations (NSArray<?> educations);
	
	@Property(selector = "works")
	public native NSArray<?> getWorks ();

	@Property(selector = "setWorks:")
	public native void setWorks (NSArray<?> works);
	
	@Property(selector = "rawData")
	public native NSDictionary<?,?> getRawData ();

	@Property(selector = "setRawData:")
	public native void setRawData (NSDictionary<?,?> rawData);
}
	

