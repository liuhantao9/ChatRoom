import Protocol.Message;
import Protocol.Requests.BroadcastMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* BroadcastMessage Tester. 
* 
* @author <Taowen Liu>
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class BroadcastMessageTest {
  byte[] username = "name".getBytes();
  byte[] username1 = "Name".getBytes();
  byte[] msg = "msg".getBytes();
  byte[] msg1 = "Msg".getBytes();

  BroadcastMessage message;
  BroadcastMessage message1;
  BroadcastMessage message2;
  BroadcastMessage message3;
  BroadcastMessage message4;

@Before
public void before() throws Exception {
  message = new BroadcastMessage(username.length,username,msg.length, msg);
  message1 = new BroadcastMessage(username.length,username,msg.length, msg);
  message2 = new BroadcastMessage(username.length,username1,msg.length, msg);
  message3 = new BroadcastMessage(username.length,username,msg.length, msg1);
  message4 = new BroadcastMessage(username.length,username,msg.length, msg1);
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getMsgSize() 
* 
*/ 
@Test
public void testGetMsgSize() throws Exception { 
//TODO: Test goes here...
  Assert.assertTrue(message.getMsgSize().equals(msg.length));
} 

/** 
* 
* Method: getMessage() 
* 
*/ 
@Test
public void testGetMessage() throws Exception { 
//TODO: Test goes here...
  Assert.assertTrue(message.getMessage().equals(msg));
} 


/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here...
  final StringBuilder sb = new StringBuilder("BroadcastMessage{");
  sb.append("msgSize=").append(message.getMsgSize());
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
