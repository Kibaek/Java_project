package java63.servlets.test03;

import java.io.IOException;
import java.io.PrintWriter;

import java63.servlets.test03.dao.ProductDao;
import java63.servlets.test03.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test03/product/list")
public class ProductListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  static final int PAGE_DEFAULT_SIZE = 3;
  
  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    int pageNo = 0;
    int pageSize = 0;
    
    if (request.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt(request.getParameter("pageNo"));
      pageSize = PAGE_DEFAULT_SIZE;
    }
    
    if (request.getParameter("pageSize") != null) {
      pageSize = Integer.parseInt(request.getParameter("pageSize"));
    }
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<link rel='stylesheet'"); 
    out.println("      href='../../css/bootstrap.min.css'>");
    out.println("<link rel='stylesheet'"); 
    out.println("      href='../../css/bootstrap-theme.min.css'>");
    out.println("<link rel='stylesheet' href='../../css/common.css'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<div class='container'>");
    out.println("<h1>제품 목록</h1>");
    out.println("<p><a href='product-form.html' class='btn btn-primary'>새제품</a></p>");
    out.println("<table class='table table-hover'>");
    out.println("<tr>");
    out.println("  <th>#</th><th>제품</th><th>수량</th><th>제조사</th>");
    out.println("</tr>");
    
    //for (Product product : AppInitServlet.productDao.selectList(pageNo, pageSize)) {
    //for (Product product : ContextLoaderListener.productDao.selectList(pageNo, pageSize)) {
    
    // ProductDao를 ServletContext 보관소에서 꺼내는 방식을 사용
    // => 단점: 위의 방식보다 코드가 늘었다.
    // => 장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
    ProductDao productDao = (ProductDao)this.getServletContext()
                                         .getAttribute("productDao");
    for (Product product : productDao.selectList(pageNo, pageSize)) {
      out.println("<tr>");
      out.println("  <td>" + product.getNo() + "</td>");
      out.println("  <td><a href='view?no=" + product.getNo() + "'>" 
            + product.getName() + "</a></td>");
      out.println("  <td>" + product.getQuantity() + "</td>");
      out.println("  <td>" + product.getMakerNo() + "</td>");
      out.println("</tr>");
    }
    out.println("</table>");
    out.println("</div>");
    
    out.println("<script src='../../js/jquery-1.11.1.js'></script>");
    
    out.println("</body>");
    out.println("</html>");
  }
  
}












