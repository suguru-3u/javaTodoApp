package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.Task;

public class TaskDB {
	
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

    public static List<Task> getDBTasks(List<Task> tasks) {
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
	    String sql = "SELECT * FROM tasks where delete_flg = 0";
	
	    try {
	        // JDBCドライバのロード
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // データベース接続
	        con = DriverManager.getConnection(AccessKey.getURL(), AccessKey.getUSER(), AccessKey.getPASS());
	        // SQL実行準備
	        stmt = con.prepareStatement(sql);
	        // 実行結果取得
	        rs = stmt.executeQuery();
	        
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
	        
	        
	    } catch (ClassNotFoundException e3) {
	        System.out.println("JDBCドライバのロードでエラーが発生しました");
	    } catch (SQLException e) {
	        System.out.println("データベースへのアクセスでエラーが発生しました。");
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	                return tasks;
	            }
	            return tasks;
	        } catch (SQLException e) {
	            System.out.println("データベースへのアクセスでエラーが発生しました。");
	        }
	    }
		return tasks;
    }
}
