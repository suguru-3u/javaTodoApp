package note;

import java.util.ArrayList;
import java.util.List;

import db.UserDB;
import form.KeyBord;
import form.UserForm;
import model.User;


public class UserMemo {
	
	 // ユーザー情報
    private List<User>  users = new ArrayList<User>();
	
    // ユーザー情報ログイン情報
    public static void userLogin() {
    	
    	System.out.println("ユーザー情報登録時のメールアドレスを入力してください");
        String userEmail = KeyBord.inputKeyBordString();
        
        System.out.println("ユーザー情報登録時のパスワードを入力してください");
        String userPassword = KeyBord.inputKeyBordString();
        
        if(userEmail.isEmpty() && userPassword.isEmpty()) {
        	 System.out.println("文字を正しく入力してください");
        }else {
        	UserDB.userLogin(userEmail, userPassword);        	
        }
        
    }
    
	// ユーザー情報をDBに保存する
	public static void userCreate(User user) {
		
		UserForm userform = new UserForm();
		
		boolean adminFlg = false;
		boolean deleteFlg = false;
		
    	if(userform.getCheakCreate()) {
    		user = new User(userform.getName(),userform.getEmail(),userform.getPassword(),adminFlg,deleteFlg);

    		String name = user.getName();
    		String email = user.getEmail();
    		String password = user.getPassword();
    		
    		UserDB.createDBUser(name, email, password);
    	}else {
    		System.out.println("正しく入力してください"); 
    	}
	}
}
