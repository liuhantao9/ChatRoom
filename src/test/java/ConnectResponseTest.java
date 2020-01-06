import Protocol.Responses.ConnectResponse;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ConnectResponse Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class ConnectResponseTest {
  ConnectResponse message;
  ConnectResponse message1;
  ConnectResponse message2;
  ConnectResponse message3;
  byte[] username = "name".getBytes();
  byte[] username1 = "name1".getBytes();


@Before
public void before() throws Exception {
  message = new ConnectResponse(true,username.length,username);
  message1 = new ConnectResponse(true,username.length,username);
  message2 = new ConnectResponse(false,username.length,username);
  message3 = new ConnectResponse(true,username.length,username1);
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
public void testGetSize() throws Exception {
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
  final StringBuilder sb = new StringBuilder("ConnectResponse{");
  sb.append("success=").append(true);
  sb.append(", msgSize=").append(username.length);
  sb.append(", message=").append(Arrays.toString(username));
  sb.append('}');

  Assert.assertTrue(sb.toString().equals(message.toString()));

} 


} 
