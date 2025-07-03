package com.kh.avengers.auth.model.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;




@Value
@Builder
@Getter
public class Login {

  @NotBlank(message = "아이디는 필수 항목입니다.")
  @Size(min = 6, max = 20, message = "아이디는 6자 이상 20자 이하로 입력해야 합니다.")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 사용할 수 있습니다.")
  private String memberId;

  @NotBlank(message = "비밀번호는 필수입니다.")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,12}$",
           message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~12자여야 합니다.")
  private String memberPw;

}
