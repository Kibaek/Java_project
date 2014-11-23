package java63.servlets.test02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java63.servlets.test02.dao.ProductDao;
import java63.servlets.test02.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



/*
 * POST 요청처리
 * => 한글이 꺠지는 문제 해결
 * => 처음 getParameter()를 호출하기 전에
 * 		request.setCharacterEncoding("UTF-8") 호출하라!
 * 		=> 클라이언트가 보내는 데이터의 문자 집합을 알려줘라!
 * 
 */
@WebServlet("/test02/product/add")
public class ProductAddServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	SqlSessionFactory sqlSessionFactory;
	ProductDao productDao;

	public ProductAddServlet() {
		try {
			String resource = "java63/servlets/test02/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);					 // 바이너리 데이터나 우리가 읽을수 없는 값이다. 컴터가 읽게 해줘라 시끼야
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 

			productDao = new ProductDao();
			productDao.setSqlSessionFactory(sqlSessionFactory);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) // 요청에 관련된도구, 응답에 관련된 도구
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Product product = new Product();
		product.setName(request.getParameter("name")); // 클라이언트에 값을 꺼내는기능이 있다.
		product.setQuantity(Integer.parseInt(request.getParameter("qty")));
		product.setMakerNo(Integer.parseInt(request.getParameter("mkno"))); 						// 클라이언트가 보낸값을 mkno 값을 정수값으로 바꿔서 담고.
		 
		productDao.insert(product);

		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.sendRedirect("list"); 
	}
}
