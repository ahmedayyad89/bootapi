package com.project.mini.repository;

import com.project.mini.model.NoteModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<NoteModel, Date> {
    Optional<NoteModel> findByDate(Date date);
}
