
import com.gsocket.client.GSocketClient;
import com.gsocket.event.client.ReceiveListener;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class ClientTest {
    public static void main(String[] args) {
        client();
//        client();
    }

    public static void client() {
        GSocketClient client = null;
        try {
//            client = new GSocketClient("localhost", 666, new ReceiveListener() {
            client = new GSocketClient("127.0.0.1", 666, new ReceiveListener() {
                @Override
                public void receive(GSocketClient client, byte[] msg) {
                    System.out.println(Arrays.toString(msg));
                    client.close();
                }

                @Override
                public void err(Exception e) {
                    System.out.println("err");
                }
            });
//            client.send(new byte[]{(byte) 0xBB, (byte) 0x00, (byte) 0x0E, (byte) 0xB5, (byte) 0x02, (byte) 0x10, (byte) 0x11, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x66});
            client.send("helle".getBytes());
//            Thread.sleep(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
