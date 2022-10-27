package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet{
/*
	서블릿 동작 방식에 대하여...
	
	1. 사용자(클라이언트)가 URL을 클릭하면 HTTP 요청을 서블릿 컨테이너로 전송한다.
	2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느서블릿을 통해 처리해야 할지를 검색한다.
	   (로딩(객체생성)이 안된 경우에는 로딩함. 로딩시 init() 메서드 호출됨.)
	3. 서블릿 컨테이너는 요청을 처리할 개별 스레드를 생성하여 해당 서블릿 객체의 service()메서드를 호출하도록 한다.
	   (이때, HttpServletRequest 및 HttpServletResponse객체를 생성하여 파라미터로 넘겨준다.)
	4. service()메서드는 메서드타입을 체크하여 적절한 메서드를 호출한다.
	   (doGet, doPost, doPut, doDelete 등)
	5. 요청처리가 완료되면 요청 및 응답 객체는 소멸한다.
	6. 컨테이너로부터 서블릿 객체가 제거되는 경우에는 destroy()가 호출됨.
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Request 객체의 메서드 확인하기
		System.out.println("getCharacterEncoding() : " + req.getCharacterEncoding());
		System.out.println("getContentLength() : " + req.getContentLength());
		System.out.println("getQueryString() : " + req.getQueryString());
		System.out.println("getRequestURI() : " + req.getRequestURI());
		System.out.println("getMethod() : " + req.getMethod());
		System.out.println("getRemoteAddr() : " + req.getRemoteAddr());
		System.out.println("getRemotePort() : " + req.getRemotePort());
		System.out.println("getProtocol() : " + req.getProtocol());
		
		// Post방식으로 넘어오는 body데이터를 인코딩 설정함.
		// GET방식은 톰캣의 URIEncoding 설정을 통해 해결.
		// 반드시 Request 객체에서 값을 가져오기 전에 먼저 설정해야 함.
		req.setCharacterEncoding("UTF-8");
		
		// 요청 메시지로부터  name속성값 가져옴.
		String name = req.getParameter("name");
		
		System.out.println("name => " + name);
		
		// 요청객체에 데이터 저장하기
		req.setAttribute("tel", "1111-1111");
		req.setAttribute("addr", "대전시 중구 오류동");
		
		System.out.println(req.getAttribute("addr"));
		
		//////////////////////////////////////////////////
		
		// 응답 메시지 인코딩 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		PrintWriter out = resp.getWriter(); // 문자열 기반(바이트기반은 input output)
		// 상대방 브라우저에 쏴주기
		out.println("name => " + name);
		out.println("서블릿 경로 => " + req.getServletPath());
		out.println("컨텍스트 경로 => " + req.getContextPath()); //여러개의 애플리케이션을 구분함
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
