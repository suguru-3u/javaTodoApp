package note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import db.TaskDB;
import form.KeyBord;
import form.TaskForm;
import model.Task;

public class TaskMemo implements Memo{

    // Taskを格納するフィールド
    private List<Task> tasks = new ArrayList<Task>();

    // 登録されているTask数の取得
    public int getTasksNumbers(){
        return tasks.size();
    }
    
 // ユーザーがTaskを登録するメソッド
    public void memoContentCreate(){
        System.out.println("\n登録したいTaskを入力してください");

        String taskTitle = KeyBord.inputTaskTitle();
        String taskMain = KeyBord.inputTaskMain();
        
        boolean inputCheakEmptyTitle = KeyBord.inputStringCheakEmpty(taskTitle); 
        boolean inputCheakEmptyMain = KeyBord.inputStringCheakEmpty(taskMain); 
        
        if(inputCheakEmptyTitle && inputCheakEmptyMain) {
        	 System.out.println("\n空白では入力しないでください");
        }else {
        	TaskForm taskForm = new TaskForm(taskTitle,taskMain); 
        	TaskDB.createDBTasks(taskForm);        	
        }
    }

    // Task一覧の表示
    public void tasksShow(){
    	
    	this.tasks.clear();
    	
    	TaskDB.getDBTasks(this.tasks);
    	
    	if(tasks.isEmpty()){
    		System.out.println("\n現在抱えているTaskはありません\n");
    	}else{
    		System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("Task情報");
    		System.out.println("Task数 ： " + this.getTasksNumbers() + "個");
    		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("");
    		this.tasks.stream()
    		.map(i -> "■ " + (tasks.indexOf(i) + 1)   + "\nタイトル：　" + i.getTitle() + "\n詳細   ：  " + i.getMain())
    		.forEach(i -> System.out.println(i));
    		System.out.println("");
    	}
    }
    
 // Task一覧の表示
    public void tasksAllIndex(){
    	
    	this.tasks.clear();
    	
    	TaskDB.getDBAllTasks(this.tasks);
    	
    	if(tasks.isEmpty()){
    		System.out.println("\n現在抱えているTaskはありません\n");
    	}else{
    		System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("Task情報");
    		System.out.println("Task数 ： " + this.getTasksNumbers() + "個");
    		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("");
    		this.tasks.stream()
    		.map(i -> "■ " + (tasks.indexOf(i) + 1)  + "\nタイトル：　" + i.getTitle() + "\n詳細   ：  " + i.getMain() 
    		+ "\n登録ユーザーID　：" + i.getUserId())
    		.forEach(i -> System.out.println(i));
    		System.out.println("");
    	}
    }
    
    // Taskを更新するメソッド
    public void memoContentEdit(){
    	
    	System.out.print("\n編集したいTaskの番号を入力してください：　");
        int taskSerchCheack = this.taskSerch();

        if(taskSerchCheack >= 0){

            System.out.print("TaskのTitleを入力してください：　");
            String taskTitleChange = KeyBord.inputKeyBordString();

            System.out.print("TaskのMainを入力してください ：　");
            String taskMainChange = KeyBord.inputKeyBordString();
            
            System.out.print("Taskの内容の変更を開始します");
            
            Task task = this.tasks.get(taskSerchCheack);
            int taskDBNumber = task.getId();        
            
            if(taskTitleChange.isEmpty()) {
            	taskTitleChange = task.getTitle();        	
            }
            
            if(taskMainChange.isEmpty()) {
            	taskMainChange = task.getMain();        	
            }	
            
            TaskDB.editDBTasks(taskDBNumber,taskTitleChange,taskMainChange);
            System.out.print("Taskの内容の変更が終了しました");
        }
    	
    }
    
    // 特定の要素を削除する 
    public void memoContentDelete(){
    	
    	 System.out.print("\n削除したいTaskの番号を入力してください：　");
         int taskSerchCheack = this.taskSerch();

         if(taskSerchCheack >= 0){
        	 System.out.println("\n削除処理を実行します");
        	 try{
        		 
        		 Task task = this.tasks.get(taskSerchCheack);
        		 int taskIdNumber = task.getId();
        		 TaskDB.deleteDBTasks(taskIdNumber);
        		 
        		 this.tasks.remove(taskSerchCheack);
        		 System.out.println("削除に成功しました");
        		 
        	 }catch(IndexOutOfBoundsException e){
        		 System.out.println("削除に失敗しました");
        	 }             
         }
    } 

    // 特定のTaskの検索メソッド
    public int taskSerch(){
    	
        try {
        	int taskNumber = KeyBord.inputKeyBordInt();
            
            taskNumber -= 1 ;
            
            Task task = this.tasks.get(taskNumber);
            
            System.out.println(task.toString());
            System.out.print("こちらのTaskでお間違い無いでしょうか？ 間違いなければ「y」を入力してください：　");

            String taskJugeAnwser = KeyBord.inputKeyBordString();

            if(taskJugeAnwser.equals("y")){
                return taskNumber;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());        
        }
        
        return -1 ;     
    }


    // クラスの内容表示メソッド
    public String toString(){
        String tasksString = this.tasks.stream()
        .map(i -> "　タイトル：　" + i.getTitle() + "　詳細：　" + i.getMain())
        .collect(Collectors.joining(", "));

        return tasksString;
    }

}