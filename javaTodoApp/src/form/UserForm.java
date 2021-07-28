package form;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import db.AccessKey;
import main.Main;
import model.User;

public class UserForm {
	
	private String name;
	private String email;
	private String password;
	private boolean cheakCreate;
	
	public UserForm(){
		
		String userName = KeyBord.inputUserName();
		String userEmail = KeyBord.inputUserEmail();
        String userPassword = KeyBord.inputUserPassword();
        
        boolean inputCount = this.inputCount(userEmail);
        
        boolean emptyCheak = this.emptyCheak(userName,userEmail,userPassword);
        
        boolean emailStringCheak = this.emailCheak(userEmail);
        
        boolean passwordStringCheak = this.passWordCheak(userPassword);
        
        if(inputCount && emptyCheak && emailStringCheak && passwordStringCheak) {
        	 String passwordHashString = null;
 			try {
 				passwordHashString = KeyBord.userPasswordHash(userPassword);
 			} catch (Exception e) {
 				// TODO 自動生成された catch ブロック
 				e.printStackTrace();
 			}
        	this.setName(userName);
        	this.setEmail(userEmail);
        	this.setPassword(passwordHashString);
        	this.setCheakCreate(true);
        }else {
        	this.setCheakCreate(false);
        }
        
	}
	
	public UserForm(String name,String email,String password){
        
		boolean passwordStringCheak = true;
		
		if(name.isEmpty()) {
			name = Main.user.getName();
		}
		if(email.isEmpty()) {
			email = Main.user.getEmail();
		}
		
		if(!password.isEmpty()){
			passwordStringCheak = this.passWordCheak(password);
			if(passwordStringCheak) {
				try {
					password = this.cryptoHash(password);
				} catch (NoSuchAlgorithmException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}				
			}
		}
		
		if(password.isEmpty()) {
			password = Main.user.getPassword();
		}
		
		
        boolean inputCount = this.inputCount(name);
        
        boolean emailStringCheak = this.emailCheak(email);
        
        if(inputCount && emailStringCheak && passwordStringCheak) {   
        	this.name = name;
        	this.email = email;
        	this.password = password;
        	this.cheakCreate = true;
        }else {
        	this.cheakCreate = false;
        }
        
	}
	
	public UserForm(String name,String email,String password,User user){
        
		boolean passwordStringCheak = true;
		
		if(name.isEmpty()) {
			name = user.getName();
		}
		if(email.isEmpty()) {
			email = user.getEmail();
		}
		
		if(!password.isEmpty()){
			passwordStringCheak = this.passWordCheak(password);
			if(passwordStringCheak) {
				try {
					password = this.cryptoHash(password);	
				} catch (NoSuchAlgorithmException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}				
			}
		}
		
		if(password.isEmpty()) {
			password = user.getPassword();
		}
		
		
        boolean inputCount = this.inputCount(name);
        
        boolean emailStringCheak = this.emailCheak(email);
        
        if(inputCount && emailStringCheak && passwordStringCheak) {   
        	this.name = name;
        	this.email = email;
        	this.password = password;
        	this.cheakCreate = true;
        }else {
        	this.cheakCreate = false;
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
