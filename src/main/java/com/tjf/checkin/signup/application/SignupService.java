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
 * The Class SignupService responsible for performing the validations and adding the participants.
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
    private void validUniqueParticipant (ParticipantModel participant) {        
        if(existsParticipantByEmailOrMacAddress(participant.getEmail(), participant.getMacAddress()))
            throw new ParticipantNonUniqueResultException();
    }
    
    /**
     * Exists participant by email or MacAddress.
     *
     * @param email the email
     * @param macAddress the MacAddress
     * @return true, if successful
     */
    private boolean existsParticipantByEmailOrMacAddress(String email, String macAddress) {
        
        boolean exists;
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        TypedQuery<Boolean> typedQuery = createQueryByEmailOrMacAddress(criteriaBuilder, email, macAddress);
        
        try {
            exists = typedQuery.getSingleResult();
        } catch(NoResultException exception) {
            exists = false;
        } catch(NonUniqueResultException exception) {
            exists = true;
        }
        
        entityManager.close();
        
        return exists;
    }
    
    /**
     * Creates the query by email or MacAddress.
     *
     * @param criteriaBuilder the criteria builder
     * @param email the email
     * @param macAddress the MacAddress
     * @return the typed query
     */
    private TypedQuery<Boolean> createQueryByEmailOrMacAddress(CriteriaBuilder criteriaBuilder, String email,
            String macAddress) {

        CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
        query.select(criteriaBuilder.literal(true));
        Root<ParticipantModel> participantRoot = query.from(ParticipantModel.class);
        query.where(createPredicateByEmailOrMacAddress(criteriaBuilder, participantRoot, email, macAddress));

        return entityManager.createQuery(query);
    }

    /**
     * Creates the predicate by email or MacAddress.
     *
     * @param criteriaBuilder the criteria builder
     * @param participantRoot the participant root
     * @param email the email
     * @param macAddress the MacAddress
     * @return the predicate
     */
    private Predicate createPredicateByEmailOrMacAddress(CriteriaBuilder criteriaBuilder,
            Root<ParticipantModel> participantRoot, String email, String macAddress) {
        
        Predicate emailPredicate = criteriaBuilder.equal(participantRoot.get("email"), email);
        Predicate macAddressPredicate = criteriaBuilder.equal(participantRoot.get("macAddress"), macAddress);
        return criteriaBuilder.or(emailPredicate, macAddressPredicate);
    }
}