package test.unitTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.User;

class UserTest {
	
	public static User user = null;
	
	@Before
	public void userSetUp() {
	    user = new User(1,"test","test@gmail.com","testtest",0,0);
	}

	@Test
    public void testConstractUserNoDeletFlg(){
		User constractUser = new User(1 ,"test", "test@gmail.com","test",0,0);
		assertEquals(1, constractUser.getId());
		assertEquals("test", constractUser.getName());
		assertEquals("test@gmail.com", constractUser.getEmail());
		assertEquals("test", constractUser.getPassword());
		assertEquals(0, constractUser.getAdminflg());
		assertEquals("\n利用中", constractUser.getDeleteflg());
    }
	
	@Test
    public void testConstractUserDeletFlg(){
		User user = new User(1 ,"test", "test@gmail.com","test",0,1);
		assertEquals("\n退会済み", user.getDeleteflg());
    }
	
	@Test
	public void testSetUserName() {
		userSetUp();
		user.setName("test2");
		String name = user.getName();
		assertThat("test2", is(name));
	}
	
	
	@Test
	public void testUserToString(){
		userSetUp();
		String userToString = "名前：　" + "test" + "\nemail:　" + "test@gmail.com" + "\npasword：　" + "testtest";
		assertEquals(userToString,user.toString());
	}
    

}
