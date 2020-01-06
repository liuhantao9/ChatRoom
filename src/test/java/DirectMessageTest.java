import Protocol.Requests.DirectMessage;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* DirectMessage Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class DirectMessageTest {
  byte[] username = "name".getBytes();
  byte[] username1 = "Name".getBytes();
  byte[] msg = "msg".getBytes();
  byte[] msg1 = "Msg".getBytes();
  byte[] recipient = "recipient".getBytes();
  byte[] recipient1 = "recipient1".getBytes();

  DirectMessage message;
  DirectMessage message1;
  DirectMessage message2;
  DirectMessage message3;
  DirectMessage message4;



@Before
public void before() throws Exception {
  message = new DirectMessage(username.length,username,recipient.length,recipient,msg.length,msg);
  message1 = new DirectMessage(username.length,username,recipient.length,recipient,msg.length,msg);
  message2 = new DirectMessage(username.length,username1,recipient.length,recipient,msg.length,msg);
  message3 = new DirectMessage(username.length,username,recipient.length,recipient1,msg.length,msg);
  message4 = new DirectMessage(username.length,username,recipient.length,recipient,msg.length,msg1);
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getRecipientSize() 
* 
*/ 
@Test
public void testGetRecipientSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRecipientUsername() 
* 
*/ 
@Test
public void testGetRecipientUsername() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here...
  final StringBuilder sb = new StringBuilder("DirectMessage{");
  sb.append("recipientSize=").append(message.getRecipientSize());
  sb.append(", recipientUsername=").append(Arrays.toString(message.getRecipientUsername()));
  sb.append(", messageSize=").append(message.getMessageSize());
  sb.append(", message=").append(Arrays.toString(message.getMessage()));
  sb.append('}');
  Assert.assertTrue(message.toString().equals(sb.toString()));
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
  Assert.assertFalse(message.equals(message4));
  Assert.assertFalse(message.equals(null));
} 

/** 
* 
* Method: hashCode() 
* 
*/ 
@Test
public void testHashCode() throws Exception { 
//TODO: Test goes here... 
} 


} 
