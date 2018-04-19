package com.mrysissb.paint.service;

import javax.servlet.http.HttpServletRequest;

import com.mrysissb.paint.entity.Result;
/** 
* @author 作者: mrysissb
* @version 创建时间：2018年2月20日 
* paint业务类接口
*/
public interface PaintService {

	/**
	 * @param request
	 * @return Result
	 * 保存用户提交的画
	 */
	public Result savePaint(HttpServletRequest request);
	/**
	 * 
	 * @return
	 * 获取最后一幅画
	 */
	public Result getLastPaint();
	/**
	 * 发布画
	 * @param request
	 * @return
	 */
	public Result publish(HttpServletRequest request);
	/**
	 * 保存画
	 * @param request
	 * @return
	 */
	public Result save(HttpServletRequest request);
}
