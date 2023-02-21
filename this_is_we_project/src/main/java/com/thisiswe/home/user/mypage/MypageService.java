package com.thisiswe.home.user.mypage;

import java.util.List;

import com.thisiswe.home.club.board.dto.BoardDTO;
import com.thisiswe.home.club.board.dto.PageRequestDTO;
import com.thisiswe.home.club.board.dto.PageResultDTO;
import com.thisiswe.home.club.board.entity.Board;
import com.thisiswe.home.user.entity.UserEntity;

public interface MypageService {

	// 회원정보 조회
	void getUserInfo(String username);

	// 회원정보 수정
	void modifyUserInfo(MypageDTO mypageDTO);
	
	public List<BoardDTO> getUserBoards(String username);
	
	// TODO [Service] 게시판 - 페이지 목록(list)
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

	public void getUserClubs(String username);

	// 사진을 받는 메서드
	public void insertUserImage(String username);

	//TODO [Service] 게시판 - Entity(DB)에서 DTO(WEB)로
	default BoardDTO entityToBoardDTO(Board board, UserEntity userEntity, Long replyCount) {
		
		//게시판 번호, 카테고리, 제목, 내용, 유저아이디, 수정일, 조회수, 댓글수
		BoardDTO boardDTO = BoardDTO.builder()
							.boardNum(board.getBoardNum())
							.boardCategory(board.getBoardCategory())
							.boardTitle(board.getBoardTitle())
							.boardContent(board.getBoardContent())
							.userId(userEntity.getUserId())
							.regDate(board.getRegDate())
							.updateDate(board.getUpdateDate())
							.boardView(board.getBoardView())
							.replyCount(replyCount.intValue())		//replyCount는 Long보다는 int 타입을 사용하기!
							.build();
												
		return boardDTO;
	}
	
}