package kr.or.ddit.fileupload.service;

import java.util.List;

import kr.or.ddit.fileupload.dao.FileinfoDaoImpl;
import kr.or.ddit.vo.FileInfoVO;

public class FileinfoServiceImpl implements IFileinfoService {
	private FileinfoDaoImpl dao;
	private static FileinfoServiceImpl service;
	private FileinfoServiceImpl() {
		dao = FileinfoDaoImpl.getInstance();
	}
	
	public static FileinfoServiceImpl getInstance() {
		return service = service == null ? new FileinfoServiceImpl() : service;
	}
	
	@Override
	public int insertFileinfo(FileInfoVO fileVO) {
		return dao.insertFileinfo(fileVO);
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		return dao.getAllFileinfo();
	}

	@Override
	public FileInfoVO getFileinfo(int file_no) {
		return dao.getFileinfo(file_no);
	}

}
