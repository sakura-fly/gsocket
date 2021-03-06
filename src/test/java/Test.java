import com.gsocket.event.sever.ConnectListener;
import com.gsocket.event.sever.ReceiveListener;
import com.gsocket.event.sever.SeverListenner;
import com.gsocket.sever.Connection;
import com.gsocket.sever.GSocketSever;

public class Test {


    public static void main(String[] args) {
        server();
    }

    // @org.junit.Test
    public static void server() {
        new GSocketSever(666, new ReceiveListener() {
            public void onReceived(Connection connection, byte[] msg) {
                System.out.println(msg);
                connection.send(msg);
            }
        }, new ConnectListener() {
            public void onConnect(Connection connection) {
                System.out.println("onConnect");
            }

            public void onClosed() {
                System.out.println("onClosed");
            }
        }, new SeverListenner() {
            public void onClosed() {
                System.out.println("SeverListenner");
            }
        });
    }

}
