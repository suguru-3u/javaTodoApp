package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.Main;
import model.User;

public class UserDB {
	
	//	ユーザー情報の登録
	public static void createDBUser(String name,String email,String password) {
		
		ResultSet rs = null;
		
	    String createSQL = "insert into users(name,email,password) VALUES(?,?,?)";
	    
	    String SQL = "SELECT * FROM users WHERE email = (?) and password = (?)";
	        
        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

            conn.setAutoCommit(false);
            
            try {
                PreparedStatement ps = conn.prepareStatement(createSQL);
                ps.setString(1,name);
                ps.setString(2,email);
                ps.setString(3,password);
                
                ps.executeUpdate();
               
                conn.commit();
                
            } catch (Exception e) {
                conn.rollback();
                System.out.println("rollback");
                throw e;    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("処理が完了しました");
        }
	}
	
	//	ユーザーログイン
	public static void userLogin(String email,String password) {
		
		ResultSet rs = null;
		
		 String SQL = "SELECT * FROM users WHERE email = (?) and password = (?) and delete_flg = 0";
	        
	        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

	            conn.setAutoCommit(false);
	            
	            try(PreparedStatement ps = conn.prepareStatement(SQL)){            
	                ps.setString(1,email);
	                ps.setString(2,password);
	                
	                rs = ps.executeQuery();    
	                
	                conn.commit();
	                
//	                if(rs.next()) {
    					while (rs.next()) {
    						Main.user  = new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),
    											 rs.getString("password"),rs.getInt("admin_flg"),rs.getInt("delete_flg"));
    						
    						Main.appp = false;
    					}
//    				}else {
//    					S	ystem.out.println("対象ユーザーが見つかりませんでした");    					
//    				}
	                
	            } catch (Exception e) {
	                conn.rollback();
	                System.out.println("rollback");
	                throw e;    
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            System.out.println("DB処理が完了しました");
	        }
	}

	//	ユーザー情報の削除
	public static void deleteDBUser(int id) {
		
	    String SQL = "update users set delete_flg = 1  where id = (?)";
	        
        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

            conn.setAutoCommit(false);
            
            try(PreparedStatement ps = conn.prepareStatement(SQL)){           
                ps.setInt(1,id);
                   
                ps.executeUpdate();
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("退会処理が完了しました");
        }
	}
	
	//	ユーザー情報の編集
	public static void editDBUser(String name,String email,String password) {
		
	    String SQL = "update users set name = (?), email = (?), password = (?) where id = (?)";
	        
        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

            conn.setAutoCommit(false);
            
            try(PreparedStatement ps = conn.prepareStatement(SQL)){
            	ps.setString(1,name);
            	ps.setString(2,email);
            	ps.setString(3,password);
                ps.setInt(4,Main.user.getId());
                   
                ps.executeUpdate();
                conn.commit();
                
            } catch (Exception e) {
                conn.rollback();
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("DB情報の変更処理が完了しました");
        }
	}
	
//	ユーザーログイン
	public static void userSerch() {
		
		ResultSet rs = null;
		
		 String SQL = "SELECT * FROM users WHERE id = (?) and delete_flg = 0";
	        
	        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

	            conn.setAutoCommit(false);
	            
	            try(PreparedStatement ps = conn.prepareStatement(SQL)){            
	                ps.setInt(1,Main.user.getId());
	                
	                rs = ps.executeQuery();    
	                
	                conn.commit();
	                
//	                if(rs.next()) {
    					while (rs.next()) {
    						Main.user  = new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),
    											 rs.getString("password"),rs.getInt("admin_flg"),rs.getInt("delete_flg"));
    						
    						Main.appp = false;
    					}
//    				}else {
//    					S	ystem.out.println("対象ユーザーが見つかりませんでした");    					
//    				}
	                
	            } catch (Exception e) {
	                conn.rollback();
	                System.out.println("rollback");
	                throw e;    
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            System.out.println("DB処理が完了しました");
	        }
	}

	//	DBからユーザー情報を取得
    public static List<User> getDBUser(List<User> users) {
    	
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
	    String SQL = "SELECT * FROM tasks where delete_flg = 0";
	    
	    try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

            conn.setAutoCommit(false);
            
            try {
            	ps = conn.prepareStatement(SQL);
            	rs = ps.executeQuery();                  
                conn.commit();
                
                try {
    				if(rs.next()) {
    					while (rs.next()) {
    						User user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),
									 rs.getString("password"),rs.getInt("admin_flg"),rs.getInt("delete_flg"));
    						users.add(user);      			
    					}				
    				}
    			} catch (SQLException e) {
    				// TODO 自動生成された catch ブロック
    				e.printStackTrace();
    			}
                
            } catch (Exception e) {
                conn.rollback();
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("DB処理が完了しました");       
        }
	    return users;
    }
}
