package com.kh.avengers.chat.controller;

import com.kh.avengers.chat.model.dto.ChatMessage;
import com.kh.avengers.chat.model.dto.ChatRoom;
import com.kh.avengers.chat.model.dto.ChatRoomCreationRequestDTO;
import com.kh.avengers.chat.model.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatRestController {

  private final ChatService chatService;
  private final SimpMessagingTemplate messagingTemplate;

  /**
   * 고객이 새로운 채팅방을 생성
   * @param request 요청 DTO
   * @return 생성된 채팅방 정보
   */
  @PostMapping("/api/chat/create")
  @ResponseBody
  public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoomCreationRequestDTO request) {

    try {
      // 1. 입력 데이터 검증
      if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
        return ResponseEntity.badRequest().build();
      }
      if (request.getUserName() == null || request.getUserName().trim().isEmpty()) {
        return ResponseEntity.badRequest().build();
      }

      // 2. 채팅방 생성 (기존 채팅방이 있으면 기존 것 반환)
      ChatRoom newRoom = chatService.createChatRoom(request.getUserId(), request.getUserName());

      // 새로운 상담 요청이라면 직원들에게 알림 전송
      if ("WAITING".equals(newRoom.getStatus())) {
        messagingTemplate.convertAndSend("/topic/staff/new-request", newRoom);
      }

      return ResponseEntity.ok(newRoom);
    } catch (Exception e) {
      System.err.println("채팅방 생성 중 오류 발생 : " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  /**
   * 특정 채팅방의 이전 메시지 목록을 조회
   * @param roomId 채팅방 ID
   * @return 메시지 목록
   */
  @GetMapping("/api/chat/room/{roomId}/messages")
  @ResponseBody
  public ResponseEntity<List<ChatMessage>> getRoomMessages(@PathVariable String roomId) {
    try {
      // 1. 채팅방의 모든 메시지 조회
      List<ChatMessage> messages = chatService.getChatMessages(roomId);
      return ResponseEntity.ok(messages);
    } catch (Exception e) {
      System.err.println("메시지 목록 조회 중 오류 발생 : " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  /**
   * 직원용 대기중인 채팅방 목록을 조회
   * @return 대기중인 채팅방 목록을 포함한 응답
   */
  @GetMapping("/api/chat/staff/waiting-rooms")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> getWaitingRooms() {

    Map<String, Object> response = new HashMap<>();

    try {
      // 1. 대기중인 채팅방 목록 조회
      List<ChatRoom> waitingRooms = chatService.getWaitingRooms();

      // 2. 응답 객체
      response.put("success", true);
      response.put("rooms", waitingRooms);
      response.put("count", waitingRooms.size());

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.err.println("대기중인 채팅방 목록 조회 중 오류 발생 : " + e.getMessage());
      e.printStackTrace();

      response.put("success", false);
      response.put("message", "대기중인 채팅방 조회 중 오류가 발생했습니다 : " + e.getMessage());
      response.put("rooms", List.of());

      return ResponseEntity.internalServerError().body(response);
    }
  }

  /**
   * 직원용 담당중인 채팅방 목록을 조회
   * @param staffId 직원 ID
   * @return 담당중인 채팅방 목록을 포함한 응답 객체
   */
  @GetMapping("/api/chat/staff/{staffId}/active-rooms")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> getActiveRooms(@PathVariable String staffId) {

    Map<String, Object> response = new HashMap<>();

    try {
      // 1. 해당 직원의 담당중인 채팅방 목록 조회
      List<ChatRoom> activeRooms = chatService.getActiveRoomsByStaff(staffId);

      // 2. 응답 객체
      response.put("success", true);
      response.put("rooms", activeRooms);
      response.put("count", activeRooms.size());

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.err.println("담당중인 채팅방 목록 조회 중 오류 발생 : " + e.getMessage());
      e.printStackTrace();

      response.put("success", false);
      response.put("message", "담당중인 채팅방 조회 중 오류가 발생했습니다 : " + e.getMessage());
      response.put("rooms", List.of());

      return ResponseEntity.internalServerError().body(response);
    }
  }

  /**
   * 채팅방 종료
   * @param roomId 채팅방 ID
   * @return 종료 결과
   */
  @PutMapping("/api/chat/room/{roomId}/close")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> closeChatRoom(@PathVariable String roomId) {
    Map<String, Object> response = new HashMap<>();
    try {
      // 1. 채팅방 종료 처리
      boolean success = chatService.closeChatRoom(roomId);

      if (success) {
        response.put("success", true);
        response.put("message", "채팅방이 종료되었습니다.");
      } else {
        response.put("success", false);
        response.put("message", "채팅방 종료에 실패했습니다.");
      }

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.err.println("채팅방 종료 중 오류 발생 : " + e.getMessage());
      e.printStackTrace();

      response.put("success", false);
      response.put("message", "채팅방 종료 중 오류가 발생했습니다 : " + e.getMessage());

      return ResponseEntity.internalServerError().body(response);
    }
  }
}

