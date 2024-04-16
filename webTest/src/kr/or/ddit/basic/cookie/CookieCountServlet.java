package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		Cookie[] cookieArr = request.getCookies();
		int cnt = 0;
		if (cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				if ("count".equals(cookie.getName())) {
					String cookieValue = cookie.getValue();
					cnt = Integer.parseInt(cookieValue);
					break;
				}
			}
		}
		cnt++;
		Cookie cntCookie = new Cookie("count", cnt + "");
		response.addCookie(cntCookie);

		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 읽기</title></head>");
		out.println("<body>");
		out.println("<h3>어서오세요 당신은 ");
		out.println(cnt + "번째 방문입니다</h3><hr>");
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>Cookie Count 증가</a>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
