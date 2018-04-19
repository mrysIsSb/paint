package com.mrysissb.paint.dao;

import com.mrysissb.paint.entity.UserLogInfo;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月18日 上午10:13:48
*/
public interface UserLogInfoDao {

	/**
	 * 添加登录信息
	 * @param userlogininfo
	 */
	public void insertUserLogInfo(UserLogInfo userloginfo);
}
