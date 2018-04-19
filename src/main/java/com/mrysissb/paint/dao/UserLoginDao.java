package com.mrysissb.paint.dao;

import com.mrysissb.paint.entity.Register;
import com.mrysissb.paint.entity.UserLogin;

/** 
* @author 作者: mrysissb
* @version  
* 2018年2月21日 下午9:46:48
*/
public interface UserLoginDao {

	/**
	 * 
	 * @param userlogin
	 * @return 登录验证情况
	 * 验证登录信息
	 */
	public Integer login(UserLogin userlogin);
	
	/**
	 * 修改最后一次登录时间
	 * @param userid
	 */
	public void updateLoginInfo(int userid,String ip,String address);
	/**
	 * 根据username查询id
	 * @param username
	 * @return
	 */
	public Integer findIdByUsername(String username);
	/**
	 * 添加用户账号密码
	 * @param register
	 * @return
	 */
	public Integer addUser(Register register);
}
