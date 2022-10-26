package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 실행시키기 위한 메인이 필요 없음

public class T01ServletLifeCycle extends HttpServlet {
	/*
	 * 서블릿이란?
	 * 
	 * 컨테이너(서블릿 엔진)에 의해 관리되는 자바기반 웹컴포넌트로서, 동적인 웹 컨텐츠의 생성을 가능하게 해준다.
	 * 
	 * 생성자와 init메서드는 한번만 호출됨.
	 * init은 초기화작업이 필요한 작업은 한번만 호출되야하니까 init안에 구현하면 좋음
	 */
	public T01ServletLifeCycle() {
		// 생성자
		System.out.println("T01ServletLifeCycle 생성자 호출됨");
	}

	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성
		System.out.println("init() 호출됨.");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업 수행이 시작되는 지점.(자바의 메인메서드 역할)
		System.out.println("service() 호출됨.");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 get인 경우 호출됨
		System.out.println("doGet() 호출됨.");
		
		throw new ServletException("서블릿에서 난리가 났어요~~~!!!!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 POST인 경우 호출됨
		System.out.println("doPost() 호출됨.");
	}

	@Override
	public void destroy() {
		// 서블릿 객체 소멸시(컨테이너로부터 제거 전) 호출됨
		System.out.println("destroy() 호출됨.");
	}

}
