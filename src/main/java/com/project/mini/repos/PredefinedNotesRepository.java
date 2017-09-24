package com.project.mini.repos;

import com.project.mini.model.PredefinedNotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "pDNRepository")
public interface PredefinedNotesRepository extends CrudRepository<PredefinedNotes, Integer> {
    PredefinedNotes getById( Integer id);
}
