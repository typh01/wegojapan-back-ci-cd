package com.kh.avengers.chat.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.pl.NIP;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoom {

  private String roomId;  // 채팅방 시퀀스id
  private String customerId; // 고객 ID
  private String customerName; // 고객 이름
  private String staffId; // 담당 직원 ID
  private String staffName; // 담당 직원 이름
  private String status; // 상담 상태 (WAITING || ACTIVE ||CLOSED)
  private LocalDateTime createdAt; // 생성 시간
  private LocalDateTime updatedAt; // 마지막 업데이트 시간
  private Set<String> participants = new HashSet<>();// 참여자 목록
  private String lastMessage; // 마지막 메시지
  private LocalDateTime lastMessageTime; // 마지막 메시지 시간



  public enum RoomStatus {
    WAITING,  // 대기 중 (직원 배정 전)
    ACTIVE,   // 상담 중
    CLOSED  // 상담 종료
  }

  // 직원 배정 메소드
  public void assignStaff(String staffId, String staffName) {
    this.staffId = staffId;
    this.staffName = staffName;
    this.status = RoomStatus.ACTIVE.toString();
    this.participants.add(staffId); // 직원을 참여자로 추가
    this.updatedAt = LocalDateTime.now();
  }

  // 참여자 추가 메소드
  public void addParticipant(String userId) {
    this.participants.add(userId);
    this.updatedAt = LocalDateTime.now();
  }

  // 참여자 제거 메소드
  public void removeParticipant(String userId) {
    this.participants.remove(userId);
    this.updatedAt = LocalDateTime.now();
  }

  // 마지막 메시지 업데이트 메소드
  public void updateLastMessage(String message) {
    this.lastMessage = message;
    this.lastMessageTime = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
