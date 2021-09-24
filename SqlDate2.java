package day60;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class SqlDate2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		String user = "scott";
		String password = "tiger";
		String sql = "select hiredate from emp12 where ename = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String hiredateString = "";
		// 데이터베이스에 접근하여 읽어온 hiredate와 (hiredate의 날짜의) 16:00:00.0 의 시간을
		// 초 단위로 계산해본다.
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"kang");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				hiredateString = rs.getString("hiredate");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //try-catch-finally
		
		String[] biggerChunkDate = hiredateString.split("\\s");
		String[] formerSmallerChunkDate = biggerChunkDate[0].split("-");
		int year = Integer.parseInt(formerSmallerChunkDate[0]);
		int month = Integer.parseInt(formerSmallerChunkDate[1]);
		int date = Integer.parseInt(formerSmallerChunkDate[2]);
		
		String[] laterSmallerChunkDate = biggerChunkDate[1].split(":");
		int hour = Integer.parseInt(laterSmallerChunkDate[0]);
		int minute = Integer.parseInt(laterSmallerChunkDate[1]);
		float second = Float.parseFloat(laterSmallerChunkDate[2]);
		
		Calendar calHireDate = Calendar.getInstance();
		calHireDate.set(year, month-1, date, hour, minute, (int)second);
		
		Calendar calSet = Calendar.getInstance();
		calSet.set(year,month-1,date,16,0,0);
		
		System.out.println((calHireDate.getTimeInMillis()-calSet.getTimeInMillis())/1); //937996 (밀리초)
		System.out.println((calHireDate.getTimeInMillis()-calSet.getTimeInMillis())/1000); //937 (초)
		
		java.util.Date utilHireDate = new java.util.Date(calHireDate.getTimeInMillis());
		System.out.println(utilHireDate); //Thu Sep 23 16:15:38 KST 2021
		
		java.util.Date utilDateSet = new java.util.Date(calSet.getTimeInMillis());
		System.out.println(utilDateSet); //Thu Sep 23 16:00:00 KST 2021

		
		
	}

}
