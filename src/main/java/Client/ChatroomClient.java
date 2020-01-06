package Client;

import Protocol.Message;
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
import assignment7.Generator;
import assignment7.Grammar;
import assignment7.JSONParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;

public class ChatroomClient extends Socket {
  private static final String SERVER_IP = "";
  private static final int SERVER_PORT = 3000;

  private Socket socket;
  private static ObjectOutputStream toServer = null;
  private static ObjectInputStream fromServer = null;
  private static String userName;

  public static void main(String[] args) {
    args = new String[]{"", "3000"};
    if(args.length != 2){
      System.out.println("Useage: need Server IP and port number");
    }
    try {
      Scanner scanner = new Scanner(System.in);
      Socket socket = new Socket(args[0], 3000);
      toServer = new ObjectOutputStream(socket.getOutputStream());
      fromServer = new ObjectInputStream(socket.getInputStream());
      System.out.println("please enter your name");
      String fromUser = scanner.nextLine();
      userName = fromUser;

      while(true){
        if (fromUser.equals("?")) {
          System.out.println("logoff: disconnect from server");
          System.out.println("who: query all users from server");
          System.out.println("@user: send direct message to user");
          System.out.println("@all: send broadcast message to all users");
          System.out.println("!user: send insult to all users");
          System.out.println("you can start typing");
        }else{
          Message message = sendMessage(fromUser);
          toServer.writeObject(message);
          toServer.flush();
          if(message.isConnectMessage()){
            Message resp = (Message) fromServer.readObject();
            if(resp.isConnectResponse()){
              ConnectResponse response = (ConnectResponse) resp;
              String res = new String (response.getMessage());
              System.out.println(res);
            }
          }
          while ((message = (Message) fromServer.readObject()) != null) {
            String res = getMessage(message);
            System.out.println(res);
          }
        }
        fromUser = scanner.nextLine();
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Message sendMessage(String fromUser) throws IOException, ClassNotFoundException {
     if (fromUser.startsWith("!")) {
      String recipientName = fromUser.replace("!", "");
      Path path = Paths.get("grammars/insult_grammar.json");
      Grammar grammar = JSONParser.parse(path);
      Generator generator = new Generator(grammar);
      String insult = generator.generate();
      SendInsultMessage sendInsultMessage = new SendInsultMessage(userName.length(),
          userName.getBytes(), recipientName.length(), recipientName.getBytes(), insult.length(), insult.getBytes());
      System.out.println("send insult");
      return sendInsultMessage;
    }else if (fromUser.equals("logoff")) {
       DisconnectMessage disconnectMessage = new DisconnectMessage(userName.length(),
           userName.getBytes());
       System.out.println(userName + " is going to log off");
       return disconnectMessage;
     }else if(fromUser.equals(userName)){
      ConnectMessage connectMessage = new ConnectMessage(userName.length(), userName.getBytes());
      System.out.println("trying to connect with server");
      return connectMessage;
    } else if (fromUser.equals("who")) {
       QueryUsersMessage queryUsersMessage = new QueryUsersMessage(userName.length(),
          userName.getBytes());
      System.out.println(userName + " queried users in system");
      return queryUsersMessage;
    } else if (fromUser.startsWith("@all")) {
      BroadcastMessage broadcastMessage = new BroadcastMessage(userName.length(),
         userName.getBytes(), fromUser.length(), fromUser.getBytes());
      System.out.println(userName + " broadcast in system");
      return broadcastMessage;
    } else {
      String[] splits = fromUser.split("\\s");
      String recipientUser = splits[0].replace("@", "");
      int len = splits[0].length();
      String m = userName + ":" + fromUser.substring(len).trim();
      DirectMessage directMessage = new DirectMessage(userName.length(),
          userName.getBytes(),
          recipientUser.length(), recipientUser.getBytes(), m.length(),
          m.getBytes());
      System.out.println(userName + " sent direct message");
      return directMessage;
    }
  }

  private static String getMessage(Message message){
    if(message.isDisconnectResponse()){
      DisconnectResponse disconnect = (DisconnectResponse) message;
      String res = new String(disconnect.getMessage());
      return res;
    }else if(message.isQueryResponse()){
      QueryResponse query = (QueryResponse) message;
      List<Pair<Integer, byte[]>> users = query.getUsers();
      StringBuilder sb = new StringBuilder();
      for(Pair<Integer, byte[]> user : users){
        sb.append(new String(user.getValue())).append(" ,");
      }
      return sb.toString();
    }else if(message.isFailedMessage()){
      FailedMessage fail = (FailedMessage) message;
      String res = new String(fail.getDescription());
      return fail.toString();
    }else if(message.isConnectResponse()){
      ConnectResponse connect = (ConnectResponse) message;
      String res = new String(connect.getMessage());
      return res;
    }else if(message.isBroadcastMessage()){
      BroadcastMessage broadCast = (BroadcastMessage) message;
      String res = new String(broadCast.getMessage());
      return res;
    } else if(message.isDirectMessage()){
      DirectMessage direct = (DirectMessage) message;
      String res = new String(direct.getMessage());
      return res;
    } else if(message.isSendInsultMessage()){
      SendInsultMessage insult = (SendInsultMessage) message;
      String res = new String(insult.getMsg());
      return res;
    }else{
      return "no response yet";
    }
  }
}
