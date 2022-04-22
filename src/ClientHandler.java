import java.net.Socket;

public class ClientHandler implements  Runnable{
    Socket socket;
    public ClientHandler(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        System.out.println("in a new client");
    }
}
