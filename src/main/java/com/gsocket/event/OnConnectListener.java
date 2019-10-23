package com.gsocket.event;

import com.gsocket.sever.Connection;


/**
 * 连接事件
 */
public interface OnConnectListener {
    /**
     * 连接成功
     * @param connection 连接对象
     */
    void onConnect(Connection connection);

    /**
     * 关闭事件
     */
    void onClosed();
}
