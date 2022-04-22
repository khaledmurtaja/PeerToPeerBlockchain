import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
    String Previous_Hash_Block;
    int Version;
    String Merkle_Root_Hash;
    String Hash;
    long Time_Stamp;
    int Nonce = 0;
    String text;
    static public List<Block> blockChain = new ArrayList<>();
    int Difficulty = 4;
    static {
        File myObj = new File("storage.txt");
        try {

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");

            }

        } catch (IOException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Block() {
    }

    public Block(String text, String previousHash) {
        this.text = text;
        this.Time_Stamp = new Date().getTime();
        this.Previous_Hash_Block = previousHash;
        this.Hash = calculateHash();

    }
    public void SetBlock(Block block) {
        MineBlock();
        block.Hash = Hash;
        blockChain.add(block);

    }
    public static void storeBlockData(ArrayList<Block> blockChain) {
        try {
            FileWriter myWriter = new FileWriter("storage.txt");
            for (Block block : blockChain) {
                myWriter.write("Hash:" + block.Hash + ",previous hash:" + block.Previous_Hash_Block + ",timestamp:" + block.Time_Stamp + "\n");

            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static void readFromStorage() {
        try {
            File myObj = new File("storage.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                formatLineFromFile(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void formatLineFromFile(String line) {
        String[] x = line.split(",");
        String hash = x[0].substring(x[0].indexOf(":") + 1, x[0].length());
        String prevHash = x[1].substring(x[1].indexOf(":") + 1, x[1].length());
        long TimeStamp=Long.parseLong(x[2].substring(x[2].indexOf(":")+1,x[2].length()));
        Block b=new Block();
        b.Hash=hash;
        b.Previous_Hash_Block=prevHash;
        b.Time_Stamp=TimeStamp;
        blockChain.add(b);
    }
    public Block GetBlock(String HashOfBlock) {
        for (Block block : blockChain) {
            if (block.Hash.equals(HashOfBlock)) {
                return block;
            }
        }
        return null;
    }

    public void MineBlock() {
        String target = "";
        for (int i = 0; i < Difficulty; i++) {
            target.concat("0");
        }
        System.out.println("mining in process...");
        while (!Hash.substring(0, Difficulty).equals(target)) {
            Nonce++;
            Hash = calculateHash();
        }

    }

    public static void ExploreBlocks() {
        for (int i = 0; i < blockChain.size(); i++) {
            Block block = blockChain.get(i);
            System.out.println("block #" + (i + 1) + "\npreviousHash:" + block.Previous_Hash_Block
                    + "\nHash:" + block.Hash + "\ntimeStamp:" + block.Time_Stamp
            );

        }

    }

    public String calculateHash() {
        String calculatedhash = Sha256(
                Previous_Hash_Block
                        + Long.toString(Time_Stamp)
                        + text
                        + Nonce
        );
        return calculatedhash;
    }

    public static String Sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
