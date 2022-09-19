package com.csti.challengebackend.application.controller;

import com.csti.challengebackend.application.dto.request.NoteRequestDto;
import com.csti.challengebackend.application.dto.response.GenericResponse;
import com.csti.challengebackend.application.dto.response.UserNotesResponseDto;
import com.csti.challengebackend.service.impl.NotesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notes")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserNotesController {

    private NotesServiceImpl notesService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<UserNotesResponseDto> getAllNotes() {
        log.info("[UserNotesController:getAllNotes]");
        final var response = new GenericResponse<UserNotesResponseDto>();
        response.setSuccess(true);
        response.setData(notesService.getAllNotes());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<UserNotesResponseDto> createNotes(@RequestBody NoteRequestDto noteRequestDto) {
        log.info("[UserNotesController:createNotes]: NoteRequestDto:{}", noteRequestDto);
        final var response = new GenericResponse<UserNotesResponseDto>();
        response.setSuccess(true);
        response.setData(notesService.createNotes(noteRequestDto));
        return response;
    }

}
