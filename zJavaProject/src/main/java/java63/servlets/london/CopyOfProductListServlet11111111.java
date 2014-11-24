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

//@WebServlet("/london/bookmark/list")
public class CopyOfProductListServlet11111111 extends GenericServlet {
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
    
    RequestDispatcher rd = 
        request.getRequestDispatcher("/common/header");
    rd.include(request, response);
    
    out.println("<html>");
    out.println("<head>");
    
    //out.println("<meta charset='UTF-8'>");
    out.println("<link rel='stylesheet' href='../../css/bootstrap.min.css'>");
    out.println("<link rel='stylesheet' href='../../css/bootstrap-theme.min.css'>");
    out.println("<link rel='stylesheet' href='../../css/common.css'>");
    out.println("<script src='../../js/jquery-1.11.1.js'></script>");
    out.println("<script src='../../js/bootstrap.min.js'></script>");
    out.println("<script src='../../js/jquery.animate-shadow.js'></script>");
    
    out.println("<title>JD_BookMark</title>");
    out.println("</head>");
    out.println("<body>");
    	out.println("<div class='container'>");
    	out.println("	<header>");
    	out.println("	<div id='headline'>");
    	out.println("		<h1>BookMark</h1>");
    	out.println("		</div>");
    		//	<!-- Add_Button trigger modal -->
    	
    	
    	out.println("		<div id='add'>");
    	out.println("		<button type='button' class='btn btn-primary btn-sm'	data-toggle='modal' data-target='#myModal'>Add_BM</button>");
    	out.println("			</div>");
    	out.println("	</header>");

    	
    	out.println("	<div class='boxWrapper'>");
    	
    	out.println("<center>");
    	out.println("<form method=get action='http://www.google.co.kr/search' target='_blank' >");
    	out.println("  <table bgcolor='#FFFFFF'>");
    	out.println("    <tr>");
    	out.println("      <td><a href='http://www.google.co.kr/'> 구글구글 </a>");
    	out.println("          <input type=text name=q size=50 maxlength=255 value='' />");
    	out.println("          <input type=hidden name=ie value=utf-8 />");
    	out.println("          <input type=hidden name=oe value=utf-8 />");
    	out.println("          <input type=hidden name=hl value=utf-8 />");
    	out.println("          <input type=submit name=btnG value='Google 연동검색' />");
    	out.println("      </td>");
    	out.println("    </tr>");
    	out.println("  </table>");
    	out.println("</form>");
    	out.println("</center>");


    	ProductDao productDao = (ProductDao)this.getServletContext()
          .getAttribute("productDao");

    	for (Product product : productDao.selectList(pageNo, pageSize)) {
    		
    	out.println("		<div class='boxList'>");
    	out.println("			<div class='box' onclick=\"location.href='" + product.getUrl() + "'\" style='cursor:pointer;' onmouseover=\"showLayer('show')\">");
    	out.println("				<h2>" +  product.getName()  + " </h2>");
    	out.println("			</div>");
    	out.println("			<div class='boxDetail'>");
    	out.println("<a href='view?dno=" + product.getDno() + "'> ShowDetail</a>");
    	//out.println("				<button type='button' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#viewModal'>자세히보기</button>");
    	out.println("			</div>");
    	out.println("		</div>");
    			
    	}
    	

    	out.println("	</div>");
    	//	<!-- end of boxWrapper -->
    	//out.println("</div>");
    	// <!-- end of container -->

    //<!-- Add_Modal -->
    	out.println("<div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
    	out.println("<div class='modal-dialog'>");
    	out.println("<div class='modal-content'>");
    	out.println("<div class='modal-header'>");
    	out.println("<button type='button' class='close' data-dismiss='modal'>");
    	out.println("<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>");
    	out.println("</button>");
    	out.println("<h3 class='modal-title' id='myModalLabel'>Bookmark_Add</h3>");
    	out.println("</div>");

    	out.println("<div class='modal-body'>");

    	out.println("<form class='form-horizontal' role='form' action='add'	method='post'>");
    	
    	
  
    	
    	

    	out.println("<div class='form-group'>");
    					out.println("<label for='name' class='col-sm-2 control-label'>Site_Name</label>");
    	out.println("<div class='col-sm-10'>");
    					out.println("<input type='text' class='form-control' id='name' name='name'	placeholder='이름을 입력해주세요. 예) java'>");
    	out.println("</div>");
    	out.println("</div>");
    	out.println("<div class='form-group'>");
    					out.println("<label for='url' class='col-sm-2 control-label'>URL</label>");
    	out.println("<div class='col-sm-10'>");
    					out.println("<input type='text' class='form-control' id='url' name='url' placeholder='주소를 입력해주세요. 예) http://www.java.com'>");
    	out.println("</div>");
    	out.println("</div>");
    	out.println("<div class='form-group'>");
    					out.println("<label for='info' class='col-sm-2 control-label'>Version</label>");
    	out.println("<div class='col-sm-10'>");
    					out.println("<input type='text' class='form-control' id='info' name='info'	placeholder='버전 정보를 입력해주세요. 예) Java SE 8'>");
    	out.println("</div>");
    	out.println("</div>");

    	out.println("<div class='form-group'>");
			    	out.println("<label for='memo' class='col-sm-2 control-label'>Comment</label>");
			    	out.println("<div class='col-sm-10'>");
			    	out.println("<input type='text' class='form-control' id='memo' name='memo' placeholder='메모를 남겨주세요. 예) JDK 7이상부터 switch는 string 가능'>");
    	out.println("</div>");

    	out.println("</div>");
    		//		out.println("</form>");
    	out.println("</div>");

    			//	out.println("<form class='form-horizontal' role='form' action='add' method='post'>");
    	out.println("<div class='modal-footer'>");
    				out.println("<button id='btnAdd' type='submit' class='btn btn-primary'>Add</button>");
    				out.println("<button id='btnCancel' type='button' class='btn btn-default' data-dismiss='modal'>Cancel</button>");
    	out.println("</div>");
    				out.println("</form>");
    	out.println("</div>");
    	out.println("</div>");
    	out.println("</div>");
    	//<!-- end of Add_Modal -->
    	
    	
    //<!-- View_Modal -->
    	


    	



    	out.println("<script>");
    	out.println("$('#btnAdd').click(function() {");
    	out.println("if ($('#name').val().length == 0) {");
    	out.println("alert('이름은 필수 입력 항목입니다.');");
    	out.println("return false;");
    	out.println("}");

    	out.println("if ($('#url').val().length == 0) {");
    	out.println("alert('URL은 필수 입력 항목입니다.');");
    	out.println("return false;");
    	out.println("}");
    	out.println("});");
    	
    	


    out.println("</script>");



    out.println("</body>");
    out.println("</html>");
    System.out.println("service() 실행 완료");
    
    
    
    
    
    
    
    
    
  
    
    
    
    
    
  }
  
}