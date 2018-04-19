package com.mrysissb.paint.tool;


import javax.annotation.Resource;
import org.apache.http.impl.client.CloseableHttpClient;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月14日 下午7:08:34
*/
public class HttpClientTool {

	@Resource(name="httpClient")
	private CloseableHttpClient httpClient;
}
