import Protocol.Responses.QueryResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;


public class QueryResponseTest {

  byte[] username = "name".getBytes();
  byte[] username1 = "test".getBytes();
  byte[] username2 = "test".getBytes();
  byte[] u1 = "test1".getBytes();
  byte[] u2 = "test2".getBytes();
  byte[] u3 = "test1".getBytes();
  byte[] u4 = "test2".getBytes();

  Pair<Integer, byte[]> pair = new Pair<>(username.length, username);
  Pair<Integer, byte[]> pair1 = new Pair<>(username1.length, username1);
  Pair<Integer, byte[]> pair2 = new Pair<>(username2.length, username2);
  Pair<Integer, byte[]> p1 = new Pair(u1.length, u1);
  Pair<Integer, byte[]> p2 = new Pair(u2.length, u2);
  Pair<Integer, byte[]> p3 = new Pair(u3.length, u3);
  Pair<Integer, byte[]> p4 = new Pair(u4.length, u4);

  List list = new ArrayList<Pair>();
  List list1 = new ArrayList<Pair>();
  List list2 = new ArrayList<Pair>();
  List<Pair<Integer, byte[]>> l1;
  List<Pair<Integer, byte[]>> l2;
  QueryResponse message;
  QueryResponse message1;
  QueryResponse message2;
  QueryResponse message3;
  QueryResponse message4;
  QueryResponse message5;

  @Before
  public void before() throws Exception {
    list.add(pair);
    list1.add(pair1);
    list2.add(pair2);
    message = new QueryResponse(list.size(), list);
    message1 = new QueryResponse(list.size(), list);
    message2 = new QueryResponse(list1.size(), list1);
    message3 = new QueryResponse(list2.size(), list2);

    l1 = new ArrayList<>(Arrays.asList(p1, p2));
    l2 = new ArrayList<>(Arrays.asList(p4, p3));
    message4 = new QueryResponse(l1.size(), l1);
    message5 = new QueryResponse(l2.size(), l2);
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: getUsers()
   */
  @Test
  public void testGetUsers() throws Exception {
//TODO: Test goes here...
  }

  /**
   * Method: getResponseSize()
   */
  @Test
  public void testGetResponseSize() throws Exception {
//TODO: Test goes here...
  }

  /**
   * Method: equals(Object object)
   */
  @Test
  public void testEquals() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(message.hashCode(), message.hashCode());
    Assert.assertTrue(message.equals(message));
    Assert.assertTrue(message.equals(message1));
    Assert.assertFalse(message.equals(message2));
    Assert.assertTrue(message3.equals(message2));
    Assert.assertFalse(message.equals(null));
    Assert.assertTrue(message4.equals(message5));
  }

}

