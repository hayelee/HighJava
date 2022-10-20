package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServerTest {
	
	public static void main(String[] args) throws IOException {
		
		// TCP 소켓 통신을 하기 위한 ServerSocket 객체 생성
		//(7777=port번호 - 도착하고 출발하는 역할을 하는(항구) ip주소는 호스트 역할을 구분하고, 그 호스트의 프로세스를 구분하는 방법은 port번호이다.)
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다...");
		
		// accept()메서드는 Client에서 연결 요청이 올 때까지 계속 기다린다.
		// 연결 요청이 오면 Socket객체를 생성해서 Client의 Socket과 연결됨.
		Socket socket = server.accept();
		
		//------------------------------------------------------
		// 이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : " + socket.getInetAddress());
		
		// Client에 메시지 보내기
		
		// OutputStream 객체를 구성하여 전송한다.
		// 접속한 Socket의 getOutputStream() 이용하여 구한다.
		OutputStream out = socket.getOutputStream();
		
		DataOutputStream dos = new DataOutputStream(out);
		
		dos.writeUTF("어서오셔요..."); // 메시지 보내기 정상적으로 읽으려면 readUTF로 읽어야한다.
		
		System.out.println("메시지를 보냈습니다.");
		
		dos.close();
	}
}
