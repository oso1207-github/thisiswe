package com.thisiswe.home.place.zone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thisiswe.home.place.reservation.dto.PlaceReservationDTO;
import com.thisiswe.home.place.zone.dto.PlaceZoneDTO;
import com.thisiswe.home.place.zone.dto.PlaceZoneTimepriceDTO;
import com.thisiswe.home.place.zone.service.PlaceZoneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/thisiswe/place/zone")
@Controller
@RequiredArgsConstructor
@Log4j2
public class PlaceZoneController {
	
	private final PlaceZoneService placeZoneService;

	//장소 zone 등록 페이지 연결
	@GetMapping({"/register"})
	public String zone(Long placeNum, Model model) {
		log.info("=== zone() ===");
		
		log.info("placeNum : "+placeNum);
	
		model.addAttribute("placeNum", placeNum);
		
		log.info("=== /zone() ===");
		return "place/place_zone_register";
		
	}
	//zone 등록 처리
	@PostMapping({"/register"})
	public String zoneRegister(PlaceZoneDTO placeZoneDTO, PlaceZoneTimepriceDTO placeZoneTimepriceDTO) {
		log.info("=== zoneRegister() ===");
		log.info("placeZoneDTO : "+placeZoneDTO);
		log.info("placeZoneDTO : "+placeZoneTimepriceDTO);
		placeZoneService.register(placeZoneDTO);
		
		log.info("=== /zoneRegister() ===");
		
		return "redirect:/thisiswe/place/c?num="+placeZoneDTO.getPlaceNum();
	}
	
	//zone 상세 페이지 연결
	@GetMapping({"/read"})
	public String readPage(PlaceZoneDTO placeZoneDTO) {
		log.info("=== readPage() ===");
		log.info(placeZoneDTO);
		//placeZoneService.get(placeZoneDTO);
		
		log.info("=== /readPage() ===");
		return "place/place_zone_read";
	}
}
