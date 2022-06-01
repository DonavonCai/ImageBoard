package com.donavon.backend.config;

import java.util.Arrays;

import com.donavon.backend.security.AuthProvider;
import com.donavon.backend.security.LoginSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private LoginSuccessHandler loginSuccessHandler;

  // Register auth provider
  @Bean
  public AuthProvider customAuthProvider() {
    AuthProvider auth = new AuthProvider();
    return auth;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(customAuthProvider());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
      "DELETE", "OPTIONS"));
      configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type",
      "x-auth-token"));
      configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
      UrlBasedCorsConfigurationSource source = new
      UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);

      return source;
  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
    /* FIXME: Enabling csrf and ignoring /login endpoint with ignoringAntMatchers()
     * causes auth.credentials to be null...
     */
    http
      // .cors()
      //   .and()
      // .csrf().ignoringAntMatchers("/login**", "/img**")
      // .and()
      .csrf().disable()
      .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/login**").permitAll()
        .antMatchers("/img**").permitAll()
        .antMatchers("/register**").permitAll().anyRequest().authenticated()
        .and()
      .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/userAuth")
        .successForwardUrl("/login_success_handler")
        // .successHandler(loginSuccessHandler) // FIXME: why isn't this called!!!!!!!
        .permitAll()
        .and()
      .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .clearAuthentication(true).permitAll()
        .permitAll();
	}
}