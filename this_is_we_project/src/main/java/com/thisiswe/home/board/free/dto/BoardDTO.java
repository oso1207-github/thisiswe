package com.thisiswe.home.board.free.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

//TODO [DTO]Board 게시판
public class BoardDTO {
	
	private Long boardNum;							// 게시글 번호	
	private String boardNategory;					// 게시판 카테고리
	private String boardTitle;						// 게시판 제목
	private String boardContent;					// 게시판 내용
	private String userId;							// 게시판 유저ID
	private LocalDateTime regDate;					// 게시판 등록일
	private LocalDateTime updateDate;				// 게시판 수정일
	private int boardView;							// 게시판 조회수
}
