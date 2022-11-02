package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.comm.dao.MyBaitsDao;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileDaoImpl extends MyBaitsDao implements IAtchFileDao {

	// 나 자신을 저장하기 위한 멤버변수(싱글톤)
	private static IAtchFileDao atchFileDao;
	
	private AtchFileDaoImpl() {

	}
	
	public static IAtchFileDao getInstance() {
		if(atchFileDao == null) {
			atchFileDao = new AtchFileDaoImpl();
		}
		return atchFileDao;
	}
	
	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) {
			
		return insert("atchFile.insertAtchFile", atchFileVO);
	}

	@Override
	public int insertAtchFileDetail(AtchFileVO atchFileVO) {
		
		return insert("atchFile.insertAtchFileDetail", atchFileVO);
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {

		return selectList("atchFile.getAtchFileList", atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		return selectOne("atchFile.getAtchFileDetail", atchFileVO);
	}
}
