package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.LprodVO;

/**
 * Servlet implementation class JsonTest
 */
@WebServlet("/jsonTest.do")
public class JsonTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 나한테 온거 이렇게 읽기
		response.setCharacterEncoding("utf-8"); // 보낼거 이렇게 보내줘

		// JSON으로 응답할 때의 contentType설정
		response.setContentType("application/html; charset=utf-8");

		// 파라미터 받기
		String choice = request.getParameter("choice");

		// gson객체 생성(라이브러리 필요)
		Gson gson = new Gson();

		// JSON구조의 문자열이 저장될 변수
		String jsonStr = null;

		switch (choice) {
		case "string":
			String str = "안녕하세요";
			jsonStr = gson.toJson(str);
			break;
		case "array":
			int[] arr = { 10, 20, 30, 40, 50 };
			jsonStr = gson.toJson(arr);
			break;
		case "object":
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(100);
			lvo.setLprod_gu("P900");
			lvo.setLprod_nm("모니터");
			jsonStr = gson.toJson(lvo);
			break;
		case "list":
			List<LprodVO> lprodList = new ArrayList<LprodVO>();

			LprodVO lvo1 = new LprodVO();
			lvo1.setLprod_id(101);
			lvo1.setLprod_gu("P901");
			lvo1.setLprod_nm("마우스");

			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(102);
			lvo2.setLprod_gu("P902");
			lvo2.setLprod_nm("키보드");

			LprodVO lvo3 = new LprodVO();
			lvo3.setLprod_id(103);
			lvo3.setLprod_gu("P903");
			lvo3.setLprod_nm("컴퓨터");

			lprodList.add(lvo1);
			lprodList.add(lvo2);
			lprodList.add(lvo3);

			jsonStr = gson.toJson(lprodList);
			break;
		case "map":
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "홍길동");
			map.put("tel", "010-1111-1111");
			map.put("addr", "대전");

			jsonStr = gson.toJson(map);
			break;
		}
		System.out.println("choice => " + choice);
		System.out.println("jsonStr => " + jsonStr);

		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		
		// 요청한 곳으로 보내주는..?
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
