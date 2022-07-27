package com.donavon.backend.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.donavon.backend.authenticationHandlers.LoginFailureHandler;

@Configuration
// @EnableConfigurationProperties
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
    configuration.setExposedHeaders(Arrays.asList("x-auth-token"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new LoginFailureHandler();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder());
  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
    // Cors and CSRF
    http
      .cors()
        .and()
      .csrf().ignoringAntMatchers("/userAuth**", "/img**", "/register**"); // returns 403 forbidden if not authenticated

    // Session management
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Set unauthorized requests exception handler
    // http
    // .exceptionHandling()
    // .authenticationEntryPoint(
    //     (request, response, ex) -> {
    //         response.sendError(
    //             HttpServletResponse.SC_UNAUTHORIZED,
    //             ex.getMessage()
    //         );
    //     }
    // );

    // Permissions on endpoints
    http
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/login**").permitAll()
      .antMatchers("/img**").permitAll()
      .antMatchers("/register**").permitAll();

    // Authentication handling
    http
      .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/userAuth")
        .successForwardUrl("/login_success_handler")
        .failureHandler(authenticationFailureHandler())
        .permitAll()
        .and()
      .logout() // TODO: implement logout handler
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .clearAuthentication(true).permitAll()
        .permitAll();
	}
}