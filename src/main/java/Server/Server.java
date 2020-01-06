package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import Protocol.Message;

/**
 * The main server thread receives request from and sends
 * response to clients.
 */
public class Server {

  /*
  Port number for the client connection
   */
  private static final int PORT = 3000;

  /*
  The number of client can be connected
   */
  private static final int SIZE = 10;

  /*
  The executor
   */
  private static ExecutorService executorService = Executors.newFixedThreadPool(SIZE);



  /**
   * Starts the main server.
   */
  public static void main(String[] args) {
    /*
    All the clients are stored into the map
    */
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = new ConcurrentHashMap<>();
    runMainServer(clientManagement);
  }

  /**
   * helper method which accepts client connection and run its thread.
   *
   * @param clientManagement contains all the client information
   */
  private static void runMainServer(ConcurrentMap<String, BlockingQueue<Message>> clientManagement) {
    try (
        ServerSocket serverSocket = new ServerSocket(PORT);
    ) {
      System.out.println("Starting server");
      while (true) {
        System.out.println("Waiting for request");
        Socket socket = serverSocket.accept();
        System.out.println("Processing request");
        ClientThread newClient = new ClientThread(socket,clientManagement);
        executorService.submit(newClient);
        //socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
