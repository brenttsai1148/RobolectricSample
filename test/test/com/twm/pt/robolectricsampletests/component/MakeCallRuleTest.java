package test.com.twm.pt.robolectricsampletests.component;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import java.util.LinkedList;
import static org.hamcrest.core.IsEqual.*;//加這行
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.example.robolectricsample.component.MakeCallRule;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class MakeCallRuleTest {
	
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void shouldUseAvailableDialNumber() throws Exception {
		assertTrue(MakeCallRule.checkDialNumberAvailable("+886956000111"));//行動
		assertTrue(MakeCallRule.checkDialNumberAvailable("+88675556666"));//市話
		assertTrue(MakeCallRule.checkDialNumberAvailable("+886956000111"));//錯誤
	}
	
	@Test
	public void testMockito() throws Exception{
		
		//assertEquals(expected, actual);
		//assertThat(actual, is(equalTo(expected)));
		//assertFalse(expected.equals(actual));
		//assertThat(actual, is(not(equalTo(expected))));
		//assertThat(actual, is(expected));
		//assertThat(actual, is(not(expected)));
		
		
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenReturn("first2");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		//following prints "first"
//		System.out.println(mockedList.get(0));
//		assertThat((String)mockedList.get(0), equalTo("first"));

		//following throws runtime exception
//		System.out.println(mockedList.get(1));

		//following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));
		System.out.println("tttttttest");
		//Although it is possible to verify a stubbed invocation, usually it's just redundant
		//If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
		//If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
		verify(mockedList).get(999);

	}
}
