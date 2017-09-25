package com.project.mini.repository;

import com.project.mini.model.PredefinedNotesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredefinedNoteRepository extends CrudRepository<PredefinedNotesModel, Integer> {
    PredefinedNotesModel getById(Integer id);
}
