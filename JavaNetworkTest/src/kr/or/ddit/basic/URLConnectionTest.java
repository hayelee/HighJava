package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	
	public static void main(String[] args) throws Exception {
		
		// URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 추상클래스
		
		// 특정 서버(ex:네이버)에 접속하여 정보 가져오기
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header 정보 가져오기
		
		// URLConnection 객체 구하기
		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		System.out.println();
		
		// 전체 Header정보 출력하기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		Iterator<String> it = headerMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("------------------------------------------------");
		
		InputStream is = urlConn.getInputStream();
		
		InputStreamReader isr = new InputStreamReader(new BufferedInputStream(is));
//		InputStreamReader isr = new InputStreamReader((InputStream) urlConn.getContent());
		
		int data = 0;
		while((data = isr.read()) != -1) {
			System.out.print((char)data);
		}
	}
}
