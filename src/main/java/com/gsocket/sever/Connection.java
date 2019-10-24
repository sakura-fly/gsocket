package com.gsocket.sever;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread {


    private Socket socket;
    private GSocketSever gSocketSever;
    private boolean isRunning;

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void run() {
        while (isRunning) {
            // Check whether the socket is onClosed.
            if (socket.isClosed()) {
                close();
                break;
            }
            try {
                InputStream is = socket.getInputStream();
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int ch;
                if ((ch = is.read(buffer)) == -1) {
                    close();
                    continue;
                }
                bytestream.write(buffer, 0, ch);
                byte[] data = bytestream.toByteArray();
                bytestream.close();
                System.out.println("接收客户端的数据：" + String.format("0x%02x", data[0]));
                gSocketSever.getReceiveListener().onReceived(this, data);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }


    Connection(Socket socket, GSocketSever gSocketSever) {
        this.socket = socket;
        this.gSocketSever = gSocketSever;
        isRunning = true;
        if (gSocketSever.getConnectListener() != null) {
            gSocketSever.getConnectListener().onConnect(this);
        }
    }

    public void send(byte[] message) {
        PrintStream ps;
        try {
            ps = new PrintStream(socket.getOutputStream(), true);
            ps.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        isRunning = false;
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gSocketSever.getConnectListener() != null) {
            gSocketSever.getConnectListener().onClosed();
        }
    }
}
