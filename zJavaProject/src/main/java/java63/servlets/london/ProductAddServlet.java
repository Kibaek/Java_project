package java63.servlets.london;

import java.io.IOException;
import java63.servlets.london.dao.ProductDao;
import java63.servlets.london.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test04/product/add")
public class ProductAddServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    
    Product product = new Product();
    
    product.setName(request.getParameter("name"));
    product.setUrl(request.getParameter("url"));
    product.setInfo(request.getParameter("info"));
    product.setMemo(request.getParameter("memo"));
    
    ProductDao productDao = (ProductDao)this.getServletContext()
                                         .getAttribute("productDao");
    try {
      productDao.insert(product);
      
    } catch (Exception e) {
    	
      RequestDispatcher rd = 
          request.getRequestDispatcher("/common/error");
      request.setAttribute("error", e);
      rd.forward(request, response);
    }
    
    HttpServletResponse orginResponse = (HttpServletResponse)response;
    orginResponse.sendRedirect("list");
  }
  
}












