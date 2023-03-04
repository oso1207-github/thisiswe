package com.thisiswe.home.place.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thisiswe.home.place.reservation.dto.PlaceReservationDTO;
import com.thisiswe.home.place.reservation.service.PlaceReservationService;
import com.thisiswe.home.user.entity.UserEntity;
import com.thisiswe.home.user.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/thisiswe/place")
@Log4j2
@RequiredArgsConstructor

public class ReservationController {

	@Autowired
	private PlaceReservationService plasceReservationService;

	// 예약 페이지 연결
	@GetMapping({ "/reservation" })
	public String reservation(Long placeZoneNum, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		log.info("=== reservation() ===");
		
		log.info("장소번호 : "  + placeZoneNum);
		plasceReservationService.getPlaceNumToZoneNumToReservationList();// 예약현황 불러오기

		model.addAttribute("userId", userDetailsImpl.getUsername());// 접속중 유저 ID
		model.addAttribute("userNickname", userDetailsImpl.getUserNickname());// 접속중 유저 닉네임

		log.info("=== /reservation() ===");

		return "reservation/reservation_register";
	}

	// 예약 등록 처리
	@PostMapping({ "/reservation" })
	public String reservationRegister(PlaceReservationDTO placeReservationDTO) {
		log.info("=== reservationRegister() ===");
		log.info("=== 사용자 입력 정보 placeReservationDTO : " + placeReservationDTO);

		// 예약 내용 체크
		Boolean result = plasceReservationService.getCheck(placeReservationDTO);

		if (result) {
			// 등록
			log.info("예약시 받아온 placeReservationDTO 값 : " + placeReservationDTO);
			plasceReservationService.register(placeReservationDTO);
			log.info("=== /reservationRegister() ===");
			return "redirect:/thisiswe/place/reservation";
		} else {

			log.info("=== /reservationRegister() ===");
			return "redirect:/thisiswe/place/reservation";
		}

	}

	// 예약 중복 확인
	@PostMapping({ "/reservationCheck" })
	public ResponseEntity<Boolean> reservationCheck(@RequestBody PlaceReservationDTO placeReservationDTO) {
		log.info("예약날짜 정보 시간 :" + placeReservationDTO);
		Boolean result = plasceReservationService.getCheck(placeReservationDTO);
		log.info("예약 가능 여부 : " + result);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	// 예약 수정 페이지
	@GetMapping({ "/reservation/modify" })
	public String reservationModifyPage(Model model, Long reservationNum) {
		log.info("=== reservationModifyPage() ===");

		PlaceReservationDTO placeReservationDTO = plasceReservationService.getRead(reservationNum);
		log.info("불러온 예약 DTO 정보 " + placeReservationDTO);
		model.addAttribute("placeReservationDTO", placeReservationDTO);

		log.info("=== /reservationModifyPage() ===");

		return "reservation/reservation_modify";
	}

	// 예약 상세 페이지
	@GetMapping({ "/reservation/read" })
	public String reservationReadPage(Model model, Long reservationNum) {
		log.info("=== reservationReadPage() ===");

		PlaceReservationDTO placeReservationDTO = plasceReservationService.getRead(reservationNum);
		log.info("불러온 예약 DTO 정보 " + placeReservationDTO);
		model.addAttribute("placeReservationDTO", placeReservationDTO);

		log.info("=== /reservationReadPage() ===");

		return "reservation/reservation_read";
	}

	// 예약 변경 처리
	@PostMapping({ "/reservation/modify" })
	public String reservationModify(PlaceReservationDTO placeReservationDTO,@AuthenticationPrincipal UserDetails userDetails) {
		log.info("=== post reservationModify ===");
		//placeReservationDTO.setPlaceReservationTimeStart(placeReservationDTO.getPlaceReservationTimeStart().substring(0,2));
		//placeReservationDTO.setPlaceReservationTimeEnd(placeReservationDTO.getPlaceReservationTimeEnd().substring(0,2));
		placeReservationDTO.setUserId(UserEntity.builder().userId(userDetails.getUsername()).build());
		log.info("예약 수정요청 DTO 내용 : " + placeReservationDTO);
		plasceReservationService.modify(placeReservationDTO);
		log.info("=== /post reservationModify ===");
		return "redirect:/thisiswe/place/reservation";
	}
}
