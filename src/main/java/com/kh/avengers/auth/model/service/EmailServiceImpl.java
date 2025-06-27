package com.kh.avengers.auth.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.avengers.auth.controller.EmailController;
import com.kh.avengers.auth.model.dao.EmailMapper;
import com.kh.avengers.auth.model.dto.EmailDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.commonexception.NotFoundException;
import com.kh.avengers.exception.util.CustomMessagingException;
import com.kh.avengers.member.model.dao.MemberMapper;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.util.ResponseUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailServiceImpl implements EmailService{

    private final ResponseUtil responseUtil;
    private final MemberMapper memberMapper;
    private final EmailMapper emailMapper;
    private final JavaMailSender sender;

  private void sendCodeEmail(String email) {
    int verifyCode = verifyCodeCreate();
    MimeMessage message = sender.createMimeMessage();
    try{
      MimeMessageHelper helper = new MimeMessageHelper(message,false, "UTF-8");
      helper.setTo(email); // 받는이
      helper.setSubject("WeGoJapan 이메일 인증 번호입니다.");
      helper.setText("""
        <div style="width:100%; background:#f4f4f4; padding:20px; font-family:Arial,sans-serif;">
           <div style="max-width:600px; margin:0 auto; background:#fff; border-radius:8px; overflow:hidden;">
             <div style="background:#2196F3; color:#fff; padding:20px; text-align:center;">
               <h1>WeGoJapan 이메일 인증</h1>
            </div>
          <div style="padding:20px; color:#333;">
            <p>안녕하세요,</p>
            <p>아래 인증 코드를 입력하여 이메일 인증을 완료해주세요.</p>
            <div style="text-align:center; margin:20px 0;">
              <span style="display:inline-block; font-size:24px; font-weight:bold; color:#2196F3;
                            padding:10px 20px; border:2px dashed #2196F3; border-radius:4px;">
                """ + verifyCode + """
              </span>
            </div>
            <p>인증 코드는 <strong>3분</strong> 동안 유효합니다.</p>
            <p>감사합니다.</p>
          </div>
        </div>
      </div>
      """, true);
      sender.send(message);
    } catch(MessagingException e){
      e.printStackTrace();
      throw new CustomMessagingException("인증코드 전송 실패");
    }
    EmailDTO emailInfo = EmailDTO.builder()
                                 .email(email)
                                 .verifyCode(String.valueOf(verifyCode))
                                 .build();                     

    emailMapper.sendCodeEmail(emailInfo);
  }

  private int verifyCodeCreate() {
    int verifyCode = (int)(Math.random() * 9000) + 1000;
    return verifyCode;
  }

  @Override
  public RequestData signUpEmailCode(Map<String, String> email) {
    String emailCode = email.get("email");
    if(memberMapper.getMemberByEmail(emailCode)!= null){
      throw new NotFoundException(("유효하지 않은 이메일입니다."));
    }
    sendCodeEmail(emailCode);
    return responseUtil.rd("200", null, "이메일로 인증 코드가 전송되었습니다.");
  }

  public RequestData checkVerifyCode(EmailDTO code){
    EmailDTO emailCode = EmailDTO.builder()
                                 .email(code.getEmail())
                                 .verifyCode(code.getVerifyCode())
                                 .build();
    EmailDTO checkVerify = emailMapper.checkedVerifyCode(emailCode);

    if(checkVerify == null){
      throw new InvalidException("인증 코드가 일치하지 않습니다.");
    }
    Date createDate = checkVerify.getCreateDate();
    Long nowMillis = System.currentTimeMillis();
    Long expireMillis = createDate.getTime() + 180000L;
    if (nowMillis > expireMillis){
      throw new InvalidException("인증 시간이 만료되었습니다.");
    }
    MemberDTO member = memberMapper.getMemberByEmail(checkVerify.getEmail());
    if(member != null){
      Map<String, String> data = new HashMap<>();
      data.put("memberId", member.getMemberId());
      return responseUtil.rd("200", data, "이미 가입된 이메일입니다. 로그인해주세요."); 
    }
    return responseUtil.rd("200", null, "인증 코드가 확인되었습니다. 회원가입을 진행해주세요.");  
  }
}


