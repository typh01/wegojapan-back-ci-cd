package com.kh.avengers.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * 메시지 브로커 설정
   * 클라이언트간 메시지 라우팅을 담당하는 브로커를 구성
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // Simple Broker 활성화 >> /topic으로 시작하는 destination에 대해 브로커가 메시지를 처리하게 함
    config.enableSimpleBroker("/topic");

    // 클라이언트에서 서버로 메시지를 보낼 때 사용할 prefix설정 >> /app으로 시작하는 메시지는 @MessageMapping이 있는 메소드로 !
    config.setApplicationDestinationPrefixes("/app");
  }

  /**
   * STOMP 엔드포인트 설정 >> 클라이언트가 WebSocket 연결을 시작할 수 있는 엔드포인트를 등록
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
            .withSockJS();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("*")
                .allowedOrigins("http://localhost:5173", "https://wegojapan.shop")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);

        registry.addMapping("/ws/**")
                .allowedOriginPatterns("*")
                .allowedOrigins("https://wegojapan.shop")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
      }
    };
  }
}