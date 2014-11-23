package java63.servlets.london;

import java.io.IOException;
import java.io.PrintWriter;
import java63.servlets.london.dao.ProductDao;
import java63.servlets.london.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test04/product/view")
public class ProductViewServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
  	
  	int dno = Integer.parseInt(request.getParameter("dno"));
    //Product product = AppInitServlet.productDao.selectOne(no);
    //Product product = ContextLoaderListener.productDao.selectOne(no);
    
    // ProductDao를 ServletContext 보관소에서 꺼내는 방식을 사용
    // => 단점: 위의 방식보다 코드가 늘었다.
    // => 장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
    ProductDao productDao = (ProductDao)this.getServletContext()
                                         .getAttribute("productDao");
    Product product = productDao.selectOne(dno);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    
    // 다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
    RequestDispatcher rd = 
        request.getRequestDispatcher("/common/header");
    rd.include(request, response);
    
    out.println("</head>");
    out.println("<body>");
    out.println("<div class='container'>");
    out.println("<h1>제품 정보</h1>");
    
    
    
    out.println("<form class='form-horizontal' role='form' "
        + "action='update' method='post'>");
    out.println("<div class='form-group'>");
    out.println("  <div class='col-sm-10' style='display:none'>");
    out.println("    <input type='text' class='form-control' readonly ");
    out.println("        id='dno' name='dno' value='" + product.getDno() + "'>");
    out.println("  </div>");
    out.println("</div>");
    
    
    
   
    out.println("<div class='form-group'>");
    out.println("  <label for='name' class='col-sm-2 control-label'>이름</label>");
    out.println("  <div class='col-sm-10'>");
    out.println("    <input type='text' class='form-control' readonly ");
    out.println("        id='name' name='name' value='" + product.getName() + "'>");
    out.println("  </div>");
    out.println("</div>");
    
    out.println("<div class='form-group'>");
    out.println("  <label for='url' class='col-sm-2 control-label'>URL</label>");
    out.println("  <div class='col-sm-10'>");
    out.println("    <input type='text' class='form-control' ");
    out.println("        id='url' name='url' value='" + product.getUrl() + "'>");
    out.println("  </div>");
    out.println("</div>");
    
    out.println("<div class='form-group'>");
    out.println("  <label for='info' class='col-sm-2 control-label'>정보</label>");
    out.println("  <div class='col-sm-10'>");
    out.println("    <input type='text' class='form-control' ");
    out.println("        id='info' name='info' value='" + product.getInfo() + "'>");
    out.println("  </div>");
    out.println("</div>");
    
    out.println("<div class='form-group'>");
    out.println("  <label for='memo' class='col-sm-2 control-label'>메모</label>");
    out.println("  <div class='col-sm-10'>");
    out.println("   <input type='text' class='form-control' ");
    out.println("        id='memo' name='memo' value='" + product.getMemo() + "'>");
    out.println("  </div>");
    out.println("</div>");
    
    out.println("<div class='form-group'>");
    out.println("  <div class='col-sm-offset-2 col-sm-10'>");
    out.println("    <button id='btnUpdate' type='submit' class='btn btn-primary'>변경</button>");
    out.println("    <button id='btnDelete' type='button' class='btn btn-primary'>삭제</button>");
    out.println("    <button id='btnCancel' type='button' class='btn btn-primary'>취소</button>");
    out.println("  </div>");
    out.println("</div>");
    
    out.println("</form>");
    out.println("</div>");
    
    out.println("<script src='../../js/jquery-1.11.1.js'></script>");
    
    out.println("<script>");
    out.println("  $('#btnCancel').click(function(){");
    out.println("    history.back();");
    out.println("  });");
    
    out.println("  $('#btnDelete').click(function(){");
    out.println("    if (window.confirm('삭제하시겠습니까?')) {");
    out.println("      location.href = 'delete?dno=" +  product.getDno() + "'");
    out.println("    }");
    out.println("  });");
    
    out.println("  $('#btnUpdate').click(function(){");
    out.println("    if ( $('#name').val().length == 0) {");
    out.println("      alert('제품명은 필수 입력 항목입니다.');");
    out.println("      return false;");
    out.println("    }");
        
    out.println("    if ( $('#url').val().length == 0) {");
    out.println("      alert('수량은 필수 입력 항목입니다.');");
    out.println("      return false;");
    out.println("    }");
    out.println("  });");
        
 
    
    out.println("</script>");
    
    // 다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
    rd = request.getRequestDispatcher("/common/footer");
    rd.include(request, response);
    
    out.println("</body>");
    out.println("</html>");
  }
  
}












