package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import db.TaskDB;

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

    public int getTasksNumbers(){
        return tasks.size();
    }

    // 特定の要素を削除する 
    public void deleteTask(int taskNumber){
        System.out.println("");
        System.out.println("削除処理を実行します");
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

    // Taskを変換するメソッド
    public void changeTask(int taskNumber ,String title,String main){
        System.out.print("Taskの内容の変更を開始します");
        
        if(title.isEmpty()) {
        	Task task = this.tasks.get(taskNumber);
        	title = task.getTitle();        	
        }
        
        if(main.isEmpty()) {
        	Task task = this.tasks.get(taskNumber);
        	main = task.getTitle();        	
        }
        
        Task task = this.tasks.get(taskNumber);
    	int taskDBNumber = task.getId();        	
           
        TaskDB.editDBTasks(taskDBNumber,title,main);
        System.out.print("Taskの内容の変更が終了しました");
    }

    // Task一覧の表示
    public void tasksShow(){
    	
    	tasks.clear();
    	
    	List<Task> tasks = TaskDB.getDBTasks(this.tasks);
    	
        if(tasks.isEmpty()){
            System.out.println("\n現在抱えているTaskはありません\n");
        }else{
            System.out.println("");
            tasks.stream()
            .map(i -> "■ " + (tasks.indexOf(i) + 1) + "\nタイトル：　" + i.getTitle() + "\n詳細    ：  " + i.getMain())
            .forEach(i -> System.out.println(i));
            System.out.println("");
        }
    }


    // 特定のTaskの検索メソッド
    public int getTask(){

        int taskNumber;
        try {
            // キーボード入力を受け付ける
            Scanner title = new Scanner(System.in);
            taskNumber = title.nextInt();
            taskNumber -= 1 ;
            Task task = this.tasks.get(taskNumber);
            System.out.println(task.toString());
            System.out.print("こちらのTaskでお間違い無いでしょうか？ 間違いなければ「y」を入力してください：　");

            Scanner taskJuge = new Scanner(System.in);
            String taskJugeAnwser = taskJuge.nextLine();

            if(taskJugeAnwser.equals("y")){
                return taskNumber;
        }else{
            return -1 ;
        }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1 ;
        }
    }


    // クラスの内容表示メソッド
    public String toString(){

        String tasksString = this.tasks.stream()
        .map(i -> "　タイトル：　" + i.getTitle() + "　詳細：　" + i.getMain())
        .collect(Collectors.joining(", "));

        return tasksString;
    }

}