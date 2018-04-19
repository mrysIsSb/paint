package com.mrysissb.paint.dao;

import java.util.List;

import com.mrysissb.paint.entity.PaintContent;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月5日 下午6:52:04
*/
public interface PaintContentDao {

	/**
	 * 添加内容
	 * @param paintContent
	 * @return
	 */
	public Integer insertAll(PaintContent paintContent);
	/**
	 * 获取最近一条数据
	 * @return
	 */
	public PaintContent selectLast();
}
