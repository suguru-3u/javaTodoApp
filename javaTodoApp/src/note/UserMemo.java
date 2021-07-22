package note;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import db.AccessKey;
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
        	// Hash化
            MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance(AccessKey.getHash());
			} catch (NoSuchAlgorithmException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
            byte[] passwordHash = digest.digest(userPassword.getBytes());
       
            // 文字列に変換
            String passwordHashString = new String(passwordHash);
        	UserDB.userLogin(userEmail, passwordHashString);   
        	
        }
    }
    
	// ユーザー情報をDBに保存する
	public static void userCreate() {
		
		UserForm userform = new UserForm();
		
		boolean adminFlg = false;
		boolean deleteFlg = false;
		
    	if(userform.getCheakCreate()) {

    		String name = userform.getName();
    		String email = userform.getEmail();
    		String password = userform.getPassword();
    		
    		UserDB.createDBUser(name, email, password);
    	}else {
    		System.out.println("正しく入力してください"); 
    	}
	}
}
