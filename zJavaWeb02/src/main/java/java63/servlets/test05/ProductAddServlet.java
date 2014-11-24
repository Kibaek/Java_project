package java63.servlets.test05;

import java.io.IOException;
import java63.servlets.test05.dao.ProductDao;
import java63.servlets.test05.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@WebServlet("/test05/product/add")
public class ProductAddServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    //다음 코드는 필터로 대체함.
    //request.setCharacterEncoding("UTF-8");
    
    Product product = new Product();
    product.setName(request.getParameter("name"));
    product.setQuantity(Integer.parseInt(request.getParameter("qty")));
    product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));
    
    //스프링의 ContextLoaderListener가 준비한 
    //ApplicationContext 객체 꺼내기
    ApplicationContext appCtx =
        WebApplicationContextUtils.getWebApplicationContext(
            this.getServletContext());
    
    ProductDao productDao = (ProductDao)appCtx.getBean("productDao");
        
    try {
      productDao.insert(product);
      
    } catch (Exception e) {
      /* Forward로 다른 서블릿에게 제어권 위임하기
       * => 제어권이 넘어가면 돌아오지 않는다.
       */
      RequestDispatcher rd = 
          request.getRequestDispatcher("/common/error");
      request.setAttribute("error", e);
      rd.forward(request, response);
    }
    
    HttpServletResponse orginResponse = (HttpServletResponse)response;
    orginResponse.sendRedirect("list");
  }
  
}












