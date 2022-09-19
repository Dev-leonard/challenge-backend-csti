package com.csti.challengebackend.persistence.repository;

import com.csti.challengebackend.persistence.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
}
