package com.tjf.checkin.signup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckinSignupConfiguration {

	@Value("${checkin.logurl}")
    public String logUrl;
}
