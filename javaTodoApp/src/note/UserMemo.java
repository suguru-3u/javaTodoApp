package note;

import java.util.ArrayList;
import java.util.List;

import db.UserDB;
import model.User;

public class UserMemo {
	
	 // ユーザー情報
    private List<User>  users = new ArrayList<User>();
	
	// ユーザー情報をDBに保存する
	public static boolean userCreate(User user) {
		
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		
		boolean cheak = UserDB.createDBUser(name, email, password);
		return cheak;
	}
	
}
