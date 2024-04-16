package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;

/*
 	- Servlet 3.0이상에서 파일 업로드를 처리하려면 서블릿에 @MultipartConfig 애노테이션을 설정해야 한다.
 	
 	- MultipartConfig 애노테이션에 설정할 변수들...
 	  1) location : 업로드한 파일이 임시로 저장될 경로 지정(기본값 : "")
 	  2) fileSizeThreshold : 이 곳에 지정한 값보다 큰 파일이 전송되면 임시 경로에 파일을 저장한다.(단위 : byte,  기본값 : 0 (무조건 임시 경로에 저장한다.))
 	  3) maxFileSize : 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1L (무제한))
 	  4) maxRequestSize : 서버로 전송되는 전체 Request데이터의 최대 크기 (단위 : byte,  기본값 : 0 (무조건 임시 경로에 저장한다.))
 	  													  (모든 파일크기 + formData)
 	  5)
 */

@WebServlet("/fileupload/fileupload.do")
@MultipartConfig(
		fileSizeThreshold = 1024*1024, // KB*KB = MB
		maxFileSize = 1024*1024*30, // 30MB
		maxRequestSize = 1024*1024*50 // 50MB
)
public class Fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드 폼으로 이동
		request.getRequestDispatcher("/fileupload/fileuploadForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드 처리
		request.setCharacterEncoding("utf-8");
		
		// 업로드된 파일들이 저장될 폴더 설정
		String uploadPath = "d:/D_Other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 준다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 데이터들은 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터(username) : + userName");
		
		// ---------------------------------------------------------------------------------------------
		// 파일데이터 처리하기
		
		/*
		- Servlet 3.0이상에서 새롭게 추가된 Upload용 메서드
		1) Request객체.getParts(); - 전체 Part를 다 가져옴
			==> 전체 Part객체를 Collection객체에 담아서 반환한다.
		2) Request객체.getPart("part이름"); - 개별 Part를 가져옴
			==> 지정된 'part이름'을 가진 개별 Part객체를 반환한다.
			==> 'part이름'은 <form>태그 안의 입력 요소의 name 속성 값을 구별한다.
		 */
		
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
		
		// 전체 Part객체의 개수만큼 반복 처리
		for (Part part : request.getParts()) {
			// Upload한 파일명 구하기
			String fileName = extractFileName(part);
			
			// 찾은 파일명이 빈문자열("")이면 이것은 파일이 아니라 일반 데이터란 의미이다
			if (!"".equals(fileName)) { // 파일여부 검사
				// 파일 정보를 저장할 VO객체 생성
				FileInfoVO fileVO = new FileInfoVO();
				
				fileVO.setFile_writer(userName); // 작성자를 VO에 저장
				fileVO.setOrigin_file_name(fileName); // 원래의 파일명을 VO에 저장

				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서
				// UUID클래스를 이용하여 저장할 파일명을 만든다
				String saveFileName = UUID.randomUUID().toString() + "_" + fileName;  
				
				// 이 저장 파일명을 VO에 저장
				fileVO.setSave_file_name(saveFileName);
				
				// upload된 파일의 크기는 Part객체의 getSize()메서드로 구할 수 있다
				fileVO.setFile_size(part.getSize());
				
				try {
					// Part객체의 write()메서드로 파일을 저장한다.
					part.write(uploadPath + File.separator + saveFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				fileList.add(fileVO); // Upload된 파일 정보 List에 추가하기
				
			} // /if
			
		} // /for
		
		// Upload된 파일 정보를 DB에 추가하기
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		// List에 저장된 데이터를 DB에 저장한다.
		for (FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		// 작업이 완료된 후 파일목록을 보여준다.
		response.sendRedirect(request.getContextPath() + "/fileupload/fileList.do");
		
	} // doPost()메서드 끝...
	
	// ---------------------------------------------------------------------------------------------
	
	/*
	 - Part의 구조
	 1) 파일이 아닌 일반 데이터일 경우
	 ----------------------strstrstrstrstrstrstrstrstrstrstrstrstr 				==> Part를 구분하는 구분선
헤더>	 content-disposition : form-data; name="username"							==> 파라미터 명
																				==> 빈줄
	 text																		==> 데이터
	 
	 ===============================================================================================
	 
	 2) 피일일 경우
	 ----------------------strstrstrstrstrstrstrstrstrstrstrstrstr				==> Part를 구분하는 구분선
헤더>	 content-disposition: form-data; name="upFile1"; filename="test1.txt"		==> 파라미터 명
	 content-type: text/plain	==> 파일종류
																				==> 빈줄
	 abc1234hello																==> 파일 내용
	 */
	// ---------------------------------------------------------------------------------------------

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
