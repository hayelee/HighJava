package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.comm.dao.MyBaitsDao;

public class BoardDaoImpl extends MyBaitsDao implements IBoardDao {

	private static IBoardDao memDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(memDao == null) {
			memDao = new BoardDaoImpl();
		}
		return memDao;
	}
	
	@Override
	public int insertBoard(BoardVO mv) {

		return insert("board.insertBoard", mv);
	}

	@Override
	public boolean checkBoard(int boardNum) {
		
		boolean isExist = false;
		
		int cnt = selectOne("board.checkBoard", boardNum);
		
		if(cnt > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public int updateBoard(BoardVO mv) {

		return update("board.updateBoard", mv);
	}

	@Override
	public int deleteBoard(int boardNum) {

		return delete("board.deleteBoard", boardNum);
	}

	@Override
	public List<BoardVO> selectAllBoard() {

		return selectList("board.selectAllBoard", null);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO mv) {

		return selectList("board.searchBoard", mv);
	}
}
