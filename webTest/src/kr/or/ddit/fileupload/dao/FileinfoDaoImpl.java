package kr.or.ddit.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.FileInfoVO;

public class FileinfoDaoImpl implements IFileinfoDao {
	
	private static FileinfoDaoImpl dao;
	
	private FileinfoDaoImpl() { }
	
	public static FileinfoDaoImpl getInstance() {
		return dao = dao == null ? new FileinfoDaoImpl() : dao;
	}
	
	@Override
	public int insertFileinfo(FileInfoVO fileVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVO);
			if (cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		SqlSession session = null;
		List<FileInfoVO> filelist = null;
		try {
			session = MybatisUtil.getSqlSession();
			filelist = session.selectList("fileinfo.getAllFileinfo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return filelist;
	}

	@Override
	public FileInfoVO getFileinfo(int file_no) {
		SqlSession session = null;
		FileInfoVO fileVO = null;
		try {
			session = MybatisUtil.getSqlSession();
			fileVO = session.selectOne("fileinfo.getFileinfo", file_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return fileVO;
	}

}
