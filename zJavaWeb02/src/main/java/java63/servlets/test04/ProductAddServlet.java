package java63.servlets.test04;

import java.io.IOException;

import java63.servlets.test04.dao.ProductDao;
import java63.servlets.test04.domain.Product;

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
    //다음 코드는 필터로 대체함.
    //request.setCharacterEncoding("UTF-8");
    
    Product product = new Product();
    
    product.setName(request.getParameter("name"));
    product.setUrl(request.getParameter("url"));
    product.setInfo(request.getParameter("info"));
    product.setMemo(request.getParameter("memo"));
    
    //AppInitServlet.productDao.insert(product);
    //ContextLoaderListener.productDao.insert(product);
    
    // ProductDao를 ServletContext 보관소에서 꺼내는 방식을 사용
    // => 단점: 위의 방식보다 코드가 늘었다.
    // => 장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
    ProductDao productDao = (ProductDao)this.getServletContext()
                                         .getAttribute("productDao");
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












