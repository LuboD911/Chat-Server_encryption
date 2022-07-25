package ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 6666;

        public Socket clientSocket;
        private PrintWriter out;
        public BufferedReader in;
        private Integer id; // za vbudeshte

    public Integer getId() {
        return id;
    }

    public Client setId(Integer id) {
        this.id = id;
        return this;
    }
    //        public void startConnection(String ip, int port) throws IOException {
//            clientSocket = new Socket(ip, port);
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(System.in));
//        }
//
//        public String sendMessage(String msg) throws IOException {
//            out.println(msg);
//            return msg;
//        }
//
//        public void stopConnection() throws IOException {
//            in.close();
//            out.close();
//            clientSocket.close();
//        }


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP,SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while(true){
            System.out.println(">");
            String command = keyboard.readLine();

            if(command.equalsIgnoreCase("stop")){
                break;
            }

            out.println(command);

//            String serverResponse = input.readLine();
//            System.out.println("Server says: " + serverResponse);

        }
    }

//    public static void main(String[] args) throws IOException {
//        Client client = new Client();
////        client.startConnection("127.0.0.1", 6666);
////        client.sendMessage("hello server");
////        String msg = client.in.readLine();
////        while(!msg.equalsIgnoreCase("stop")){
////            client.sendMessage(msg);
////            msg = client.in.readLine();
////        }
////        client.stopConnection();
//        client.newClient();
//
//    }

}
