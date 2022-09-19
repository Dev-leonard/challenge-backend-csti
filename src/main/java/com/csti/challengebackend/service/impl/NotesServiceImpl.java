package com.csti.challengebackend.service.impl;

import com.csti.challengebackend.application.dto.request.NoteRequestDto;
import com.csti.challengebackend.application.dto.response.UserNotesResponseDto;
import com.csti.challengebackend.application.mapper.NotesMapper;
import com.csti.challengebackend.infrastructure.Utils.JwtUtils;
import com.csti.challengebackend.infrastructure.exception.custom.UserNotFoundException;
import com.csti.challengebackend.persistence.entities.User;
import com.csti.challengebackend.persistence.repository.UserRepository;
import com.csti.challengebackend.service.NotesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotesServiceImpl implements NotesService {

    private UserRepository userRepository;
    private NotesMapper notesMapper;
    private JwtUtils jwtUtils;


    @Override
    public UserNotesResponseDto getAllNotes() {
        String username = jwtUtils.getUsernameFromCurrentSession();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return notesMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserNotesResponseDto createNotes(NoteRequestDto noteRequestDto) {
        String username = jwtUtils.getUsernameFromCurrentSession();
        User oldUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        oldUser.getNotes().add(notesMapper.toEntity(noteRequestDto));
        User newUser = userRepository.save(oldUser);
        return notesMapper.toDto(newUser);
    }
}
