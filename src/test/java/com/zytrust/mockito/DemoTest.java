package com.zytrust.mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DemoTest {
	
	// -----------------Mock------------------------
	@Test
	void test() {
	    List<String> mockList = Mockito.mock(ArrayList.class);
	    
	    mockList.add("one");
	    System.out.println("mockList.size(): " + mockList.size());
	    Mockito.verify(mockList).add("one");
	    assertEquals(0, mockList.size());
	 
	    Mockito.when(mockList.size()).thenReturn(100);
	    assertEquals(100, mockList.size());
	}
	
	
	// ------------------------------------------
	@Mock
	List<String> mockedList;
	
	@Test
	public void whenUseMockAnnotation_thenMockIsInjected() {
	    mockedList.add("one");
	    Mockito.verify(mockedList).add("one");
	    Mockito.when(mockedList.size()).thenReturn(5);

	    assertEquals(5, mockedList.size());
	 
	    Mockito.when(mockedList.size()).thenReturn(100);
	    assertEquals(100, mockedList.size());
	}
	
	
	
	// -------------------Spy----------------------
	@Test
	public void whenNotUseSpyAnnotation_thenCorrect() {
	    List<String> spyList = Mockito.spy(new ArrayList<String>());
	    
	    spyList.add("one");
	    spyList.add("two");
	 
	    Mockito.verify(spyList).add("one");
	    Mockito.verify(spyList).add("two");
	 
	    assertEquals(2, spyList.size());
	 
	    //Mockito.doReturn(100).when(spyList).size();
	    Mockito.when(spyList.size()).thenReturn(100);
	    assertEquals(100, spyList.size());
	}
	
	// ------------------------------------------
	@Spy
	List<String> spiedList = new ArrayList<String>();
	 
	@Test
	public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
	    spiedList.add("one");
	    spiedList.add("two");
	 
	    Mockito.verify(spiedList).add("one");
	    Mockito.verify(spiedList).add("two");
	 
	    assertEquals(2, spiedList.size());
	 
	    Mockito.doReturn(100).when(spiedList).size();
	    assertEquals(100, spiedList.size());
	}
	
	// -----------------Captor------------------------

	@Test
	public void whenNotUseCaptorAnnotation_thenCorrect() {
	    List<String> mockList = Mockito.mock(List.class);
	    ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
	 
	    mockList.add("one");
	    Mockito.verify(mockList).add(arg.capture());
	 
	    assertEquals("one", arg.getValue());
	}
	
	// ------------------------------------------

	@Mock
	List<String> mockedList1;
	 
	@Captor 
	ArgumentCaptor<String> argCaptor;
	 
	@Test
	public void whenUseCaptorAnnotation_thenTheSam() {
	    mockedList1.add("one");
	    Mockito.verify(mockedList1).add(argCaptor.capture());
	 
	    assertEquals("one", argCaptor.getValue());
	}
	
	
	// --------------InjectMocks---------------------------
	
	@Mock
	Map<String, String> wordMap;
	 
	@InjectMocks
	MyDictionary dic = new MyDictionary();
	 
	@Test
	public void whenUseInjectMocksAnnotation_thenCorrect() {
	    Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");
	 
	    assertEquals("aMeaning", dic.getMeaning("aWord"));
	}
	

}
