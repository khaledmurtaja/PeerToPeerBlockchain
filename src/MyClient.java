import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        //System.out.println("heelo");

        Socket socket=new Socket("localhost",6666);
       // DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
       // System.out.println(dataInputStream.readUTF());


    }
}
