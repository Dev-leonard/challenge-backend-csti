package com.csti.challengebackend.service;

import com.csti.challengebackend.application.dto.request.NoteRequestDto;
import com.csti.challengebackend.application.dto.response.UserNotesResponseDto;

public interface NotesService {

    UserNotesResponseDto getAllNotes() throws NoSuchFieldException;
    UserNotesResponseDto createNotes(NoteRequestDto noteRequestDto);

}
