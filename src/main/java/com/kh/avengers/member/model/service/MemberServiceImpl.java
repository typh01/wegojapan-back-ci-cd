package com.kh.avengers.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.avengers.auth.model.dto.UpdatePasswordDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.member.model.dao.MemberMapper;
import com.kh.avengers.member.model.dto.ChangeMemberNameDTO;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.member.model.vo.Member;
import com.kh.avengers.token.model.dao.TokenMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kh.avengers.util.ResponseUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;
  private final ResponseUtil responseUtil;
  private final TokenMapper tokenMapper;

  @Override
  public RequestData signUp(MemberDTO member) {
    Long searchMember = memberMapper.getMemberByMemberId(member.getMemberId());
    MemberDTO searchEmail =  memberMapper.getMemberByEmail(member.getEmail());


    if(searchMember ==1 || searchEmail != null){
      throw new InvalidException("존재하는 아이디 또는 이메일 입니다.");
    }

    Member memberValue = Member.builder()
                               .memberId(member.getMemberId())
                               .memberPw(passwordEncoder.encode(member.getMemberPw()))
                               .memberName(member.getMemberName())
                               .email(member.getEmail())
                               .memberRole("ROLE_USER")
                               .build();
    memberMapper.signUp(memberValue);            
    return responseUtil.rd("200", null, "회원가입 성공"); 
  }

  @Override
  public RequestData checkName(String member){
     log.info("닉네임 중복 확인 : {}", member);
    ChangeMemberNameDTO searchName = memberMapper.getMemberByMemberName(member);
     log.info("닉네임 검색 결과: {}", searchName);
    if(searchName != null){
      log.info("중복된 닉네임 감지: {}", member);
      throw new InvalidException(("중복된 닉네임입니다."));
    }
    return responseUtil.rd("200",null, "중복 확인되었습니다.");
  }

  @Override
  public RequestData updateMember(ChangeMemberNameDTO member) {
    
    Long searchMember = memberMapper.updateMemberName(member);

    if(searchMember == 0){
      throw new InvalidException(("닉네임 변경 실패했습니다."));
    }

    return responseUtil.rd("200", null, "닉네임 변경 성공했습니다.");
    

  }

  @Override
  public RequestData updatePassword(Map<String, String> member) {
    String memberId = member.get("memberId");
    String currentPassword = member.get("currentPassword");
    String newPassword = member.get("newPassword");

    MemberDTO memberInfo = memberMapper.findByMemberId(memberId);
    log.info("잘찍힘? {}", memberInfo);
   if(memberInfo == null ){
    throw new InvalidException("회원 정보를 찾을 수 없습니다.");
   }

   if(!passwordEncoder.matches(currentPassword, memberInfo.getMemberPw())){ // 암호화 비교할때는 passwordEncoder.matches(평문, 암호문)
    throw new InvalidException("현재 비밀번호가 일치하지 않습니다.");
   }

   if(passwordEncoder.matches(newPassword,memberInfo.getMemberPw())){
    throw new InvalidException("현재 비밀번호랑 새 비밀번호가 같습니다.");
   }

   UpdatePasswordDTO updatePw = UpdatePasswordDTO.builder()
                                                  .memberId(memberId)
                                                  .newPw(passwordEncoder.encode(newPassword))
                                                  .build();
   
    memberMapper.updateMemberPassword(updatePw);
    return responseUtil.rd("200", null, "비밀번호 변경 성공했습니다.");
  }

  @Override
  public RequestData deleteMember(Map<String, String> member) {
    Long memberNo = Long.parseLong(member.get("memberNo"));
    String memberPw = member.get("memberPw");

    MemberDTO memberInfo = memberMapper.findByMemberNo(memberNo);

    if(memberInfo == null){
      throw new InvalidException("회원 정보를 찾을 수 없습니다.");
    }

    if(!passwordEncoder.matches(memberPw, memberInfo.getMemberPw())){
      throw new InvalidException("비밀번호가 일치하지 않습니다.");
    }
   
    tokenMapper.deleteByMemberNo(memberNo);
    memberMapper.deleteMember(memberNo);
    return responseUtil.rd("200", null,"회원탈퇴 되었습니다.");
  }


}
