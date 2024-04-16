package kr.or.ddit.fileupload.dao;

import java.util.List;

import kr.or.ddit.vo.FileInfoVO;

public interface IFileinfoDao {
	/**
	 * FileInfoVO객체에 저장된 자료를 DB에 insert하는 메서드
	 * 
	 * @param fileVO insert할 자료가 저장된 FileInfoVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertFileinfo(FileInfoVO fileVO);
	
	/**
	 * 전체 파일 정보 목록을 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return 전체 파일 정보 목록이 저장된 List객체
	 */
	public List<FileInfoVO> getAllFileinfo();
	
	/**
	 * 파일 번호를 인수로 받아서 해당 파일 정보를 찾아서 반환하는 메서드
	 * 
	 * @param file_no 검색할 파일 번호
	 * @return 검색된 파일 정보가 저장된FileInfoVO객체
	 */
	public FileInfoVO getFileinfo(int file_no);
}
