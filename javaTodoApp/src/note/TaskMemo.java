package note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import db.TaskDB;
import form.KeyBord;
import model.Task;

public class TaskMemo implements Memo{

    // Taskを格納するフィールド
    private List<Task> tasks = new ArrayList<Task>();

    // Taskを格納するメソッド
    public void setTasks(Task task){
        this.tasks.add(task);
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

//    登録されているTask数の取得
    public int getTasksNumbers(){
        return tasks.size();
    }

    // Task一覧の表示
    public void tasksShow(){
    	
    	tasks.clear();
    	
    	List<Task> tasks = TaskDB.getDBTasks(this.tasks);
    	
    	if(tasks.isEmpty()){
    		System.out.println("\n現在抱えているTaskはありません\n");
    	}else{
    		System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("Task情報");
    		System.out.println("Task数 ： " + this.getTasksNumbers() + "個");
    		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    		System.out.println("");
    		tasks.stream()
    		.map(i -> "■ " + (tasks.indexOf(i) + 1) + "\nタイトル：　" + i.getTitle() + "\n詳細   ：  " + i.getMain())
    		.forEach(i -> System.out.println(i));
    		System.out.println("");
    	}
    }
    
    // Taskを更新するメソッド
    public void updateTask(int taskNumber ,String title,String main){
    	System.out.print("Taskの内容の変更を開始します");
    	
    	Task task = this.tasks.get(taskNumber);
    	int taskDBNumber = task.getId();        
    	
    	if(title.isEmpty()) {
    		title = task.getTitle();        	
    	}
    	
    	if(main.isEmpty()) {
    		main = task.getMain();        	
    	}	
    	
    	TaskDB.editDBTasks(taskDBNumber,title,main);
    	System.out.print("Taskの内容の変更が終了しました");
    }
    
    // 特定の要素を削除する 
    public void deleteTask(int taskNumber){
        System.out.println("\n削除処理を実行します");
        try{
        	
        	Task task = this.tasks.get(taskNumber);
        	int taskIdNumber = task.getId();
        	TaskDB.deleteDBTasks(taskIdNumber);
        	
            this.tasks.remove(taskNumber);
            System.out.println("削除に成功しました");
        }catch(IndexOutOfBoundsException e){
            System.out.println("削除に失敗しました");
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