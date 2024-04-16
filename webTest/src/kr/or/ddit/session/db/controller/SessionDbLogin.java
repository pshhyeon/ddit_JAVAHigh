package kr.or.ddit.session.db.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.session.db.service.ILoginMemberService;
import kr.or.ddit.session.db.service.LoginMemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/sessionDbLogin.do")
public class SessionDbLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// form에서 입력한 ID와 Password를 구한다.
		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");
		
		// 구한 ID와 Password를 VO객체에 저장한다.
		MemberVO paramMemberVO = new MemberVO();
		paramMemberVO.setMem_id(userId);
		paramMemberVO.setMem_pass(userPass);
		
		// DB처리를 위한 Service객체 생성
		ILoginMemberService service = LoginMemberServiceImpl.getInstance();
		
		// ID와 Password에 일차하는 자료를 찾는다
		MemberVO memVO = service.getLoginMember(paramMemberVO);
		System.out.println(memVO);
		// Session 객체 생성
		HttpSession session = request.getSession();
		
		if (memVO != null) { // 로그인 성공시
			session.setAttribute("loginMember", memVO);
		}
		
		response.sendRedirect(request.getContextPath() + "/session/dbLogin/sessionDbLogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
