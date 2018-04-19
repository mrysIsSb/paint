package com.mrysissb.paint.dao;
/** 
* @author 作者: mrysissb
* @version  
* 2018年2月26日 下午5:54:05
*/

import com.mrysissb.paint.entity.Register;
import com.mrysissb.paint.entity.UserInfo;

public interface UserInfoDao {
	/**
	 * 根据userid获取用户信息
	 * @param userid
	 * @return
	 */
	public UserInfo findByUserid(int userid);
	/**
	 * 新增userid和nickname
	 * @param register
	 * @return
	 */
	public Integer addUseridNickname(Register register);
}
