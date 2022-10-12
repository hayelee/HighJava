package kr.or.ddit.basic;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
/*
 'd:/D_Other/'에 있는 'Tulips.jpg'파일을

 '복사본_Tulips.jpg'로 복사하는 프로그램을 작성하시오.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class FileTest {
    
    public static void main(String[] args) throws IOException {
            
            FileInputStream fis = new FileInputStream("D:\\D_Other\\Tulips.jpg"); //읽을파일
            FileOutputStream fos = new FileOutputStream("D:\\D_Other\\복사본_Tulips.jpg"); //복사할파일
            
            // 버퍼
            BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
            int fileByte = 0;  // 읽어온 바이트 데이터 저장
            
            // fis.read()가 -1 이면 파일을 다 읽은것
            while((fileByte = bis.read()) != -1) {
                bos.write(fileByte);
            }
            System.out.println("복사완료...");
            //자원사용종료
            bis.close();
            bos.close();
    }
}