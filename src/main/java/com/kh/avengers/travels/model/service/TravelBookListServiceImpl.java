package com.kh.avengers.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelMapper;
import com.kh.avengers.admin.travels.model.dto.TravelDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.member.model.dao.MemberMapper;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.util.ResponseUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelBookListServiceImpl implements TravelBookListService {

  private final ResponseUtil responseUtil;
  private final MemberMapper memberMapper;
  private final TravelMapper travelMapper;
  
  @Override
  public RequestData getBookMarks(Long memberNo) {

    MemberDTO member = memberMapper.findByMemberNo(memberNo);
    if (member == null) {
        throw new InvalidException("존재하지 않는 회원입니다.");
    }

    List<TravelDTO> travelList = travelMapper.selectBookList(memberNo);

    for (TravelDTO t : travelList) {
        t.setTimeList(travelMapper.selectTravelTimeList(t.getTravelNo()));
        t.setImageList(travelMapper.selectTravelImageList(t.getTravelNo()));
        if (t.getImageList() != null && !t.getImageList().isEmpty()) {
            t.setTravelImage(t.getImageList().get(0).getImageUrl());
        }
        t.setTagListForView(travelMapper.selectTravelTagList(t.getTravelNo()));
        t.setThemaListForView(travelMapper.selectTravelThemaList(t.getTravelNo()));
        t.setOptionListForView(travelMapper.selectTravelOptionList(t.getTravelNo()));
    }
    

    return responseUtil.rd("200", travelList, "즐겨찾기 목록 조회 성공");
  }

}
