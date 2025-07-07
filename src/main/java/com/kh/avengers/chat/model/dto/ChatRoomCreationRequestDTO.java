package com.kh.avengers.chat.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoomCreationRequestDTO {
  private String userId;
  private String userName;

}
