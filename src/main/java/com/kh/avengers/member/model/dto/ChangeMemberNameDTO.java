package com.kh.avengers.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMemberNameDTO {

  @NotBlank(message = "닉네임은 필수입니다.")
  @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "닉네임은 2~10자, 한글 또는 영문만 가능합니다.")
  private String newMemberName;

  private Long memberNo;

}
