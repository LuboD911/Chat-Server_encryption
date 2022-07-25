package ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    private final String secretKey = "kur";
    public ClientHandler(Socket clientSocket) throws IOException {

        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run(){
        try{
            while(true){
             String message = in.readLine();
             String messageEncrypted = AES.encrypt(message, secretKey);
             System.out.println("MSG encr: "+ messageEncrypted);
             System.out.println("MSG decr: "+ AES.decrypt(message, secretKey));
            }
        }catch (IOException e){
            System.err.println("IO exception in client handler");
        }
        finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    @Override
//    public void run() {
//
//        Client client = new Client();
//        try {
//            client.startConnection("127.0.0.1", 6666);
//            client.sendMessage("hello server");
//            String msg = client.in.readLine();
//            while (!msg.equalsIgnoreCase("stop")) {
//                client.sendMessage(msg);
//                msg = client.in.readLine();
//            }
//            client.stopConnection();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
