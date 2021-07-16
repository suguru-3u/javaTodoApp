package model;

/*
* Userが登録するTaskクラス
* Title,main,finshflgの3つのフィールドを持っている
*/

public class Task{

    // フィールド
	private int id;
    private String title;
    private String main;
    private int finishFlg;
    

    // コンストラクト
    public Task(int id, String title,String main,int finishFlg){
    	this.id = id;
        this.title = title;
        this.main = main;
        this.finishFlg = finishFlg;
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
    public void setFinishFlg(int finishFlg){
        this.finishFlg = finishFlg;
    }
    
 // idフィールドの呼び出し
    public int getId(){
        return this.id;
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
    public int getFinishFlg(){
        return this.finishFlg;
    }

    // クラスの内容表示メソッド
    public String toString(){
        if(this.finishFlg == 1){
            String finshTask = "このTaskは終了しています..  タイトル：　" + this.title + "詳細:　" + this.main;
            return finshTask;
        }else{
            String noFinishTask = "タイトル：　" + this.title + " 詳細:　" + this.main;
            return noFinishTask;
        }
         
    }
}
