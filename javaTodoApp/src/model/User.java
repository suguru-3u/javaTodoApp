package model;

import db.TaskDB;
import form.KeyBord;
import note.TaskMemo;

public class User extends Human{

    // Taskクラスを収納
    private TaskMemo taskMemo;

    // コンストラクタ
    public User(){
        System.out.print("\nお名前を入力してください  :");
        String name = KeyBord.inputKeyBordString();

        System.out.print("emailを入力してください   :");
        String email = KeyBord.inputKeyBordString();

        System.out.print("Passwordを入力してください:");
        String password = KeyBord.inputKeyBordString();
        
        super.setName(name);
        super.setEmail(email);
        super.setPassword(password);
        super.setAdminflg(false);
        super.setDeleteflg(false);

        System.out.println("\nようこそ、JavaTodoAooへ" + this.getName() + "さん");
    } 

    // TaskMemoをインスタンス変数に格納
    public void setTaskMemo(TaskMemo taskMemo){
        this.taskMemo = taskMemo; 
    }

    // ユーザーがTaskを登録するメソッド
    public void memoContentCreate(){
        System.out.println("\n登録したいTaskを入力してください");

        System.out.print("TaskのTitleを入力してください：　");
        String taskTitle = KeyBord.inputKeyBordString();

        System.out.print("TaskのMainを入力してください ：　");
        String taskMain = KeyBord.inputKeyBordString();

        TaskDB.createDBTasks(taskTitle,taskMain);

    }

    // ユーザーが登録したTaskを削除するメソッド
    public void memoContentDelete(){

        System.out.print("\n削除したいTaskの番号を入力してください：　");
        int taskSerchCheack = this.taskMemo.taskSerch();

        if(taskSerchCheack >= 0){
            this.taskMemo.deleteTask(taskSerchCheack);
        }
    }

    // ユーザーが登録したTaskの内容を変更するメソッド
    public void memoContentEdit(){
        
        System.out.print("\n編集したいTaskの番号を入力してください：　");
        int taskSerchCheack = this.taskMemo.taskSerch();

        if(taskSerchCheack >= 0){

            System.out.print("TaskのTitleを入力してください：　");
            String taskTitleChange = KeyBord.inputKeyBordString();

            System.out.print("TaskのMainを入力してください ：　");
            String taskMainChange = KeyBord.inputKeyBordString();

            taskMemo.updateTask(taskSerchCheack,taskTitleChange,taskMainChange);
        }
    }
}