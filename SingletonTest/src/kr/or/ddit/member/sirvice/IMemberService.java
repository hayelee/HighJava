package kr.or.ddit.member.sirvice;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 컨트롤러의 요청을 받아 처리하는 서비스의 Interface.
 * @author PC-09
 */
public interface IMemberService {

	   /**
	    * 회원등록 하기 위한 메서드
	    * @param mv DB에 등록할 데이터가 저장된 MemberVO객체
	    * @return 회원등록이 성공하면 1이상의 값이 반환됨
	    */
	   public int registMember(MemberVO mv);
	   
	   /**
	    * 주어진 회원ID가 존재하는 지 여부를 알아내기 위한 메서드
	    * @param memId 확인대상 회원ID
	    * @return 해당 회원 ID가 있으면 true, 없으면 false
	    */
	   
	   public boolean checkMember(String memId);
	   
	   /**
	    * 회원정보를 수정하기 위한 메서드
	    * @param mv update할 회원 정보가 들어있는 MemberVO
	    * @return 작업성공 : 1 ,작업실패 : 0
	    */
	   
	   public int modifyMember(MemberVO mv);
	   
	   /**
	    * 회원 ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메세드
	    * @param memId 삭제할 회원 ID
	    * @return 작업성공: 1, 작업실패: 0
	    */
	   
	   public int removeMember(String memId);

	   /**
	    * 전체 회원을 조회하기 위한 메서드
	    * @return 회원 정보를 담고 있는 List타입의 객체
	    */
	   public List<MemberVO> selectAllMember();
	   
	   /**
	    * MemberVO에 담긴 데이터를 이용하여 회원정보를 검색하는 메서드
	    * @param mv 회원정보를 검색하기 위한 데이터
	    * @return 검색된 결과를 담고 있는 List타입의 객체
	    */
	   public List<MemberVO> searchMember(MemberVO mv);
	

}
