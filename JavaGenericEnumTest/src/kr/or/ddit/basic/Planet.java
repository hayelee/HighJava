package kr.or.ddit.basic;

public class Planet {

	/*
	 * 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 행성의 면적을 구하시오. (단, enum 객체 생성시 반지름을 이용하도록
	 * 정의하시오.)
	 * 
	 * 예) 행성의 반지름(KM): 수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232),
	 * 천왕성(25362), 해왕성(24622)
	 */
	public enum Planets {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double str;

		Planets(double data) {
			this.str = data;
		}

		public double getStr() {
			return str;
		}
	}

	public static void main(String[] args) {
		for(Planets s : Planets.values()) {
			System.out.println(s + "의 면적 : "+(4 * Math.PI * Math.pow(s.getStr(), 2)));
		}
	}
}


