package com.gsocket.event;

import com.gsocket.sever.Connection;


/**
 * 接受信息
 */
public interface OnReceiveListener {


    /**
     * 接受信息
     * @param connection 连接对象，可以发送消息
     * @param msg 信息
     */
    void received(Connection connection, byte[] msg);

}



