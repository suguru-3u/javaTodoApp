package note;

import java.util.ArrayList;
import java.util.List;

import db.UserDB;
import form.UserForm;
import model.User;


public class UserMemo {
	
	 // ユーザー情報
    private List<User>  users = new ArrayList<User>();
	
	// ユーザー情報をDBに保存する
	public static void userCreate(User user) {
		
		UserForm userform = new UserForm();
		
    	if(userform.getCheakCreate()) {
    		user = new User(userform.getName(),userform.getEmail(),userform.getPassword());

    		String name = user.getName();
    		String email = user.getEmail();
    		String password = user.getPassword();
    		
    		UserDB.createDBUser(name, email, password);
    	}else {
    		System.out.println("正しく入力してください"); 
    	}
	}
	
}
