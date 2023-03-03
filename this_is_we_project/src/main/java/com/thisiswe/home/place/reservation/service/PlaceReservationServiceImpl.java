package com.thisiswe.home.place.reservation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thisiswe.home.place.reservation.dto.PlaceReservationDTO;
import com.thisiswe.home.place.reservation.entity.PlaceReservationEntity;
import com.thisiswe.home.place.reservation.repository.PlaceReservationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlaceReservationServiceImpl implements PlaceReservationService {
	
	private final PlaceReservationRepository placeReservationRepository;

	@Override
	public void register(PlaceReservationDTO placeReservationDTO) {
		log.info("... Reservation register() ...");
		
		
		//placeReservationRepository.save(dtoToEntity(placeReservationDTO));
		
		log.info("... /Reservation register() ...");
	}

	@Override
	public Boolean getCheck(PlaceReservationDTO placeReservationDTO) {
		log.info("... Reservation getCheck() ...");
		log.info("... placeReservationDTO 입력받은 날짜 : "+placeReservationDTO.getPlaceReservationDate());
		log.info("... placeReservationDTO 입력받은 시간 : "+placeReservationDTO.getPlaceReservationTime());
		
		//예약시 사용시간 나열
		int timeStart=Integer.parseInt(placeReservationDTO.getPlaceReservationTimeStart());
		int timeEnd=Integer.parseInt(placeReservationDTO.getPlaceReservationTimeEnd());
		System.out.println(" 시작시간: "+timeStart);
		System.out.println(" 끝시간 : "+timeEnd);
		List<String> time=new ArrayList<>();
		for(int i=timeStart;i<timeEnd+1;i++) {
			if(i<10) {
				time.add("0"+i);
				 System.out.println("추가 : 0"+i);
			}
			else {
				time.add(i+"");
				System.out.println("숫자10이상 추가 : "+i);
			}
		}
		System.out.println("요청한시간 : "+time.toString().substring(1,time.toString().length()-1));
		String reservationTime=time.toString().substring(1,time.toString().length()-1);
		//log.info("요청한 시간 체크 : "+reservationTime.split(", "));//배열화 함
		
		//날짜를 이용해서 시간 가져오기
		List<PlaceReservationEntity> list=placeReservationRepository.dateToList(placeReservationDTO.getPlaceReservationDate());
		String[] checkTime=reservationTime.split(", ");
		log.info("데이터에 저장되어있는 예약 시간 : "+list);
		log.info("사용자가 요청하는 예약시간 : "+checkTime);
		List<Boolean> times= new ArrayList<>();
		for(int j=0; j<checkTime.length;j++) {
			log.info("예약가능 체크시간 요청 : "+checkTime[j]);
			
			
			for(PlaceReservationEntity arr : list ) {
				
				String[] reservationTimes=arr.getPlaceReservationTime().split(", ");
				
				log.info("개별 값 : "+Arrays.toString(reservationTimes));
				
				if(Arrays.toString(reservationTimes).contains(checkTime[j])) {
					log.info("같은 값이 존재합니다.");
					times.add(Arrays.toString(reservationTimes).contains(checkTime[j]));
				}else {
					log.info("같은 값이 존재하지 않습니다.");
					times.add(Arrays.toString(reservationTimes).contains(checkTime[j]));
				}

//				for(int i=0; i<reservationTimes.length;i++) {
//					log.info("등록되어있는 숫자 값"+reservationTimes[i]);
//					
//				}
		}
		 
		}
		log.info("확인여부 :"+times);
		
		if(times.contains(true)==false) {
			log.info("예약 가능합니다");
			log.info("... /Reservation getCheck() ...");
			return true;
			
		}else {
			
			log.info("예약 불가능합니다.");
		}
	//	log.info("예약하고자하는 날짜의 사용 불가 시간 : "+time);
		log.info("... /Reservation getCheck() ...");
		return false;
	}
	
	

}
