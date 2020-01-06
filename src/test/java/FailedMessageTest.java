import Protocol.Responses.DisconnectResponse;
import Protocol.Responses.FailedMessage;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* FailedMessage Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class FailedMessageTest {
  FailedMessage message;
  FailedMessage message1;
  FailedMessage message2;
  FailedMessage message3;
  byte[] username = "name".getBytes();
  byte[] username1 = "name1".getBytes();

@Before
public void before() throws Exception {
  message = new FailedMessage(username.length,username);
  message1 = new FailedMessage(username.length,username);
  message2 = new FailedMessage(username1.length,username);
  message3 = new FailedMessage(username.length,username1);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getDescription() 
* 
*/ 
@Test
public void testGetDescription() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getResponseSize() 
* 
*/ 
@Test
public void testGetResponseSize() throws Exception { 
//TODO: Test goes here... 
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
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here...
  final StringBuilder sb = new StringBuilder("FailedMessage{");
  sb.append("size=").append(message.getResponseSize());
  sb.append(", description=").append(Arrays.toString(message.getDescription()));
  sb.append('}');
  Assert.assertTrue(message.toString().equals(sb.toString()));
} 


} 
