package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 컨트롤러의 요청을 받아 처리하는 서비스의 Interface.
 * @author PC-09
 */
// 기능을 정의
public interface IBoardService {

	   /**
	    * 회원등록 하기 위한 메서드
	    * @param mv DB에 등록할 데이터가 저장된BoardVO객체
	    * @return 회원등록이 성공하면 1이상의 값이 반환됨
	    */
	   public int registBoard(BoardVO mv);
	   
	   /**
	    * 주어진 글이 존재하는 지 여부를 알아내기 위한 메서드
	    * @param boardNum 확인대상 글 번호
	    * @return 해당 글 번호가 있으면 true, 없으면 false
	    */
	   public boolean checkBoard(int boardNum);
	   
	   /**
	    * 글을 수정하기 위한 메서드
	    * @param mv update할 글 정보가 들어있는 BoardVO
	    * @return 작업성공 : 1 ,작업실패 : 0
	    */
	   public int modifyBoard(BoardVO mv);
	   
	   /**
	    * 글 번호를 매개변수로 받아서 해당 회원 정보를 삭제하는 메세드
	    * @param boardNum 삭제할 글 번호
	    * @return 작업성공: 1, 작업실패: 0
	    */
	   public int removeBoard(int boardNum);

	   /**
	    * 전체 회원을 조회하기 위한 메서드
	    * @return 회원 정보를 담고 있는 List타입의 객체
	    */
	   public List<BoardVO> selectAllBoard();
	   
	   /**
	    * MemberVO에 담긴 데이터를 이용하여 회원정보를 검색하는 메서드
	    * @param mv 회원정보를 검색하기 위한 데이터
	    * @return 검색된 결과를 담고 있는 List타입의 객체
	    */
	   public List<BoardVO> searchBoard(BoardVO mv);

	
	

}
