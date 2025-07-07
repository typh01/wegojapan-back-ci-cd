package com.kh.avengers.configuration;


import com.kh.avengers.chat.model.dto.ChatMessage;
import com.kh.avengers.chat.model.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * WebSocket 연결/해제를 이벤트 처리하는 리스너 >> 사용자의 연결 상태를 모니터링하고 채팅방 퇴장 처리
 */
@Component
public class WebSocketEventListener {

  @Autowired
  private SimpMessageSendingOperations messagingTemplate; // 메시지 전송

  @Autowired
  private ChatService chatService; // 채팅 서비스

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    System.out.println("새로운 WebSocket 연결이 생성되었습니다.");
  }

  /**
   * WebSocket의 연결 해제를 처리 >> 사용자가 브라우저를 닫거나 연결 끊어지면!
   * @param event
   */
  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    try {
      StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

      // 세션에서 사용자 정보 가져오기
      String userId = (String) headerAccessor.getSessionAttributes().get("userId");
      String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");

      if (userId != null && roomId != null) {
        System.out.println("사용자 연결 해제: " + userId + " (채팅방: " + roomId + ")");

        // 채팅방에서 사용자 제거
        chatService.removeParticipant(roomId, userId);

        // 퇴장 메시지 생성
        ChatMessage leaveMessage = new ChatMessage();
        leaveMessage.setRoomId(roomId);
        leaveMessage.setSenderId(userId);
        leaveMessage.setSenderName("Unknown");
        leaveMessage.setContent(userId + "님이 채팅방을 나가셨습니다.");
        leaveMessage.setType(ChatMessage.MessageType.LEAVE);

        // 퇴장 메시지 저장
        chatService.saveMessage(leaveMessage);

        // 채팅방의 다른 참여자들에게 퇴장 알림 전송
        messagingTemplate.convertAndSend("/topic/chat/" + roomId, leaveMessage);

      }

    } catch (Exception e) {
      System.err.println("WebSocket 연결 해제 처리 중 오류 발생: " + e.getMessage());
      e.printStackTrace();
    }
  }
}