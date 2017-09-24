package com.project.mini.repository;

import com.project.mini.model.PredefinedNoteModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "pDNRepository")
public interface PredefinedNoteRepository extends CrudRepository<PredefinedNoteModel, Integer> {
    PredefinedNoteModel getById(Integer id);
}
