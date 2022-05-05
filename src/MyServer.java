import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    // MY Server here act as the peer which has found the right hash and then send the blockchain to another peers
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(6661);
            Socket socket=ss.accept();
            System.out.println("a peer is connected to the server");
        if(Block.blockChain.size()==0){
            Block genesisBlock = new Block("Hi im the first block", "null");
            genesisBlock.SetBlock(genesisBlock);
        }
        Block genesisBlock = new Block("Hi im the first block", Block.blockChain.get(Block.blockChain.size()-1).Hash);
        genesisBlock.SetBlock(genesisBlock);
        Block secondBlock = new Block("Yo im the second block", genesisBlock.Hash);
        secondBlock.SetBlock(secondBlock);
        Block thirdBlock = new Block("Hey im the third block", secondBlock.Hash);
        thirdBlock.SetBlock(thirdBlock);
           System.out.println(Block.ExploreBlocks());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(Block.ExploreBlocks());
            dataOutputStream.flush();
        }
        //establishes connection


    }



