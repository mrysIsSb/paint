package com.mrysissb.paint.dao;

import com.mrysissb.paint.entity.UserDo;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月8日 下午1:27:49
*/
public interface UserDoDao {

	/**
	 * 插入userdo数据
	 */
	public int insert(UserDo userdo,int osbrowserid);
}
