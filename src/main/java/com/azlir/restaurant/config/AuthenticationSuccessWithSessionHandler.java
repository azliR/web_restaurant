package com.azlir.restaurant.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessWithSessionHandler
    extends SavedRequestAwareAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler, LogoutSuccessHandler {

  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    request.getSession().removeAttribute(USERNAME);
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    super.onAuthenticationSuccess(request, response, authentication);
    request.getSession().setAttribute(USERNAME, request.getParameter(USERNAME));
  }
}
