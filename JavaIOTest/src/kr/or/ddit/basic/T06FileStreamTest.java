package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력(저장) 예제
 */
public class T06FileStreamTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		
		try {
			// 출력을 위한 FileOutputStream 객체 생성
			fos = new FileOutputStream("d:/D_Other/out.txt", true); // out.txt파일생성 // true -> 실행할때마다 데이터가 추가됨~
			
			for(char ch = 'a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일 쓰기 작업 완료....");
			
			// 파일 읽기 
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
			
			fis.close();
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			
		}
	}
}
