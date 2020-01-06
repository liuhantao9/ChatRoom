import Protocol.Responses.ConnectResponse;
import Protocol.Responses.DisconnectResponse;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* DisconnectResponse Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class DisconnectResponseTest {
  DisconnectResponse message;
  DisconnectResponse message1;
  DisconnectResponse message2;
  DisconnectResponse message3;
  byte[] username = "name".getBytes();
  byte[] username1 = "name1".getBytes();


  @Before
public void before() throws Exception {
    message = new DisconnectResponse(true,username.length,username);
    message1 = new DisconnectResponse(true,username.length,username);
    message2 = new DisconnectResponse(false,username.length,username);
    message3 = new DisconnectResponse(true,username.length,username1);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isSuccess() 
* 
*/ 
@Test
public void testIsSuccess() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getMessage() 
* 
*/ 
@Test
public void testGetMessage() throws Exception { 
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
  final StringBuilder sb = new StringBuilder("DisconnectResponse{");
  sb.append("success=").append(message.isSuccess());
  sb.append(", msgSize=").append(message.getResponseSize());
  sb.append(", message=").append(Arrays.toString(message.getMessage()));
  sb.append('}');
  Assert.assertTrue(sb.toString().equals(message.toString()));
} 


} 
