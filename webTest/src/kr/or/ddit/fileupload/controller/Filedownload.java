package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;

@WebServlet("/fileupload/filedownload.do")
public class Filedownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 파일번호를 받는다.
		String strFileNo = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileNo);
		
		// 파일번호를 이용하여 DB에서 해당 파일 정보를 가져온다.
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		FileInfoVO fileVO = service.getFileinfo(fileNo);
		
		// Upload된 파일이 저장된 폴더 설정
		String uploadPath = "d:/D_Other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 준다
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// 다운받을 파일에 대한 File객체 생성 ==> 실제 저장된 파일명을 지정하여 생성한다.
		File downFile = new File(f, fileVO.getSave_file_name());
		
		if (downFile.exists()) { // 파일이 있을때
			// ContentType 설정
			response.setContentType("application/octet-stream; charset=utf-8");
			
			// Response 객체의 Header에 'content-disposition'속성을 설정한다.
			String headKey = "content-disposition";
			
			// 한글일 경우 설정
			String headValue = "attachment; filename*=UTF-8''" 
						+ URLEncoder.encode(fileVO.getOrigin_file_name(), "utf-8").replace("\\+", "%20"); // replace("\\+", "%20") >> 공백을 코드값으로 표시해라
			response.setHeader(headKey, headValue);
			
			// 서버에 저장된 파일을 읽어서 클라이언트로 전송한다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try {
				// 출력용 스트림 객체 생성 ==> Response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(downFile));
				
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
			
		} else { // 파일이 없을 때
			// html로 보낼때 >> text/html
			// json으로 보낼때 application/html
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fileVO.getOrigin_file_name() + "파일이 존재하지 않습니다.</h3>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
