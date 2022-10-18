package kr.or.ddit.board.sirvice;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {

	private IBoardDao memDao;
	
	public BoardServiceImpl() {
		memDao = new BoardDaoImpl();
	}

	@Override
	public int registBoard(BoardVO mv) {
		
		int cnt = memDao.insertBoard(mv);
		
		return cnt;
	}

	@Override
	public boolean checkBoard(String bordWriter) {
		
		boolean exist = memDao.checkBoard(bordWriter);
		
		return exist;
	}

	@Override
	public int modifyBoard(BoardVO mv) {
		
		int cnt = memDao.updateBoard(mv);
		
		return cnt;
	}

	@Override
	public int removeBoard(String bordWriter) {

		int cnt = memDao.deleteBoard(bordWriter);
		
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> memList = memDao.selectAllBoard();
		
		return memList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO mv) {
		
		List<BoardVO> memList = memDao.searchBoard(mv);
		
		return memList;
	}
}
