package com.csti.challengebackend.application.mapper;


import com.csti.challengebackend.application.dto.request.NoteRequestDto;
import com.csti.challengebackend.application.dto.response.NoteResponseDto;
import com.csti.challengebackend.application.dto.response.UserNotesResponseDto;
import com.csti.challengebackend.persistence.entities.Note;
import com.csti.challengebackend.persistence.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class NotesMapper {

    public UserNotesResponseDto toDto(User user) {
        return UserNotesResponseDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .notes(user.getNotes()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public NoteResponseDto toDto(Note note) {
        return NoteResponseDto.builder()
                .note(note.getNote())
                .course(note.getCourse())
                .teacher(note.getTeacher())
                .createDate(note.getCreateDate())
                .build();
    }

    public Note toEntity(NoteRequestDto note) {
        return Note.builder()
                .note(note.getNote())
                .course(note.getCourse())
                .teacher(note.getTeacher())
                .build();
    }

}
