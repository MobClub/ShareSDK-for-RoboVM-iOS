package com.mob.sharesdk;
public enum SSDKResponseState {
	
	 /* 回调状态类型枚举
	  * */
	
	 Begin (0),
	 Success (1),
	 Fail (2),
	 Cancel (3) ;

	 private final int n;

	 private SSDKResponseState (int n) {
	    this.n = n;
	 }

	 public long value () {
	    return n;
	 }
	
}