package com.tjf.checkin.signup.api;

import static com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion.v1;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tjf.checkin.signup.CheckinSignupConfiguration;
import com.tjf.checkin.signup.application.SignupService;
import com.tjf.checkin.signup.exception.ParticipantContraintException;
import com.tjf.checkin.signup.repository.ParticipantModel;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.core.validation.ValidatorService;

/**
 * This controller is responsible for mapping the application's REST endpoints.
 * 
 * @author Marcos Paulo dos Santos
 */

@RestController
@RequestMapping(path = "/api/v1/checkin", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(v1)
public class SignupController {

	/**
	 * Exception Validator.
	 * 
	 * {@link ValidatorService}}
	 */
	@Autowired
	private ValidatorService validator;

	/**
	 * Service
	 * 
	 * @see SignupService
	 */
	@Autowired
	private SignupService service;

	/**
	 * Config Parameter
	 */
	@Autowired
	private CheckinSignupConfiguration checkinSignupConfiguration;

	/**
	 * Sign up.
	 *
	 * @param participant Content of the RequestBody
	 * @return participant
	 * @throws ParticipantContraintException If there is any violation
	 */
	@PostMapping
	@ResponseStatus(CREATED)
	public ParticipantModel signUp(@RequestBody ParticipantModel participant) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		if (checkinSignupConfiguration.logUrl.equalsIgnoreCase("true")) {
			System.out.println(request.getRequestURL().toString() + participant.toString());
		}

		validator.validate(participant).ifPresent(violations -> {
			throw new ParticipantContraintException(violations);
		});

		return service.signup(participant);
	}

}
