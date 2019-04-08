import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServController {
    private static int port = 8080;
    private InetAddress addr;
    private ServerSocket sock;
    private ArrayList<Socket> clients;
    public ServController() {
        clients = new ArrayList<Socket>();
            try {
                addr = InetAddress.getByName(null);
                System.out.println("Created Server Socket");
            } catch (UnknownHostException e) {
                System.out.println("Unable to connect to provided host");
                e.printStackTrace();
            }
        
    }
    public void init() {
        
    }
    public static void main(String[] args) {
        ServController servCon = new ServController();
    }
    
   

}
