package model;

/*
* このクラスは利用者と管理者の機能の元を持っている人間クラス
*/

abstract class Human{

	private int id;
    private String name;
    private String email;
    private String password;
    private int adminflg;
    private int deleteflg;

    // TaskMemoクラスにTaskインスタンスを生成
    abstract public void memoContentCreate();

    // TaskMemoクラスのTaskインスタンスを削除
    abstract public void memoContentDelete();

    // TaskMemoクラスのTaskインスタンスの内容変更
    abstract public void memoContentEdit();


    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
    	return this.id;
    }
    
    // 名前をインスタンス変数に格納
    public void setName(String name){
        this.name = name;
    }

    // 名前をインスタンス変数から取得
    public String getName(){
        return this.name;
    }

     public void setEmail(String email){
        this.email = email;
    }

    // 名前をインスタンス変数から取得
    public String getEmail(){
        return this.email;
    }

     public void setPassword(String password){
        this.password = password;
    }

    // 名前をインスタンス変数から取得
    public String getPassword(){
        return this.password;
    }

     public void setAdminflg(int adminflg){
        this.adminflg = adminflg;
    }

    // 名前をインスタンス変数から取得
    public int getAdminflg(){
        return this.adminflg;
    }

     public void setDeleteflg(int deleteflg){
        this.deleteflg = deleteflg;
    }

    // 名前をインスタンス変数から取得
    public int getDeleteflg(){
        return this.deleteflg;
    }

}
