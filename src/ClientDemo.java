import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {

    public void client() throws IOException {

        System.out.println("requesting to connect...");

        Socket client = null;
        Scanner getKey = null;
        Scanner getServer = null;

        // create a new writer "out"
        PrintWriter out = null;

        try {

            //
            // getInputStream is the process of capture
            //
            // getOutputStream is the process for you to input
            //
            client = new Socket("127.0.0.1", 6666);

            // look what data receive from server
            getServer = new Scanner(client.getInputStream());
            System.out.println(getServer.nextLine());

            out = new PrintWriter(client.getOutputStream());
            System.out.println("enter number: ");

            // read input
            getKey = new Scanner(System.in);
            while (getKey.hasNextLine()) {

                // write down to server controller
                out.println(getKey.nextLine());
                out.flush();
                try {
                    System.out.println("tip message: " + getServer.nextLine());
                    System.out.println("enter number: ");
                } catch (Exception e) {
                    System.out.println("game over!!");
                    break;
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            getKey.close();
            getServer.close();
            out.close();
            client.close();

        }

    }

    public static void main(String[] args) throws IOException {
        new ClientDemo().client();
    }
}
