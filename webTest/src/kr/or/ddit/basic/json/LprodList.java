package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

@WebServlet("/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// JSON으로 응답할 때의 contentType설정
		response.setContentType("application/html; charset=utf-8");
		
		// gson객체 생성(라이브러리 필요)
		Gson gson = new Gson();;
		
		// JSON구조의 문자열이 저장될 변수
		String jsonStr = null;
		
		ILprodService service = LprodServiceImpl.getInstance();
		
		List<LprodVO> lprodList = service.getAllLprod();
		
		jsonStr = gson.toJson(lprodList);
		
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		
		// 요청한곳으로 보냄
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
