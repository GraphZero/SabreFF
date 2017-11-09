package com.sabre.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author Andrzej
 */

@Component
public class RESTAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOG = Logger.getLogger(RESTAuthenticationFailureHandler.class.getName());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
		LOG.info("AUTH FAILURE: ");
                LOG.info("USERNAME: " + request.getParameter("username") );
                LOG.info("PASSWORD: " + request.getParameter("password") );
                LOG.info("AuthenticationException: " + exception.getMessage() );
		super.onAuthenticationFailure(request, response, exception);
	}
}