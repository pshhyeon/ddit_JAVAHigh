package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		int sNum1 = Integer.parseInt(num1);
		int sNum2 = Integer.parseInt(num2);
		String oper = request.getParameter("oper");
		
		double result = 0;
		boolean calcOk = true; // 계산 성공 여부가 저장될 변수(계산 실패시 false)
		
		switch (oper) {
		case "+": result = sNum1 + sNum2; break;
		case "-": result = sNum1 - sNum2; break;
		case "*": result = sNum1 * sNum2; break;
		case "/": 
				if(sNum2 != 0) {
					result = (double)sNum1 / sNum2;
				} else {
					calcOk = false;
				}
				 break;
		case "%": 
			if(sNum2 != 0) {
				result = sNum1 % sNum2;
			} else {
				calcOk = false;
			}
			 break;
		}
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>사칙연산</title></head>");
		out.println("<body>");
		out.println("<h2>계산 결과</h2><hr>");
//		out.println("<p>" + num1 + oper + num2 + " = " + result +"</p>");
		if (calcOk) {
			out.printf("<p> %d %s %d = %.2f </p>\n", sNum1, oper, sNum2, result);
		} else {
			out.printf("<p> num1 = %d num2 = %d 연산자 = %s </p>\n", sNum1, sNum2, oper);
			out.println("<p>계산 불가능</p>");
		}
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
