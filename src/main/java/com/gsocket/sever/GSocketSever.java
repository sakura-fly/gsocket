package com.gsocket.sever;

import com.gsocket.event.OnConnectListener;
import com.gsocket.event.OnReceiveListener;
import com.gsocket.event.OnSeverClose;

import java.io.IOException;
import java.net.ServerSocket;

public class GSocketSever {
    private ServerSocket serverSocket;
    private OnReceiveListener onReceiveListener;
    private OnConnectListener onConnectListener;
    private OnSeverClose onSeverClose;
    private ListeningThread listeningThread;

    public OnReceiveListener getOnReceiveListener() {
        return onReceiveListener;
    }

    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        this.onReceiveListener = onReceiveListener;
    }

    public OnConnectListener getOnConnectListener() {
        return onConnectListener;
    }


    public OnSeverClose getOnSeverClose() {
        return onSeverClose;
    }

    public void setOnSeverClose(OnSeverClose onSeverClose) {
        this.onSeverClose = onSeverClose;
    }



    public GSocketSever(int port, OnReceiveListener onReceiveListener, OnConnectListener onConnectListener) {
        this.onReceiveListener = onReceiveListener;
        this.onConnectListener = onConnectListener;
        startSever(port);
    }

    public GSocketSever(int port, OnReceiveListener onReceiveListener, OnConnectListener onConnectListener, OnSeverClose onSeverClose) {
        this.onReceiveListener = onReceiveListener;
        this.onConnectListener = onConnectListener;
        this.onSeverClose = onSeverClose;
        startSever(port);
    }

    public GSocketSever(int port, OnReceiveListener onReceiveListener) {
        this.onReceiveListener = onReceiveListener;
        startSever(port);
    }


    /**
     * 启动服务
     * @param port 端口
     */
    private void startSever(int port) {
        try {
            serverSocket = new ServerSocket(port);
            listeningThread = new ListeningThread(serverSocket, this);
            listeningThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
