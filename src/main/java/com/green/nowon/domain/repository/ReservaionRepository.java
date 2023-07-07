package com.green.nowon.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.entity.ReservationEntity;

public interface ReservaionRepository extends JpaRepository<ReservationEntity, Long> {

	Optional<ReservationEntity> findByMemberNo(long mno);

}
