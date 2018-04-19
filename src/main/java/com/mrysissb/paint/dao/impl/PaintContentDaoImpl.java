package com.mrysissb.paint.dao.impl;

import java.sql.ResultSet;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.PaintContentDao;
import com.mrysissb.paint.entity.PaintContent;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月5日 下午6:57:46
*/
@Repository("paintcontentdaoimpl")
public class PaintContentDaoImpl implements PaintContentDao {

	@Resource(name="mysql")
	private JdbcTemplate jdbcTemplate;
	@Override
	public Integer insertAll(PaintContent paintContent) {
		String sql="insert into paint_content (label,content,`describe`,userid,state,createtime)value(?,?,?,?,?,now())";
		Object[] args=new Object[] {
				paintContent.getLabel(),
				paintContent.getContent(),
				paintContent.getDescribe(),
				paintContent.getUserid(),
				paintContent.getState()
		};
		return jdbcTemplate.update(sql, args);
	}
	@Override
	public PaintContent selectLast() {
		String sql="select id,label,content,`describe`,userid,state,createtime from paint_content where state='1' order by createtime desc limit 1";
		return (PaintContent) jdbcTemplate.query(sql,(ResultSet rs, int rowNum)->{
			PaintContent content=new PaintContent();
			content.setId(rs.getInt("id"));
			content.setLabel(rs.getString("label"));
			content.setContent(rs.getString("content"));
			content.setDescribe(rs.getString("describe"));
			content.setUserid(rs.getInt("userid"));
			content.setCreatetime((Date)rs.getObject("createtime"));
			return content;
		}).get(0);
	}

}
