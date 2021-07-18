package form;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import db.AccessKey;

public class UserForm {
	
	private String name;
	private String email;
	private String password;
	private boolean cheakCreate;
	
	public UserForm(){
		
		System.out.print("\nお名前を入力してください  :");
        String name = KeyBord.inputKeyBordString();

        System.out.print("emailを入力してください   :");
        String email = KeyBord.inputKeyBordString();

        System.out.print("Passwordを入力してください:");
        String password = KeyBord.inputKeyBordString();
        
        boolean inputCount = this.inputCount(name);
        
        boolean emptyCheak = this.emptyCheak(name,email,password);
        
        boolean emailStringCheak = this.emailCheak(email);
        
        boolean passwordStringCheak = this.passWordCheak(password);
        
        if(inputCount && emptyCheak && emailStringCheak && passwordStringCheak) {
        	String passwordHash = null;
			try {
				passwordHash = this.cryptoHash(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        	this.setName(name);
        	this.setEmail(email);
        	this.setPassword(passwordHash);
        	this.setCheakCreate(true);
        }else {
        	this.setCheakCreate(false);
        }
        
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setCheakCreate(boolean cheakCreate) {
		this.cheakCreate = cheakCreate;
	}
	
	public boolean getCheakCreate() {
		return this.cheakCreate;
	}
	
	public boolean inputCount(String inputName){
		if(inputName.length() > 20) {
			return false;
		}
		return true;
	}
	
	public boolean emptyCheak(String inputName,String inputEmail,String inputPassword) {
		if(inputName.isEmpty() && inputEmail.isEmpty() && inputPassword.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean emailCheak(String email) {
		
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		
		if(email.matches(mailFormat)){
			return true;		
		}
		return false;
	}
	
	public boolean passWordCheak(String password) {
		
		String mailFormat = "^[a-zA-Z0-9!#$%&^@]{6,12}$";
		if(password.matches(mailFormat)){
			return true;		
		}
		return false;
	}
	
	public String cryptoHash(String password) throws NoSuchAlgorithmException {
		
		// Hash化
        MessageDigest digest = MessageDigest.getInstance(AccessKey.getHash());
        byte[] passwordHash = digest.digest(password.getBytes());
   
        // 文字列に変換
        String passwordHashString = new String(passwordHash);
        
        return passwordHashString;
    }

}
