package ChatServer;

//import ChatClient.Client;
import ChatClient.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();

//    private static ExecutorService pool = Executors.newFixedThreadPool(1);
    private static ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while(true) {
            System.out.println("Server waiting for connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client!");
            ClientHandler clientThread = new ClientHandler(clientSocket);
            clients.add(clientThread);

            System.out.println(clients.size());

            pool.execute(clientThread);
            System.out.println(pool.getActiveCount());
            if(clients.size() > 1){
                System.out.println("Server accepts only one connection");
                clients.remove(clients.size()-1);
                clientThread.stop(); // ne go spira i NZ ZASHTO
            }
        }


    }

//    public void start(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        while(true) {
//            clientSocket = serverSocket.accept();
//
//            Client client = new Client();
////            client.clientSocket = clientSocket;
//            client.newClient();
//
//
////            clientThread = new Thread(new ClientInit(clientSocket));
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String msg = in.readLine();
//
//            while (!msg.equalsIgnoreCase("stop")) {
//                if ("hello server".equals(msg)) {
//                    System.out.println("hello client");
//                } else {
//                    System.out.println("Sent msg: " + msg);
//                }
//                msg = in.readLine();
//
//                if (msg == null) {
//                    break;
//                }
//            }
//        }
//    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Server is running... I hope...");
        Server server=new Server();
        server.start(6666);
        server.stop();
    }


}
