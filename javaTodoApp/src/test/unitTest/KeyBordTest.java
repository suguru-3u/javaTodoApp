package test.unitTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import form.KeyBord;

class KeyBordTest {
	
	
	@Test
	public void testInputTaskTitle() {
		String taskTitle = "test";
		InputStream in = new ByteArrayInputStream(taskTitle.getBytes());
	    System.setIn(in);
	    
		String inputTaskTitle = KeyBord.inputTaskTitle();
		assertThat("test", is(inputTaskTitle));
	}
	
	@Test
	public void testInputTaskMain() {
		String taskTitle = "testMain";
		InputStream in = new ByteArrayInputStream(taskTitle.getBytes());
	    System.setIn(in);
	    
		String inputTaskTitle = KeyBord.inputTaskMain();
		assertThat("testMain", is(inputTaskTitle));
	}
	
	@Test
	public void tesInputKeyBordString() {
		String input = "test";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		String inputString = KeyBord.inputKeyBordString();
		assertThat("test", is(inputString));
	}
	
	@Test
	public void tesInputKeyBordFlaseString() {
		String input = "testtest";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		String inputString = KeyBord.inputKeyBordString();
		assertThat("test", is(not(inputString)));
	}
	
	@Test
	public void tesInputKeyBordEmptyString() {
		String input = "　";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		String inputString = KeyBord.inputKeyBordString();
		assertThat("　", is(inputString));
		
	}
	
	@Test
	public void tesInputKeyBordExceptionString() {
		String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		String inputString = KeyBord.inputKeyBordString();
		assertThat(1, is(Integer.parseInt(inputString)));	
	}
	
	@Test
	public void testinputKeyBordInt() {
		String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		int inputInt = KeyBord.inputKeyBordInt();
		assertThat(1, is(inputInt));	
	}
	
	@Test
	public void testinputKeyBordunInt() {
		String input = "１";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		int inputInt = KeyBord.inputKeyBordInt();
		assertThat(1, is(inputInt));	
	}
	
	@Test
	public void testinputKeyBordFalseInt() {
		String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		int inputInt = KeyBord.inputKeyBordInt();
		assertThat(2, is(not(inputInt)));	
	}
	
	@Test
	public void testinputKeyBordIntString() {
		String input = "a";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		int inputInt = KeyBord.inputKeyBordInt();
		assertThat(0, is(inputInt));	
	}
	
	@Test
	public void testInputStringCheakEmpty() {
		String inputString = "";
	    
		boolean emailcheak = KeyBord.inputStringCheakEmpty(inputString);
		assertThat(true, is(emailcheak));
	}
	
	@Test
	public void testInputStringCheakNoEmpty() {
		String inputString = "a";
	    
		boolean emailcheak = KeyBord.inputStringCheakEmpty(inputString);
		assertThat(false, is(emailcheak));
	}

}
