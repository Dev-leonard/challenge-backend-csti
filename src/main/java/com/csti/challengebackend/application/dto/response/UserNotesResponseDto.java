package com.csti.challengebackend.application.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class UserNotesResponseDto {

    private String username;
    private String email;
    private List<NoteResponseDto> notes;

}
