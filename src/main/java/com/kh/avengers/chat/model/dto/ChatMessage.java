package com.kh.avengers.chat.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {

  private Long id; // 메시지 고유 식별번호
  private String roomId; // 채팅방 ID
  private String senderId; // 발신자 ID
  private String senderName; // 발신자 이름
  private String content; // 메시지 내용
  private MessageType type; // 메시지 타입 (입장 || 퇴장 || 채팅)
  private LocalDateTime timestamp; // 메시지 전송 시간
  private boolean isStaff;  // 직원 여부 (true: 직원 || false: 고객)

  public enum MessageType {
    ENTER,   // 채팅방 입장
    LEAVE,   // 채팅방 퇴장
    CHAT  ,   // 일반 채팅
    SYSTEM
  }

}
