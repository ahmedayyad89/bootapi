package com.project.mini.dro;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class PredfinedNotesDRO {
    private Integer id;

    @NotBlank(message = "Note must note be empty")
    private String message;
}
