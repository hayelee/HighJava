package kr.or.ddit.basic;

import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws Exception {
	// URL 클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
	URL url = new URL("http", "ddit.or.kr", 80, "/main/index.html?name=홍길동&age=30#kkk");
	
	System.out.println("전체 URL주소 : " + url);
	
	System.out.println("protocol : " + url.getProtocol());
	System.out.println("host : " + url.getHost());
	System.out.println("qyery : " + url.getQuery());
	System.out.println("file : " + url.getFile()); // 쿼리 정보 포함
	System.out.println("path : " + url.getPath()); // 쿼리 정보 미포함
	System.out.println("port : " + url.getPort());
	System.out.println("ref : " + url.getRef()); // 참조
	
	System.out.println(url.toExternalForm()); // 외부에 노출되는 형태로 출력
	System.out.println(url.toString());
	System.out.println(url.toURI().toString()); // url은 uri이기도 하니까~
	}
}
