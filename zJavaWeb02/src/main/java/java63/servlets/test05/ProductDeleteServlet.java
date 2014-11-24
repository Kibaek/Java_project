package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;

import java63.servlets.test05.dao.ProductDao;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@WebServlet("/test05/product/delete")
public class ProductDeleteServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    int no = Integer.parseInt(request.getParameter("no"));

    //스프링의 ContextLoaderListener가 준비한 
    //ApplicationContext 객체 꺼내기
    ApplicationContext appCtx =
        WebApplicationContextUtils.getWebApplicationContext(
            this.getServletContext());
    
    ProductDao productDao = (ProductDao)appCtx.getBean("productDao");
    
    productDao.delete(no);
    
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
    out.println("<h1>삭제 결과</h1>");
    out.println("<p>삭제하였습니다</p>");
    out.println("</div>");
    
    out.println("</body>");
    out.println("</html>");
    
    /* Redirect는 클라이언트에 재요청 URL만 보낸다.
     * 따라서 이전에 출력한 콘텐츠는 취소한다.
     * => 버퍼에 출력된 내용은 클라이언트로 보내지 않고 버린다.
     * => 위의 출력문은 작성할 필요가 없다.
     */
    HttpServletResponse orginResponse = (HttpServletResponse)response;
    orginResponse.sendRedirect("list");
    
    /* Redirect의 응답 내용 => Location 헤더에 재요청 URL이 있다.
     * => 클라이언트에 어떤 내용을 출력하지 않고 
     *    페이지를 바로 전환하는 효과를 내고 싶을 때.
HTTP/1.1 302 Found
Server: Apache-Coyote/1.1
Location: http://192.168.0.32:8080/web02t/test02/product/list
Content-Type: text/html;charset=UTF-8
Content-Length: 0
Date: Thu, 20 Nov 2014 06:40:18 GMT
     */
  }
  
}












