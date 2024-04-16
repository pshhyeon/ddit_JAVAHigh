package kr.or.ddit.basic.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.member.service.IMyMemberService;
import kr.or.ddit.basic.member.service.MyMemberServiceImpl;
import kr.or.ddit.vo.MyMemberVO;


@WebServlet("/insertMember.do")
@MultipartConfig(
		fileSizeThreshold = 1024*1024, // KB*KB = MB
		maxFileSize = 1024*1024*30, // 30MB
		maxRequestSize = 1024*1024*50 // 50MB
)
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MyMemberVO vo = new MyMemberVO();
		
		// 데이터 있는지 체크해서 없으면 null처리
		vo.setMem_id(request.getParameter("mem_id")); 
		vo.setMem_pass(request.getParameter("mem_pass"));
		vo.setMem_name(request.getParameter("mem_name"));
		vo.setMem_tel(request.getParameter("mem_tel"));
		vo.setMem_addr(request.getParameter("mem_addr"));
		vo.setMem_photo(getFileName(request.getParts()));
		
		/*
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		*/
		
		
		System.out.println("\n\nvo ==>> \n\n" + vo + "\n\n");
		
		IMyMemberService service = MyMemberServiceImpl.getIntance();
		
		int cnt = service.insertMember(vo);
		
		request.setAttribute("cnt", cnt);
//		request.getRequestDispatcher("/view/result.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/myMember/memList.jsp");
	}
	
	
	public String getFileName(Collection<Part> parts) {
		// 업로드된 파일들이 저장될 폴더 설정
		String uploadPath = "d:/D_Other/uploadProfile";
		String fileName = "";
		
		// 저장될 폴더가 없으면 새로 만들어 준다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// 전체 Part객체의 개수만큼 반복 처리
		for (Part part : parts) {
			// Upload한 파일명 구하기
			fileName = extractFileName(part);

			// 찾은 파일명이 빈문자열("")이면 이것은 파일이 아니라 일반 데이터란 의미니다
			if (!"".equals(fileName)) { // 파일여부 검사
				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서
				// UUID클래스를 이용하여 저장할 파일명을 만든다
				fileName = UUID.randomUUID().toString() + "_" + fileName;  
				
				try {
					// Part객체의 write()메서드로 파일을 저장한다.
					part.write(uploadPath + File.separator + fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return fileName;
			} // /if
			
		} // /for
		
		return "";
	}
	
	
	// Part구조 안에서 파일명을 찾는 메서드
	private String extractFileName(Part part) {
		String fileName = "";
		
		// 헤더의 키값이 'content-disposition'인 헤더의 Value값 구하기
		String headerValue = part.getHeader("content-disposition");
		String[] itemArr = headerValue.split(";");
		
		for (String item : itemArr) {
			if (item.trim().startsWith("filename")) { // 'filename' 찾기
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1); // '='두칸 뒤 클자 부터 마지막까지
			} 
		}
		
		return fileName;
	}
	
	
	}
