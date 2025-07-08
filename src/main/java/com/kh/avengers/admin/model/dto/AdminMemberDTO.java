package com.kh.avengers.admin.model.dto;

import java.sql.Date;

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
public class AdminMemberDTO {
    private Long memberNo;
    private Long reviewNo;      
    private Long travelNo;
    private String memberId;
    private String memberName;
    private String email;
    private String memberRole;
    private String isActive;
    private Date enrollDate;
    private Date modifiedDate;

}
