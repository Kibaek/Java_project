package java63.servlets.test02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java63.servlets.test02.dao.ProductDao;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/* Refresh하기
 * => 응답헤더에 리프래시 정보 추가하기
 * 
 * service()메서드가 호출될 때 넘어오는 파라미터의 비밀!
 * HttpServletRequest와 HttpServletResponse객체에 넘어온다.
 * 
 */

//@WebServlet("/test02/product/delete") // 서블릿으로 등록
public class ProductDeleteServlet02 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	SqlSessionFactory sqlSessionFactory;
	ProductDao productDao;

	public ProductDeleteServlet02() {
		try {
			String resource = "java63/servlets/test02/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			productDao = new ProductDao();
			productDao.setSqlSessionFactory(sqlSessionFactory);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		productDao.delete(no);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");

			out.println("<head>");
				out.println("<link rel='stylesheet' href='../../css/bootstrap.min.css'>");
				out.println("<link rel='stylesheet' href='../../css/bootstrap-theme.min.css'>");
				out.println("<link rel='stylesheet' href='../../css/common.css'>");
			out.println("</head>");
	
			out.println("<body>");
			
				out.println("<div class='container'>");
						out.println("<h1>삭제 결과</h1>");
						
						out.println("<p>삭제하였습니다.</p>");
						
				out.println("</div>");
				
				out.println("<script>");
				out.println("window.setTimeout(function(){");
				out.println("	window.location.href = 'list';");
				out.println("},1000);");
				out.println("</script>");
			
			out.println("</body>");
		out.println("</html>");
		
		/*
		 * 생각해 볼 문제
		 * Q) 응답 헤더 다음에 콘텐츠가 출력되는데, 이와 같이
		 * 	  콘텐츠를 출력한 다음에 응답 헤더를 설정하는 것은
		 * 	  무의미한 일이 아니냐?
		 * 
		 * A) out.println()을 호출한다고 해서 바로 클라이언트로
		 * 	  데이터를 보내는 것은 아니다. 내부 버퍼에 보관하고 있다가
		 * 	  버퍼가 모두 차거나 service() 메서드가 리턴할 때
		 *    비로서 클라이언트로 응답한다.
		 */
		
		
		/*
		 * setHeader(응답헤더종류, 시간;요청url)
		 * 화면에 자동으로 바뀌는게 아니라 서버에서 응답할 때 2초뒤에 해당하는 URL로 가랏!
		 */
		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.setHeader("Refresh", "2;url=list");
	}
}
