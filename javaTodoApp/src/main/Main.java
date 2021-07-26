package main;

import db.UserDB;
import form.KeyBord;
import model.User;
import note.AdminMemo;
import note.TaskMemo;
import note.UserMemo;

public class Main{
	
	public static boolean appp = true;
	public static boolean appStatus = true;
	
	public static User user = null;
	
	public static void main(String[] args){
	  
    boolean app = true;
    
	System.out.println("ようこそJavaTodoAppへ");
	
    //ログイン、会員登録
	while(appp){
		System.out.println("ログインは「1」、会員登録は「2」、アプリを終了する場合は「3」を入力してください");
		int inputSelectNumber = KeyBord.inputFirstSelectNumber();
		
		switch(inputSelectNumber){
	
		    case 1 :    	
		    	UserMemo.userLogin();
		    	break;
		
		    case 2 :
	    		UserMemo.userCreate();
	    		break;
		      
		    case 3 :
		    	System.exit(0);
		    	break;
		      
		    default:
		    	System.out.println("正しく入力してください");
		    	break;
			}
	}
	
    // Taskメイン機能
    while(app){
    	
    	UserDB.userSerch();
    	TaskMemo taskMemo = new TaskMemo();
    	
    	int userAdminFlg = Main.user.getAdminflg();
    	
    	if(userAdminFlg == 0) {
    		
    		
    		taskMemo.tasksShow();
    		
    		System.out.print("Task関係は「1」、User関係は「２」終了する場合は「５」を入力してください　：");
    		
    		int yoursTask = KeyBord.inputKeyBordInt();
    		
    		switch(yoursTask){
    		
    		// Taskに関する処理
    		case 1 :
    			
    			System.out.print("Taskを登録する場合は「1」、Taskを削除する場合は「２」、Taskを編集する場合は「３」を入力してください　：");
    			int tasknumber = KeyBord.inputKeyBordInt();   
    			
    			if(tasknumber == 1) {
    				taskMemo.memoContentCreate();
    			}else if(tasknumber == 2) {
    				taskMemo.memoContentDelete();
    			}else if(tasknumber == 3) {
    				taskMemo.memoContentEdit();
    			}else {
    				System.out.print("正しく入力してください");
    			}    	
    			break;
    			
    			// Userに関する処理
    		case 2 :
    			
    			System.out.print("User情報の変更は「1」、アプリを退会する場合は「２」を入力してください：");
    			int userknumber = KeyBord.inputKeyBordInt();   
    			
    			if(userknumber == 1) {
    				UserMemo.memoContentEdit();
    			}else if(userknumber == 2) {
    				UserMemo.memoContentDelete();
    			}else {
    				System.out.print("正しく入力してください");
    			}    	
    			break;
    			
    			// Task内容変更処理
    		case 3 :
    			
    			break;
    			
    		case 5 :
    			System.exit(0);
    			break;
    			
    		default:
    			System.out.print("正しく入力してください");
    			break;
    		}
    		
    	}else {
    		AdminMemo adminmemo = new AdminMemo(); 		
    		
    		System.out.print("User関係は「1」、Task関係は「２」終了する場合は「５」を入力してください　：");
    		
    		int yoursTask = KeyBord.inputKeyBordInt();
    		
    		switch(yoursTask){
    		
    		// Taskに関する処理
    		case 1 :
    			
    			adminmemo.usersShow();
    			
    			System.out.print("Userを登録する場合は「1」、Userを削除する場合は「２」、Userを編集する場合は「３」を入力してください　：");
    			int userNumber = KeyBord.inputKeyBordInt();   
    			
    			if(userNumber == 1) {
    				UserMemo.userCreate();
    			}else if(userNumber == 2) {
    				adminmemo.memoContentDelete();
    			}else if(userNumber == 3) {
    				adminmemo.memoContentEdit();
    			}else {
    				System.out.print("正しく入力してください");
    			}    	
    			break;
    			
    		case 2:
    			
    			taskMemo.tasksAllIndex();
    			
    			System.out.print("Taskを登録する場合は「1」、Taskを削除する場合は「２」、Taskを編集する場合は「３」を入力してください　：");
    			int tasknumber = KeyBord.inputKeyBordInt();   
    			
    			if(tasknumber == 1) {
    				taskMemo.memoContentCreate();
    			}else if(tasknumber == 2) {
    				taskMemo.memoContentDelete();
    			}else if(tasknumber == 3) {
    				taskMemo.memoContentEdit();
    			}else {
    				System.out.print("正しく入力してください");
    			}    	
    			break;
    			
    		case 5 :
    			System.exit(0);
    			break;
    			
    		default:
    			System.out.print("正しく入力してください");
    			break;
    		}
    	}
    	
    }
  }
}