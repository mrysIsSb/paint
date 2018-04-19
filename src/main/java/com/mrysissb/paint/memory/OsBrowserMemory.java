package com.mrysissb.paint.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mrysissb.paint.dao.OsBrowserDao;
import com.mrysissb.paint.entity.OsBrowser;

/** 
 * 用于将在启动系统的时候将osbrowser的数据放到内存中减少读取数据的次数////这个里面的格式要记下
* @author 作者: mrysissb
* @version  
* 2018年4月9日 下午9:04:16
*/
@Component
public class OsBrowserMemory {

	@Resource(name="osbrowserdaoimpl")
	private OsBrowserDao osbrowserdaoimpl;
	
	private Map<String, Integer> map=new HashMap();
	
	private static OsBrowserMemory instance;
	
	@PostConstruct
    public void init() {
		instance = this;
		instance.osbrowserdaoimpl = this.osbrowserdaoimpl;
		getAll();
    }
	public static synchronized OsBrowserMemory getInstance() {
        return instance;  
	}
	public OsBrowserMemory() {
		System.out.println("OsBrowserMemory is instance");
	}
	private void getAll() {
		List<OsBrowser> list=osbrowserdaoimpl.selectAll();
		for (OsBrowser osBrowser : list) {
			map.put(osBrowser.getOs()+osBrowser.getBrowser(), osBrowser.getId());
		}
	}
	public int getId(String os,String browser) {
		Integer result=map.get(os+browser);
		System.out.println(result);
		if(result!=null) {
			return map.get(os+browser);
		}else {
			addOsBrowser(os, browser);
			return getId(os, browser);
		}	
	}
	
	private void addOsBrowser(String os ,String browser) {
		int id=osbrowserdaoimpl.insertRtId(os, browser);
		if(id!=0) {
			map.put(os+browser, id);
		}
	}
}
