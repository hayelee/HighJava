package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) {

		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");

		if (f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");

			try {
				if (f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("------------------------------------------");

		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();

		for (File f : files) {
			System.out.print(f.getName() + " => ");

			if (f.isFile()) {
				System.out.println("파일");
			} else if (f.isDirectory()) {
				System.out.println("디렉토리");
			}
		}

		System.out.println("==========================================");
		String[] strFiles = f3.list();

		for (String str : strFiles) {
			System.out.println(str);
		}

		System.out.println("------------------------------------------");
		System.out.println();

		// =============================================================================
		// //

		// 출력할 디렉토리 정보를 갖는 File객체 생성
		File f4 = new File("D:/D_Other");

		displayFileList(f4); // 파일목록을 조회
	}

	// 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	private static void displayFileList(File dir) {

		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용");
		
		// 디렉토리 안의 모든 파일 목록을 가져온다.
		File[] files = dir.listFiles();
		
		// 하위 디렉토리의 정보를 저장할 List객체 생성(인덱스값 저장용)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		// 날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i=0; i<files.length; i++) {
			
			//파일속성(읽기,쓰기,히든,디렉토리구분)
			String attr = "";
			//파일 용량
			String size = "";
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i);
				
			}else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			}
			System.out.printf("%s %5s %12s %s\n", sdf.format(new Date(files[i].lastModified())), attr, size, files[i].getName());
		}
		
		int dirCnt = subDirList.size(); // 폴더 안의 하위폴더 개수
		
		// 폴더 안의 파일 개수
		int fileCnt = files.length - dirCnt;
		
		System.out.println(fileCnt + "개의 파일, " + dirCnt + "개의 디렉토리");
		System.out.println();
		
		for(Integer i : subDirList) { // subDirList에 있는 i(인덱스값)만 빼옴
			
			displayFileList(files[i]);
			
			// 재귀호출 장점 : 간편함 나만 호출하면 되니까~ but 예외가 발생할 수 있고 메모리를 잡아먹기 때문에 권고하지는 않음
		}
	}
}
