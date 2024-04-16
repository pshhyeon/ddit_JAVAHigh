package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST방식으로 전달되는 데이터의 문자 인코딩 방식 지정
		request.setCharacterEncoding("utf-8");
		
		// request객체의 getParameter()메서드를 이용하여 값 구하기
		// 형식) request객체.getParameter("파라미터명");
		// ==> 해당 파라미터에 설정된 '값'을 가져온다.('값'은 tag의 value속성이다.)
		// ==> 가져오는'값'의 자료형은 'String'이다.('파라미터명'은 tag의 name속성이다.)
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// request객체의 getParameterValues()메서드 이용하기
		// 형식) request객체.getParameterValues("파라미터명");
		// ==> '파라미터명'이 같은 것이 여러개 일 경우 에 사용한다.
		// ==> 가져오는 데이터의 자료형은 'String[]'이다.
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Request객체연습</title></head>");
		out.println("<body>");
		out.println("<h2>Request객체 데이터 가져오기</h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>이 름</td>");
		out.println("<td>" + userName + "</td></tr>");
		out.println("<tr><td>직 업</td>");
		out.println("<td>" + job + "</td></tr>");
		out.println("<tr><td>취 미</td>");
		out.println("<td>");
		if (hobbies != null) {
			for (String hobby : hobbies) {
				out.println(hobby + "<br>");
			}
		}
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<br><hr><br>");
		out.println("<h2>Request객체 제공 메서드들</h2>");
		out.println("<ol>");
		out.println("<li>클라이언트 IP주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li>요청 메서드 : " + request.getMethod() + "</li>");
		out.println("<li>Context Path : " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li>URL 정보 : " + request.getRequestURL() + "</li>");
		out.println("<li>URI 정보 : " + request.getRequestURI() + "</li>");
		out.println("</ol>");
		
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
