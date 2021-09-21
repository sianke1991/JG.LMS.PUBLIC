package sqlDatePrac;

public class Ex02 {
	
	public static void main(String[] args) {
		int beginTime=930; //강좌 시작 시각=09시 30분
		java.util.Calendar calBegin = java.util.Calendar.getInstance();
		calBegin.set(java.util.Calendar.HOUR_OF_DAY,beginTime/100); //강좌 시작 시각의 시(hour_of_day)를 calBegin에 대입한다.
		calBegin.set(java.util.Calendar.MINUTE,beginTime%100); //강좌 시작 시각의 분(minute)을 calBegin에 대입한다.
		java.sql.Date dateBegin = new java.sql.Date(calBegin.getTimeInMillis()); //강좌 시작 시간의 정보를 담고 있는 calBegin을 가지고 java.sql.Date 객체 dateBegin을 생성한다.
		
		java.sql.Date dateEnter = new java.sql.Date(new java.util.Date().getTime()); //현재 이 시각에 입실했다고 가정하자 (실제로는 DB에서 java.sql.Date 객체를 읽어와야 한다.)
		System.out.println(dateEnter.before(dateBegin)); //true (입실 시각이 강좌 시작 시각보다 이전일 경우)
		
		long dateInterval = dateEnter.getTime() - dateBegin.getTime(); //입실 시각에서 강좌 시작 시각을 뺀 값 (강좌 시작 전에 입실했으면 이 값은 음수가 된다.)
		System.out.println((dateInterval<0)?Math.abs(dateInterval/60000.0)+" 분 전에 입실":dateInterval/60000.0+" 분 후에 입실"); //35.9997 분 전에 입실
		
		//만약 강좌 시작 후 10 분까지 제 시각 입실로 인정한다면 long dateInterval이 600000 이하일 때 제 시각 입실, dateInterval이 600000 초과일 때 지각으로 판별하면 된다.
		if(dateInterval<600000) {
			System.out.println("정상 입실입니다.");
		} else {
			System.out.println("지각입니다.");
		} //정상 입실입니다.
		
	}

}
