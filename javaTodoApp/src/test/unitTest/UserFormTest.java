package test.unitTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import form.UserForm;

class UserFormTest {
	
	public static UserForm userFormTest = null;
	
	@Before
	public UserForm userSetUp() {
		return userFormTest = new UserForm("test","test@gmail.com","testtest");
	}
	
	@Test
	void testEmailCheak() {
		UserForm userForm = userSetUp();
		boolean emailcheak = userFormTest.emailCheak(userForm.getEmail());
		assertThat(true, is(emailcheak));
	}
	
//	public boolean emailCheak(String email) {
//		
//		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
//		
//		if(email.matches(mailFormat)){
//			return true;		
//		}
//		return false;
//	}

}
