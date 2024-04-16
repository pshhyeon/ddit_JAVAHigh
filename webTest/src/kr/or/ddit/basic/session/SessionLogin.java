package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId"); // JSP요소의 'name'값
		String userPass = request.getParameter("userPass"); 

		HttpSession session = request.getSession();
		
		if ("admin".equals(userId) && "1234".equals(userPass)) { // 로그인 성공시
			session.setAttribute("loginId", userId);
		} 
		
		// sessionLogin.jsp로 이동하기
		response.sendRedirect(request.getContextPath() + "/session/sessionLogin.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
