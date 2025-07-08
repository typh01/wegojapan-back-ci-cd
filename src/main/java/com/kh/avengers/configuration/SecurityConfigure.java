package com.kh.avengers.configuration;

import java.util.Arrays;
import java.util.List;

import com.kh.avengers.configuration.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration

public class SecurityConfigure {

  private final JwtFilter jwtFilter;

  SecurityConfigure(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

    return httpSecurity.formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(requests -> {

              requests.requestMatchers("/ws/**", "/api/chat/**").permitAll();
              requests.requestMatchers("/api/admin/**").hasRole("ADMIN");
              requests.requestMatchers(HttpMethod.POST, "/api/auth/login",
                      "/api/members",
                      "/api/emails/send-email",
                      "/api/emails/verify-code",
                      "/api/emails/find-id",
                      "/api/emails/findVerify-code",
                      "/api/emails/find-password",
                      "/api/emails/findPassword-code",
                      "/api/emails/new-password",
                      "/api/upload/s3",
                      "/api/admin/**"
              ).permitAll();


              requests.requestMatchers(HttpMethod.PATCH, "/api/members/changeName",
                      "/api/members/changePassword"
              ).authenticated();
              requests.requestMatchers(HttpMethod.GET).permitAll();

              requests.requestMatchers(HttpMethod.GET,"/api/admin/**", "/api/travels/**").permitAll();


              requests.requestMatchers(HttpMethod.GET, "/api/members/checkedMemberName", "/api/reviews/my"  ).authenticated();
              requests.requestMatchers(HttpMethod.POST, "/api/reports/review", "/api/bookMark/insert-book",
                      "/api/reviews/insert-like",
                      "/api/reviews/**").authenticated();
              requests.requestMatchers(HttpMethod.PUT).authenticated();
              requests.requestMatchers(HttpMethod.DELETE, "/api/members/deleteMember", "/api/bookMark/delete-book",
                      "/api/reviews/delete-like", "/api/reviews/**").authenticated();
              requests.anyRequest().authenticated();
            })
            .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling(ex -> {
              ex.authenticationEntryPoint((request, response, authException) -> {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\": \"인증이 필요합니다.\", \"status\": 401}");
              });
              ex.accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\": \"접근 권한이 없습니다.\", \"status\": 403}");
              });
            })

            .build();

  }


  @Bean
  public CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("https://wegojapan.shop"));
    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }



  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authentiConfig) throws Exception {
    return authentiConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
} 