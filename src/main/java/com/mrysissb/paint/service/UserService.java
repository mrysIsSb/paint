package com.mrysissb.paint.service;
/** 
* @author 作者: mrysissb
* @version 创建时间：2018年2月21日 下午7:15:22 
* 用户业务接口
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrysissb.paint.entity.Result;
import com.mrysissb.paint.entity.UserDo;

public interface UserService {
	/**
	 * 
	 * @param request
	 * @return Result
	 * 
	 * 用户登录
	 */
	public Result login(HttpServletRequest request);
	/**
	 * 是否存在username
	 * @param request
	 * @return
	 */
	public Result isHaveUsername(HttpServletRequest request);
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	public Result register(HttpServletRequest request);
	/**
	 * 添加userdo
	 * @param userdo
	 */
	public void addUserDo(UserDo userdo);
	/**
	 * 添加userdo
	 * @param userdo
	 * @param osBrowserId
	 */
	public void addUserDo(UserDo userdo,int osbrowserid);
}
