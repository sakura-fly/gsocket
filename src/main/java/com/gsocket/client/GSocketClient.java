package com.gsocket.client;

import com.gsocket.event.client.ReceiveListener;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class GSocketClient {

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public GSocketClient(String host, int port, ReceiveListener receiveListener) throws IOException {
        socket = new Socket(host, port);
        ReceiviceThread receivice = new ReceiviceThread(this, receiveListener);
        receivice.start();
    }

//    private void receiveMsg() {
//
//
//    }

    public void send(String message) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()), true);
            writer.println(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void send(byte[] message) {
        try {
            socket.getOutputStream().write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
