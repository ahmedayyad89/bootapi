package com.project.mini.repository;

import com.project.mini.model.PredefinedNotesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PredefinedNotesRepository extends CrudRepository<PredefinedNotesModel, Integer> {
    Optional<PredefinedNotesModel> findById(Integer id);
    List<PredefinedNotesModel> findAll();
}
