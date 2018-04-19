package com.mrysissb.paint.dao;
/** 
* @author 作者: mrysissb
* @version  
* 2018年4月8日 下午1:45:59
*/

import java.util.List;

import com.mrysissb.paint.entity.OsBrowser;

public interface OsBrowserDao {

	/**
	 * 查询所有OsBrowser
	 * @return list
	 */
	public List<OsBrowser> selectAll();
	/**
	 * 插入数据返回id
	 * @param os
	 * @param browser
	 * @return
	 */
	public int insertRtId(String os,String browser);
}
