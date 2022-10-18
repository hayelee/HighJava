package kr.or.ddit.board.vo;

import java.util.Date;

/**
 * DB 테이블에 존재하는 컬럼이름을 기준으로 데이터를 객체화한 클래스
 * @author PC-09
 * 
 * <p>
 *	DB 테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.
 * </p>
 *
 */
public class BoardVO { // 게시글에 대한 세부데이터 정보를 하나의 객체에 담아서 관리할 것
	
	private int boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String keyWord;
	private Date boardDate;
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", keyWord=" + keyWord + ", boardDate=" + boardDate + "]";
	}
}
