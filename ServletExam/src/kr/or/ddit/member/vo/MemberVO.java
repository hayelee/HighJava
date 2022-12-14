package kr.or.ddit.member.vo;

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
public class MemberVO { // 회원에 대한 세부데이터 정보를 하나의 객체에 담아서 관리할 것
	
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	
	private Date regDate;

	private long atchFileId = -1; // 첨부파일Id->-1이면 첨부되지 않았다는 
	
	
	public long getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ ", regDate=" + regDate + "]";
	}
}
