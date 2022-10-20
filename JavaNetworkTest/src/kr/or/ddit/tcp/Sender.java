package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {

	// 필요한 객체들을 담기 위한 멤버변수 생성 완
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	public Sender(Socket socket) {
		
		// 상대방히 볼 이름(대화명) 초기화
		name = "[" + socket.getInetAddress() + ":" + socket.getLocalPort() + "]";
		
		scan = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void run() {
		
		while(dos != null) {
			try {
				dos.writeUTF(name + " >>> " + scan.nextLine());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
