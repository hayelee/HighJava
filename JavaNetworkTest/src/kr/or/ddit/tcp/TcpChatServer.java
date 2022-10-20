package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = server.accept(); // 클라이언트가 만들어지면 시작함
			System.out.println("접속되었습니다.");
			// 스레드로 만들어서 보내기와 받기 동시에 진행
			Sender sender = new Sender(socket);
			sender.start();
			
			Receiver receiver = new Receiver(socket);
			receiver.start();
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
