package Server;

import Protocol.Message;
import Protocol.MessageIdentifier;
import Protocol.Requests.BroadcastMessage;
import Protocol.Requests.ConnectMessage;
import Protocol.Requests.DirectMessage;
import Protocol.Requests.DisconnectMessage;
import Protocol.Requests.QueryUsersMessage;
import Protocol.Requests.SendInsultMessage;
import Protocol.Responses.ConnectResponse;
import Protocol.Responses.DisconnectResponse;
import Protocol.Responses.FailedMessage;
import Protocol.Responses.QueryResponse;
import Exception.BadMessageException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.util.Pair;

/**
 * The client thread which handles incoming and outgoing messages.
 *
 */
public class ClientThread implements Runnable {

  private Socket socket;
  private ConcurrentMap<String, BlockingQueue<Message>> clientManagement;
  private BlockingQueue<Message> userInput;
  private String username;

  /**
   * Construct a new ClientThread.
   *
   * @param socket a Socket object which handles input and output
   * @param clientManagement a map contains all client information
   */
  public ClientThread(Socket socket, ConcurrentMap<String, BlockingQueue<Message>> clientManagement) {
    this.userInput = new LinkedBlockingQueue<>();
    this.socket = socket;
    this.clientManagement = clientManagement;
    this.username = "";
}

  /**
   * Runs the thread.
   */
  @Override
  public void run() {
    try (
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
      Message m = (Message) in.readObject();
      if (m.getIdentifier() == MessageIdentifier.CONNECT_MESSAGE) {
        ConnectMessage cm = (ConnectMessage) m;
        String name = new String(cm.getUsername());
        if (clientManagement.containsKey(name) ||
            name.equals("")) {
          throw new BadMessageException("Bad Connection message ");
        }
        this.username = new String(cm.getUsername());
        clientManagement.put(username, new LinkedBlockingQueue<>());
        out.writeObject(new ConnectResponse(true, "Successfully Connected!".getBytes().length, "Successfully Connected!".getBytes()));
        out.flush();
      } else {
        throw new BadMessageException("You have to connect first");
      }
      Thread userInputThread = new Thread(new UserInputRequestThread(userInput, in));
      userInputThread.start();
      handleClient(in, out);
      userInputThread.stop();
      socket.close();
    } catch (IOException | ClassNotFoundException | BadMessageException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Handles the client input request, e.g. Direct Message request.
   *
   * @param in the object input line which allow server to read client input
   * @param out the object out line which allow server to output to client
   * @throws InterruptedException Interrupted Exception is thrown when interrupt happened
   * @throws IOException IO error exception
   * @throws ClassNotFoundException Class not found error
   * @throws BadMessageException when bad message is given
   */
  private void handleClient(ObjectInputStream in, ObjectOutputStream out)
      throws InterruptedException, IOException, ClassNotFoundException, BadMessageException {
    while (true) {
      if (!userInput.isEmpty()) {
        Message m = userInput.take();
        if (m.getIdentifier() == MessageIdentifier.DISCONNECT_MESSAGE) {
          DisconnectMessage dm = (DisconnectMessage) m;
          // Check if the username is right
          if (!username.equals(new String(dm.getUsername()))) {
            handleFailedMsg(out, "Disconnect username is wrong");
          }
          clientManagement.remove(username);
          out.writeObject(new DisconnectResponse(true, "See you again!".getBytes().length,
              "See you again!".getBytes()));
          out.flush();
          break;
        }
        // Handle other message
        if (!handleRequest(m, out)) {
          handleFailedMsg(out, "The request failed");
        }
      }

      // Handle message taken from the queue
      if (!clientManagement.get(username).isEmpty()) {
        Message msgFromQueue = clientManagement.get(username).take();
        handleQueueRequest(msgFromQueue, out);
      }
    }
  }

  /**
   * Helper method which handle several user given request.
   *
   * @param request the request as Message object is given by client
   * @param out the object out line which allow server to output to client
   * @return a boolean to indicate if the request is successfully process
   * @throws IOException IO error exception
   */
  private boolean handleRequest(Message request, ObjectOutputStream out) throws IOException {
    switch (request.getIdentifier()) {
      // If broadcast, then every one should know
      case BROADCAST_MESSAGE:
        BroadcastMessage bm = (BroadcastMessage) request;
        String bmUser = new String(bm.getUsername());
        if (!username.equals(bmUser)) {
          return false;
        }
        for (String user : clientManagement.keySet()) {
          if (!username.equals(user)) {
            clientManagement.get(user).add(bm);
          }
        }
        break;
      // If user want the list of connected users
      case QUERY_CONNECTED_USERS:
        QueryUsersMessage qu = (QueryUsersMessage) request;
        String quUser = new String(qu.getUsername());
        if (!username.equals(quUser)) {
          return false;
        }
        List<Pair<Integer, byte[]>> userList = new ArrayList<>();
        for (String username : clientManagement.keySet()) {
          Pair<Integer, byte[]> user = new Pair<>(username.getBytes().length, username.getBytes());
          userList.add(user);
        }
        // Create a new query response containing all the users
        QueryResponse qr = new QueryResponse(clientManagement.keySet().size(), userList);
        out.writeObject(qr);
        out.flush();
        break;
      // If user wants to send a direct message to the other user
      case DIRECT_MESSAGE:
        DirectMessage dm = (DirectMessage) request;
        // If the username for the sender is not the same as this
        // or recipient does not exist
        String dmUser = new String(dm.getUsername());
        String dmRecipient = new String(dm.getRecipientUsername());
        if (!username.equals(dmUser) || !clientManagement.containsKey(dmRecipient)) {
          return false;
        }
        clientManagement.get(dmRecipient).add(dm);
        break;
      // If user wants to send an insult to the other user and broadcast to the chat room
      case SEND_INSULT:
        SendInsultMessage im = (SendInsultMessage) request;
        String imUser = new String(im.getUsername());
        if (!username.equals(imUser)) {
          return false;
        }
        for (String user : clientManagement.keySet()) {
          if (!username.equals(user)) {
            clientManagement.get(user).add(im);
          }
        }
        break;
    }
    return true;
  }

  /**
   * Helper methods which sends fail message to client if any error occurs.
   *
   * @param out the object out line which allow server to output to client
   * @param description the description which describe the reason of failing
   * @throws IOException IO error exception
   */
  private void handleFailedMsg(ObjectOutputStream out, String description) throws IOException {
    byte[] failedMsg = description.getBytes();
    FailedMessage fm = new FailedMessage(failedMsg.length, failedMsg);
    out.writeObject(fm);
    out.flush();
  }

  /**
   * Helper methods which handles the request sent by other client to this client.
   *
   * @param request the request as Message object is given by client
   * @param out the object out line which allow server to output to client
   * @throws IOException IO error exception
   */
  private void handleQueueRequest(Message request, ObjectOutputStream out)
      throws IOException, BadMessageException {
    switch (request.getIdentifier()) {
      case SEND_INSULT:
        // Gets the message from queue
        SendInsultMessage im = (SendInsultMessage) request;
        // Check if the user already gotten the message
        out.writeObject(im);
        out.flush();
        break;
      case DIRECT_MESSAGE:
        DirectMessage dm = (DirectMessage) request;
        String dmUser = new String(dm.getRecipientUsername());
        // Check if the message is for this user
        if (username.equals(dmUser)) {
          out.writeObject(dm);
          out.flush();
        } else {
          throw new BadMessageException("The recipient and username doesn't match");
        }
        break;
      case BROADCAST_MESSAGE:
        // Gets the message from queue
        BroadcastMessage bm = (BroadcastMessage) request;
        // Check if the user already gotten the message
        out.writeObject(bm);
        out.flush();
        break;
      default:
        break;
    }
  }
}
