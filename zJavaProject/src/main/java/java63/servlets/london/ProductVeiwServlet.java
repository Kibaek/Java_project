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

@WebServlet("/london/bookmark/view")
public class ProductVeiwServlet extends GenericServlet {
private static final long serialVersionUID = 1L;

@Override
public void service(ServletRequest request, ServletResponse response)
throws ServletException, IOException {
 	
 	int dno = Integer.parseInt(request.getParameter("dno"));
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
out.println("<link rel='stylesheet' href='../../css/bootstrap.min.css'>");
out.println("<link rel='stylesheet' href='../../css/bootstrap-theme.min.css'>");
out.println("<link rel='stylesheet' href='../../css/common.css'>");
out.println("<script src='../../js/jquery-1.11.1.js'></script>");
out.println("<script src='../../js/bootstrap.min.js'></script>");
out.println("</head>");
out.println("<body>");
out.println("<div class='container'>");

 	out.println("<div class='modal fade' id='viewModal' tabindex='-1' role='dialog'	aria-labelledby='myModalLabel' aria-hidden='true'>");
	out.println("	<div class='modal-dialog'>");
	out.println("		<div class='modal-content'>");
	out.println("			<div class='modal-header'>");
	out.println("				<button type='button' class='close' id='btnClose'>");
	out.println("					<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>");
	out.println("				</button>");
	out.println("				<h3 class='modal-title' id='myModalLabel'>Bookmark_View</h3>");
	out.println("			</div>");

	out.println("			<div class='modal-body'>");

	out.println("				<form class='form-horizontal' role='form' action='update'	method='post'>");

	out.println("<div class='form-group'>");
out.println(" <div class='col-sm-10' style='display:none'>");
out.println(" <input type='text' class='form-control' readonly ");
out.println(" id='dno' name='dno' value='" + product.getDno() + "'>");
out.println(" </div>");
out.println("</div>");

out.println("<div class='form-group'>");
out.println(" <label for='name' class='col-sm-2 control-label'>Site_Name</label>");
out.println(" <div class='col-sm-10'>");
out.println(" <input type='text' class='form-control' ");
out.println(" id='name' name='name' value='" + product.getName() + "'>");
out.println(" </div>");
out.println("</div>");

out.println("<div class='form-group'>");
out.println(" <label for='url' class='col-sm-2 control-label'>URL</label>");
out.println(" <div class='col-sm-10'>");
out.println(" <input type='text' class='form-control' ");
out.println(" id='url' name='url' value='" + product.getUrl() + "'>");
out.println(" </div>");
out.println("</div>");

out.println("<div class='form-group'>");
out.println(" <label for='info' class='col-sm-2 control-label'>Version</label>");
out.println(" <div class='col-sm-10'>");
out.println(" <input type='text' class='form-control' ");
out.println(" id='info' name='info' value='" + product.getInfo() + "'>");
out.println(" </div>");
out.println("</div>");

out.println("<div class='form-group'>");
out.println(" <label for='memo' class='col-sm-2 control-label'>Comment</label>");
out.println(" <div class='col-sm-10'>");
out.println(" <input type='text' class='form-control' ");
out.println(" id='memo' name='memo' value='" + product.getMemo() + "'>");
out.println(" </div>");
out.println("</div>");

	out.println("			</div>");
	out.println("				<div class='modal-footer'>");
	out.println("					<button id='btnUpdate' type='submit' class='btn btn-primary'>Update</button>");
	out.println("					<button id='btnDelete' type='button' class='btn btn-primary'>Delete</button>");
	out.println("					<button id='btnCancel' type='button' class='btn btn-default'>Cancel</button>");
	out.println("				</div>");
	out.println("			</form>");
	out.println("		</div>");
	out.println("	</div>");
	out.println("</div>");
//out.println("<script src='../../js/jquery-1.11.1.js'></script>");
out.println("<script>");
out.println("$('#viewModal').modal('show')");
out.println("  $('#btnCancel').click(function(){");
out.println("    history.back();");
out.println("  });");
out.println("  $('#btnClose').click(function(){");
out.println("    history.back();");
out.println("  });");
out.println(" $('#btnDelete').click(function(){");
out.println(" if (window.confirm('삭제하시겠습니까?')) {");
out.println(" location.href = 'delete?dno=" + product.getDno() + "'");
out.println(" }");
out.println(" });");
out.println(" $('#btnUpdate').click(function(){");
out.println(" if ( $('#name').val().length == 0) {");
out.println(" alert('제품명은 필수 입력 항목입니다.');");
out.println(" return false;");
out.println(" }");
out.println(" if ( $('#url').val().length == 0) {");
out.println(" alert('수량은 필수 입력 항목입니다.');");
out.println(" return false;");
out.println(" }");
out.println(" });");
out.println("</script>");
// 다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
out.println("</body>");
out.println("</html>");
}
}
