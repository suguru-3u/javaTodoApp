package form;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import db.AccessKey;

public class UserForm {
	
	private String name;
	private String email;
	private byte[] password;
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
        
        if(inputCount && emptyCheak) {
        	byte[] passwordHash = null;
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
	
	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	public byte[] getPassword() {
		return this.password;
	}
	
	public void setCheakCreate(boolean cheakCreate) {
		this.cheakCreate = cheakCreate;
	}
	
	public boolean getCheakCreate() {
		return this.cheakCreate;
	}
	
	public boolean inputCount(String inputName){
		if(inputName.length() < 20) {
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
	
	public byte[] cryptoHash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(AccessKey.getHash());
        byte[] passwordHash = digest.digest(password.getBytes());
        System.out.println(passwordHash); 
        return passwordHash;
    }

}
