package java63.assign01.servlets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import java63.assign01.dao.ProductDao;
import java63.assign01.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {
  
  ProductDao productDao;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		int pageNo = 0;
	  int pageSize = 0;
	  
		try {
			productDao = new ProductDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		
		 if (request.getParameter("pageNo") != null) {
	      pageNo = Integer.parseInt((String)request.getParameter("pageNo"));
	      pageSize = 3;
	    }
	    
	    if (request.getParameter("pageSize") != null) {
	      pageSize = Integer.parseInt((String)request.getParameter("pageSize"));
	    }
	    
	    out.println("<html>");
	    out.println("<body>");
	    
	    for (Product product : productDao.selectList(pageNo, pageSize)) {
	      out.printf("%-3d %-20s %7d %-3d<br>", 
	          product.getNo(), 
	          product.getName(), 
	          product.getQuantity(), 
	          product.getMakerNo());
	    }
	    
	    out.println("</body>");
	    out.println("</html>");
	    // =======
	}
}
