package com.project.mini.dro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredefinedNotesDRO {
    private Integer id;

    @NotBlank(message = "Note must note be empty")
    private String message;

    public PredefinedNotesDRO(){}
}
