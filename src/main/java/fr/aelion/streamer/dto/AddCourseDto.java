package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.Module;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class AddCourseDto {
    @NotBlank(message = "Title cannot be null")
    @Size(max = 255, message="Title cannot exceed 255 characters")
    private String title;

    private String objective;

    private LocalDate createdAt;

    private LocalDate updatedAt;

}
