package com.mrysissb.paint.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.OsBrowserDao;
import com.mrysissb.paint.entity.OsBrowser;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月8日 下午1:52:50
*/
@Repository("osbrowserdaoimpl")
public class OsBrowserDaoImpl implements OsBrowserDao {

	@Resource(name="mysql")
	private JdbcTemplate jdbctemplate;
	public OsBrowserDaoImpl() {
		System.out.println("os----------------------------------");
	}
	@Override
	public List<OsBrowser> selectAll() {
		String sql="select id,os,browser from os_browser";
		List<OsBrowser> list=jdbctemplate.query(sql, (ResultSet rs, int rowNum)->{
			OsBrowser ob=new OsBrowser();
			ob.setId(rs.getInt("id"));
			ob.setOs(rs.getString("os"));
			ob.setBrowser(rs.getString("browser"));
			return ob;
		});
		return list;
	}
	@Override
	public int insertRtId(String os, String browser) {
		String sql="insert into os_browser (os,browser)value(?,?)";
		if(jdbctemplate.update(sql,new Object[] {
				os,
				browser
		})!=0) {
			sql="select last_insert_id()";
			return jdbctemplate.queryForObject(sql, Integer.class);
		}
		return 0;
	}

}
