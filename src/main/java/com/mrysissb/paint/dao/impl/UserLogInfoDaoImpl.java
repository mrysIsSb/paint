package com.mrysissb.paint.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.UserLogInfoDao;
import com.mrysissb.paint.entity.UserLogInfo;


/** 
* @author 作者: mrysissb
* @version  
* 2018年4月18日 上午10:16:51
*/
@Repository("userloginfodaoimpl")
public class UserLogInfoDaoImpl implements UserLogInfoDao {

	@Resource(name="mysql")
	private JdbcTemplate jdbctemplate;
	@Override
	public void insertUserLogInfo(UserLogInfo userloginfo) {
		String sql="insert into user_log_info (userid,logintime,ip,address)value(?,now(),?,?)";
		jdbctemplate.update(sql, new Object[] {
			userloginfo.getUserid(),
			userloginfo.getIp(),
			userloginfo.getAddress()
		});

	}

}
