package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws Exception {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;
		byte[] temp = new byte[4];

		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int readBytes = 0; // 몇바이트인지 관리하기 위해 생성
		
		while((readBytes = bais.read(temp)) != -1) {
			baos.write(temp, 0, readBytes); // 중복되는 숫자를 제거하기 위한
			
			System.out.println("temp : " +  Arrays.toString(temp));
		}
		outSrc = baos.toByteArray();
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

	}

}
