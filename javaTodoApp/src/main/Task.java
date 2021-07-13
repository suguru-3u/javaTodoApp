package main;

/*
* Userが登録するTaskクラス
* Title,main,finshflgの3つのフィールドを持っている
*/

public class Task{

    // フィールド
    private String title;
    private String main;
    private boolean finishFlg;

    // コンストラクト
    public Task(String title,String main){
        this.title = title;
        this.main = main;
        this.finishFlg = false;
    }

     // Titleフィールドの呼び出し
    public void setTitle(String title){
        this.title = title;
    }

    // Mainフィールドの呼び出し
    public void setMain(String main){
        this.main = main;
    }

    // FinishFlgフィールドへ代入
    public void setFinishFlg(){
        this.finishFlg = true;
    }

    // Titleフィールドの呼び出し
    public String getTitle(){
        return this.title;
    }

    // Mainフィールドの呼び出し
    public String getMain(){
        return this.main;
    }

    // FinishFlgフィールドの呼び出し
    public boolean getFinishFlg(){
        return this.finishFlg;
    }

    // クラスの内容表示メソッド
    public String toString(){
        if(this.finishFlg){
            String finshTask = "このTaskは終了しています..  タイトル：　" + this.title + "詳細:　" + this.main;
            return finshTask;
        }else{
            String noFinishTask = "タイトル：　" + this.title + " 詳細:　" + this.main;
            return noFinishTask;
        }
         
    }
}
