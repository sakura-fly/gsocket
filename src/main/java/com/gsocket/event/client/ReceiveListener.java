package com.gsocket.event.client;

import com.gsocket.client.GSocketClient;

public interface ReceiveListener {
    void receive(GSocketClient client, byte[] msg);

    void err(Exception e);
}
