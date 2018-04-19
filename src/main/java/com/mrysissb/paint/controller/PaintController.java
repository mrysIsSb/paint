package com.mrysissb.paint.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrysissb.paint.service.PaintService;
import com.mrysissb.util.JsonUtil;

@Controller
@RequestMapping("paintcontroller")
public class PaintController {

	@Resource(name="paintserviceimpl")
	private PaintService paintService;
	
	@ResponseBody
	@RequestMapping(value="/publish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private String publish(HttpServletRequest request) {
		return JsonUtil.objectToJsonStr(paintService.publish(request));
	}
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private String save(HttpServletRequest request) {
		return JsonUtil.objectToJsonStr(paintService.save(request));
	}
}
