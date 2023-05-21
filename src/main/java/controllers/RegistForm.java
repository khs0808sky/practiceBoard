package controllers;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistForm {
    @NotBlank
    @Size(max = 25)
    private String title;

    @NotBlank
    @Size(max = 50)
    private String content;
}
