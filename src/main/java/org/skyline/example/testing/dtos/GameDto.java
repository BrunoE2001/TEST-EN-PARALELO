package org.skyline.example.testing.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.skyline.example.testing.annotations.UniqueValue;

@Getter @Setter
public class GameDto {
    @Size(max = 60)
    @NotBlank(message = "Title is required")
    @UniqueValue(tableName = "games", fieldName = "title", message = "The title must be unique")
    private String title;

    @Size(max = 100)
    @NotBlank(message = "Genre is required")
    @UniqueValue(tableName = "games", fieldName = "genre", message = "The genre must be unique")
    private String genre;

    @Size(max = 50)
    @NotBlank(message = "Platform is required")
    private String platform;
}
