package kr.or.ddit.basic.json;

import java.util.List;

import kr.or.ddit.vo.LprodVO;

public class LprodServiceImpl implements ILprodService {
	
	private LprodDaoImpl dao;
	
	private static LprodServiceImpl service;
	
	private LprodServiceImpl() { 
		dao = LprodDaoImpl.getInstance();
	}
	
	public static LprodServiceImpl getInstance() {
		return service = service == null ? new LprodServiceImpl() : service;
	}
	
	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}

}
