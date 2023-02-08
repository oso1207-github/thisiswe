package com.thisiswe.home.board.notice.service;

import com.thisiswe.home.board.notice.dto.NoticeDTO;
import com.thisiswe.home.board.notice.dto.PageRequestDTO;
import com.thisiswe.home.board.notice.dto.PageResultDTO;
import com.thisiswe.home.board.notice.entity.Notice;

//TODO [Service] 공지사항
public interface NoticeService {

	//TODO [Service] 공지사항 - 등록(register)
	Long register(NoticeDTO noticeDTO);
	
	//TODO [Service] 공지사항 - 읽기(get)
	NoticeDTO get(Long noticeNum);
	
	//TODO [Service] 공지사항 - 페이지 불러오기(pageResult)	
	PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	//TODO [Service] 공지사항 - 수정(get)
	void modify(NoticeDTO noticeDTO);
	
	//TODO [Service] 공지사항 - 삭제(get)
	void removeWithReplies(Long noticeNum);
	
	//TODO [Service] 공지사항 - DTO에서 Entity로
	default Notice noticeDTOToEntity(NoticeDTO noticeDTO) {
		
		Notice notice = Notice.builder()
						.noticeNum(noticeDTO.getNoticeNum())
						.noticeCategory(noticeDTO.getNoticeCategory())
						.noticeTitle(noticeDTO.getNoticeTitle())
						.noticeContent(noticeDTO.getNoticeContent())
						.noticeView(noticeDTO.getNoticeVew())
						.build();
		
		return notice;
	}
	
	default NoticeDTO entityToNoticeDTO(Notice notice) {
		
		NoticeDTO noticeDTO = NoticeDTO.builder()
								.noticeNum(notice.getNoticeNum())
								.noticeCategory(notice.getNoticeCategory())
								.noticeTitle(notice.getNoticeTitle())
								.noticeContent(notice.getNoticeContent())
								.updateDate(notice.getUpdateDate())
								.build();
				
		
		return null;
	}

}
