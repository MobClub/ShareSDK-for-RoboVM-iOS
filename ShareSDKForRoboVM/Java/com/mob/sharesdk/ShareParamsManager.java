package com.mob.sharesdk;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSMutableDictionary;
import org.robovm.apple.foundation.NSNumber;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.NSURL;

public class ShareParamsManager {

	 /**本类的使用方法**/
	 /**1 : 首先自行创建一个NSMutableDictionary<NSString, NSObject> , 然后调用setBasicShareParams方法, 
	 * 传入由你创建的NSMutableDictionary,并定制分享的通用内容(text、images等等)。
	 * 通常仅需配置好基本的分享内容就能满足大部分要求**/
	
	private static NSMutableDictionary<NSString, NSObject> sharingParams;
	
	public static void setBasicShareParams(NSMutableDictionary<NSString, NSObject> shareParams,
			NSString text,NSArray<SSDKImage> images, NSURL url, 
			NSString title, SSDKContentType contentType){
		
		sharingParams = shareParams;
		
		if(text != null)
		{
			sharingParams.put("text", text);
		}
		if(images.size() > 0)
		{
			sharingParams.put("images", images);
		}
		if(url != null)
		{
			sharingParams.put("url", url);
		}
		if(contentType != null)
		{
			sharingParams.put("type", contentType.value());
		}

	}
	
	/**2 (可选): 在完成并定制过第一步的情况下,如需对某特定平台定制分享内容,则可选择性调用以下定制各个平台的方法**/
	
	public static void setSinaWeiboShareParams(NSString text, NSString title, SSDKImage image,
			NSURL url, double latitude, double longitude, 
			NSString ObjectID, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}

		if(ObjectID != null)
		{
			dict.put("object_id", ObjectID);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		
		dict.put("lat", NSNumber.valueOf(latitude));
		dict.put("long", NSNumber.valueOf(longitude));
		
		sharingParams.put("@platform(1)", dict);
	}
	
	public static void setTencentWeiboShareParams(NSString text, NSArray<SSDKImage> images,
			 double latitude, double longitude,  SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		if(text != null)
		{
			dict.put("text", text);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}

		dict.put("lat", NSNumber.valueOf(latitude));
		dict.put("long", NSNumber.valueOf(longitude));
		
		sharingParams.put("@platform(2)", dict);
	}
	
	public static void setWeChatShareParams(NSString text, NSString title, NSURL url,
			SSDKImage thumbImage, SSDKImage image, NSURL musicFileURL,
			NSString extInfo, SSDKData fileData, SSDKData emotionData,
			SSDKContentType type, SSDKPlatformType platformSubType){
			
		if(platformSubType.value() != 22 && platformSubType.value() != 23 && platformSubType.value() != 37){
			
			return;
		}
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		
		if(thumbImage != null)
		{
			dict.put("thumb_image", thumbImage);
		}
		if(image != null)
		{
			dict.put("images", image);
		}
		if(musicFileURL != null)
		{
			dict.put("audio_url", musicFileURL);
		}
		
		if(extInfo != null)
		{
			dict.put("ext_info", extInfo);
		}
		if(fileData != null)
		{
			dict.put("file_data", fileData);
		}
		if(emotionData != null)
		{
			dict.put("emoticon_data", emotionData);
		}
		if(type != null)
		{
			dict.put("type", type.value());
		}
		sharingParams.put("@platform(" + platformSubType.value() + ")", dict);
	}
	
	public static void setTwitterShareParams(NSString text, NSArray<SSDKImage> images,
			 double latitude, double longitude,  SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		if(text != null)
		{
			dict.put("text", text);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}

		dict.put("lat", NSNumber.valueOf(latitude));
		dict.put("long", NSNumber.valueOf(longitude));
		
		sharingParams.put("@platform(11)", dict);
	}
	
	public static void setQQShareParams(NSString text, NSString title, NSURL url,

			SSDKImage thumbImage, SSDKImage image,SSDKContentType type, SSDKPlatformType platformSubType){
			
		if(platformSubType.value() != 6 && platformSubType.value() != 24){
			
			return;
		}
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		
		if(thumbImage != null)
		{
			dict.put("thumb_image", thumbImage);
		}
		if(image != null)
		{
			dict.put("images", image);
		}
		if(type != null)
		{
			dict.put("type", type.value());
		}
		sharingParams.put("@platform(" + platformSubType.value() + ")", dict);
	}

	public static void setFacebookShareParams(NSString text, SSDKImage image, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(10)", dict);
		
	}

	public static void setSMSShareParams(NSString text,NSString title, NSArray<SSDKImage> images,
			NSArray<?> attachments,NSArray<NSString> recipients, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		if(attachments.size() > 0)
		{
			dict.put("attachments", attachments);
		}
		if(recipients.size() > 0)
		{
			dict.put("recipients", recipients);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(19)", dict);
	}
	 
	public static void setMailShareParams(NSString text,NSString title, NSArray<SSDKImage> images,
			NSArray<?> attachments,NSArray<NSString> recipients, 
			NSArray<NSString> ccRecipients, NSArray<NSString> bccRecipients, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		if(attachments.size() > 0)
		{
			dict.put("attachments", attachments);
		}
		if(recipients.size() > 0)
		{
			dict.put("recipients", recipients);
		}
		if(ccRecipients.size() > 0)
		{
			dict.put("cc_recipients", ccRecipients);
		}
		if(bccRecipients.size() > 0)
		{
			dict.put("bcc_recipients", bccRecipients);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(18)", dict);
	}

	public static void setCopyParams(NSString text, SSDKImage image,NSURL url, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(21)", dict);
		
	}

	public static void setDouBanShareParams(NSString text, NSString title, SSDKImage image,
			NSURL url, NSString urlDesc, SSDKContentType contentType){
		
	NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}

		if(urlDesc != null)
		{
			dict.put("desc", urlDesc);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(5)", dict);
	}
	
	public static void setRenRenShareParams(NSString text, SSDKImage image,
			NSURL url, NSString albumId, SSDKContentType contentType){
		
	NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}

		if(url != null)
		{
			dict.put("url", url);
		}

		if(albumId != null)
		{
			dict.put("album_id", albumId);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(7)", dict);
	}

	public static void setKaiXinShareParams(NSString text, SSDKImage image, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(8)", dict);
	}
	
	public static void setPocketShareParams(NSURL url, NSString title, NSArray<NSString> tags,
			 NSString tweetId){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}

		if(tweetId != null)
		{
			dict.put("tweet_id", tweetId);
		}
		if(tags.size() > 0)
		{
			dict.put("tags", tags);
		}
		sharingParams.put("@platform(26)", dict);
	}
	
	public static void setGooglePlusShareParams(NSString text, NSURL url, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}

		if(url != null)
		{
			dict.put("url", url);
		}

		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(14)", dict);
	}
	
	public static void setInstagramShareParams(SSDKImage image, CGPoint menuPoint){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		dict.put("menu_display_x", menuPoint.getX());
		dict.put("menu_display_y", menuPoint.getY());
		
		sharingParams.put("@platform(15)", dict);
	}
	
	public static void setLinkedInShareParams(NSString text,  SSDKImage image,
			NSURL url, NSString title, NSString urlDesc, NSString visibility, SSDKContentType contentType){
		
	NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}

		if(urlDesc != null)
		{
			dict.put("desc", urlDesc);
		}
		if(visibility != null)
		{
			dict.put("visibility", visibility);
		}
		
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(16)", dict);
	}
	
	public static void setTumblrShareParams(NSString text,  SSDKImage image,
			NSURL url, NSString title, NSString blogName, SSDKContentType contentType){
		
	NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}

		if(blogName != null)
		{
			dict.put("blog_name", blogName);
		}
		
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(17)", dict);
	}
	
	public static void setFlickrShareParams(NSString text,  SSDKImage image, NSString title,
			NSArray<?> tags, boolean isPublic, boolean isFriend,boolean isFamily,
			int safetyLevel, int contentType, int hidden){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(tags.size() > 0)
		{
			dict.put("tags", tags);
		}
		
		dict.put("is_public", NSNumber.valueOf(isPublic));
		dict.put("is_friend", NSNumber.valueOf(isFriend));
		dict.put("is_family", NSNumber.valueOf(isFamily));
		dict.put("safety_level", NSNumber.valueOf(safetyLevel));
		dict.put("content_type", NSNumber.valueOf(contentType));
		dict.put("hidden", NSNumber.valueOf(hidden));
		sharingParams.put("@platform(34)", dict);
	}
	
	public static void setWhatsAppShareParams(NSString text,  SSDKImage image,
			SSDKData audioData, SSDKData videoData, CGPoint menuPoint, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(audioData != null)
		{
			dict.put("audio", audioData);
		}
		if(videoData != null)
		{
			dict.put("video", videoData);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		dict.put("menu_display_x", menuPoint.getX());
		dict.put("menu_display_y", menuPoint.getY());

		sharingParams.put("@platform(43)", dict);
	}
	
	public static void setYouDaoNoteShareParams(NSString text,  NSArray<SSDKImage> images,
		  NSString title,NSString source,NSString author,NSString notebook){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(source != null)
		{
			dict.put("source", source);
		}
		if(author != null)
		{
			dict.put("author", author);
		}
		if(notebook != null)
		{
			dict.put("notebook", notebook);
		}
	
		sharingParams.put("@platform(27)", dict);
	}
	
	public static void setLineShareParams(NSString text, SSDKImage image, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(42)", dict);
		
	}
	
	public static void setEvernoteShareParams(NSString text,  NSArray<SSDKImage> images,
			  NSString title,NSString notebook, NSArray<NSString> tags,SSDKPlatformType platformSubType){
			
		if(platformSubType.value() != 12 && platformSubType.value() != 996)
		{
			return;
		}
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
			
		if(text != null)
		{
			dict.put("text", text);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		if(title != null)
		{
			dict.put("title", title);
		}

		if(notebook != null)
		{		
			dict.put("notebook", notebook);
		}
		if(tags.size() > 0)
		{
			dict.put("tags", tags);
		}
			
			
		sharingParams.put("@platform(" + platformSubType.value() + ")", dict);
	}
	
	public static void setAliPaySocialShareParams(NSString text, SSDKImage image, NSString title, 
			NSURL url, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(50)", dict);
		
	}
	
	public static void setPinterestShareParams(SSDKImage image,NSString desc, NSURL url, NSString boardId){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(desc != null)
		{
			dict.put("desc", desc);
		}
		if(boardId != null)
		{
			dict.put("board", boardId);
		}
		if(url != null)
		{
			dict.put("url", url);
		}

		sharingParams.put("@platform(30)", dict);
		
	    }
	
	public static void setKakaoShareParams(NSString text, NSArray<SSDKImage> images, NSString title, NSURL url,
			NSString permission, boolean enableShare, CGSize imageSize, NSString appButtonTitle,
			NSDictionary<?,?> androidExecParam, NSString androidMarkParam, 
			NSDictionary<?,?> iphoneExecParams, NSString iphoneMarkParam,
			NSDictionary<?,?> ipadExecParams, NSString ipadMarkParam,
			SSDKContentType contentType, SSDKPlatformType platformSubType){
		
		if(platformSubType.value() != 44 && platformSubType.value() != 45)
		{
			return;
		}
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		if(permission != null)
		{
			dict.put("permission", permission);
		}
		dict.put("enable_share", NSNumber.valueOf(enableShare));
		dict.put("image_width", imageSize.getWidth());
		dict.put("image_height", imageSize.getHeight());
		if(appButtonTitle != null)
		{
			dict.put("app_button_title", appButtonTitle);
		}
		if(androidExecParam != null)
		{
			dict.put("android_exec_param", androidExecParam);
		}
		if(androidMarkParam != null)
		{
			dict.put("android_market_​param", androidMarkParam);
		}
		
		if(iphoneExecParams != null)
		{
			dict.put("iphone_exec_param", iphoneExecParams);
		}
		if(iphoneMarkParam != null)
		{
			dict.put("iphone_market_param​param", iphoneMarkParam);
		}
		
		if(ipadExecParams != null)
		{
			dict.put("ipad_exec_param", ipadExecParams);
		}
		if(ipadMarkParam != null)
		{
			dict.put("ipad_market_param​param", ipadMarkParam);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(" + platformSubType.value() + ")", dict);
		
	}
	
	public static void setDropboxShareParams(SSDKData attachmentData)
	{
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(attachmentData != null)
		{
			dict.put("attachments", attachmentData);
		}
		
		sharingParams.put("@platform(35)", dict);
	}
	
	public static void setVKontakteShareParams(NSString text, NSArray<SSDKImage> images, NSURL url, NSString groupId,
			 boolean friendsOnly, double latitude,double longitude, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(images.size() > 0)
		{
			dict.put("images", images);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		if(groupId != null)
		{
			dict.put("group_id", groupId);
		}
		dict.put("is_friend", NSNumber.valueOf(friendsOnly));
		dict.put("lat", NSNumber.valueOf(latitude));
		dict.put("long", NSNumber.valueOf(longitude));
		
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		
		sharingParams.put("@platform(36)", dict);
	}
	
	public static void setMingDaoShareParams(NSString text,  SSDKImage image,
			NSURL url, NSString title, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(image != null)
		{
			dict.put("images", new NSArray<SSDKImage>(image));
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
	
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		sharingParams.put("@platform(41)", dict);
	}
	
	
	public static void setInstapaperShareParams(NSURL url, NSString title, NSString desc, NSString content, 
			boolean isPrivateFromSource,int folderId, boolean resolveFinalUrl){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(url != null)
		{
			dict.put("url", url);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(desc != null)
		{
			dict.put("desc", desc);
		}
		if(content != null)
		{
			dict.put("text", content);
		}
	
		dict.put("private_from_source", NSNumber.valueOf(isPrivateFromSource));
		dict.put("folder_id", NSNumber.valueOf(folderId));
		dict.put("resolve_final_url", NSNumber.valueOf(resolveFinalUrl));
		
		sharingParams.put("@platform(25)", dict);
	}
	
	public static void setYiXinShareParams(NSString text, NSString title, NSURL url,
			SSDKImage thumbImage, SSDKImage image, NSURL musicFileURL,
			NSString extInfo, SSDKData fileData, NSString comment, NSString userId,
			SSDKContentType type, SSDKPlatformType platformSubType){
			
		if(platformSubType.value() != 38 && platformSubType.value() != 39 && platformSubType.value() != 40){
			
			return;
		}
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(text != null)
		{
			dict.put("text", text);
		}
		if(title != null)
		{
			dict.put("title", title);
		}
		if(url != null)
		{
			dict.put("url", url);
		}
		
		if(thumbImage != null)
		{
			dict.put("thumb_image", thumbImage);
		}
		if(image != null)
		{
			dict.put("images", image);
		}
		if(musicFileURL != null)
		{
			dict.put("audio_url", musicFileURL);
		}
		
		if(extInfo != null)
		{
			dict.put("ext_info", extInfo);
		}
		if(fileData != null)
		{
			dict.put("file_data", fileData);
		}
		if(comment != null)
		{
			dict.put("comment", comment);
		}
		if(userId != null)
		{
			dict.put("uid", userId);
		}
		
		if(type != null)
		{
			dict.put("type", type.value());
		}
		sharingParams.put("@platform(" + platformSubType.value() + ")", dict);
	}
	
	public static void setFacebookMessengerShareParams(SSDKImage image, SSDKData gif, 
			SSDKData audio, SSDKData video, SSDKContentType contentType){
		
		NSMutableDictionary<NSString, NSObject> dict = new NSMutableDictionary<NSString, NSObject>();
		
		if(image != null)
		{
			dict.put("images", image);
		}
		if(gif != null)
		{
			dict.put("emoticon_data", gif);
		}
		if(audio != null)
		{
			dict.put("audio", audio);
		}
		if(video != null)
		{
			dict.put("video", video);
		}
		if(contentType != null)
		{
			dict.put("type", contentType.value());
		}
		
		sharingParams.put("@platform(46)", dict);
		
	}
}
