package fr.aelion.streamer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AddCourseDto {
    @NotBlank(message = "Title cannot be null")
    @Size(max = 255, message="Title cannot exceed 255 characters")
    private String title;

    private String objective;

    private Date createdAt;

    private Date updatedAt;
}
