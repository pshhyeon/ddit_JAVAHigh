package kr.or.ddit.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB서버와 연결해서 SQL문을 수행하여 결과를 작성하여 Service에게 전달하는 DAO의 interface
 * 
 * DAO의 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.
 * 
 * 
 * @author PC-08
 *
 */
public interface IMemberDao {

	/**
	 * MemberVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업 성공 : 1, insert 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVo);

	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return delete 작업 성공 : 1, delete 작업 실패 : 0
	 */
	public int deleteMember(String memId);

	/**
	 * 매개변수로 받은 MemberVo객체를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo update할 회원정보가 저장된 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO memVo);

	/**
	 * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return 전체 MemberVO객체가 저장된 List
	 */
	public List<MemberVO> getAllMember();

	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 ID의 개수를 반환하는 매서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 */
	public int getMemberIdCount(String memId);

	/**
	 * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 * key값 정보 ==> 회원ID(memID), 컬럼명(field), 데이터값(data)
	 * 
	 * @param paramMap 수정할 회원ID, 수정할 컬럼명, 수정할 데이터가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);
}
