package test.com.twm.pt.robolectricsampletests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.example.robolectricsample.LoginActivity;
import com.example.robolectricsample.component.MakeCallRule;
import com.example.robolectricsample.utility.L;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
	
	LoginActivity loginActivity;
	
	
	@Before
	public void setup() {
		loginActivity = Robolectric.buildActivity(LoginActivity.class).create().start().resume().get();
	}
	
	@Test 
	public void testMockito(){
		MakeCallRule makeCallRule = new MakeCallRule();
//		assertTrue(true);
		assertTrue(makeCallRule.checkDialNumberAvailable("+886956000111"));
//		assertTrue(makeCallRule.checkDialNumberAvailable("+88675556666"));
//		assertTrue(makeCallRule.checkDialNumberAvailable("886956000111"));
		
//		LinkedList mockedList = mock(LinkedList.class);
//		String moduleString = "first";
//		when(mockedList.get(0)).thenReturn(moduleString);
//		when(mockedList.get(1)).thenThrow(new RuntimeException());
//		L.d("mockedList.get(0) = " + mockedList.get(0));
//		if(mockedList.get(0).equals(moduleString)){
//			assertTrue(false);
//		}else{
//			assertTrue(false);
//		}
	}

	@Test
	public void shouldHaveHappySmiles() throws Exception {
		ProtocolVersion httpProtocolVersion = new ProtocolVersion("HTTP", 1, 1);
		HttpResponse successResponse = 
		        new BasicHttpResponse(httpProtocolVersion, 200, "OK");
		Robolectric.addHttpResponseRule("http://kkbox.open-168.com/mobileapi/Login.aspx", successResponse);
		loginActivity.login("brent","123");
		assertTrue(loginActivity.result.length()==0);
	}
	
	
}
