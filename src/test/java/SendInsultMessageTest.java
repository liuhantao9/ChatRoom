import Protocol.Requests.DirectMessage;
import Protocol.Requests.SendInsultMessage;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* SendInsultMessage Tester. 
* 
* @author <Taowen Liu>
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class SendInsultMessageTest {
  byte[] username = "name".getBytes();
  byte[] username1 = "Name".getBytes();
  byte[] msg = "msg".getBytes();
  byte[] msg1 = "Msg".getBytes();
  byte[] recipient = "recipient".getBytes();
  byte[] recipient1 = "recipient1".getBytes();

  SendInsultMessage message;
  SendInsultMessage message1;
  SendInsultMessage message2;
  SendInsultMessage message3;
  SendInsultMessage message4;

@Before
public void before() throws Exception {
  message = new SendInsultMessage(username.length,username,recipient.length,recipient,msg.length,msg);
  message1 = new SendInsultMessage(username.length,username,recipient.length,recipient,msg.length,msg);
  message2 = new SendInsultMessage(username.length,username1,recipient.length,recipient,msg.length,msg);
  message3 = new SendInsultMessage(username.length,username,recipient.length,recipient1,msg.length,msg);
  message4 = new SendInsultMessage(username.length,username,recipient.length,recipient,msg.length,msg1);
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
* Method: getRecipientName() 
* 
*/ 
@Test
public void testGetRecipientName() throws Exception { 
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
  final StringBuilder sb = new StringBuilder("SendInsultMessage{");
  sb.append("recipientSize=").append(message.getRecipientSize());
  sb.append(", recipientName=").append(Arrays.toString(message.getRecipientName()));
  sb.append(", msgSize=").append(message.getMsgSize());
  sb.append(", msg=").append(Arrays.toString(msg));
  sb.append('}');
  Assert.assertTrue(sb.toString().equals(message.toString()));
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

/** 
* 
* Method: getOtherUsers() 
* 
*/ 
@Test
public void testGetOtherUsers() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getMsgSize() 
* 
*/ 
@Test
public void testGetMsgSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getMsg() 
* 
*/ 
@Test
public void testGetMsg() throws Exception { 
//TODO: Test goes here... 
} 


} 
