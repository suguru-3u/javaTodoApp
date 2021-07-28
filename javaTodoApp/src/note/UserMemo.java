package note;

import db.UserDB;
import form.KeyBord;
import form.UserForm;
import main.Main;


public class UserMemo {
	
    // ユーザー情報ログイン情報
    public static void userLogin() {
    	
    	String userEmail = KeyBord.inputUserEmail();
        String userPassword = KeyBord.inputUserPassword();
        
        boolean userEmailCheakEmpty = KeyBord.inputCheackEmpty(userEmail);
        boolean userPasswordCheakEmpty = KeyBord.inputCheackEmpty(userPassword);
        
        if(userEmailCheakEmpty && userPasswordCheakEmpty) {
        	 System.out.println("文字を正しく入力し	てください");
        }else {
            String passwordHashString = null;
			try {
				passwordHashString = KeyBord.userPasswordHash(userPassword);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        	UserDB.userLogin(userEmail, passwordHashString);          	
        }
    }
    
	// ユーザー情報をDBに保存する
	public static void userCreate() {
		
		UserForm userform = new UserForm();
		
    	if(userform.getCheakCreate()) {

    		String userFormName = userform.getName();
    		String userFormEmail = userform.getEmail();
    		String userFormPassword = userform.getPassword();
    		
    		UserDB.createDBUser(userFormName, userFormEmail, userFormPassword);
    	}else {
    		System.out.println("正しく入力してください"); 
    	}
	}
	
	 // User情報を更新するメソッド
    public static void memoContentEdit(){
    	
    	Main.user.toString();
    	
    	String inputString = KeyBord.inputCheakY("userEdit");
    	
        if(inputString.equals("y")){	       	
    		     	
            String userName = KeyBord.inputUserName();
            String userEmail = KeyBord.inputUserEmail();
            String userPassword = KeyBord.inputUserPassword();
            
            UserForm userform = new UserForm(userName,userEmail,userPassword);
    		
        	if(userform.getCheakCreate()) {

        		String userFormName = userform.getName();
        		String userFormEmail = userform.getEmail();
        		String userFormPassword = userform.getPassword();
        		
        		UserDB.editDBUser(userFormName, userFormEmail, userFormPassword);
        	}else {
        		System.out.println("正しく入力してください"); 
        	}
        	
        }
    }
    
    //	ユーザー退会処理
    public static void memoContentDelete(){
    		  	
     	 String taskJugeAnwser = KeyBord.inputCheakY("userDelete");
     	
         if(taskJugeAnwser.equals("y")){	
        	 	
         	Main.user.toString();
         	       		
        	String deleteAnwser = KeyBord.inputCheakY("userFinalDelete");
        	
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
}
