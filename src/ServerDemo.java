import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ServerDemo {

    private int flag;

    public void server() throws IOException {

        // set up a server
        System.out.println("server ready wait for client connect...");

        PrintWriter out = null;
        Scanner getClient = null;
        ServerSocket server = null; // "ServerSocket" type for implement a server
        Socket client = null; // "Socket" type for dealing client side socket connect
        this.setFlag();

        try {
            server = new ServerSocket(6666);

            // set up an object to connect with client
            client = server.accept();
            System.out.println(client.getInetAddress() + " successful connect to sever.");

            out = new PrintWriter(client.getOutputStream()); // send data to client
            out.println("please guest a number between 1-100");
            out.flush(); // output all the data in buffer

            getClient = new Scanner(client.getInputStream()); // retrieve data from client

            // retrieve data from client
            while (getClient.hasNextLine()) {
                String returnMsg = null;
                String tmp = getClient.nextLine();
                if ("e".equals(tmp)) {
                    break;
                } else if ("c".equals(tmp)) {
                    returnMsg = "Welcome to new round of game !!!";
                    this.setFlag();
                } else {
                    int clientInput = Integer.parseInt(tmp);

                    if (clientInput > flag) {
                        returnMsg = "number is too big!";
                    } else if (clientInput < flag) {
                        returnMsg = "number is too small!";
                    } else {
                        returnMsg = "good guess!! continue(c), exit(e)";
                    }
                }

                out.println(returnMsg);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            out.close();
            getClient.close();
            server.close();
            client.close();

        }
    }

    public void setFlag() {
        Random r = new Random();
        flag = r.nextInt(100);
        System.out.println("guesting number game: flag =  " + flag);
    }

    public static void main(String[] args) throws IOException{
        new ServerDemo().server();
    }
}

