package com.kh.avengers.configuration;

import java.util.Arrays;

import com.kh.avengers.configuration.filter.JwtFilter;
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
                        requests.requestMatchers("/admin/**").hasRole("ADMIN");
                        requests.requestMatchers(HttpMethod.POST, "/api/auth/login", "/api/members", "/api/emails/send-email","/api/emails/verify-code").permitAll();
                        requests.requestMatchers(HttpMethod.GET, "/api/admin/*").permitAll();
                        requests.requestMatchers(HttpMethod.POST, "/api/admin/*").authenticated();
                        requests.requestMatchers(HttpMethod.PUT).authenticated();
                        requests.requestMatchers(HttpMethod.DELETE).authenticated();
                        requests.anyRequest().authenticated();
                       })
                       .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                       .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                       .build();

  }

  	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration configuration = new CorsConfiguration();
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
