package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/* Include에 사용할 서블릿 
 * => HTML 페이지의 헤더 부분의 출력을 맡는다.
 */

@WebServlet("/common/header")
public class HtmlHeaderServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    
    //웹 애플리케이션 경로 알아내기 예) /web02
    String webAppPath = 
        this.getServletContext().getContextPath();
    
    //웹 애플리케이션의 화면에 보여주는 이름
    // web.xml의 <display-name>태그 값 출력한다.
    // 그리고, 이 태그의 값은 관리자 화면에 출력된다.
    //System.out.println(this.getServletContext().getServletContextName());
    
    PrintWriter out = response.getWriter();
    
    out.println("<link rel='stylesheet'"); 
    out.println("      href='" + webAppPath
        + "/css/bootstrap.min.css'>");
    out.println("<link rel='stylesheet'"); 
    out.println("      href='" + webAppPath
        + "/css/bootstrap-theme.min.css'>");
    out.println("<link rel='stylesheet' href='" + webAppPath
        + "/css/common.css'>");
  }

}











