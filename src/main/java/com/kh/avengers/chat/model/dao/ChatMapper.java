package com.kh.avengers.chat.model.dao;


import com.kh.avengers.chat.model.dto.ChatMessage;
import com.kh.avengers.chat.model.dto.ChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 채팅 관련 MyBatis 매퍼 인터페이스
 * 데이터베이스와의 상호작용을 담당
 */
@Mapper
public interface ChatMapper {

  /**
   * 채팅방 정보 저장
   * @param chatRoom 저장할 채팅방 객체
   * @return 저장된행의 수
   */
  int insertChatRoom(ChatRoom chatRoom);

  /**
   * 채팅방 ID로 채팅방 조회
   * @param roomId 채팅방 ID
   * @return 채팅방 객체
   */
  ChatRoom selectChatRoomById(@Param("roomId") String roomId);

  /**
   * 채팅 메시지 저장
   * @param chatMessage 저장할 메시지의 객체
   * @return 십입된 행의 수
   */
  int insertChatMessage(ChatMessage chatMessage);

  /**
   * 채팅방 정보 업데이트
   * @param chatRoom 업데이트할 채팅방 객체
   * @return 삽입된 행의 수
   */
  int updateChatRoom(ChatRoom chatRoom);



  /**
   * 채팅방 ID로 메시지 목록 조회 (시간순 정렬)
   * @param roomId 채팅방 ID
   * @return 메시지 목록
   */
  List<ChatMessage> selectMessagesByRoomId(@Param("roomId") String roomId);

  /**
   * 대기중인 채팅방 목록 조회 (직원 배정 전)
   * @return 대기중인 채팅방 목록
   */
  List<ChatRoom> selectWaitingRooms();

  /**
   * 특정 직원이 담당중인 활성 채팅방 목록 조회
   * @param staffId 직원 ID
   * @return 담당중인 채팅방 목록
   */
  List<ChatRoom> selectActiveRoomsByStaffId(@Param("staffId") String staffId);

  /**
   * 특정 고객의 활성 채팅방 조회 (WAITING || ACTIVE)
   * @param customerId 고객 ID
   * @return 활성 채팅방
   */
  ChatRoom selectActiveRoomByCustomerId(@Param("customerId") String customerId);

}
