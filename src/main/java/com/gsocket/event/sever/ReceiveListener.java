package com.gsocket.event.sever;

import com.gsocket.sever.Connection;


/**
 * 接受信息
 */
public interface ReceiveListener {


    /**
     * 接受信息
     * @param connection 连接对象，可以发送消息
     * @param msg 信息
     */
    void onReceived(Connection connection, byte[] msg);

}



