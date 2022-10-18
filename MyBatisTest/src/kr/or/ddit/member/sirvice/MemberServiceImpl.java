package kr.or.ddit.member.sirvice;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.dao.MemberDaoImplForJDBC;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService { 
	
	// 나 자신을 담기 위한 멤버변수
	private static IMemberService memService;
	
	private IMemberDao memDao;
	
	// 생성자는 public->private으로 바꾼다
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	// 나 자신의 타입을 IMemberService으로 리턴
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	@Override
	public int registMember(MemberVO mv) {
		
		int cnt = memDao.insertMember(mv);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memID) {
		
		boolean exist = memDao.checkMember(memID);
		
		return exist;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		
		int cnt = memDao.updateMember(mv);
		
		return cnt;
	}

	@Override
	public int removeMember(String memId) {

		int cnt = memDao.deleteMember(memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		
		List<MemberVO> memList = memDao.selectAllMember();
		
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = memDao.searchMember(mv);
		
		return memList;
	}
}
