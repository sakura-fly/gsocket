import com.gsocket.event.OnConnectListener;
import com.gsocket.event.OnReceiveListener;
import com.gsocket.event.OnSeverClose;
import com.gsocket.sever.Connection;
import com.gsocket.sever.GSocketSever;

public class Test {


    public static void main(String[] args) {
        server();
    }

    // @org.junit.Test
    public static void server() {
        new GSocketSever(666, new OnReceiveListener() {
            public void received(Connection connection, byte[] msg) {
                System.out.println(new String(msg));
                connection.send(msg);
            }
        }, new OnConnectListener() {
            public void onConnect(Connection connection) {
                System.out.println("onConnect");
            }

            public void onClosed() {
                System.out.println("onClosed");
            }
        }, new OnSeverClose() {
            public void closed() {
                System.out.println("OnSeverClose");
            }
        });
    }

}
