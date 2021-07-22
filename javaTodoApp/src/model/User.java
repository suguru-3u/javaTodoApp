package model;

public class User extends Human{

    // コンストラクタ
    public User(int id ,String name,String email,String password,int adminFlg,int deleteFlg){
    	super.setId(id);
        super.setName(name);
        super.setEmail(email);
        super.setPassword(password);
        super.setAdminflg(adminFlg);
        super.setDeleteflg(deleteFlg);
    } 
    
}