package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String user = "system";
	private String password = "1234";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 동적로딩 - 메모리에 올림
			conn = DriverManager.getConnection(url,user,password); // db와 접속시킴, 연결 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
}
