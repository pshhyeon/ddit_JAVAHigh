package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;


// 서버에 저장된 Image데이터를 응답으로보내는 서블릿
@WebServlet("/images/imageView.do")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 파일 번호를 구한다
		int fileNO = Integer.parseInt(request.getParameter("fileno"));
		
		// 파일 번호를 이용하여 DB에서 해당 파일 정보를 가져온다
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		FileInfoVO fileVO = service.getFileinfo(fileNO);
		
		// 업로드된 파일들이 저장된 폴더 설정
		String uploadPath = "d:/D_Other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 준다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		File imgFile = new File(f, fileVO.getSave_file_name());

		System.out.println(imgFile.exists());
		
		// 이미지파일을 읽어서 전송하는 기능
		if (imgFile.exists()) { // 해당 파일이 있을 때...
			
			// 서버에 저장된 파일을 읽어서 클라이언트로 전송한다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try {
				// 출력용 스트림 객체 생성 ==> Response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(imgFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				while ((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);					
				}
				bout.flush();
				
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e);
			} finally {
				if (bin != null) try { bin.close(); } catch (Exception e) { e.printStackTrace(); }
				if (bout != null) try { bin.close(); } catch (Exception e) { e.printStackTrace(); }
			}
			
		} 
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
