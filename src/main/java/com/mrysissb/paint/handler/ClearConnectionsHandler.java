package com.mrysissb.paint.handler;

import org.apache.http.conn.HttpClientConnectionManager;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月14日 下午8:34:55
*/
public class ClearConnectionsHandler extends Thread{
	
	private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public ClearConnectionsHandler(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
