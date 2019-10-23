package com.gsocket.sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ListeningThread extends Thread {
    private ServerSocket serverSocket;
    private GSocketSever gSocketSever;
    private boolean isRun;
    private List<Connection> connections;

    public ListeningThread(ServerSocket serverSocket, GSocketSever gSocketSever) {
        this.serverSocket = serverSocket;
        this.gSocketSever = gSocketSever;
        isRun = true;
        connections = new ArrayList<Connection>();
    }

    @Override
    public void run() {
        while (isRun) {
            if (serverSocket.isClosed()) {
                closed();
                break;
            }
            try {
                Socket socket = serverSocket.accept();
                Connection connectionThread = new Connection(socket, gSocketSever);
                connectionThread.start();
                connections.add(connectionThread);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closed() {
        isRun = false;
        for (Connection connection: connections) {
            connection.close();
        }
    }

}
