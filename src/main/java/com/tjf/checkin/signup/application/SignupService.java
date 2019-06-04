package com.tjf.checkin.signup.application;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjf.checkin.signup.exception.ParticipantNonUniqueResultException;
import com.tjf.checkin.signup.repository.ParticipantModel;
import com.tjf.checkin.signup.repository.ParticipantRepository;

/**
 * The Class SignupService responsible for performing the validations and adding
 * the participants.
 * 
 * @author Marcos Paulo dos Santos
 */
@Service
@Transactional
public class SignupService {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The repository. */
	@Autowired
	private ParticipantRepository repository;

	/**
	 * Signup.
	 *
	 * @param participant the participant
	 * @return the participant model
	 */
	public ParticipantModel signup(ParticipantModel participant) {
		validUniqueParticipant(participant);
		return repository.save(participant);
	}

	/**
	 * Valid unique participant.
	 *
	 * @param participant the participant
	 */
	private void validUniqueParticipant(ParticipantModel participant) {
		if (existsCheckinByEmailAndEvent(participant.getEmail(), participant.getCode_event()))
			throw new ParticipantNonUniqueResultException();
	}

	/**
	 * Exists participant by email and code_event.
	 *
	 * @param email      the email
	 * @param code 		 the code of event
	 * @return true, if successful
	 */

	public boolean existsCheckinByEmailAndEvent(String email, String code) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
		
		query.select(criteriaBuilder.literal(true));

		Root<ParticipantModel> participant = query.from(ParticipantModel.class);

		Predicate emailNamePredicate = criteriaBuilder.equal(participant.get("email"), email);
		Predicate eventPredicate = criteriaBuilder.equal(participant.get("code_event"), code);

		query.where(emailNamePredicate, eventPredicate);

		TypedQuery<Boolean> typedQuery = entityManager.createQuery(query);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException exception) {
			return false;
		} catch (NonUniqueResultException exception) {
			return true;
		}
	}
}