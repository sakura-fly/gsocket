package com.gsocket.client;

import com.gsocket.event.client.ReceiveListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReceiviceThread extends Thread {
    private boolean isRun = true;
    private GSocketClient client;
    private ReceiveListener receiveListener;

    public ReceiviceThread(GSocketClient client, ReceiveListener receiveListener) {
        this.client = client;
        this.receiveListener = receiveListener;
    }

    @Override
    public void run() {
        while (isRun) {
            try {
                if (client.getSocket().isClosed()) {
                    close();
                    return;
                }
                InputStream is = client.getSocket().getInputStream();
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
                System.out.println("接收服务端的数据：" + String.format("0x%02x", data[0]));
                if (receiveListener != null) {
                    receiveListener.receive(client, data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                close();
                if (receiveListener != null) {
                    receiveListener.err(e);
                }
            }

        }
    }

    private void close() {
        isRun = false;
        if (client.getSocket() != null) {
            try {
                client.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
