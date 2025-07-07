package com.kh.avengers.chat.model.service;


import com.kh.avengers.chat.model.dto.ChatMessage;
import com.kh.avengers.chat.model.dto.ChatRoom;

import java.util.List;

public interface ChatService {

  /**
   * 새로운 채팅방 생성
   * @param customerId 고객(회원,비회원) 고유식별 번호
   * @param customerName 고객 이름
   * @return 생성된 채팅방 객체
   */
  ChatRoom createChatRoom(String customerId, String customerName);

  /**
   * 채팅방 정보 조회
   * @param roomId
   * @return
   */
  ChatRoom getChatRoom(String roomId);

  /**
   * 채팅 메시지 저장
   * @param chatMessage 저장할 메시지의 객체
   * @return 저장된 메시지
   */
  ChatMessage saveMessage(ChatMessage chatMessage);

  /**
   * 채팅방의 메시지 목록 조회 >> 시간순으로 정렬된 메시지 목록을 반환
   * @param roomId 채팅방 ID
   * @return 메시지 목록 (시간순 정렬)
   */
  List<ChatMessage> getChatMessages(String roomId);

  /**
   * 채팅방에 참여자 추가
   * @param roomId 채팅방ID
   * @param userId 추가할 사용자 ID
   */
  void addParticipant(String roomId, String userId);

  /**
   * 채팅방에서 참여자 제거
   * @param roomId 채팅방 ID
   * @param userId 제거할 사용자 ID
   */
  void removeParticipant(String roomId, String userId);

  /**
   * 채팅방에 직원 배정
   * @param roomId 채팅방 ID
   * @param staffId 배정할 직원 ID
   * @param staffName 배정할 직원 이름
   */
  void assignStaff(String roomId, String staffId, String staffName);

  /**
   * 마지막 메시지 업데이트
   * @param roomId 채팅방 ID
   * @param message 마지막 메시지 내용
   */
  void updateLastMessage(String roomId, String message);

  /**
   * 대기중인 채팅방 목록 조회 (직원용)
   * @return 대기중인 채팅방 목록
   */
  List<ChatRoom> getWaitingRooms();

  /**
   * 특정 직원이 담당중인 채팅방 목록 조회
   * @param staffId 직원 ID
   * @return 담당중인 채팅방 목록
   */
  List<ChatRoom> getActiveRoomsByStaff(String staffId);

  /**
   * 고객의 활성 채팅방 조회
   * @param customerId 고객 ID
   * @return 활성 채팅방 (없으면 null)
   */
  ChatRoom getActiveRoomByCustomer(String customerId);

  /**
   * 채팅방 종료
   * @param roomId 종료할 채팅방 ID
   * @return 종료 성공 여부 (true >> 성공!!,  false >> 실패)
   */
  boolean closeChatRoom(String roomId);

}
