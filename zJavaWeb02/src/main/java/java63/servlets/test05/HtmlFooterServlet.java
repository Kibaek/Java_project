package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/* Include에 사용할 서블릿 
 * => HTML 페이지의 권리 안내문 출력을 맡는다.
 */

@WebServlet("/common/footer")
public class HtmlFooterServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    
    /* HTML Entity => HTML의 상수
     * &amp; => &
     * &lt;  => <
     * &gt;  => >
     * &copy; => (C)
     * &reg;  => (R)
     * &yen;  => 엔화
     */
    out.println("<address class='copyright'>Copyright&copy;Java63</address>");
  }

}











