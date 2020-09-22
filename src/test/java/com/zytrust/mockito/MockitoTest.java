package com.zytrust.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MockitoTest {

	
	// -------------------Verify----------------------
	@Test
	public void demoVerify() {
	
		 //mock creation
		 List<String> mockedList = mock(List.class);
	
		 //using mock object
		 mockedList.add("one");
		 mockedList.clear();
	
		 //verification
		 verify(mockedList).add("one");
		 verify(mockedList).clear();
	 
	}
	
	// -------------------Stubbing----------------------
	@Test
	public void demoStubbing() {
	
		 //You can mock concrete classes, not just interfaces
		 LinkedList<String> mockedList = mock(LinkedList.class);

		 //stubbing
		 when(mockedList.get(0)).thenReturn("first");
		 when(mockedList.get(1)).thenThrow(new RuntimeException());

		 //following prints "first"
		 System.out.println(mockedList.get(0));

		 //following throws runtime exception
		 //System.out.println(mockedList.get(1));

		 //following prints "null" because get(999) was not stubbed
		 System.out.println(mockedList.get(999));

		 //Although it is possible to verify a stubbed invocation, usually it's just redundant
		 verify(mockedList).get(0);
	 
	}
	
	// -------------------Argument matchers----------------------
	@Test
	public void demoArgumentMatchers() {
	
		 List<String> mockedList = mock(List.class);

		//stubbing using built-in anyInt() argument matcher
		 when(mockedList.get(anyInt())).thenReturn("element");

		 //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
		 //when(mockedList.contains(argThat(isValid()))).thenReturn(true);

		 //following prints "element"
		 System.out.println(mockedList.get(999));

		 //you can also verify using an argument matcher
		 verify(mockedList).get(anyInt());


		 //argument matchers can also be written as Java 8 Lambdas
		 System.out.println(mockedList.add("longString"));

		 verify(mockedList).add(argThat(someString -> someString.length() > 5));

	 
	}
	
	
}
