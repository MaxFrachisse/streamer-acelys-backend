package fr.aelion.streamer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MediaDto {
    private int id;
    private String title;
    private String summary;
    private float duration;
    private LocalDate createdAt;
    private String url;
}
