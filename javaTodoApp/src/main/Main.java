package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
	
	public static void main(String[] args) throws IOException{
	  
    boolean app = true;
    

//        Runtime runtime = Runtime.getRuntime();
//        try {
//			runtime.exec("mysql.server start");
//			System.out.println("DBを起動しました");
//		} catch (IOException e) {		
//			e.printStackTrace();
//		}
    
    // 実行する外部プログラムを指定してProcessBuilderインスタンスを生成する
    // Macの場合はこちら  /usr/local/mysql/bin/mysql
    
    String mysqlenv = System.getenv("mysql");
    System.out.println(mysqlenv);
    ProcessBuilder p = new ProcessBuilder("/Users/oohirasuguru/Desktop/java jar/mysql.sh");
//    ProcessBuilder p = new ProcessBuilder( "sh", "musql.server start'");
//    ProcessBuilder p = new ProcessBuilder("mysql.server start");
    
    p.redirectErrorStream(true);

    // プロセスを開始する
    Process process = p.start();

    // 結果を受け取る
    try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.defaultCharset()))) {
        String line;
        while ((line = r.readLine()) != null) {
            System.out.println(line);
        }
    }
    int result = process.exitValue();
    System.out.printf("result=%d%n", result);

    
    
	System.out.println("ようこそJavaTodoAppへ");
	
    //ログイン、会員登録
	while(appp){
//		System.out.println("ログインは「1」、会員登録は「2」、アプリを終了する場合は「3」を入力してください");
		int inputSelectNumber = KeyBord.inputFirstSelectNumber();
		
		switch(inputSelectNumber){
	
		    case 1 :    	
		    	UserMemo.userLogin();
		    	break;
		
		    case 2 :
	    		UserMemo.userCreate();
	    		break;
		      
		    case 3 :
		    	try {
 			        Runtime runtime2 = Runtime.getRuntime();
 			        runtime2.exec("mysql.server stop");
 			        System.out.println("DBを停止しました");
 			    } catch (IOException ex) {
 			    }
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
    			
    		case 5 :
    			 try {
    			        Runtime runtime3 = Runtime.getRuntime();
    			        runtime3.exec("mysql.server stop");
    			        System.out.println("DBを停止しました");
    			    } catch (IOException ex) {
    			    	System.out.println("DBの起動に失敗しました。アプリを再起動してください");
    			    }
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
    			try {
 			        Runtime runtime4 = Runtime.getRuntime();
 			        runtime4.exec("mysql.server stop");
 			        System.out.println("DBを停止しました");
 			    } catch (IOException ex) {
 			    	System.out.println("DBの起動に失敗しました。アプリを再起動してください");
 			    }
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