package com.kh.avengers.chat.model.service;

import com.kh.avengers.chat.model.dao.ChatMapper;
import com.kh.avengers.chat.model.dto.ChatMessage;
import com.kh.avengers.chat.model.dto.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatServiceImpl implements ChatService {

  @Autowired
  private ChatMapper chatMapper; // MyBatis 매퍼 주입

  // 실시간 성능을 위해 메모리에 채팅방 정보를 저장하는 맵
  private final ConcurrentHashMap<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

  /**
   * 새로운 채팅방 생성
   * @param customerId 고객(회원,비회원) 고유식별 번호
   * @param customerName 고객 이름
   * @return 생성된 채팅방 객체
   */
  public ChatRoom createChatRoom(String customerId, String customerName) {
    try {

      // 1. 고객이 이미 활성 채팅방을 가지고 있는지 확인
      ChatRoom existingRoom = getActiveRoomByCustomer(customerId);
      if (existingRoom != null) {
        return existingRoom; // 기존 채팅방 반환
      }

      // 2. 고유한 채팅방 ID 생성
      String roomId = "ROOM_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);

      // 3. 채팅방 객체 생성
      ChatRoom chatRoom = new ChatRoom();

      chatRoom.setRoomId(roomId);
      chatRoom.setCustomerId(customerId);
      chatRoom.setCustomerName(customerName);
      chatRoom.setStatus("WAITING");

      // 4. 데이터베이스에 채팅방 저장
      chatMapper.insertChatRoom(chatRoom);

      // 5. 빠른 접근을 위해 메모리에 채팅방 저장
      chatRooms.put(roomId, chatRoom);

      return chatRoom;

    } catch (Exception e) {
      throw new RuntimeException("채팅방 생성 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 채팅방 정보 조회
   * @param roomId
   * @return
   */
  public ChatRoom getChatRoom(String roomId) {

    try {

      // 1. 메모리에서 먼저 확인
      ChatRoom chatRoom = chatRooms.get(roomId);

      if (chatRoom == null) {
        // 2. 메모리에 없으면 >>  DB서 조회
        chatRoom = chatMapper.selectChatRoomById(roomId);

        if (chatRoom != null) {
          // 3. 메모리에 저장
          chatRooms.put(roomId, chatRoom);
        }
      }

      return chatRoom;

    } catch (Exception e) {
      throw new RuntimeException("채팅방 조회 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 채팅 메시지 저장
   * @param chatMessage 저장할 메시지의 객체
   * @return 저장된 메시지
   */
  public ChatMessage saveMessage(ChatMessage chatMessage) {
    try {
      // 1. DB에 메시지 저장
      chatMapper.insertChatMessage(chatMessage);

      // 채팅방의 마지막 메시지 정보 업데이트
      updateLastMessage(chatMessage.getRoomId(), chatMessage.getContent());

      return chatMessage;

    } catch (Exception e) {
      throw new RuntimeException("메시지 저장 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 채팅방의 메시지 목록 조회 >> 시간순으로 정렬된 메시지 목록을 반환
   * @param roomId 채팅방 ID
   * @return 메시지 목록 (시간순 정렬)
   */
  public List<ChatMessage> getChatMessages(String roomId) {

    try {
      // 1. DB에서 해당 채팅방의 모든 메시지를 조회하기
      return chatMapper.selectMessagesByRoomId(roomId);

    } catch (Exception e) {
      throw new RuntimeException("메시지 조회 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 채팅방에 참여자 추가
   * @param roomId 채팅방ID
   * @param userId 추가할 사용자 ID
   */
  public void addParticipant(String roomId, String userId) {
    try {
      // 1.채팅방 정보 조회
      ChatRoom chatRoom = getChatRoom(roomId);
      if (chatRoom != null) {
        chatRoom.addParticipant(userId);

        // 2. DB 업데이트
        chatMapper.updateChatRoom(chatRoom);

        // 4. 메모리업데이트
        chatRooms.put(roomId, chatRoom);
      }

    } catch (Exception e) {
      throw new RuntimeException("참여자 추가 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 채팅방에서 참여자 제거
   * @param roomId 채팅방 ID
   * @param userId 제거할 사용자 ID
   */
  public void removeParticipant(String roomId, String userId) {

    try {
      // 1. 채팅방 정보조회하기
      ChatRoom chatRoom = getChatRoom(roomId);
      if (chatRoom != null) {
        // 2. 참여자 제거
        chatRoom.removeParticipant(userId);

        // 3. db 업데이트
        chatMapper.updateChatRoom(chatRoom);

        // 4. 메모리 업데이트
        chatRooms.put(roomId, chatRoom);
      }

    } catch (Exception e) {
      throw new RuntimeException("참여자 제거 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 채팅방에 직원 배정
   * @param roomId 채팅방 ID
   * @param staffId 배정할 직원 ID
   * @param staffName 배정할 직원 이름
   */
  public void assignStaff(String roomId, String staffId, String staffName) {

    try {
      // 1. 채팅방 정보 조회
      ChatRoom chatRoom = getChatRoom(roomId);
      if (chatRoom != null) {
        // 2. 직원 정보 설정
        chatRoom.setStaffId(staffId);
        chatRoom.setStaffName(staffName);

        // 3. 상태를 ACTIVE로 변경 >> 직원이 배정됨
        chatRoom.setStatus("ACTIVE");

        // DB업데이트
        chatMapper.updateChatRoom(chatRoom);

        // 메모리 업데이트
        chatRooms.put(roomId, chatRoom);
      }

    } catch (Exception e) {
      throw new RuntimeException("직원 배정 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 마지막 메시지 업데이트
   * @param roomId 채팅방 ID
   * @param message 마지막 메시지 내용
   */
  public void updateLastMessage(String roomId, String message) {

    try {
      // 1. 채팅방 정보 조회
      ChatRoom chatRoom = getChatRoom(roomId);
      if (chatRoom != null) {
        // 2. 마지막 메시지 정보 설정
        chatRoom.setLastMessage(message);
        // 3. 데이터베이스 업데이트
        chatMapper.updateChatRoom(chatRoom);

        // 4. 메모리 캐시 업데이트
        chatRooms.put(roomId, chatRoom);
      }

    } catch (Exception e) {
      throw new RuntimeException("마지막 메시지 업데이트 중 오류 발생: " + e.getMessage(), e);
    }
  }

  /**
   * 대기중인 채팅방 목록 조회 (직원용)
   * @return 대기중인 채팅방 목록
   */
  public List<ChatRoom> getWaitingRooms() {

    try {
      // dn에서 WAITING 상태인 채팅방들 조회
      return chatMapper.selectWaitingRooms();

    } catch (Exception e) {
      throw new RuntimeException("대기중인 채팅방 조회 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 특정 직원이 담당중인 채팅방 목록 조회
   * @param staffId 직원 ID
   * @return 담당중인 채팅방 목록
   */
  public List<ChatRoom> getActiveRoomsByStaff(String staffId) {

    try {

      // DB에서 해당 직원이 담당하는 ACTIVE 상태 채팅방들 조회
      return chatMapper.selectActiveRoomsByStaffId(staffId);

    } catch (Exception e) {
      throw new RuntimeException("담당중인 채팅방 조회 중 오류 발생 : " + e.getMessage(), e);
    }
  }

  /**
   * 고객의 활성 채팅방 조회
   * @param customerId 고객 ID
   * @return 활성 채팅방 (없으면 null)
   */
  public ChatRoom getActiveRoomByCustomer(String customerId) {

    try {
      // db서 해당 고객의 WAITING || ACTIVE 상태인 채팅방 조회
      return chatMapper.selectActiveRoomByCustomerId(customerId);

    } catch (Exception e) {
      throw new RuntimeException("고객 활성 채팅방 조회 중 오류 발생: " + e.getMessage(), e);
    }
  }

  /**
   * 채팅방 종료
   * @param roomId 종료할 채팅방 ID
   * @return 종료 성공 여부 (true >> 성공!!,  false >> 실패)
   */
  public boolean closeChatRoom(String roomId) {

    try {
      // 1. 채팅방 정보 조회
      ChatRoom chatRoom = getChatRoom(roomId);
      if (chatRoom != null) {
        // 2. 상태를 CLOSED로 변경
        chatRoom.setStatus("CLOSED");

        // 3. DB업데아트
        chatMapper.updateChatRoom(chatRoom);

        // 4.메모리에서 제거
        chatRooms.remove(roomId);

        return true;
      }

      return false; // 채팅방을 찾을 수 없음

    } catch (Exception e) {
      throw new RuntimeException("채팅방 종료 중 오류 발생: " + e.getMessage(), e);
    }
  }
}