package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행후 결과를 작성하여 서비스에 전달하는 DAO의 Interface.
 * @author PC-09
 */
public interface IBoardDao {

	   /**
	    * MemberVO에 담겨진 데이터를 DB에 insert하는 메서드
	    * @param mv DB에 insert할 데이터가 저장된 MemberVO객체
	    * @return DB 작업이 성공하면 1이상의 값이 반환됨
	    */
	   public int insertBoard(BoardVO mv);
	   
	   /**
	    * 주어진 번호가 존재하는 지 여부를 알아내기 위한 메서드
	    * @param boardNum 확인대상 글 번호
	    * @return 해당 글 번호가 있으면 true, 없으면 false
	    */
	   
	   public boolean checkBoard(int boardNum);
	   
	   /**
	    * 하나의 MemberVO객체를 이용하여 DB정보를 update하는 메서드
	    * @param mv update할 회원 정보가 들어있는 BoardVO
	    * @return 작업성공 : 1 ,작업실패 : 0
	    */
	   
	   public int updateBoard(BoardVO mv);
	   
	   /**
	    * 글 번호를 매개변수로 받아서 해당 회원 정보를 삭제하는 메세드
	    * @param boardNum 삭제할 글 번호
	    * @return 작업성공: 1, 작업실패: 0
	    */
	   
	   public int deleteBoard(int boardNum);
	   
	   /**
	    * DB에 테이블에 존재하는 전체 회원정보를 조회하기 위한 메서드
	    * @return 회원정보를 담고 있는 List타입의 객체
	    */
	   public List<BoardVO> selectAllBoard();
	
	   /**
	    * MemberVO에 담긴 데이터를 이용하여 회원정보를 검색하는 메서드
	    * @param mv 회원정보를 검색하기 위한 데이터
	    * @return 검색된 결과를 담고 있는 List타입의 객체
	    */
	   public List<BoardVO> searchBoard(BoardVO mv);

	


}
