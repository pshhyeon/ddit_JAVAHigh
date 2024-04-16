package kr.or.ddit.basic.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.basic.member.service.IMyMemberService;
import kr.or.ddit.basic.member.service.MyMemberServiceImpl;
import kr.or.ddit.vo.MyMemberVO;

@WebServlet("/myMemberList.do")
public class MyMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// JSON으로 응답할 때의 contentType설정
		response.setContentType("application/html; charset=utf-8");
		
		// 서비스 객체 생성
		IMyMemberService service = MyMemberServiceImpl.getIntance();
		List<MyMemberVO> list = service.getMemberList();
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		// 요청한곳으로 보냄
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
