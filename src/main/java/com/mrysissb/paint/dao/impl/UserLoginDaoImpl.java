package com.mrysissb.paint.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.UserLoginDao;
import com.mrysissb.paint.entity.Register;
import com.mrysissb.paint.entity.UserLogin;

/** 
* @author 作者: mrysissb
* @version  
* 2018年2月21日 下午9:48:37
*/
@Repository("userlogindaoimpl")
public class UserLoginDaoImpl implements UserLoginDao {

	@Autowired
	@Qualifier("mysql")
	private JdbcTemplate jdbctemplate;
	@Override
	public Integer login(UserLogin userlogin) {
		String sql="select id from user_login where username=? and password =?";
		Integer id=0;
		try {
			id=jdbctemplate.queryForObject(sql, new Object[] {
					userlogin.getUsername(),
					userlogin.getPassword()
			}, Integer.class);
		} catch (Exception e) {
		}
		return id;
	}
	@Override
	public void updateLoginInfo(int userid,String ip,String address) {
		String sql="update user_login set lasttime=now(),ip=?,address=? where id=?";
		Object[] args=new Object[] {
				ip,
				address,
				userid
		};
		jdbctemplate.update(sql, args);
	}
	@Override
	public Integer findIdByUsername(String username) {
		String sql="select id from user_login where username=?";
		Integer id=0;
		try {
			id=jdbctemplate.queryForObject(sql, new Object[] {
					username
			}, Integer.class);
		} catch (Exception e) {
		}
		return id;
	}
	@Override
	public Integer addUser(Register register) {
		String sql="insert into user_login (username,password,lasttime)value(?,?,now())";
		if(jdbctemplate.update(sql,new Object[] {
				register.getUsername(),
				register.getPassword()
		})!=0) {
			sql="select last_insert_id()";
			return jdbctemplate.queryForObject(sql, Integer.class);
		}
		return 0;
	}

}
