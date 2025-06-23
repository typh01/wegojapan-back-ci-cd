package com.kh.avengers.member.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Member {

  private Long memberNo;
  private String memberId;
  private String memberPw;
  private String memberName;
  private String email;
  private Date enrollDate;
  private Date modifiedDate;
  private String memberRole;
  private char isActive;
}
