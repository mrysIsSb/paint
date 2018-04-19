package com.mrysissb.paint.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.mrysissb.paint.dao.PaintContentDao;
import com.mrysissb.paint.dao.UserInfoDao;
import com.mrysissb.paint.entity.PaintContent;
import com.mrysissb.paint.entity.Result;
import com.mrysissb.paint.entity.UserInfo;
import com.mrysissb.paint.enums.State;
import com.mrysissb.paint.service.PaintService;
import com.mrysissb.util.JsonUtil;
import com.mrysissb.util.RequestDataUtil;

/** 
* @author 作者: mrysissb
* @version 创建时间：2018年2月20日 下午10:12:41 
* 
*/
@Repository("paintserviceimpl")
public class PaintServiceImpl implements PaintService {

	@Resource(name="paintcontentdaoimpl")
	private PaintContentDao paintcontentdao;
	@Resource(name="userinfodaoimpl")
	private UserInfoDao userinfodao;
	@Override
	public Result savePaint(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getLastPaint() {
		Result result=new Result();
		PaintContent pc=paintcontentdao.selectLast();
		String paint=JsonUtil.objectToJsonStr(pc);
		UserInfo ui=userinfodao.findByUserid(pc.getUserid());
		StringBuilder content= new StringBuilder(paint.substring(0, paint.length()-1));
		content.append(",\"nickname\":\"");
		content.append(ui.getNickname());
		content.append("\"}");
		result.setState(State.SUCCESS);
		result.setContent(content.toString());
		result.setMessage("获取成功");
		return result;
	}

	@Override
	public Result publish(HttpServletRequest request) {
		Result result=new Result();
		PaintContent paintContent=null;
		try {
			paintContent=(PaintContent) RequestDataUtil.encapsulationParameter(PaintContent.class, request);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(paintcontentdao.insertAll(paintContent)!=0) {
			result.setState(State.SUCCESS);
			result.setMessage("发布成功");
		}else {
			result.setState(State.ERROR);
			result.setMessage("发布失败");
		}
		return result;
	}

	@Override
	public Result save(HttpServletRequest request) {
		Result result=new Result();
		PaintContent paintContent=null;
		try {
			paintContent=(PaintContent) RequestDataUtil.encapsulationParameter(PaintContent.class, request);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(paintcontentdao.insertAll(paintContent)!=0) {
			result.setState(State.SUCCESS);
			result.setMessage("保存成功");
		}else {
			result.setState(State.ERROR);
			result.setMessage("保存失败");
		}
		return result;
	}

}
