package com.tjf.checkin.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;

/**
 * The Interface ParticipantRepository.
 * 
 * @author Marcos Paulo dos Santos
 */
@Repository
@Transactional
public interface ParticipantRepository extends JpaRepository<ParticipantModel, Long>, ApiJpaRepository<ParticipantModel> {}
