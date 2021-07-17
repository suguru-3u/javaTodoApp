package main;

import form.KeyBord;
import model.User;
import note.TaskMemo;

public class Main{
  public static void main(String[] args){
	
  // インスタンス変数生成
    TaskMemo taskMemo = new TaskMemo();
    User user = null;
    
    boolean app = true;
	boolean appp = true;
	System.out.println("ようこそJavaTodoAppへ");
	
    //ログイン、会員登録
	while(appp){
		System.out.println("ログインは「1」、会員登録は「2」を入力してください");
		int a = KeyBord.inputKeyBordInt();
		
		switch(a){
	
	    // Task登録処理
	    case 1 :
	//      user.memoContentCreate();
	    	appp = false;
	      break;
	
	    case 2 :
	    // Task削除処理
	    	appp = false;
	    	// ユーザー情報入力
	        user = new User();
	      break;
	      
	    default:
	      System.out.print("正しく入力してください");
	      break;
		}
	}
    

    // Taskメイン機能
    while(app){
    	
      taskMemo.tasksShow();

      System.out.print("Taskを入力する場合は「1」、Taskを削除する場合は「２」、Taskを編集する場合は「３」、終了する場合は「５」を入力してください　：");

      int yoursTask = KeyBord.inputKeyBordInt();

      switch(yoursTask){

        // Task登録処理
        case 1 :
          user.memoContentCreate();
          break;

        // Task削除処理
        case 2 :
          user.memoContentDelete();
          break;

        // Task内容変更処理
        case 3 :
          user.memoContentEdit();
          break;

        case 5 :
          app = false;
          break;

        default:
          System.out.print("正しく入力してください");
          break;
      }
    }
  }
}