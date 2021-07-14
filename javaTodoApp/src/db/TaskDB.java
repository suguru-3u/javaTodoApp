package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDB {
	
	
	
	public static void createDBTasks(String title,String main) {
		String URL = "";
	    String USER = "";
	    String PASS = "";
	    String SQL = "insert into tasks(title,main) VALUES(?,?)";
	        
        try(Connection conn = 
                DriverManager.getConnection(URL, USER, PASS)){

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

    public static ResultSet getDBTasks() {
//    	
//    	List<ResultSet> tasks  = new ArrayList<ResultSet>();
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
// SQL文の作成
	    String sql = "SELECT * FROM tasks where delete_flg = 0";
	
	    try {
	        // JDBCドライバのロード
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // データベース接続
	        con = DriverManager.getConnection("", "", "");
	        // SQL実行準備
	        stmt = con.prepareStatement(sql);
	        // 実行結果取得
	        rs = stmt.executeQuery();
	
	  // データがなくなるまで(rs.next()がfalseになるまで)繰り返す
//	        while (rs.next()) {
//	            tasks.add(rs);      
//	        }
	    } catch (ClassNotFoundException e3) {
	        System.out.println("JDBCドライバのロードでエラーが発生しました");
	    } catch (SQLException e) {
	        System.out.println("データベースへのアクセスでエラーが発生しました。");
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	                return rs;
	            }
	            return rs;
	        } catch (SQLException e) {
	            System.out.println("データベースへのアクセスでエラーが発生しました。");
	        }
	    }
		return rs;
    }
}
