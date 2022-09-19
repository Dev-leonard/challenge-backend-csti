package com.csti.challengebackend.application.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class NoteResponseDto {

    private String teacher;
    private String course;
    private Integer note;
    private Date createDate;

}
