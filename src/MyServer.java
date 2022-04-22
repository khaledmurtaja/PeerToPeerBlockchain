import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(6696);
        while (true){
            Socket socket=ss.accept();
            System.out.println("a peer is connected to the server");
            ClientHandler z=new ClientHandler(socket);
            Thread thread=new Thread(z);
            thread.start();
//            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            dataOutputStream.writeUTF("recevied data");
//            dataOutputStream.flush();
//            System.out.println("khkha");
        }
        //establishes connection


    }


}
