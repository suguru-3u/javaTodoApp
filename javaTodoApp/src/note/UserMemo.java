package note;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import db.AccessKey;
import db.UserDB;
import form.KeyBord;
import form.UserForm;
import main.Main;
import model.User;


public class UserMemo {
	
	 // ユーザー情報
    private List<User>  users = new ArrayList<User>();
    
    public int getUserNumbers(){
        return users.size();
    }
	
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
	
	 // User情報を更新するメソッド
    public static void memoContentEdit(){
    	
    	System.out.print("\nUser情報を表示します");   	
    	System.out.print(Main.user);
    	
    	System.out.print("\n情報を変更する場合は「y」を入力してください");   	  	
    	String taskJugeAnwser = KeyBord.inputKeyBordString();
    	
        if(taskJugeAnwser.equals("y")){	       	
    		
        	System.out.print("\nお名前を入力してください  :");
            String name = KeyBord.inputKeyBordString();

            System.out.print("emailを入力してください   :");
            String email = KeyBord.inputKeyBordString();

            System.out.print("Passwordを入力してください:");
            String password = KeyBord.inputKeyBordString();
            
            UserForm userform = new UserForm(name,email,password);
    		
        	if(userform.getCheakCreate()) {

        		String nameForm = userform.getName();
        		String emailForm = userform.getEmail();
        		String passwordForm = userform.getPassword();
        		
        		UserDB.editDBUser(nameForm, emailForm, passwordForm);
        	}else {
        		System.out.println("正しく入力してください"); 
        	}
        	
        }
    }
    
//     特定の要素を削除する 
    public static void memoContentDelete(){
    	
    	 System.out.print("\n退会のご案内です。\n退会されますとデータが消えてしまい復元することができな区なりますが、よろしいですか？");
    	 System.out.print("\n退会する場合は「y」を入力してください");   	  	
     	 String taskJugeAnwser = KeyBord.inputKeyBordString();
     	
         if(taskJugeAnwser.equals("y")){	
        	System.out.print("\nUser情報を表示します");   	
         	System.out.print(Main.user);
         	
         	System.out.print("\n本当に退会する場合は「y」を入力してください");   	  	
        	String deleteAnwser = KeyBord.inputKeyBordString();
        	
            if(deleteAnwser.equals("y")){
            	int userID = Main.user.getId();
            	UserDB.deleteDBUser(userID);
            	System.exit(0);
            }else{
            	System.out.print("\n退会処理を中止します。");  
            }
         }else {
        	 System.out.print("\n退会処理を中止します。");   	  	
         }
    } 
    
    public void usersShow(){
    	
    	this.users.clear();
    	
    	UserDB.getDBUser(this.users);
    	
    	if(users.isEmpty()){
    		System.out.println("\n現在登録しているユーザーはいません\n");
    	}else{
    		System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("user情報");
    		System.out.println("user数 ： " + this.getUserNumbers() + "人");
    		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("");
    		this.users.stream()
    		.map(i -> "■ " + i.getId() + "\n名前：　" + i.getName() + "\nメールアドレス：  " + i.getEmail())
    		.forEach(i -> System.out.println(i));
    		System.out.println("");
    	}
    }
}
