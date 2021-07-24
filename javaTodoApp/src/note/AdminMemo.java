package note;

import java.util.ArrayList;
import java.util.List;

import db.UserDB;
import form.KeyBord;
import form.UserForm;
import model.User;

public class AdminMemo implements Memo{
	
    // ユーザー情報
    private List<User>  users = new ArrayList<User>();
    
    public List<User> getUsers(){
    	return users;
    }
    
    public int getUserNumbers(){
        return users.size();
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
    		.map(i -> "■ " + (users.indexOf(i) + 1) + "\n名前：　" + i.getName() + "\nメールアドレス：  " + i.getEmail())
    		.forEach(i -> System.out.println(i));
    		System.out.println("");
    	}
    }
    
 // ユーザーがTaskを登録するメソッド
//    public void memoContentCreate(){
//        System.out.println("\n登録したいTaskを入力してください");
//
//        System.out.print("TaskのTitleを入力してください：　");
//        String taskTitle = KeyBord.inputKeyBordString();
//
//        System.out.print("TaskのMainを入力してください ：　");
//        String taskMain = KeyBord.inputKeyBordString();
//
//        TaskDB.createDBTasks(taskTitle,taskMain);
//
//    }

    
    // Taskを更新するメソッド
    public void memoContentEdit(){
    	
    	System.out.print("\n編集したいUserの番号を入力してください：　");
        int taskSerchCheack = this.userSerch();

        if(taskSerchCheack >= 0){
        	
        	User user = this.users.get(taskSerchCheack);
        	System.out.print("\nUser情報を表示します");   	
        	System.out.print(user);
        	
        	System.out.print("\n情報を変更する場合は「y」を入力してください");   	  	
        	String taskJugeAnwser = KeyBord.inputKeyBordString();
        	
            if(taskJugeAnwser.equals("y")){      	
        		
            	System.out.print("\nお名前を入力してください  :");
                String name = KeyBord.inputKeyBordString();

                System.out.print("emailを入力してください   :");
                String email = KeyBord.inputKeyBordString();

                System.out.print("Passwordを入力してください:");
                String password = KeyBord.inputKeyBordString();
                
                UserForm userform = new UserForm(name,email,password,user);
        		
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
    }
    
    // 特定の要素を削除する 
    public void memoContentDelete(){
    	
//    	 System.out.print("\n削除したいTaskの番号を入力してください：　");
//         int taskSerchCheack = this.taskSerch();
//
//         if(taskSerchCheack >= 0){
//        	 System.out.println("\n削除処理を実行します");
//        	 try{
//        		 
//        		 Task task = this.tasks.get(taskSerchCheack);
//        		 int taskIdNumber = task.getId();
//        		 TaskDB.deleteDBTasks(taskIdNumber);
//        		 
//        		 this.tasks.remove(taskSerchCheack);
//        		 System.out.println("削除に成功しました");
//        		 
//        	 }catch(IndexOutOfBoundsException e){
//        		 System.out.println("削除に失敗しました");
//        	 }             
//         }
    } 

    // 特定のTaskの検索メソッド
    public int userSerch(){
    	
        try {
        	int userNumber = KeyBord.inputKeyBordInt();
            
        	userNumber -= 1 ;
            
            User user = this.users.get(userNumber);
            
            System.out.println(user.toString());
            System.out.print("こちらのTaskでお間違い無いでしょうか？ 間違いなければ「y」を入力してください：　");

            String taskJugeAnwser = KeyBord.inputKeyBordString();

            if(taskJugeAnwser.equals("y")){
                return userNumber;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());        
        }
        
        return -1 ;     
    }


//    // クラスの内容表示メソッド
//    public String toString(){
//        String tasksString = this.tasks.stream()
//        .map(i -> "　タイトル：　" + i.getTitle() + "　詳細：　" + i.getMain())
//        .collect(Collectors.joining(", "));
//
//        return tasksString;
//    }
}
