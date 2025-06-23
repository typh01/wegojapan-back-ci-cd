package com.kh.avengers.member.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

  private Long memberNo;

  @NotBlank(message = "아이디는 필수 항목입니다.")
  @Size(min = 6, max = 20, message = "아이디는 6자 이상 20자 이하로 입력해야 합니다.")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 사용할 수 있습니다.")
  private String memberId;

  @NotBlank(message = "비밀번호는 필수입니다.")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,12}$",
           message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~12자여야 합니다.")
  private String memberPw;

  @NotBlank(message = "닉네임은 필수입니다.")
  @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "닉네임은 2~10자, 한글 또는 영문만 가능합니다.")
  private String memberName;

  @NotBlank(message = "이메일은 필수입니다.")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
           message = "이메일 형식이 올바르지 않습니다.")
  private String email;
  private Date enrollDate;
  private Date modifiedDate;
  private String memberRole;
  private char isActive;

}
