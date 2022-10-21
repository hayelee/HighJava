package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiverAddr;
	private int port; // 데이터를 보낼때 사용할 포트번호
	private File file;
	
	public UdpFileSender(String receiverIp, int port) {
		try {
			ds = new DatagramSocket();
			this.port = port;
			receiverAddr = InetAddress.getByName(receiverIp);
			file = new File("C:/Users/PC-09/Desktop/강아지/새 폴더 (2)/갓파쿠.png");
			
			if(!file.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
				System.exit(0);
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void start() throws Exception {
		
		long fileSize = file.length();
		long totlaReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			// 전송 시작을 알려주기 위한 문자열 전송
			sendData("start".getBytes());
			
			// 파일명 전송
			sendData(file.getName().getBytes());
			
			// 총 파일 크기 정보를 전송
			sendData(String.valueOf(fileSize).getBytes());
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];
			while(true) {
				// 패킷전송간의 간격을 주기 위해
				Thread.sleep(10);  
				int readBytes = fis.read(buffer, 0, buffer.length);
				if(readBytes == -1) { // 다 읽은 경우...
					break;
				}
				
				// 읽은 파일내용 전송하기
				sendData(buffer, readBytes);
				
				totlaReadBytes += readBytes;
				System.out.println("진행 상태 : " + totlaReadBytes 
								+ "/" + fileSize + " Byte(s) (" 
								+ (totlaReadBytes*100/fileSize) + " %)");
			}
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린시간 : " + diffTime + " (ms)");
			System.out.println("평균 전송 속도: " + transferSpeed + " Bytes/ms");
			
			System.out.println("전송완...");
			
			fis.close();
			ds.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 바이트배열 데이터 전송하기
	 * @param date
	 * @param length
	 * @throws IOException
	 */
	public void sendData(byte[] date, int length) throws IOException {
		dp = new DatagramPacket(date, length, receiverAddr, port);
		ds.send(dp);
	}
	
	public void sendData(byte[] data) throws IOException {
		sendData(data, data.length);
	}
	
	public static void main(String[] args) throws Exception {
		new UdpFileSender("192.168.35.47", 8888).start();
	}
}
