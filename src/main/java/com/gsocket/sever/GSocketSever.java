package com.gsocket.sever;

import com.gsocket.event.sever.ConnectListener;
import com.gsocket.event.sever.ReceiveListener;
import com.gsocket.event.sever.SeverListenner;

import java.io.IOException;
import java.net.ServerSocket;

public class GSocketSever {
    private ServerSocket serverSocket;
    private ReceiveListener receiveListener;
    private ConnectListener connectListener;
    private SeverListenner severListenner;
    private ListeningThread listeningThread;

    public ReceiveListener getReceiveListener() {
        return receiveListener;
    }

    public void setReceiveListener(ReceiveListener receiveListener) {
        this.receiveListener = receiveListener;
    }

    public ConnectListener getConnectListener() {
        return connectListener;
    }


    public SeverListenner getSeverListenner() {
        return severListenner;
    }

    public void setSeverListenner(SeverListenner severListenner) {
        this.severListenner = severListenner;
    }



    public GSocketSever(int port, ReceiveListener receiveListener, ConnectListener connectListener) {
        this.receiveListener = receiveListener;
        this.connectListener = connectListener;
        startSever(port);
    }

    public GSocketSever(int port, ReceiveListener receiveListener, ConnectListener connectListener, SeverListenner severListenner) {
        this.receiveListener = receiveListener;
        this.connectListener = connectListener;
        this.severListenner = severListenner;
        startSever(port);
    }

    public GSocketSever(int port, ReceiveListener receiveListener) {
        this.receiveListener = receiveListener;
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
