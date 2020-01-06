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
import Server.ClientThread;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import Exception.BadMessageException;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientThreadTest {

  private List<ClientThread> clientList;
  private final int PORT = 3000;

  @Test
  public void testConnect1() throws IOException, BadMessageException {
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = clientManagement = new ConcurrentHashMap<>();
    ServerSocket serverSocket = new ServerSocket(PORT);
    // Foo
    Socket fooClient = new Socket("localhost", PORT);
    ObjectOutputStream fooOut = new ObjectOutputStream(fooClient.getOutputStream());
    clientManagement.put("foo", new LinkedBlockingQueue<Message>());
    Thread foo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    foo.start();
    ConnectMessage cm = new ConnectMessage("foo".getBytes().length, "foo".getBytes());
    fooOut.writeObject(cm);

    // Bar
    Socket barClient = new Socket("localhost", PORT);
    ObjectOutputStream barOut = new ObjectOutputStream(barClient.getOutputStream());
    Thread bar = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    bar.start();
    cm = new ConnectMessage("".getBytes().length, "".getBytes());
    barOut.writeObject(cm);
    serverSocket.close();
    fooClient.close();
    barClient.close();
  }

  @Test
  public void testConnect2() throws IOException, BadMessageException {
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = clientManagement = new ConcurrentHashMap<>();
    ServerSocket serverSocket = new ServerSocket(PORT);
    Socket fooClient = new Socket("localhost", PORT);
    ObjectOutputStream fooOut = new ObjectOutputStream(fooClient.getOutputStream());
    Thread foo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    foo.start();
    QueryUsersMessage qum = new QueryUsersMessage("foo".getBytes().length, "foo".getBytes());
    fooOut.writeObject(qum);
    serverSocket.close();
    fooClient.close();
  }

  @Test
  public void testDisconnect() throws IOException, ClassNotFoundException {
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = clientManagement = new ConcurrentHashMap<>();
    ServerSocket serverSocket = new ServerSocket(PORT);

    Socket fooClient = new Socket("localhost", PORT);
    ObjectOutputStream fooOut = new ObjectOutputStream(fooClient.getOutputStream());
    Thread foo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    foo.start();
    ObjectInputStream fooIn = new ObjectInputStream(fooClient.getInputStream());
    ConnectMessage cm = new ConnectMessage("foo".getBytes().length, "foo".getBytes());
    fooOut.writeObject(cm);
    ConnectResponse cr = (ConnectResponse) fooIn.readObject();
    DisconnectMessage dmTest = new DisconnectMessage("random".getBytes().length, "random".getBytes());
    fooOut.writeObject(dmTest);
    FailedMessage fm = (FailedMessage) fooIn.readObject();
    Assert.assertEquals(fm, new FailedMessage( "Disconnect username is wrong".getBytes().length, "Disconnect username is wrong".getBytes()));
    DisconnectMessage dm = new DisconnectMessage("foo".getBytes().length, "foo".getBytes());
    fooOut.writeObject(dm);
    DisconnectResponse dr = (DisconnectResponse) fooIn.readObject();
    Assert.assertEquals(dr, new DisconnectResponse(true, "See you again!".getBytes().length, "See you again!".getBytes()));
    serverSocket.close();
    fooClient.close();

  }

  @Test
  public void testRequest() throws IOException, ClassNotFoundException {
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = clientManagement = new ConcurrentHashMap<>();
    ServerSocket serverSocket = new ServerSocket(PORT);

    // Foo
    Socket fooClient = new Socket("localhost", PORT);
    Thread foo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    foo.start();
    ObjectOutputStream fooOut = new ObjectOutputStream(fooClient.getOutputStream());
    ObjectInputStream fooIn = new ObjectInputStream(fooClient.getInputStream());
    fooOut.writeObject(new ConnectMessage("foo".getBytes().length, "foo".getBytes()));
    fooOut.flush();
    ConnectResponse crFoo = (ConnectResponse) fooIn.readObject();

    // Bar
    Socket barClient = new Socket("localhost", PORT);
    Thread bar = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    bar.start();
    ObjectOutputStream barOut = new ObjectOutputStream(barClient.getOutputStream());
    ObjectInputStream barIn = new ObjectInputStream(barClient.getInputStream());
    barOut.writeObject(new ConnectMessage("bar".getBytes().length, "bar".getBytes()));
    barOut.flush();
    ConnectResponse crBar = (ConnectResponse) barIn.readObject();

    // Broadcast
    BroadcastMessage bm = new BroadcastMessage("foo".getBytes().length,
        "foo".getBytes(), "Hello all".getBytes().length, "Hello all".getBytes());
    fooOut.writeObject(bm);
    fooOut.flush();

    BroadcastMessage msg = (BroadcastMessage) barIn.readObject();
    Assert.assertEquals(bm, msg);
    Assert.assertTrue(clientManagement.get("foo").size() == 0);

    BroadcastMessage falBm = new BroadcastMessage("random".getBytes().length,
        "random".getBytes(), "Hello all".getBytes().length, "Hello all".getBytes());
    fooOut.writeObject(falBm);
    fooOut.flush();
    FailedMessage fmBm = (FailedMessage) fooIn.readObject();
    Assert.assertEquals(fmBm, new FailedMessage("The request failed".getBytes().length, "The request failed".getBytes()));

    // Poo
    Socket pooClient = new Socket("localhost", PORT);
    Thread poo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    poo.start();
    ObjectOutputStream pooOut = new ObjectOutputStream(pooClient.getOutputStream());
    ObjectInputStream pooIn = new ObjectInputStream(pooClient.getInputStream());
    pooOut.writeObject(new ConnectMessage("poo".getBytes().length, "poo".getBytes()));
    pooOut.flush();
    ConnectResponse crPoo = (ConnectResponse) pooIn.readObject();

    // Insult
    SendInsultMessage sim = new SendInsultMessage("foo".getBytes().length, "foo".getBytes(),
        "bar".getBytes().length, "bar".getBytes(), "LOL".getBytes().length, "LOL".getBytes());

    fooOut.writeObject(sim);
    fooOut.flush();
    SendInsultMessage barSim = (SendInsultMessage) barIn.readObject();
    SendInsultMessage pooSim = (SendInsultMessage) pooIn.readObject();
    Assert.assertEquals(sim, barSim);
    Assert.assertEquals(barSim, pooSim);

    SendInsultMessage falSim = new SendInsultMessage("random".getBytes().length, "random".getBytes(),
        "bar".getBytes().length, "bar".getBytes(), "LOL".getBytes().length, "LOL".getBytes());
    fooOut.writeObject(falSim);
    fooOut.flush();
    FailedMessage fmSim = (FailedMessage) fooIn.readObject();
    Assert.assertEquals(fmSim, new FailedMessage("The request failed".getBytes().length, "The request failed".getBytes()));

    // Direct Message
    DirectMessage dm = new DirectMessage("foo".getBytes().length, "foo".getBytes(),
        "bar".getBytes().length, "bar".getBytes(), "Hello bar!".getBytes().length, "Hello bar!".getBytes());
    fooOut.writeObject(dm);
    fooOut.flush();
    DirectMessage barDm = (DirectMessage) barIn.readObject();
    Assert.assertEquals(barDm, dm);

    DirectMessage falDm = new DirectMessage("cool".getBytes().length, "cool".getBytes(),
        "ggg".getBytes().length, "ggg".getBytes(), "Hello ggg!".getBytes().length, "Hello ggg!".getBytes());
    fooOut.writeObject(falDm);
    fooOut.flush();
    FailedMessage fmDm = (FailedMessage) fooIn.readObject();
    Assert.assertEquals(fmDm, new FailedMessage("The request failed".getBytes().length, "The request failed".getBytes()));

    // Query Users
    QueryUsersMessage qum = new QueryUsersMessage("foo".getBytes().length, "foo".getBytes());
    fooOut.writeObject(qum);
    fooOut.flush();
    Pair<Integer, byte[]> fooPair = new Pair<>("foo".getBytes().length, "foo".getBytes());
    Pair<Integer, byte[]> barPair = new Pair<>("bar".getBytes().length, "bar".getBytes());
    Pair<Integer, byte[]> pooPair = new Pair<>("poo".getBytes().length, "poo".getBytes());
    List<Pair<Integer, byte[]>> userList = new ArrayList<>(Arrays.asList(pooPair, barPair, fooPair));

    QueryResponse qr = new QueryResponse(userList.size(), userList);
    QueryResponse qrFoo = (QueryResponse) fooIn.readObject();
    Assert.assertEquals(qr, qrFoo);

    fooOut.writeObject(new QueryUsersMessage("bar".getBytes().length, "bar".getBytes()));
    FailedMessage fmQum = (FailedMessage) fooIn.readObject();
    Assert.assertEquals(fmQum, new FailedMessage("The request failed".getBytes().length, "The request failed".getBytes()));


    // Close all thread and server
    fooOut.writeObject(new DisconnectMessage("foo".getBytes().length, "foo".getBytes()));
    barOut.writeObject(new DisconnectMessage("bar".getBytes().length, "bar".getBytes()));
    pooOut.writeObject(new DisconnectMessage("poo".getBytes().length, "poo".getBytes()));
    serverSocket.close();
    fooClient.close();
  }

  @Test
  public void testMessage() throws IOException, ClassNotFoundException {
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = clientManagement = new ConcurrentHashMap<>();
    ServerSocket serverSocket = new ServerSocket(PORT);

    // Foo
    Socket fooClient = new Socket("localhost", PORT);
    Thread foo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    foo.start();
    ObjectOutputStream fooOut = new ObjectOutputStream(fooClient.getOutputStream());
    ObjectInputStream fooIn = new ObjectInputStream(fooClient.getInputStream());
    fooOut.writeObject(new ConnectMessage("foo".getBytes().length, "foo".getBytes()));
    fooOut.flush();
    ConnectResponse crFoo = (ConnectResponse) fooIn.readObject();

    // Insult
    SendInsultMessage sim = new SendInsultMessage("bar".getBytes().length, "bar".getBytes(),
        "foo".getBytes().length, "foo".getBytes(), "LOL".getBytes().length, "LOL".getBytes());
    // Broadcast
    BroadcastMessage bm = new BroadcastMessage("bar".getBytes().length,
        "bar".getBytes(), "Hello all".getBytes().length, "Hello all".getBytes());
    // Direct
    DirectMessage dm = new DirectMessage("bar".getBytes().length, "bar".getBytes(),
        "foo".getBytes().length, "foo".getBytes(), "Hello bar!".getBytes().length, "Hello bar!".getBytes());
    DirectMessage faldm = new DirectMessage("gigi".getBytes().length, "gigi".getBytes(),
        "bar".getBytes().length, "bar".getBytes(), "Hello bar!".getBytes().length, "Hello bar!".getBytes());

    clientManagement.get("foo").add(sim);
    clientManagement.get("foo").add(bm);
    clientManagement.get("foo").add(dm);

    SendInsultMessage rIm = (SendInsultMessage) fooIn.readObject();
    BroadcastMessage rbm = (BroadcastMessage) fooIn.readObject();
    DirectMessage rdm = (DirectMessage) fooIn.readObject();
    Assert.assertEquals(rIm, sim);
    Assert.assertEquals(rbm, bm);
    Assert.assertEquals(rdm, dm);

    fooOut.writeObject(new DisconnectMessage("foo".getBytes().length, "foo".getBytes()));
    serverSocket.close();
    fooClient.close();
  }

  @Test
  public void test() throws IOException, ClassNotFoundException {
    ConcurrentMap<String, BlockingQueue<Message>> clientManagement = clientManagement = new ConcurrentHashMap<>();
    ServerSocket serverSocket = new ServerSocket(PORT);
    System.out.println("Client is starting");
    Socket fooClient = new Socket("localhost", PORT);
    Thread foo = new Thread(new ClientThread(serverSocket.accept(), clientManagement));
    foo.start();
    System.out.println("Thread is running");
    ObjectOutputStream out = new ObjectOutputStream(fooClient.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(fooClient.getInputStream());
    // First test Connection message
    byte[] username = "foo".getBytes();
    ConnectMessage cm = new ConnectMessage(username.length, username);
    // The message need to get
    byte[] cntMsg = "Successfully Connected!".getBytes();
    ConnectResponse cr = new ConnectResponse(true, cntMsg.length, cntMsg);
    out.writeObject(cm);
    ConnectResponse m = (ConnectResponse) in.readObject();
    System.out.println(cr.toString());
    System.out.println(m.toString());
    Assert.assertTrue(cr.equals(m));
    DisconnectMessage dm = new DisconnectMessage(username.length, username);
    out.writeObject(dm);
    byte[] dscMsg = "See you again!".getBytes();
    DisconnectResponse dr = new DisconnectResponse(true, dscMsg.length, dscMsg);
    DisconnectResponse drMsg = (DisconnectResponse) in.readObject();
    Assert.assertTrue(dr.equals(drMsg));
    serverSocket.close();
    fooClient.close();
  }

}