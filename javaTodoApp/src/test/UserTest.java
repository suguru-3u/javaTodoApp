package test;

import org.junit.Before;

import model.User;

class UserTest {
	
	public static User user = null;
	
	@Before
	public void userSetUp() {
	    User user = new User(1,"test","test@gmail.com","testtest",0,0);
	}

//	@Test
//	void testUserGetId() {	
//		assertThat(UserTest.user.getId(), is(1));
//	}

}
