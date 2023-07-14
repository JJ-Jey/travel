package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.domain.dto.ReservationSaveDTO;

public interface MemberService {

	void saveProcess(MemberSaveDTO dto);

	void reservationProcess(ReservationSaveDTO dto);

	void findAll(Model model);

	void findMember(Model model);

}
