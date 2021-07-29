package form;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

import db.AccessKey;


public class KeyBord{
	
	public static int inputFirstSelectNumber() {
		System.out.println("ログインは「1」、会員登録は「2」、アプリを終了する場合は「3」を入力してください");
		int inputSelectNumber = KeyBord.inputKeyBordInt();
		return inputSelectNumber;
	}
	
	public static String inputCheakY(String y) {
		if(y.equals("userEdit")) {
			System.out.print("\n情報を変更する場合は「y」を入力してください");   	  				
		}else if(y.equals("userDelete")){
			System.out.print("\n退会のご案内です。\n退会されますとデータが消えてしまい復元することができな区なりますが、よろしいですか？");
			System.out.print("\n退会する場合は「y」を入力してください");   
		}else if(y.equals("userFinalDelete")){
			System.out.print("\n本当に退会する場合は「y」を入力してください");   	  
		}
		String inputString = KeyBord.inputKeyBordString();		
		return inputString;
	}
	
	public static String inputUserName() {
		System.out.print("\nお名前を入力してください  :");
	    String userName = KeyBord.inputKeyBordString();  	
		return userName;
	}
	
	public static String inputUserEmail() {
		System.out.print("emailを入力してください   :");
	    String userEmail = KeyBord.inputKeyBordString();
		return userEmail;
	}
	
	public static String inputUserPassword() {
		System.out.print("Passwordを入力してください:");
	    String userPassword = KeyBord.inputKeyBordString();
		return userPassword;
	}
	
	public static boolean inputCheackEmpty(String inputString) {
		if(inputString.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static String inputTaskTitle() {
		System.out.println("TaskのTitleを入力してください：　");
	    String inputTaskTitle = KeyBord.inputKeyBordString();
	    return inputTaskTitle;
	}
	
	public static String inputTaskMain() {
		System.out.println("TaskのMainを入力してください：　");
	    String inputTaskMain = KeyBord.inputKeyBordString();
	    return inputTaskMain;
	}
	
	public static boolean inputStringCheakEmpty(String inputString){
		if(inputString.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

    public static String inputKeyBordString(){
    	
        try {
            // キーボード入力を受け付ける
            Scanner keyString = new Scanner(System.in);
            String taskString = keyString.nextLine();

            return taskString;

        }catch (Exception e) {

            System.out.println(e.getMessage());

            String errorMessage = "エラーが発生しました";

            return errorMessage;

        }
    }

    public static int inputKeyBordInt(){

        Scanner yourselect = null;
        int yoursTask = 0 ;

        try {
            // キーボード入力を受け付ける
            yourselect = new Scanner(System.in);
            yoursTask = yourselect.nextInt();
            return yoursTask;

        } catch (InputMismatchException e) {
            System.out.println("入力エラーを検知しました");
            return yoursTask;
        }

    }
    
    public static String userPasswordHash(String userPassword) {
    	
//    	userPasswordを暗号化
    	MessageDigest digest = null;
    	try {
    		digest = MessageDigest.getInstance(AccessKey.getHash);
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	byte[] userPasswordHash = digest.digest(userPassword.getBytes());
    	
    	// 文字列に変換
    	String userPasswordHashString = new String(userPasswordHash);
    	
    	return userPasswordHashString;
	}
    
}
