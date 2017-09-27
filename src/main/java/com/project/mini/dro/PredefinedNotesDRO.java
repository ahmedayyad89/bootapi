package com.project.mini.dro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
@AllArgsConstructor
public class PredefinedNotesDRO {
    private Integer id;

    @NotBlank(message = "Note must note be empty")
    private String message;

    public PredefinedNotesDRO(){}
}
