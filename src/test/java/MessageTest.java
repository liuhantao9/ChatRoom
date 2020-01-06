import Protocol.Message;
import Protocol.MessageIdentifier;
import Protocol.Requests.ConnectMessage;
import Protocol.Requests.DirectMessage;
import Protocol.Requests.DisconnectMessage;
import Protocol.Requests.QueryUsersMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Message Tester. 
* 
* @author <Taowen Liu>
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class MessageTest {
  byte[] username = "name".getBytes();
  byte[] username1 = "name1".getBytes();
  Message message;
  Message message1;
  Message message2;
  Message message3;

@Before
public void before() throws Exception {
    message = new QueryUsersMessage(username.length,username);
    message1 = new QueryUsersMessage(username.length,username);
    message2 = new QueryUsersMessage(username1.length,username1);
    message3 = new ConnectMessage(username.length,username);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getIdentifier() 
* 
*/ 
@Test
public void testGetIdentifier() throws Exception { 
//TODO: Test goes here...
  Assert.assertTrue(message.getIdentifier().equals(MessageIdentifier.QUERY_CONNECTED_USERS));
}

  /**
   *
   * Method: equals(Object object)
   *
   */
  @Test
  public void testEquals() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(message.hashCode(), message.hashCode());
    Assert.assertTrue(message.equals(message));
    Assert.assertTrue(message.equals(message1));
    Assert.assertFalse(message.equals(message2));
    Assert.assertFalse(message.equals(message3));
    Assert.assertFalse(message.equals(null));
  }

} 
