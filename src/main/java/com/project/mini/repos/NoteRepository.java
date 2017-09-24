package com.project.mini.repos;

import com.project.mini.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Date> {
    Optional<Note> findByDate(Date date);
}
