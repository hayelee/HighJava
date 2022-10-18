package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardDaoImpl implements IBoardDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertBoard(BoardVO mv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection(); // 커넥션 가져오기
			
			String sql = " insert into JDBC_BOARD " 
			+ " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, BOARD_DATE)"
			+ " values (board_seq.nextVal, ?, ?, ?, sysdate)"; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getBoardTitle());
			pstmt.setString(2, mv.getBoardWriter());
			pstmt.setString(3, mv.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(String bordWriter) {
		
		boolean exist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt " 
			+ " FROM JDBC_BOARD " 
			+ " where BOARD_WRITER = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bordWriter);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) { // 한 번 도니까 while 안쓰고 if 써도 됨
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				exist = true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return exist;
	}

	@Override
	public int updateBoard(BoardVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " update JDBC_BOARD" 
					     + " set BOARD_TITLE = ?," 
					     + "     BOARD_CONTENT = ?" 
					     + " where BOARD_WRITER = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getBoardTitle());
			pstmt.setString(2, mv.getBoardContent());
			pstmt.setString(3, mv.getBoardWriter());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String memId) {
	
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from JDBC_BOARD where BOARD_WRITER = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> memList = new ArrayList<BoardVO>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from JDBC_BOARD";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				BoardVO mv = new BoardVO();
				mv.setBoardNum(rs.getString("BOARD_NO"));
				mv.setBoardTitle(rs.getString("BOARD_TITLE"));
				mv.setBoardContent(rs.getString("BOARD_CONTENT"));
				mv.setBoardWriter(rs.getString("BOARD_WRITER"));
				mv.setBoardDate(rs.getDate("BOARD_DATE"));
				
				
				memList.add(mv); // List에 MemberVO객체 담기
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO mv){
		List<BoardVO> memList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT from JDBC_BOARD where 1=1"; // and를 붙일지 말지 고민하기 싫어서 1=1 써줌
			sql += " and (board_title like '%"+mv.getKeyWord()+"%' or board_writer like '%"+mv.getKeyWord()+"%' or board_content like '%"+mv.getKeyWord()+"%')";
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; 

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO mv2 = new BoardVO();
				mv2.setBoardNum(rs.getString("BOARD_NO"));
				mv2.setBoardTitle(rs.getString("BOARD_TITLE"));
				mv2.setBoardContent(rs.getString("BOARD_CONTENT"));
				mv2.setBoardWriter(rs.getString("BOARD_WRITER"));
				mv2.setBoardDate(rs.getDate("BOARD_DATE"));;
				
				memList.add(mv2); // List에 MemberVO객체 담기
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		
		return memList;
	}

}
