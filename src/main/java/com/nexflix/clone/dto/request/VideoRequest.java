package com.nexflix.clone.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class VideoRequest {
    @NotBlank(message = "El título del video es requerido")
    private String title;

    @Size(max=400, message = "La descripción no puede tener más de 400 caracteres")
    private String description;

    private String rating, src, poster;
    private Integer year, duration;
    private Boolean published;
    private List<String> categories;

}
