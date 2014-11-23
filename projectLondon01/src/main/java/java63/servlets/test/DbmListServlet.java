package java63.servlets.test;

import java.io.IOException;
import java.io.PrintWriter;
import java63.servlets.test.dao.DbmDao;
import java63.servlets.test.domain.Dbm;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test/dbm/list")
public class DbmListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  static final int PAGE_DEFAULT_SIZE = 3;
  
  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
  	System.out.println("service() 실행 시작");
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
    // 다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
    //RequestDispatcher rd =
    	//	request.getRequestDispatcher("/common/header");
    //rd.include(request, response); // 자기가 받은 리퀘스트와 리스판스를 넘겨줘서 돌아갈 수 있게 만들어 준다.
    //out.println("</head>");
    
    
    
    out.println("</head>");
    out.println("<body>");
    out.println("<div class='container'>");
    out.println("<h1>개발자 맵</h1>");
    out.println("<p><a href='dbm-form.html' class='btn btn-primary'>맵추가</a></p>");
    out.println("<table class='table table-hover'>");
    out.println("<tr>");
    out.println("  <th>#</th><th>이름</th><th>url</th><th>정보</th><th>메모</th>");
    out.println("</tr>");
    
    //for (Product product : AppInitServlet.productDao.selectList(pageNo, pageSize)) {
    //for (Product product : ContextLoaderListener.productDao.selectList(pageNo, pageSize)) {
    
    // ProductDao를 ServletContext 보관소에서 꺼내는 방식을 사용
    // => 단점: 위의 방식보다 코드가 늘었다.
    // => 장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
    DbmDao dbmDao = (DbmDao)this.getServletContext()
                                         .getAttribute("DbmDao");
    for (Dbm dbm : dbmDao.selectList(pageNo, pageSize)) {
      out.println("<tr>");
      out.println("  <td>" + dbm.getName() + "</td>");
      out.println("  <td>" + dbm.getUrl() + "</td>");
      out.println("  <td>" + dbm.getInfo() + "</td>");
      out.println("  <td>" + dbm.getMemo() + "</td>");
      out.println("</tr>");
    }
    out.println("</table>");
    out.println("</div>");
    
    out.println("<script src='../../js/jquery-1.11.1.js'></script>");
    
    //다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
    //rd = request.getRequestDispatcher("/common/footer");
   // rd.include(request, response);
    
    out.println("</body>");
    out.println("</html>");
    System.out.println("service() 실행 완료");
  }
  
}












