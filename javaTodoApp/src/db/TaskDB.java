package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDB {
	  // 変数の準備

    public static ResultSet getDBTasks() {
    	
    	List< ResultSet > tasks  = new ArrayList< ResultSet >();
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
// SQL文の作成
	    String sql = "SELECT * FROM users";
	
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
