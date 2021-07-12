package com.koreait.test1;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.test1.config.BeanConfiguration;
import com.koreait.test1.dao.BoardDAO;
import com.koreait.test1.dto.BoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BeanConfiguration.class})     
public class Test {


	
	@Autowired
	private SqlSession sqlSession;
	
	@org.junit.Test
	public void 추가테스트() {
		
		BoardDTO board = new BoardDTO();
		board.setBWriter("갑돌이");
		board.setBTitle("갑돌이와갑순이");
		board.setBContent("테스트");
		
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int count = boardDAO.insertBoard(board.getBWriter(), board.getBTitle(), board.getBContent());
		
		assertEquals("추가 실패", 1, count);
		
		
		
	}
	
	
	@org.junit.Test
	public void 조회테스트() {
		int bIdx = 9999;
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		BoardDTO result = boardDAO.selectBybIdx(bIdx);
		assertEquals("조회 성공", null, result);
	}
	
	@org.junit.Test
	public void 변경테스트() {
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		BoardDTO board = new BoardDTO();
		int bIdx = 9999;
		 board.setBTitle("갑돌이와갑순이");
		 board.setBContent("테스트");
		int result = boardDAO.updateBoard(board.getBTitle(), board.getBContent(), bIdx);
		assertEquals("변경 실패", 1, result);
	}
	
	@org.junit.Test
	public void 삭제테스트() {
		int bIdx = 9999;
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int result = boardDAO.deleteBoard(bIdx);
		assertEquals("삭제 실패", 1, result);
	}

}