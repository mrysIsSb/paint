package com.mrysissb.paint.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.UserInfoDao;
import com.mrysissb.paint.entity.Register;
import com.mrysissb.paint.entity.UserInfo;

/** 
* @author 作者: mrysissb
* @version  
* 2018年2月26日 下午6:00:12
*/
@Repository("userinfodaoimpl")
public class UserInfoDaoImpl implements UserInfoDao {

	@Resource(name="mysql")
	private JdbcTemplate jdbctemplate;
	@Override
	public UserInfo findByUserid(int userid) {
		String sql="select id,userid,nickname,sex,phonenumber from user_info where userid=?";
		Object[] args=new Object[] {
				userid
		};
		List<UserInfo> list=jdbctemplate.query(sql, args, (ResultSet rs, int rowNum)->{
				UserInfo userinfo=new UserInfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUserid(rs.getInt("userid"));
				userinfo.setNickname(rs.getString("nickname"));
				userinfo.setSex(rs.getString("sex"));
				userinfo.setPhonenumber(rs.getString("phonenumber"));
				return userinfo;
			}); 
//		List<UserInfo> list=jdbctemplate.query(sql, args, new RowMapper<UserInfo>(){
//			
//			@Override
//			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				UserInfo userinfo=new UserInfo();
//				userinfo.setId(rs.getInt("id"));
//				userinfo.setUserid(rs.getInt("userid"));
//				userinfo.setNickname(rs.getString("nickname"));
//				userinfo.setSex(rs.getString("sex"));
//				userinfo.setPhonenumber(rs.getString("phonenumber"));
//				return userinfo;
//			}}); 
		if(list!=null) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public Integer addUseridNickname(Register register) {
		String sql="insert into user_info (userid,nickname)value(?,?)";
		return jdbctemplate.update(sql, new Object[] {register.getId(),register.getNickname()});
	}

}
