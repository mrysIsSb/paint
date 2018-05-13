package com.mrysissb.paint.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.UserDoDao;
import com.mrysissb.paint.entity.UserDo;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月8日 下午1:31:01
*/
@Repository("userdodaoimpl")
public class UserDoDaoImpl implements UserDoDao {

	@Resource(name="mysql")
	private JdbcTemplate jdbctemplate;
	
//	private Logger logger=Logger.getLogger(UserDoDaoImpl.class);
	
	private Logger logger=LoggerFactory.getLogger(UserDoDaoImpl.class);
	@Override
	public int insert(UserDo userdo, int osbrowserid) {
//		logger.info("userDo开始");
		logger.error("123123");
		String sql="insert into user_do(userid,url,ip,time,osbrowserid)value(?,?,?,now(),?)";
		Object[] args=new Object[] {
				userdo.getUserid(),
				userdo.getUrl(),
				userdo.getIp(),
				osbrowserid
		};		
		return jdbctemplate.update(sql, args);
	}

}
