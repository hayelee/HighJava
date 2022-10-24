package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.BoardDaoImplForJDBC;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

// 기능을 구현
public class BoardServiceImpl implements IBoardService { //dao를 가져오기 위한 싱글톤
	
	// 나 자신을 담기 위한 멤버변수
	private static IBoardService boardService;
	
	private IBoardDao boardDao;
	
	// 생성자는 public->private으로 바꾼다
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	// 나 자신의 타입을 IMemberService으로 리턴
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	@Override
	public int registBoard(BoardVO mv) {
		
		int cnt = boardDao.insertBoard(mv);
		
		return cnt;
	}

	@Override
	public boolean checkBoard(int boardNum) {

		boolean exist = boardDao.checkBoard(boardNum);
		
		return exist;
	}

	@Override
	public int modifyBoard(BoardVO mv) {
		
		int cnt = boardDao.updateBoard(mv);
		
		return cnt;
	}

	@Override
	public int removeBoard(int boardNum) {

		int cnt = boardDao.deleteBoard(boardNum);
		
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> boardList = boardDao.selectAllBoard();
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO mv) {
		
		List<BoardVO> boardList = boardDao.searchBoard(mv);
		
		return boardList;
	}
}
