package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Session정보 삭제용 서블릿
@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 삭제하기
		
		// 1. Session객체 구하기
		HttpSession session = request.getSession();
		
		// 2. Session 정보 삭제하기
		// 방법1) Session객체의 removeAttribute()메서드를 이용하여 개별적인 Session값 삭제하기
		// 		 형식) Session객체.removeAttribute("key값");
		session.removeAttribute("testSession");
		
		// 방법2) Session객체.invalidate(); ==> 세션 자체를 삭제한다 (세션 ID도 달라진다)
		session.invalidate();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 삭제</title></head>");
		out.println("<body>");
		out.println("<h2>Session데이터 삭제하기</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/session/sessionTest01.jsp'>시작문서로 이동</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
