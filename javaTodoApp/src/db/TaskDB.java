package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Task;

public class TaskDB {
	
//	Task登録メソッド
	public static void createDBTasks(String title,String main) {
		
	    String SQL = "insert into tasks(title,main) VALUES(?,?)";
	        
        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

            conn.setAutoCommit(false);
            
            try(PreparedStatement ps = conn.prepareStatement(SQL)){
                ps.setString(1,title);
                ps.setString(2,main);
                
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

	public static void deleteDBTasks(int id) {
		
	    String SQL = "delete from tasks where id = (?)";
	        
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
            System.out.println("処理が完了しました");
        }
	}
	
	public static void editDBTasks(int id ,String title,String main) {
		
	    String SQL = "update tasks set title = (?),main = (?) where id = (?)";
	        
        try(Connection conn = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS())){

            conn.setAutoCommit(false);
            
            try(PreparedStatement ps = conn.prepareStatement(SQL)){
            	ps.setString(1,title);
            	ps.setString(2,main);
                ps.setInt(3,id);
                   
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
            System.out.println("DB変更の処理が完了しました");
        }
	}

//	DBからTask情報を取得
    public static List<Task> getDBTasks(List<Task> tasks) {
    	
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
    						Task task = new Task(rs.getInt("id"),rs.getString("title"),rs.getString("main"),rs.getInt("delete_flg"));
    						tasks.add(task);      			
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
	    return tasks;
    }
}
