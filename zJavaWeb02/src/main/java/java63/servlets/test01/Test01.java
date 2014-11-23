package java63.servlets.test01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/* 웹 브라우저에게 출력하기 
 *
 * 
 * ServletRequest => 요청 정보를 다루는 도구
 * ServletResponse => 응답 정보를 다루는 도구
 * 
 */

@WebServlet("/test01/Test01")
public class Test01 extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		//1. ServletResponse 도구를 사용하여 출력 스트림을 준비한다.
		PrintWriter out = response.getWriter();
		
		// 2. 출력 스트림을 사용하여 브라우저로 출력한다.
		out.println("아하~~안녕하세여?, 곤니찌와");
	} 

}
