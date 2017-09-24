package com.project.mini.dro;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class NoteDRO {
    @NotBlank(message = "note must not be empty")
    private String note;
}
