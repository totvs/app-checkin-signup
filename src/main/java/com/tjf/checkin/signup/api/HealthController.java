package com.tjf.checkin.signup.api;

import static com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion.v1;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tjf.checkin.signup.CheckinSignupConfiguration;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;

@RestController
@RequestMapping(path = "/health_check", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(v1)
public class HealthController {

	/**
	 * Config Parameter
	 */
	@Autowired
	private CheckinSignupConfiguration checkinSignupConfiguration;

	/**
	 * Health Check.
	 *
	 * @return status OK
	 */
	@GetMapping
	@ResponseStatus(OK)
	public String healthCheck() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		if (checkinSignupConfiguration.logUrl.equalsIgnoreCase("true")) {
			System.out.println(request.getRequestURL().toString());
		}

		return "{\"status\": \"ok\"}";
	}

}
