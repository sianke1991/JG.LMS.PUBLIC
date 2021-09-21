package sqlDatePrac;

public class Ex01 {

	public static void main(String[] args) {
		//java.sql.Date의 생성자는 long 타입의 정수를 받는다. 이 정수는 1970년 1월 1일 부터 경과된 시간을 밀리초 단위로 나타낸 것이다.
		//이 long 타입의 정수를 longTime이라고 부르자.
		java.sql.Date date = new java.sql.Date(0);
		System.out.println(date); //1970-01-01
		//java.sql.Date 객체의 longTime은 setTime 메서드를 통해 설정할 수 있다.
		date.setTime(new java.util.Date().getTime());
		System.out.println(date); //2021-09-21
		//java.sql.Date 객체의 longTime은 getTime 메서드를 통해 읽어올 수 있다.
		System.out.println(date.getTime()); //1632180378289
		
		//DB에 접근하여 얻어오는 java.sql.Date 객체는 적절한 longTime 값을 가지고 있는 객체일 것이다. (추후에 실험 필요)
		
		//java.util.Calendar의 객체를 생성할 때는 생성자를 사용하지 못하고 getInstance 메서드를 사용한다.
		java.util.Calendar cal = java.util.Calendar.getInstance();
		//java.util.Calendar 객체의 시간을 설정하려면 setTime 메서드에 인자로 java.util.Date 객체를 넣어주면 된다. (java.sql.Date는 java.util.Date의 서브클래스이므로 
		//java.sql.Date의 객체 역시 인자로 들어갈 수 있다.)
		cal.setTime(date);
		
		System.out.println(cal.get(java.util.Calendar.YEAR)); //2021
		System.out.println(cal.get(java.util.Calendar.MONTH)); //8 (여기에 1을 더해줘야 9월을 얻는다.)
		System.out.println(cal.get(java.util.Calendar.DATE)); //21
		System.out.println(cal.get(java.util.Calendar.DAY_OF_WEEK)); //3 (일요일을 1, 토요일을 7로 하여 요일 값 출력)
			System.out.println(java.util.Calendar.SUNDAY); //1
			System.out.println(java.util.Calendar.MONDAY); //2
			System.out.println(java.util.Calendar.TUESDAY); //3
			System.out.println(java.util.Calendar.SATURDAY); //7
		System.out.println(cal.get(java.util.Calendar.HOUR_OF_DAY)); //8 (오전, 오후를 명확하게 하기 위해 HOUR_OF_DAY를 사용)
		System.out.println(cal.get(java.util.Calendar.HOUR)); //8
		System.out.println(cal.get(java.util.Calendar.MINUTE)); //39
		System.out.println(cal.get(java.util.Calendar.SECOND)); //6
		System.out.println(cal.get(java.util.Calendar.MILLISECOND)); //566 (이건 필요 없을 듯...)
	}
	
}
