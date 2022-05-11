package com.azlir.restaurant.config;

import com.azlir.restaurant.services.framework.StoreAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final StoreAccountService storeAccountService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(storeAccountService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    final var successHandler = new AuthenticationSuccessWithSessionHandler();
    http.authorizeRequests()
        .antMatchers(
            "/signup**",
            "/login**",
            "/assets/**",
            "/static/**",
            "/blog.css/**",
            "/forgot-password**",
            "/reset-password**")
        .permitAll()
        .antMatchers("/js/**", "/css/**", "/img/**", "/webjars/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .failureUrl("/login?message=error")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/", true)
        .successHandler(successHandler)
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .permitAll();
  }
}
