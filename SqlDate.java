package day60;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class SqlDate {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		String user = "scott";
		String password = "tiger";
		String sql = "select hiredate from emp12 where ename = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//java.sql.Date hiredate = null;  //데이터베이스의 DATE 타입의 값을 getDate 메서드로 읽어오면 시,분,초의 정보가 잘려나가게 된다.
		String hiredateString = ""; //오히려 getString 메서드로 읽어오면 시,분,초의 정보가 유지된다.
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"kang");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//hiredate = rs.getDate("hiredate");
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
		
		/*
		Calendar cal = Calendar.getInstance();
		cal.setTime(hiredate);
		System.out.println(cal.getTimeInMillis());
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DATE));
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.get(Calendar.SECOND));
		
		System.out.println(hiredate);
		System.out.println(hiredate.getTime());
		cal.setTimeInMillis(hiredate.getTime());
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DATE));
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.get(Calendar.SECOND));
		*/
		
		System.out.println(hiredateString); //2021-09-23 16:15:38.0
		String[] biggerChunkDate = hiredateString.split("\\s"); //공백을 기준으로 hiredateString을 split함
		System.out.println(biggerChunkDate[0]); //hiredateString에서의 날짜부 (2021-09-23)
		System.out.println(biggerChunkDate[1]); //hiredateString에서의 시각부 (16:15:38.0)
		
		String[] smallerChunkDate = biggerChunkDate[1].split(":"); //쌍점을 기준으로 biggerChunkDate[1]을 split함
		System.out.println(smallerChunkDate[0]); //시각 (16)
		System.out.println(smallerChunkDate[1]); //분 (15)
		System.out.println(smallerChunkDate[2]); //초 (38.0)
		//smallerChunkDate[0] - smallerChunkDate[2] 각각은 문자열이다. 이를 수치로 변경한다.
		
		int hour = Integer.parseInt(smallerChunkDate[0]);
		int minute = Integer.parseInt(smallerChunkDate[1]);
		float second = Float.parseFloat(smallerChunkDate[2]);
		
		System.out.println(hour); //16
		System.out.println(minute); //15
		System.out.println(second); //38.0
	}

}
